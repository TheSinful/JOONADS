package me.jooon.JooonAddons.gui.buttons;

import gg.essential.universal.UResolution;
import java.awt.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.gui.GuiElement;
import me.jooon.JooonAddons.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Mouse;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 !2\u00020\u0001:\u0002!\"B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0016J \u0010\u001f\u001a\u00020 2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015¨\u0006#"},
   d2 = {"Lme/jooon/JooonAddons/gui/buttons/ResizeButton;", "Lnet/minecraft/client/gui/GuiButton;", "x", "", "y", "element", "Lme/jooon/JooonAddons/gui/GuiElement;", "corner", "Lme/jooon/JooonAddons/gui/buttons/ResizeButton$Corner;", "(FFLme/jooon/JooonAddons/gui/GuiElement;Lme/jooon/JooonAddons/gui/buttons/ResizeButton$Corner;)V", "getCorner", "()Lme/jooon/JooonAddons/gui/buttons/ResizeButton$Corner;", "cornerOffsetX", "cornerOffsetY", "getElement", "()Lme/jooon/JooonAddons/gui/GuiElement;", "setElement", "(Lme/jooon/JooonAddons/gui/GuiElement;)V", "getX", "()F", "setX", "(F)V", "getY", "setY", "drawButton", "", "mc", "Lnet/minecraft/client/Minecraft;", "mouseX", "", "mouseY", "mousePressed", "", "Companion", "Corner", "JooonAddons"}
)
public final class ResizeButton extends GuiButton {
   @NotNull
   public static final ResizeButton.Companion Companion = new ResizeButton.Companion((DefaultConstructorMarker)null);
   private float x;
   private float y;
   @NotNull
   private GuiElement element;
   @NotNull
   private final ResizeButton.Corner corner;
   private float cornerOffsetX;
   private float cornerOffsetY;
   public static final int SIZE = 2;

   public ResizeButton(float x, float y, @NotNull GuiElement element, @NotNull ResizeButton.Corner corner) {
      Intrinsics.checkNotNullParameter(element, "element");
      Intrinsics.checkNotNullParameter(corner, "corner");
      super(-1, 0, 0, (String)null);
      this.x = x;
      this.y = y;
      this.element = element;
      this.corner = corner;
   }

   public final float getX() {
      return this.x;
   }

   public final void setX(float <set-?>) {
      this.x = var1;
   }

   public final float getY() {
      return this.y;
   }

   public final void setY(float <set-?>) {
      this.y = var1;
   }

   @NotNull
   public final GuiElement getElement() {
      return this.element;
   }

   public final void setElement(@NotNull GuiElement <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.element = var1;
   }

   @NotNull
   public final ResizeButton.Corner getCorner() {
      return this.corner;
   }

   public void func_146112_a(@NotNull Minecraft mc, int mouseX, int mouseY) {
      Intrinsics.checkNotNullParameter(mc, "mc");
      float scale = this.element.getScale();
      this.field_146123_n = (float)mouseX >= this.x && (float)mouseY >= this.y && (float)mouseX < this.x + 4.0F * scale && (float)mouseY < this.y + 4.0F * scale;
      Color color = this.field_146123_n ? new Color(255, 255, 255, 100) : new Color(255, 255, 255, 70);
      RenderUtils.INSTANCE.drawRect(0.0D, 0.0D, 4.0D, 4.0D, color.getRGB());
   }

   public boolean func_146116_c(@NotNull Minecraft mc, int mouseX, int mouseY) {
      Intrinsics.checkNotNullParameter(mc, "mc");
      UResolution sr = UResolution.INSTANCE;
      float minecraftScale = (float)UResolution.getScaleFactor();
      float floatMouseX = (float)Mouse.getX() / minecraftScale;
      float floatMouseY = (float)(mc.field_71440_d - Mouse.getY()) / minecraftScale;
      this.cornerOffsetX = floatMouseX;
      this.cornerOffsetY = floatMouseY;
      return this.field_146123_n;
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"},
      d2 = {"Lme/jooon/JooonAddons/gui/buttons/ResizeButton$Companion;", "", "()V", "SIZE", "", "JooonAddons"}
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"},
      d2 = {"Lme/jooon/JooonAddons/gui/buttons/ResizeButton$Corner;", "", "(Ljava/lang/String;I)V", "TOP_LEFT", "TOP_RIGHT", "BOTTOM_RIGHT", "BOTTOM_LEFT", "JooonAddons"}
   )
   public static enum Corner {
      TOP_LEFT,
      TOP_RIGHT,
      BOTTOM_RIGHT,
      BOTTOM_LEFT;

      // $FF: synthetic method
      private static final ResizeButton.Corner[] $values() {
         ResizeButton.Corner[] var0 = new ResizeButton.Corner[]{TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT};
         return var0;
      }
   }
}
