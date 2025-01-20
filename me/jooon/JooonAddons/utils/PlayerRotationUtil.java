package me.jooon.JooonAddons.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.JooonAddons;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0018\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u001eH\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0007R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0010R\u001a\u0010\u001a\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016¨\u0006("},
   d2 = {"Lme/jooon/JooonAddons/utils/PlayerRotationUtil;", "", "rotationData", "Lme/jooon/JooonAddons/utils/PlayerRotation$RotationData;", "(Lme/jooon/JooonAddons/utils/PlayerRotation$RotationData;)V", "done", "", "getDone", "()Z", "setDone", "(Z)V", "endRot", "Lme/jooon/JooonAddons/utils/PlayerRotation$Rotation;", "getEndRot", "()Lme/jooon/JooonAddons/utils/PlayerRotation$Rotation;", "setEndRot", "(Lme/jooon/JooonAddons/utils/PlayerRotation$Rotation;)V", "endTime", "", "getEndTime", "()J", "setEndTime", "(J)V", "startRot", "getStartRot", "setStartRot", "startTime", "getStartTime", "setStartTime", "easeOutCubic", "", "number", "", "interpolate", "start", "end", "renderWorldLast", "", "event", "Lnet/minecraftforge/client/event/RenderWorldLastEvent;", "JooonAddons"}
)
public final class PlayerRotationUtil {
   @NotNull
   private PlayerRotation.Rotation startRot;
   @NotNull
   private PlayerRotation.Rotation endRot;
   private long startTime;
   private long endTime;
   private volatile boolean done;

   public PlayerRotationUtil(@NotNull PlayerRotation.RotationData rotationData) {
      Intrinsics.checkNotNullParameter(rotationData, "rotationData");
      super();
      this.startRot = rotationData.getStartRot();
      this.endRot = rotationData.getEndRotation();
      this.startTime = rotationData.getStartTime();
      this.endTime = rotationData.getEndTime();
      this.done = rotationData.getDone();
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

   @SubscribeEvent
   public final void renderWorldLast(@NotNull RenderWorldLastEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (JooonAddons.Companion.getMc().field_71439_g != null && JooonAddons.Companion.getMc().field_71441_e != null) {
         if (System.currentTimeMillis() <= this.endTime) {
            JooonAddons.Companion.getMc().field_71439_g.field_70177_z = this.interpolate(this.startRot.getYaw(), this.endRot.getYaw());
            JooonAddons.Companion.getMc().field_71439_g.field_70125_A = this.interpolate(this.startRot.getPitch(), this.endRot.getPitch());
         } else if (!this.done) {
            JooonAddons.Companion.getMc().field_71439_g.field_70177_z = this.endRot.getYaw();
            JooonAddons.Companion.getMc().field_71439_g.field_70125_A = this.endRot.getPitch();
            this.done = true;
            MinecraftForge.EVENT_BUS.unregister(this);
         }

      } else {
         MinecraftForge.EVENT_BUS.unregister(this);
      }
   }

   private final float interpolate(float start, float end) {
      float spentMillis = (float)(System.currentTimeMillis() - this.startTime);
      float relativeProgress = spentMillis / (float)(this.endTime - this.startTime);
      return (end - start) * this.easeOutCubic((double)relativeProgress) + start;
   }

   private final float easeOutCubic(double number) {
      return (float)(1.0D - Math.pow(1.0D - number, 3.0D));
   }
}
