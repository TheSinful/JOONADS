package me.jooon.JooonAddons.utils;

import gg.essential.api.utils.Multithreading;
import gg.essential.universal.wrappers.message.UTextComponent;
import gg.essential.universal.wrappers.message.UTextComponent.Companion;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.events.impl.MainReceivePacketEvent;
import me.jooon.JooonAddons.events.impl.PacketEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\"\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u0013J\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eJ\u0014\u0010\u001f\u001a\u00020\u00162\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00160!J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0018J\u000e\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020\u0018J\u000e\u0010'\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0018J\u0006\u0010(\u001a\u00020\u0006J\u0012\u0010)\u001a\u0004\u0018\u00010\u00182\b\u0010*\u001a\u0004\u0018\u00010+J\u0016\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180-2\u0006\u0010.\u001a\u00020/J\u0006\u00100\u001a\u00020\u0011J\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00180-J\u001e\u00102\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u00112\u0006\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u0011J&\u00106\u001a\u00020\u00162\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\u00112\u0006\u0010;\u001a\u00020\u0011J\u0006\u0010<\u001a\u00020\u0016J\u0006\u0010=\u001a\u00020\u0016J\u000e\u0010>\u001a\u00020\u00162\u0006\u0010?\u001a\u00020#J\u000e\u0010@\u001a\u00020\u00162\u0006\u0010?\u001a\u00020#J\n\u0010A\u001a\u00020\u0006*\u00020BJ\f\u0010C\u001a\u00020\u0018*\u00020\u0018H\u0007J\f\u0010D\u001a\u00020\u0018*\u0004\u0018\u00010\u0018J\f\u0010E\u001a\u00020\u0013*\u00020\u001bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\u000e¨\u0006F"},
   d2 = {"Lme/jooon/JooonAddons/utils/Utils;", "", "()V", "STRIP_COLOR_PATTERN", "Lkotlin/text/Regex;", "inDungeon", "", "getInDungeon", "()Z", "inSkyblock", "getInSkyblock", "skyblock", "getSkyblock", "setSkyblock", "(Z)V", "VecToYawPitch", "Lkotlin/Pair;", "", "vec", "Lnet/minecraft/util/Vec3;", "playerPos", "addMessage", "", "message", "", "blockPosToYawPitch", "blockPos", "Lnet/minecraft/util/BlockPos;", "cancelChatPacket", "ReceivePacketEvent", "Lme/jooon/JooonAddons/events/impl/PacketEvent$ReceiveEvent;", "checkThreadAndQueue", "run", "Lkotlin/Function0;", "findItemInHotbar", "", "name", "findItemInHotbarWithLore", "lore", "findItemInInventory", "fullInventory", "getGuiName", "gui", "Lnet/minecraft/client/gui/GuiScreen;", "getItemLore", "", "itemStack", "Lnet/minecraft/item/ItemStack;", "getRenderPartialTicks", "getScoreboardLines", "interpolateRotation", "par1", "par2", "par3", "rotateCorpse", "bat", "Lnet/minecraft/entity/EntityLivingBase;", "p_77043_2_", "p_77043_3_", "partialTicks", "scoreboardData", "sendItemTags", "switchToItemInHotbar", "slotIndex", "switchToItemInInventory", "ensureFile", "Ljava/io/File;", "stripColor", "stripControlCodes", "toVec3", "JooonAddons"}
)
@SourceDebugExtension({"SMAP\nUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utils.kt\nme/jooon/JooonAddons/utils/Utils\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,284:1\n1549#2:285\n1620#2,3:286\n*S KotlinDebug\n*F\n+ 1 Utils.kt\nme/jooon/JooonAddons/utils/Utils\n*L\n106#1:285\n106#1:286,3\n*E\n"})
public final class Utils {
   @NotNull
   public static final Utils INSTANCE = new Utils();
   @NotNull
   private static final Regex STRIP_COLOR_PATTERN = new Regex("(?i)§[\\dA-FK-OR]");
   private static boolean skyblock;

   private Utils() {
   }

   public final boolean getSkyblock() {
      return skyblock;
   }

