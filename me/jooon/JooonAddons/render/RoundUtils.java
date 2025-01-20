package me.jooon.JooonAddons.render;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import net.minecraft.client.renderer.GlStateManager;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004JP\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0007J@\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0007J:\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\rH\u0007JX\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0007JP\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0007J8\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0007J\u0006\u0010\u001b\u001a\u00020\u0004J\u000e\u0010\u001c\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r¨\u0006\u001d"},
   d2 = {"Lme/jooon/JooonAddons/render/RoundUtils;", "", "()V", "disableGL2D", "", "drawRoundedGradientRectCorner", "x", "", "y", "x1", "y1", "radius", "color", "", "color2", "color3", "color4", "drawRoundedOutline", "lineWidth", "drawRoundedRect", "drawSelectRoundedOutline", "radius1", "radius2", "radius3", "radius4", "drawSelectRoundedRect", "drawSmoothRoundedRect", "enableGL2D", "setColor", "JooonAddons"}
)
public final class RoundUtils {
   @NotNull
   public static final RoundUtils INSTANCE = new RoundUtils();

   private RoundUtils() {
   }

   public final void enableGL2D() {
      GL11.glDisable(2929);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(true);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
   }

   public final void disableGL2D() {
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GL11.glHint(3155, 4352);
   }

