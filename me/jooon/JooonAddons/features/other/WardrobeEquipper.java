package me.jooon.JooonAddons.features.other;

import gg.essential.api.utils.Multithreading;
import gg.essential.universal.UChat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.features.Feature;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent.Pre;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"},
   d2 = {"Lme/jooon/JooonAddons/features/other/WardrobeEquipper;", "Lme/jooon/JooonAddons/features/Feature;", "arg", "", "(Ljava/lang/String;)V", "onDrawScreen", "", "event", "Lnet/minecraftforge/client/event/GuiScreenEvent$DrawScreenEvent$Pre;", "JooonAddons"}
)
public final class WardrobeEquipper extends Feature {
   @NotNull
   private final String arg;

   public WardrobeEquipper(@NotNull String arg) {
      Intrinsics.checkNotNullParameter(arg, "arg");
      super();
      this.arg = arg;
   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public final void onDrawScreen(@NotNull Pre event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (event.gui instanceof GuiChest) {
         GuiScreen var10000 = event.gui;
         Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type net.minecraft.client.gui.inventory.GuiChest");
         GuiChest chest = (GuiChest)var10000;
         BooleanRef foundArmor = new BooleanRef();
         Multithreading.runAsync(WardrobeEquipper::onDrawScreen$lambda$0);
         MinecraftForge.EVENT_BUS.unregister(this);
      }
   }

   private static final void onDrawScreen$lambda$0(GuiChest $chest, WardrobeEquipper this$0, BooleanRef $foundArmor) {
      Intrinsics.checkNotNullParameter($chest, "$chest");
      Intrinsics.checkNotNullParameter(this$0, "this$0");
      Intrinsics.checkNotNullParameter($foundArmor, "$foundArmor");
      Thread.sleep(200L);

      ItemStack var10000;
      int index;
      ItemStack helmet;
      String var5;
      for(index = 0; index < 9; ++index) {
         var10000 = (ItemStack)$chest.field_147002_h.func_75138_a().get(index);
         if (var10000 != null) {
            helmet = var10000;
            var5 = helmet.func_82833_r();
            Intrinsics.checkNotNullExpressionValue(var5, "helmet.displayName");
            if (StringsKt.contains((CharSequence)Utils.stripColor(var5), (CharSequence)this$0.arg, true)) {
               $foundArmor.element = true;
               var5 = ((ItemStack)$chest.field_147002_h.func_75138_a().get(index + 36)).func_82833_r();
               Intrinsics.checkNotNullExpressionValue(var5, "chest.inventorySlots.inv…y[index + 36].displayName");
               if (StringsKt.contains$default((CharSequence)Utils.stripColor(var5), (CharSequence)"Equipped", false, 2, (Object)null)) {
                  UChat.chat("§7[§aJooonAddons§7]§8 " + this$0.arg + " is already equipped");
               } else {
                  this$0.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow($chest.field_147002_h.field_75152_c, index + 36, 0, 0, (ItemStack)$chest.field_147002_h.func_75138_a().get(index + 36), (short)0)));
               }
            }
         }
      }

      if (!$foundArmor.element) {
         this$0.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow($chest.field_147002_h.field_75152_c, 53, 0, 0, (ItemStack)$chest.field_147002_h.func_75138_a().get(53), (short)0)));
         Thread.sleep(500L);

         for(index = 0; index < 9; ++index) {
            var10000 = (ItemStack)this$0.getMc().field_71439_g.field_71070_bA.func_75138_a().get(index);
            if (var10000 != null) {
               helmet = var10000;
               var5 = helmet.func_82833_r();
               Intrinsics.checkNotNullExpressionValue(var5, "helmet.displayName");
               if (StringsKt.contains((CharSequence)Utils.stripColor(var5), (CharSequence)this$0.arg, true)) {
                  $foundArmor.element = true;
                  var5 = ((ItemStack)this$0.getMc().field_71439_g.field_71070_bA.func_75138_a().get(index + 36)).func_82833_r();
                  Intrinsics.checkNotNullExpressionValue(var5, "mc.thePlayer.openContain…y[index + 36].displayName");
                  if (StringsKt.contains$default((CharSequence)Utils.stripColor(var5), (CharSequence)"Equipped", false, 2, (Object)null)) {
                     UChat.chat("§7[§aJooonAddons§7]§8 " + this$0.arg + " is already equipped");
                  } else {
                     this$0.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow(this$0.getMc().field_71439_g.field_71070_bA.field_75152_c, index + 36, 0, 0, (ItemStack)this$0.getMc().field_71439_g.field_71070_bA.func_75138_a().get(index + 36), (short)0)));
                  }
               }
            }
         }
      }

      if (!$foundArmor.element) {
         UChat.chat("§7[§aJooonAddons§7]§8 Haven't Found " + this$0.arg + " in your wardrobe!");
      }

      this$0.getMc().field_71439_g.func_71053_j();
   }
}
