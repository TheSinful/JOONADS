package me.jooon.JooonAddons.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.features.Feature;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0017\u0018\u00002\u00020\u0001:\u0002\u001d\u001eB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011R\u001a\u0010\u001a\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016¨\u0006\u001f"},
   d2 = {"Lme/jooon/JooonAddons/utils/PlayerRotation;", "Lme/jooon/JooonAddons/features/Feature;", "rotation", "Lme/jooon/JooonAddons/utils/PlayerRotation$Rotation;", "time", "", "(Lme/jooon/JooonAddons/utils/PlayerRotation$Rotation;J)V", "done", "", "getDone", "()Z", "setDone", "(Z)V", "endRot", "getEndRot", "()Lme/jooon/JooonAddons/utils/PlayerRotation$Rotation;", "setEndRot", "(Lme/jooon/JooonAddons/utils/PlayerRotation$Rotation;)V", "endTime", "getEndTime", "()J", "setEndTime", "(J)V", "startRot", "getStartRot", "setStartRot", "startTime", "getStartTime", "setStartTime", "Rotation", "RotationData", "JooonAddons"}
)
public final class PlayerRotation extends Feature {
   @NotNull
   private PlayerRotation.Rotation startRot;
   @NotNull
   private PlayerRotation.Rotation endRot;
   private long startTime;
   private long endTime;
   private volatile boolean done;

   public PlayerRotation(@NotNull PlayerRotation.Rotation rotation, long time) {
      Intrinsics.checkNotNullParameter(rotation, "rotation");
      super();
      this.startRot = new PlayerRotation.Rotation(0.0F, 0.0F);
      this.endRot = new PlayerRotation.Rotation(0.0F, 0.0F);
      this.done = true;
      this.done = false;
      this.startRot = new PlayerRotation.Rotation(this.getMc().field_71439_g.field_70177_z, this.getMc().field_71439_g.field_70125_A);
      this.endRot = rotation;
      this.startTime = System.currentTimeMillis();
      this.endTime = System.currentTimeMillis() + time;
      MinecraftForge.EVENT_BUS.register(new PlayerRotationUtil(new PlayerRotation.RotationData(this.startRot, this.endRot, this.startTime, this.endTime, this.done)));
   }

   @NotNull
   public final PlayerRotation.Rotation getStartRot() {
      return this.startRot;
   }

   public final void setStartRot(@NotNull PlayerRotation.Rotation <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.startRot = var1;
   }

   @NotNull
   public final PlayerRotation.Rotation getEndRot() {
      return this.endRot;
   }

   public final void setEndRot(@NotNull PlayerRotation.Rotation <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.endRot = var1;
   }

   public final long getStartTime() {
      return this.startTime;
   }

   public final void setStartTime(long <set-?>) {
      this.startTime = var1;
   }

   public final long getEndTime() {
      return this.endTime;
   }

   public final void setEndTime(long <set-?>) {
      this.endTime = var1;
   }

   public final boolean getDone() {
      return this.done;
   }

   public final void setDone(boolean <set-?>) {
      this.done = var1;
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0016"},
      d2 = {"Lme/jooon/JooonAddons/utils/PlayerRotation$Rotation;", "", "yaw", "", "pitch", "(FF)V", "getPitch", "()F", "setPitch", "(F)V", "getYaw", "setYaw", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "JooonAddons"}
   )
   public static final class Rotation {
      private float yaw;
      private float pitch;

      public Rotation(float yaw, float pitch) {
         this.yaw = yaw;
         this.pitch = pitch;
      }

      public final float getYaw() {
         return this.yaw;
      }

      public final void setYaw(float <set-?>) {
         this.yaw = var1;
      }

      public final float getPitch() {
         return this.pitch;
      }

      public final void setPitch(float <set-?>) {
         this.pitch = var1;
      }

      public final float component1() {
         return this.yaw;
      }

      public final float component2() {
         return this.pitch;
      }

      @NotNull
      public final PlayerRotation.Rotation copy(float yaw, float pitch) {
         return new PlayerRotation.Rotation(yaw, pitch);
      }

      // $FF: synthetic method
      public static PlayerRotation.Rotation copy$default(PlayerRotation.Rotation var0, float var1, float var2, int var3, Object var4) {
         if ((var3 & 1) != 0) {
            var1 = var0.yaw;
         }

         if ((var3 & 2) != 0) {
            var2 = var0.pitch;
         }

         return var0.copy(var1, var2);
      }

      @NotNull
      public String toString() {
         return "Rotation(yaw=" + this.yaw + ", pitch=" + this.pitch + ')';
      }

