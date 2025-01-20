package me.jooon.JooonAddons.features.other;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.events.impl.PacketEvent;
import me.jooon.JooonAddons.features.Feature;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C16PacketClientStatus.EnumState;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0006\u0010\u0013\u001a\u00020\u0010R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0014"},
   d2 = {"Lme/jooon/JooonAddons/features/other/Ping;", "Lme/jooon/JooonAddons/features/Feature;", "()V", "lastPingTime", "", "getLastPingTime", "()J", "setLastPingTime", "(J)V", "ping", "", "getPing", "()D", "setPing", "(D)V", "onPacket", "", "event", "Lme/jooon/JooonAddons/events/impl/PacketEvent$ReceiveEvent;", "sendPing", "JooonAddons"}
)
public final class Ping extends Feature {
   @NotNull
   public static final Ping INSTANCE = new Ping();
   private static long lastPingTime = -1L;
   private static double ping = -1.0D;

   private Ping() {
   }

   public final long getLastPingTime() {
      return lastPingTime;
   }

   public final void setLastPingTime(long <set-?>) {
      lastPingTime = var1;
   }

   public final double getPing() {
      return ping;
   }

   public final void setPing(double <set-?>) {
      ping = var1;
   }

   @SubscribeEvent
   public final void onPacket(@NotNull PacketEvent.ReceiveEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (lastPingTime > 0L) {
         if (event.getPacket() instanceof S01PacketJoinGame) {
            lastPingTime = -1L;
         }

         if (event.getPacket() instanceof S37PacketStatistics) {
            double diff = (double)Math.abs(System.nanoTime() - lastPingTime) / 1000000.0D;
            lastPingTime *= (long)-1;
            ping = diff;
         }
      }

      if (lastPingTime < 0L && (this.getMc().field_71462_r != null || this.getMc().field_71439_g != null) && System.nanoTime() - Math.abs(lastPingTime) > 5000000000L) {
         this.sendPing();
      }

   }

   public final void sendPing() {
      if (this.getMc().field_71439_g != null) {
         this.getMc().field_71439_g.field_71174_a.func_147298_b().func_179288_a((Packet)(new C16PacketClientStatus(EnumState.REQUEST_STATS)), Ping::sendPing$lambda$0, new GenericFutureListener[0]);
      }
   }

   private static final void sendPing$lambda$0(Future it) {
      Ping var10000 = INSTANCE;
      lastPingTime = System.nanoTime();
   }
}
