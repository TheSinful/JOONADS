package me.jooon.JooonAddons.render;

import gg.essential.elementa.font.DefaultFonts;
import gg.essential.elementa.font.ElementaFonts;
import gg.essential.elementa.font.FontProvider;
import gg.essential.universal.ChatColor;
import gg.essential.universal.UGraphics;
import gg.essential.universal.UMatrixStack;
import gg.essential.universal.UGraphics.DrawMode;
import java.awt.Color;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.mixin.IMixinMinecraft;
import me.jooon.JooonAddons.mixin.IMixinRenderManager;
import me.jooon.JooonAddons.mixin.IMixinRendererLivingEntity;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001PB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u001e\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J&\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0011J\u001e\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u0011J\u0016\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u001c2\u0006\u0010\t\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J^\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\t\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020\u00112\b\b\u0002\u0010)\u001a\u00020'H\u0002J&\u0010*\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0011J.\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020\u001f2\u0006\u0010/\u001a\u00020\u001f2\u0006\u0010\t\u001a\u00020\nJ \u00100\u001a\u0014\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001f012\u0006\u0010\u0015\u001a\u00020\u0011J\b\u00102\u001a\u00020\u0006H\u0002J \u00103\u001a\u0002042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0011H\u0002J.\u00105\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0011J\u0016\u00106\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u0002072\u0006\u0010\t\u001a\u00020\nJ\"\u00108\u001a\u00020\u00062\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010 \u001a\u00020\nH\u0007Jn\u0010;\u001a\u00020\u00062\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\b2\u0006\u0010?\u001a\u00020\u00112\u0006\u0010@\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010A\u001a\u00020\u00112\u0006\u0010B\u001a\u00020\u00112\u0006\u0010C\u001a\u00020\u00112\u0006\u0010D\u001a\u00020\u00112\u0006\u0010E\u001a\u00020\u00112\u0006\u0010F\u001a\u00020\u00112\u0006\u0010G\u001a\u00020\u00112\u0006\u0010H\u001a\u00020\u0011J@\u0010I\u001a\u00020\u00062\b\u0010J\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010 \u001a\u00020\n2\b\b\u0002\u0010K\u001a\u00020\n2\b\b\u0002\u0010L\u001a\u00020\n2\b\b\u0002\u0010M\u001a\u00020'H\u0007J^\u0010N\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001f2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010(\u001a\u00020\u00112\b\b\u0002\u0010)\u001a\u00020'2\b\b\u0002\u0010&\u001a\u00020'2\b\b\u0002\u0010O\u001a\u00020'R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Q"},
   d2 = {"Lme/jooon/JooonAddons/render/RenderUtils;", "", "()V", "beaconBeam", "Lnet/minecraft/util/ResourceLocation;", "drawBeaconBeam", "", "entity", "Lnet/minecraft/entity/EntityLivingBase;", "color", "", "type", "drawBox", "pos", "Lnet/minecraft/util/Vec3;", "Ljava/awt/Color;", "pt", "", "drawChamsEsp", "model", "Lnet/minecraft/client/model/ModelBase;", "partialTicks", "drawFilledBoundingBox", "aabb", "Lnet/minecraft/util/AxisAlignedBB;", "c", "alphaMultiplier", "drawFishingBox", "Lnet/minecraft/util/BlockPos;", "drawNametag", "x", "", "y", "z", "str", "", "matrixStack", "Lgg/essential/universal/UMatrixStack;", "shadow", "", "scale", "background", "drawOutlinedEsp", "drawRect", "left", "top", "right", "bottom", "getViewerPos", "Lkotlin/Triple;", "postModelDraw", "preModelDraw", "Lme/jooon/JooonAddons/render/RenderUtils$ModelData;", "renderBeaconBeam", "renderBoundingBox", "Lnet/minecraft/entity/Entity;", "renderItem", "itemStack", "Lnet/minecraft/item/ItemStack;", "renderLayers", "renderer", "Lme/jooon/JooonAddons/mixin/IMixinRendererLivingEntity;", "entitylivingbaseIn", "p_177093_2_", "p_177093_3_", "p_177093_5_", "p_177093_6_", "p_177093_7_", "p_177093_8_", "red", "green", "blue", "alpha", "renderTexture", "texture", "width", "height", "enableLighting", "renderWaypointText", "distance", "ModelData", "JooonAddons"}
)
public final class RenderUtils {
   @NotNull
   public static final RenderUtils INSTANCE = new RenderUtils();
   @NotNull
   private static final ResourceLocation beaconBeam = new ResourceLocation("textures/entity/beacon_beam.png");

   private RenderUtils() {
   }

   @JvmStatic
   public static final void renderItem(@Nullable ItemStack itemStack, int x, int y) {
      RenderHelper.func_74520_c();
      GlStateManager.func_179126_j();
      JooonAddons.Companion.getMc().func_175599_af().func_180450_b(itemStack, x, y);
   }