      public int hashCode() {
         int result = Float.hashCode(this.yaw);
         result = result * 31 + Float.hashCode(this.pitch);
         return result;
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof PlayerRotation.Rotation)) {
            return false;
         } else {
            PlayerRotation.Rotation var2 = (PlayerRotation.Rotation)other;
            if (Float.compare(this.yaw, var2.yaw) != 0) {
               return false;
            } else {
               return Float.compare(this.pitch, var2.pitch) == 0;
            }
         }
      }
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J;\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010!\u001a\u00020\t2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020&HÖ\u0001R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016¨\u0006'"},
      d2 = {"Lme/jooon/JooonAddons/utils/PlayerRotation$RotationData;", "", "startRot", "Lme/jooon/JooonAddons/utils/PlayerRotation$Rotation;", "endRotation", "startTime", "", "endTime", "done", "", "(Lme/jooon/JooonAddons/utils/PlayerRotation$Rotation;Lme/jooon/JooonAddons/utils/PlayerRotation$Rotation;JJZ)V", "getDone", "()Z", "setDone", "(Z)V", "getEndRotation", "()Lme/jooon/JooonAddons/utils/PlayerRotation$Rotation;", "setEndRotation", "(Lme/jooon/JooonAddons/utils/PlayerRotation$Rotation;)V", "getEndTime", "()J", "setEndTime", "(J)V", "getStartRot", "setStartRot", "getStartTime", "setStartTime", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "JooonAddons"}
   )
   public static final class RotationData {
      @NotNull
      private PlayerRotation.Rotation startRot;
      @NotNull
      private PlayerRotation.Rotation endRotation;
      private long startTime;
      private long endTime;
      private boolean done;

      public RotationData(@NotNull PlayerRotation.Rotation startRot, @NotNull PlayerRotation.Rotation endRotation, long startTime, long endTime, boolean done) {
         Intrinsics.checkNotNullParameter(startRot, "startRot");
         Intrinsics.checkNotNullParameter(endRotation, "endRotation");
         super();
         this.startRot = startRot;
         this.endRotation = endRotation;
         this.startTime = startTime;
         this.endTime = endTime;
         this.done = done;
      }

      @NotNull
      public final PlayerRotation.Rotation getStartRot() {
         return this.startRot;
      }

      public final void setStartRot(@NotNull PlayerRotation.Rotation <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.startRot = var1;
      }

      @NotNull
      public final PlayerRotation.Rotation getEndRotation() {
         return this.endRotation;
      }

      public final void setEndRotation(@NotNull PlayerRotation.Rotation <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.endRotation = var1;
      }

      public final long getStartTime() {
         return this.startTime;
      }

      public final void setStartTime(long <set-?>) {
         this.startTime = var1;
      }

      public final long getEndTime() {
         return this.endTime;
      }

      public final void setEndTime(long <set-?>) {
         this.endTime = var1;
      }

      public final boolean getDone() {
         return this.done;
      }

      public final void setDone(boolean <set-?>) {
         this.done = var1;
      }

      @NotNull
      public final PlayerRotation.Rotation component1() {
         return this.startRot;
      }

      @NotNull
      public final PlayerRotation.Rotation component2() {
         return this.endRotation;
      }

      public final long component3() {
         return this.startTime;
      }

      public final long component4() {
         return this.endTime;
      }

      public final boolean component5() {
         return this.done;
      }

      @NotNull
      public final PlayerRotation.RotationData copy(@NotNull PlayerRotation.Rotation startRot, @NotNull PlayerRotation.Rotation endRotation, long startTime, long endTime, boolean done) {
         Intrinsics.checkNotNullParameter(startRot, "startRot");
         Intrinsics.checkNotNullParameter(endRotation, "endRotation");
         return new PlayerRotation.RotationData(startRot, endRotation, startTime, endTime, done);
      }

      // $FF: synthetic method
      public static PlayerRotation.RotationData copy$default(PlayerRotation.RotationData var0, PlayerRotation.Rotation var1, PlayerRotation.Rotation var2, long var3, long var5, boolean var7, int var8, Object var9) {
         if ((var8 & 1) != 0) {
            var1 = var0.startRot;
         }

         if ((var8 & 2) != 0) {
            var2 = var0.endRotation;
         }

         if ((var8 & 4) != 0) {
            var3 = var0.startTime;
         }

         if ((var8 & 8) != 0) {
            var5 = var0.endTime;
         }

         if ((var8 & 16) != 0) {
            var7 = var0.done;
         }

         return var0.copy(var1, var2, var3, var5, var7);
      }

      @NotNull
      public String toString() {
         return "RotationData(startRot=" + this.startRot + ", endRotation=" + this.endRotation + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", done=" + this.done + ')';
      }

      public int hashCode() {
         int result = this.startRot.hashCode();
         result = result * 31 + this.endRotation.hashCode();
         result = result * 31 + Long.hashCode(this.startTime);
         result = result * 31 + Long.hashCode(this.endTime);
         int var10000 = result * 31;
         byte var10001 = this.done;
         if (var10001 != 0) {
            var10001 = 1;
         }

         result = var10000 + var10001;
         return result;
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof PlayerRotation.RotationData)) {
            return false;
         } else {
            PlayerRotation.RotationData var2 = (PlayerRotation.RotationData)other;
            if (!Intrinsics.areEqual(this.startRot, var2.startRot)) {
               return false;
            } else if (!Intrinsics.areEqual(this.endRotation, var2.endRotation)) {
               return false;
            } else if (this.startTime != var2.startTime) {
               return false;
            } else if (this.endTime != var2.endTime) {
               return false;
            } else {
               return this.done == var2.done;
            }
         }
      }
   }
}
