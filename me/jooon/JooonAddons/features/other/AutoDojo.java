package me.jooon.JooonAddons.features.other;

import gg.essential.api.utils.Multithreading;
import java.awt.Color;
import java.util.List;
import java.util.Vector;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.events.impl.PacketEvent;
import me.jooon.JooonAddons.events.impl.PlayerAttackEntityEvent;
import me.jooon.JooonAddons.events.impl.RenderEntityModelEvent;
import me.jooon.JooonAddons.features.Feature;
import me.jooon.JooonAddons.render.RenderUtils;
import me.jooon.JooonAddons.utils.PlayerRotation;
import me.jooon.JooonAddons.utils.SBInfo;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u000201B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0007J\u0010\u0010&\u001a\u00020#2\u0006\u0010$\u001a\u00020'H\u0007J\u0010\u0010(\u001a\u00020#2\u0006\u0010$\u001a\u00020)H\u0007J\u0010\u0010*\u001a\u00020#2\u0006\u0010$\u001a\u00020+H\u0007J\u0010\u0010,\u001a\u00020#2\u0006\u0010$\u001a\u00020-H\u0007J\u0010\u0010.\u001a\u00020#2\u0006\u0010$\u001a\u00020/H\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u00062"},
   d2 = {"Lme/jooon/JooonAddons/features/other/AutoDojo;", "Lme/jooon/JooonAddons/features/Feature;", "()V", "dojoType", "Lme/jooon/JooonAddons/features/other/AutoDojo$DojoType;", "getDojoType", "()Lme/jooon/JooonAddons/features/other/AutoDojo$DojoType;", "setDojoType", "(Lme/jooon/JooonAddons/features/other/AutoDojo$DojoType;)V", "lastJerryState", "", "getLastJerryState", "()Z", "setLastJerryState", "(Z)V", "lookCooldown", "", "getLookCooldown", "()J", "setLookCooldown", "(J)V", "masteryBlocks", "Ljava/util/Vector;", "Lme/jooon/JooonAddons/features/other/AutoDojo$MasteryBlock;", "getMasteryBlocks", "()Ljava/util/Vector;", "setMasteryBlocks", "(Ljava/util/Vector;)V", "skeletonEntity", "Lnet/minecraft/entity/EntityLivingBase;", "getSkeletonEntity", "()Lnet/minecraft/entity/EntityLivingBase;", "setSkeletonEntity", "(Lnet/minecraft/entity/EntityLivingBase;)V", "detectDojo", "", "event", "Lnet/minecraftforge/client/event/ClientChatReceivedEvent;", "onEntityRender", "Lme/jooon/JooonAddons/events/impl/RenderEntityModelEvent;", "onPlayerEntityAttack", "Lme/jooon/JooonAddons/events/impl/PlayerAttackEntityEvent;", "onTick", "Lnet/minecraftforge/fml/common/gameevent/TickEvent$PlayerTickEvent;", "onWorldUnload", "Lnet/minecraftforge/event/world/WorldEvent$Load;", "onblockChangePacket", "Lme/jooon/JooonAddons/events/impl/PacketEvent$ReceiveEvent;", "DojoType", "MasteryBlock", "JooonAddons"}
)
public final class AutoDojo extends Feature {
   @NotNull
   public static final AutoDojo INSTANCE = new AutoDojo();
   @Nullable
   private static EntityLivingBase skeletonEntity;
   private static long lookCooldown;
   @NotNull
   private static AutoDojo.DojoType dojoType;
   private static boolean lastJerryState;
   @NotNull
   private static Vector<AutoDojo.MasteryBlock> masteryBlocks;

   private AutoDojo() {
   }

   @Nullable
   public final EntityLivingBase getSkeletonEntity() {
      return skeletonEntity;
   }

   public final void setSkeletonEntity(@Nullable EntityLivingBase <set-?>) {
      skeletonEntity = var1;
   }

   public final long getLookCooldown() {
      return lookCooldown;
   }

