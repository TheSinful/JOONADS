package me.jooon.JooonAddons.utils.core;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0015B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u0006\u0010\u0010\u001a\u00020\bJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"},
   d2 = {"Lme/jooon/JooonAddons/utils/core/CustomName;", "", "name", "", "animated", "", "nicks", "", "Lme/jooon/JooonAddons/utils/core/CustomName$Nick;", "(Ljava/lang/String;ZLjava/util/List;)V", "currentIndex", "", "getName", "()Ljava/lang/String;", "time", "", "getNick", "onWorldRender", "", "event", "Lnet/minecraftforge/client/event/RenderWorldLastEvent;", "Nick", "JooonAddons"}
)
public final class CustomName {
   @NotNull
   private final String name;
   private final boolean animated;
   @NotNull
   private final List<CustomName.Nick> nicks;
   private long time;
   private int currentIndex;

   public CustomName(@NotNull String name, boolean animated, @NotNull List<CustomName.Nick> nicks) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(nicks, "nicks");
      super();
      this.name = name;
      this.animated = animated;
      this.nicks = nicks;
      this.time = -1L;
      MinecraftForge.EVENT_BUS.register(this);
   }

   @NotNull
   public final String getName() {
      return this.name;
   }

   @SubscribeEvent
   public final void onWorldRender(@NotNull RenderWorldLastEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (this.animated) {
         long currentTime = System.currentTimeMillis();
         if (this.time == -1L) {
            this.time = currentTime;
         } else if (currentTime - this.time > (long)((CustomName.Nick)this.nicks.get(this.currentIndex)).getDelay()) {
            ++this.currentIndex;
            if (this.currentIndex >= this.nicks.size()) {
               this.currentIndex = 0;
            }

            this.time = currentTime;
            Cosmetics.INSTANCE.getNamesCache().entrySet().removeIf(CustomName::onWorldRender$lambda$0);
         }

      }
   }

   @NotNull
   public final CustomName.Nick getNick() {
      return (CustomName.Nick)this.nicks.get(this.currentIndex);
   }

   private static final boolean onWorldRender$lambda$0(Function1 $tmp0, Object p0) {
      Intrinsics.checkNotNullParameter($tmp0, "$tmp0");
      return (Boolean)$tmp0.invoke(p0);
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0016"},
      d2 = {"Lme/jooon/JooonAddons/utils/core/CustomName$Nick;", "", "nick", "", "prefix", "delay", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "getDelay", "()I", "getNick", "()Ljava/lang/String;", "getPrefix", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "JooonAddons"}
   )
   public static final class Nick {
      @NotNull
      private final String nick;
      @NotNull
      private final String prefix;
      private final int delay;

      public Nick(@NotNull String nick, @NotNull String prefix, int delay) {
         Intrinsics.checkNotNullParameter(nick, "nick");
         Intrinsics.checkNotNullParameter(prefix, "prefix");
         super();
         this.nick = nick;
         this.prefix = prefix;
         this.delay = delay;
      }

      @NotNull
      public final String getNick() {
         return this.nick;
      }

      @NotNull
      public final String getPrefix() {
         return this.prefix;
      }

      public final int getDelay() {
         return this.delay;
      }

      @NotNull
      public final String component1() {
         return this.nick;
      }

      @NotNull
      public final String component2() {
         return this.prefix;
      }

      public final int component3() {
         return this.delay;
      }

      @NotNull
      public final CustomName.Nick copy(@NotNull String nick, @NotNull String prefix, int delay) {
         Intrinsics.checkNotNullParameter(nick, "nick");
         Intrinsics.checkNotNullParameter(prefix, "prefix");
         return new CustomName.Nick(nick, prefix, delay);
      }

      // $FF: synthetic method
      public static CustomName.Nick copy$default(CustomName.Nick var0, String var1, String var2, int var3, int var4, Object var5) {
         if ((var4 & 1) != 0) {
            var1 = var0.nick;
         }

         if ((var4 & 2) != 0) {
            var2 = var0.prefix;
         }

         if ((var4 & 4) != 0) {
            var3 = var0.delay;
         }

         return var0.copy(var1, var2, var3);
      }

      @NotNull
      public String toString() {
         return "Nick(nick=" + this.nick + ", prefix=" + this.prefix + ", delay=" + this.delay + ')';
      }

      public int hashCode() {
         int result = this.nick.hashCode();
         result = result * 31 + this.prefix.hashCode();
         result = result * 31 + Integer.hashCode(this.delay);
         return result;
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof CustomName.Nick)) {
            return false;
         } else {
            CustomName.Nick var2 = (CustomName.Nick)other;
            if (!Intrinsics.areEqual(this.nick, var2.nick)) {
               return false;
            } else if (!Intrinsics.areEqual(this.prefix, var2.prefix)) {
               return false;
            } else {
               return this.delay == var2.delay;
            }
         }
      }
   }
}
