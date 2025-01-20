package me.jooon.JooonAddons.mixin;

import me.jooon.JooonAddons.hooks.gui.GuiScreenHookKt;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiScreen.class})
public abstract class MixinGuiScreen extends Gui implements GuiYesNoCallback {
   @Inject(
      method = {"sendChatMessage(Ljava/lang/String;Z)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onSendChatMessage(String message, boolean addToChat, CallbackInfo ci) {
      GuiScreenHookKt.onSendChatMessage(message, addToChat, ci);
   }
}
