package me.jooon.JooonAddons.features.autoExperiments;

import gg.essential.api.utils.Multithreading;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.features.betterlootshare.ESP;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002)*B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010 \u001a\u00020!J\u0010\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020$H\u0007J\u0010\u0010%\u001a\u00020!2\u0006\u0010#\u001a\u00020&H\u0007J\u000e\u0010'\u001a\u00020!2\u0006\u0010(\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR*\u0010\u001b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001d0\u001cj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001d`\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"},
   d2 = {"Lme/jooon/JooonAddons/features/autoExperiments/AutoSequencer;", "", "()V", "clicking", "", "getClicking", "()Z", "setClicking", "(Z)V", "containerIndex", "", "getContainerIndex", "()I", "setContainerIndex", "(I)V", "currentSolver", "Lme/jooon/JooonAddons/features/autoExperiments/AutoSequencer$SolverType;", "getCurrentSolver", "()Lme/jooon/JooonAddons/features/autoExperiments/AutoSequencer$SolverType;", "setCurrentSolver", "(Lme/jooon/JooonAddons/features/autoExperiments/AutoSequencer$SolverType;)V", "stack", "Lnet/minecraft/item/ItemStack;", "getStack", "()Lnet/minecraft/item/ItemStack;", "setStack", "(Lnet/minecraft/item/ItemStack;)V", "ultraSequencerOrder", "Ljava/util/HashMap;", "Lme/jooon/JooonAddons/features/autoExperiments/AutoSequencer$UltrasequencerItem;", "Lkotlin/collections/HashMap;", "ultrasequencerReplayIndex", "clickCorrect", "", "onGuiOpen", "event", "Lnet/minecraftforge/client/event/GuiOpenEvent;", "onTick", "Lnet/minecraftforge/fml/common/gameevent/TickEvent$ClientTickEvent;", "processInventoryContents", "fromTick", "SolverType", "UltrasequencerItem", "JooonAddons"}
)
public final class AutoSequencer {
   @NotNull
   public static final AutoSequencer INSTANCE = new AutoSequencer();
   @NotNull
   private static AutoSequencer.SolverType currentSolver;
   @Nullable
   private static ItemStack stack;
   private static int containerIndex;
   @NotNull
   private static final HashMap<Integer, AutoSequencer.UltrasequencerItem> ultraSequencerOrder;
   private static int ultrasequencerReplayIndex;
   private static boolean clicking;

   private AutoSequencer() {
   }

   @NotNull
   public final AutoSequencer.SolverType getCurrentSolver() {
      return currentSolver;
   }

   public final void setCurrentSolver(@NotNull AutoSequencer.SolverType <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      currentSolver = var1;
   }

   @Nullable
   public final ItemStack getStack() {
      return stack;
   }

   public final void setStack(@Nullable ItemStack <set-?>) {
      stack = var1;
   }

   public final int getContainerIndex() {
      return containerIndex;
   }

   public final void setContainerIndex(int <set-?>) {
      containerIndex = var1;
   }

