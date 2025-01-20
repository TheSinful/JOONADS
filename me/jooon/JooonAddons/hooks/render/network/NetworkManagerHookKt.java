package me.jooon.JooonAddons.hooks.render.network;

import io.netty.channel.ChannelHandlerContext;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.events.impl.PacketEvent;
import net.minecraft.network.Packet;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Metadata(
   mv = {1, 8, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"},
   d2 = {"onReceivePacket", "", "context", "Lio/netty/channel/ChannelHandlerContext;", "packet", "Lnet/minecraft/network/Packet;", "ci", "Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfo;", "JooonAddons"}
)
public final class NetworkManagerHookKt {
   public static final void onReceivePacket(@NotNull ChannelHandlerContext context, @NotNull Packet<?> packet, @NotNull CallbackInfo ci) {
      Intrinsics.checkNotNullParameter(context, "context");
      Intrinsics.checkNotNullParameter(packet, "packet");
      Intrinsics.checkNotNullParameter(ci, "ci");
      if ((new PacketEvent.ReceiveEvent(packet)).postAndCatch()) {
         ci.cancel();
      }

   }
}
