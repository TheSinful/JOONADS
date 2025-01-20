package me.jooon.JooonAddons.features.dungeons;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0013H\u0007R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0014"},
   d2 = {"Lme/jooon/JooonAddons/features/dungeons/AutoP3GhostBlocks;", "", "()V", "ghostBlockCoords", "", "Lnet/minecraft/util/Vec3;", "getGhostBlockCoords", "()Ljava/util/List;", "lastTimeGhostBLocksDone", "", "getLastTimeGhostBLocksDone", "()J", "setLastTimeGhostBLocksDone", "(J)V", "onTick", "", "event", "Lnet/minecraftforge/fml/common/gameevent/TickEvent$ClientTickEvent;", "onWorldLoad", "Lnet/minecraftforge/event/world/WorldEvent$Load;", "JooonAddons"}
)
public final class AutoP3GhostBlocks {
   @NotNull
   public static final AutoP3GhostBlocks INSTANCE = new AutoP3GhostBlocks();
   private static long lastTimeGhostBLocksDone;
   @NotNull
   private static final List<Vec3> ghostBlockCoords;

   private AutoP3GhostBlocks() {
   }

   public final long getLastTimeGhostBLocksDone() {
      return lastTimeGhostBLocksDone;
   }

   public final void setLastTimeGhostBLocksDone(long <set-?>) {
      lastTimeGhostBLocksDone = var1;
   }

   @SubscribeEvent
   public final void onWorldLoad(@NotNull Load event) {
      Intrinsics.checkNotNullParameter(event, "event");
      lastTimeGhostBLocksDone = 0L;
   }

   @NotNull
   public final List<Vec3> getGhostBlockCoords() {
      return ghostBlockCoords;
   }

   @SubscribeEvent
   public final void onTick(@NotNull ClientTickEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getAutoP3P5GhostBlocks()) {
         if (JooonAddons.Companion.getMc().field_71439_g != null && JooonAddons.Companion.getMc().field_71441_e != null) {
            if (Utils.INSTANCE.getInDungeon()) {
               if (System.currentTimeMillis() / (long)1000 - lastTimeGhostBLocksDone >= 10L) {
                  try {
                     if (!StringsKt.contains$default((CharSequence)Utils.INSTANCE.getScoreboardLines().get(3), (CharSequence)"F7", false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)Utils.INSTANCE.getScoreboardLines().get(3), (CharSequence)"M7", false, 2, (Object)null)) {
                        return;
                     }

                     lastTimeGhostBLocksDone = System.currentTimeMillis() / (long)1000;
                     Iterator var2 = ghostBlockCoords.iterator();

                     while(var2.hasNext()) {
                        Vec3 block = (Vec3)var2.next();
                        BlockPos blockPos = new BlockPos(block);
                        JooonAddons.Companion.getMc().field_71439_g.field_70170_p.func_175698_g(blockPos);
                     }
                  } catch (Exception var5) {
                     var5.printStackTrace();
                  }

               }
            }
         }
      }
   }

   static {
      Vec3[] var0 = new Vec3[]{new Vec3(88.5D, 167.0D, 41.5D), new Vec3(89.5D, 167.0D, 41.5D), new Vec3(91.5D, 167.0D, 41.5D), new Vec3(92.5D, 167.0D, 41.5D), new Vec3(93.5D, 167.0D, 41.5D), new Vec3(94.5D, 167.0D, 41.5D), new Vec3(95.5D, 167.0D, 41.5D), new Vec3(96.5D, 167.0D, 41.5D), new Vec3(96.5D, 167.0D, 40.5D), new Vec3(95.5D, 167.0D, 40.5D), new Vec3(94.5D, 167.0D, 40.5D), new Vec3(93.5D, 167.0D, 40.5D), new Vec3(92.5D, 167.0D, 40.5D), new Vec3(90.5D, 167.0D, 41.5D), new Vec3(91.5D, 167.0D, 40.5D), new Vec3(91.5D, 166.0D, 40.5D), new Vec3(92.5D, 166.0D, 40.5D), new Vec3(93.5D, 166.0D, 40.5D), new Vec3(94.5D, 166.0D, 40.5D), new Vec3(95.5D, 166.0D, 40.5D), new Vec3(91.5D, 166.0D, 41.5D), new Vec3(92.5D, 166.0D, 41.5D), new Vec3(93.5D, 166.0D, 41.5D), new Vec3(94.5D, 166.0D, 41.5D), new Vec3(95.5D, 166.0D, 41.5D), new Vec3(54.5D, 64.0D, 78.5D), new Vec3(54.5D, 64.0D, 77.5D), new Vec3(54.5D, 64.0D, 76.5D), new Vec3(54.5D, 64.0D, 75.5D), new Vec3(54.5D, 64.0D, 74.5D)};
      ghostBlockCoords = CollectionsKt.listOf(var0);
   }
}
