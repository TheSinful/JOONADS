package me.jooon.JooonAddons.events;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"},
   d2 = {"Lme/jooon/JooonAddons/events/RenderHUDEvent;", "Lme/jooon/JooonAddons/events/JooonAddonsEvent;", "event", "Lnet/minecraftforge/client/event/RenderGameOverlayEvent;", "(Lnet/minecraftforge/client/event/RenderGameOverlayEvent;)V", "getEvent", "()Lnet/minecraftforge/client/event/RenderGameOverlayEvent;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "JooonAddons"}
)
public final class RenderHUDEvent extends JooonAddonsEvent {
   @NotNull
   private final RenderGameOverlayEvent event;

   public RenderHUDEvent(@NotNull RenderGameOverlayEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      super();
      this.event = event;
   }

   @NotNull
   public final RenderGameOverlayEvent getEvent() {
      return this.event;
   }

   @NotNull
   public final RenderGameOverlayEvent component1() {
      return this.event;
   }

   @NotNull
   public final RenderHUDEvent copy(@NotNull RenderGameOverlayEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      return new RenderHUDEvent(event);
   }

   // $FF: synthetic method
   public static RenderHUDEvent copy$default(RenderHUDEvent var0, RenderGameOverlayEvent var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.event;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "RenderHUDEvent(event=" + this.event + ')';
   }

   public int hashCode() {
      return this.event.hashCode();
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof RenderHUDEvent)) {
         return false;
      } else {
         RenderHUDEvent var2 = (RenderHUDEvent)other;
         return Intrinsics.areEqual(this.event, var2.event);
      }
   }
}
