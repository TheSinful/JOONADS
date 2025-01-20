package me.jooon.JooonAddons.mixin;

import me.jooon.JooonAddons.utils.core.Cosmetics;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LayerCape.class})
public abstract class MixinLayerCape implements LayerRenderer<AbstractClientPlayer> {
   @Shadow
   @Final
   private RenderPlayer field_177167_a;

   @Inject(
      method = {"doRenderLayer(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V",
   shift = At.Shift.BEFORE
)}
   )
   private void scaleChild(AbstractClientPlayer entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, CallbackInfo ci) {
      if (this.field_177167_a.func_177087_b().field_78091_s) {
         GlStateManager.func_179139_a(0.5D, 0.5D, 0.5D);
         GlStateManager.func_179109_b(0.0F, 24.0F * scale, 0.0F);
      }

   }

   @Inject(
      method = {"doRenderLayer(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderCustomCape(AbstractClientPlayer entitylivingbaseIn, float f, float g, float partialTicks, float h, float i, float j, float scale, CallbackInfo ci) {
      ResourceLocation textureLocation = Cosmetics.getCustomCape(entitylivingbaseIn.func_70005_c_());
      if (textureLocation != null) {
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         this.field_177167_a.func_110776_a(textureLocation);
         GlStateManager.func_179094_E();
         if (this.field_177167_a.func_177087_b().field_78091_s) {
            GlStateManager.func_179139_a(0.5D, 0.5D, 0.5D);
            GlStateManager.func_179109_b(0.0F, 24.0F * scale, 0.2F);
         } else {
            GlStateManager.func_179109_b(0.0F, 0.0F, 0.125F);
         }

         double d = entitylivingbaseIn.field_71091_bM + (entitylivingbaseIn.field_71094_bP - entitylivingbaseIn.field_71091_bM) * (double)partialTicks - (entitylivingbaseIn.field_70169_q + (entitylivingbaseIn.field_70165_t - entitylivingbaseIn.field_70169_q) * (double)partialTicks);
         double e = entitylivingbaseIn.field_71096_bN + (entitylivingbaseIn.field_71095_bQ - entitylivingbaseIn.field_71096_bN) * (double)partialTicks - (entitylivingbaseIn.field_70167_r + (entitylivingbaseIn.field_70163_u - entitylivingbaseIn.field_70167_r) * (double)partialTicks);
         double k = entitylivingbaseIn.field_71097_bO + (entitylivingbaseIn.field_71085_bR - entitylivingbaseIn.field_71097_bO) * (double)partialTicks - (entitylivingbaseIn.field_70166_s + (entitylivingbaseIn.field_70161_v - entitylivingbaseIn.field_70166_s) * (double)partialTicks);
         float l = entitylivingbaseIn.field_70760_ar + (entitylivingbaseIn.field_70761_aq - entitylivingbaseIn.field_70760_ar) * partialTicks;
         double m = (double)MathHelper.func_76126_a(l * 3.1415927F / 180.0F);
         double n = (double)(-MathHelper.func_76134_b(l * 3.1415927F / 180.0F));
         float o = (float)e * 10.0F;
         o = MathHelper.func_76131_a(o, -6.0F, 32.0F);
         float p = (float)(d * m + k * n) * 100.0F;
         float q = (float)(d * n - k * m) * 100.0F;
         if (p < 0.0F) {
            p = 0.0F;
         }

         float r = entitylivingbaseIn.field_71107_bF + (entitylivingbaseIn.field_71109_bG - entitylivingbaseIn.field_71107_bF) * partialTicks;
         o += MathHelper.func_76126_a((entitylivingbaseIn.field_70141_P + (entitylivingbaseIn.field_70140_Q - entitylivingbaseIn.field_70141_P) * partialTicks) * 6.0F) * 32.0F * r;
         if (entitylivingbaseIn.func_70093_af()) {
            o += 25.0F;
         }

         GlStateManager.func_179114_b(6.0F + p / 2.0F + o, 1.0F, 0.0F, 0.0F);
         GlStateManager.func_179114_b(q / 2.0F, 0.0F, 0.0F, 1.0F);
         GlStateManager.func_179114_b(-q / 2.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179114_b(180.0F, 0.0F, 1.0F, 0.0F);
         this.field_177167_a.func_177087_b().func_178728_c(0.0625F);
         GlStateManager.func_179121_F();
         ci.cancel();
      }
   }
}
