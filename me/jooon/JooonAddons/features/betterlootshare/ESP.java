package me.jooon.JooonAddons.features.betterlootshare;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.features.Feature;
import me.jooon.JooonAddons.mixin.IMixinRendererLivingEntity;
import me.jooon.JooonAddons.render.RenderUtils;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.RenderLivingEvent.Pre;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u000e\u0010\u0017\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005J\u001e\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001dJ\u0016\u0010\u001e\u001a\u00020\r2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060 H\u0007J\u0010\u0010!\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\"H\u0007J\f\u0010#\u001a\u00020$*\u00020\u0006H\u0002R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006%"},
   d2 = {"Lme/jooon/JooonAddons/features/betterlootshare/ESP;", "Lme/jooon/JooonAddons/features/Feature;", "()V", "customMobs", "Ljava/util/HashMap;", "Lnet/minecraft/entity/Entity;", "Lnet/minecraft/entity/EntityLivingBase;", "Lkotlin/collections/HashMap;", "logger", "Lorg/apache/logging/log4j/Logger;", "getLogger", "()Lorg/apache/logging/log4j/Logger;", "drawEsp", "", "entity", "model", "Lnet/minecraft/client/model/ModelBase;", "color", "", "partialTicks", "", "getMobsForESP", "", "getMobsWithinAABB", "getMobsWithinAABBForEntity", "chunk", "Lnet/minecraft/world/chunk/Chunk;", "entityIn", "aabb", "Lnet/minecraft/util/AxisAlignedBB;", "onRenderMob", "event", "Lnet/minecraftforge/client/event/RenderLivingEvent$Pre;", "onWorldLoad", "Lnet/minecraftforge/event/world/WorldEvent$Load;", "isDead", "", "JooonAddons"}
)
public final class ESP extends Feature {
   @NotNull
   public static final ESP INSTANCE = new ESP();
   @NotNull
   private static final Logger logger;
   @NotNull
   private static final HashMap<Entity, EntityLivingBase> customMobs;

   private ESP() {
   }

   @NotNull
   public final Logger getLogger() {
      return logger;
   }

