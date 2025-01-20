package me.jooon.JooonAddons.features.other;

import gg.essential.api.utils.Multithreading;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.features.Feature;
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
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"},
   d2 = {"Lme/jooon/JooonAddons/features/other/InstaSell;", "Lme/jooon/JooonAddons/features/Feature;", "()V", "onDrawBazzarScreen", "", "event", "Lnet/minecraftforge/client/event/GuiScreenEvent$DrawScreenEvent$Pre;", "JooonAddons"}
)
public final class InstaSell extends Feature {
   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public final void onDrawBazzarScreen(@NotNull Pre event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (event.gui instanceof GuiChest) {
         GuiScreen var10000 = event.gui;
         Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type net.minecraft.client.gui.inventory.GuiChest");
         GuiChest chest = (GuiChest)var10000;
         this.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow(chest.field_147002_h.field_75152_c, 47, 0, 0, (ItemStack)chest.field_147002_h.func_75138_a().get(47), (short)0)));
         MinecraftForge.EVENT_BUS.unregister(this);
         Multithreading.runAsync(InstaSell::onDrawBazzarScreen$lambda$0);
      }
   }

   private static final void onDrawBazzarScreen$lambda$0(InstaSell this$0) {
      Intrinsics.checkNotNullParameter(this$0, "this$0");
      Thread.sleep(500L);
      this$0.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow(this$0.getMc().field_71439_g.field_71070_bA.field_75152_c, 11, 0, 0, (ItemStack)this$0.getMc().field_71439_g.field_71070_bA.func_75138_a().get(11), (short)0)));
      Thread.sleep(800L);
      this$0.getMc().field_71439_g.func_71053_j();
   }
}
