package me.jooon.JooonAddons.mixin;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import me.jooon.JooonAddons.hooks.render.network.NetworkManagerHookKt;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   value = {NetworkManager.class},
   priority = 999
)
public abstract class MixinNetworkManager extends SimpleChannelInboundHandler<Packet<?>> {
   @Inject(
      method = {"channelRead0*"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onReceivePacket(ChannelHandlerContext context, Packet<?> packet, CallbackInfo ci) {
      NetworkManagerHookKt.onReceivePacket(context, packet, ci);
   }
}