   @SubscribeEvent
   public final void onGuiOpen(@NotNull GuiOpenEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getAutoExperiments()) {
         if (event.gui instanceof GuiChest) {
            currentSolver = AutoSequencer.SolverType.NONE;
            String openChestName = Utils.INSTANCE.getGuiName(event.gui);
            if (openChestName != null && !StringsKt.contains$default((CharSequence)openChestName, (CharSequence)"Stakes", false, 2, (Object)null)) {
               if (StringsKt.startsWith$default(openChestName, "Chronomatron", false, 2, (Object)null)) {
                  currentSolver = AutoSequencer.SolverType.CHRONOMATRON;
               } else if (StringsKt.startsWith$default(openChestName, "Ultrasequencer", false, 2, (Object)null)) {
                  currentSolver = AutoSequencer.SolverType.ULTRASEQUENCER;
               }
            }

         }
      }
   }

   public final void processInventoryContents(boolean fromTick) {
      if (currentSolver == AutoSequencer.SolverType.CHRONOMATRON || fromTick) {
         if (Minecraft.func_71410_x().field_71462_r instanceof GuiChest) {
            GuiScreen var10000 = Minecraft.func_71410_x().field_71462_r;
            Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type net.minecraft.client.gui.inventory.GuiChest");
            GuiChest chest = (GuiChest)var10000;
            Container var10 = chest.field_147002_h;
            Intrinsics.checkNotNull(var10, "null cannot be cast to non-null type net.minecraft.inventory.ContainerChest");
            ContainerChest container = (ContainerChest)var10;
            IInventory var11 = container.func_85151_d();
            Intrinsics.checkNotNullExpressionValue(var11, "container.lowerChestInventory");
            IInventory lower = var11;
            if (currentSolver == AutoSequencer.SolverType.ULTRASEQUENCER) {
               ItemStack var12 = lower.func_70301_a(lower.func_70302_i_() - 5);
               if (var12 == null) {
                  return;
               }

               ItemStack timerStack = var12;
               if (Intrinsics.areEqual(timerStack.func_77973_b(), Item.func_150898_a(Blocks.field_150426_aN))) {
                  ultrasequencerReplayIndex = 0;
               }

               if (Intrinsics.areEqual(timerStack.func_77973_b(), Items.field_151113_aN)) {
                  this.clickCorrect();
               }

               int index = 0;
               int var7 = lower.func_70302_i_();
               if (index <= var7) {
                  while(true) {
                     ItemStack stack = lower.func_70301_a(index);
                     if (stack != null && Intrinsics.areEqual(stack.func_77973_b(), Items.field_151100_aR)) {
                        if (ultraSequencerOrder.containsKey(stack.field_77994_a - 1)) {
                           Object var13 = ultraSequencerOrder.get(stack.field_77994_a - 1);
                           Intrinsics.checkNotNull(var13);
                           AutoSequencer.UltrasequencerItem ultrasequencerItem = (AutoSequencer.UltrasequencerItem)var13;
                           ultrasequencerItem.setContainerIndex(index);
                           ultrasequencerItem.setStack(stack);
                        } else {
                           ((Map)ultraSequencerOrder).put(stack.field_77994_a - 1, new AutoSequencer.UltrasequencerItem(stack, index));
                        }
                     }

                     if (index == var7) {
                        break;
                     }

                     ++index;
                  }
               }

               if (ultraSequencerOrder.size() > 9 && Config.INSTANCE.getAutoCloseExperiments()) {
                  JooonAddons.Companion.getMc().field_71439_g.func_71053_j();
                  return;
               }
            } else {
               ultraSequencerOrder.clear();
            }
         }

      }
   }

   public final boolean getClicking() {
      return clicking;
   }

   public final void setClicking(boolean <set-?>) {
      clicking = var1;
   }

   public final void clickCorrect() {
      if (!clicking) {
         if (currentSolver != AutoSequencer.SolverType.NONE) {
            clicking = true;
            Multithreading.runAsync(AutoSequencer::clickCorrect$lambda$0);
         }
      }
   }

   @SubscribeEvent
   public final void onTick(@NotNull ClientTickEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getAutoExperiments()) {
         if (!(Minecraft.func_71410_x().field_71462_r instanceof GuiChest)) {
            currentSolver = AutoSequencer.SolverType.NONE;
            clicking = false;
         }

         if (event.phase == Phase.END) {
            this.processInventoryContents(true);
         }
      }
   }

   private static final void clickCorrect$lambda$0() {
      if (Minecraft.func_71410_x().field_71462_r instanceof GuiChest) {
         GuiScreen var10000 = Minecraft.func_71410_x().field_71462_r;
         Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type net.minecraft.client.gui.inventory.GuiChest");
         GuiChest chest = (GuiChest)var10000;
         Container var6 = chest.field_147002_h;
         Intrinsics.checkNotNull(var6, "null cannot be cast to non-null type net.minecraft.inventory.ContainerChest");
         ContainerChest container = (ContainerChest)var6;
         IInventory var7 = container.func_85151_d();
         Intrinsics.checkNotNullExpressionValue(var7, "container.lowerChestInventory");
         IInventory lower = var7;
         AutoSequencer var8 = INSTANCE;
         if (currentSolver == AutoSequencer.SolverType.ULTRASEQUENCER) {
            ItemStack var9 = lower.func_70301_a(lower.func_70302_i_() - 5);
            if (var9 == null) {
               return;
            }

            ItemStack timerStack = var9;
            if (!Intrinsics.areEqual(timerStack.func_77973_b(), Items.field_151113_aN)) {
               return;
            }

            int turn = 0;

            for(int var5 = ultraSequencerOrder.size(); turn < var5; ++turn) {
               ESP.INSTANCE.getLogger().info("" + ultraSequencerOrder.size() + ' ' + turn);
               Object var10001;
               if (ultraSequencerOrder.get(turn) != null) {
                  Logger var10 = ESP.INSTANCE.getLogger();
                  var10001 = ultraSequencerOrder.get(turn);
                  Intrinsics.checkNotNull(var10001);
                  var10.info(((AutoSequencer.UltrasequencerItem)var10001).getContainerIndex());
               } else {
                  ESP.INSTANCE.getLogger().info("Null");
               }

               NetHandlerPlayClient var11 = JooonAddons.Companion.getMc().func_147114_u();
               int var10003 = container.field_75152_c;
               Object var10004 = ultraSequencerOrder.get(turn);
               Intrinsics.checkNotNull(var10004);
               var11.func_147297_a((Packet)(new C0EPacketClickWindow(var10003, ((AutoSequencer.UltrasequencerItem)var10004).getContainerIndex(), 0, 0, (ItemStack)null, (short)0)));
               var10001 = ultraSequencerOrder.get(turn);
               Intrinsics.checkNotNull(var10001);
               container.func_75144_a(((AutoSequencer.UltrasequencerItem)var10001).getContainerIndex(), 0, 0, (EntityPlayer)JooonAddons.Companion.getMc().field_71439_g);
               Thread.sleep((long)Config.INSTANCE.getAutoExperimentsDelay());
            }
         }

         Thread.sleep(800L);
         var8 = INSTANCE;
         clicking = false;
      }

   }

   static {
      currentSolver = AutoSequencer.SolverType.NONE;
      ultraSequencerOrder = new HashMap();
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"},
      d2 = {"Lme/jooon/JooonAddons/features/autoExperiments/AutoSequencer$SolverType;", "", "(Ljava/lang/String;I)V", "NONE", "CHRONOMATRON", "ULTRASEQUENCER", "JooonAddons"}
   )
   public static enum SolverType {
      NONE,
      CHRONOMATRON,
      ULTRASEQUENCER;

      // $FF: synthetic method
      private static final AutoSequencer.SolverType[] $values() {
         AutoSequencer.SolverType[] var0 = new AutoSequencer.SolverType[]{NONE, CHRONOMATRON, ULTRASEQUENCER};
         return var0;
      }
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"},
      d2 = {"Lme/jooon/JooonAddons/features/autoExperiments/AutoSequencer$UltrasequencerItem;", "", "stack", "Lnet/minecraft/item/ItemStack;", "index", "", "(Lnet/minecraft/item/ItemStack;I)V", "containerIndex", "getContainerIndex", "()I", "setContainerIndex", "(I)V", "getStack", "()Lnet/minecraft/item/ItemStack;", "setStack", "(Lnet/minecraft/item/ItemStack;)V", "JooonAddons"}
   )
   public static final class UltrasequencerItem {
      @Nullable
      private ItemStack stack;
      private int containerIndex;

      public UltrasequencerItem(@NotNull ItemStack stack, int index) {
         Intrinsics.checkNotNullParameter(stack, "stack");
         super();
         this.stack = stack;
         this.containerIndex = index;
      }

      @Nullable
      public final ItemStack getStack() {
         return this.stack;
      }

      public final void setStack(@Nullable ItemStack <set-?>) {
         this.stack = var1;
      }

      public final int getContainerIndex() {
         return this.containerIndex;
      }

      public final void setContainerIndex(int <set-?>) {
         this.containerIndex = var1;
      }
   }
}
