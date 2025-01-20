package gg.essential.loader.stage1;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.CoreModManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EssentialSetupTweaker implements ITweaker {
   private static final Logger LOGGER = LogManager.getLogger(EssentialSetupTweaker.class);
   private final ITweaker stage0;
   private final EssentialLoader loader;

   public EssentialSetupTweaker(ITweaker stage0) throws Exception {
      this.stage0 = stage0;
      if (DelayedStage0Tweaker.isRequired()) {
         DelayedStage0Tweaker.prepare(stage0);
         this.loader = null;
      } else {
         EssentialSetupTweaker.Forge forge = EssentialSetupTweaker.Forge.getIfPresent();
         EssentialSetupTweaker.Unknown unknown = new EssentialSetupTweaker.Unknown.Impl();
         EssentialSetupTweaker.Platform platform = forge != null ? forge : unknown;
         ((EssentialSetupTweaker.Platform)platform).setupPreLoad(this);
         this.loader = EssentialLoader.getInstance(((EssentialSetupTweaker.Platform)platform).getVersion());
         this.loader.load(Launch.minecraftHome.toPath());
         ((EssentialSetupTweaker.Platform)platform).setupPostLoad(this);
      }
   }

   public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
   }

   public void injectIntoClassLoader(LaunchClassLoader classLoader) {
      if (this.loader == null) {
         DelayedStage0Tweaker.inject();
      } else {
         this.loader.initialize();
      }
   }

   public String getLaunchTarget() {
      return null;
   }

   public String[] getLaunchArguments() {
      return new String[0];
   }

   private interface Forge extends EssentialSetupTweaker.Platform {
      static EssentialSetupTweaker.Forge getIfPresent() throws IOException {
         return Launch.classLoader.getClassBytes("net.minecraftforge.common.ForgeVersion") != null ? getUnchecked() : null;
      }

      static EssentialSetupTweaker.Forge getUnchecked() {
         return new EssentialSetupTweaker.Forge.Impl();
      }

      public static class Impl implements EssentialSetupTweaker.Forge {
         private static final String MIXIN_TWEAKER = "org.spongepowered.asm.launch.MixinTweaker";

         public String getVersion() {
            try {
               return "forge_" + ForgeVersion.class.getDeclaredField("mcVersion").get((Object)null);
            } catch (NoSuchFieldException | IllegalAccessException var2) {
               var2.printStackTrace();
               return "unknown";
            }
         }

         public void setupPostLoad(EssentialSetupTweaker stage1) throws Exception {
            List<EssentialSetupTweaker.Forge.Impl.SourceFile> sourceFiles = this.getSourceFiles(stage1.stage0.getClass());
            if (sourceFiles.isEmpty()) {
               System.out.println("Not able to determine current file. Mod will NOT work");
            } else {
               Iterator var3 = sourceFiles.iterator();

               while(var3.hasNext()) {
                  EssentialSetupTweaker.Forge.Impl.SourceFile sourceFile = (EssentialSetupTweaker.Forge.Impl.SourceFile)var3.next();
                  this.setupSourceFile(sourceFile);
               }

            }
         }

         private void setupSourceFile(EssentialSetupTweaker.Forge.Impl.SourceFile sourceFile) throws Exception {
            Field ignoredModFile = CoreModManager.class.getDeclaredField("ignoredModFiles");
            ignoredModFile.setAccessible(true);
            ((List)ignoredModFile.get((Object)null)).remove(sourceFile.file.getName());
            CoreModManager.getReparseableCoremods().add(sourceFile.file.getName());
            String coreMod = sourceFile.coreMod;
            if (coreMod != null && !sourceFile.mixin) {
               Method loadCoreMod = CoreModManager.class.getDeclaredMethod("loadCoreMod", LaunchClassLoader.class, String.class, File.class);
               loadCoreMod.setAccessible(true);
               ITweaker tweaker = (ITweaker)loadCoreMod.invoke((Object)null, Launch.classLoader, coreMod, sourceFile.file);
               ((List)Launch.blackboard.get("Tweaks")).add(tweaker);
            }

            if (sourceFile.mixin) {
               try {
                  this.injectMixinTweaker();
                  Class<?> MixinBootstrap = Class.forName("org.spongepowered.asm.launch.MixinBootstrap");
                  Class<?> MixinPlatformManager = Class.forName("org.spongepowered.asm.launch.platform.MixinPlatformManager");
                  Object platformManager = MixinBootstrap.getDeclaredMethod("getPlatform").invoke((Object)null);

                  Method addContainer;
                  Object arg;
                  try {
                     addContainer = MixinPlatformManager.getDeclaredMethod("addContainer", URI.class);
                     arg = sourceFile.file.toURI();
                  } catch (NoSuchMethodException var12) {
                     Class<?> IContainerHandle = Class.forName("org.spongepowered.asm.launch.platform.container.IContainerHandle");
                     Class<?> ContainerHandleURI = Class.forName("org.spongepowered.asm.launch.platform.container.ContainerHandleURI");
                     addContainer = MixinPlatformManager.getDeclaredMethod("addContainer", IContainerHandle);
                     arg = ContainerHandleURI.getDeclaredConstructor(URI.class).newInstance(sourceFile.file.toURI());
                  }

                  addContainer.invoke(platformManager, arg);
               } catch (Exception var13) {
                  var13.printStackTrace();
               }
            }

         }

         private List<EssentialSetupTweaker.Forge.Impl.SourceFile> getSourceFiles(Class<?> tweakerClass) {
            String tweakerClassName = tweakerClass.getName();
            List<EssentialSetupTweaker.Forge.Impl.SourceFile> sourceFiles = new ArrayList();
            Iterator var4 = Launch.classLoader.getSources().iterator();

            while(var4.hasNext()) {
               URL url = (URL)var4.next();

               try {
                  URI uri = url.toURI();
                  if ("file".equals(uri.getScheme())) {
                     File file = new File(uri);
                     if (file.exists() && file.isFile()) {
                        String tweakClass = null;
                        String coreMod = null;
                        boolean mixin = false;
                        JarFile jar = new JarFile(file);
                        Throwable var12 = null;

                        try {
                           if (jar.getManifest() != null) {
                              Attributes attributes = jar.getManifest().getMainAttributes();
                              tweakClass = attributes.getValue("TweakClass");
                              coreMod = attributes.getValue("FMLCorePlugin");
                              mixin = attributes.getValue("MixinConfigs") != null;
                           }
                        } catch (Throwable var22) {
                           var12 = var22;
                           throw var22;
                        } finally {
                           if (jar != null) {
                              if (var12 != null) {
                                 try {
                                    jar.close();
                                 } catch (Throwable var21) {
                                    var12.addSuppressed(var21);
                                 }
                              } else {
                                 jar.close();
                              }
                           }

                        }

                        if (tweakerClassName.equals(tweakClass)) {
                           sourceFiles.add(new EssentialSetupTweaker.Forge.Impl.SourceFile(file, coreMod, mixin));
                        }
                     }
                  }
               } catch (Exception var24) {
                  EssentialSetupTweaker.LOGGER.error("Failed to read manifest from " + url + ":", var24);
               }
            }

            return sourceFiles;
         }

         private void injectMixinTweaker() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
            List<String> tweakClasses = (List)Launch.blackboard.get("TweakClasses");
            if (tweakClasses.contains("org.spongepowered.asm.launch.MixinTweaker")) {
               this.initMixinTweaker();
            } else if (Launch.blackboard.get("mixin.initialised") == null) {
               System.out.println("Injecting MixinTweaker from EssentialSetupTweaker");
               List<ITweaker> tweaks = (List)Launch.blackboard.get("Tweaks");
               tweaks.add(this.initMixinTweaker());
            }
         }

         private ITweaker initMixinTweaker() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
            Launch.classLoader.addClassLoaderExclusion("org.spongepowered.asm.launch.MixinTweaker".substring(0, "org.spongepowered.asm.launch.MixinTweaker".lastIndexOf(46)));
            return (ITweaker)Class.forName("org.spongepowered.asm.launch.MixinTweaker", true, Launch.classLoader).newInstance();
         }

         private static class SourceFile {
            final File file;
            final String coreMod;
            final boolean mixin;

            private SourceFile(File file, String coreMod, boolean mixin) {
               this.file = file;
               this.coreMod = coreMod;
               this.mixin = mixin;
            }

            // $FF: synthetic method
            SourceFile(File x0, String x1, boolean x2, Object x3) {
               this(x0, x1, x2);
            }
         }
      }
   }

   private interface Unknown extends EssentialSetupTweaker.Platform {
      public static class Impl implements EssentialSetupTweaker.Unknown {
         public String getVersion() {
            return "unknown";
         }
      }
   }

   private interface Platform {
      String getVersion();

      default void setupPreLoad(EssentialSetupTweaker stage1) throws Exception {
      }

      default void setupPostLoad(EssentialSetupTweaker stage1) throws Exception {
      }
   }
}
