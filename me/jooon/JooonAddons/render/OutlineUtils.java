package me.jooon.JooonAddons.render;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.mixin.IMixinRendererLivingEntity;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002JV\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0004H\u0002J\b\u0010\u0015\u001a\u00020\u0004H\u0002J\b\u0010\u0016\u001a\u00020\u0004H\u0002J\b\u0010\u0017\u001a\u00020\u0004H\u0002J\b\u0010\u0018\u001a\u00020\u0004H\u0002J(\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000bH\u0002J\u0010\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 H\u0002¨\u0006!"},
   d2 = {"Lme/jooon/JooonAddons/render/OutlineUtils;", "", "()V", "checkSetupFBO", "", "outlineEntity", "model", "Lnet/minecraft/client/model/ModelBase;", "entity", "Lnet/minecraft/entity/EntityLivingBase;", "limbSwing", "", "limbSwingAmount", "ageInTicks", "headYaw", "headPitch", "scaleFactor", "partialTicks", "color", "", "renderFive", "renderFour", "renderOne", "renderThree", "renderTwo", "setColor", "f", "f1", "f2", "f3", "setupFBO", "fbo", "Lnet/minecraft/client/shader/Framebuffer;", "JooonAddons"}
)
public final class OutlineUtils {
   @NotNull
   public static final OutlineUtils INSTANCE = new OutlineUtils();

   private OutlineUtils() {
   }

   public final void outlineEntity(@NotNull ModelBase model, @NotNull EntityLivingBase entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scaleFactor, float partialTicks, int color) {
      Intrinsics.checkNotNullParameter(model, "model");
      Intrinsics.checkNotNullParameter(entity, "entity");
      Render var10000 = JooonAddons.Companion.getMc().func_175598_ae().func_78713_a((Entity)entity);
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type me.jooon.JooonAddons.mixin.IMixinRendererLivingEntity");
      IMixinRendererLivingEntity renderer = (IMixinRendererLivingEntity)var10000;
      boolean fancyGraphics = JooonAddons.Companion.getMc().field_71474_y.field_74347_j;
      float gamma = JooonAddons.Companion.getMc().field_71474_y.field_74333_Y;
      JooonAddons.Companion.getMc().field_71474_y.field_74347_j = false;
      JooonAddons.Companion.getMc().field_71474_y.field_74333_Y = Float.MAX_VALUE;
      float f3 = (float)(color >> 24 & 255) / 255.0F;
      float f = (float)(color >> 16 & 255) / 255.0F;
      float f1 = (float)(color >> 8 & 255) / 255.0F;
      float f2 = (float)(color & 255) / 255.0F;
      GlStateManager.func_179117_G();
      this.setColor(f, f1, f2, f3);
      this.renderOne();
      model.func_78088_a((Entity)entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scaleFactor);
      this.setColor(f, f1, f2, f3);
      RenderUtils.INSTANCE.renderLayers(renderer, entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, headYaw, headPitch, scaleFactor, f, f1, f2, f3);
      this.setColor(f, f1, f2, f3);
      this.renderTwo();
      model.func_78088_a((Entity)entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scaleFactor);
      this.setColor(f, f1, f2, f3);
      RenderUtils.INSTANCE.renderLayers(renderer, entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, headYaw, headPitch, scaleFactor, f, f1, f2, f3);
      this.setColor(f, f1, f2, f3);
      this.renderThree();
      model.func_78088_a((Entity)entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scaleFactor);
      RenderUtils.INSTANCE.renderLayers(renderer, entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, headYaw, headPitch, scaleFactor, f, f1, f2, f3);
      this.setColor(f, f1, f2, f3);
      this.setColor(f, f1, f2, f3);
      this.renderFour();
      model.func_78088_a((Entity)entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scaleFactor);
      this.setColor(f, f1, f2, f3);
      RenderUtils.INSTANCE.renderLayers(renderer, entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, headYaw, headPitch, scaleFactor, f, f1, f2, f3);
      this.setColor(f, f1, f2, f3);
      this.renderFive();
      JooonAddons.Companion.getMc().field_71474_y.field_74347_j = fancyGraphics;
      JooonAddons.Companion.getMc().field_71474_y.field_74333_Y = gamma;
   }

   private final void renderOne() {
      this.checkSetupFBO();
      GL11.glPushAttrib(1048575);
      GL11.glDisable(3008);
      GL11.glDisable(3553);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glLineWidth(5.0F);
      GL11.glEnable(2848);
      GL11.glEnable(2960);
      GL11.glClear(1024);
      GL11.glClearStencil(15);
      GL11.glStencilFunc(512, 1, 15);
      GL11.glStencilOp(7681, 7681, 7681);
      GL11.glPolygonMode(1032, 6913);
   }

   private final void renderTwo() {
      GL11.glStencilFunc(512, 0, 15);
      GL11.glStencilOp(7681, 7681, 7681);
      GL11.glPolygonMode(1032, 6914);
   }

   private final void renderThree() {
      GL11.glStencilFunc(514, 1, 15);
      GL11.glStencilOp(7680, 7680, 7680);
      GL11.glPolygonMode(1032, 6913);
   }

   private final void renderFour() {
      GL11.glDepthMask(false);
      GL11.glDisable(2929);
      GL11.glEnable(10754);
      GL11.glPolygonOffset(1.0F, -2000000.0F);
      OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0F, 240.0F);
   }

   private final void renderFive() {
      GL11.glPolygonOffset(1.0F, 2000000.0F);
      GL11.glDisable(10754);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(2960);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GL11.glEnable(3042);
      GL11.glEnable(2896);
      GL11.glEnable(3553);
      GL11.glEnable(3008);
      GL11.glPopAttrib();
   }

   private final void setColor(float f, float f1, float f2, float f3) {
      GlStateManager.func_179131_c(f, f1, f2, f3);
   }

   private final void checkSetupFBO() {
      Framebuffer fbo = JooonAddons.Companion.getMc().func_147110_a();
      if (fbo != null && fbo.field_147624_h > -1) {
         this.setupFBO(fbo);
         fbo.field_147624_h = -1;
      }

   }

   private final void setupFBO(Framebuffer fbo) {
      EXTFramebufferObject.glDeleteRenderbuffersEXT(fbo.field_147624_h);
      int stencilDepthBufferId = EXTFramebufferObject.glGenRenderbuffersEXT();
      EXTFramebufferObject.glBindRenderbufferEXT(36161, stencilDepthBufferId);
      EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, JooonAddons.Companion.getMc().field_71443_c, JooonAddons.Companion.getMc().field_71440_d);
      EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, stencilDepthBufferId);
      EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, stencilDepthBufferId);
   }
}
