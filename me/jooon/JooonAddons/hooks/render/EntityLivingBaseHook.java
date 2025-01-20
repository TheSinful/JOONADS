package me.jooon.JooonAddons.hooks.render;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.utils.SBInfo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.Team.EnumVisible;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"},
   d2 = {"Lme/jooon/JooonAddons/hooks/render/EntityLivingBaseHook;", "", "entity", "Lnet/minecraft/entity/EntityLivingBase;", "(Lnet/minecraft/entity/EntityLivingBase;)V", "getEntity", "()Lnet/minecraft/entity/EntityLivingBase;", "isChild", "", "cir", "Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfoReturnable;", "", "JooonAddons"}
)
public final class EntityLivingBaseHook {
   @NotNull
   private final EntityLivingBase entity;

   public EntityLivingBaseHook(@NotNull EntityLivingBase entity) {
      Intrinsics.checkNotNullParameter(entity, "entity");
      super();
      this.entity = entity;
   }

   @NotNull
   public final EntityLivingBase getEntity() {
      return this.entity;
   }

   public final void isChild(@NotNull CallbackInfoReturnable<Boolean> cir) {
      Intrinsics.checkNotNullParameter(cir, "cir");
      if (SBInfo.INSTANCE.getOnSkyblock()) {
         if (this.entity instanceof EntityPlayer) {
            if (Config.INSTANCE.getRealisticHeight()) {
               if (Config.INSTANCE.getRealisticHeightType() == 0) {
                  cir.setReturnValue(Intrinsics.areEqual(((EntityPlayer)this.entity).func_70005_c_(), "tripleB36"));
               } else if (((EntityPlayer)this.entity).func_96124_cp() != null) {
                  cir.setReturnValue(((EntityPlayer)this.entity).func_96124_cp().func_178770_i() != EnumVisible.NEVER);
               } else {
                  cir.setReturnValue(false);
               }
            } else {
               cir.setReturnValue(false);
            }
         } else {
            cir.setReturnValue(false);
         }

      }
   }
}