   public final void setSkyblock(boolean <set-?>) {
      skyblock = var1;
   }

   public final boolean getInSkyblock() {
      return Intrinsics.areEqual(SBInfo.INSTANCE.getMode(), "SKYBLOCK") || skyblock;
   }

   public final boolean getInDungeon() {
      return Intrinsics.areEqual(SBInfo.INSTANCE.getMode(), "dungeon");
   }

   public final void sendItemTags() {
      NBTTagCompound tags = JooonAddons.Companion.getMc().field_71439_g.func_70694_bm().func_77978_p();
      JooonAddons.Companion.getMc().field_71439_g.func_145747_a((IChatComponent)(new ChatComponentText(String.valueOf(tags))));
   }

   @Nullable
   public final String getGuiName(@Nullable GuiScreen gui) {
      String var2;
      if (gui instanceof GuiChest) {
         Container var10000 = ((GuiChest)gui).field_147002_h;
         Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type net.minecraft.inventory.ContainerChest");
         var2 = ((ContainerChest)var10000).func_85151_d().func_145748_c_().func_150260_c();
      } else {
         var2 = "";
      }

      return var2;
   }

   @NotNull
   public final List<String> getItemLore(@NotNull ItemStack itemStack) {
      Intrinsics.checkNotNullParameter(itemStack, "itemStack");
      int NBT_INTEGER = true;
      int NBT_STRING = 8;
      int NBT_LIST = 9;
      int NBT_COMPOUND = 10;
      List var10000;
      if (itemStack.func_77942_o() && itemStack.func_77978_p().func_150297_b("display", NBT_COMPOUND)) {
         NBTTagCompound display = itemStack.func_77978_p().func_74775_l("display");
         if (display.func_150297_b("Lore", NBT_LIST)) {
            NBTTagList lore = display.func_150295_c("Lore", NBT_STRING);
            List loreAsList = (List)(new ArrayList());
            int lineNumber = 0;

            for(int var10 = lore.func_74745_c(); lineNumber < var10; ++lineNumber) {
               String var10001 = lore.func_150307_f(lineNumber);
               Intrinsics.checkNotNullExpressionValue(var10001, "lore.getStringTagAt(lineNumber)");
               loreAsList.add(var10001);
            }

            var10000 = Collections.unmodifiableList(loreAsList);
            Intrinsics.checkNotNullExpressionValue(var10000, "unmodifiableList(loreAsList)");
            return var10000;
         }
      }

      var10000 = Collections.emptyList();
      Intrinsics.checkNotNullExpressionValue(var10000, "emptyList()");
      return var10000;
   }

   public final void scoreboardData() {
      if (StringsKt.contains$default((CharSequence)this.getScoreboardLines().get(3), (CharSequence)"F7", false, 2, (Object)null) || StringsKt.contains$default((CharSequence)this.getScoreboardLines().get(3), (CharSequence)"M7", false, 2, (Object)null)) {
         this.addMessage("True");
      }

   }

   public final boolean fullInventory() {
      ItemStack[] inventory = JooonAddons.Companion.getMc().field_71439_g.field_71071_by.field_70462_a;

      for(int i = 0; i < 36; ++i) {
         if (inventory[i] == null) {
            return false;
         }
      }

      return true;
   }

   public final void addMessage(@NotNull String message) {
      Intrinsics.checkNotNullParameter(message, "message");
      JooonAddons.Companion.getMc().field_71439_g.func_145747_a((IChatComponent)(new ChatComponentText(message)));
   }

   @NotNull
   public final List<String> getScoreboardLines() {
      Iterable $this$map$iv = (Iterable)ScoreboardUtil.INSTANCE.fetchScoreboardLines();
      int $i$f$map = false;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = false;
      Iterator var7 = $this$map$iv.iterator();

      while(var7.hasNext()) {
         Object item$iv$iv = var7.next();
         String it = (String)item$iv$iv;
         int var10 = false;
         destination$iv$iv.add(INSTANCE.stripControlCodes(it));
      }

      List lines = (List)destination$iv$iv;
      return lines;
   }