   @SubscribeEvent
   public final void onRenderMob(@NotNull Pre<EntityLivingBase> event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getGlowOnMob()) {
         String mobsForESP;
         CharSequence var12;
         String var10000;
         EntityLivingBase var10001;
         if (event.entity instanceof EntityArmorStand) {
            if (!event.entity.func_145818_k_()) {
               return;
            }

            mobsForESP = this.getMobsForESP();
            if (Intrinsics.areEqual(mobsForESP, "") || Intrinsics.areEqual(mobsForESP, " ")) {
               return;
            }

            var10000 = event.entity.func_95999_t();
            Intrinsics.checkNotNullExpressionValue(var10000, "event.entity.customNameTag");
            String name = Utils.stripColor(var10000);
            var12 = (CharSequence)mobsForESP;
            String[] var5 = new String[]{", "};
            Iterator var4 = StringsKt.split$default(var12, var5, false, 0, 6, (Object)null).iterator();

            while(var4.hasNext()) {
               String cname = (String)var4.next();
               if (StringsKt.contains((CharSequence)name, (CharSequence)(' ' + cname + ' '), true)) {
                  EntityLivingBase mob = (EntityLivingBase)customMobs.get(event.entity);
                  if (mob == null) {
                     var10001 = event.entity;
                     Intrinsics.checkNotNullExpressionValue(var10001, "event.entity");
                     this.getMobsWithinAABB((Entity)var10001);
                     return;
                  }

                  if (!this.isDead(mob)) {
                     Render var13 = this.getMc().func_175598_ae().func_78713_a((Entity)mob);
                     Intrinsics.checkNotNull(var13, "null cannot be cast to non-null type me.jooon.JooonAddons.mixin.IMixinRendererLivingEntity");
                     ModelBase model = ((IMixinRendererLivingEntity)var13).getMainModel();
                     Intrinsics.checkNotNullExpressionValue(model, "model");
                     this.drawEsp(mob, model, Config.INSTANCE.getGlowColor().getRGB(), 1.0F);
                     return;
                  }

                  customMobs.remove(event.entity);
                  break;
               }
            }
         } else {
            mobsForESP = this.getMobsForESP();
            if (Intrinsics.areEqual(mobsForESP, "") || Intrinsics.areEqual(mobsForESP, " ")) {
               return;
            }

            var12 = (CharSequence)mobsForESP;
            String[] var9 = new String[]{", "};
            Iterator var8 = StringsKt.split$default(var12, var9, false, 0, 6, (Object)null).iterator();

            while(var8.hasNext()) {
               String cname = (String)var8.next();
               var10000 = event.entity.func_70005_c_();
               if (!(var10000 != null ? !StringsKt.contains((CharSequence)var10000, (CharSequence)(' ' + cname + ' '), true) : false)) {
                  var10001 = event.entity;
                  Intrinsics.checkNotNullExpressionValue(var10001, "event.entity");
                  ModelBase var10002 = event.renderer.func_177087_b();
                  Intrinsics.checkNotNullExpressionValue(var10002, "event.renderer.mainModel");
                  this.drawEsp(var10001, var10002, Config.INSTANCE.getGlowColor().getRGB(), 1.0F);
                  return;
               }
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
      switch(Config.INSTANCE.getEspSelector()) {
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

   public final void getMobsWithinAABB(@NotNull Entity entity) {
      Intrinsics.checkNotNullParameter(entity, "entity");
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

   public final void getMobsWithinAABBForEntity(@NotNull Chunk chunk, @NotNull Entity entityIn, @NotNull AxisAlignedBB aabb) {
      Intrinsics.checkNotNullParameter(chunk, "chunk");
      Intrinsics.checkNotNullParameter(entityIn, "entityIn");
      Intrinsics.checkNotNullParameter(aabb, "aabb");
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
                  if (e.func_174813_aQ().func_72326_a(aabb) && e instanceof EntityMob && !(((EntityMob)e).func_110143_aJ() <= 0.0F) && !((EntityMob)e).func_82150_aj()) {
                     ((Map)customMobs).put(entityIn, (EntityLivingBase)e);
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

   private final String getMobsForESP() {
      String mobs = "";
      if (Config.INSTANCE.getNutterNotification()) {
         mobs = mobs + "Nutcracker, ";
      }

      if (Config.INSTANCE.getGwSharkNotification()) {
         mobs = mobs + "Great White Shark, ";
      }

      if (Config.INSTANCE.getThunderNotification()) {
         mobs = mobs + "Thunder, ";
      }

      if (Config.INSTANCE.getJawbusNotification()) {
         mobs = mobs + "Lord Jawbus, ";
      }

      if (Config.INSTANCE.getGrimNotification()) {
         mobs = mobs + "Grim Reaper, ";
      }

      if (Config.INSTANCE.getYetiNotification()) {
         mobs = mobs + "Yeti, ";
      }

      if (Config.INSTANCE.getHydraNotification()) {
         mobs = mobs + "Hydra, ";
      }

      if (!Intrinsics.areEqual(Config.INSTANCE.getCustomESPMobs(), "")) {
         mobs = mobs + Config.INSTANCE.getCustomESPMobs();
      }

      if (StringsKt.endsWith$default(mobs, ",", false, 2, (Object)null)) {
         mobs = StringsKt.removeSuffix(mobs, (CharSequence)",");
      }

      if (StringsKt.endsWith$default(mobs, ", ", false, 2, (Object)null)) {
         mobs = StringsKt.removeSuffix(mobs, (CharSequence)", ");
      }

      return mobs;
   }

   private final boolean isDead(EntityLivingBase $this$isDead) {
      return $this$isDead.field_70128_L || $this$isDead.func_110138_aP() <= 0.0F;
   }

   static {
      Logger var10000 = LogManager.getLogger("JooonAddons");
      Intrinsics.checkNotNullExpressionValue(var10000, "getLogger(\"JooonAddons\")");
      logger = var10000;
      customMobs = new HashMap();
   }
}
