package me.jooon.Seecreter.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;

public class PacketUtils {
   public static void sendPacket(Packet packet) {
      Minecraft.func_71410_x().func_147114_u().func_147298_b().func_179290_a(packet);
   }
}
