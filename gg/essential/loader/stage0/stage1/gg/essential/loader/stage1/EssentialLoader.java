package gg.essential.loader.stage1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;

public final class EssentialLoader extends EssentialLoaderBase {
   private static EssentialLoader instance;

   public static synchronized EssentialLoader getInstance(String gameVersion) {
      if (instance == null) {
         instance = new EssentialLoader(gameVersion);
      }

      return instance;
   }

   private EssentialLoader(String gameVersion) {
      super("launchwrapper", gameVersion);
   }

   protected ClassLoader addToClassLoader(URL stage2Url) throws Exception {
      LaunchClassLoader classLoader = Launch.classLoader;
      classLoader.addURL(stage2Url);
      classLoader.addClassLoaderExclusion("gg.essential.loader.stage2.");
      addUrlHack(classLoader.getClass().getClassLoader(), stage2Url);
      return classLoader;
   }

   private static void addUrlHack(ClassLoader loader, URL url) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
      ClassLoader classLoader = Launch.classLoader.getClass().getClassLoader();
      Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
      method.setAccessible(true);
      method.invoke(classLoader, url);
   }
}
