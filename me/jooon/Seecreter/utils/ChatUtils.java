package me.jooon.Seecreter.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class ChatUtils {
   public static void sendModMessage(String message) {
      Minecraft.func_71410_x().field_71439_g.func_145747_a(new ChatComponentText("§7[§aJooonAddons§7]§8 " + message));
   }
}
