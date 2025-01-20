package me.jooon.Seecreter.commands;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import me.jooon.Seecreter.Seecreter;
import me.jooon.Seecreter.config.SeecreterConfig;
import me.jooon.Seecreter.features.SecretAura;
import me.jooon.Seecreter.utils.ChatUtils;
import me.jooon.Seecreter.utils.DungeonLocationUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class ScCommand extends CommandBase {
   public String func_71517_b() {
      return "sc";
   }

   public String func_71518_a(ICommandSender sender) {
      return "";
   }

   public void func_71515_b(ICommandSender sender, String[] args) {
      if (args.length < 1) {
         Seecreter.config.openGui();
         ChatUtils.sendModMessage("/sc tg to toggle | /sc cl to clear clicked blocks");
      } else if (Objects.equals(args[0], "roomid")) {
         ChatUtils.sendModMessage(String.valueOf(DungeonLocationUtils.getCurrentRoomId()));
      } else if (args[0].startsWith("t")) {
         SeecreterConfig.secretAuraEnabled = !SeecreterConfig.secretAuraEnabled;
         ChatUtils.sendModMessage(SeecreterConfig.secretAuraEnabled ? "Seecreter enabled!" : "Seecreter disabled!");
      } else if (args[0].startsWith("c")) {
         SecretAura.clearBlocks();
         ChatUtils.sendModMessage("Cleared blocks clicked by Seecreter");
      }

   }

   public boolean func_71519_b(ICommandSender sender) {
      return true;
   }

   public List<String> func_71514_a() {
      return Collections.emptyList();
   }
}
