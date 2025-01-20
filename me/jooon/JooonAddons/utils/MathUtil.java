package me.jooon.JooonAddons.utils;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0007¨\u0006\n"},
   d2 = {"Lme/jooon/JooonAddons/utils/MathUtil;", "", "()V", "ceil", "", "value", "", "", "fastFloor", "floor", "JooonAddons"}
)
public final class MathUtil {
   @NotNull
   public static final MathUtil INSTANCE = new MathUtil();

   private MathUtil() {
   }

   public final int fastFloor(double value) {
      return (int)(value + 1024.0D) - 1024;
   }

   public final int ceil(float value) {
      int i = (int)value;
      return value > (float)i ? i + 1 : i;
   }

   public final int ceil(double value) {
      int i = (int)value;
      return value > (double)i ? i + 1 : i;
   }

   public final int floor(float value) {
      int i = (int)value;
      return value < (float)i ? i - 1 : i;
   }
}
