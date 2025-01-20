package me.jooon.JooonAddons.mixin;

import me.jooon.JooonAddons.hooks.render.RenderEntityHookKt;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({RenderManager.class})
public abstract class MixinRenderManager {
   @Shadow
   public TextureManager field_78724_e;

   @Shadow
   public abstract <T extends Entity> Render<T> func_78713_a(Entity var1);

   @Inject(
      method = {"doRenderEntity(Lnet/minecraft/entity/Entity;DDDFFZ)Z"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void onRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks, boolean p_147939_10_, CallbackInfoReturnable<Boolean> cir) {
      if (entity instanceof EntityLivingBase) {
         Render<Entity> render = this.func_78713_a(entity);
         if (this.field_78724_e != null && render instanceof RendererLivingEntity) {
            RenderEntityHookKt.onRenderEntityModel((EntityLivingBase)entity, ((IMixinRendererLivingEntity)render).getMainModel(), partialTicks, cir);
         }
      }
   }
}