   @NotNull
   public final String stripControlCodes(@Nullable String $this$stripControlCodes) {
      Companion var10000 = UTextComponent.Companion;
      String var10001 = $this$stripControlCodes;
      if ($this$stripControlCodes == null) {
         var10001 = "";
      }

      return var10000.stripFormatting(var10001);
   }

   public final void checkThreadAndQueue(@NotNull Function0<Unit> run) {
      Intrinsics.checkNotNullParameter(run, "run");
      if (!JooonAddons.Companion.getMc().func_152345_ab()) {
         JooonAddons.Companion.getMc().func_152344_a(Utils::checkThreadAndQueue$lambda$1);
      } else {
         run.invoke();
      }

   }

   public final void cancelChatPacket(@NotNull PacketEvent.ReceiveEvent ReceivePacketEvent) {
      Intrinsics.checkNotNullParameter(ReceivePacketEvent, "ReceivePacketEvent");
      if (ReceivePacketEvent.getPacket() instanceof S02PacketChat) {
         ReceivePacketEvent.setCanceled(true);
         final Packet packet = ReceivePacketEvent.getPacket();
         this.checkThreadAndQueue((Function0)(new Function0<Unit>() {
            public final void invoke() {
               MinecraftForge.EVENT_BUS.post((Event)(new MainReceivePacketEvent((INetHandler)JooonAddons.Companion.getMc().func_147114_u(), ReceivePacketEvent.getPacket())));
               MinecraftForge.EVENT_BUS.post((Event)(new ClientChatReceivedEvent(((S02PacketChat)packet).func_179841_c(), ((S02PacketChat)packet).func_148915_c())));
            }
         }));
      }
   }

