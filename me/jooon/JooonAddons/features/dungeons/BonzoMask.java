package me.jooon.JooonAddons.features.dungeons;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.events.impl.PacketEvent;
import me.jooon.JooonAddons.features.betterlootshare.ESP;
import me.jooon.JooonAddons.gui.GuiElement;
import me.jooon.JooonAddons.render.font.FontUtils;
import me.jooon.JooonAddons.utils.Utils;
import me.jooon.JooonAddons.utils.core.FloatPair;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001,B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0007J\u0010\u0010%\u001a\u00020\"2\u0006\u0010#\u001a\u00020&H\u0007J\u0012\u0010'\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010(H\u0007J\u000e\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020\u0017J\u0006\u0010+\u001a\u00020\"R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0013\"\u0004\b \u0010\u0015¨\u0006-"},
   d2 = {"Lme/jooon/JooonAddons/features/dungeons/BonzoMask;", "", "()V", "bonzoMaskItem", "Lnet/minecraft/item/ItemStack;", "getBonzoMaskItem", "()Lnet/minecraft/item/ItemStack;", "setBonzoMaskItem", "(Lnet/minecraft/item/ItemStack;)V", "displayedMessage", "", "hasBonzoInInv", "getHasBonzoInInv", "()Z", "setHasBonzoInInv", "(Z)V", "lastBonzoEquipTime", "", "getLastBonzoEquipTime", "()J", "setLastBonzoEquipTime", "(J)V", "mainHelmetSlotIndex", "", "nextBonzoUse", "", "getNextBonzoUse", "()D", "setNextBonzoUse", "(D)V", "timeWorldJoined", "getTimeWorldJoined", "setTimeWorldJoined", "onChat", "", "event", "Lnet/minecraftforge/client/event/ClientChatReceivedEvent;", "onChatPacket", "Lme/jooon/JooonAddons/events/impl/PacketEvent$ReceiveEvent;", "onWorldChange", "Lnet/minecraftforge/event/world/WorldEvent$Load;", "sendInventoryClick", "slot", "swapToBonzo", "BonzoMaskTimerGuiElement", "JooonAddons"}
)
public final class BonzoMask {
   @NotNull
   public static final BonzoMask INSTANCE = new BonzoMask();
   private static boolean hasBonzoInInv;
   private static double nextBonzoUse;
   private static long lastBonzoEquipTime;
   @Nullable
   private static ItemStack bonzoMaskItem;
   private static long timeWorldJoined;
   private static boolean displayedMessage;
   private static int mainHelmetSlotIndex;

   private BonzoMask() {
   }

   public final boolean getHasBonzoInInv() {
      return hasBonzoInInv;
   }

   public final void setHasBonzoInInv(boolean <set-?>) {
      hasBonzoInInv = var1;
   }

   public final double getNextBonzoUse() {
      return nextBonzoUse;
   }

   public final void setNextBonzoUse(double <set-?>) {
      nextBonzoUse = var1;
   }

   public final long getLastBonzoEquipTime() {
      return lastBonzoEquipTime;
   }

   public final void setLastBonzoEquipTime(long <set-?>) {
      lastBonzoEquipTime = var1;
   }

   @Nullable
   public final ItemStack getBonzoMaskItem() {
      return bonzoMaskItem;
   }

   public final void setBonzoMaskItem(@Nullable ItemStack <set-?>) {
      bonzoMaskItem = var1;
   }

   public final long getTimeWorldJoined() {
      return timeWorldJoined;
   }

   public final void setTimeWorldJoined(long <set-?>) {
      timeWorldJoined = var1;
   }

   @SubscribeEvent
   public final void onWorldChange(@Nullable Load event) {
      nextBonzoUse = 0.0D;
      timeWorldJoined = System.currentTimeMillis() / (long)1000;
      displayedMessage = false;
   }

