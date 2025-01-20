package me.jooon.JooonAddons.features.autoExperiments;

import gg.essential.api.utils.Multithreading;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.events.impl.PacketEvent;
import me.jooon.JooonAddons.features.Feature;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020!H\u0007R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012¨\u0006\""},
   d2 = {"Lme/jooon/JooonAddons/features/autoExperiments/AutoChromanotron;", "Lme/jooon/JooonAddons/features/Feature;", "()V", "chronomatronIndexOrder", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "clicking", "", "getClicking", "()Z", "setClicking", "(Z)V", "lastItemStack", "Lnet/minecraft/item/ItemStack;", "getLastItemStack", "()Lnet/minecraft/item/ItemStack;", "setLastItemStack", "(Lnet/minecraft/item/ItemStack;)V", "lastSlot", "getLastSlot", "()I", "setLastSlot", "(I)V", "timerStack", "getTimerStack", "setTimerStack", "clickCorrect", "", "onPacket", "event", "Lme/jooon/JooonAddons/events/impl/PacketEvent$ReceiveEvent;", "onTick", "Lnet/minecraftforge/fml/common/gameevent/TickEvent$ClientTickEvent;", "JooonAddons"}
)
public final class AutoChromanotron extends Feature {
   @NotNull
   public static final AutoChromanotron INSTANCE = new AutoChromanotron();
   @Nullable
   private static ItemStack timerStack;
   @Nullable
   private static ItemStack lastItemStack;
   private static int lastSlot = -1;
   @NotNull
   private static final ArrayList<Integer> chronomatronIndexOrder = new ArrayList();
   private static boolean clicking;

   private AutoChromanotron() {
   }

   @Nullable
   public final ItemStack getTimerStack() {
      return timerStack;
   }

   public final void setTimerStack(@Nullable ItemStack <set-?>) {
      timerStack = var1;
   }

   @Nullable
   public final ItemStack getLastItemStack() {
      return lastItemStack;
   }

   public final void setLastItemStack(@Nullable ItemStack <set-?>) {
      lastItemStack = var1;
   }

   public final int getLastSlot() {
      return lastSlot;
   }

   public final void setLastSlot(int <set-?>) {
      lastSlot = var1;
   }

   public final boolean getClicking() {
      return clicking;
   }

   public final void setClicking(boolean <set-?>) {
      clicking = var1;
   }

   @SubscribeEvent
   public final void onTick(@NotNull ClientTickEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getAutoExperiments()) {
         if (AutoSequencer.INSTANCE.getCurrentSolver() == AutoSequencer.SolverType.CHRONOMATRON) {
            if (!(Minecraft.func_71410_x().field_71462_r instanceof GuiChest)) {
               chronomatronIndexOrder.clear();
               lastSlot = -1;
               lastItemStack = null;
               clicking = false;
            } else {
               GuiScreen var10000 = Minecraft.func_71410_x().field_71462_r;
               Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type net.minecraft.client.gui.inventory.GuiChest");
               GuiChest chest = (GuiChest)var10000;
               Container var7 = chest.field_147002_h;
               Intrinsics.checkNotNull(var7, "null cannot be cast to non-null type net.minecraft.inventory.ContainerChest");
               ContainerChest container = (ContainerChest)var7;
               IInventory var8 = container.func_85151_d();
               Intrinsics.checkNotNullExpressionValue(var8, "container.lowerChestInventory");
               IInventory lower = var8;
               ItemStack var9 = lower.func_70301_a(lower.func_70302_i_() - 5);
               if (var9 != null) {
                  timerStack = var9;

                  try {
                     if (Config.INSTANCE.getAutoCloseExperiments() && lower.func_70301_a(4).field_77994_a >= 13) {
                        this.getMc().field_71439_g.func_71053_j();
                        return;
                     }
                  } catch (Exception var6) {
                     var6.printStackTrace();
                  }

                  if (!clicking) {
                     var9 = timerStack;
                     Intrinsics.checkNotNull(var9);
                     if (Intrinsics.areEqual(var9.func_77973_b(), Items.field_151113_aN)) {
                        clicking = true;
                        this.clickCorrect();
                        return;
                     }
                  }

               }
            }
         }
      }
   }

   public final void clickCorrect() {
      if (Config.INSTANCE.getAutoExperiments()) {
         if (AutoSequencer.INSTANCE.getCurrentSolver() == AutoSequencer.SolverType.CHRONOMATRON) {
            GuiScreen var10000 = Minecraft.func_71410_x().field_71462_r;
            Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type net.minecraft.client.gui.inventory.GuiChest");
            GuiChest chest = (GuiChest)var10000;
            Container var3 = chest.field_147002_h;
            Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type net.minecraft.inventory.ContainerChest");
            ContainerChest container = (ContainerChest)var3;
            Multithreading.runAsync(AutoChromanotron::clickCorrect$lambda$0);
         }
      }
   }

   @SubscribeEvent
   public final void onPacket(@NotNull PacketEvent.ReceiveEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (event.getPacket() instanceof S2FPacketSetSlot) {
         if (Config.INSTANCE.getAutoExperiments()) {
            if (AutoSequencer.INSTANCE.getCurrentSolver() == AutoSequencer.SolverType.CHRONOMATRON) {
               ItemStack var10000 = ((S2FPacketSetSlot)event.getPacket()).func_149174_e();
               if (var10000 != null) {
                  ItemStack itemStack = var10000;
                  if (timerStack != null) {
                     if (itemStack.func_77948_v() && itemStack.func_82833_r() != null) {
                        var10000 = timerStack;
                        Intrinsics.checkNotNull(var10000);
                        if (Intrinsics.areEqual(var10000.func_77973_b(), Item.func_150898_a(Blocks.field_150426_aN))) {
                           if (lastItemStack != null) {
                              String var3 = itemStack.func_82833_r();
                              ItemStack var10001 = lastItemStack;
                              Intrinsics.checkNotNull(var10001);
                              if (Intrinsics.areEqual(var3, var10001.func_82833_r()) && lastSlot != ((S2FPacketSetSlot)event.getPacket()).func_149173_d()) {
                                 return;
                              }
                           }

                           String var4 = itemStack.func_82833_r();
                           Intrinsics.checkNotNullExpressionValue(var4, "itemStack.displayName");
                           this.printdev(var4);
                           chronomatronIndexOrder.add(((S2FPacketSetSlot)event.getPacket()).func_149173_d());
                           lastSlot = ((S2FPacketSetSlot)event.getPacket()).func_149173_d();
                           lastItemStack = itemStack;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private static final void clickCorrect$lambda$0(ContainerChest $container) {
      Intrinsics.checkNotNullParameter($container, "$container");
      Iterator var1 = chronomatronIndexOrder.iterator();

      while(var1.hasNext()) {
         Integer index = (Integer)var1.next();
         INSTANCE.printdev("Clicking " + index);
         NetHandlerPlayClient var10000 = INSTANCE.getMc().func_147114_u();
         int var10003 = $container.field_75152_c;
         Intrinsics.checkNotNullExpressionValue(index, "index");
         var10000.func_147297_a((Packet)(new C0EPacketClickWindow(var10003, index, 0, 0, $container.func_85151_d().func_70301_a(index), (short)0)));
         INSTANCE.printdev("Sleeping for: " + (long)Config.INSTANCE.getAutoExperimentsDelay());
         Thread.sleep((long)Config.INSTANCE.getAutoExperimentsDelay());
      }

      chronomatronIndexOrder.clear();
      AutoChromanotron var3 = INSTANCE;
      clicking = false;
   }
}
