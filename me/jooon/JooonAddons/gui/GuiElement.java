package me.jooon.JooonAddons.gui;

import gg.essential.universal.UResolution;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.utils.core.FloatPair;
import net.minecraft.client.gui.FontRenderer;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\b&\u0018\u0000 .2\u00020\u0001:\u0001.B\u0019\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\b\u0010)\u001a\u00020*H&J\b\u0010+\u001a\u00020*H&J\u0016\u0010\u001e\u001a\u00020*2\u0006\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\bJ\u0016\u0010\u001e\u001a\u00020*2\u0006\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u0015R\u0011\u0010\u000b\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0010\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\u0012\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\rR\u0012\u0010\u0014\u001a\u00020\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\r\"\u0004\b!\u0010\"R\u0012\u0010#\u001a\u00020$X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0012\u0010'\u001a\u00020\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0017¨\u0006/"},
   d2 = {"Lme/jooon/JooonAddons/gui/GuiElement;", "", "name", "", "fp", "Lme/jooon/JooonAddons/utils/core/FloatPair;", "(Ljava/lang/String;Lme/jooon/JooonAddons/utils/core/FloatPair;)V", "scale", "", "pos", "(Ljava/lang/String;FLme/jooon/JooonAddons/utils/core/FloatPair;)V", "actualHeight", "getActualHeight", "()F", "actualWidth", "getActualWidth", "actualX", "getActualX", "actualY", "getActualY", "height", "", "getHeight", "()I", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getPos", "()Lme/jooon/JooonAddons/utils/core/FloatPair;", "setPos", "(Lme/jooon/JooonAddons/utils/core/FloatPair;)V", "getScale", "setScale", "(F)V", "toggled", "", "getToggled", "()Z", "width", "getWidth", "demoRender", "", "render", "x", "y", "Companion", "JooonAddons"}
)
public abstract class GuiElement {
   @NotNull
   public static final GuiElement.Companion Companion = new GuiElement.Companion((DefaultConstructorMarker)null);
   @NotNull
   private String name;
   private float scale;
   @NotNull
   private FloatPair pos;
   @NotNull
   private static final UResolution sr;
   @NotNull
   private static final Lazy<FontRenderer> fr$delegate;

   public GuiElement(@NotNull String name, float scale, @NotNull FloatPair pos) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(pos, "pos");
      super();
      this.name = name;
      this.scale = scale;
      this.pos = pos;
      Object var10001 = GuiManager.INSTANCE.getGUIPOSITIONS().getOrDefault(this.name, this.pos);
      Intrinsics.checkNotNullExpressionValue(var10001, "GuiManager.GUIPOSITIONS.getOrDefault(name, pos)");
      this.pos = (FloatPair)var10001;
      var10001 = GuiManager.INSTANCE.getGUISCALES().getOrDefault(this.name, this.scale);
      Intrinsics.checkNotNullExpressionValue(var10001, "GuiManager.GUISCALES.getOrDefault(name, scale)");
      this.scale = ((Number)var10001).floatValue();
   }

   @NotNull
   public final String getName() {
      return this.name;
   }

   public final void setName(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.name = var1;
   }

   public final float getScale() {
      return this.scale;
   }

   public final void setScale(float <set-?>) {
      this.scale = var1;
   }

   @NotNull
   public final FloatPair getPos() {
      return this.pos;
   }

   public final void setPos(@NotNull FloatPair <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.pos = var1;
   }

   @JvmOverloads
   public GuiElement(@NotNull String name, @NotNull FloatPair fp) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(fp, "fp");
      this(name, 1.0F, fp);
   }

   // $FF: synthetic method
   public GuiElement(String var1, FloatPair var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = new FloatPair(0, 0);
      }

      this(var1, var2);
   }

   public abstract void render();

   public abstract void demoRender();

   public abstract boolean getToggled();

   public final void setPos(int x, int y) {
      float var10000 = (float)x;
      UResolution var10001 = sr;
      float fX = var10000 / (float)UResolution.getScaledWidth();
      var10000 = (float)y;
      var10001 = sr;
      float fY = var10000 / (float)UResolution.getScaledHeight();
      this.setPos(fX, fY);
   }

   public final void setPos(float x, float y) {
      this.pos = new FloatPair(x, y);
   }

   public final float getActualX() {
      int maxX = UResolution.getScaledWidth();
      return (float)maxX * this.pos.getX();
   }

   public final float getActualY() {
      int maxY = UResolution.getScaledHeight();
      return (float)maxY * this.pos.getY();
   }

   public abstract int getHeight();

   public abstract int getWidth();

   public final float getActualHeight() {
      return (float)this.getHeight() * this.scale;
   }

   public final float getActualWidth() {
      return (float)this.getWidth() * this.scale;
   }

   @JvmOverloads
   public GuiElement(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      this(name, (FloatPair)null, 2, (DefaultConstructorMarker)null);
   }

   static {
      sr = UResolution.INSTANCE;
      fr$delegate = LazyKt.lazy((Function0)null.INSTANCE);
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R#\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"},
      d2 = {"Lme/jooon/JooonAddons/gui/GuiElement$Companion;", "", "()V", "fr", "Lnet/minecraft/client/gui/FontRenderer;", "kotlin.jvm.PlatformType", "getFr", "()Lnet/minecraft/client/gui/FontRenderer;", "fr$delegate", "Lkotlin/Lazy;", "sr", "Lgg/essential/universal/UResolution;", "getSr", "()Lgg/essential/universal/UResolution;", "JooonAddons"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final UResolution getSr() {
         return GuiElement.sr;
      }

      public final FontRenderer getFr() {
         Lazy var1 = GuiElement.fr$delegate;
         return (FontRenderer)var1.getValue();
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
