package me.jooon.JooonAddons.features.dungeons;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.events.impl.RenderEntityModelEvent;
import me.jooon.JooonAddons.features.Feature;
import me.jooon.JooonAddons.mixin.IMixinRendererLivingEntity;
import me.jooon.JooonAddons.render.RenderUtils;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J \u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J\u0010\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001dH\u0007J\f\u0010\u001e\u001a\u00020\u001f*\u00020\u0006H\u0002R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "},
   d2 = {"Lme/jooon/JooonAddons/features/dungeons/StarredMobESP;", "Lme/jooon/JooonAddons/features/Feature;", "()V", "customMobs", "Ljava/util/HashMap;", "Lnet/minecraft/entity/Entity;", "Lnet/minecraft/entity/EntityLivingBase;", "Lkotlin/collections/HashMap;", "starredMobs", "drawEsp", "", "entity", "model", "Lnet/minecraft/client/model/ModelBase;", "color", "", "partialTicks", "", "getStarredMobsWithinAABB", "getStarredMobsWithinAABBForEntity", "chunk", "Lnet/minecraft/world/chunk/Chunk;", "entityIn", "aabb", "Lnet/minecraft/util/AxisAlignedBB;", "onRenderEntityModel", "event", "Lme/jooon/JooonAddons/events/impl/RenderEntityModelEvent;", "onWorldLoad", "Lnet/minecraftforge/event/world/WorldEvent$Load;", "isDead", "", "JooonAddons"}
)
@SourceDebugExtension({"SMAP\nStarredMobESP.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StarredMobESP.kt\nme/jooon/JooonAddons/features/dungeons/StarredMobESP\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,170:1\n1#2:171\n*E\n"})
public final class StarredMobESP extends Feature {
   @NotNull
   public static final StarredMobESP INSTANCE = new StarredMobESP();
   @NotNull
   private static final HashMap<Entity, EntityLivingBase> starredMobs = new HashMap();
   @NotNull
   private static final HashMap<Entity, EntityLivingBase> customMobs = new HashMap();

   private StarredMobESP() {
   }

