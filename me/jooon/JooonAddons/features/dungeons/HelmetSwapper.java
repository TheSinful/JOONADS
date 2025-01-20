package me.jooon.JooonAddons.features.dungeons;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.config.Config;
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
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0004J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0004H\u0002J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0004H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0018"},
   d2 = {"Lme/jooon/JooonAddons/features/dungeons/HelmetSwapper;", "", "()V", "helmetOneLocation", "", "getHelmetOneLocation", "()I", "setHelmetOneLocation", "(I)V", "helmetTwoLocation", "getHelmetTwoLocation", "setHelmetTwoLocation", "lastTimeSwapped", "", "getLastTimeSwapped", "()J", "setLastTimeSwapped", "(J)V", "helmetSwapper", "", "helmetOption", "sendClick", "slotIndex", "swapHelmet", "JooonAddons"}
)
public final class HelmetSwapper {
   @NotNull
   public static final HelmetSwapper INSTANCE = new HelmetSwapper();
   private static int helmetOneLocation = -1;
   private static int helmetTwoLocation = -1;
   private static long lastTimeSwapped;

   private HelmetSwapper() {
   }

   public final int getHelmetOneLocation() {
      return helmetOneLocation;
   }

   public final void setHelmetOneLocation(int <set-?>) {
      helmetOneLocation = var1;
   }

   public final int getHelmetTwoLocation() {
      return helmetTwoLocation;
   }

   public final void setHelmetTwoLocation(int <set-?>) {
      helmetTwoLocation = var1;
   }

   public final long getLastTimeSwapped() {
      return lastTimeSwapped;
   }

   public final void setLastTimeSwapped(long <set-?>) {
      lastTimeSwapped = var1;
   }

   public final void helmetSwapper(int helmetOption) {
      if (System.currentTimeMillis() - lastTimeSwapped > 300L) {
         JooonAddons.Companion.getMc().func_147108_a((GuiScreen)(new GuiInventory((EntityPlayer)JooonAddons.Companion.getMc().field_71439_g)));
         this.swapHelmet(helmetOption);
         JooonAddons.Companion.getMc().field_71439_g.func_71053_j();
         JooonAddons.Companion.getMc().field_71439_g.func_85030_a("random.orb", 1.0F, 0.5F);
         lastTimeSwapped = System.currentTimeMillis();
      } else {
         JooonAddons.Companion.getMc().field_71439_g.func_145747_a((IChatComponent)(new ChatComponentText("§7[§aJooonAddons§7]§8 Slow down!")));
      }

   }

   private final void swapHelmet(int helmetOption) {
      String helmetToLookFor = helmetOption == 1 ? Config.INSTANCE.getHelmetToSwapNameOne() : Config.INSTANCE.getHelmetToSwapNameTwo();
      if (Intrinsics.areEqual(helmetToLookFor, "")) {
         JooonAddons.Companion.getMc().field_71439_g.func_145747_a((IChatComponent)(new ChatComponentText("§7[§aJooonAddons§7]§8 Helmet Name Cannot Be Empty")));
      } else {
         ItemStack var10000;
         int i;
         ItemStack itemStack;
         int armorSlot;
         String var8;
         if (helmetTwoLocation != -1 || helmetOneLocation != -1) {
            for(i = 5; i < 9; ++i) {
               var10000 = JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75139_a(i).func_75211_c();
               if (var10000 != null) {
                  itemStack = var10000;
                  var8 = itemStack.func_82833_r();
                  Intrinsics.checkNotNullExpressionValue(var8, "itemStack.displayName");
                  if (StringsKt.contains$default((CharSequence)var8, (CharSequence)helmetToLookFor, false, 2, (Object)null)) {
                     armorSlot = helmetOption == 1 ? helmetOneLocation : helmetTwoLocation;
                     if (armorSlot != -1) {
                        var10000 = (ItemStack)JooonAddons.Companion.getMc().field_71439_g.field_71069_bz.func_75138_a().get(armorSlot);
                        String helmetName = var10000 != null ? var10000.func_82833_r() : null;
                        this.sendClick(armorSlot);
                        JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75144_a(armorSlot, 0, 0, (EntityPlayer)JooonAddons.Companion.getMc().field_71439_g);
                        this.sendClick(i);
                        JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75144_a(i, 0, 0, (EntityPlayer)JooonAddons.Companion.getMc().field_71439_g);
                        JooonAddons.Companion.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow((new GuiInventory((EntityPlayer)JooonAddons.Companion.getMc().field_71439_g)).field_147002_h.field_75152_c, armorSlot, 0, 0, (ItemStack)null, (short)0)));
                        JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75144_a(armorSlot, 0, 0, (EntityPlayer)JooonAddons.Companion.getMc().field_71439_g);
                        JooonAddons.Companion.getMc().field_71439_g.func_145747_a((IChatComponent)(new ChatComponentText("§7[§aJooonAddons§7]§8 Equipped " + helmetName)));
                        return;
                     }
                     break;
                  }
               }
            }
         }

         for(i = 9; i < 45; ++i) {
            var10000 = JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75139_a(i).func_75211_c();
            if (var10000 != null) {
               itemStack = var10000;
               var8 = itemStack.func_82833_r();
               Intrinsics.checkNotNullExpressionValue(var8, "itemStack.displayName");
               if (StringsKt.contains$default((CharSequence)var8, (CharSequence)helmetToLookFor, false, 2, (Object)null)) {
                  armorSlot = 0;

                  for(int a = 0; a < 4; ++a) {
                     if (itemStack.func_77973_b().isValidArmor(itemStack, a, (Entity)JooonAddons.Companion.getMc().field_71439_g)) {
                        armorSlot = a + 5;
                        break;
                     }
                  }

                  this.sendClick(i);
                  JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75144_a(i, 0, 0, (EntityPlayer)JooonAddons.Companion.getMc().field_71439_g);
                  this.sendClick(armorSlot);
                  JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75144_a(armorSlot, 0, 0, (EntityPlayer)JooonAddons.Companion.getMc().field_71439_g);
                  JooonAddons.Companion.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow((new GuiInventory((EntityPlayer)JooonAddons.Companion.getMc().field_71439_g)).field_147002_h.field_75152_c, i, 0, 0, (ItemStack)null, (short)0)));
                  JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75144_a(i, 0, 0, (EntityPlayer)JooonAddons.Companion.getMc().field_71439_g);
                  JooonAddons.Companion.getMc().field_71439_g.func_145747_a((IChatComponent)(new ChatComponentText("§7[§aJooonAddons§7]§8 Equipped " + itemStack.func_82833_r())));
                  if (helmetOption == 1) {
                     helmetOneLocation = i;
                  } else {
                     helmetTwoLocation = i;
                  }

                  return;
               }
            }
         }

         JooonAddons.Companion.getMc().field_71439_g.func_145747_a((IChatComponent)(new ChatComponentText("§7[§aJooonAddons§7]§8 Couldn't find " + helmetToLookFor)));
      }
   }

   private final void sendClick(int slotIndex) {
      int windowId = (new GuiInventory((EntityPlayer)JooonAddons.Companion.getMc().field_71439_g)).field_147002_h.field_75152_c;
      ItemStack itemStack = JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75139_a(slotIndex).func_75211_c();
      JooonAddons.Companion.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow(windowId, slotIndex, 0, 0, itemStack, (short)0)));
   }
}
