package gg.essential.loader.stage1;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.CoreModManager;
import net.minecraftforge.fml.relauncher.FMLRelaunchLog;

public class DelayedStage0Tweaker implements ITweaker {
   private static final String FML_TWEAKER = "net.minecraftforge.fml.common.launcher.FMLTweaker";
   private static final String COMMAND_LINE_COREMODS_PROP = "fml.coreMods.load";
   private static ITweaker realStage0;
   private static String[] commandLineCoremods;
   private final ITweaker stage1;

   public DelayedStage0Tweaker() throws Exception {
      if (commandLineCoremods.length > 0) {
         System.setProperty("fml.coreMods.load", String.join(",", commandLineCoremods));
      }

      this.stage1 = new EssentialSetupTweaker(realStage0);
      System.clearProperty("fml.coreMods.load");
      String[] var1 = commandLineCoremods;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         String commandLineCoremod = var1[var3];
         FMLRelaunchLog.info("Found a command line coremod : %s", new Object[]{commandLineCoremod});
         Method loadCoreMod = CoreModManager.class.getDeclaredMethod("loadCoreMod", LaunchClassLoader.class, String.class, File.class);
         loadCoreMod.setAccessible(true);
         ITweaker tweaker = (ITweaker)loadCoreMod.invoke((Object)null, Launch.classLoader, commandLineCoremod, null);
         if (tweaker != null) {
            List<ITweaker> tweakers = (List)Launch.blackboard.get("Tweaks");
            tweakers.add(tweaker);
         }
      }

   }

   public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
      this.stage1.acceptOptions(args, gameDir, assetsDir, profile);
   }

   public void injectIntoClassLoader(LaunchClassLoader classLoader) {
      this.stage1.injectIntoClassLoader(classLoader);
   }

   public String getLaunchTarget() {
      return this.stage1.getLaunchTarget();
   }

   public String[] getLaunchArguments() {
      return this.stage1.getLaunchArguments();
   }

   public static boolean isRequired() {
      List<ITweaker> currentCycle = (List)Launch.blackboard.get("Tweaks");
      return currentCycle.stream().anyMatch((it) -> {
         return it.getClass().getName().equals("net.minecraftforge.fml.common.launcher.FMLTweaker");
      });
   }

   public static void prepare(ITweaker stage0) {
      if (realStage0 != null) {
         throw new IllegalStateException("Can only delay one stage0 tweaker. Why are there multiple anyway?");
      } else {
         realStage0 = stage0;
         String commandLineCoremodsStr = System.getProperty("fml.coreMods.load", "");
         commandLineCoremods = commandLineCoremodsStr.isEmpty() ? new String[0] : commandLineCoremodsStr.split(",");
         System.clearProperty("fml.coreMods.load");
      }
   }

   public static void inject() {
      List<String> nextCycle = (List)Launch.blackboard.get("TweakClasses");
      nextCycle.add(DelayedStage0Tweaker.class.getName());
      Map<String, String> launchArgs = (Map)Launch.blackboard.get("launchArgs");
      String prevValue = (String)launchArgs.put("--tweakClass", "gg.essential.loader.stage0.EssentialSetupTweaker");
      if (prevValue != null) {
         throw new UnsupportedOperationException("Cannot re-register Essential tweaker because \"" + prevValue + "\" was already there. This will require a more complex implementation.");
      }
   }
}