   public final void drawBox(@NotNull Vec3 pos, @NotNull Color color, float pt) {
      Intrinsics.checkNotNullParameter(pos, "pos");
      Intrinsics.checkNotNullParameter(color, "color");
      Entity var10000 = JooonAddons.Companion.getMc().func_175606_aa();
      Intrinsics.checkNotNullExpressionValue(var10000, "mc.renderViewEntity");
      Entity viewer = var10000;
      double viewerX = viewer.field_70142_S + (viewer.field_70165_t - viewer.field_70142_S) * (double)pt;
      double viewerY = viewer.field_70137_T + (viewer.field_70163_u - viewer.field_70137_T) * (double)pt;
      double viewerZ = viewer.field_70136_U + (viewer.field_70161_v - viewer.field_70136_U) * (double)pt;
      double x = pos.field_72450_a - 0.5D - viewerX;
      double y = pos.field_72448_b - viewerY + 1.5D;
      double z = pos.field_72449_c - 0.5D - viewerZ;
      this.drawFilledBoundingBox(new AxisAlignedBB(x, y, z, x + (double)1, y + (double)1, z + (double)1), color, 0.6F);
   }

   public final void drawFishingBox(@NotNull BlockPos pos, @NotNull Color color, float pt) {
      Intrinsics.checkNotNullParameter(pos, "pos");
      Intrinsics.checkNotNullParameter(color, "color");
      Entity var10000 = JooonAddons.Companion.getMc().func_175606_aa();
      Intrinsics.checkNotNullExpressionValue(var10000, "mc.renderViewEntity");
      Entity viewer = var10000;
      double viewerX = viewer.field_70142_S + (viewer.field_70165_t - viewer.field_70142_S) * (double)pt;
      double viewerY = viewer.field_70137_T + (viewer.field_70163_u - viewer.field_70137_T) * (double)pt;
      double viewerZ = viewer.field_70136_U + (viewer.field_70161_v - viewer.field_70136_U) * (double)pt;
      double x = (double)pos.func_177958_n() - viewerX;
      double y = (double)pos.func_177956_o() - viewerY;
      double z = (double)pos.func_177952_p() - viewerZ;
      this.drawFilledBoundingBox(new AxisAlignedBB(x + 0.3D, y + 0.3D, z + 0.3D, x + 0.7D, y + 0.7D, z + 0.7D), color, 0.6F);
   }

   public final void drawFilledBoundingBox(@NotNull AxisAlignedBB aabb, @NotNull Color c, float alphaMultiplier) {
      Intrinsics.checkNotNullParameter(aabb, "aabb");
      Intrinsics.checkNotNullParameter(c, "c");
      GlStateManager.func_179097_i();
      GlStateManager.func_179129_p();
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179090_x();
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      GlStateManager.func_179131_c((float)c.getRed() / 255.0F, (float)c.getGreen() / 255.0F, (float)c.getBlue() / 255.0F, (float)c.getAlpha() / 255.0F * alphaMultiplier);
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
      tessellator.func_78381_a();
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
      tessellator.func_78381_a();
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
      tessellator.func_78381_a();
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
      tessellator.func_78381_a();
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
      tessellator.func_78381_a();
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179098_w();
      GlStateManager.func_179084_k();
      GlStateManager.func_179140_f();
      GlStateManager.func_179126_j();
      GlStateManager.func_179089_o();
   }

   public final void renderBoundingBox(@NotNull Entity entity, int color) {
      Intrinsics.checkNotNullParameter(entity, "entity");
      boolean visible = false;
      if (Minecraft.func_71410_x().field_71439_g.func_70685_l(entity)) {
         visible = true;
      }

      if (!Config.INSTANCE.getDisableVisible() || !visible) {
         RenderManager var10000 = JooonAddons.Companion.getMc().func_175598_ae();
         Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type me.jooon.JooonAddons.mixin.IMixinRenderManager");
         IMixinRenderManager rm = (IMixinRenderManager)var10000;
         float partialTicks = Utils.INSTANCE.getRenderPartialTicks();
         double renderPosX = rm.getRenderPosX();
         double renderPosY = rm.getRenderPosY();
         double renderPosZ = rm.getRenderPosZ();
         double x = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * (double)partialTicks - renderPosX;
         double y = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * (double)partialTicks - renderPosY;
         double z = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * (double)partialTicks - renderPosZ;
         AxisAlignedBB bbox = entity.func_174813_aQ().func_72314_b(0.15D, 0.2D, 0.15D);
         AxisAlignedBB aabb = new AxisAlignedBB(bbox.field_72340_a - entity.field_70165_t + x, bbox.field_72338_b - entity.field_70163_u + y, bbox.field_72339_c - entity.field_70161_v + z, bbox.field_72336_d - entity.field_70165_t + x, bbox.field_72337_e - entity.field_70163_u + y, bbox.field_72334_f - entity.field_70161_v + z);
         if (entity instanceof EntityArmorStand) {
            AxisAlignedBB var20 = aabb.func_72314_b(0.3D, 1.0D, 0.3D);
            Intrinsics.checkNotNullExpressionValue(var20, "aabb.expand(0.3, 1.0, 0.3)");
            aabb = var20;
         }

         this.drawFilledBoundingBox(aabb, color);
      }
   }

