package me.jooon.JooonAddons.features.dungeons;

import gg.essential.universal.UMatrixStack;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.render.RenderUtils;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J.\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000b¨\u0006\u0016"},
   d2 = {"Lme/jooon/JooonAddons/features/dungeons/TerminalWaypoints;", "", "()V", "checkForMob", "", "event", "Lnet/minecraftforge/client/event/RenderWorldLastEvent;", "drawTerminalWaypoint", "entity", "Lnet/minecraft/entity/EntityLivingBase;", "color", "", "type", "partialTicks", "", "text", "", "getEntitiesInRadius", "", "Lnet/minecraft/entity/Entity;", "center", "radius", "JooonAddons"}
)
public final class TerminalWaypoints {
   @NotNull
   public static final TerminalWaypoints INSTANCE = new TerminalWaypoints();

   private TerminalWaypoints() {
   }

   @SubscribeEvent
   public final void checkForMob(@NotNull RenderWorldLastEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getTerminalWaypoints()) {
         if (Utils.INSTANCE.getInDungeon()) {
            WorldClient world = Minecraft.func_71410_x().field_71441_e;
            List entityList = world.field_72996_f;
            Iterator var4 = entityList.iterator();

            while(true) {
               Entity entity;
               do {
                  label58:
                  while(true) {
                     do {
                        do {
                           if (!var4.hasNext()) {
                              return;
                           }

                           entity = (Entity)var4.next();
                        } while(!entity.func_145818_k_());
                     } while(!(entity instanceof EntityArmorStand));

                     String var10000 = ((EntityArmorStand)entity).func_95999_t();
                     Intrinsics.checkNotNullExpressionValue(var10000, "entity.customNameTag");
                     String var6 = Utils.stripColor(var10000);
                     switch(var6.hashCode()) {
                     case -2040349564:
                        if (!var6.equals("Not Activated")) {
                           break;
                        }

                        this.drawTerminalWaypoint((EntityLivingBase)entity, Config.INSTANCE.getLeverBeaconColor().getRGB(), 2, event.partialTicks, "Lever");
                        break label58;
                     case -710573423:
                        if (!var6.equals("Inactive Terminal")) {
                           break;
                        }

                        this.drawTerminalWaypoint((EntityLivingBase)entity, Config.INSTANCE.getTerminalBeaconColor().getRGB(), 3, event.partialTicks, "Terminal");
                        break label58;
                     case 89309323:
                        if (var6.equals("Inactive")) {
                           this.drawTerminalWaypoint((EntityLivingBase)entity, Config.INSTANCE.getDeviceBeaconColor().getRGB(), 1, event.partialTicks, "Device");
                           break label58;
                        }
                     }
                  }

                  if (Math.abs(JooonAddons.Companion.getMc().field_71439_g.field_70165_t - entity.field_70165_t) < 3.0D && Math.abs(JooonAddons.Companion.getMc().field_71439_g.field_70161_v - entity.field_70161_v) < 3.0D) {
                     return;
                  }
               } while(!Config.INSTANCE.getHideDefaultNames());

               Iterator var8 = this.getEntitiesInRadius(entity, 1).iterator();

               while(var8.hasNext()) {
                  Entity near = (Entity)var8.next();
                  if (near instanceof EntityArmorStand) {
                     ((EntityArmorStand)near).func_174805_g(false);
                  }
               }
            }
         }
      }
   }

   @NotNull
   public final List<Entity> getEntitiesInRadius(@NotNull Entity center, int radius) {
      Intrinsics.checkNotNullParameter(center, "center");
      World world = center.field_70170_p;
      double x = center.field_70165_t;
      double y = center.field_70163_u;
      double z = center.field_70161_v;
      List var10000 = world.func_72872_a(Entity.class, new AxisAlignedBB(x - (double)radius, y - (double)radius, z - (double)radius, x + (double)radius, y + (double)radius, z + (double)radius));
      Intrinsics.checkNotNullExpressionValue(var10000, "world.getEntitiesWithinA…z + radius)\n            )");
      return var10000;
   }

   public final void drawTerminalWaypoint(@NotNull EntityLivingBase entity, int color, int type, float partialTicks, @NotNull String text) {
      Intrinsics.checkNotNullParameter(entity, "entity");
      Intrinsics.checkNotNullParameter(text, "text");
      String diffX = String.valueOf(Math.abs(JooonAddons.Companion.getMc().field_71439_g.field_70165_t - entity.field_70165_t));
      String diffZ = String.valueOf(Math.abs(JooonAddons.Companion.getMc().field_71439_g.field_70161_v - entity.field_70161_v));
      if (!(Math.abs(JooonAddons.Companion.getMc().field_71439_g.field_70165_t - entity.field_70165_t) < 3.0D) || !(Math.abs(JooonAddons.Companion.getMc().field_71439_g.field_70161_v - entity.field_70161_v) < 3.0D)) {
         RenderUtils.INSTANCE.drawBeaconBeam(entity, color, type);
         RenderUtils.renderWaypointText$default(RenderUtils.INSTANCE, text, entity.field_70165_t, entity.field_70163_u + 3.5D, entity.field_70161_v, partialTicks, new UMatrixStack(), 0.0F, false, false, false, 960, (Object)null);
      }
   }
}
