package me.jooon.JooonAddons.features.dungeons;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"},
   d2 = {"Lme/jooon/JooonAddons/features/dungeons/RemoveBlindness;", "", "()V", "removeBlindness", "", "event", "Lnet/minecraftforge/fml/common/gameevent/TickEvent$PlayerTickEvent;", "JooonAddons"}
)
public final class RemoveBlindness {
   @NotNull
   public static final RemoveBlindness INSTANCE = new RemoveBlindness();

   private RemoveBlindness() {
   }

   @SubscribeEvent
   public final void removeBlindness(@NotNull PlayerTickEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getRemoveBlindness()) {
         if (Utils.INSTANCE.getInDungeon()) {
            JooonAddons.Companion.getMc().field_71439_g.func_70618_n(Potion.field_76440_q.field_76415_H);
         }
      }
   }
}
