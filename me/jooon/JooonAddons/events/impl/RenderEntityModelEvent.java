package me.jooon.JooonAddons.events.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.events.JooonAddonsEvent;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"},
   d2 = {"Lme/jooon/JooonAddons/events/impl/RenderEntityModelEvent;", "Lme/jooon/JooonAddons/events/JooonAddonsEvent;", "entity", "Lnet/minecraft/entity/EntityLivingBase;", "model", "Lnet/minecraft/client/model/ModelBase;", "partialTicks", "", "(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/client/model/ModelBase;F)V", "getEntity", "()Lnet/minecraft/entity/EntityLivingBase;", "getModel", "()Lnet/minecraft/client/model/ModelBase;", "getPartialTicks", "()F", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "JooonAddons"}
)
public final class RenderEntityModelEvent extends JooonAddonsEvent {
   @NotNull
   private final EntityLivingBase entity;
   @NotNull
   private final ModelBase model;
   private final float partialTicks;

   public RenderEntityModelEvent(@NotNull EntityLivingBase entity, @NotNull ModelBase model, float partialTicks) {
      Intrinsics.checkNotNullParameter(entity, "entity");
      Intrinsics.checkNotNullParameter(model, "model");
      super();
      this.entity = entity;
      this.model = model;
      this.partialTicks = partialTicks;
   }

   @NotNull
   public final EntityLivingBase getEntity() {
      return this.entity;
   }

   @NotNull
   public final ModelBase getModel() {
      return this.model;
   }

   public final float getPartialTicks() {
      return this.partialTicks;
   }

   @NotNull
   public final EntityLivingBase component1() {
      return this.entity;
   }

   @NotNull
   public final ModelBase component2() {
      return this.model;
   }

   public final float component3() {
      return this.partialTicks;
   }

   @NotNull
   public final RenderEntityModelEvent copy(@NotNull EntityLivingBase entity, @NotNull ModelBase model, float partialTicks) {
      Intrinsics.checkNotNullParameter(entity, "entity");
      Intrinsics.checkNotNullParameter(model, "model");
      return new RenderEntityModelEvent(entity, model, partialTicks);
   }

   // $FF: synthetic method
   public static RenderEntityModelEvent copy$default(RenderEntityModelEvent var0, EntityLivingBase var1, ModelBase var2, float var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = var0.entity;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.model;
      }

      if ((var4 & 4) != 0) {
         var3 = var0.partialTicks;
      }

      return var0.copy(var1, var2, var3);
   }

   @NotNull
   public String toString() {
      return "RenderEntityModelEvent(entity=" + this.entity + ", model=" + this.model + ", partialTicks=" + this.partialTicks + ')';
   }

   public int hashCode() {
      int result = this.entity.hashCode();
      result = result * 31 + this.model.hashCode();
      result = result * 31 + Float.hashCode(this.partialTicks);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof RenderEntityModelEvent)) {
         return false;
      } else {
         RenderEntityModelEvent var2 = (RenderEntityModelEvent)other;
         if (!Intrinsics.areEqual(this.entity, var2.entity)) {
            return false;
         } else if (!Intrinsics.areEqual(this.model, var2.model)) {
            return false;
         } else {
            return Float.compare(this.partialTicks, var2.partialTicks) == 0;
         }
      }
   }
}
