package me.jooon.JooonAddons.commands;

import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.features.dungeons.Jerry;
import me.jooon.JooonAddons.features.other.Ping;
import me.jooon.JooonAddons.gui.LocationEditGui;
import me.jooon.JooonAddons.utils.PlayerRotation;
import me.jooon.JooonAddons.utils.SBInfo;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J)\u0010\f\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0010\u0010\u000e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u000fH\u0016¢\u0006\u0002\u0010\u0010¨\u0006\u0011"},
   d2 = {"Lme/jooon/JooonAddons/commands/JooonAddonsCommand;", "Lnet/minecraft/command/CommandBase;", "()V", "getCommandAliases", "", "", "getCommandName", "getCommandUsage", "sender", "Lnet/minecraft/command/ICommandSender;", "getRequiredPermissionLevel", "", "processCommand", "", "args", "", "(Lnet/minecraft/command/ICommandSender;[Ljava/lang/String;)V", "JooonAddons"}
)
public final class JooonAddonsCommand extends CommandBase {
   @NotNull
   public String func_71517_b() {
      return "melody";
   }

   @NotNull
   public List<String> func_71514_a() {
      String[] var1 = new String[]{"melodymod", "dojo", "helper", "jooon"};
      return CollectionsKt.listOf(var1);
   }

   @NotNull
   public String func_71518_a(@Nullable ICommandSender sender) {
      return '/' + this.func_71517_b();
   }

   public int func_82362_a() {
      return 0;
   }

   public void func_71515_b(@Nullable ICommandSender sender, @Nullable String[] args) {
      Intrinsics.checkNotNull(args);
      if (args.length == 0) {
         JooonAddons.Companion.setCurrentGui((GuiScreen)Config.INSTANCE.gui());
      } else {
         String var10000 = args[0].toLowerCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var10000, "this as java.lang.String).toLowerCase(Locale.ROOT)");
         String var3 = var10000;
         if (Intrinsics.areEqual(var3, "")) {
            JooonAddons.Companion.setCurrentGui((GuiScreen)(new LocationEditGui()));
         } else if (Intrinsics.areEqual(var3, "")) {
            JooonAddons.Companion.setCurrentGui((GuiScreen)(new LocationEditGui()));
         } else if (Intrinsics.areEqual(var3, "")) {
            JooonAddons.Companion.setCurrentGui((GuiScreen)(new LocationEditGui()));
         } else if (Intrinsics.areEqual(var3, "")) {
            Config.INSTANCE.writeData();
         } else if (Intrinsics.areEqual(var3, "")) {
            if (args.length < 2) {
            }
         } else if (!Intrinsics.areEqual(var3, "")) {
            if (Intrinsics.areEqual(var3, "")) {
               Utils.INSTANCE.addMessage(String.valueOf(Ping.INSTANCE.getPing()));
               Ping.INSTANCE.sendPing();
            } else if (Intrinsics.areEqual(var3, "")) {
               JooonAddons.Companion.setDevMode(!JooonAddons.Companion.getDevMode());
               Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§8 Developer mode: " + JooonAddons.Companion.getDevMode());
            } else if (Intrinsics.areEqual(var3, "")) {
               JooonAddons.Companion.getMc().field_71439_g.func_145747_a((IChatComponent)(new ChatComponentText("mode: " + SBInfo.INSTANCE.getMode() + " inSkyblock: " + SBInfo.INSTANCE.getOnSkyblock())));
            } else if (Intrinsics.areEqual(var3, "")) {
               Utils.INSTANCE.scoreboardData();
            } else if (Intrinsics.areEqual(var3, "")) {
               Jerry.INSTANCE.toggleJerry();
            } else if (Intrinsics.areEqual(var3, "")) {
               new PlayerRotation(new PlayerRotation.Rotation(Float.parseFloat(args[1]), Float.parseFloat(args[2])), 0L);
            } else {
               JooonAddons.Companion.getMc().field_71439_g.func_145747_a((IChatComponent)(new ChatComponentText("§aJooonAddons §f:: §bUsage:\n§aJooonAddons §f:: §b/jooon §f- §aOpens feature GUI.")));
            }
         }

      }
   }
}
