package me.jooon.JooonAddons.features.other;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.BackgroundDrawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0013H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"},
   d2 = {"Lme/jooon/JooonAddons/features/other/AutoMelody;", "", "()V", "lastInv", "", "melodyOpen", "", "getMelodyOpen", "()Z", "setMelodyOpen", "(Z)V", "notes", "", "Lme/jooon/JooonAddons/features/other/AutoMelody$Note;", "onGuiOpen", "", "event", "Lnet/minecraftforge/client/event/GuiOpenEvent;", "onRender", "Lnet/minecraftforge/client/event/GuiScreenEvent$BackgroundDrawnEvent;", "Note", "JooonAddons"}
)
public final class AutoMelody {
   @NotNull
   public static final AutoMelody INSTANCE = new AutoMelody();
   private static int lastInv;
   private static boolean melodyOpen;
   @NotNull
   private static final List<AutoMelody.Note> notes;

   private AutoMelody() {
   }

   public final boolean getMelodyOpen() {
      return melodyOpen;
   }

   public final void setMelodyOpen(boolean <set-?>) {
      melodyOpen = var1;
   }

   @SubscribeEvent
   public final void onGuiOpen(@NotNull GuiOpenEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getAutoMelody()) {
         if (event.gui instanceof GuiChest) {
            melodyOpen = false;
            String openChestName = Utils.INSTANCE.getGuiName(event.gui);
            if (openChestName != null && StringsKt.startsWith$default(openChestName, "Harp", false, 2, (Object)null)) {
               melodyOpen = true;
            }

         }
      }
   }

   @SubscribeEvent
   public final void onRender(@NotNull BackgroundDrawnEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getAutoMelody()) {
         if (melodyOpen) {
            if (!(Minecraft.func_71410_x().field_71462_r instanceof GuiChest)) {
               melodyOpen = false;
            } else {
               Container var10000 = JooonAddons.Companion.getMc().field_71439_g.field_71070_bA;
               if (var10000 != null) {
                  Container container = var10000;
                  int newHash = CollectionsKt.joinToString$default((Iterable)container.field_75151_b.subList(0, 36), (CharSequence)"", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null.INSTANCE, 30, (Object)null).hashCode();
                  if (lastInv != newHash) {
                     lastInv = newHash;

                     for(int ii = 0; ii < 7; ++ii) {
                        Slot slot = (Slot)container.field_75151_b.get(37 + ii);
                        ItemStack var7 = slot.func_75211_c();
                        Item var6 = var7 != null ? var7.func_77973_b() : null;
                        if (((var6 instanceof ItemBlock ? (ItemBlock)var6 : null) != null ? (var6 instanceof ItemBlock ? (ItemBlock)var6 : null).field_150939_a : null) == Blocks.field_150371_ca) {
                           JooonAddons.Companion.getMc().field_71442_b.func_78753_a(container.field_75152_c, slot.field_75222_d, 3, 3, (EntityPlayer)JooonAddons.Companion.getMc().field_71439_g);
                           break;
                        }
                     }

                  }
               }
            }
         }
      }
   }

   static {
      AutoMelody.Note[] var0 = new AutoMelody.Note[]{new AutoMelody.Note(37), new AutoMelody.Note(38), new AutoMelody.Note(39), new AutoMelody.Note(40), new AutoMelody.Note(41), new AutoMelody.Note(42), new AutoMelody.Note(43)};
      notes = CollectionsKt.listOf(var0);
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\r\"\u0004\b\u0010\u0010\u0004¨\u0006\u0011"},
      d2 = {"Lme/jooon/JooonAddons/features/other/AutoMelody$Note;", "", "slot", "", "(I)V", "clicked", "", "getClicked", "()Z", "setClicked", "(Z)V", "delay", "getDelay", "()I", "setDelay", "getSlot", "setSlot", "JooonAddons"}
   )
   public static final class Note {
      private int slot;
      private boolean clicked;
      private int delay;

      public Note(int slot) {
         this.slot = slot;
      }

      public final int getSlot() {
         return this.slot;
      }

      public final void setSlot(int <set-?>) {
         this.slot = var1;
      }

      public final boolean getClicked() {
         return this.clicked;
      }

      public final void setClicked(boolean <set-?>) {
         this.clicked = var1;
      }

      public final int getDelay() {
         return this.delay;
      }

      public final void setDelay(int <set-?>) {
         this.delay = var1;
      }
   }
}
