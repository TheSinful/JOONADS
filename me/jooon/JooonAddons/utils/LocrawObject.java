package me.jooon.JooonAddons.utils;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 $2\u00020\u0001:\u0002#$BA\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB+\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\fJ\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001J!\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"HÇ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006%"},
   d2 = {"Lme/jooon/JooonAddons/utils/LocrawObject;", "", "seen1", "", "server", "", "gametype", "mode", "map", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getGametype", "()Ljava/lang/String;", "getMap", "getMode", "getServer", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "JooonAddons"}
)
public final class LocrawObject {
   @NotNull
   public static final LocrawObject.Companion Companion = new LocrawObject.Companion((DefaultConstructorMarker)null);
   @NotNull
   private final String server;
   @NotNull
   private final String gametype;
   @NotNull
   private final String mode;
   @NotNull
   private final String map;

   public LocrawObject(@NotNull String server, @NotNull String gametype, @NotNull String mode, @NotNull String map) {
      Intrinsics.checkNotNullParameter(server, "server");
      Intrinsics.checkNotNullParameter(gametype, "gametype");
      Intrinsics.checkNotNullParameter(mode, "mode");
      Intrinsics.checkNotNullParameter(map, "map");
      super();
      this.server = server;
      this.gametype = gametype;
      this.mode = mode;
      this.map = map;
   }

   // $FF: synthetic method
   public LocrawObject(String var1, String var2, String var3, String var4, int var5, DefaultConstructorMarker var6) {
      if ((var5 & 2) != 0) {
         var2 = "unknown";
      }

      if ((var5 & 4) != 0) {
         var3 = "unknown";
      }

      if ((var5 & 8) != 0) {
         var4 = "unknown";
      }

      this(var1, var2, var3, var4);
   }

   @NotNull
   public final String getServer() {
      return this.server;
   }

   @NotNull
   public final String getGametype() {
      return this.gametype;
   }

   @NotNull
   public final String getMode() {
      return this.mode;
   }

   @NotNull
   public final String getMap() {
      return this.map;
   }

   @NotNull
   public final String component1() {
      return this.server;
   }

   @NotNull
   public final String component2() {
      return this.gametype;
   }

   @NotNull
   public final String component3() {
      return this.mode;
   }

   @NotNull
   public final String component4() {
      return this.map;
   }

   @NotNull
   public final LocrawObject copy(@NotNull String server, @NotNull String gametype, @NotNull String mode, @NotNull String map) {
      Intrinsics.checkNotNullParameter(server, "server");
      Intrinsics.checkNotNullParameter(gametype, "gametype");
      Intrinsics.checkNotNullParameter(mode, "mode");
      Intrinsics.checkNotNullParameter(map, "map");
      return new LocrawObject(server, gametype, mode, map);
   }

   // $FF: synthetic method
   public static LocrawObject copy$default(LocrawObject var0, String var1, String var2, String var3, String var4, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = var0.server;
      }

      if ((var5 & 2) != 0) {
         var2 = var0.gametype;
      }

      if ((var5 & 4) != 0) {
         var3 = var0.mode;
      }

      if ((var5 & 8) != 0) {
         var4 = var0.map;
      }

      return var0.copy(var1, var2, var3, var4);
   }

   @NotNull
   public String toString() {
      return "LocrawObject(server=" + this.server + ", gametype=" + this.gametype + ", mode=" + this.mode + ", map=" + this.map + ')';
   }

   public int hashCode() {
      int result = this.server.hashCode();
      result = result * 31 + this.gametype.hashCode();
      result = result * 31 + this.mode.hashCode();
      result = result * 31 + this.map.hashCode();
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof LocrawObject)) {
         return false;
      } else {
         LocrawObject var2 = (LocrawObject)other;
         if (!Intrinsics.areEqual(this.server, var2.server)) {
            return false;
         } else if (!Intrinsics.areEqual(this.gametype, var2.gametype)) {
            return false;
         } else if (!Intrinsics.areEqual(this.mode, var2.mode)) {
            return false;
         } else {
            return Intrinsics.areEqual(this.map, var2.map);
         }
      }
   }

   // $FF: synthetic method
   @JvmStatic
   public static final void write$Self(LocrawObject self, CompositeEncoder output, SerialDescriptor serialDesc) {
      output.encodeStringElement(serialDesc, 0, self.server);
      if (output.shouldEncodeElementDefault(serialDesc, 1) ? true : !Intrinsics.areEqual(self.gametype, "unknown")) {
         output.encodeStringElement(serialDesc, 1, self.gametype);
      }

      if (output.shouldEncodeElementDefault(serialDesc, 2) ? true : !Intrinsics.areEqual(self.mode, "unknown")) {
         output.encodeStringElement(serialDesc, 2, self.mode);
      }

      if (output.shouldEncodeElementDefault(serialDesc, 3) ? true : !Intrinsics.areEqual(self.map, "unknown")) {
         output.encodeStringElement(serialDesc, 3, self.map);
      }

   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This synthesized declaration should not be used directly",
      replaceWith = @ReplaceWith(
   expression = "",
   imports = {}
),
      level = DeprecationLevel.HIDDEN
   )
   public LocrawObject(int seen1, String server, String gametype, String mode, String map, SerializationConstructorMarker serializationConstructorMarker) {
      if (1 != (1 & seen1)) {
         PluginExceptionsKt.throwMissingFieldException(seen1, 1, LocrawObject$$serializer.INSTANCE.getDescriptor());
      }

      super();
      this.server = server;
      if ((seen1 & 2) == 0) {
         this.gametype = "unknown";
      } else {
         this.gametype = gametype;
      }

      if ((seen1 & 4) == 0) {
         this.mode = "unknown";
      } else {
         this.mode = mode;
      }

      if ((seen1 & 8) == 0) {
         this.map = "unknown";
      } else {
         this.map = map;
      }

   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"},
      d2 = {"Lme/jooon/JooonAddons/utils/LocrawObject$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lme/jooon/JooonAddons/utils/LocrawObject;", "JooonAddons"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final KSerializer<LocrawObject> serializer() {
         return (KSerializer)LocrawObject$$serializer.INSTANCE;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
