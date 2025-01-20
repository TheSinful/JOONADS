package me.jooon.JooonAddons.utils.core;

import gg.essential.universal.UResolution;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0006\u0012\u0006\u0010\u0004\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\t\u001a\u00020\u0000J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0006\u0010\r\u001a\u00020\u0006J\u0006\u0010\u000e\u001a\u00020\u0006J\b\u0010\u000f\u001a\u00020\u0003H\u0016J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u0006J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0006J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\u0002\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"},
   d2 = {"Lme/jooon/JooonAddons/utils/core/FloatPair;", "", "x", "", "y", "(II)V", "", "(FF)V", "Lorg/apache/commons/lang3/mutable/MutableFloat;", "cloneCoords", "equals", "", "other", "getX", "getY", "hashCode", "setX", "", "setY", "toString", "", "Companion", "JooonAddons"}
)
public final class FloatPair {
   @NotNull
   public static final FloatPair.Companion Companion = new FloatPair.Companion((DefaultConstructorMarker)null);
   @NotNull
   private final MutableFloat x;
   @NotNull
   private final MutableFloat y;
   @NotNull
   private static final UResolution sr;

   public FloatPair(float x, float y) {
      this.x = new MutableFloat(x);
      this.y = new MutableFloat(y);
   }

   public FloatPair(int x, int y) {
      float var10001 = (float)x;
      UResolution var10002 = sr;
      var10001 /= (float)UResolution.getScaledHeight();
      float var3 = (float)y;
      UResolution var10003 = sr;
      this(var10001, var3 / (float)UResolution.getScaledHeight());
   }

   public final float getX() {
      Float var10000 = this.x.getValue();
      Intrinsics.checkNotNullExpressionValue(var10000, "x.value");
      return ((Number)var10000).floatValue();
   }

   public final float getY() {
      Float var10000 = this.y.getValue();
      Intrinsics.checkNotNullExpressionValue(var10000, "y.value");
      return ((Number)var10000).floatValue();
   }

   public final void setY(float y) {
      this.y.setValue(y);
   }

   public final void setX(float x) {
      this.x.setValue(x);
   }

   public boolean equals(@Nullable Object other) {
      if (other == null) {
         return false;
      } else if (other == this) {
         return true;
      } else if (!Intrinsics.areEqual(other.getClass(), this.getClass())) {
         return false;
      } else {
         FloatPair otherFloatPair = (FloatPair)other;
         return (new EqualsBuilder()).append(this.getX(), otherFloatPair.getX()).append(this.getY(), otherFloatPair.getY()).isEquals();
      }
   }

   public int hashCode() {
      return (new HashCodeBuilder(83, 11)).append(this.getX()).append(this.getY()).toHashCode();
   }

   @NotNull
   public String toString() {
      return "" + this.getX() + '|' + this.getY();
   }

   @NotNull
   public final FloatPair cloneCoords() {
      return new FloatPair(this.getX(), this.getY());
   }

   static {
      sr = UResolution.INSTANCE;
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"},
      d2 = {"Lme/jooon/JooonAddons/utils/core/FloatPair$Companion;", "", "()V", "sr", "Lgg/essential/universal/UResolution;", "JooonAddons"}
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
