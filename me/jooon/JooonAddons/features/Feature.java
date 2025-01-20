package me.jooon.JooonAddons.features;

import gg.essential.universal.UChat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.JooonAddons;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"},
   d2 = {"Lme/jooon/JooonAddons/features/Feature;", "", "()V", "mc", "Lnet/minecraft/client/Minecraft;", "getMc", "()Lnet/minecraft/client/Minecraft;", "printdev", "", "text", "", "JooonAddons"}
)
public abstract class Feature {
   @NotNull
   private final Minecraft mc;

   public Feature() {
      Minecraft var10001 = Minecraft.func_71410_x();
      Intrinsics.checkNotNullExpressionValue(var10001, "getMinecraft()");
      this.mc = var10001;
   }

   @NotNull
   protected final Minecraft getMc() {
      return this.mc;
   }

   protected final void printdev(@NotNull String text) {
      Intrinsics.checkNotNullParameter(text, "text");
      if (JooonAddons.Companion.getDevMode()) {
         System.out.println("[JooonAddons DEV] " + text);
         UChat.chat("[JooonAddons DEV] " + text);
      }

   }
}
