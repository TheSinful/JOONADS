package me.jooon.Seecreter.features;

import cc.polyfrost.oneconfig.events.EventManager;
import cc.polyfrost.oneconfig.events.event.ReceivePacketEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import me.jooon.Seecreter.config.SeecreterConfig;
import me.jooon.Seecreter.utils.LocationUtils;
import me.jooon.Seecreter.utils.PacketUtils;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class AutoClose {
   private static Integer closeId = null;

   public AutoClose() {
      EventManager.INSTANCE.register(this);
   }

   @SubscribeEvent
   public void onTick(ClientTickEvent event) {
      if (event.phase == Phase.END && closeId != null) {
         PacketUtils.sendPacket(new C0DPacketCloseWindow(closeId));
         closeId = null;
      }
   }

   @Subscribe
   public void onPacketReceive(ReceivePacketEvent event) {
      if (SeecreterConfig.secretAuraAutoClose && SeecreterConfig.secretAuraEnabled && event.packet instanceof S2DPacketOpenWindow) {
         if (LocationUtils.getCurrentLocation() == LocationUtils.LocationType.DUNGEONS || SeecreterConfig.secretAuraNotDungeon) {
            S2DPacketOpenWindow packet = (S2DPacketOpenWindow)event.packet;
            if (packet.func_148902_e().equals("minecraft:chest")) {
               if (packet.func_179840_c().func_150254_d().equals("Chest§r") && packet.func_148898_f() == 27 || packet.func_179840_c().func_150254_d().equals("Large Chest§r") && packet.func_148898_f() == 54) {
                  closeId = packet.func_148901_c();
                  event.isCancelled = true;
               }

            }
         }
      }
   }
}
