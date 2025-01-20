package me.jooon.Seecreter.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class DungeonLocationUtils {
   private static int roomX = -1;
   private static int roomZ = -1;
   private static int currentRoomId = 0;

   @SubscribeEvent
   public void onTick(ClientTickEvent event) {
      if (event.phase == Phase.END) {
         if (LocationUtils.getCurrentLocation() != LocationUtils.LocationType.DUNGEONS) {
            roomX = -1;
            roomZ = -1;
            currentRoomId = 0;
         } else {
            Minecraft mc = Minecraft.func_71410_x();
            EntityPlayerSP player = mc.field_71439_g;
            if (player != null) {
               int prevRoomX = roomX;
               int prevRoomZ = roomZ;
               roomX = (int)((player.field_70165_t + 200.0D) / 32.0D);
               roomZ = (int)((player.field_70161_v + 200.0D) / 32.0D);
               if (prevRoomX != roomX || prevRoomZ != roomZ) {
                  int cx = -185 + roomX * 32;
                  int cz = -185 + roomZ * 32;
                  List<Integer> blocks = new ArrayList();

                  for(int y = 140; y > 11; --y) {
                     int id = Block.func_149682_b(mc.field_71441_e.func_180495_p(new BlockPos(cx, y, cz)).func_177230_c());
                     if (id != 5 && id != 54) {
                        blocks.add(id);
                     }
                  }

                  currentRoomId = ((String)blocks.stream().map(String::valueOf).collect(Collectors.joining())).hashCode();
               }

            }
         }
      }
   }

   public static int getCurrentRoomId() {
      return currentRoomId;
   }
}
