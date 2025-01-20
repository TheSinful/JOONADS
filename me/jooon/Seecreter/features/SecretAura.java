package me.jooon.Seecreter.features;

import cc.polyfrost.oneconfig.events.EventManager;
import cc.polyfrost.oneconfig.events.event.ReceivePacketEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import com.mojang.authlib.GameProfile;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import me.jooon.Seecreter.config.SeecreterConfig;
import me.jooon.Seecreter.utils.BlockUtils;
import me.jooon.Seecreter.utils.ChatUtils;
import me.jooon.Seecreter.utils.DungeonLocationUtils;
import me.jooon.Seecreter.utils.LocationUtils;
import me.jooon.Seecreter.utils.PacketUtils;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockLever.EnumOrientation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S22PacketMultiBlockChange.BlockUpdateData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class SecretAura {
   private static final List<BlockPos> blocksDone = new LinkedList();
   private static final Map<BlockPos, Long> blocksCooldown = new HashMap();
   private static boolean redstoneKey = false;
   private static int prevSlot = -1;

   public SecretAura() {
      EventManager.INSTANCE.register(this);
   }

   @SubscribeEvent
   public void onTick(ClientTickEvent event) {
      if (SeecreterConfig.secretAuraEnabled) {
         if (event.phase == Phase.START) {
            if (LocationUtils.getCurrentLocation() == LocationUtils.LocationType.DUNGEONS || SeecreterConfig.secretAuraNotDungeon) {
               Minecraft mc = Minecraft.func_71410_x();
               EntityPlayerSP player = mc.field_71439_g;
               if (player != null) {
                  WorldClient world = mc.field_71441_e;
                  if (world != null) {
                     Vec3 eyePos = player.func_174824_e(0.0F);
                     BlockPos blockPos1 = new BlockPos(eyePos.field_72450_a - (double)SeecreterConfig.secretAuraRange, eyePos.field_72448_b - (double)SeecreterConfig.secretAuraRange, eyePos.field_72449_c - (double)SeecreterConfig.secretAuraRange);
                     BlockPos blockPos2 = new BlockPos(eyePos.field_72450_a + (double)SeecreterConfig.secretAuraRange, eyePos.field_72448_b + (double)SeecreterConfig.secretAuraRange, eyePos.field_72449_c + (double)SeecreterConfig.secretAuraRange);
                     Iterable<BlockPos> positions = BlockPos.func_177980_a(blockPos1, blockPos2);
                     long time = (new Date()).getTime();
                     int roomId = DungeonLocationUtils.getCurrentRoomId();
                     if ((SeecreterConfig.roomBlaze || roomId != 1103121487 && roomId != 23134049 && roomId != -1092103153 && roomId != -2027662369) && (SeecreterConfig.roomBoulder || roomId != -671152674 && roomId != 307825200 && roomId != -598953709 && roomId != -591423781) && (SeecreterConfig.roomCreeperBeams || roomId != -755321869 && roomId != -1709550764) && (SeecreterConfig.roomIceFill || roomId != 1328525306 && roomId != 327081838 && roomId != 161828987 && roomId != -1480765683) && (SeecreterConfig.roomIcePath || roomId != 1073658098) && (SeecreterConfig.roomTeleportMaze || roomId != 487124604 && roomId != 2089453469) && (SeecreterConfig.roomThreeWeirdos || roomId != -476788643) && (SeecreterConfig.roomTicTacToe || roomId != 1958698161) && (SeecreterConfig.roomWaterBoard || roomId != -109725212 && roomId != -353291158 && roomId != 1998063202 && roomId != 660384222 && roomId != -1012522341 && roomId != 660396563 && roomId != 1980639456 && roomId != 43497702 && roomId != 2014437159 && roomId != 1513261276 && roomId != 862140000 && roomId != -364886424 && roomId != -714138899 && roomId != -1489069695 && roomId != -685683836)) {
                        Iterator var12 = positions.iterator();

                        label280:
                        while(true) {
                           BlockPos position;
                           AxisAlignedBB aabb;
                           label278:
                           while(true) {
                              while(true) {
                                 while(true) {
                                    do {
                                       do {
                                          if (!var12.hasNext()) {
                                             break label280;
                                          }

                                          position = (BlockPos)var12.next();
                                       } while(blocksDone.contains(position));
                                    } while(blocksCooldown.containsKey(position) && (Long)blocksCooldown.get(position) + 500L > time);

                                    IBlockState blockState = world.func_180495_p(position);
                                    Vec3 centerPos;
                                    MovingObjectPosition movingObjectPosition;
                                    if (blockState.func_177230_c() != Blocks.field_150486_ae && blockState.func_177230_c() != Blocks.field_150447_bR) {
                                       if (blockState.func_177230_c() == Blocks.field_150442_at) {
                                          EnumOrientation orientation = (EnumOrientation)blockState.func_177228_b().get(BlockLever.field_176360_a);
                                          if (orientation == EnumOrientation.EAST) {
                                             aabb = new AxisAlignedBB(0.0D, 0.20000000298023224D, 0.3149999976158142D, 0.375D, 0.800000011920929D, 0.6875D);
                                             break label278;
                                          }

                                          if (orientation == EnumOrientation.WEST) {
                                             aabb = new AxisAlignedBB(0.625D, 0.20000000298023224D, 0.3149999976158142D, 1.0D, 0.800000011920929D, 0.6875D);
                                             break label278;
                                          }

                                          if (orientation == EnumOrientation.SOUTH) {
                                             aabb = new AxisAlignedBB(0.3125D, 0.20000000298023224D, 0.0D, 0.6875D, 0.800000011920929D, 0.375D);
                                             break label278;
                                          }

                                          if (orientation == EnumOrientation.NORTH) {
                                             aabb = new AxisAlignedBB(0.3125D, 0.20000000298023224D, 0.625D, 0.6875D, 0.800000011920929D, 1.0D);
                                             break label278;
                                          }

                                          if (orientation != EnumOrientation.UP_Z && orientation != EnumOrientation.UP_X) {
                                             if (orientation != EnumOrientation.DOWN_X && orientation != EnumOrientation.DOWN_Z) {
                                                continue;
                                             }

                                             aabb = new AxisAlignedBB(0.25D, 0.4000000059604645D, 0.25D, 0.75D, 1.0D, 0.75D);
                                             break label278;
                                          }

                                          aabb = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.6000000238418579D, 0.75D);
                                          break label278;
                                       } else if (blockState.func_177230_c() == Blocks.field_150465_bP) {
                                          TileEntity tileEntity = world.func_175625_s(position);
                                          if (tileEntity instanceof TileEntitySkull) {
                                             GameProfile profile = ((TileEntitySkull)tileEntity).func_152108_a();
                                             if (profile != null) {
                                                String profileId = profile.getId().toString();
                                                if (!Objects.equals(profileId, "e0f3e929-869e-3dca-9504-54c666ee6f23")) {
                                                   if (!Objects.equals(profileId, "fed95410-aba1-39df-9b95-1d4f361eb66e")) {
                                                      continue;
                                                   }

                                                   if (world.func_180495_p(position.func_177977_b()).func_177230_c() == Blocks.field_150451_bX || world.func_180495_p(position.func_177978_c()).func_177230_c() == Blocks.field_150451_bX || world.func_180495_p(position.func_177968_d()).func_177230_c() == Blocks.field_150451_bX || world.func_180495_p(position.func_177976_e()).func_177230_c() == Blocks.field_150451_bX || world.func_180495_p(position.func_177974_f()).func_177230_c() == Blocks.field_150451_bX) {
                                                      redstoneKey = false;
                                                      blocksDone.add(position);
                                                      continue;
                                                   }
                                                }

                                                EnumFacing facing = (EnumFacing)blockState.func_177228_b().get(BlockSkull.field_176418_a);
                                                AxisAlignedBB aabb;
                                                if (facing == EnumFacing.NORTH) {
                                                   aabb = new AxisAlignedBB(0.25D, 0.25D, 0.5D, 0.75D, 0.75D, 1.0D);
                                                } else if (facing == EnumFacing.SOUTH) {
                                                   aabb = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.5D);
                                                } else if (facing == EnumFacing.WEST) {
                                                   aabb = new AxisAlignedBB(0.5D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
                                                } else if (facing == EnumFacing.EAST) {
                                                   aabb = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 0.5D, 0.75D, 0.75D);
                                                } else {
                                                   aabb = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
                                                }

                                                Vec3 centerPos = (new Vec3(position)).func_72441_c((aabb.field_72340_a + aabb.field_72336_d) / 2.0D, (aabb.field_72338_b + aabb.field_72337_e) / 2.0D, (aabb.field_72339_c + aabb.field_72334_f) / 2.0D);
                                                if (eyePos.func_72438_d(centerPos) <= (double)SeecreterConfig.secretAuraSkullRange) {
                                                   MovingObjectPosition movingObjectPosition = BlockUtils.collisionRayTrace(position, aabb, eyePos, centerPos);
                                                   if (movingObjectPosition != null) {
                                                      if (SeecreterConfig.secretAuraSwapOn >= 1 && player.field_71071_by.field_70461_c != SeecreterConfig.secretAuraSlot - 1) {
                                                         prevSlot = player.field_71071_by.field_70461_c;
                                                         player.field_71071_by.field_70461_c = SeecreterConfig.secretAuraSlot - 1;
                                                         return;
                                                      }

                                                      PacketUtils.sendPacket(new C08PacketPlayerBlockPlacement(position, movingObjectPosition.field_178784_b.func_176745_a(), player.func_70694_bm(), (float)movingObjectPosition.field_72307_f.field_72450_a, (float)movingObjectPosition.field_72307_f.field_72448_b, (float)movingObjectPosition.field_72307_f.field_72449_c));
                                                      blocksCooldown.put(position, (new Date()).getTime());
                                                      return;
                                                   }
                                                }
                                             }
                                          }
                                       } else if (blockState.func_177230_c() == Blocks.field_150451_bX && redstoneKey && !(player.field_70165_t < -200.0D) && !(player.field_70161_v < -200.0D) && !(player.field_70165_t > 0.0D) && !(player.field_70161_v > 0.0D)) {
                                          if (world.func_180495_p(position.func_177984_a()).func_177230_c() != Blocks.field_150465_bP && world.func_180495_p(position.func_177978_c()).func_177230_c() != Blocks.field_150465_bP && world.func_180495_p(position.func_177968_d()).func_177230_c() != Blocks.field_150465_bP && world.func_180495_p(position.func_177976_e()).func_177230_c() != Blocks.field_150465_bP && world.func_180495_p(position.func_177974_f()).func_177230_c() != Blocks.field_150465_bP) {
                                             centerPos = (new Vec3(position)).func_72441_c(0.5D, 0.5D, 0.5D);
                                             if (eyePos.func_72438_d(new Vec3(position)) <= (double)SeecreterConfig.secretAuraRange) {
                                                movingObjectPosition = BlockUtils.collisionRayTrace(position, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), eyePos, centerPos);
                                                if (movingObjectPosition != null && movingObjectPosition.field_178784_b != EnumFacing.DOWN) {
                                                   if (SeecreterConfig.secretAuraSwapOn >= 1 && player.field_71071_by.field_70461_c != SeecreterConfig.secretAuraSlot - 1) {
                                                      prevSlot = player.field_71071_by.field_70461_c;
                                                      player.field_71071_by.field_70461_c = SeecreterConfig.secretAuraSlot - 1;
                                                      return;
                                                   }

                                                   PacketUtils.sendPacket(new C08PacketPlayerBlockPlacement(position, movingObjectPosition.field_178784_b.func_176745_a(), player.func_70694_bm(), (float)movingObjectPosition.field_72307_f.field_72450_a, (float)movingObjectPosition.field_72307_f.field_72448_b, (float)movingObjectPosition.field_72307_f.field_72449_c));
                                                   blocksCooldown.put(position, (new Date()).getTime());
                                                   return;
                                                }
                                             }
                                          } else {
                                             redstoneKey = false;
                                             blocksDone.add(position);
                                          }
                                       }
                                    } else {
                                       centerPos = new Vec3((double)position.func_177958_n() + 0.5D, (double)position.func_177956_o() + 0.4375D, (double)position.func_177952_p() + 0.5D);
                                       if (eyePos.func_72438_d(new Vec3(position)) <= (double)SeecreterConfig.secretAuraRange) {
                                          movingObjectPosition = BlockUtils.collisionRayTrace(position, new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D), eyePos, centerPos);
                                          if (movingObjectPosition != null) {
                                             if (SeecreterConfig.secretAuraSwapOn >= 2 && player.field_71071_by.field_70461_c != SeecreterConfig.secretAuraSlot - 1) {
                                                prevSlot = player.field_71071_by.field_70461_c;
                                                player.field_71071_by.field_70461_c = SeecreterConfig.secretAuraSlot - 1;
                                                return;
                                             }

                                             PacketUtils.sendPacket(new C08PacketPlayerBlockPlacement(position, movingObjectPosition.field_178784_b.func_176745_a(), player.func_70694_bm(), (float)movingObjectPosition.field_72307_f.field_72450_a, (float)movingObjectPosition.field_72307_f.field_72448_b, (float)movingObjectPosition.field_72307_f.field_72449_c));
                                             if (!player.func_70093_af() && SeecreterConfig.secretAuraSwingHand) {
                                                player.func_71038_i();
                                             }

                                             blocksCooldown.put(position, (new Date()).getTime());
                                             return;
                                          }
                                       }
                                    }
                                 }
                              }
                           }

                           Vec3 centerPos = (new Vec3(position)).func_72441_c((aabb.field_72340_a + aabb.field_72336_d) / 2.0D, (aabb.field_72338_b + aabb.field_72337_e) / 2.0D, (aabb.field_72339_c + aabb.field_72334_f) / 2.0D);
                           if (eyePos.func_72438_d(new Vec3(position)) <= (double)SeecreterConfig.secretAuraRange) {
                              MovingObjectPosition movingObjectPosition = BlockUtils.collisionRayTrace(position, aabb, eyePos, centerPos);
                              if (movingObjectPosition != null) {
                                 if (SeecreterConfig.secretAuraSwapOn >= 2 && player.field_71071_by.field_70461_c != SeecreterConfig.secretAuraSlot - 1) {
                                    prevSlot = player.field_71071_by.field_70461_c;
                                    player.field_71071_by.field_70461_c = SeecreterConfig.secretAuraSlot - 1;
                                    return;
                                 }

                                 PacketUtils.sendPacket(new C08PacketPlayerBlockPlacement(position, movingObjectPosition.field_178784_b.func_176745_a(), player.func_70694_bm(), (float)movingObjectPosition.field_72307_f.field_72450_a, (float)movingObjectPosition.field_72307_f.field_72448_b, (float)movingObjectPosition.field_72307_f.field_72449_c));
                                 if (!player.func_70093_af() && SeecreterConfig.secretAuraSwingHand) {
                                    player.func_71038_i();
                                 }

                                 blocksCooldown.put(position, (new Date()).getTime());
                                 return;
                              }
                           }
                        }
                     }

                     if (SeecreterConfig.secretAuraSwapBack && prevSlot >= 0) {
                        player.field_71071_by.field_70461_c = prevSlot;
                     }

                     prevSlot = -1;
                  }
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void onWorldLoad(Load event) {
      clearBlocks();
      redstoneKey = false;
   }

   @Subscribe
   public void onPacketReceive(ReceivePacketEvent event) {
      if (event.packet instanceof S24PacketBlockAction) {
         S24PacketBlockAction packet = (S24PacketBlockAction)event.packet;
         if (packet.func_148868_c() == Blocks.field_150486_ae) {
            blocksDone.add(packet.func_179825_a());
         }
      } else {
         Minecraft mc;
         WorldClient world;
         if (event.packet instanceof S23PacketBlockChange) {
            S23PacketBlockChange packet = (S23PacketBlockChange)event.packet;
            mc = Minecraft.func_71410_x();
            world = mc.field_71441_e;
            BlockPos blockPos = packet.func_179827_b();
            IBlockState blockState = world.func_180495_p(blockPos);
            if (blockState.func_177230_c() == Blocks.field_150442_at) {
               blocksDone.add(blockPos);
            } else if (blockState.func_177230_c() == Blocks.field_150465_bP) {
               if (packet.func_180728_a().func_177230_c() != Blocks.field_150350_a) {
                  return;
               }

               TileEntity tileEntity = world.func_175625_s(blockPos);
               if (!(tileEntity instanceof TileEntitySkull)) {
                  return;
               }

               GameProfile profile = ((TileEntitySkull)tileEntity).func_152108_a();
               if (profile == null) {
                  return;
               }

               String profileId = profile.getId().toString();
               if (profileId.equals("fed95410-aba1-39df-9b95-1d4f361eb66e")) {
                  redstoneKey = true;
               }
            } else if (blockState.func_177230_c() == Blocks.field_150451_bX) {
               blocksDone.add(blockPos);
            }
         } else if (event.packet instanceof S22PacketMultiBlockChange) {
            S22PacketMultiBlockChange packet = (S22PacketMultiBlockChange)event.packet;
            mc = Minecraft.func_71410_x();
            world = mc.field_71441_e;
            BlockUpdateData[] var19 = packet.func_179844_a();
            int var21 = var19.length;

            for(int var23 = 0; var23 < var21; ++var23) {
               BlockUpdateData changedBlock = var19[var23];
               BlockPos blockPos = changedBlock.func_180090_a();
               IBlockState blockState = world.func_180495_p(blockPos);
               if (blockState.func_177230_c() == Blocks.field_150442_at) {
                  blocksDone.add(blockPos);
               } else if (blockState.func_177230_c() == Blocks.field_150465_bP) {
                  if (changedBlock.func_180088_c().func_177230_c() != Blocks.field_150350_a) {
                     return;
                  }

                  TileEntity tileEntity = world.func_175625_s(blockPos);
                  if (!(tileEntity instanceof TileEntitySkull)) {
                     return;
                  }

                  GameProfile profile = ((TileEntitySkull)tileEntity).func_152108_a();
                  if (profile == null) {
                     return;
                  }

                  String profileId = profile.getId().toString();
                  if (profileId.equals("fed95410-aba1-39df-9b95-1d4f361eb66e")) {
                     redstoneKey = true;
                  }
               } else if (blockState.func_177230_c() == Blocks.field_150451_bX) {
                  blocksDone.add(blockPos);
               }
            }
         } else if (event.packet instanceof S04PacketEntityEquipment) {
            S04PacketEntityEquipment packet = (S04PacketEntityEquipment)event.packet;
            mc = Minecraft.func_71410_x();
            world = mc.field_71441_e;
            Entity entity = world.func_73045_a(packet.func_149389_d());
            if (!(entity instanceof EntityArmorStand)) {
               return;
            }

            if (packet.func_149388_e() != 4) {
               return;
            }

            ItemStack itemStack = packet.func_149390_c();
            if (itemStack == null) {
               return;
            }

            if (itemStack.func_77973_b() != Items.field_151144_bL) {
               return;
            }

            if (!itemStack.func_77942_o()) {
               return;
            }

            String profileId = itemStack.func_77978_p().func_74775_l("SkullOwner").func_74779_i("Id");
            if (!profileId.equals("e0f3e929-869e-3dca-9504-54c666ee6f23")) {
               return;
            }

            blocksDone.add(new BlockPos(entity.field_70165_t, entity.field_70163_u + 2.0D, entity.field_70161_v));
         } else if (event.packet instanceof S02PacketChat) {
            S02PacketChat packet = (S02PacketChat)event.packet;
            if (packet.func_179841_c() == 2) {
               return;
            }

            String message = packet.func_148915_c().func_150260_c().replaceAll("§[0-9a-fk-or]", "");
            if (message.equals("[BOSS] Goldor: Who dares trespass into my domain?")) {
               clearBlocks();
               ChatUtils.sendModMessage("Blocks cleared!");
            }
         }
      }

   }

   public static void clearBlocks() {
      blocksDone.clear();
      blocksCooldown.clear();
   }
}
