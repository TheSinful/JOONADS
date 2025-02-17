package me.jooon.JooonAddons.mixin;

import me.jooon.JooonAddons.config.Cache;
import me.jooon.JooonAddons.utils.core.Cosmetics;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
   value = {EntityPlayer.class},
   remap = false
)
public class MixinEntityPlayer {
   @Inject(
      method = {"getDisplayNameString"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void getDisplayNameString(CallbackInfoReturnable<String> cir) {
      Cache.attempts = 0;
      cir.setReturnValue(Cosmetics.getCustomNicks((String)cir.getReturnValue()));
   }
}
