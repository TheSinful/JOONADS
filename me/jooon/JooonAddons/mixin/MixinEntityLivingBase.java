package me.jooon.JooonAddons.mixin;

import me.jooon.JooonAddons.hooks.render.EntityLivingBaseHook;
import me.jooon.JooonAddons.hooks.render.ExtensionEntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
   value = {EntityLivingBase.class},
   priority = 999
)
public abstract class MixinEntityLivingBase extends Entity implements ExtensionEntityLivingBase {
   @Unique
   private final EntityLivingBaseHook hook = new EntityLivingBaseHook((EntityLivingBase)this);

   public MixinEntityLivingBase(World worldIn) {
      super(worldIn);
   }

   @Inject(
      method = {"isChild"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void setChildState(CallbackInfoReturnable<Boolean> cir) {
      this.hook.isChild(cir);
   }

   @NotNull
   public EntityLivingBaseHook getJooonAddonsHook() {
      return this.hook;
   }
}