   @SubscribeEvent
   public final void onChat(@NotNull ClientChatReceivedEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      String message = event.message.func_150260_c();
      if (Utils.INSTANCE.getInDungeon()) {
         if (bonzoMaskItem != null) {
            Intrinsics.checkNotNullExpressionValue(message, "message");
            if (StringsKt.contains$default((CharSequence)message, (CharSequence)"Bonzo's Mask", false, 2, (Object)null) && StringsKt.contains$default((CharSequence)message, (CharSequence)"saved your life!", false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)message, (CharSequence)":", false, 2, (Object)null)) {
               double usedTime = (double)(System.currentTimeMillis() / (long)1000);
               int cooldownSeconds = 0;
               Utils var10000 = Utils.INSTANCE;
               ItemStack var10001 = bonzoMaskItem;
               Intrinsics.checkNotNull(var10001);
               Iterator var6 = var10000.getItemLore(var10001).iterator();

               while(var6.hasNext()) {
                  String line = (String)var6.next();
                  if (line != null && StringsKt.startsWith$default(Utils.stripColor(line), "Cooldown: ", false, 2, (Object)null)) {
                     CharSequence var8 = (CharSequence)Utils.stripColor(line);
                     Regex var9 = new Regex("[^\\d]");
                     String var10 = "";
                     cooldownSeconds = Integer.parseInt(var9.replace(var8, var10));
                  }
               }

               ESP.INSTANCE.getLogger().info("Got Bonzo Mask Cooldown: " + cooldownSeconds);
               if (cooldownSeconds > 0) {
                  nextBonzoUse = usedTime + (double)cooldownSeconds;
               }

               if (mainHelmetSlotIndex != -1) {
                  Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§8 AutoMask switched back to " + ((ItemStack)JooonAddons.Companion.getMc().field_71439_g.field_71069_bz.func_75138_a().get(mainHelmetSlotIndex)).func_82833_r());
                  JooonAddons.Companion.getMc().func_147108_a((GuiScreen)(new GuiInventory((EntityPlayer)JooonAddons.Companion.getMc().field_71439_g)));
                  this.sendInventoryClick(mainHelmetSlotIndex);
                  this.sendInventoryClick(5);
                  this.sendInventoryClick(mainHelmetSlotIndex);
                  JooonAddons.Companion.getMc().field_71439_g.func_71053_j();
                  mainHelmetSlotIndex = -1;
                  JooonAddons.Companion.getMc().field_71439_g.func_85030_a("random.orb", 1.0F, 0.5F);
               }

            }
         }
      }
   }

   @SubscribeEvent(
      priority = EventPriority.LOW,
      receiveCanceled = true
   )
   public final void onChatPacket(@NotNull PacketEvent.ReceiveEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Utils.INSTANCE.getInDungeon()) {
         if (Config.INSTANCE.getAutoBonzoMask()) {
            String var10000;
            if (JooonAddons.Companion.getMc().field_71439_g.field_71071_by.func_70301_a(0) != null) {
               var10000 = JooonAddons.Companion.getMc().field_71439_g.field_71071_by.func_70301_a(0).func_82833_r();
               Intrinsics.checkNotNullExpressionValue(var10000, "mc.thePlayer.inventory.g…tackInSlot(0).displayName");
               if (StringsKt.contains$default((CharSequence)Utils.stripColor(var10000), (CharSequence)"Haunt", false, 2, (Object)null)) {
                  return;
               }
            }

            if (System.currentTimeMillis() - lastBonzoEquipTime >= 4000L) {
               if (nextBonzoUse - (double)(System.currentTimeMillis() / (long)1000) < 0.0D) {
                  try {
                     if (event.getPacket() instanceof S02PacketChat) {
                        if (System.currentTimeMillis() / (long)1000 - timeWorldJoined < 5L) {
                           return;
                        }

                        var10000 = ((S02PacketChat)event.getPacket()).func_148915_c().func_150260_c();
                        Intrinsics.checkNotNullExpressionValue(var10000, "event.packet.chatComponent.unformattedText");
                        if (StringsKt.contains$default((CharSequence)var10000, (CharSequence)"❤", false, 2, (Object)null)) {
                           var10000 = ((S02PacketChat)event.getPacket()).func_148915_c().func_150260_c();
                           Intrinsics.checkNotNullExpressionValue(var10000, "event.packet.chatComponent.unformattedText");
                           if (StringsKt.contains$default((CharSequence)var10000, (CharSequence)"❈", false, 2, (Object)null)) {
                              var10000 = ((S02PacketChat)event.getPacket()).func_148915_c().func_150260_c();
                              Intrinsics.checkNotNullExpressionValue(var10000, "event.packet.chatComponent.unformattedText");
                              if (StringsKt.contains$default((CharSequence)var10000, (CharSequence)"✎", false, 2, (Object)null)) {
                                 var10000 = ((S02PacketChat)event.getPacket()).func_148915_c().func_150260_c();
                                 Intrinsics.checkNotNullExpressionValue(var10000, "event.packet.chatComponent.unformattedText");
                                 if (!StringsKt.contains$default((CharSequence)var10000, (CharSequence)":", false, 2, (Object)null)) {
                                    String unformatted = ((S02PacketChat)event.getPacket()).func_148915_c().func_150260_c();
                                    Intrinsics.checkNotNullExpressionValue(unformatted, "unformatted");
                                    CharSequence var12 = (CharSequence)unformatted;
                                    String[] var5 = new String[]{"/"};
                                    double health = Double.parseDouble(Utils.stripColor(StringsKt.replace$default((String)StringsKt.split$default(var12, var5, false, 0, 6, (Object)null).get(0), ",", "", false, 4, (Object)null)));
                                    var12 = (CharSequence)unformatted;
                                    String[] var7 = new String[]{" "};
                                    var12 = (CharSequence)StringsKt.split$default(var12, var7, false, 0, 6, (Object)null).get(0);
                                    var7 = new String[]{"/"};
                                    var12 = (CharSequence)StringsKt.replace$default(StringsKt.replace$default((String)StringsKt.split$default(var12, var7, false, 0, 6, (Object)null).get(1), "❤", "", false, 4, (Object)null), ",", "", false, 4, (Object)null);
                                    var7 = new String[]{"+"};
                                    double maxHealth = Double.parseDouble(Utils.stripColor((String)StringsKt.split$default(var12, var7, false, 0, 6, (Object)null).get(0)));
                                    int healthPercent = MathKt.roundToInt(health / maxHealth * (double)100);
                                    ItemStack helmetEquipped = (ItemStack)JooonAddons.Companion.getMc().field_71439_g.field_71069_bz.func_75138_a().get(5);
                                    if ((float)healthPercent < Config.INSTANCE.getAutoBonzoMaskHealth() * (float)100) {
                                       if (helmetEquipped == null) {
                                          this.swapToBonzo();
                                          lastBonzoEquipTime = System.currentTimeMillis();
                                          Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§8 AutoMask Automatically Equipped: " + ((ItemStack)JooonAddons.Companion.getMc().field_71439_g.field_71069_bz.func_75138_a().get(5)).func_82833_r());
                                       } else {
                                          var10000 = helmetEquipped.func_82833_r();
                                          Intrinsics.checkNotNullExpressionValue(var10000, "helmetEquipped.displayName");
                                          if (!StringsKt.contains$default((CharSequence)var10000, (CharSequence)"Bonzo's Mask", false, 2, (Object)null)) {
                                             this.swapToBonzo();
                                          }
                                       }
                                    }

                                    if ((float)healthPercent > Config.INSTANCE.getAutoBonzoMaskHealth() * (float)100 && mainHelmetSlotIndex != -1) {
                                       var10000 = helmetEquipped.func_82833_r();
                                       Intrinsics.checkNotNullExpressionValue(var10000, "helmetEquipped.displayName");
                                       if (StringsKt.contains$default((CharSequence)var10000, (CharSequence)"Bonzo's Mask", false, 2, (Object)null)) {
                                          this.sendInventoryClick(mainHelmetSlotIndex);
                                          this.sendInventoryClick(5);
                                          this.sendInventoryClick(mainHelmetSlotIndex);
                                          JooonAddons.Companion.getMc().field_71439_g.func_71053_j();
                                          mainHelmetSlotIndex = -1;
                                          Utils var13 = Utils.INSTANCE;
                                          StringBuilder var10001 = (new StringBuilder()).append("§7[§aJooonAddons§7]§8 AutoMask switched back to ");
                                          ItemStack var10002 = (ItemStack)JooonAddons.Companion.getMc().field_71439_g.field_71069_bz.func_75138_a().get(mainHelmetSlotIndex);
                                          var13.addMessage(var10001.append(var10002 != null ? var10002.func_82833_r() : null).toString());
                                          JooonAddons.Companion.getMc().field_71439_g.func_85030_a("random.orb", 1.0F, 0.5F);
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  } catch (ArrayIndexOutOfBoundsException var9) {
                     ESP.INSTANCE.getLogger().error("");
                  }
               }

            }
         }
      }
   }

   public final void swapToBonzo() {
      int bonzoSlotIndex = -1;

      for(int slot = 9; slot < 44; ++slot) {
         ItemStack var10000 = JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75139_a(slot).func_75211_c();
         if (var10000 != null) {
            ItemStack itemStack = var10000;
            String var4 = itemStack.func_82833_r();
            Intrinsics.checkNotNullExpressionValue(var4, "itemStack.displayName");
            if (StringsKt.contains$default((CharSequence)var4, (CharSequence)"Bonzo's Mask", false, 2, (Object)null)) {
               bonzoSlotIndex = slot;
               break;
            }
         }
      }

      if (bonzoSlotIndex == -1) {
         if (!displayedMessage) {
            Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§8 AutoMask Couldn't find your Bonzo's Mask! Ensure it's not a Ghost item.");
            displayedMessage = true;
         }

      } else {
         JooonAddons.Companion.getMc().func_147108_a((GuiScreen)(new GuiInventory((EntityPlayer)JooonAddons.Companion.getMc().field_71439_g)));
         this.sendInventoryClick(bonzoSlotIndex);
         this.sendInventoryClick(5);
         this.sendInventoryClick(bonzoSlotIndex);
         JooonAddons.Companion.getMc().field_71439_g.func_71053_j();
         mainHelmetSlotIndex = bonzoSlotIndex;
         JooonAddons.Companion.getMc().field_71439_g.func_85030_a("random.orb", 1.0F, 0.5F);
         Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§8 AutoMask Automatically Equipped: " + ((ItemStack)JooonAddons.Companion.getMc().field_71439_g.field_71069_bz.func_75138_a().get(5)).func_82833_r());
      }
   }

   public final void sendInventoryClick(int slot) {
      ItemStack itemStack = (ItemStack)JooonAddons.Companion.getMc().field_71439_g.field_71069_bz.func_75138_a().get(slot);
      JooonAddons.Companion.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow((new GuiInventory((EntityPlayer)JooonAddons.Companion.getMc().field_71439_g)).field_147002_h.field_75152_c, slot, 0, 0, itemStack, (short)0)));
      JooonAddons.Companion.getMc().field_71439_g.field_71070_bA.func_75144_a(slot, 0, 0, (EntityPlayer)JooonAddons.Companion.getMc().field_71439_g);
   }

   static {
      new BonzoMask.BonzoMaskTimerGuiElement();
      timeWorldJoined = -1L;
      mainHelmetSlotIndex = -1;
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006¨\u0006\u0010"},
      d2 = {"Lme/jooon/JooonAddons/features/dungeons/BonzoMask$BonzoMaskTimerGuiElement;", "Lme/jooon/JooonAddons/gui/GuiElement;", "()V", "height", "", "getHeight", "()I", "toggled", "", "getToggled", "()Z", "width", "getWidth", "demoRender", "", "render", "JooonAddons"}
   )
   public static final class BonzoMaskTimerGuiElement extends GuiElement {
      public BonzoMaskTimerGuiElement() {
         super("Bonzo Mask Timer", new FloatPair(10, 10));
         JooonAddons.Companion.getGuiManager().registerElement((GuiElement)this);
      }

      public void render() {
         if (Config.INSTANCE.getBonzoMaskTimer() && Utils.INSTANCE.getInDungeon()) {
            int i = 0;

            for(int var2 = JooonAddons.Companion.getMc().field_71439_g.field_71071_by.func_70302_i_(); i < var2; ++i) {
               ItemStack var10000 = JooonAddons.Companion.getMc().field_71439_g.field_71071_by.func_70301_a(i);
               if (var10000 != null) {
                  ItemStack itemStack = var10000;
                  String var7 = itemStack.func_82833_r();
                  Intrinsics.checkNotNullExpressionValue(var7, "itemStack.displayName");
                  if (StringsKt.contains$default((CharSequence)Utils.stripColor(var7), (CharSequence)"Bonzo's Mask", false, 2, (Object)null)) {
                     BonzoMask.INSTANCE.setHasBonzoInInv(true);
                     BonzoMask.INSTANCE.setBonzoMaskItem(itemStack);
                     break;
                  }

                  BonzoMask.INSTANCE.setHasBonzoInInv(false);
                  BonzoMask.INSTANCE.setBonzoMaskItem((ItemStack)null);
               }
            }

            if (BonzoMask.INSTANCE.getHasBonzoInInv()) {
               if (BonzoMask.INSTANCE.getBonzoMaskItem() != null) {
                  double timeNow = (double)(System.currentTimeMillis() / (long)1000);
                  String text = BonzoMask.INSTANCE.getNextBonzoUse() - timeNow < 0.0D ? "§aReady" : FontUtils.INSTANCE.getTimeBetween(timeNow, BonzoMask.INSTANCE.getNextBonzoUse());
                  float textX = FontUtils.INSTANCE.smartFontPlacement(16.0F, Utils.stripColor(text), (GuiElement)this);
                  FontUtils.INSTANCE.smartTexturePlacement(0.0F, (GuiElement)this, "JooonAddons/bonzoBasic.png", "JooonAddons/bonzoMirror.png");
                  GuiElement.Companion.getFr().func_175065_a(text, textX, 5.0F, 16777215, true);
               }
            }
         }
      }

      public void demoRender() {
         float textX = FontUtils.INSTANCE.smartFontPlacement(16.0F, "READY", (GuiElement)this);
         FontUtils.INSTANCE.smartTexturePlacement(0.0F, (GuiElement)this, "JooonAddons/bonzoBasic.png", "JooonAddons/bonzoMirror.png");
         GuiElement.Companion.getFr().func_175065_a("§aREADY", textX, 5.0F, 16777215, true);
      }

      public boolean getToggled() {
         return Config.INSTANCE.getBonzoMaskTimer();
      }

      public int getHeight() {
         return GuiElement.Companion.getFr().field_78288_b;
      }

      public int getWidth() {
         return 20 + GuiElement.Companion.getFr().func_78256_a("READY");
      }
   }
}