   public final void drawFilledBoundingBox(@NotNull AxisAlignedBB aabb, int color) {
      Intrinsics.checkNotNullParameter(aabb, "aabb");
      GlStateManager.func_179147_l();
      GlStateManager.func_179097_i();
      GlStateManager.func_179140_f();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179090_x();
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      float a = (float)(color >> 24 & 255) / 255.0F;
      float r = (float)(color >> 16 & 255) / 255.0F;
      float g = (float)(color >> 8 & 255) / 255.0F;
      float b = (float)(color & 255) / 255.0F;
      float opacity = 0.3F;
      GlStateManager.func_179131_c(r, g, b, a * opacity);
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
      tessellator.func_78381_a();
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179131_c(r, g, b, a * opacity);
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
      tessellator.func_78381_a();
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179131_c(r, g, b, a * opacity);
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
      tessellator.func_78381_a();
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179131_c(r, g, b, a);
      RenderGlobal.func_181561_a(aabb);
      GlStateManager.func_179098_w();
      GlStateManager.func_179126_j();
      GlStateManager.func_179084_k();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public final void drawRect(double left, double top, double right, double bottom, int color) {
      double leftModifiable = left;
      double topModifiable = top;
      double rightModifiable = right;
      double bottomModifiable = bottom;
      if (left < right) {
         leftModifiable = right;
         rightModifiable = left;
      }

      if (top < bottom) {
         topModifiable = bottom;
         bottomModifiable = top;
      }

      float f3 = (float)(color >> 24 & 255) / 255.0F;
      float f = (float)(color >> 16 & 255) / 255.0F;
      float f1 = (float)(color >> 8 & 255) / 255.0F;
      float f2 = (float)(color & 255) / 255.0F;
      GlStateManager.func_179131_c(f, f1, f2, f3);
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldRenderer = tessellator.func_178180_c();
      GlStateManager.func_179147_l();
      GlStateManager.func_179090_x();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      worldRenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      worldRenderer.func_181662_b(leftModifiable, bottomModifiable, 0.0D).func_181675_d();
      worldRenderer.func_181662_b(rightModifiable, bottomModifiable, 0.0D).func_181675_d();
      worldRenderer.func_181662_b(rightModifiable, topModifiable, 0.0D).func_181675_d();
      worldRenderer.func_181662_b(leftModifiable, topModifiable, 0.0D).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179098_w();
      GlStateManager.func_179084_k();
   }

   public final void drawOutlinedEsp(@NotNull EntityLivingBase entity, @NotNull ModelBase model, int color, float partialTicks) {
      Intrinsics.checkNotNullParameter(entity, "entity");
      Intrinsics.checkNotNullParameter(model, "model");
      boolean visible = false;
      if (Minecraft.func_71410_x().field_71439_g.func_70685_l((Entity)entity)) {
         visible = true;
      }

      if (!Config.INSTANCE.getDisableVisible() || !visible) {
         RenderUtils.ModelData modelData = this.preModelDraw(entity, model, partialTicks);
         OutlineUtils.INSTANCE.outlineEntity(model, entity, modelData.getLimbSwing(), modelData.getLimbSwingAmount(), modelData.getAge(), modelData.getRotationYaw(), modelData.getRotationPitch(), 0.0625F, partialTicks, color);
         this.postModelDraw();
      }
   }

   public final void drawChamsEsp(@NotNull EntityLivingBase entity, @NotNull ModelBase model, int color, float partialTicks) {
      Intrinsics.checkNotNullParameter(entity, "entity");
      Intrinsics.checkNotNullParameter(model, "model");
      RenderUtils.ModelData modelData = this.preModelDraw(entity, model, partialTicks);
      float f3 = (float)(color >> 24 & 255) / 255.0F;
      float f = (float)(color >> 16 & 255) / 255.0F;
      float f1 = (float)(color >> 8 & 255) / 255.0F;
      float f2 = (float)(color & 255) / 255.0F;
      GlStateManager.func_179094_E();
      GL11.glEnable(10754);
      GlStateManager.func_179136_a(1.0F, 1000000.0F);
      OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0F, 240.0F);
      GlStateManager.func_179147_l();
      GlStateManager.func_179090_x();
      GlStateManager.func_179140_f();
      GlStateManager.func_179112_b(770, 771);
      GlStateManager.func_179131_c(f, f1, f2, f3);
      GlStateManager.func_179097_i();
      GlStateManager.func_179132_a(false);
      model.func_78088_a((Entity)entity, modelData.getLimbSwing(), modelData.getLimbSwingAmount(), modelData.getAge(), modelData.getRotationYaw(), modelData.getRotationPitch(), 0.0625F);
      this.renderLayers(modelData.getRenderer(), entity, modelData.getLimbSwing(), modelData.getLimbSwingAmount(), partialTicks, modelData.getAge(), modelData.getRotationYaw(), modelData.getRotationPitch(), 0.0625F, f, f1, f2, f3);
      GlStateManager.func_179126_j();
      GlStateManager.func_179132_a(true);
      GlStateManager.func_179131_c(f, f1, f2, f3);
      model.func_78088_a((Entity)entity, modelData.getLimbSwing(), modelData.getLimbSwingAmount(), modelData.getAge(), modelData.getRotationYaw(), modelData.getRotationPitch(), 0.0625F);
      this.renderLayers(modelData.getRenderer(), entity, modelData.getLimbSwing(), modelData.getLimbSwingAmount(), partialTicks, modelData.getAge(), modelData.getRotationYaw(), modelData.getRotationPitch(), 0.0625F, f, f1, f2, f3);
      GlStateManager.func_179098_w();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179084_k();
      GlStateManager.func_179145_e();
      GlStateManager.func_179136_a(1.0F, -1000000.0F);
      GL11.glDisable(10754);
      GlStateManager.func_179121_F();
      this.postModelDraw();
   }

