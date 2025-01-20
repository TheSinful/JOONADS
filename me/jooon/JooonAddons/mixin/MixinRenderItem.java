package me.jooon.JooonAddons.mixin;

import me.jooon.JooonAddons.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({RenderItem.class})
public abstract class MixinRenderItem {
   @Inject(
      method = {"renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/resources/model/IBakedModel;)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/renderer/entity/RenderItem;renderModel(Lnet/minecraft/client/resources/model/IBakedModel;Lnet/minecraft/item/ItemStack;)V",
   shift = At.Shift.AFTER
)},
      cancellable = true
   )
   void stopGlint(ItemStack stack, IBakedModel model, CallbackInfo ci) {
      if (Config.INSTANCE.getRemoveArmorGlint()) {
         ItemStack[] var4 = Minecraft.func_71410_x().field_71439_g.field_71071_by.field_70460_b;
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            ItemStack itemStack = var4[var6];
            if (itemStack == stack) {
               stack.func_77978_p().func_82580_o("ench");
            }
         }
      }

   }
}
