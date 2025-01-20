package me.jooon.JooonAddons.events.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.events.JooonAddonsEvent;
import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Cancelable
@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0017\u0018\u00002\u00020\u0001:\u0003\r\u000e\u000fB\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"},
   d2 = {"Lme/jooon/JooonAddons/events/impl/PacketEvent;", "Lme/jooon/JooonAddons/events/JooonAddonsEvent;", "packet", "Lnet/minecraft/network/Packet;", "(Lnet/minecraft/network/Packet;)V", "direction", "Lme/jooon/JooonAddons/events/impl/PacketEvent$Direction;", "getDirection", "()Lme/jooon/JooonAddons/events/impl/PacketEvent$Direction;", "setDirection", "(Lme/jooon/JooonAddons/events/impl/PacketEvent$Direction;)V", "getPacket", "()Lnet/minecraft/network/Packet;", "Direction", "ReceiveEvent", "SendEvent", "JooonAddons"}
)
public class PacketEvent extends JooonAddonsEvent {
   @NotNull
   private final Packet<?> packet;
   @Nullable
   private PacketEvent.Direction direction;

   public PacketEvent(@NotNull Packet<?> packet) {
      Intrinsics.checkNotNullParameter(packet, "packet");
      super();
      this.packet = packet;
   }

   @NotNull
   public final Packet<?> getPacket() {
      return this.packet;
   }

   @Nullable
   public final PacketEvent.Direction getDirection() {
      return this.direction;
   }

   public final void setDirection(@Nullable PacketEvent.Direction <set-?>) {
      this.direction = var1;
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"},
      d2 = {"Lme/jooon/JooonAddons/events/impl/PacketEvent$Direction;", "", "(Ljava/lang/String;I)V", "INBOUND", "OUTBOUND", "JooonAddons"}
   )
   public static enum Direction {
      INBOUND,
      OUTBOUND;

      // $FF: synthetic method
      private static final PacketEvent.Direction[] $values() {
         PacketEvent.Direction[] var0 = new PacketEvent.Direction[]{INBOUND, OUTBOUND};
         return var0;
      }
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"},
      d2 = {"Lme/jooon/JooonAddons/events/impl/PacketEvent$ReceiveEvent;", "Lme/jooon/JooonAddons/events/impl/PacketEvent;", "packet", "Lnet/minecraft/network/Packet;", "(Lnet/minecraft/network/Packet;)V", "JooonAddons"}
   )
   public static final class ReceiveEvent extends PacketEvent {
      public ReceiveEvent(@NotNull Packet<?> packet) {
         Intrinsics.checkNotNullParameter(packet, "packet");
         super(packet);
         this.setDirection(PacketEvent.Direction.INBOUND);
      }
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"},
      d2 = {"Lme/jooon/JooonAddons/events/impl/PacketEvent$SendEvent;", "Lme/jooon/JooonAddons/events/impl/PacketEvent;", "packet", "Lnet/minecraft/network/Packet;", "(Lnet/minecraft/network/Packet;)V", "JooonAddons"}
   )
   public static final class SendEvent extends PacketEvent {
      public SendEvent(@NotNull Packet<?> packet) {
         Intrinsics.checkNotNullParameter(packet, "packet");
         super(packet);
         this.setDirection(PacketEvent.Direction.OUTBOUND);
      }
   }
}
