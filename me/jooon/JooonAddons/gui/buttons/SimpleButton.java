package me.jooon.JooonAddons.gui.buttons;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.UIConstraints;
import gg.essential.elementa.components.UIBlock;
import gg.essential.elementa.components.UIText;
import gg.essential.elementa.constraints.CenterConstraint;
import gg.essential.elementa.constraints.ColorConstraint;
import gg.essential.elementa.constraints.HeightConstraint;
import gg.essential.elementa.constraints.RelativeConstraint;
import gg.essential.elementa.constraints.WidthConstraint;
import gg.essential.elementa.constraints.XConstraint;
import gg.essential.elementa.constraints.YConstraint;
import gg.essential.elementa.constraints.animation.AnimatingConstraints;
import gg.essential.elementa.constraints.animation.AnimationStrategy;
import gg.essential.elementa.constraints.animation.Animations;
import gg.essential.elementa.dsl.ComponentsKt;
import gg.essential.elementa.dsl.UtilitiesKt;
import java.awt.Color;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\t¨\u0006\u0011"},
   d2 = {"Lme/jooon/JooonAddons/gui/buttons/SimpleButton;", "Lgg/essential/elementa/components/UIBlock;", "t", "", "h", "", "w", "(Ljava/lang/String;ZZ)V", "getH", "()Z", "getT", "()Ljava/lang/String;", "text", "Lgg/essential/elementa/components/UIText;", "getText", "()Lgg/essential/elementa/components/UIText;", "getW", "JooonAddons"}
)
@SourceDebugExtension({"SMAP\nSimpleButton.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SimpleButton.kt\nme/jooon/JooonAddons/gui/buttons/SimpleButton\n+ 2 components.kt\ngg/essential/elementa/dsl/ComponentsKt\n+ 3 Extensions.kt\ngg/essential/vigilance/utils/ExtensionsKt\n*L\n1#1,88:1\n9#2,3:89\n9#2,3:92\n6#3,5:95\n*S KotlinDebug\n*F\n+ 1 SimpleButton.kt\nme/jooon/JooonAddons/gui/buttons/SimpleButton\n*L\n41#1:89,3\n49#1:92,3\n84#1:95,5\n*E\n"})
public final class SimpleButton extends UIBlock {
   @NotNull
   private final String t;
   private final boolean h;
   private final boolean w;
   @NotNull
   private final UIText text;

