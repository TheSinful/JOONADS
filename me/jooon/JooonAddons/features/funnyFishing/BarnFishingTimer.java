package me.jooon.JooonAddons.features.funnyFishing;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.gui.GuiElement;
import me.jooon.JooonAddons.render.DisplayNotification;
import me.jooon.JooonAddons.render.font.FontUtils;
import me.jooon.JooonAddons.utils.core.FloatPair;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0017"},
   d2 = {"Lme/jooon/JooonAddons/features/funnyFishing/BarnFishingTimer;", "", "()V", "notificationDisplayed", "", "getNotificationDisplayed", "()Z", "setNotificationDisplayed", "(Z)V", "timerRunning", "getTimerRunning", "setTimerRunning", "timerStartTime", "", "getTimerStartTime", "()J", "setTimerStartTime", "(J)V", "onRodCast", "", "event", "Lnet/minecraftforge/event/entity/player/PlayerInteractEvent;", "BarnFishingTimerGUI", "JooonAddons"}
)
public final class BarnFishingTimer {
   @NotNull
   public static final BarnFishingTimer INSTANCE = new BarnFishingTimer();
   private static boolean timerRunning;
   private static long timerStartTime;
   private static boolean notificationDisplayed;

   private BarnFishingTimer() {
   }

   public final boolean getTimerRunning() {
      return timerRunning;
   }

   public final void setTimerRunning(boolean <set-?>) {
      timerRunning = var1;
   }

   public final long getTimerStartTime() {
      return timerStartTime;
   }

   public final void setTimerStartTime(long <set-?>) {
      timerStartTime = var1;
   }

   public final boolean getNotificationDisplayed() {
      return notificationDisplayed;
   }

   public final void setNotificationDisplayed(boolean <set-?>) {
      notificationDisplayed = var1;
   }

   @SubscribeEvent
   public final void onRodCast(@NotNull PlayerInteractEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getBarnFishingTimer()) {
         if (event.entityPlayer.func_70694_bm() != null) {
            if (Intrinsics.areEqual(event.entityPlayer.func_70694_bm().func_77973_b(), Items.field_151112_aM) && !timerRunning) {
               timerRunning = true;
               timerStartTime = System.currentTimeMillis();
            } else {
               if (!Intrinsics.areEqual(event.entityPlayer.func_70694_bm().func_77973_b(), Items.field_151112_aM) && timerRunning) {
                  JooonAddons.Companion.getMc().field_71439_g.func_145747_a((IChatComponent)(new ChatComponentText("§7[§aJooonAddons§7]§8 Detected right click on not a rod, disabling timer")));
                  timerRunning = false;
                  timerStartTime = 0L;
                  notificationDisplayed = false;
               }

            }
         }
      }
   }

   static {
      new BarnFishingTimer.BarnFishingTimerGUI();
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\b¨\u0006\u0012"},
      d2 = {"Lme/jooon/JooonAddons/features/funnyFishing/BarnFishingTimer$BarnFishingTimerGUI;", "Lme/jooon/JooonAddons/gui/GuiElement;", "()V", "fishingRod", "Lnet/minecraft/item/ItemStack;", "height", "", "getHeight", "()I", "toggled", "", "getToggled", "()Z", "width", "getWidth", "demoRender", "", "render", "JooonAddons"}
   )
   public static final class BarnFishingTimerGUI extends GuiElement {
      @NotNull
      private final ItemStack fishingRod;

      public BarnFishingTimerGUI() {
         super("Barn Fishing Timer", new FloatPair(10, 10));
         this.fishingRod = new ItemStack((Item)Items.field_151112_aM, 1, 1);
         JooonAddons.Companion.getGuiManager().registerElement((GuiElement)this);
      }

      public void render() {
         NBTTagCompound compound = this.fishingRod.func_77978_p();
         if (compound == null) {
            this.fishingRod.func_77982_d(new NBTTagCompound());
         }

         this.fishingRod.func_77978_p().func_74778_a("Name", "Hellfire Rod");
         if (this.getToggled()) {
            if (JooonAddons.Companion.getMc().field_71439_g.func_70694_bm() == null) {
               return;
            }

            if (Intrinsics.areEqual(JooonAddons.Companion.getMc().field_71439_g.func_70694_bm().func_77973_b(), Items.field_151112_aM) || BarnFishingTimer.INSTANCE.getTimerRunning()) {
               String text = BarnFishingTimer.INSTANCE.getTimerRunning() ? FontUtils.INSTANCE.getTimeBetween((double)(BarnFishingTimer.INSTANCE.getTimerStartTime() / (long)1000), (double)(System.currentTimeMillis() / (long)1000)) : "0m0s";
               FontUtils.INSTANCE.smartItemPlacement((GuiElement)this, this.fishingRod);
               GuiElement.Companion.getFr().func_175065_a(text, FontUtils.INSTANCE.smartFontPlacement(16.0F, text, (GuiElement)this), 6.0F, 16777215, true);
               if ((System.currentTimeMillis() - BarnFishingTimer.INSTANCE.getTimerStartTime()) / (long)1000 >= (long)Config.INSTANCE.getTimestampOfBarnFishingNotification() && !BarnFishingTimer.INSTANCE.getNotificationDisplayed() && BarnFishingTimer.INSTANCE.getTimerRunning()) {
                  BarnFishingTimer.INSTANCE.setNotificationDisplayed(true);
                  DisplayNotification.INSTANCE.displayNotification("§c" + Config.INSTANCE.getBarnFishingTimerText(), 3000L, true);
               }
            }
         }

      }

      public void demoRender() {
         FontUtils.INSTANCE.smartItemPlacement((GuiElement)this, this.fishingRod);
         GuiElement.Companion.getFr().func_175065_a("3m21s", FontUtils.INSTANCE.smartFontPlacement(16.0F, "3m21s", (GuiElement)this), 6.0F, 16777215, true);
      }

      public boolean getToggled() {
         return Config.INSTANCE.getBarnFishingTimer();
      }

      public int getHeight() {
         return GuiElement.Companion.getFr().field_78288_b + 3;
      }

      public int getWidth() {
         return 20 + GuiElement.Companion.getFr().func_78256_a("3m21s");
      }
   }
}
