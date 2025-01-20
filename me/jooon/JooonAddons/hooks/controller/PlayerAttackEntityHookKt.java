package me.jooon.JooonAddons.hooks.controller;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.events.impl.PlayerAttackEntityEvent;
import me.jooon.JooonAddons.features.other.AutoDojo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Metadata(
   mv = {1, 8, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"},
   d2 = {"onPlayerEntityAttack", "", "playerIn", "Lnet/minecraft/entity/player/EntityPlayer;", "targetEntity", "Lnet/minecraft/entity/Entity;", "ci", "Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfo;", "JooonAddons"}
)
public final class PlayerAttackEntityHookKt {
   public static final void onPlayerEntityAttack(@NotNull EntityPlayer playerIn, @NotNull Entity targetEntity, @NotNull CallbackInfo ci) {
      Intrinsics.checkNotNullParameter(playerIn, "playerIn");
      Intrinsics.checkNotNullParameter(targetEntity, "targetEntity");
      Intrinsics.checkNotNullParameter(ci, "ci");
      if ((new PlayerAttackEntityEvent(targetEntity)).postAndCatch()) {
         ci.cancel();
      }

      if (Config.INSTANCE.getAutoDojo() && AutoDojo.INSTANCE.getDojoType() == AutoDojo.DojoType.FORCE) {
         if (!(targetEntity instanceof EntityZombie)) {
            return;
         }

         ItemStack var10000 = ((EntityZombie)targetEntity).func_71124_b(4);
         if (var10000 == null) {
            return;
         }

         ItemStack targetHelmet = var10000;
         if (Intrinsics.areEqual(targetHelmet.func_77973_b(), Items.field_151024_Q)) {
            ci.cancel();
         }
      }

   }
}