   private final RenderUtils.ModelData preModelDraw(EntityLivingBase entity, ModelBase model, float partialTicks) {
      Render render = JooonAddons.Companion.getMc().func_175598_ae().func_78713_a((Entity)entity);
      RenderManager var10000 = JooonAddons.Companion.getMc().func_175598_ae();
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type me.jooon.JooonAddons.mixin.IMixinRenderManager");
      IMixinRenderManager renderManager = (IMixinRenderManager)var10000;
      Intrinsics.checkNotNull(render, "null cannot be cast to non-null type me.jooon.JooonAddons.mixin.IMixinRendererLivingEntity");
      IMixinRendererLivingEntity renderer = (IMixinRendererLivingEntity)render;
      float renderYaw = Utils.INSTANCE.interpolateRotation(entity.field_70760_ar, entity.field_70761_aq, partialTicks);
      float prevYaw = Utils.INSTANCE.interpolateRotation(entity.field_70758_at, entity.field_70759_as, partialTicks);
      float rotationYaw = prevYaw - renderYaw;
      float rotationPitch = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * partialTicks;
      float limbSwing = entity.field_70754_ba - entity.field_70721_aZ * (1.0F - partialTicks);
      float limbSwingAmout = entity.field_70722_aY + (entity.field_70721_aZ - entity.field_70722_aY) * partialTicks;
      float age = (float)entity.field_70173_aa + partialTicks;
      GlStateManager.func_179094_E();
      GlStateManager.func_179129_p();
      model.field_78095_p = entity.func_70678_g(partialTicks);
      model.field_78091_s = entity.func_70631_g_();
      double x = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * (double)partialTicks;
      double y = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * (double)partialTicks;
      double z = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * (double)partialTicks;
      GlStateManager.func_179137_b(x - renderManager.getRenderPosX(), y - renderManager.getRenderPosY(), z - renderManager.getRenderPosZ());
      float f = Utils.INSTANCE.interpolateRotation(entity.field_70760_ar, entity.field_70761_aq, partialTicks);
      Utils.INSTANCE.rotateCorpse(entity, age, f, partialTicks);
      GlStateManager.func_179091_B();
      GlStateManager.func_179152_a(-1.0F, -1.0F, 1.0F);
      GlStateManager.func_179109_b(0.0F, -1.5078125F, 0.0F);
      model.func_78086_a(entity, limbSwing, limbSwingAmout, partialTicks);
      model.func_78087_a(limbSwing, limbSwingAmout, age, rotationYaw, rotationPitch, 0.0625F, (Entity)entity);
      return new RenderUtils.ModelData(renderer, rotationYaw, rotationPitch, limbSwing, limbSwingAmout, age);
   }

   private final void postModelDraw() {
      GlStateManager.func_179117_G();
      GlStateManager.func_179101_C();
      GlStateManager.func_179098_w();
      GlStateManager.func_179089_o();
      GlStateManager.func_179121_F();
   }

   public final void renderLayers(@NotNull IMixinRendererLivingEntity renderer, @NotNull EntityLivingBase entitylivingbaseIn, float p_177093_2_, float p_177093_3_, float partialTicks, float p_177093_5_, float p_177093_6_, float p_177093_7_, float p_177093_8_, float red, float green, float blue, float alpha) {
      Intrinsics.checkNotNullParameter(renderer, "renderer");
      Intrinsics.checkNotNullParameter(entitylivingbaseIn, "entitylivingbaseIn");
      if (entitylivingbaseIn instanceof EntitySkeleton) {
         Iterator var14 = renderer.getLayerRenderers().iterator();

         while(true) {
            LayerRenderer layerrenderer;
            do {
               if (!var14.hasNext()) {
                  return;
               }

               layerrenderer = (LayerRenderer)var14.next();
            } while(!(layerrenderer instanceof LayerArmorBase));

            for(int i = 1; i < 5; ++i) {
               ItemStack itemstack = entitylivingbaseIn.func_82169_q(i - 1);
               if (itemstack != null && itemstack.func_77973_b() instanceof ItemArmor) {
                  ModelBase armorModel = ((LayerArmorBase)layerrenderer).func_177175_a(i);
                  armorModel.func_78086_a(entitylivingbaseIn, p_177093_2_, p_177093_3_, partialTicks);
                  GlStateManager.func_179131_c(red, green, blue, alpha);
                  armorModel.func_78088_a((Entity)entitylivingbaseIn, p_177093_2_, p_177093_3_, p_177093_5_, p_177093_6_, p_177093_7_, p_177093_8_);
               }
            }
         }
      }
   }

   @JvmStatic
   public static final void renderTexture(@Nullable ResourceLocation texture, int x, int y, int width, int height, boolean enableLighting) {
      if (enableLighting) {
         RenderHelper.func_74520_c();
      }

      GlStateManager.func_179091_B();
      GlStateManager.func_179147_l();
      GlStateManager.func_179126_j();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179094_E();
      JooonAddons.Companion.getMc().func_110434_K().func_110577_a(texture);
      GlStateManager.func_179091_B();
      GlStateManager.func_179141_d();
      GlStateManager.func_179092_a(516, 0.1F);
      GlStateManager.func_179147_l();
      GlStateManager.func_179112_b(770, 771);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      Gui.func_146110_a(x, y, 0.0F, 0.0F, width, height, (float)width, (float)height);
      GlStateManager.func_179118_c();
      GlStateManager.func_179101_C();
      GlStateManager.func_179140_f();
      GlStateManager.func_179121_F();
   }

