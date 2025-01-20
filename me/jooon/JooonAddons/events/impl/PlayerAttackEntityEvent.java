package me.jooon.JooonAddons.events.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.events.JooonAddonsEvent;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"},
   d2 = {"Lme/jooon/JooonAddons/events/impl/PlayerAttackEntityEvent;", "Lme/jooon/JooonAddons/events/JooonAddonsEvent;", "targetEntity", "Lnet/minecraft/entity/Entity;", "(Lnet/minecraft/entity/Entity;)V", "getTargetEntity", "()Lnet/minecraft/entity/Entity;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "JooonAddons"}
)
public final class PlayerAttackEntityEvent extends JooonAddonsEvent {
   @NotNull
   private final Entity targetEntity;

   public PlayerAttackEntityEvent(@NotNull Entity targetEntity) {
      Intrinsics.checkNotNullParameter(targetEntity, "targetEntity");
      super();
      this.targetEntity = targetEntity;
   }

   @NotNull
   public final Entity getTargetEntity() {
      return this.targetEntity;
   }

   @NotNull
   public final Entity component1() {
      return this.targetEntity;
   }

   @NotNull
   public final PlayerAttackEntityEvent copy(@NotNull Entity targetEntity) {
      Intrinsics.checkNotNullParameter(targetEntity, "targetEntity");
      return new PlayerAttackEntityEvent(targetEntity);
   }

   // $FF: synthetic method
   public static PlayerAttackEntityEvent copy$default(PlayerAttackEntityEvent var0, Entity var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.targetEntity;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "PlayerAttackEntityEvent(targetEntity=" + this.targetEntity + ')';
   }

   public int hashCode() {
      return this.targetEntity.hashCode();
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof PlayerAttackEntityEvent)) {
         return false;
      } else {
         PlayerAttackEntityEvent var2 = (PlayerAttackEntityEvent)other;
         return Intrinsics.areEqual(this.targetEntity, var2.targetEntity);
      }
   }
}