   @JvmStatic
   public static final void drawSmoothRoundedRect(float x, float y, float x1, float y1, float radius, int color) {
      GL11.glPushAttrib(0);
      GL11.glScaled(0.5D, 0.5D, 0.5D);
      float x = x * 2.0F;
      float y = y * 2.0F;
      float x1 = x1 * 2.0F;
      float y1 = y1 * 2.0F;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      INSTANCE.setColor(color);
      GL11.glEnable(2848);
      GL11.glBegin(9);

      int i;
      for(i = 0; i <= 90; i += 3) {
         GL11.glVertex2d((double)(x + radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D, (double)(y + radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D);
      }

      for(i = 90; i <= 180; i += 3) {
         GL11.glVertex2d((double)(x + radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D, (double)(y1 - radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D);
      }

      for(i = 0; i <= 90; i += 3) {
         GL11.glVertex2d((double)(x1 - radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)(y1 - radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
      }

      for(i = 90; i <= 180; i += 3) {
         GL11.glVertex2d((double)(x1 - radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)(y + radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
      }

      GL11.glEnd();
      GL11.glBegin(2);

      for(i = 0; i <= 90; i += 3) {
         GL11.glVertex2d((double)(x + radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D, (double)(y + radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D);
      }

      for(i = 90; i <= 180; i += 3) {
         GL11.glVertex2d((double)(x + radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D, (double)(y1 - radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D);
      }

      for(i = 0; i <= 90; i += 3) {
         GL11.glVertex2d((double)(x1 - radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)(y1 - radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
      }

      for(i = 90; i <= 180; i += 3) {
         GL11.glVertex2d((double)(x1 - radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)(y + radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
      }

      GL11.glEnd();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glScaled(2.0D, 2.0D, 2.0D);
      GL11.glPopAttrib();
   }

   @JvmStatic
   public static final void drawRoundedRect(float x, float y, float x1, float y1, float radius, int color) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179139_a(0.5D, 0.5D, 0.5D);
      float x = x * 2.0F;
      float y = y * 2.0F;
      float x1 = x1 * 2.0F;
      float y1 = y1 * 2.0F;
      GlStateManager.func_179147_l();
      GlStateManager.func_179090_x();
      GL11.glEnable(2848);
      if (color != -1) {
         INSTANCE.setColor(color);
      }

      GL11.glEnable(2848);
      GL11.glBegin(9);

      int i;
      for(i = 0; i <= 90; i += 3) {
         GL11.glVertex2d((double)(x + radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D, (double)(y + radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D);
      }

      for(i = 90; i <= 180; i += 3) {
         GL11.glVertex2d((double)(x + radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D, (double)(y1 - radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D);
      }

      for(i = 0; i <= 90; i += 3) {
         GL11.glVertex2d((double)(x1 - radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)(y1 - radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
      }

      for(i = 90; i <= 180; i += 3) {
         GL11.glVertex2d((double)(x1 - radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)(y + radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
      }

      GL11.glEnd();
      GlStateManager.func_179084_k();
      GL11.glDisable(2848);
      GL11.glDisable(2848);
      GlStateManager.func_179098_w();
      GlStateManager.func_179152_a(2.0F, 2.0F, 2.0F);
      GlStateManager.func_179117_G();
      GlStateManager.func_179121_F();
   }

   // $FF: synthetic method
   public static void drawRoundedRect$default(float var0, float var1, float var2, float var3, float var4, int var5, int var6, Object var7) {
      if ((var6 & 32) != 0) {
         var5 = -1;
      }

      drawRoundedRect(var0, var1, var2, var3, var4, var5);
   }

   @JvmStatic
   public static final void drawRoundedOutline(float x, float y, float x1, float y1, float radius, float lineWidth, int color) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179139_a(0.5D, 0.5D, 0.5D);
      float x = x * 2.0F;
      float y = y * 2.0F;
      float x1 = x1 * 2.0F;
      float y1 = y1 * 2.0F;
      GlStateManager.func_179147_l();
      GlStateManager.func_179090_x();
      INSTANCE.setColor(color);
      GL11.glEnable(2848);
      GL11.glLineWidth(lineWidth);
      GL11.glBegin(2);

      int i;
      for(i = 0; i <= 90; i += 3) {
         GL11.glVertex2d((double)(x + radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D, (double)(y + radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D);
      }

      for(i = 90; i <= 180; i += 3) {
         GL11.glVertex2d((double)(x + radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D, (double)(y1 - radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D);
      }

      for(i = 0; i <= 90; i += 3) {
         GL11.glVertex2d((double)(x1 - radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)(y1 - radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
      }

      for(i = 90; i <= 180; i += 3) {
         GL11.glVertex2d((double)(x1 - radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)(y + radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
      }

      GL11.glEnd();
      GlStateManager.func_179084_k();
      GL11.glDisable(2848);
      GlStateManager.func_179098_w();
      GlStateManager.func_179152_a(2.0F, 2.0F, 2.0F);
      GlStateManager.func_179117_G();
      GlStateManager.func_179121_F();
   }

   @JvmStatic
   public static final void drawSelectRoundedRect(float x, float y, float x1, float y1, float radius1, float radius2, float radius3, float radius4, int color) {
      GL11.glPushAttrib(0);
      GL11.glScaled(0.5D, 0.5D, 0.5D);
      float x = x * 2.0F;
      float y = y * 2.0F;
      float x1 = x1 * 2.0F;
      float y1 = y1 * 2.0F;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      INSTANCE.setColor(color);
      GL11.glEnable(2848);
      GL11.glBegin(9);

      int i;
      for(i = 0; i <= 90; i += 3) {
         GL11.glVertex2d((double)(x + radius1) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius1 * -1.0D, (double)(y + radius1) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius1 * -1.0D);
      }

      for(i = 90; i <= 180; i += 3) {
         GL11.glVertex2d((double)(x + radius2) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius2 * -1.0D, (double)(y1 - radius2) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius2 * -1.0D);
      }

      for(i = 0; i <= 90; i += 3) {
         GL11.glVertex2d((double)(x1 - radius3) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius3, (double)(y1 - radius3) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius3);
      }

      for(i = 90; i <= 180; i += 3) {
         GL11.glVertex2d((double)(x1 - radius4) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius4, (double)(y + radius4) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius4);
      }

      GL11.glEnd();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glDisable(3042);
      GL11.glEnable(3553);
      GL11.glScaled(2.0D, 2.0D, 2.0D);
      GL11.glPopAttrib();
   }

   @JvmStatic
   public static final void drawSelectRoundedOutline(float x, float y, float x1, float y1, float radius1, float radius2, float radius3, float radius4, float lineWidth, int color) {
      GL11.glPushAttrib(0);
      GL11.glScaled(0.5D, 0.5D, 0.5D);
      float x = x * 2.0F;
      float y = y * 2.0F;
      float x1 = x1 * 2.0F;
      float y1 = y1 * 2.0F;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      INSTANCE.setColor(color);
      GL11.glEnable(2848);
      GL11.glLineWidth(lineWidth);
      GL11.glBegin(2);

      int i;
      for(i = 0; i <= 90; i += 3) {
         GL11.glVertex2d((double)(x + radius1) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius1 * -1.0D, (double)(y + radius1) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius1 * -1.0D);
      }

      for(i = 90; i <= 180; i += 3) {
         GL11.glVertex2d((double)(x + radius2) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius2 * -1.0D, (double)(y1 - radius2) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius2 * -1.0D);
      }

      for(i = 0; i <= 90; i += 3) {
         GL11.glVertex2d((double)(x1 - radius3) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius3, (double)(y1 - radius3) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius3);
      }

      for(i = 90; i <= 180; i += 3) {
         GL11.glVertex2d((double)(x1 - radius4) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius4, (double)(y + radius4) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius4);
      }

      GL11.glEnd();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glDisable(3042);
      GL11.glEnable(3553);
      GL11.glScaled(2.0D, 2.0D, 2.0D);
      GL11.glPopAttrib();
   }

   public final void setColor(int color) {
      float a = (float)(color >> 24 & 255) / 255.0F;
      float r = (float)(color >> 16 & 255) / 255.0F;
      float g = (float)(color >> 8 & 255) / 255.0F;
      float b = (float)(color & 255) / 255.0F;
      GL11.glColor4f(r, g, b, a);
   }

   @JvmStatic
   public static final void drawRoundedGradientRectCorner(float x, float y, float x1, float y1, float radius, int color, int color2, int color3, int color4) {
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glShadeModel(7425);
      GL11.glPushAttrib(0);
      GL11.glScaled(0.5D, 0.5D, 0.5D);
      float x = x * 2.0F;
      float y = y * 2.0F;
      float x1 = x1 * 2.0F;
      float y1 = y1 * 2.0F;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      INSTANCE.setColor(color);
      GL11.glEnable(2848);
      GL11.glShadeModel(7425);
      GL11.glBegin(9);

      int i;
      for(i = 0; i <= 90; i += 3) {
         INSTANCE.setColor(color);
      }

      GL11.glVertex2d((double)(x + radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D, (double)(y + radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D);

      for(i = 90; i <= 180; i += 3) {
         INSTANCE.setColor(color2);
      }

      GL11.glVertex2d((double)(x + radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D, (double)(y1 - radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius * -1.0D);

      for(i = 0; i <= 90; i += 3) {
         INSTANCE.setColor(color3);
      }

      GL11.glVertex2d((double)(x1 - radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)(y1 - radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);

      for(i = 90; i <= 180; i += 3) {
         INSTANCE.setColor(color4);
      }

      GL11.glVertex2d((double)(x1 - radius) + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)(y + radius) + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
      GL11.glEnd();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glDisable(3042);
      GL11.glEnable(3553);
      GL11.glScaled(2.0D, 2.0D, 2.0D);
      GL11.glPopAttrib();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glShadeModel(7424);
   }
}