   // $FF: synthetic method
   public static void renderTexture$default(ResourceLocation var0, int var1, int var2, int var3, int var4, boolean var5, int var6, Object var7) {
      if ((var6 & 8) != 0) {
         var3 = 16;
      }

      if ((var6 & 16) != 0) {
         var4 = 16;
      }

      if ((var6 & 32) != 0) {
         var5 = true;
      }

      renderTexture(var0, var1, var2, var3, var4, var5);
   }

   public final void drawBeaconBeam(@NotNull EntityLivingBase entity, int color, int type) {
      Intrinsics.checkNotNullParameter(entity, "entity");
      Minecraft var10000 = JooonAddons.Companion.getMc();
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type me.jooon.JooonAddons.mixin.IMixinMinecraft");
      float partialTicks = ((IMixinMinecraft)var10000).getTimer().field_74281_c;
      double x = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * (double)partialTicks - 0.5D;
      double y = 0.0D;
      switch(type) {
      case 1:
         y = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * (double)partialTicks + (double)2;
         break;
      case 2:
         y = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * (double)partialTicks - 0.5D;
         break;
      case 3:
         y = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * (double)partialTicks + (double)1;
         break;
      case 4:
         y = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * (double)partialTicks;
      }

      double z = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * (double)partialTicks - 0.5D;
      this.renderBeaconBeam(x, y, z, color, partialTicks);
   }

