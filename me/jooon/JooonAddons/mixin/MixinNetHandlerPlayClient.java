package me.jooon.JooonAddons.mixin;

import me.jooon.JooonAddons.hooks.render.network.NetHandlerPlayClientHookKt;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   value = {NetHandlerPlayClient.class},
   priority = 1001
)
public abstract class MixinNetHandlerPlayClient implements INetHandlerPlayClient {
   @Shadow
   private WorldClient field_147300_g;

   @Inject(
      method = {"addToSendQueue"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onSendPacket(Packet<?> packet, CallbackInfo ci) {
      NetHandlerPlayClientHookKt.onSendPacket(packet, ci);
   }
}
