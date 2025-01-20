package me.jooon.JooonAddons.hooks.render;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.events.impl.FishingHookHookedEvent;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"},
   d2 = {"Lme/jooon/JooonAddons/hooks/render/FishEntityHooked;", "", "()V", "Companion", "JooonAddons"}
)
public final class FishEntityHooked {
   @NotNull
   public static final FishEntityHooked.Companion Companion = new FishEntityHooked.Companion((DefaultConstructorMarker)null);

   @JvmStatic
   public static final void fishEntityHooked(@NotNull Entity entity) {
      Companion.fishEntityHooked(entity);
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"},
      d2 = {"Lme/jooon/JooonAddons/hooks/render/FishEntityHooked$Companion;", "", "()V", "fishEntityHooked", "", "entity", "Lnet/minecraft/entity/Entity;", "JooonAddons"}
   )
   public static final class Companion {
      private Companion() {
      }

      @JvmStatic
      public final void fishEntityHooked(@NotNull Entity entity) {
         Intrinsics.checkNotNullParameter(entity, "entity");
         (new FishingHookHookedEvent(entity)).postAndCatch();
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
