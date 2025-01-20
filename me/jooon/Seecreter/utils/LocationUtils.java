package me.jooon.Seecreter.utils;

import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LocationUtils {
   private static LocationUtils.LocationType currentLocation;

   public LocationUtils() {
      ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
      executor.scheduleAtFixedRate(() -> {
         try {
            currentLocation = LocationUtils.LocationType.OTHER;
            WorldClient world = Minecraft.func_71410_x().field_71441_e;
            if (world == null) {
               return;
            }

            Scoreboard scoreboard = world.func_96441_U();
            if (scoreboard == null) {
               return;
            }

            Iterator var2 = ScoreboardUtils.getSidebarScores(scoreboard).iterator();

            while(var2.hasNext()) {
               String line = (String)var2.next();
               if (line.matches("^ §7⏣ §cThe Catac§combs §7\\((\\w+)\\)$")) {
                  currentLocation = LocationUtils.LocationType.DUNGEONS;
                  return;
               }
            }
         } catch (Throwable var4) {
            var4.printStackTrace();
         }

      }, 1000L, 1000L, TimeUnit.MILLISECONDS);
   }

   @SubscribeEvent
   public void onWorldLoad(Load event) {
      currentLocation = LocationUtils.LocationType.OTHER;
   }

   public static LocationUtils.LocationType getCurrentLocation() {
      return currentLocation;
   }

   static {
      currentLocation = LocationUtils.LocationType.OTHER;
   }

   public static enum LocationType {
      DUNGEONS,
      OTHER;
   }
}
