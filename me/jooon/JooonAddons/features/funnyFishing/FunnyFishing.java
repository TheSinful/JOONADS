package me.jooon.JooonAddons.features.funnyFishing;

import gg.essential.api.utils.Multithreading;
import gg.essential.universal.UChat;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.events.impl.PacketEvent;
import me.jooon.JooonAddons.features.Feature;
import me.jooon.JooonAddons.features.other.InstaSell;
import me.jooon.JooonAddons.features.other.WardrobeEquipper;
import me.jooon.JooonAddons.render.RenderUtils;
import me.jooon.JooonAddons.utils.PlayerRotation;
import me.jooon.JooonAddons.utils.SBInfo;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S14PacketEntity.S17PacketEntityLookMove;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010/\u001a\u0004\u0018\u000100J\b\u00101\u001a\u00020%H\u0002J\u0006\u00102\u001a\u00020%J\u0010\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0002J \u00107\u001a\u0002042\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u0002062\u0006\u0010;\u001a\u00020<H\u0002J\b\u0010=\u001a\u00020\tH\u0002J\b\u0010>\u001a\u000204H\u0002J\u0010\u0010?\u001a\u0002042\u0006\u0010@\u001a\u00020AH\u0007J\u0010\u0010B\u001a\u0002042\u0006\u0010@\u001a\u00020CH\u0007J\u0010\u0010D\u001a\u0002042\u0006\u0010@\u001a\u00020EH\u0007J\u0010\u0010F\u001a\u0002042\u0006\u0010@\u001a\u00020GH\u0007J\u0010\u0010H\u001a\u0002042\u0006\u0010@\u001a\u00020IH\u0007J\u0006\u0010J\u001a\u000204J\u000e\u0010K\u001a\u0002042\u0006\u0010L\u001a\u00020\tJ\u0006\u0010M\u001a\u000204R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000b\"\u0004\b\u001e\u0010\rR\u001a\u0010\u001f\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u000b\"\u0004\b!\u0010\rR\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020%0(X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010)\u001a\u0004\u0018\u00010*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u0006N"},
   d2 = {"Lme/jooon/JooonAddons/features/funnyFishing/FunnyFishing;", "Lme/jooon/JooonAddons/features/Feature;", "()V", "collidingEntity", "Lnet/minecraft/entity/EntityLivingBase;", "enemyEntity", "Lnet/minecraft/entity/item/EntityArmorStand;", "goldenFishEntity", "killing", "", "getKilling", "()Z", "setKilling", "(Z)V", "lastGoldenFishLookTime", "", "lastTimeHitEntity", "lastTimeReeled", "lastTimeSold", "lastTimeTotemPlaced", "lookForGolenFish", "mainLookAtBlock", "Lnet/minecraft/util/MovingObjectPosition;", "getMainLookAtBlock", "()Lnet/minecraft/util/MovingObjectPosition;", "setMainLookAtBlock", "(Lnet/minecraft/util/MovingObjectPosition;)V", "movementCooldown", "pauseMacro", "getPauseMacro", "setPauseMacro", "placingTotem", "getPlacingTotem", "setPlacingTotem", "playersFishHook", "Lnet/minecraft/entity/projectile/EntityFishHook;", "rodSlotIndex", "", "rotateCooldown", "seenFlames", "", "startingPosition", "Lnet/minecraft/util/Vec3;", "getStartingPosition", "()Lnet/minecraft/util/Vec3;", "setStartingPosition", "(Lnet/minecraft/util/Vec3;)V", "getBlocksForTotem", "Lnet/minecraft/util/BlockPos;", "getFireVeil", "getFishingRod", "getMobsWithinAABB", "", "entity", "Lnet/minecraft/entity/Entity;", "getMobsWithinAABBForEntity", "chunk", "Lnet/minecraft/world/chunk/Chunk;", "entityIn", "aabb", "Lnet/minecraft/util/AxisAlignedBB;", "isTotemInRange", "movePlayer", "onChat", "event", "Lnet/minecraftforge/client/event/ClientChatReceivedEvent;", "onPacketRecive", "Lme/jooon/JooonAddons/events/impl/PacketEvent$ReceiveEvent;", "onPlayerTick", "Lnet/minecraftforge/fml/common/gameevent/TickEvent$PlayerTickEvent;", "onWorldLoad", "Lnet/minecraftforge/event/world/WorldEvent$Load;", "onWorldRender", "Lnet/minecraftforge/client/event/RenderWorldLastEvent;", "placeTotem", "reelIn", "recast", "toggleFishing", "JooonAddons"}
)
public final class FunnyFishing extends Feature {
   @NotNull
   public static final FunnyFishing INSTANCE = new FunnyFishing();
   @Nullable
   private static MovingObjectPosition mainLookAtBlock;
   @Nullable
   private static Vec3 startingPosition;
   private static long rotateCooldown;
   private static long movementCooldown;
   private static long lastTimeHitEntity;
   @Nullable
   private static EntityLivingBase collidingEntity;
   private static long lastTimeTotemPlaced;
   private static long lastTimeReeled;
   private static long lastTimeSold;
   private static int rodSlotIndex = -1;
   private static boolean lookForGolenFish;
   @Nullable
   private static EntityArmorStand goldenFishEntity;
   @NotNull
   private static List<Integer> seenFlames = (List)(new ArrayList());
   @Nullable
   private static EntityArmorStand enemyEntity;
   private static long lastGoldenFishLookTime;
   private static boolean pauseMacro;
   @Nullable
   private static EntityFishHook playersFishHook;
   private static boolean placingTotem;
   private static boolean killing;

   private FunnyFishing() {
   }

   @Nullable
   public final MovingObjectPosition getMainLookAtBlock() {
      return mainLookAtBlock;
   }

   public final void setMainLookAtBlock(@Nullable MovingObjectPosition <set-?>) {
      mainLookAtBlock = var1;
   }

   @Nullable
   public final Vec3 getStartingPosition() {
      return startingPosition;
   }

   public final void setStartingPosition(@Nullable Vec3 <set-?>) {
      startingPosition = var1;
   }

   public final boolean getPauseMacro() {
      return pauseMacro;
   }

   public final void setPauseMacro(boolean <set-?>) {
      pauseMacro = var1;
   }

   public final void toggleFishing() {
      if (!Config.INSTANCE.getFunnyFishing()) {
         mainLookAtBlock = this.getMc().field_71439_g.func_174822_a(100.0D, 1.0F);
         int rodSlot = this.getFishingRod();
         if (rodSlot == -1) {
            this.getMc().field_71439_g.func_145747_a((IChatComponent)(new ChatComponentText("§7[§aJooonAddons§7]§8 Haven't detected rod in hotbar")));
            return;
         }

         this.getMc().field_71439_g.field_71071_by.field_70461_c = rodSlot;
         this.reelIn(false);
         startingPosition = this.getMc().field_71439_g.func_174791_d();
         rotateCooldown = System.currentTimeMillis();
         movementCooldown = System.currentTimeMillis();
         if (!BarnFishingTimer.INSTANCE.getTimerRunning()) {
            BarnFishingTimer.INSTANCE.setTimerRunning(true);
            BarnFishingTimer.INSTANCE.setTimerStartTime(System.currentTimeMillis());
         }
      } else {
         mainLookAtBlock = null;
      }

      Config.INSTANCE.setFunnyFishing(!Config.INSTANCE.getFunnyFishing());
      Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§8 Auto Fishing: " + (Config.INSTANCE.getFunnyFishing() ? "§aOn" : "§cOff"));
   }

