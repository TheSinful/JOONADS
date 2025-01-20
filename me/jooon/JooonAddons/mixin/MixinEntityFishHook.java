package me.jooon.JooonAddons.mixin;

import me.jooon.JooonAddons.hooks.render.FishEntityHooked;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFishHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityFishHook.class})
public abstract class MixinEntityFishHook {
   @Shadow
   public Entity field_146043_c;

   @Inject(
      method = {"onUpdate"},
      at = {@At("RETURN")}
   )
   public void onHook(CallbackInfo ci) {
      if (this.field_146043_c != null) {
         FishEntityHooked.fishEntityHooked(this.field_146043_c);
      }

   }
}