   public final void setLookCooldown(long <set-?>) {
      lookCooldown = var1;
   }

   @NotNull
   public final AutoDojo.DojoType getDojoType() {
      return dojoType;
   }

   public final void setDojoType(@NotNull AutoDojo.DojoType <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      dojoType = var1;
   }

   @SubscribeEvent
   public final void onEntityRender(@NotNull RenderEntityModelEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getAutoDojo()) {
         if (dojoType == AutoDojo.DojoType.CONTROL && skeletonEntity == null && event.getEntity() instanceof EntitySkeleton && ((EntitySkeleton)event.getEntity()).func_82202_m() == 1 && event.getEntity().func_70032_d((Entity)this.getMc().field_71439_g) < 25.0F) {
            skeletonEntity = event.getEntity();
            this.printdev("Detected Skeleton");
         }

         if (event.getEntity() instanceof EntitySkeleton && ((EntitySkeleton)event.getEntity()).func_82202_m() == 1 && event.getEntity().func_70032_d((Entity)this.getMc().field_71439_g) < 25.0F && dojoType == AutoDojo.DojoType.CONTROL && skeletonEntity != null) {
            EntityLivingBase var10000 = skeletonEntity;
            Intrinsics.checkNotNull(var10000);
            double var9 = var10000.field_70165_t;
            EntityLivingBase var10001 = skeletonEntity;
            Intrinsics.checkNotNull(var10001);
            double var10 = var10001.field_70165_t;
            EntityLivingBase var10002 = skeletonEntity;
            Intrinsics.checkNotNull(var10002);
            double x = var9 + (var10 - var10002.field_70142_S) * (Ping.INSTANCE.getPing() / (double)20);
            var10000 = skeletonEntity;
            Intrinsics.checkNotNull(var10000);
            var9 = var10000.field_70163_u;
            var10001 = skeletonEntity;
            Intrinsics.checkNotNull(var10001);
            var10 = var10001.field_70163_u;
            var10002 = skeletonEntity;
            Intrinsics.checkNotNull(var10002);
            double y = var9 + (var10 - var10002.field_70137_T);
            var10000 = skeletonEntity;
            Intrinsics.checkNotNull(var10000);
            var9 = var10000.field_70161_v;
            var10001 = skeletonEntity;
            Intrinsics.checkNotNull(var10001);
            var10 = var10001.field_70161_v;
            var10002 = skeletonEntity;
            Intrinsics.checkNotNull(var10002);
            double z = var9 + (var10 - var10002.field_70136_U) * (Ping.INSTANCE.getPing() / (double)20);
            RenderUtils var11 = RenderUtils.INSTANCE;
            Vec3 var13 = new Vec3(x, y, z);
            Color var14 = Color.cyan;
            Intrinsics.checkNotNullExpressionValue(var14, "cyan");
            var11.drawBox(var13, var14, event.getPartialTicks());
            if (System.currentTimeMillis() - lookCooldown < 40L) {
               return;
            }

            lookCooldown = System.currentTimeMillis();
            Utils var12 = Utils.INSTANCE;
            var13 = new Vec3(x, y + 1.5D, z);
            Vec3 var15 = this.getMc().field_71439_g.func_174791_d();
            Intrinsics.checkNotNullExpressionValue(var15, "mc.thePlayer.positionVector");
            Pair yawAndPitch = var12.VecToYawPitch(var13, var15);
            new PlayerRotation(new PlayerRotation.Rotation(((Number)yawAndPitch.getFirst()).floatValue(), ((Number)yawAndPitch.getSecond()).floatValue()), 150L);
         }

      }
   }

   @SubscribeEvent
   public final void onWorldUnload(@NotNull Load event) {
      Intrinsics.checkNotNullParameter(event, "event");
      skeletonEntity = null;
      lookCooldown = 0L;
      dojoType = AutoDojo.DojoType.NONE;
   }

   public final boolean getLastJerryState() {
      return lastJerryState;
   }

   public final void setLastJerryState(boolean <set-?>) {
      lastJerryState = var1;
   }

   @SubscribeEvent
   public final void detectDojo(@NotNull ClientChatReceivedEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getAutoDojo()) {
         if (Intrinsics.areEqual(SBInfo.INSTANCE.getMode(), "crimson_isle")) {
            String var10000 = event.message.func_150260_c();
            Intrinsics.checkNotNullExpressionValue(var10000, "event.message.unformattedText");
            String message = Utils.stripColor(var10000);
            String var3 = StringsKt.replace$default(message, " ", "", false, 4, (Object)null);
            switch(var3.hashCode()) {
            case -1603379652:
               if (var3.equals("TestofForceOBJECTIVES")) {
                  dojoType = AutoDojo.DojoType.FORCE;
                  this.printdev("Test Of Force");
                  Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§6 Current Dojo: Force");
               }
               break;
            case -1429495437:
               if (var3.equals("TestofDisciplineOBJECTIVES")) {
                  dojoType = AutoDojo.DojoType.DISCIPLINE;
                  this.printdev("Test Of Discipline");
                  Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§6 Current Dojo: Discipline");
               }
               break;
            case 552960110:
               if (var3.equals("TestofControlOBJECTIVES")) {
                  dojoType = AutoDojo.DojoType.CONTROL;
                  this.printdev("Test Of Control");
                  Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§6 Current Dojo: Control, move your head to the skeleton once it spawns!");
                  lastJerryState = Config.INSTANCE.getJerryKB();
                  Config.INSTANCE.setJerryKB(true);
               }
               break;
            case 1022479592:
               if (var3.equals("TestofMasteryOBJECTIVES")) {
                  dojoType = AutoDojo.DojoType.MASTERY;
                  this.printdev("Test Of Mastery");
                  Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§6 Current Dojo: Mastery. Hold still!");
                  Multithreading.runAsync(AutoDojo::detectDojo$lambda$0);
               }
            }

            if (StringsKt.contains$default((CharSequence)StringsKt.replace$default(message, " ", "", false, 4, (Object)null), (CharSequence)"YourRank:", false, 2, (Object)null)) {
               this.printdev("Clearing");
               Config.INSTANCE.setJerryKB(lastJerryState);
               dojoType = AutoDojo.DojoType.NONE;
               skeletonEntity = null;
               masteryBlocks.clear();
               Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§8 Current Dojo: None");
            }

         }
      }
   }

   @SubscribeEvent
   public final void onPlayerEntityAttack(@NotNull PlayerAttackEntityEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getAutoDojo()) {
         if (event.getTargetEntity() instanceof EntityZombie) {
            Entity target = event.getTargetEntity();
            switch(AutoDojo.WhenMappings.$EnumSwitchMapping$0[dojoType.ordinal()]) {
            case 1:
               ItemStack var10000 = ((EntityZombie)target).func_71124_b(4);
               if (var10000 == null) {
                  return;
               } else {
                  ItemStack targetHelmet = var10000;
                  Item var4 = targetHelmet.func_77973_b();
                  int slot;
                  if (Intrinsics.areEqual(var4, Items.field_151024_Q)) {
                     slot = Utils.INSTANCE.findItemInHotbar("Wooden Sword");
                     if (slot != -1) {
                        this.getMc().field_71439_g.field_71071_by.field_70461_c = slot;
                     } else {
                        this.printdev("Havent Found Wooden Sword");
                     }
                  } else if (Intrinsics.areEqual(var4, Items.field_151028_Y)) {
                     slot = Utils.INSTANCE.findItemInHotbar("Iron Sword");
                     if (slot != -1) {
                        this.getMc().field_71439_g.field_71071_by.field_70461_c = slot;
                     } else {
                        this.printdev("Havent Found Iron Sword");
                     }
                  } else if (Intrinsics.areEqual(var4, Items.field_151169_ag)) {
                     slot = Utils.INSTANCE.findItemInHotbar("Golden Sword");
                     if (slot != -1) {
                        this.getMc().field_71439_g.field_71071_by.field_70461_c = slot;
                     } else {
                        this.printdev("Havent Found Golden Sword");
                     }
                  } else if (Intrinsics.areEqual(var4, Items.field_151161_ac)) {
                     slot = Utils.INSTANCE.findItemInHotbar("Diamond Sword");
                     if (slot != -1) {
                        this.getMc().field_71439_g.field_71071_by.field_70461_c = slot;
                     } else {
                        this.printdev("Havent Found Diamond Sword");
                     }
                  }
               }
            case 2:
            default:
            }
         }
      }
   }

   @NotNull
   public final Vector<AutoDojo.MasteryBlock> getMasteryBlocks() {
      return masteryBlocks;
   }

   public final void setMasteryBlocks(@NotNull Vector<AutoDojo.MasteryBlock> <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      masteryBlocks = var1;
   }

   @SubscribeEvent
   public final void onblockChangePacket(@NotNull PacketEvent.ReceiveEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getAutoDojo()) {
         if (dojoType == AutoDojo.DojoType.MASTERY) {
            if (event.getPacket() instanceof S23PacketBlockChange) {
               Packet packet = event.getPacket();
               if (((S23PacketBlockChange)packet).field_148883_d != null) {
                  if (this.getMc().field_71439_g.func_174831_c(((S23PacketBlockChange)packet).func_179827_b()) < Math.pow(2.0D, 10.0D)) {
                     int state = Block.func_176210_f(((S23PacketBlockChange)packet).field_148883_d);
                     if (state == 16419) {
                        Vector var10000 = masteryBlocks;
                        BlockPos var10003 = ((S23PacketBlockChange)packet).func_179827_b();
                        Intrinsics.checkNotNullExpressionValue(var10003, "packet.blockPosition");
                        var10000.add(new AutoDojo.MasteryBlock(var10003, System.currentTimeMillis() + (long)3000));
                     }
                  }

               }
            }
         }
      }
   }

   @SubscribeEvent
   public final void onTick(@NotNull PlayerTickEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (dojoType == AutoDojo.DojoType.MASTERY) {
         if (Config.INSTANCE.getAutoDojo()) {
            if (!masteryBlocks.isEmpty()) {
               AutoDojo.MasteryBlock closestMasteryBlock = (AutoDojo.MasteryBlock)CollectionsKt.first((List)masteryBlocks);
               if (this.getMc().field_71439_g.func_70694_bm() == null || !Intrinsics.areEqual(this.getMc().field_71439_g.func_70694_bm().func_77973_b(), Items.field_151031_f)) {
                  int bowSlot = Utils.INSTANCE.findItemInHotbar("Bow");
                  if (bowSlot != -1) {
                     this.getMc().field_71439_g.field_71071_by.field_70461_c = bowSlot;
                  }
               }

               if (closestMasteryBlock.getTimeTurnsRed() - System.currentTimeMillis() < 330L) {
                  double x = closestMasteryBlock.getPos().func_177958_n() < 0 ? (double)closestMasteryBlock.getPos().func_177958_n() + 0.5D : (double)closestMasteryBlock.getPos().func_177958_n() - 0.5D;
                  double y = (double)closestMasteryBlock.getPos().func_177956_o() + 0.7D;
                  double z = closestMasteryBlock.getPos().func_177952_p() < 0 ? (double)closestMasteryBlock.getPos().func_177952_p() + 0.5D : (double)closestMasteryBlock.getPos().func_177952_p() - 0.5D;
                  Utils var10000 = Utils.INSTANCE;
                  Vec3 var10001 = new Vec3(x, y, z);
                  Vec3 var10002 = this.getMc().field_71439_g.func_174791_d();
                  Intrinsics.checkNotNullExpressionValue(var10002, "mc.thePlayer.positionVector");
                  Pair yawAndPitch = var10000.VecToYawPitch(var10001, var10002);
                  new PlayerRotation(new PlayerRotation.Rotation(((Number)yawAndPitch.getFirst()).floatValue(), ((Number)yawAndPitch.getSecond()).floatValue()), 5L);
                  masteryBlocks.remove(closestMasteryBlock);
                  Multithreading.runAsync(AutoDojo::onTick$lambda$1);
               }

            }
         }
      }
   }

   private static final void detectDojo$lambda$0() {
      Thread.sleep(200L);
      int bowSlot = Utils.INSTANCE.findItemInHotbar("Bow");
      if (bowSlot != -1) {
         INSTANCE.getMc().field_71439_g.field_71071_by.field_70461_c = bowSlot;
      }

      INSTANCE.getMc().func_147114_u().func_147297_a((Packet)(new C08PacketPlayerBlockPlacement(INSTANCE.getMc().field_71439_g.func_70694_bm())));
   }

   private static final void onTick$lambda$1() {
      Thread.sleep(120L);
      INSTANCE.getMc().func_147114_u().func_147297_a((Packet)(new C07PacketPlayerDigging(Action.RELEASE_USE_ITEM, new BlockPos(0, 0, 0), EnumFacing.DOWN)));
      Thread.sleep(30L);
      INSTANCE.getMc().func_147114_u().func_147297_a((Packet)(new C08PacketPlayerBlockPlacement(INSTANCE.getMc().field_71439_g.func_70694_bm())));
   }

   static {
      dojoType = AutoDojo.DojoType.NONE;
      masteryBlocks = new Vector();
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"},
      d2 = {"Lme/jooon/JooonAddons/features/other/AutoDojo$DojoType;", "", "(Ljava/lang/String;I)V", "NONE", "CONTROL", "FORCE", "MASTERY", "DISCIPLINE", "JooonAddons"}
   )
   public static enum DojoType {
      NONE,
      CONTROL,
      FORCE,
      MASTERY,
      DISCIPLINE;

      // $FF: synthetic method
      private static final AutoDojo.DojoType[] $values() {
         AutoDojo.DojoType[] var0 = new AutoDojo.DojoType[]{NONE, CONTROL, FORCE, MASTERY, DISCIPLINE};
         return var0;
      }
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"},
      d2 = {"Lme/jooon/JooonAddons/features/other/AutoDojo$MasteryBlock;", "", "pos", "Lnet/minecraft/util/BlockPos;", "timeTurnsRed", "", "(Lnet/minecraft/util/BlockPos;J)V", "getPos", "()Lnet/minecraft/util/BlockPos;", "setPos", "(Lnet/minecraft/util/BlockPos;)V", "getTimeTurnsRed", "()J", "setTimeTurnsRed", "(J)V", "JooonAddons"}
   )
   public static final class MasteryBlock {
      @NotNull
      private BlockPos pos;
      private long timeTurnsRed;

      public MasteryBlock(@NotNull BlockPos pos, long timeTurnsRed) {
         Intrinsics.checkNotNullParameter(pos, "pos");
         super();
         this.pos = pos;
         this.timeTurnsRed = timeTurnsRed;
      }

      @NotNull
      public final BlockPos getPos() {
         return this.pos;
      }

      public final void setPos(@NotNull BlockPos <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.pos = var1;
      }

      public final long getTimeTurnsRed() {
         return this.timeTurnsRed;
      }

      public final void setTimeTurnsRed(long <set-?>) {
         this.timeTurnsRed = var1;
      }
   }

   // $FF: synthetic class
   @Metadata(
      mv = {1, 8, 0},
      k = 3,
      xi = 48
   )
   public class WhenMappings {
      // $FF: synthetic field
      public static final int[] $EnumSwitchMapping$0;

      static {
         int[] var0 = new int[AutoDojo.DojoType.values().length];

         try {
            var0[AutoDojo.DojoType.DISCIPLINE.ordinal()] = 1;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[AutoDojo.DojoType.FORCE.ordinal()] = 2;
         } catch (NoSuchFieldError var2) {
         }

         $EnumSwitchMapping$0 = var0;
      }
   }
}