   @NotNull
   public final Pair<Float, Float> blockPosToYawPitch(@NotNull BlockPos blockPos, @NotNull Vec3 playerPos) {
      Intrinsics.checkNotNullParameter(blockPos, "blockPos");
      Intrinsics.checkNotNullParameter(playerPos, "playerPos");
      double diffX = (double)blockPos.func_177958_n() - playerPos.field_72450_a - 0.5D;
      double diffY = (double)blockPos.func_177956_o() - (playerPos.field_72448_b + (double)JooonAddons.Companion.getMc().field_71439_g.func_70047_e()) + 0.5D;
      double diffZ = (double)blockPos.func_177952_p() - playerPos.field_72449_c + 0.5D;
      float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
      double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
      float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, dist)));
      return new Pair(JooonAddons.Companion.getMc().field_71439_g.field_70177_z + MathHelper.func_76142_g(yaw - JooonAddons.Companion.getMc().field_71439_g.field_70177_z), JooonAddons.Companion.getMc().field_71439_g.field_70125_A + MathHelper.func_76142_g(pitch - JooonAddons.Companion.getMc().field_71439_g.field_70125_A));
   }

   @NotNull
   public final Pair<Float, Float> VecToYawPitch(@NotNull Vec3 vec, @NotNull Vec3 playerPos) {
      Intrinsics.checkNotNullParameter(vec, "vec");
      Intrinsics.checkNotNullParameter(playerPos, "playerPos");
      double diffX = vec.field_72450_a - playerPos.field_72450_a;
      double diffY = vec.field_72448_b - (playerPos.field_72448_b + (double)JooonAddons.Companion.getMc().field_71439_g.func_70047_e()) + 0.5D;
      double diffZ = vec.field_72449_c - playerPos.field_72449_c;
      float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
      double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
      float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, dist)));
      return new Pair(JooonAddons.Companion.getMc().field_71439_g.field_70177_z + MathHelper.func_76142_g(yaw - JooonAddons.Companion.getMc().field_71439_g.field_70177_z), JooonAddons.Companion.getMc().field_71439_g.field_70125_A + MathHelper.func_76142_g(pitch - JooonAddons.Companion.getMc().field_71439_g.field_70125_A));
   }

   public final int findItemInHotbar(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");

      for(int slot = 0; slot < 9; ++slot) {
         ItemStack var10000 = JooonAddons.Companion.getMc().field_71439_g.field_71071_by.field_70462_a[slot];
         if (var10000 != null) {
            ItemStack itemStack = var10000;
            String var4 = itemStack.func_82833_r();
            Intrinsics.checkNotNullExpressionValue(var4, "itemStack.displayName");
            if (StringsKt.contains$default((CharSequence)stripColor(var4), (CharSequence)name, false, 2, (Object)null)) {
               return slot;
            }
         }
      }

      return -1;
   }

   public final int findItemInHotbarWithLore(@NotNull String lore) {
      Intrinsics.checkNotNullParameter(lore, "lore");

      for(int slot = 0; slot < 36; ++slot) {
         ItemStack var10000 = JooonAddons.Companion.getMc().field_71439_g.field_71071_by.func_70301_a(slot);
         if (var10000 != null) {
            ItemStack itemStack = var10000;
            Iterator var4 = this.getItemLore(itemStack).iterator();

            while(var4.hasNext()) {
               String line = (String)var4.next();
               if (line != null && StringsKt.contains$default((CharSequence)stripColor(line), (CharSequence)lore, false, 2, (Object)null)) {
                  return slot;
               }
            }
         }
      }

      return -1;
   }

   public final int findItemInInventory(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");

      for(int slot = 9; slot < 36; ++slot) {
         ItemStack var10000 = JooonAddons.Companion.getMc().field_71439_g.field_71071_by.func_70301_a(slot);
         if (var10000 != null) {
            ItemStack itemStack = var10000;
            String var4 = itemStack.func_82833_r();
            Intrinsics.checkNotNullExpressionValue(var4, "itemStack.displayName");
            if (StringsKt.contains$default((CharSequence)stripColor(var4), (CharSequence)name, false, 2, (Object)null)) {
               return slot;
            }
         }
      }

      return -1;
   }

   public final void switchToItemInInventory(int slotIndex) {
      Multithreading.runAsync(Utils::switchToItemInInventory$lambda$2);
   }

   public final void switchToItemInHotbar(int slotIndex) {
      Multithreading.runAsync(Utils::switchToItemInHotbar$lambda$3);
   }

   public final float interpolateRotation(float par1, float par2, float par3) {
      float f;
      for(f = par2 - par1; f < -180.0F; f += 360.0F) {
      }

      while(f >= 180.0F) {
         f -= 360.0F;
      }

      return par1 + par3 * f;
   }

   public final void rotateCorpse(@NotNull EntityLivingBase bat, float p_77043_2_, float p_77043_3_, float partialTicks) {
      Intrinsics.checkNotNullParameter(bat, "bat");
      GlStateManager.func_179114_b(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);
      if (bat.field_70725_aQ > 0) {
         float f = ((float)bat.field_70725_aQ + partialTicks - 1.0F) / 20.0F * 1.6F;
         f = MathHelper.func_76129_c(f);
         if (f > 1.0F) {
            f = 1.0F;
         }

         GlStateManager.func_179114_b(f * 90.0F, 0.0F, 0.0F, 1.0F);
      } else {
         String s = EnumChatFormatting.func_110646_a(bat.func_70005_c_());
         if (s != null && Intrinsics.areEqual(s, "Dinnerbone") || Intrinsics.areEqual(s, "Grumm") && (!(bat instanceof EntityPlayer) || ((EntityPlayer)bat).func_175148_a(EnumPlayerModelParts.CAPE))) {
            GlStateManager.func_179109_b(0.0F, bat.field_70131_O + 0.1F, 0.0F);
            GlStateManager.func_179114_b(180.0F, 0.0F, 0.0F, 1.0F);
         }
      }

   }

   public final float getRenderPartialTicks() {
      Minecraft minecraft = Minecraft.func_71410_x();

      try {
         Field timerField = Minecraft.class.getDeclaredField("timer");
         timerField.setAccessible(true);
         Object timer = timerField.get(minecraft);
         Field renderPartialTicksField = timer.getClass().getDeclaredField("renderPartialTicks");
         renderPartialTicksField.setAccessible(true);
         return renderPartialTicksField.getFloat(timer);
      } catch (NoSuchFieldException var5) {
      } catch (IllegalAccessException var6) {
      }

      return 0.0F;
   }

   @JvmStatic
   @NotNull
   public static final String stripColor(@NotNull String $this$stripColor) {
      Intrinsics.checkNotNullParameter($this$stripColor, "<this>");
      return STRIP_COLOR_PATTERN.replace((CharSequence)$this$stripColor, "");
   }

   @JvmStatic
   @NotNull
   public static final Vec3 toVec3(@NotNull BlockPos $this$toVec3) {
      Intrinsics.checkNotNullParameter($this$toVec3, "<this>");
      return new Vec3((double)$this$toVec3.func_177958_n(), (double)$this$toVec3.func_177956_o(), (double)$this$toVec3.func_177952_p());
   }

   public final boolean ensureFile(@NotNull File $this$ensureFile) {
      Intrinsics.checkNotNullParameter($this$ensureFile, "<this>");
      return ($this$ensureFile.getParentFile().exists() || $this$ensureFile.getParentFile().mkdirs()) && $this$ensureFile.createNewFile();
   }

   private static final void checkThreadAndQueue$lambda$1(Function0 $tmp0) {
      Intrinsics.checkNotNullParameter($tmp0, "$tmp0");
      $tmp0.invoke();
   }

   private static final void switchToItemInInventory$lambda$2(int $slotIndex) {
      JooonAddons.Companion.getMc().func_147108_a((GuiScreen)(new GuiInventory((EntityPlayer)JooonAddons.Companion.getMc().field_71439_g)));
      int windowId = (new GuiInventory((EntityPlayer)JooonAddons.Companion.getMc().field_71439_g)).field_147002_h.field_75152_c;
      ItemStack itemStack = JooonAddons.Companion.getMc().field_71439_g.field_71071_by.field_70462_a[$slotIndex];
      JooonAddons.Companion.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow(windowId, $slotIndex, 0, 0, itemStack, (short)0)));
      itemStack = JooonAddons.Companion.getMc().field_71439_g.field_71071_by.field_70462_a[JooonAddons.Companion.getMc().field_71439_g.field_71071_by.field_70461_c];
      JooonAddons.Companion.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow(windowId, JooonAddons.Companion.getMc().field_71439_g.field_71071_by.field_70461_c + 36, 0, 0, itemStack, (short)0)));
      itemStack = JooonAddons.Companion.getMc().field_71439_g.field_71071_by.field_70462_a[$slotIndex];
      JooonAddons.Companion.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow(windowId, $slotIndex, 0, 0, itemStack, (short)0)));
      Thread.sleep(100L);
      JooonAddons.Companion.getMc().field_71439_g.func_71053_j();
   }

   private static final void switchToItemInHotbar$lambda$3(int $slotIndex) {
      JooonAddons.Companion.getMc().func_147108_a((GuiScreen)(new GuiInventory((EntityPlayer)JooonAddons.Companion.getMc().field_71439_g)));
      int windowId = (new GuiInventory((EntityPlayer)JooonAddons.Companion.getMc().field_71439_g)).field_147002_h.field_75152_c;
      ItemStack itemStack = JooonAddons.Companion.getMc().field_71439_g.field_71071_by.func_70301_a($slotIndex);
      JooonAddons.Companion.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow(windowId, $slotIndex + 36, 0, 0, itemStack, (short)0)));
      itemStack = JooonAddons.Companion.getMc().field_71439_g.field_71071_by.func_70301_a(JooonAddons.Companion.getMc().field_71439_g.field_71071_by.field_70461_c);
      JooonAddons.Companion.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow(windowId, JooonAddons.Companion.getMc().field_71439_g.field_71071_by.field_70461_c + 36, 0, 0, itemStack, (short)0)));
      itemStack = JooonAddons.Companion.getMc().field_71439_g.field_71071_by.func_70301_a($slotIndex);
      JooonAddons.Companion.getMc().func_147114_u().func_147297_a((Packet)(new C0EPacketClickWindow(windowId, $slotIndex + 36, 0, 0, itemStack, (short)0)));
      Thread.sleep(100L);
      JooonAddons.Companion.getMc().field_71439_g.func_71053_j();
   }
}
