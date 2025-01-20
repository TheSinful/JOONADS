package me.jooon.JooonAddons.gui.buttons;

import java.awt.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.gui.GuiElement;
import me.jooon.JooonAddons.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 $2\u00020\u0001:\u0001$B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016J \u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016J\u0010\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0018H\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u001a\u0010\u0011\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0014\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\r¨\u0006%"},
   d2 = {"Lme/jooon/JooonAddons/gui/buttons/LocationButton;", "Lnet/minecraft/client/gui/GuiButton;", "element", "Lme/jooon/JooonAddons/gui/GuiElement;", "(Lme/jooon/JooonAddons/gui/GuiElement;)V", "getElement", "()Lme/jooon/JooonAddons/gui/GuiElement;", "setElement", "x", "", "getX", "()F", "setX", "(F)V", "x2", "getX2", "setX2", "y", "getY", "setY", "y2", "getY2", "setY2", "drawButton", "", "mc", "Lnet/minecraft/client/Minecraft;", "mouseX", "", "mouseY", "mousePressed", "", "playPressSound", "soundHandlerIn", "Lnet/minecraft/client/audio/SoundHandler;", "refreshLocations", "Companion", "JooonAddons"}
)
public final class LocationButton extends GuiButton {
   @NotNull
   public static final LocationButton.Companion Companion = new LocationButton.Companion((DefaultConstructorMarker)null);
   @NotNull
   private GuiElement element;
   private float x;
   private float y;
   private float x2;
   private float y2;
   @Nullable
   private static GuiElement lastHoveredElement;

   public LocationButton(@NotNull GuiElement element) {
      Intrinsics.checkNotNullParameter(element, "element");
      super(-1, 0, 0, (String)null);
      this.element = element;
      this.x = this.element.getActualX();
      this.y = this.element.getActualY() * this.element.getScale();
      this.x2 = this.x + this.element.getActualWidth();
      this.y2 = this.y + this.element.getActualHeight() * this.element.getScale();
   }

   @NotNull
   public final GuiElement getElement() {
      return this.element;
   }

   public final void setElement(@NotNull GuiElement <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.element = var1;
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

   public final float getX2() {
      return this.x2;
   }

   public final void setX2(float <set-?>) {
      this.x2 = var1;
   }

   public final float getY2() {
      return this.y2;
   }

   public final void setY2(float <set-?>) {
      this.y2 = var1;
   }

   private final void refreshLocations() {
      this.x = this.element.getActualX();
      this.y = this.element.getActualY() + (float)2 * this.element.getScale();
      this.x2 = this.x + this.element.getActualWidth();
      this.y2 = this.y + this.element.getActualHeight() + (float)2 * this.element.getScale();
   }

   public void func_146112_a(@NotNull Minecraft mc, int mouseX, int mouseY) {
      Intrinsics.checkNotNullParameter(mc, "mc");
      this.refreshLocations();
      this.field_146123_n = (float)mouseX >= this.x && (float)mouseY >= this.y && (float)mouseX < this.x2 && (float)mouseY < this.y2 + (float)2 * this.element.getScale();
      Color c = new Color(255, 255, 255, this.field_146123_n ? 100 : 40);
      RenderUtils.INSTANCE.drawRect(0.0D, 0.0D, (double)this.element.getWidth(), (double)(this.element.getHeight() + 4), c.getRGB());
      GlStateManager.func_179109_b(0.0F, -2.0F, 0.0F);
      this.element.demoRender();
      GlStateManager.func_179109_b(0.0F, 0.0F, 0.0F);
      if (this.field_146123_n) {
         LocationButton.Companion var10000 = Companion;
         lastHoveredElement = this.element;
      }

   }

   public boolean func_146116_c(@NotNull Minecraft mc, int mouseX, int mouseY) {
      Intrinsics.checkNotNullParameter(mc, "mc");
      return this.field_146124_l && this.field_146125_m && this.field_146123_n;
   }

   public void func_146113_a(@NotNull SoundHandler soundHandlerIn) {
      Intrinsics.checkNotNullParameter(soundHandlerIn, "soundHandlerIn");
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"},
      d2 = {"Lme/jooon/JooonAddons/gui/buttons/LocationButton$Companion;", "", "()V", "lastHoveredElement", "Lme/jooon/JooonAddons/gui/GuiElement;", "getLastHoveredElement", "()Lme/jooon/JooonAddons/gui/GuiElement;", "setLastHoveredElement", "(Lme/jooon/JooonAddons/gui/GuiElement;)V", "JooonAddons"}
   )
   public static final class Companion {
      private Companion() {
      }

      @Nullable
      public final GuiElement getLastHoveredElement() {
         return LocationButton.lastHoveredElement;
      }

      public final void setLastHoveredElement(@Nullable GuiElement <set-?>) {
         LocationButton.lastHoveredElement = var1;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