   public final int getFishingRod() {
      for(int i = 0; i < 8; ++i) {
         ItemStack var10000 = this.getMc().field_71439_g.field_71071_by.field_70462_a[i];
         if (var10000 != null) {
            ItemStack item = var10000;
            if (Intrinsics.areEqual(item.func_77973_b(), Items.field_151112_aM)) {
               return i;
            }
         }
      }

      return -1;
   }

   @SubscribeEvent
   public final void onPacketRecive(@NotNull PacketEvent.ReceiveEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getFunnyFishing()) {
         if (event.getPacket() instanceof S17PacketEntityLookMove) {
            Entity entity = ((S17PacketEntityLookMove)event.getPacket()).func_149065_a((World)this.getMc().field_71441_e);
            if (!(entity instanceof EntityFishHook) || !Intrinsics.areEqual(((EntityFishHook)entity).field_146042_b, this.getMc().field_71439_g)) {
               return;
            }

            playersFishHook = (EntityFishHook)entity;
         }

         if (event.getPacket() instanceof S2APacketParticles) {
            if (playersFishHook == null) {
               return;
            }

            if (((S2APacketParticles)event.getPacket()).func_179749_a() != EnumParticleTypes.WATER_WAKE && ((S2APacketParticles)event.getPacket()).func_179749_a() != EnumParticleTypes.FLAME) {
               return;
            }

            if (((S2APacketParticles)event.getPacket()).func_149222_k() != 6 || ((S2APacketParticles)event.getPacket()).func_149227_j() != 0.2F) {
               return;
            }

            double particlePosX = ((S2APacketParticles)event.getPacket()).func_149220_d();
            double particlePosZ = ((S2APacketParticles)event.getPacket()).func_149225_f();
            EntityFishHook var10000 = playersFishHook;
            Intrinsics.checkNotNull(var10000);
            EntityFishHook var10002 = playersFishHook;
            Intrinsics.checkNotNull(var10002);
            if (var10000.func_70011_f(particlePosX, var10002.field_70163_u, particlePosZ) < 0.1D) {
               StringBuilder var10001 = (new StringBuilder()).append("Count: ").append(((S2APacketParticles)event.getPacket()).func_149222_k()).append(" Speed: ").append(((S2APacketParticles)event.getPacket()).func_149227_j()).append(" Distance: ");
               var10002 = playersFishHook;
               Intrinsics.checkNotNull(var10002);
               EntityFishHook var10004 = playersFishHook;
               Intrinsics.checkNotNull(var10004);
               this.printdev(var10001.append(var10002.func_70011_f(particlePosX, var10004.field_70163_u, particlePosZ)).toString());
               if (Config.INSTANCE.getSpecialTropyMode() == 1) {
                  this.reelIn(false);
                  int starterRodSlotIndex = Utils.INSTANCE.findItemInHotbar("Starter Lava Rod");
                  if (starterRodSlotIndex == -1) {
                     UChat.chat("§7[§aJooonAddons§7]§8 Haven't found Starter Lava Rod, make sure it's in the hotbar");
                     this.reelIn(false);
                  } else {
                     Multithreading.runAsync(FunnyFishing::onPacketRecive$lambda$0);
                  }
               } else if (Config.INSTANCE.getSpecialTropyMode() == 2) {
                  this.reelIn(false);
                  Multithreading.runAsync(FunnyFishing::onPacketRecive$lambda$1);
               } else {
                  this.reelIn(true);
               }
            }
         }

      }
   }

   @SubscribeEvent
   public final void onWorldLoad(@NotNull Load event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getFunnyFishing() && !pauseMacro) {
         UChat.chat("§7[§aJooonAddons§7]§8 Pausing Fishing Macro");
         pauseMacro = true;
      }

      rotateCooldown = 0L;
      lastTimeHitEntity = 0L;
      lastTimeSold = 0L;
      playersFishHook = null;
      placingTotem = false;
      goldenFishEntity = null;
      lookForGolenFish = false;
      enemyEntity = null;
   }

   @SubscribeEvent
   public final void onPlayerTick(@NotNull PlayerTickEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (this.getMc().field_71439_g != null) {
         StringBuilder var10001;
         if (collidingEntity != null) {
            var10001 = (new StringBuilder()).append("Colliding With: ");
            EntityLivingBase var10002 = collidingEntity;
            Intrinsics.checkNotNull(var10002);
            this.printdev(var10001.append(var10002.func_70005_c_()).toString());
            if (System.currentTimeMillis() / (long)1000 - lastTimeHitEntity > 2L) {
               collidingEntity = null;
               playersFishHook = null;
               this.reelIn(true);
               lastTimeHitEntity = System.currentTimeMillis() / (long)1000;
            }
         }

         if (Config.INSTANCE.getFunnyFishing()) {
            if (!placingTotem) {
               if (!killing) {
                  if (!pauseMacro) {
                     Utils var6;
                     Vec3 var11;
                     if (goldenFishEntity != null && System.currentTimeMillis() - lastGoldenFishLookTime > 9L) {
                        lastGoldenFishLookTime = System.currentTimeMillis();
                        if (System.currentTimeMillis() - lastTimeReeled > 5000L) {
                           this.printdev("No activity with Golden Fish, Recasting");
                           this.reelIn(true);
                        }

                        var6 = Utils.INSTANCE;
                        EntityArmorStand var8 = goldenFishEntity;
                        Intrinsics.checkNotNull(var8);
                        Vec3 var9 = var8.func_174791_d();
                        EntityArmorStand var13 = goldenFishEntity;
                        Intrinsics.checkNotNull(var13);
                        var9 = var9.func_72441_c(0.0D, (double)var13.func_70047_e(), 0.0D);
                        Intrinsics.checkNotNullExpressionValue(var9, "goldenFishEntity!!.posit…eyeHeight.toDouble(),0.0)");
                        var11 = this.getMc().field_71439_g.func_174791_d();
                        Intrinsics.checkNotNullExpressionValue(var11, "mc.thePlayer.positionVector");
                        Pair yawAndPitch = var6.VecToYawPitch(var9, var11);
                        this.printdev("Rotating to Golden Fish");
                        new PlayerRotation(new PlayerRotation.Rotation(((Number)yawAndPitch.getFirst()).floatValue(), ((Number)yawAndPitch.getSecond()).floatValue()), 10L);
                     } else {
                        if (Utils.INSTANCE.fullInventory() && System.currentTimeMillis() - lastTimeSold > 10000L && Config.INSTANCE.getFunnyFishingAutoSell()) {
                           lastTimeSold = System.currentTimeMillis();
                           this.getMc().field_71439_g.func_71165_d("/bz");
                           MinecraftForge.EVENT_BUS.register(new InstaSell());
                        }

                        if (System.currentTimeMillis() - lastTimeReeled > 30000L) {
                           this.reelIn(true);
                        }

                        if (this.getMc().field_71439_g.field_71071_by.field_70461_c != this.getFishingRod() && !killing) {
                           Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§8 Detected slot change, disabling");
                           Config.INSTANCE.setFunnyFishing(false);
                           playersFishHook = null;
                        } else {
                           if (playersFishHook != null) {
                              EntityFishHook var10000 = playersFishHook;
                              Intrinsics.checkNotNull(var10000);
                              if (var10000.field_70122_E) {
                                 var10000 = playersFishHook;
                                 Intrinsics.checkNotNull(var10000);
                                 if (var10000.field_70159_w == 0.0D) {
                                    var10000 = playersFishHook;
                                    Intrinsics.checkNotNull(var10000);
                                    if (var10000.field_70179_y == 0.0D) {
                                       UChat.chat("§7[§aJooonAddons§7]§8 Bobber was on the ground, recasting.");
                                       this.printdev("Should Recast");
                                       this.reelIn(true);
                                    }
                                 }
                              }
                           }

                           if (Config.INSTANCE.getFishingTotem() && !this.isTotemInRange() && System.currentTimeMillis() - lastTimeTotemPlaced > 10000L) {
                              this.printdev("Placing Totem");
                              this.placeTotem();
                              lastTimeTotemPlaced = System.currentTimeMillis();
                           }

                           if (Config.INSTANCE.getFishingRotate() && System.currentTimeMillis() - rotateCooldown >= 10000L) {
                              this.printdev("Rotating");
                              var10001 = (new StringBuilder()).append("Coords: ");
                              MovingObjectPosition var10 = mainLookAtBlock;
                              Intrinsics.checkNotNull(var10);
                              this.printdev(var10001.append(var10.func_178782_a().func_177958_n()).toString());
                              float pitchOffset = (float)(Math.random() * 5.0D + -2.5D);
                              float yawOffset = (float)(Math.random() * 5.0D + -2.5D);
                              var6 = Utils.INSTANCE;
                              MovingObjectPosition var10003 = mainLookAtBlock;
                              Intrinsics.checkNotNull(var10003);
                              int var12 = var10003.func_178782_a().func_177958_n() + 1;
                              MovingObjectPosition var10004 = mainLookAtBlock;
                              Intrinsics.checkNotNull(var10004);
                              int var14 = var10004.func_178782_a().func_177956_o();
                              MovingObjectPosition var10005 = mainLookAtBlock;
                              Intrinsics.checkNotNull(var10005);
                              BlockPos var7 = new BlockPos(var12, var14, var10005.func_178782_a().func_177952_p());
                              var11 = this.getMc().field_71439_g.func_174791_d();
                              Intrinsics.checkNotNullExpressionValue(var11, "mc.thePlayer.positionVector");
                              Pair yawAndPitch = var6.blockPosToYawPitch(var7, var11);
                              this.printdev("OffsetP: " + pitchOffset + " OffsetY: " + yawOffset);
                              new PlayerRotation(new PlayerRotation.Rotation(((Number)yawAndPitch.getFirst()).floatValue() + pitchOffset, ((Number)yawAndPitch.getSecond()).floatValue() + yawOffset), 600L);
                              rotateCooldown = System.currentTimeMillis();
                           }

                           if (Config.INSTANCE.getFishingMove() && System.currentTimeMillis() - movementCooldown >= 15000L && this.getMc().field_71462_r == null) {
                              this.movePlayer();
                              movementCooldown = System.currentTimeMillis();
                           }

                        }
                     }
                  }
               }
            }
         }
      }
   }

   private final boolean isTotemInRange() {
      Iterator var1 = this.getMc().field_71441_e.field_72996_f.iterator();

      while(var1.hasNext()) {
         Entity entity = (Entity)var1.next();
         if (!(this.getMc().field_71439_g.func_70032_d(entity) > 10.0F) && entity.func_145818_k_()) {
            String var10000 = entity.func_95999_t();
            Intrinsics.checkNotNullExpressionValue(var10000, "entity.customNameTag");
            if (StringsKt.contains$default((CharSequence)Utils.stripColor(var10000), (CharSequence)"Totem of Corruption", false, 2, (Object)null)) {
               return true;
            }
         }
      }

      return false;
   }

   public final boolean getPlacingTotem() {
      return placingTotem;
   }

   public final void setPlacingTotem(boolean <set-?>) {
      placingTotem = var1;
   }

   public final void placeTotem() {
      BlockPos blockForTotem = this.getBlocksForTotem();
      int totemSlot = Utils.INSTANCE.findItemInHotbar("Totem of Corruption");
      if (totemSlot == -1) {
         Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§8 Haven't Found Totem in your hotbar");
      } else if (blockForTotem == null) {
         Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§8 Haven't Found any proper blocks for totem");
      } else {
         placingTotem = true;
         Multithreading.runAsync(FunnyFishing::placeTotem$lambda$2);
      }
   }

   @Nullable
   public final BlockPos getBlocksForTotem() {
      for(int offsetX = -2; offsetX < 3; ++offsetX) {
         for(int offsetZ = -2; offsetZ < 3; ++offsetZ) {
            if (offsetX != 0 || offsetZ != 0) {
               BlockPos blockPos = new BlockPos(this.getMc().field_71439_g.field_70165_t + (double)offsetX, this.getMc().field_71439_g.field_70163_u - (double)1, this.getMc().field_71439_g.field_70161_v + (double)offsetZ);
               Block blockAtBlockPos = this.getMc().field_71441_e.func_175726_f(new BlockPos((Vec3i)blockPos)).func_177428_a(new BlockPos((Vec3i)blockPos));
               Block blockOverBlockPos = this.getMc().field_71441_e.func_175726_f(new BlockPos((Vec3i)blockPos)).func_177428_a(new BlockPos((Vec3i)blockPos.func_177982_a(0, 1, 0)));
               if (!Intrinsics.areEqual(blockAtBlockPos, Blocks.field_150350_a) && !Intrinsics.areEqual(blockAtBlockPos, Blocks.field_150355_j) && !Intrinsics.areEqual(blockAtBlockPos, Blocks.field_150358_i) && !Intrinsics.areEqual(blockAtBlockPos, Blocks.field_150353_l) && !Intrinsics.areEqual(blockAtBlockPos, Blocks.field_150356_k) && Intrinsics.areEqual(blockOverBlockPos, Blocks.field_150350_a)) {
                  this.printdev("Found Proper Block: " + blockPos + " Block Type: " + blockAtBlockPos);
                  return blockPos;
               }
            }
         }
      }

      return null;
   }

   private final void movePlayer() {
      long moveStartedTime = System.currentTimeMillis();
      Multithreading.runAsync(FunnyFishing::movePlayer$lambda$3);
   }

   private final void getMobsWithinAABB(Entity entity) {
      AxisAlignedBB aabb = new AxisAlignedBB(entity.field_70165_t + (double)6, entity.field_70163_u - 3.0D, entity.field_70161_v + (double)6, entity.field_70165_t - (double)6, entity.field_70163_u + (double)5, entity.field_70161_v - (double)6);
      int i = MathHelper.func_76128_c(aabb.field_72340_a - 1.0D) >> 4;
      int j = MathHelper.func_76128_c(aabb.field_72336_d + 1.0D) >> 4;
      int k = MathHelper.func_76128_c(aabb.field_72339_c - 1.0D) >> 4;
      int l = MathHelper.func_76128_c(aabb.field_72334_f + 1.0D) >> 4;
      int i1 = i;
      if (i <= j) {
         while(true) {
            int j1 = k;
            if (k <= l) {
               while(true) {
                  Chunk var10001 = this.getMc().field_71441_e.func_72964_e(i1, j1);
                  Intrinsics.checkNotNullExpressionValue(var10001, "mc.theWorld.getChunkFromChunkCoords(i1, j1)");
                  this.getMobsWithinAABBForEntity(var10001, entity, aabb);
                  if (j1 == l) {
                     break;
                  }

                  ++j1;
               }
            }

            if (i1 == j) {
               break;
            }

            ++i1;
         }
      }

   }

   private final void getMobsWithinAABBForEntity(Chunk chunk, Entity entityIn, AxisAlignedBB aabb) {
      ClassInheritanceMultiMap[] entityLists = chunk.func_177429_s();
      int i = MathHelper.func_76128_c((aabb.field_72338_b - World.MAX_ENTITY_RADIUS) / 16.0D);
      int j = MathHelper.func_76128_c((aabb.field_72337_e + World.MAX_ENTITY_RADIUS) / 16.0D);
      i = MathHelper.func_76125_a(i, 0, entityLists.length - 1);
      j = MathHelper.func_76125_a(j, 0, entityLists.length - 1);
      int k = i;
      int var8 = j;
      if (i <= j) {
         while(true) {
            if (!entityLists[k].isEmpty()) {
               Iterator var9 = entityLists[k].iterator();

               while(var9.hasNext()) {
                  Entity e = (Entity)var9.next();
                  if (enemyEntity != null) {
                     return;
                  }

                  if (e.func_174813_aQ().func_72326_a(aabb) && e instanceof EntityArmorStand && e.func_145818_k_()) {
                     String var10000 = ((EntityArmorStand)e).func_95999_t();
                     Intrinsics.checkNotNullExpressionValue(var10000, "e.customNameTag");
                     if (!StringsKt.contains$default((CharSequence)Utils.stripColor(var10000), (CharSequence)"0/", false, 2, (Object)null)) {
                        var10000 = ((EntityArmorStand)e).func_95999_t();
                        Intrinsics.checkNotNullExpressionValue(var10000, "e.customNameTag");
                        if (StringsKt.contains$default((CharSequence)Utils.stripColor(var10000), (CharSequence)"[Lv", false, 2, (Object)null)) {
                           var10000 = ((EntityArmorStand)e).func_95999_t();
                           Intrinsics.checkNotNullExpressionValue(var10000, "e.customNameTag");
                           if (StringsKt.contains$default((CharSequence)var10000, (CharSequence)"❤", false, 2, (Object)null)) {
                              enemyEntity = (EntityArmorStand)e;
                              this.printdev("Detected Enemy In Range: " + ((EntityArmorStand)e).func_95999_t());
                              killing = true;
                              Multithreading.runAsync(FunnyFishing::getMobsWithinAABBForEntity$lambda$4);
                              return;
                           }
                        }
                     }
                  }
               }
            }

            if (k == var8) {
               break;
            }

            ++k;
         }
      }

   }

   @SubscribeEvent
   public final void onWorldRender(@NotNull RenderWorldLastEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getFunnyFishing()) {
         if (Config.INSTANCE.getFocusOnGoldenFish() && lookForGolenFish && goldenFishEntity == null) {
            this.printdev("Looking for Golden Fish Entity");
            Iterator var2 = this.getMc().field_71441_e.field_72996_f.iterator();

            while(var2.hasNext()) {
               Entity entity = (Entity)var2.next();
               if (entity instanceof EntityArmorStand && !(this.getMc().field_71439_g.func_70032_d(entity) > 7.0F) && ((EntityArmorStand)entity).func_82169_q(3) != null && ((EntityArmorStand)entity).func_82169_q(0) == null && ((EntityArmorStand)entity).func_82169_q(1) == null && ((EntityArmorStand)entity).func_82169_q(2) == null) {
                  goldenFishEntity = (EntityArmorStand)entity;
                  this.printdev("Golden Fish Entity Found");
               }
            }
         }

         if (enemyEntity != null) {
            EntityArmorStand var10000 = enemyEntity;
            Intrinsics.checkNotNull(var10000);
            if (!var10000.func_70089_S()) {
               enemyEntity = null;
            }
         }

         if (enemyEntity != null) {
            EntityPlayerSP var4 = this.getMc().field_71439_g;
            EntityArmorStand var10001 = enemyEntity;
            Intrinsics.checkNotNull(var10001);
            if (var4.func_70032_d((Entity)var10001) > 6.0F) {
               enemyEntity = null;
            }
         }

         if (Config.INSTANCE.getFishingKilling() == 2 && enemyEntity == null) {
            EntityPlayerSP var6 = this.getMc().field_71439_g;
            Intrinsics.checkNotNullExpressionValue(var6, "mc.thePlayer");
            this.getMobsWithinAABB((Entity)var6);
            if (lastTimeHitEntity != 0L && System.currentTimeMillis() - lastTimeHitEntity > 2000L) {
               lastTimeHitEntity = 0L;
               Multithreading.runAsync(FunnyFishing::onWorldRender$lambda$5);
            }
         }

         RenderUtils var5 = RenderUtils.INSTANCE;
         MovingObjectPosition var7 = mainLookAtBlock;
         Intrinsics.checkNotNull(var7);
         BlockPos var8 = var7.func_178782_a();
         Intrinsics.checkNotNullExpressionValue(var8, "mainLookAtBlock!!.blockPos");
         var5.drawFishingBox(var8, new Color(236, 204, 8, 255), event.partialTicks);
      }
   }

   public final void reelIn(boolean recast) {
      playersFishHook = null;
      if (!BarnFishingTimer.INSTANCE.getTimerRunning()) {
         BarnFishingTimer.INSTANCE.setTimerRunning(true);
         BarnFishingTimer.INSTANCE.setTimerStartTime(System.currentTimeMillis());
      }

      lastTimeReeled = System.currentTimeMillis();
      Multithreading.runAsync(FunnyFishing::reelIn$lambda$6);
   }

   private final int getFireVeil() {
      for(int i = 0; i < 8; ++i) {
         ItemStack var10000 = this.getMc().field_71439_g.field_71071_by.field_70462_a[i];
         if (var10000 != null) {
            ItemStack item = var10000;
            String var3 = item.func_82833_r();
            Intrinsics.checkNotNullExpressionValue(var3, "item.displayName");
            if (StringsKt.contains$default((CharSequence)Utils.stripColor(var3), (CharSequence)"Fire Veil Wand", false, 2, (Object)null)) {
               return i;
            }
         }
      }

      return -1;
   }

   public final boolean getKilling() {
      return killing;
   }

   public final void setKilling(boolean <set-?>) {
      killing = var1;
   }

   @SubscribeEvent
   public final void onChat(@NotNull ClientChatReceivedEvent event) {
      String var10000;
      label62: {
         Intrinsics.checkNotNullParameter(event, "event");
         var10000 = event.message.func_150260_c();
         Intrinsics.checkNotNullExpressionValue(var10000, "event.message.unformattedText");
         if (!Intrinsics.areEqual(Utils.stripColor(var10000), "You were spawned in Limbo.")) {
            var10000 = event.message.func_150260_c();
            Intrinsics.checkNotNullExpressionValue(var10000, "event.message.unformattedText");
            if (!Intrinsics.areEqual(Utils.stripColor(var10000), "A kick occurred in your connection, so you were put in the SkyBlock lobby!")) {
               break label62;
            }
         }

         Multithreading.runAsync(FunnyFishing::onChat$lambda$7);
      }

      if (Config.INSTANCE.getFunnyFishing()) {
         var10000 = event.message.func_150260_c();
         Intrinsics.checkNotNullExpressionValue(var10000, "event.message.unformattedText");
         if (StringsKt.startsWith$default(Utils.stripColor(var10000), " ☠ You were killed by", false, 2, (Object)null)) {
            Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§8 You died, applying failsafe");
            Config.INSTANCE.setFunnyFishing(false);
            rotateCooldown = 0L;
            lastTimeHitEntity = 0L;
            lastTimeSold = 0L;
            playersFishHook = null;
            placingTotem = false;
            goldenFishEntity = null;
            lookForGolenFish = false;
            enemyEntity = null;
         }

         var10000 = event.message.func_150260_c();
         Intrinsics.checkNotNullExpressionValue(var10000, "event.message.unformattedText");
         if (Intrinsics.areEqual(Utils.stripColor(var10000), "You spot a Golden Fish surface from beneath the lava!")) {
            this.printdev("Start Looking For Golden Fish");
            lookForGolenFish = true;
            this.reelIn(false);
            Multithreading.runAsync(FunnyFishing::onChat$lambda$8);
         }

         var10000 = event.message.func_150260_c();
         Intrinsics.checkNotNullExpressionValue(var10000, "event.message.unformattedText");
         if (StringsKt.startsWith$default(Utils.stripColor(var10000), "The Golden Fish is weak!", false, 2, (Object)null)) {
            this.reelIn(false);
         }

         var10000 = event.message.func_150260_c();
         Intrinsics.checkNotNullExpressionValue(var10000, "event.message.unformattedText");
         if (StringsKt.startsWith$default(Utils.stripColor(var10000), "TROPHY FISH! You caught a Golden Fish", false, 2, (Object)null)) {
            goldenFishEntity = null;
            lookForGolenFish = false;
            Multithreading.runAsync(FunnyFishing::onChat$lambda$9);
         }

         var10000 = event.message.func_150260_c();
         Intrinsics.checkNotNullExpressionValue(var10000, "event.message.unformattedText");
         if (Intrinsics.areEqual(Utils.stripColor(var10000), "The Golden Fish escapes your hook but looks weakened.") && Config.INSTANCE.getFocusOnGoldenFish()) {
            this.printdev("Recasting on Golden Fish");
            this.reelIn(false);
         }

         if (Config.INSTANCE.getFishingKilling() != 0) {
            Iterator var2 = FishingTracker.INSTANCE.getSeaCreatureMessages().entrySet().iterator();

            while(var2.hasNext()) {
               Entry mobMessage = (Entry)var2.next();
               var10000 = event.message.func_150260_c();
               Intrinsics.checkNotNullExpressionValue(var10000, "event.message.unformattedText");
               var10000 = Utils.stripColor(var10000).toLowerCase(Locale.ROOT);
               Intrinsics.checkNotNullExpressionValue(var10000, "this as java.lang.String).toLowerCase(Locale.ROOT)");
               String var10001 = ((String)mobMessage.getKey()).toLowerCase(Locale.ROOT);
               Intrinsics.checkNotNullExpressionValue(var10001, "this as java.lang.String).toLowerCase(Locale.ROOT)");
               if (Intrinsics.areEqual(var10000, var10001)) {
                  switch(Config.INSTANCE.getFishingKilling()) {
                  case 1:
                     if (this.getFireVeil() == -1) {
                        Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§8 Haven't Found Fire Veil Wand!");
                     } else {
                        killing = true;
                        Multithreading.runAsync(FunnyFishing::onChat$lambda$10);
                     }
                  case 2:
                  default:
                     break;
                  case 3:
                     if (Utils.INSTANCE.findItemInHotbar("Midas Staff") == -1) {
                        Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§8 Haven't Found Midas Staff!");
                     } else {
                        killing = true;
                        Multithreading.runAsync(FunnyFishing::onChat$lambda$11);
                     }
                  }
               }
            }

         }
      }
   }

   private static final void onPacketRecive$lambda$0() {
      int normalRodSlot = -1;
      if (Utils.INSTANCE.findItemInHotbar("Magma Rod") == -1) {
         if (Utils.INSTANCE.findItemInHotbar("Inferno Rod") == -1) {
            if (Utils.INSTANCE.findItemInHotbar("Hellfire Rod") == -1) {
               UChat.chat("§7[§aJooonAddons§7]§8 Haven't found Lava Rod that's not Starter Lava Rod in your hotbar");
            } else {
               normalRodSlot = Utils.INSTANCE.findItemInHotbar("Hellfire Rod");
            }
         } else {
            normalRodSlot = Utils.INSTANCE.findItemInHotbar("Inferno Rod");
         }
      } else {
         normalRodSlot = Utils.INSTANCE.findItemInHotbar("Magma Rod");
      }

      Thread.sleep(300L);
      Utils.INSTANCE.switchToItemInHotbar(normalRodSlot);
      Thread.sleep(1500L);
      INSTANCE.reelIn(false);
      Thread.sleep(1500L);
      Utils.INSTANCE.switchToItemInHotbar(Utils.INSTANCE.findItemInHotbar("Starter Lava Rod"));
   }

   private static final void onPacketRecive$lambda$1() {
      INSTANCE.getMc().field_71439_g.func_71165_d("/wardrobe");
      MinecraftForge.EVENT_BUS.register(new WardrobeEquipper("Ember Helmet"));
      Thread.sleep(2000L);
      INSTANCE.reelIn(false);
      Thread.sleep(20000L);
      if (Config.INSTANCE.getFunnyFishing()) {
         INSTANCE.getMc().field_71439_g.func_71165_d("/wardrobe");
         MinecraftForge.EVENT_BUS.register(new WardrobeEquipper("Hunter"));
      }

   }

   private static final void placeTotem$lambda$2(int $totemSlot, BlockPos $blockForTotem) {
      INSTANCE.getMc().field_71439_g.field_71071_by.field_70461_c = $totemSlot;
      Utils var10000 = Utils.INSTANCE;
      Vec3 var10001 = new Vec3($blockForTotem.func_177958_n() > 0 ? (double)$blockForTotem.func_177958_n() - 0.5D : (double)$blockForTotem.func_177958_n() + 0.5D, (double)$blockForTotem.func_177956_o(), $blockForTotem.func_177952_p() > 0 ? (double)$blockForTotem.func_177952_p() - 0.5D : (double)$blockForTotem.func_177952_p() + 0.5D);
      Vec3 var10002 = INSTANCE.getMc().field_71439_g.func_174791_d();
      Intrinsics.checkNotNullExpressionValue(var10002, "mc.thePlayer.positionVector");
      Pair yawAndPitch = var10000.VecToYawPitch(var10001, var10002);
      new PlayerRotation(new PlayerRotation.Rotation(((Number)yawAndPitch.getFirst()).floatValue(), ((Number)yawAndPitch.getSecond()).floatValue()), 600L);
      Thread.sleep(500L);
      MovingObjectPosition var4 = INSTANCE.getMc().field_71439_g.func_174822_a(5.0D, 1.0F);
      if (var4 != null) {
         MovingObjectPosition raytrace = var4;
         INSTANCE.getMc().func_147114_u().func_147297_a((Packet)(new C08PacketPlayerBlockPlacement(raytrace.func_178782_a(), 1, INSTANCE.getMc().field_71439_g.func_70694_bm(), 0.5F, 1.0F, 0.5F)));
         Thread.sleep(200L);
         INSTANCE.getMc().field_71439_g.field_71071_by.field_70461_c = INSTANCE.getFishingRod();
         var10000 = Utils.INSTANCE;
         FunnyFishing var6 = INSTANCE;
         MovingObjectPosition var7 = mainLookAtBlock;
         Intrinsics.checkNotNull(var7);
         BlockPos var8 = var7.func_178782_a();
         Intrinsics.checkNotNullExpressionValue(var8, "mainLookAtBlock!!.blockPos");
         var10002 = INSTANCE.getMc().field_71439_g.func_174791_d();
         Intrinsics.checkNotNullExpressionValue(var10002, "mc.thePlayer.positionVector");
         yawAndPitch = var10000.blockPosToYawPitch(var8, var10002);
         new PlayerRotation(new PlayerRotation.Rotation(((Number)yawAndPitch.getFirst()).floatValue(), ((Number)yawAndPitch.getSecond()).floatValue()), 500L);
         Thread.sleep(1500L);
         INSTANCE.getMc().field_71442_b.func_78769_a((EntityPlayer)INSTANCE.getMc().field_71439_g, (World)INSTANCE.getMc().field_71441_e, INSTANCE.getMc().field_71439_g.func_70694_bm());
         FunnyFishing var5 = INSTANCE;
         placingTotem = false;
      }
   }

   private static final void movePlayer$lambda$3(long $moveStartedTime) {
      FunnyFishing var10000 = INSTANCE;
      if (startingPosition == null) {
         INSTANCE.printdev("Starting Postion Is Null");
      } else {
         int decreaseXButton = 0;
         int increaseXButton = 0;
         int decreaseZButton = 0;
         int increadeZButton = 0;
         String var6 = INSTANCE.getMc().field_71439_g.func_174811_aO().name();
         switch(var6.hashCode()) {
         case 2120701:
            if (var6.equals("EAST")) {
               decreaseXButton = 31;
               increaseXButton = 17;
               decreaseZButton = 30;
               increadeZButton = 32;
            }
            break;
         case 2660783:
            if (var6.equals("WEST")) {
               decreaseXButton = 17;
               increaseXButton = 31;
               decreaseZButton = 32;
               increadeZButton = 30;
            }
            break;
         case 74469605:
            if (var6.equals("NORTH")) {
               decreaseXButton = 30;
               increaseXButton = 32;
               decreaseZButton = 17;
               increadeZButton = 31;
            }
            break;
         case 79090093:
            if (var6.equals("SOUTH")) {
               decreaseXButton = 32;
               increaseXButton = 30;
               decreaseZButton = 31;
               increadeZButton = 17;
            }
         }

         int sneakKey = INSTANCE.getMc().field_71474_y.field_74311_E.func_151463_i();
         KeyBinding.func_74510_a(sneakKey, true);
         EntityPlayerSP var13 = INSTANCE.getMc().field_71439_g;
         FunnyFishing var10001 = INSTANCE;
         Vec3 var15 = startingPosition;
         Intrinsics.checkNotNull(var15);
         double var16 = var15.field_72450_a;
         FunnyFishing var10002 = INSTANCE;
         Vec3 var17 = startingPosition;
         Intrinsics.checkNotNull(var17);
         double var19 = var17.field_72448_b;
         FunnyFishing var10003 = INSTANCE;
         Vec3 var20 = startingPosition;
         Intrinsics.checkNotNull(var20);
         double var14;
         if (var13.func_70011_f(var16, var19, var20.field_72449_c) > 0.4D) {
            INSTANCE.printdev("Coming Back to Main");

            do {
               var13 = INSTANCE.getMc().field_71439_g;
               var10001 = INSTANCE;
               var15 = startingPosition;
               Intrinsics.checkNotNull(var15);
               var16 = var15.field_72450_a;
               var10002 = INSTANCE;
               var17 = startingPosition;
               Intrinsics.checkNotNull(var17);
               var19 = var17.field_72448_b;
               var10003 = INSTANCE;
               var20 = startingPosition;
               Intrinsics.checkNotNull(var20);
               if (!(var13.func_70011_f(var16, var19, var20.field_72449_c) > 0.3D) || System.currentTimeMillis() - $moveStartedTime >= 2000L) {
                  break;
               }

               var14 = INSTANCE.getMc().field_71439_g.func_174791_d().field_72450_a;
               var10001 = INSTANCE;
               var15 = startingPosition;
               Intrinsics.checkNotNull(var15);
               if (var14 > var15.field_72450_a) {
                  KeyBinding.func_74510_a(decreaseXButton, true);
               } else {
                  var14 = INSTANCE.getMc().field_71439_g.func_174791_d().field_72450_a;
                  var10001 = INSTANCE;
                  var15 = startingPosition;
                  Intrinsics.checkNotNull(var15);
                  if (var14 < var15.field_72450_a) {
                     KeyBinding.func_74510_a(increaseXButton, true);
                  }
               }

               var14 = INSTANCE.getMc().field_71439_g.func_174791_d().field_72449_c;
               var10001 = INSTANCE;
               var15 = startingPosition;
               Intrinsics.checkNotNull(var15);
               if (var14 > var15.field_72449_c) {
                  KeyBinding.func_74510_a(decreaseZButton, true);
               } else {
                  var14 = INSTANCE.getMc().field_71439_g.func_174791_d().field_72449_c;
                  var10001 = INSTANCE;
                  var15 = startingPosition;
                  Intrinsics.checkNotNull(var15);
                  if (var14 < var15.field_72449_c) {
                     KeyBinding.func_74510_a(increadeZButton, true);
                  }
               }
            } while(Config.INSTANCE.getFunnyFishing());
         } else {
            INSTANCE.printdev("Moving Randomly");
            double xOffset = Math.random() * (double)2 + (double)-1;
            double zOffset = Math.random() * (double)2 + (double)-1;
            var10003 = INSTANCE;
            Chunk var18 = INSTANCE.getMc().field_71441_e.func_175726_f(new BlockPos(startingPosition));
            var10003 = INSTANCE;
            var20 = startingPosition;
            Intrinsics.checkNotNull(var20);

            for(Block targetBlock = var18.func_177428_a(new BlockPos(var20.func_178787_e(new Vec3(xOffset, -1.0D, zOffset)))); (Intrinsics.areEqual(targetBlock, Blocks.field_150350_a) || targetBlock instanceof BlockDynamicLiquid || targetBlock instanceof BlockStaticLiquid) && Config.INSTANCE.getFunnyFishing() && System.currentTimeMillis() - $moveStartedTime < 2000L; targetBlock = var18.func_177428_a(new BlockPos(var20.func_178787_e(new Vec3(xOffset, -1.0D, zOffset))))) {
               xOffset = Math.random() * (double)2 + (double)-1;
               zOffset = Math.random() * (double)2 + (double)-1;
               var10003 = INSTANCE;
               var18 = INSTANCE.getMc().field_71441_e.func_175726_f(new BlockPos(startingPosition));
               var10003 = INSTANCE;
               var20 = startingPosition;
               Intrinsics.checkNotNull(var20);
            }

            var10000 = INSTANCE;
            StringBuilder var24 = (new StringBuilder()).append("New Location is: ");
            var10002 = INSTANCE;
            var17 = startingPosition;
            Intrinsics.checkNotNull(var17);
            var24 = var24.append(var17.field_72450_a + xOffset).append(' ');
            var10002 = INSTANCE;
            var17 = startingPosition;
            Intrinsics.checkNotNull(var17);
            var24 = var24.append(var17.field_72448_b).append(' ');
            var10002 = INSTANCE;
            var17 = startingPosition;
            Intrinsics.checkNotNull(var17);
            var10000.printdev(var24.append(var17.field_72449_c + zOffset).toString());
            var10000 = INSTANCE;
            var24 = (new StringBuilder()).append("Distance: ");
            EntityPlayerSP var25 = INSTANCE.getMc().field_71439_g;
            var10003 = INSTANCE;
            var20 = startingPosition;
            Intrinsics.checkNotNull(var20);
            double var26 = var20.field_72450_a + xOffset;
            FunnyFishing var10004 = INSTANCE;
            Vec3 var21 = startingPosition;
            Intrinsics.checkNotNull(var21);
            double var22 = var21.field_72448_b;
            FunnyFishing var10005 = INSTANCE;
            Vec3 var23 = startingPosition;
            Intrinsics.checkNotNull(var23);
            var10000.printdev(var24.append(var25.func_70011_f(var26, var22, var23.field_72449_c + zOffset)).toString());

            do {
               var13 = INSTANCE.getMc().field_71439_g;
               var10001 = INSTANCE;
               var15 = startingPosition;
               Intrinsics.checkNotNull(var15);
               var16 = var15.field_72450_a + xOffset;
               var10002 = INSTANCE;
               var17 = startingPosition;
               Intrinsics.checkNotNull(var17);
               var19 = var17.field_72448_b;
               var10003 = INSTANCE;
               var20 = startingPosition;
               Intrinsics.checkNotNull(var20);
               if (!(var13.func_70011_f(var16, var19, var20.field_72449_c + zOffset) > 0.2D) || System.currentTimeMillis() - $moveStartedTime >= 2000L) {
                  break;
               }

               var14 = INSTANCE.getMc().field_71439_g.func_174791_d().field_72450_a;
               var10001 = INSTANCE;
               var15 = startingPosition;
               Intrinsics.checkNotNull(var15);
               if (var14 > var15.field_72450_a + xOffset) {
                  KeyBinding.func_74510_a(decreaseXButton, true);
               } else {
                  var14 = INSTANCE.getMc().field_71439_g.func_174791_d().field_72450_a;
                  var10001 = INSTANCE;
                  var15 = startingPosition;
                  Intrinsics.checkNotNull(var15);
                  if (var14 < var15.field_72450_a + xOffset) {
                     KeyBinding.func_74510_a(increaseXButton, true);
                  }
               }

               var14 = INSTANCE.getMc().field_71439_g.func_174791_d().field_72449_c;
               var10001 = INSTANCE;
               var15 = startingPosition;
               Intrinsics.checkNotNull(var15);
               if (var14 > var15.field_72449_c + zOffset) {
                  KeyBinding.func_74510_a(decreaseZButton, true);
               } else {
                  var14 = INSTANCE.getMc().field_71439_g.func_174791_d().field_72449_c;
                  var10001 = INSTANCE;
                  var15 = startingPosition;
                  Intrinsics.checkNotNull(var15);
                  if (var14 < var15.field_72449_c + zOffset) {
                     KeyBinding.func_74510_a(increadeZButton, true);
                  }
               }
            } while(Config.INSTANCE.getFunnyFishing());
         }

         KeyBinding.func_74510_a(17, false);
         KeyBinding.func_74510_a(31, false);
         KeyBinding.func_74510_a(30, false);
         KeyBinding.func_74510_a(32, false);
         Thread.sleep((long)(Math.random() * (double)100 + (double)400));
         KeyBinding.func_74510_a(sneakKey, false);
      }
   }

   private static final void getMobsWithinAABBForEntity$lambda$4(Entity $e) {
      if (Config.INSTANCE.getFunnyFishingAutoHook()) {
         String var10000 = ((EntityArmorStand)$e).func_95999_t();
         Intrinsics.checkNotNullExpressionValue(var10000, "e.customNameTag");
         if (StringsKt.contains$default((CharSequence)var10000, (CharSequence)"Lava Flame", false, 2, (Object)null) && !seenFlames.contains(((EntityArmorStand)$e).func_145782_y())) {
            seenFlames.add(((EntityArmorStand)$e).func_145782_y());
            INSTANCE.printdev("Its Lava Flame");
            int grappleshotSlotIndex = Utils.INSTANCE.findItemInHotbar("Moody Grappleshot");
            if (grappleshotSlotIndex == -1) {
               Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§8 Haven't found Grappleshot for Lava Flame, skipping");
            } else {
               INSTANCE.printdev("Attempting to hook it");
               INSTANCE.getMc().field_71439_g.field_71071_by.field_70461_c = grappleshotSlotIndex;
               Utils var9 = Utils.INSTANCE;
               Vec3 var10001 = ((EntityArmorStand)$e).func_174791_d();
               Intrinsics.checkNotNullExpressionValue(var10001, "e.positionVector");
               Vec3 var10002 = INSTANCE.getMc().field_71439_g.func_174791_d();
               Intrinsics.checkNotNullExpressionValue(var10002, "mc.thePlayer.positionVector");
               Pair yawAndPitch = var9.VecToYawPitch(var10001, var10002);
               new PlayerRotation(new PlayerRotation.Rotation(((Number)yawAndPitch.getFirst()).floatValue(), ((Number)yawAndPitch.getSecond()).floatValue()), 300L);
               Thread.sleep(350L);
               INSTANCE.getMc().field_71442_b.func_78769_a((EntityPlayer)INSTANCE.getMc().field_71439_g, (World)INSTANCE.getMc().field_71441_e, INSTANCE.getMc().field_71439_g.func_70694_bm());
               Thread.sleep(100L);
            }
         }
      }

      String[] var6 = new String[]{"Scylla", "Astraea", "Hyperion", "Valkyrie"};
      List witherBlades = CollectionsKt.listOf(var6);
      int bladeSlotIndex = -1;
      Iterator var3 = witherBlades.iterator();

      while(var3.hasNext()) {
         String blade = (String)var3.next();
         if (Utils.INSTANCE.findItemInHotbar(blade) != -1) {
            bladeSlotIndex = Utils.INSTANCE.findItemInHotbar(blade);
            break;
         }
      }

      FunnyFishing var10;
      if (bladeSlotIndex == -1) {
         Utils.INSTANCE.addMessage("§7[§aJooonAddons§7]§8 Haven't Found Wither Blade!");
         var10 = INSTANCE;
         killing = false;
      } else {
         long lastTimeAttacked = 0L;
         Thread.sleep((long)Config.INSTANCE.getFunnyFishingAutoKillingDelay());
         INSTANCE.printdev("Changing slot to Blade");
         INSTANCE.getMc().field_71439_g.field_71071_by.field_70461_c = bladeSlotIndex;
         Thread.sleep(100L);
         INSTANCE.printdev("Looking Down");
         new PlayerRotation(new PlayerRotation.Rotation(INSTANCE.getMc().field_71439_g.field_70177_z, 90.0F), 300L);
         Thread.sleep(400L);
         INSTANCE.printdev("Loop Start");

         while(true) {
            var10 = INSTANCE;
            if (!killing || !Config.INSTANCE.getFunnyFishing() || enemyEntity == null) {
               break;
            }

            EntityArmorStand var11 = enemyEntity;
            Intrinsics.checkNotNull(var11);
            if (!var11.func_70089_S()) {
               break;
            }

            if (System.currentTimeMillis() - lastTimeAttacked > 160L) {
               INSTANCE.printdev("Attacking");
               INSTANCE.getMc().field_71442_b.func_78769_a((EntityPlayer)INSTANCE.getMc().field_71439_g, (World)INSTANCE.getMc().field_71441_e, INSTANCE.getMc().field_71439_g.func_70694_bm());
               lastTimeAttacked = System.currentTimeMillis();
            }
         }

         INSTANCE.printdev("Loop End");
         var10 = INSTANCE;
         lastTimeHitEntity = System.currentTimeMillis();
      }
   }

   private static final void onWorldRender$lambda$5() {
      Thread.sleep(400L);
      INSTANCE.printdev("Looking At Old Location");
      Utils var10000 = Utils.INSTANCE;
      FunnyFishing var10003 = INSTANCE;
      MovingObjectPosition var3 = mainLookAtBlock;
      Intrinsics.checkNotNull(var3);
      int var4 = var3.func_178782_a().func_177958_n() + 1;
      FunnyFishing var10004 = INSTANCE;
      MovingObjectPosition var5 = mainLookAtBlock;
      Intrinsics.checkNotNull(var5);
      int var6 = var5.func_178782_a().func_177956_o();
      FunnyFishing var10005 = INSTANCE;
      MovingObjectPosition var1 = mainLookAtBlock;
      Intrinsics.checkNotNull(var1);
      BlockPos var10001 = new BlockPos(var4, var6, var1.func_178782_a().func_177952_p());
      Vec3 var10002 = INSTANCE.getMc().field_71439_g.func_174791_d();
      Intrinsics.checkNotNullExpressionValue(var10002, "mc.thePlayer.positionVector");
      Pair yawAndPitch = var10000.blockPosToYawPitch(var10001, var10002);
      new PlayerRotation(new PlayerRotation.Rotation(((Number)yawAndPitch.getFirst()).floatValue(), ((Number)yawAndPitch.getSecond()).floatValue()), 600L);
      Thread.sleep(100L);
      INSTANCE.printdev("Changing slot to Rod");
      INSTANCE.getMc().field_71439_g.field_71071_by.field_70461_c = INSTANCE.getFishingRod();
      Thread.sleep(500L);
      INSTANCE.printdev("Casting");
      INSTANCE.getMc().field_71442_b.func_78769_a((EntityPlayer)INSTANCE.getMc().field_71439_g, (World)INSTANCE.getMc().field_71441_e, INSTANCE.getMc().field_71439_g.func_70694_bm());
      FunnyFishing var2 = INSTANCE;
      killing = false;
      INSTANCE.printdev("Disabling Killing Variable");
   }

   private static final void reelIn$lambda$6(boolean $recast) {
      long randomCooldown = (long)(Math.random() * (double)50 + (double)50);
      INSTANCE.printdev(String.valueOf(randomCooldown));
      Thread.sleep(randomCooldown);
      INSTANCE.getMc().field_71442_b.func_78769_a((EntityPlayer)INSTANCE.getMc().field_71439_g, (World)INSTANCE.getMc().field_71441_e, INSTANCE.getMc().field_71439_g.func_70694_bm());
      if ($recast) {
         randomCooldown = (long)(Math.random() * (double)(Config.INSTANCE.getFishingRecastDelay() + 25 - (Config.INSTANCE.getFishingRecastDelay() - 25)) + (double)Config.INSTANCE.getFishingRecastDelay() - (double)25);
         INSTANCE.printdev(String.valueOf(randomCooldown));
         Thread.sleep(randomCooldown);
         INSTANCE.getMc().field_71442_b.func_78769_a((EntityPlayer)INSTANCE.getMc().field_71439_g, (World)INSTANCE.getMc().field_71441_e, INSTANCE.getMc().field_71439_g.func_70694_bm());
         FunnyFishing var10000 = INSTANCE;
         collidingEntity = null;
      }
   }

   private static final void onChat$lambda$7() {
      Thread.sleep(1000L);
      INSTANCE.getMc().field_71439_g.func_71165_d("/lobby");
      Thread.sleep(2000L);

      do {
         INSTANCE.getMc().field_71439_g.func_71165_d("/play sb");
         Thread.sleep(10000L);
      } while(!SBInfo.INSTANCE.getOnSkyblock());

   }

   private static final void onChat$lambda$8() {
      Thread.sleep(1000L);
      INSTANCE.printdev("Attempting To Hook Golden Fish");
      INSTANCE.reelIn(false);
   }

   private static final void onChat$lambda$9() {
      Thread.sleep(2000L);
      INSTANCE.printdev("Recasting into lava");
      INSTANCE.reelIn(false);
   }

   private static final void onChat$lambda$10() {
      Thread.sleep((long)Config.INSTANCE.getFunnyFishingAutoKillingDelay());
      INSTANCE.getMc().field_71439_g.field_71071_by.field_70461_c = INSTANCE.getFireVeil();
      Thread.sleep(150L);
      INSTANCE.getMc().field_71442_b.func_78769_a((EntityPlayer)INSTANCE.getMc().field_71439_g, (World)INSTANCE.getMc().field_71441_e, INSTANCE.getMc().field_71439_g.func_70694_bm());
      Thread.sleep(150L);
      INSTANCE.getMc().field_71439_g.field_71071_by.field_70461_c = INSTANCE.getFishingRod();
      Thread.sleep(150L);
      INSTANCE.getMc().field_71442_b.func_78769_a((EntityPlayer)INSTANCE.getMc().field_71439_g, (World)INSTANCE.getMc().field_71441_e, INSTANCE.getMc().field_71439_g.func_70694_bm());
      FunnyFishing var10000 = INSTANCE;
      killing = false;
   }

   private static final void onChat$lambda$11() {
      Thread.sleep((long)Config.INSTANCE.getFunnyFishingAutoKillingDelay());
      INSTANCE.getMc().field_71439_g.field_71071_by.field_70461_c = Utils.INSTANCE.findItemInHotbar("Midas Staff");
      Thread.sleep(100L);
      INSTANCE.getMc().field_71442_b.func_78769_a((EntityPlayer)INSTANCE.getMc().field_71439_g, (World)INSTANCE.getMc().field_71441_e, INSTANCE.getMc().field_71439_g.func_70694_bm());
      Thread.sleep(100L);
      INSTANCE.getMc().field_71439_g.field_71071_by.field_70461_c = INSTANCE.getFishingRod();
      Thread.sleep(100L);
      INSTANCE.getMc().field_71442_b.func_78769_a((EntityPlayer)INSTANCE.getMc().field_71439_g, (World)INSTANCE.getMc().field_71441_e, INSTANCE.getMc().field_71439_g.func_70694_bm());
      FunnyFishing var10000 = INSTANCE;
      killing = false;
   }
}
