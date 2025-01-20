package me.jooon.JooonAddons.events.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.events.JooonAddonsEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Cancelable
@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"},
   d2 = {"Lme/jooon/JooonAddons/events/impl/SendChatMessageEvent;", "Lme/jooon/JooonAddons/events/JooonAddonsEvent;", "message", "", "addToChat", "", "(Ljava/lang/String;Z)V", "getAddToChat", "()Z", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "JooonAddons"}
)
public final class SendChatMessageEvent extends JooonAddonsEvent {
   @NotNull
   private final String message;
   private final boolean addToChat;

   public SendChatMessageEvent(@NotNull String message, boolean addToChat) {
      Intrinsics.checkNotNullParameter(message, "message");
      super();
      this.message = message;
      this.addToChat = addToChat;
   }

   @NotNull
   public final String getMessage() {
      return this.message;
   }

   public final boolean getAddToChat() {
      return this.addToChat;
   }

   @NotNull
   public final String component1() {
      return this.message;
   }

   public final boolean component2() {
      return this.addToChat;
   }

   @NotNull
   public final SendChatMessageEvent copy(@NotNull String message, boolean addToChat) {
      Intrinsics.checkNotNullParameter(message, "message");
      return new SendChatMessageEvent(message, addToChat);
   }

   // $FF: synthetic method
   public static SendChatMessageEvent copy$default(SendChatMessageEvent var0, String var1, boolean var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.message;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.addToChat;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "SendChatMessageEvent(message=" + this.message + ", addToChat=" + this.addToChat + ')';
   }

   public int hashCode() {
      int result = this.message.hashCode();
      int var10000 = result * 31;
      byte var10001 = this.addToChat;
      if (var10001 != 0) {
         var10001 = 1;
      }

      result = var10000 + var10001;
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof SendChatMessageEvent)) {
         return false;
      } else {
         SendChatMessageEvent var2 = (SendChatMessageEvent)other;
         if (!Intrinsics.areEqual(this.message, var2.message)) {
            return false;
         } else {
            return this.addToChat == var2.addToChat;
         }
      }
   }
}