   @SubscribeEvent
   public final void onRenderEntityModel(@NotNull RenderEntityModelEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getStarredMobESP()) {
         float maxHP = 0.0F;
         EntityLivingBase var3 = event.getEntity();
         String var10000;
         if (var3 instanceof EntityArmorStand) {
            if (!event.getEntity().func_145818_k_()) {
               return;
            }

            var10000 = ((EntityArmorStand)event.getEntity()).func_95999_t();
            Intrinsics.checkNotNullExpressionValue(var10000, "event.entity.customNameTag");
            String name = Utils.stripColor(var10000);
            if (Config.INSTANCE.getStarredMobESP() && StringsKt.startsWith$default(name, "✯ ", false, 2, (Object)null)) {
               EntityLivingBase mob = (EntityLivingBase)starredMobs.get(event.getEntity());
               if (mob != null) {
                  if (this.isDead(mob)) {
                     starredMobs.remove(event.getEntity());
                     return;
                  }

                  Render var9 = this.getMc().func_175598_ae().func_78713_a((Entity)mob);
                  Intrinsics.checkNotNull(var9, "null cannot be cast to non-null type me.jooon.JooonAddons.mixin.IMixinRendererLivingEntity");
                  ModelBase model = ((IMixinRendererLivingEntity)var9).getMainModel();
                  Intrinsics.checkNotNullExpressionValue(model, "model");
                  this.drawEsp(mob, model, Config.INSTANCE.getStarredMobESPColor().getRGB(), 1.0F);
               } else {
                  this.getStarredMobsWithinAABB((Entity)event.getEntity());
               }
            } else if (Intrinsics.areEqual(name, "Wither Key") || Intrinsics.areEqual(name, "Blood Key")) {
               RenderUtils.INSTANCE.drawBeaconBeam(event.getEntity(), Config.INSTANCE.getStarredMobESPColor().getRGB(), 3);
            }
         } else if (var3 instanceof EntityOtherPlayerMP) {
            if (Config.INSTANCE.getStarredMobESP()) {
               var10000 = ((EntityOtherPlayerMP)event.getEntity()).func_70005_c_();
               if (Intrinsics.areEqual(var10000 != null ? StringsKt.trim((CharSequence)var10000).toString() : null, "Shadow Assassin")) {
                  this.drawEsp(event.getEntity(), event.getModel(), Config.INSTANCE.getStarredMobESPColor().getRGB(), 1.0F);
               }
            }
         } else if (var3 instanceof EntityEnderman) {
            if (((EntityEnderman)event.getEntity()).func_82150_aj()) {
               ((EntityEnderman)event.getEntity()).func_82142_c(false);
            }
         } else if (var3 instanceof EntityBat) {
            float var7 = ((EntityBat)event.getEntity()).func_110138_aP();
            int var8 = false;
            if (var7 == 100.0F || var7 == 200.0F) {
               RenderUtils.INSTANCE.drawChamsEsp(event.getEntity(), event.getModel(), Config.INSTANCE.getStarredMobESPColor().getRGB(), 1.0F);
            }
         }

      }
   }

   @SubscribeEvent
   public final void onWorldLoad(@NotNull Load event) {
      Intrinsics.checkNotNullParameter(event, "event");
      customMobs.clear();
   }

   private final void drawEsp(EntityLivingBase entity, ModelBase model, int color, float partialTicks) {
      switch(Config.INSTANCE.getStarredESPType()) {
      case 0:
         RenderUtils.INSTANCE.drawChamsEsp(entity, model, color, partialTicks);
         break;
      case 1:
         RenderUtils.INSTANCE.renderBoundingBox((Entity)entity, color);
         break;
      case 2:
         RenderUtils.INSTANCE.drawOutlinedEsp(entity, model, color, partialTicks);
      }

   }

   private final void getStarredMobsWithinAABB(Entity entity) {
      AxisAlignedBB aabb = new AxisAlignedBB(entity.field_70165_t + 0.4D, entity.field_70163_u - 2.0D, entity.field_70161_v + 0.4D, entity.field_70165_t - 0.4D, entity.field_70163_u + 0.2D, entity.field_70161_v - 0.4D);
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
                  this.getStarredMobsWithinAABBForEntity(var10001, entity, aabb);
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

   private final void getStarredMobsWithinAABBForEntity(Chunk chunk, Entity entityIn, AxisAlignedBB aabb) {
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
                  if (e.func_174813_aQ().func_72326_a(aabb)) {
                     Map var13;
                     if (e instanceof EntityOtherPlayerMP) {
                        if (!(((EntityOtherPlayerMP)e).func_110143_aJ() <= 0.0F) && e.func_70005_c_() != null) {
                           String var10000 = e.func_70005_c_();
                           Intrinsics.checkNotNullExpressionValue(var10000, "e.getName()");
                           String var12 = StringsKt.trim((CharSequence)var10000).toString();
                           if (Intrinsics.areEqual(var12, "Lost Adventurer") ? true : Intrinsics.areEqual(var12, "Diamond Guy")) {
                              var13 = (Map)starredMobs;
                              Intrinsics.checkNotNull(e, "null cannot be cast to non-null type net.minecraft.entity.EntityLivingBase");
                              var13.put(entityIn, (EntityLivingBase)e);
                           } else if (!e.func_82150_aj() && e.func_110124_au().version() == 2) {
                              var13 = (Map)starredMobs;
                              Intrinsics.checkNotNull(e, "null cannot be cast to non-null type net.minecraft.entity.EntityLivingBase");
                              var13.put(entityIn, (EntityLivingBase)e);
                           }
                        }
                     } else if (e instanceof EntitySkeleton ? true : e instanceof EntityZombie) {
                        Intrinsics.checkNotNull(e, "null cannot be cast to non-null type net.minecraft.entity.monster.EntityMob");
                        if (!(((EntityMob)e).func_110143_aJ() <= 0.0F) && !((EntityMob)e).func_82150_aj()) {
                           ((Map)starredMobs).put(entityIn, (EntityLivingBase)e);
                        }
                     } else if (e instanceof EntityEnderman && !(((EntityEnderman)e).func_110143_aJ() <= 0.0F)) {
                        var13 = (Map)starredMobs;
                        Intrinsics.checkNotNull(e, "null cannot be cast to non-null type net.minecraft.entity.EntityLivingBase");
                        var13.put(entityIn, (EntityLivingBase)e);
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

   private final boolean isDead(EntityLivingBase $this$isDead) {
      return $this$isDead.field_70128_L || $this$isDead.func_110138_aP() <= 0.0F;
   }
}
