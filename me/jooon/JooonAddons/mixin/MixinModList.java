package me.jooon.JooonAddons.mixin;

import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   value = {ModList.class},
   remap = false
)
public class MixinModList {
   @Shadow
   private Map<String, String> modTags;

   @Inject(
      method = {"<init>(Ljava/util/List;)V"},
      at = {@At("RETURN")}
   )
   private void removeMod(List<ModContainer> modList, CallbackInfo ci) {
      if (!Minecraft.func_71410_x().func_71387_A()) {
         this.modTags.remove("jooonaddons");
      }

   }
}