   @JvmOverloads
   public SimpleButton(@NotNull String t, boolean h, boolean w) {
      Intrinsics.checkNotNullParameter(t, "t");
      super(new Color(0, 0, 0, 80));
      this.t = t;
      this.h = h;
      this.w = w;
      UIComponent $this$onLeftClick$iv = (UIComponent)(new UIText(this.t, false, (Color)null, 6, (DefaultConstructorMarker)null));
      int $i$f$onLeftClick = false;
      int var8 = false;
      UIConstraints $this$_init__u24lambda_u241 = $this$onLeftClick$iv.getConstraints();
      int var10 = false;
      $this$_init__u24lambda_u241.setX((XConstraint)(new CenterConstraint()));
      $this$_init__u24lambda_u241.setY((YConstraint)(new CenterConstraint()));
      $this$_init__u24lambda_u241.setColor((ColorConstraint)UtilitiesKt.toConstraint(new Color(14737632)));
      this.text = (UIText)ComponentsKt.childOf($this$onLeftClick$iv, (UIComponent)this);
      $this$onLeftClick$iv = (UIComponent)this;
      $i$f$onLeftClick = false;
      var8 = false;
      $this$_init__u24lambda_u241 = $this$onLeftClick$iv.getConstraints();
      var10 = false;
      $this$_init__u24lambda_u241.setWidth(this.w ? (WidthConstraint)(new RelativeConstraint(0.0F, 1, (DefaultConstructorMarker)null)) : (WidthConstraint)UtilitiesKt.pixels$default((Number)this.text.getWidth() + (float)40, false, false, 3, (Object)null));
      $this$_init__u24lambda_u241.setHeight(this.h ? (HeightConstraint)(new RelativeConstraint(0.0F, 1, (DefaultConstructorMarker)null)) : (HeightConstraint)UtilitiesKt.pixels$default((Number)this.text.getHeight() + (float)10, false, false, 3, (Object)null));
      $this$onLeftClick$iv = ((SimpleButton)$this$onLeftClick$iv).onMouseEnter((Function1)(new Function1<UIComponent, Unit>() {
         public final void invoke(@NotNull UIComponent $this$onMouseEnter) {
            Intrinsics.checkNotNullParameter($this$onMouseEnter, "$this$onMouseEnter");
            int $i$f$constrain = false;
            int var6 = false;
            AnimatingConstraints anim$iv = $this$onMouseEnter.makeAnimation();
            int var9 = false;
            anim$iv.setColorAnimation((AnimationStrategy)Animations.OUT_EXP, 0.5F, (ColorConstraint)UtilitiesKt.toConstraint(new Color(255, 255, 255, 80)), 0.0F);
            $this$onMouseEnter.animateTo(anim$iv);
            UIComponent $this$constrain$iv = (UIComponent)SimpleButton.this.getText();
            $i$f$constrain = false;
            var6 = false;
            UIConstraints $this$invoke_u24lambda_u241 = $this$constrain$iv.getConstraints();
            int var8 = false;
            $this$invoke_u24lambda_u241.setColor((ColorConstraint)UtilitiesKt.toConstraint(new Color(16777120)));
         }
      })).onMouseLeave((Function1)(new Function1<UIComponent, Unit>() {
         public final void invoke(@NotNull UIComponent $this$onMouseLeave) {
            Intrinsics.checkNotNullParameter($this$onMouseLeave, "$this$onMouseLeave");
            int $i$f$constrain = false;
            int var6 = false;
            AnimatingConstraints anim$iv = $this$onMouseLeave.makeAnimation();
            int var9 = false;
            AnimatingConstraints.setColorAnimation$default(anim$iv, (AnimationStrategy)Animations.OUT_EXP, 0.5F, (ColorConstraint)UtilitiesKt.toConstraint(new Color(0, 0, 0, 80)), 0.0F, 8, (Object)null);
            $this$onMouseLeave.animateTo(anim$iv);
            UIComponent $this$constrain$iv = (UIComponent)SimpleButton.this.getText();
            $i$f$constrain = false;
            var6 = false;
            UIConstraints $this$invoke_u24lambda_u241 = $this$constrain$iv.getConstraints();
            int var8 = false;
            $this$invoke_u24lambda_u241.setColor((ColorConstraint)UtilitiesKt.toConstraint(new Color(14737632)));
         }
      }));
      $i$f$onLeftClick = false;
      $this$onLeftClick$iv.onMouseClick((Function2)(new SimpleButton$special$$inlined$onLeftClick$1()));
   }

   // $FF: synthetic method
   public SimpleButton(String var1, boolean var2, boolean var3, int var4, DefaultConstructorMarker var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      this(var1, var2, var3);
   }

   @NotNull
   public final String getT() {
      return this.t;
   }

   public final boolean getH() {
      return this.h;
   }

   public final boolean getW() {
      return this.w;
   }

   @NotNull
   public final UIText getText() {
      return this.text;
   }

   @JvmOverloads
   public SimpleButton(@NotNull String t, boolean h) {
      Intrinsics.checkNotNullParameter(t, "t");
      this(t, h, false, 4, (DefaultConstructorMarker)null);
   }

   @JvmOverloads
   public SimpleButton(@NotNull String t) {
      Intrinsics.checkNotNullParameter(t, "t");
      this(t, false, false, 6, (DefaultConstructorMarker)null);
   }
}
