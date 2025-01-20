package me.jooon.JooonAddons.hooks.render;

import kotlin.Metadata;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.utils.SBInfo;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"},
   d2 = {"Lme/jooon/JooonAddons/hooks/render/FarmingBlocksHook;", "", "()V", "shouldChangeSize", "", "JooonAddons"}
)
public final class FarmingBlocksHook {
   public final boolean shouldChangeSize() {
      return !SBInfo.INSTANCE.getOnSkyblock() ? false : Config.INSTANCE.getBetterFarmingHitboxes();
   }
}
