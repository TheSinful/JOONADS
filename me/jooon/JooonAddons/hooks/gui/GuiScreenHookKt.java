package me.jooon.JooonAddons.hooks.gui;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.events.impl.SendChatMessageEvent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Metadata(
   mv = {1, 8, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"},
   d2 = {"onSendChatMessage", "", "message", "", "addToChat", "", "ci", "Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfo;", "JooonAddons"}
)
public final class GuiScreenHookKt {
   public static final void onSendChatMessage(@NotNull String message, boolean addToChat, @NotNull CallbackInfo ci) {
      Intrinsics.checkNotNullParameter(message, "message");
      Intrinsics.checkNotNullParameter(ci, "ci");
      if ((new SendChatMessageEvent(message, addToChat)).postAndCatch()) {
         ci.cancel();
      }

   }
}