   public final void renderBeaconBeam(double x, double y, double z, int color, float partialTicks) {
      EntityPlayerSP player = JooonAddons.Companion.getMc().field_71439_g;
      double playerX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
      double playerY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
      double playerZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
      GlStateManager.func_179094_E();
      GlStateManager.func_179137_b(-playerX, -playerY, -playerZ);
      int height = 300;
      int bottomOffset = 0;
      int topOffset = bottomOffset + height;
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      JooonAddons.Companion.getMc().func_110434_K().func_110577_a(beaconBeam);
      GL11.glTexParameterf(3553, 10242, 10497.0F);
      GL11.glTexParameterf(3553, 10243, 10497.0F);
      GlStateManager.func_179140_f();
      GlStateManager.func_179097_i();
      GlStateManager.func_179098_w();
      GlStateManager.func_179120_a(770, 1, 1, 0);
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      double time = (double)JooonAddons.Companion.getMc().field_71441_e.func_82737_E() + (double)partialTicks;
      double d1 = MathHelper.func_181162_h(-time * 0.2D - (double)MathHelper.func_76128_c(-time * 0.1D));
      float alpha = (float)(color >> 24 & 255) / 255.0F;
      float r = (float)(color >> 16 & 255) / 255.0F;
      float g = (float)(color >> 8 & 255) / 255.0F;
      float b = (float)(color & 255) / 255.0F;
      double d2 = time * 0.025D * -1.5D;
      double d4 = 0.5D + Math.cos(d2 + 2.356194490192345D) * 0.2D;
      double d5 = 0.5D + Math.sin(d2 + 2.356194490192345D) * 0.2D;
      double d6 = 0.5D + Math.cos(d2 + 0.7853981633974483D) * 0.2D;
      double d7 = 0.5D + Math.sin(d2 + 0.7853981633974483D) * 0.2D;
      double d8 = 0.5D + Math.cos(d2 + 3.9269908169872414D) * 0.2D;
      double d9 = 0.5D + Math.sin(d2 + 3.9269908169872414D) * 0.2D;
      double d10 = 0.5D + Math.cos(d2 + 5.497787143782138D) * 0.2D;
      double d11 = 0.5D + Math.sin(d2 + 5.497787143782138D) * 0.2D;
      double d14 = -1.0D + d1;
      double d15 = (double)height * 2.5D + d14;
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      worldrenderer.func_181662_b(x + d4, y + (double)topOffset, z + d5).func_181673_a(1.0D, d15).func_181666_a(r, g, b, alpha).func_181675_d();
      worldrenderer.func_181662_b(x + d4, y + (double)bottomOffset, z + d5).func_181673_a(1.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
      worldrenderer.func_181662_b(x + d6, y + (double)bottomOffset, z + d7).func_181673_a(0.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
      worldrenderer.func_181662_b(x + d6, y + (double)topOffset, z + d7).func_181673_a(0.0D, d15).func_181666_a(r, g, b, alpha).func_181675_d();
      worldrenderer.func_181662_b(x + d10, y + (double)topOffset, z + d11).func_181673_a(1.0D, d15).func_181666_a(r, g, b, alpha).func_181675_d();
      worldrenderer.func_181662_b(x + d10, y + (double)bottomOffset, z + d11).func_181673_a(1.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
      worldrenderer.func_181662_b(x + d8, y + (double)bottomOffset, z + d9).func_181673_a(0.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
      worldrenderer.func_181662_b(x + d8, y + (double)topOffset, z + d9).func_181673_a(0.0D, d15).func_181666_a(r, g, b, alpha).func_181675_d();
      worldrenderer.func_181662_b(x + d6, y + (double)topOffset, z + d7).func_181673_a(1.0D, d15).func_181666_a(r, g, b, alpha).func_181675_d();
      worldrenderer.func_181662_b(x + d6, y + (double)bottomOffset, z + d7).func_181673_a(1.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
      worldrenderer.func_181662_b(x + d10, y + (double)bottomOffset, z + d11).func_181673_a(0.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
      worldrenderer.func_181662_b(x + d10, y + (double)topOffset, z + d11).func_181673_a(0.0D, d15).func_181666_a(r, g, b, alpha).func_181675_d();
      worldrenderer.func_181662_b(x + d8, y + (double)topOffset, z + d9).func_181673_a(1.0D, d15).func_181666_a(r, g, b, alpha).func_181675_d();
      worldrenderer.func_181662_b(x + d8, y + (double)bottomOffset, z + d9).func_181673_a(1.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
      worldrenderer.func_181662_b(x + d4, y + (double)bottomOffset, z + d5).func_181673_a(0.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
      worldrenderer.func_181662_b(x + d4, y + (double)topOffset, z + d5).func_181673_a(0.0D, d15).func_181666_a(r, g, b, alpha).func_181675_d();
      tessellator.func_78381_a();
      double d12 = -1.0D + d1;
      double d13 = (double)height + d12;
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      worldrenderer.func_181662_b(x + 0.2D, y + (double)topOffset, z + 0.2D).func_181673_a(1.0D, d13).func_181666_a(r, g, b, 0.25F * alpha).func_181675_d();
      worldrenderer.func_181662_b(x + 0.2D, y + (double)bottomOffset, z + 0.2D).func_181673_a(1.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
      worldrenderer.func_181662_b(x + 0.8D, y + (double)bottomOffset, z + 0.2D).func_181673_a(0.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
      worldrenderer.func_181662_b(x + 0.8D, y + (double)topOffset, z + 0.2D).func_181673_a(0.0D, d13).func_181666_a(r, g, b, 0.25F * alpha).func_181675_d();
      worldrenderer.func_181662_b(x + 0.8D, y + (double)topOffset, z + 0.8D).func_181673_a(1.0D, d13).func_181666_a(r, g, b, 0.25F * alpha).func_181675_d();
      worldrenderer.func_181662_b(x + 0.8D, y + (double)bottomOffset, z + 0.8D).func_181673_a(1.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
      worldrenderer.func_181662_b(x + 0.2D, y + (double)bottomOffset, z + 0.8D).func_181673_a(0.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
      worldrenderer.func_181662_b(x + 0.2D, y + (double)topOffset, z + 0.8D).func_181673_a(0.0D, d13).func_181666_a(r, g, b, 0.25F * alpha).func_181675_d();
      worldrenderer.func_181662_b(x + 0.8D, y + (double)topOffset, z + 0.2D).func_181673_a(1.0D, d13).func_181666_a(r, g, b, 0.25F * alpha).func_181675_d();
      worldrenderer.func_181662_b(x + 0.8D, y + (double)bottomOffset, z + 0.2D).func_181673_a(1.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
      worldrenderer.func_181662_b(x + 0.8D, y + (double)bottomOffset, z + 0.8D).func_181673_a(0.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
      worldrenderer.func_181662_b(x + 0.8D, y + (double)topOffset, z + 0.8D).func_181673_a(0.0D, d13).func_181666_a(r, g, b, 0.25F * alpha).func_181675_d();
      worldrenderer.func_181662_b(x + 0.2D, y + (double)topOffset, z + 0.8D).func_181673_a(1.0D, d13).func_181666_a(r, g, b, 0.25F * alpha).func_181675_d();
      worldrenderer.func_181662_b(x + 0.2D, y + (double)bottomOffset, z + 0.8D).func_181673_a(1.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
      worldrenderer.func_181662_b(x + 0.2D, y + (double)bottomOffset, z + 0.2D).func_181673_a(0.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
      worldrenderer.func_181662_b(x + 0.2D, y + (double)topOffset, z + 0.2D).func_181673_a(0.0D, d13).func_181666_a(r, g, b, 0.25F * alpha).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179126_j();
      GlStateManager.func_179121_F();
   }

   public final void renderWaypointText(@NotNull String str, double x, double y, double z, float partialTicks, @NotNull UMatrixStack matrixStack, float scale, boolean background, boolean shadow, boolean distance) {
      Intrinsics.checkNotNullParameter(str, "str");
      Intrinsics.checkNotNullParameter(matrixStack, "matrixStack");
      matrixStack.push();
      GlStateManager.func_179092_a(516, 0.1F);
      Triple var14 = this.getViewerPos(partialTicks);
      double viewerX = ((Number)var14.component1()).doubleValue();
      double viewerY = ((Number)var14.component2()).doubleValue();
      double viewerZ = ((Number)var14.component3()).doubleValue();
      double distX = x - viewerX;
      double distY = y - viewerY - (double)JooonAddons.Companion.getMc().func_175606_aa().func_70047_e();
      double distZ = z - viewerZ;
      double dist = Math.sqrt(distX * distX + distY * distY + distZ * distZ);
      double renderX = 0.0D;
      double renderY = 0.0D;
      double renderZ = 0.0D;
      if (dist > 12.0D) {
         renderX = distX * (double)12 / dist + viewerX;
         renderY = distY * (double)12 / dist + viewerY + (double)JooonAddons.Companion.getMc().func_175606_aa().func_70047_e();
         renderZ = distZ * (double)12 / dist + viewerZ;
      } else {
         renderX = x;
         renderY = y;
         renderZ = z;
      }

      Color var35 = Color.WHITE;
      Intrinsics.checkNotNullExpressionValue(var35, "WHITE");
      this.drawNametag(renderX, renderY, renderZ, str, var35, partialTicks, matrixStack, shadow, scale, background);
      UMatrixStack.rotate$default(matrixStack, -JooonAddons.Companion.getMc().func_175598_ae().field_78735_i, 0.0F, 1.0F, 0.0F, false, 16, (Object)null);
      UMatrixStack.rotate$default(matrixStack, JooonAddons.Companion.getMc().func_175598_ae().field_78732_j, 1.0F, 0.0F, 0.0F, false, 16, (Object)null);
      matrixStack.translate(0.0D, -0.25D, 0.0D);
      UMatrixStack.rotate$default(matrixStack, -JooonAddons.Companion.getMc().func_175598_ae().field_78732_j, 1.0F, 0.0F, 0.0F, false, 16, (Object)null);
      UMatrixStack.rotate$default(matrixStack, JooonAddons.Companion.getMc().func_175598_ae().field_78735_i, 0.0F, 1.0F, 0.0F, false, 16, (Object)null);
      if (distance) {
         String var10004 = "" + ChatColor.YELLOW + MathKt.roundToInt(dist) + 'm';
         Color var10005 = Color.WHITE;
         Intrinsics.checkNotNullExpressionValue(var10005, "WHITE");
         drawNametag$default(this, renderX, renderY, renderZ, var10004, var10005, partialTicks, matrixStack, false, 0.0F, false, 896, (Object)null);
      }

      matrixStack.pop();
   }

   // $FF: synthetic method
   public static void renderWaypointText$default(RenderUtils var0, String var1, double var2, double var4, double var6, float var8, UMatrixStack var9, float var10, boolean var11, boolean var12, boolean var13, int var14, Object var15) {
      if ((var14 & 64) != 0) {
         var10 = 1.0F;
      }

      if ((var14 & 128) != 0) {
         var11 = true;
      }

      if ((var14 & 256) != 0) {
         var12 = true;
      }

      if ((var14 & 512) != 0) {
         var13 = true;
      }

      var0.renderWaypointText(var1, var2, var4, var6, var8, var9, var10, var11, var12, var13);
   }

   @NotNull
   public final Triple<Double, Double, Double> getViewerPos(float partialTicks) {
      Entity viewer = JooonAddons.Companion.getMc().func_175606_aa();
      double viewerX = viewer.field_70142_S + (viewer.field_70165_t - viewer.field_70142_S) * (double)partialTicks;
      double viewerY = viewer.field_70137_T + (viewer.field_70163_u - viewer.field_70137_T) * (double)partialTicks;
      double viewerZ = viewer.field_70136_U + (viewer.field_70161_v - viewer.field_70136_U) * (double)partialTicks;
      return new Triple(viewerX, viewerY, viewerZ);
   }

   private final void drawNametag(double x, double y, double z, String str, Color color, float partialTicks, UMatrixStack matrixStack, boolean shadow, float scale, boolean background) {
      EntityPlayerSP player = JooonAddons.Companion.getMc().field_71439_g;
      double x1 = x - player.field_70142_S + (x - player.field_70165_t - (x - player.field_70142_S)) * (double)partialTicks;
      double y1 = y - player.field_70137_T + (y - player.field_70163_u - (y - player.field_70137_T)) * (double)partialTicks;
      double z1 = z - player.field_70136_U + (z - player.field_70161_v - (z - player.field_70136_U)) * (double)partialTicks;
      double f1 = 0.0266666688D;
      int width = JooonAddons.Companion.getMc().field_71466_p.func_78256_a(str) / 2;
      matrixStack.push();
      matrixStack.translate(x1, y1, z1);
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      UMatrixStack.rotate$default(matrixStack, -JooonAddons.Companion.getMc().func_175598_ae().field_78735_i, 0.0F, 1.0F, 0.0F, false, 16, (Object)null);
      UMatrixStack.rotate$default(matrixStack, JooonAddons.Companion.getMc().func_175598_ae().field_78732_j, 1.0F, 0.0F, 0.0F, false, 16, (Object)null);
      matrixStack.scale(-f1, -f1, -f1);
      UGraphics.disableLighting();
      UGraphics.depthMask(false);
      UGraphics.enableBlend();
      UGraphics.tryBlendFuncSeparate(770, 771, 1, 0);
      if (background) {
         UGraphics worldRenderer = UGraphics.getFromTessellator();
         worldRenderer.beginWithDefaultShader(DrawMode.QUADS, DefaultVertexFormats.field_181706_f);
         worldRenderer.pos(matrixStack, (double)(-width) - 1.0D, -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
         worldRenderer.pos(matrixStack, (double)(-width) - 1.0D, 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
         worldRenderer.pos(matrixStack, (double)width + 1.0D, 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
         worldRenderer.pos(matrixStack, (double)width + 1.0D, -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
         worldRenderer.drawDirect();
      }

      GlStateManager.func_179098_w();
      FontProvider.drawString$default(DefaultFonts.getVANILLA_FONT_RENDERER(), matrixStack, str, color, -((float)width), ElementaFonts.getMINECRAFT().getBelowLineHeight() * scale, (float)width * 2.0F, scale, shadow, (Color)null, 256, (Object)null);
      UGraphics.depthMask(true);
      matrixStack.pop();
   }

   // $FF: synthetic method
   static void drawNametag$default(RenderUtils var0, double var1, double var3, double var5, String var7, Color var8, float var9, UMatrixStack var10, boolean var11, float var12, boolean var13, int var14, Object var15) {
      if ((var14 & 128) != 0) {
         var11 = true;
      }

      if ((var14 & 256) != 0) {
         var12 = 1.0F;
      }

      if ((var14 & 512) != 0) {
         var13 = true;
      }

      var0.drawNametag(var1, var3, var5, var7, var8, var9, var10, var11, var12, var13);
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006!"},
      d2 = {"Lme/jooon/JooonAddons/render/RenderUtils$ModelData;", "", "renderer", "Lme/jooon/JooonAddons/mixin/IMixinRendererLivingEntity;", "rotationYaw", "", "rotationPitch", "limbSwing", "limbSwingAmount", "age", "(Lme/jooon/JooonAddons/mixin/IMixinRendererLivingEntity;FFFFF)V", "getAge", "()F", "getLimbSwing", "getLimbSwingAmount", "getRenderer", "()Lme/jooon/JooonAddons/mixin/IMixinRendererLivingEntity;", "getRotationPitch", "getRotationYaw", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "", "JooonAddons"}
   )
   public static final class ModelData {
      @NotNull
      private final IMixinRendererLivingEntity renderer;
      private final float rotationYaw;
      private final float rotationPitch;
      private final float limbSwing;
      private final float limbSwingAmount;
      private final float age;

      public ModelData(@NotNull IMixinRendererLivingEntity renderer, float rotationYaw, float rotationPitch, float limbSwing, float limbSwingAmount, float age) {
         Intrinsics.checkNotNullParameter(renderer, "renderer");
         super();
         this.renderer = renderer;
         this.rotationYaw = rotationYaw;
         this.rotationPitch = rotationPitch;
         this.limbSwing = limbSwing;
         this.limbSwingAmount = limbSwingAmount;
         this.age = age;
      }

      @NotNull
      public final IMixinRendererLivingEntity getRenderer() {
         return this.renderer;
      }

      public final float getRotationYaw() {
         return this.rotationYaw;
      }

      public final float getRotationPitch() {
         return this.rotationPitch;
      }

      public final float getLimbSwing() {
         return this.limbSwing;
      }

      public final float getLimbSwingAmount() {
         return this.limbSwingAmount;
      }

      public final float getAge() {
         return this.age;
      }

      @NotNull
      public final IMixinRendererLivingEntity component1() {
         return this.renderer;
      }

      public final float component2() {
         return this.rotationYaw;
      }

      public final float component3() {
         return this.rotationPitch;
      }

      public final float component4() {
         return this.limbSwing;
      }

      public final float component5() {
         return this.limbSwingAmount;
      }

      public final float component6() {
         return this.age;
      }

      @NotNull
      public final RenderUtils.ModelData copy(@NotNull IMixinRendererLivingEntity renderer, float rotationYaw, float rotationPitch, float limbSwing, float limbSwingAmount, float age) {
         Intrinsics.checkNotNullParameter(renderer, "renderer");
         return new RenderUtils.ModelData(renderer, rotationYaw, rotationPitch, limbSwing, limbSwingAmount, age);
      }

      // $FF: synthetic method
      public static RenderUtils.ModelData copy$default(RenderUtils.ModelData var0, IMixinRendererLivingEntity var1, float var2, float var3, float var4, float var5, float var6, int var7, Object var8) {
         if ((var7 & 1) != 0) {
            var1 = var0.renderer;
         }

         if ((var7 & 2) != 0) {
            var2 = var0.rotationYaw;
         }

         if ((var7 & 4) != 0) {
            var3 = var0.rotationPitch;
         }

         if ((var7 & 8) != 0) {
            var4 = var0.limbSwing;
         }

         if ((var7 & 16) != 0) {
            var5 = var0.limbSwingAmount;
         }

         if ((var7 & 32) != 0) {
            var6 = var0.age;
         }

         return var0.copy(var1, var2, var3, var4, var5, var6);
      }

      @NotNull
      public String toString() {
         return "ModelData(renderer=" + this.renderer + ", rotationYaw=" + this.rotationYaw + ", rotationPitch=" + this.rotationPitch + ", limbSwing=" + this.limbSwing + ", limbSwingAmount=" + this.limbSwingAmount + ", age=" + this.age + ')';
      }

      public int hashCode() {
         int result = this.renderer.hashCode();
         result = result * 31 + Float.hashCode(this.rotationYaw);
         result = result * 31 + Float.hashCode(this.rotationPitch);
         result = result * 31 + Float.hashCode(this.limbSwing);
         result = result * 31 + Float.hashCode(this.limbSwingAmount);
         result = result * 31 + Float.hashCode(this.age);
         return result;
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof RenderUtils.ModelData)) {
            return false;
         } else {
            RenderUtils.ModelData var2 = (RenderUtils.ModelData)other;
            if (!Intrinsics.areEqual(this.renderer, var2.renderer)) {
               return false;
            } else if (Float.compare(this.rotationYaw, var2.rotationYaw) != 0) {
               return false;
            } else if (Float.compare(this.rotationPitch, var2.rotationPitch) != 0) {
               return false;
            } else if (Float.compare(this.limbSwing, var2.limbSwing) != 0) {
               return false;
            } else if (Float.compare(this.limbSwingAmount, var2.limbSwingAmount) != 0) {
               return false;
            } else {
               return Float.compare(this.age, var2.age) == 0;
            }
         }
      }
   }
}
