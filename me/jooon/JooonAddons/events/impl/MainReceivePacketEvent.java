package me.jooon.JooonAddons.events.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.events.JooonAddonsEvent;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Cancelable
@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00030\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005B\u0015\u0012\u0006\u0010\u0006\u001a\u00028\u0001\u0012\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0002\u0010\bJ\u000e\u0010\u000f\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\nJ\u000e\u0010\u0010\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\rJ.\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0006\u001a\u00028\u00012\b\b\u0002\u0010\u0007\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0013\u0010\u0006\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0007\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\r¨\u0006\u001b"},
   d2 = {"Lme/jooon/JooonAddons/events/impl/MainReceivePacketEvent;", "T", "Lnet/minecraft/network/Packet;", "U", "Lnet/minecraft/network/INetHandler;", "Lme/jooon/JooonAddons/events/JooonAddonsEvent;", "handler", "packet", "(Lnet/minecraft/network/INetHandler;Lnet/minecraft/network/Packet;)V", "getHandler", "()Lnet/minecraft/network/INetHandler;", "Lnet/minecraft/network/INetHandler;", "getPacket", "()Lnet/minecraft/network/Packet;", "Lnet/minecraft/network/Packet;", "component1", "component2", "copy", "(Lnet/minecraft/network/INetHandler;Lnet/minecraft/network/Packet;)Lme/jooon/JooonAddons/events/impl/MainReceivePacketEvent;", "equals", "", "other", "", "hashCode", "", "toString", "", "JooonAddons"}
)
public final class MainReceivePacketEvent<T extends Packet<U>, U extends INetHandler> extends JooonAddonsEvent {
   @NotNull
   private final U handler;
   @NotNull
   private final T packet;

   public MainReceivePacketEvent(@NotNull U handler, @NotNull T packet) {
      Intrinsics.checkNotNullParameter(handler, "handler");
      Intrinsics.checkNotNullParameter(packet, "packet");
      super();
      this.handler = handler;
      this.packet = packet;
   }

   @NotNull
   public final U getHandler() {
      return this.handler;
   }

   @NotNull
   public final T getPacket() {
      return this.packet;
   }

   @NotNull
   public final U component1() {
      return this.handler;
   }

   @NotNull
   public final T component2() {
      return this.packet;
   }

   @NotNull
   public final MainReceivePacketEvent<T, U> copy(@NotNull U handler, @NotNull T packet) {
      Intrinsics.checkNotNullParameter(handler, "handler");
      Intrinsics.checkNotNullParameter(packet, "packet");
      return new MainReceivePacketEvent(handler, packet);
   }

   // $FF: synthetic method
   public static MainReceivePacketEvent copy$default(MainReceivePacketEvent var0, INetHandler var1, Packet var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.handler;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.packet;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "MainReceivePacketEvent(handler=" + this.handler + ", packet=" + this.packet + ')';
   }

   public int hashCode() {
      int result = this.handler.hashCode();
      result = result * 31 + this.packet.hashCode();
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof MainReceivePacketEvent)) {
         return false;
      } else {
         MainReceivePacketEvent var2 = (MainReceivePacketEvent)other;
         if (!Intrinsics.areEqual(this.handler, var2.handler)) {
            return false;
         } else {
            return Intrinsics.areEqual(this.packet, var2.packet);
         }
      }
   }
}
