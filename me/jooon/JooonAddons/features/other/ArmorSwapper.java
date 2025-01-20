package me.jooon.JooonAddons.features.other;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\u0004H\u0002J\u0016\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b¨\u0006\u0010"},
   d2 = {"Lme/jooon/JooonAddons/features/other/ArmorSwapper;", "", "()V", "armorSwapper", "", "isHypixelHelmet", "", "item", "Lnet/minecraft/item/ItemStack;", "sendClick", "slotIndex", "", "swapArmor", "swapPiece", "armorSlot", "inventorySlot", "JooonAddons"}
)
public final class ArmorSwapper {
   @NotNull
   public static final ArmorSwapper INSTANCE = new ArmorSwapper();

   private ArmorSwapper() {
   }

   public final void armorSwapper() {
      JooonAddons.Companion.getMc().func_147108_a((GuiScreen)(new GuiInventory((EntityPlayer)JooonAddons.Companion.getMc().field_71439_g)));
      this.swapArmor();
      JooonAddons.Companion.getMc().field_71439_g.func_71053_j();
      JooonAddons.Companion.getMc().field_71439_g.func_85030_a("random.orb", 1.0F, 0.5F);
   }

   private final void swapArmor() {
      List armorSlotsUsed = (List)(new ArrayList());

      for(int i = 9; i < 13; ++i) {
         ItemStack var10000 = JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75139_a(i).func_75211_c();
         if (var10000 != null) {
            ItemStack inventorySlot = var10000;
            int armorSlot = 0;

            for(int a = 0; a < 4; ++a) {
               if (inventorySlot.func_77973_b().isValidArmor(inventorySlot, a, (Entity)JooonAddons.Companion.getMc().field_71439_g)) {
                  if (!armorSlotsUsed.contains(a + 5) && (a != 0 || this.isHypixelHelmet(inventorySlot))) {
                     armorSlot = a + 5;
                     armorSlotsUsed.add(armorSlot);
                  }
                  break;
               }
            }

            if (armorSlot != 0) {
               this.swapPiece(armorSlot, i);
               JooonAddons.Companion.getMc().field_71439_g.func_145747_a((IChatComponent)(new ChatComponentText("§7[§aJooonAddons§7]§8 Equipped: " + inventorySlot.func_82833_r())));
            }
         }
      }

   }

   private final void sendClick(int slotIndex) {
      int windowId = (new GuiInventory((EntityPlayer)JooonAddons.Companion.getMc().field_71439_g)).field_147002_h.field_75152_c;
      ItemStack itemStack = JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75139_a(slotIndex).func_75211_c();
      JooonAddons.Companion.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow(windowId, slotIndex, 0, 0, itemStack, (short)0)));
   }

   public final void swapPiece(int armorSlot, int inventorySlot) {
      this.sendClick(inventorySlot);
      JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75144_a(inventorySlot, 0, 0, (EntityPlayer)JooonAddons.Companion.getMc().field_71439_g);
      this.sendClick(armorSlot);
      JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75144_a(armorSlot, 0, 0, (EntityPlayer)JooonAddons.Companion.getMc().field_71439_g);
      ItemStack itemStack = JooonAddons.Companion.getMc().field_71439_g.field_71069_bz.func_75138_a().get(inventorySlot) == null ? null : (ItemStack)JooonAddons.Companion.getMc().field_71439_g.field_71069_bz.func_75138_a().get(inventorySlot);
      JooonAddons.Companion.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow((new GuiInventory((EntityPlayer)JooonAddons.Companion.getMc().field_71439_g)).field_147002_h.field_75152_c, inventorySlot, 0, 0, itemStack, (short)0)));
      JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75144_a(inventorySlot, 0, 0, (EntityPlayer)JooonAddons.Companion.getMc().field_71439_g);
   }

   public final boolean isHypixelHelmet(@NotNull ItemStack item) {
      Intrinsics.checkNotNullParameter(item, "item");
      Iterator var2 = Utils.INSTANCE.getItemLore(item).iterator();

      String line;
      do {
         if (!var2.hasNext()) {
            return false;
         }

         line = (String)var2.next();
      } while(line == null || !StringsKt.contains$default((CharSequence)Utils.stripColor(line), (CharSequence)"HELMET", false, 2, (Object)null));

      return true;
   }
}
