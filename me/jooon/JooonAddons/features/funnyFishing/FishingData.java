package me.jooon.JooonAddons.features.funnyFishing;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.StringFormat;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import me.jooon.JooonAddons.config.PersistentSave;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0018B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R6\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR6\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000b¨\u0006\u0019"},
   d2 = {"Lme/jooon/JooonAddons/features/funnyFishing/FishingData;", "Lme/jooon/JooonAddons/config/PersistentSave;", "()V", "lastTimeCatched", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "getLastTimeCatched", "()Ljava/util/HashMap;", "setLastTimeCatched", "(Ljava/util/HashMap;)V", "mobsCatched", "", "getMobsCatched", "setMobsCatched", "read", "", "reader", "Ljava/io/Reader;", "setDefault", "writer", "Ljava/io/Writer;", "write", "SeaCreatureData", "JooonAddons"}
)
@SourceDebugExtension({"SMAP\nFishingData.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FishingData.kt\nme/jooon/JooonAddons/features/funnyFishing/FishingData\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 SerialFormat.kt\nkotlinx/serialization/SerialFormatKt\n*L\n1#1,57:1\n96#2:58\n1179#3,2:59\n1253#3,4:61\n113#4:65\n*S KotlinDebug\n*F\n+ 1 FishingData.kt\nme/jooon/JooonAddons/features/funnyFishing/FishingData\n*L\n35#1:58\n42#1:59,2\n42#1:61,4\n42#1:65\n*E\n"})
public final class FishingData extends PersistentSave {
   @NotNull
   public static final FishingData INSTANCE = new FishingData();
   @NotNull
   private static HashMap<String, Integer> mobsCatched = new HashMap();
   @NotNull
   private static HashMap<String, Double> lastTimeCatched = new HashMap();

   private FishingData() {
      super(new File("config/joonaddons", "fishydata.json"));
   }

   @NotNull
   public final HashMap<String, Integer> getMobsCatched() {
      return mobsCatched;
   }

   public final void setMobsCatched(@NotNull HashMap<String, Integer> <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      mobsCatched = var1;
   }

   @NotNull
   public final HashMap<String, Double> getLastTimeCatched() {
      return lastTimeCatched;
   }

   public final void setLastTimeCatched(@NotNull HashMap<String, Double> <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      lastTimeCatched = var1;
   }

   public void read(@NotNull Reader reader) {
      Intrinsics.checkNotNullParameter(reader, "reader");
      Json this_$iv = this.getJson();
      String string$iv = TextStreamsKt.readText(reader);
      int $i$f$decodeFromString = false;
      this_$iv.getSerializersModule();
      ((Map)this_$iv.decodeFromString((DeserializationStrategy)(new LinkedHashMapSerializer(StringSerializer.INSTANCE, FishingData.SeaCreatureData.Companion.serializer())), string$iv)).forEach(FishingData::read$lambda$0);
   }

   public void write(@NotNull Writer writer) {
      Intrinsics.checkNotNullParameter(writer, "writer");
      StringFormat $this$encodeToString$iv = (StringFormat)this.getJson();
      Set var10001 = mobsCatched.entrySet();
      Intrinsics.checkNotNullExpressionValue(var10001, "mobsCatched.entries");
      Iterable $this$associate$iv = (Iterable)var10001;
      int $i$f$encodeToString = false;
      int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv, 10)), 16);
      Map destination$iv$iv = (Map)(new LinkedHashMap(capacity$iv));
      int $i$f$associateTo = false;
      Iterator var9 = $this$associate$iv.iterator();

      while(var9.hasNext()) {
         Object element$iv$iv = var9.next();
         Entry it = (Entry)element$iv$iv;
         int var13 = false;
         Object var10000 = it.getKey();
         FishingData.SeaCreatureData var16 = new FishingData.SeaCreatureData;
         Object var10003 = it.getValue();
         Intrinsics.checkNotNullExpressionValue(var10003, "it.value");
         int var17 = ((Number)var10003).intValue();
         FishingData var10004 = INSTANCE;
         Double var18 = (Double)lastTimeCatched.get(it.getKey());
         if (var18 == null) {
            var18 = 0.0D;
         }

         Intrinsics.checkNotNullExpressionValue(var18, "lastTimeCatched[it.key] ?: 0.0");
         var16.<init>(var17, ((Number)var18).doubleValue());
         Pair var15 = TuplesKt.to(var10000, var16);
         destination$iv$iv.put(var15.getFirst(), var15.getSecond());
      }

      $i$f$encodeToString = false;
      $this$encodeToString$iv.getSerializersModule();
      writer.write($this$encodeToString$iv.encodeToString((SerializationStrategy)(new LinkedHashMapSerializer(StringSerializer.INSTANCE, FishingData.SeaCreatureData.Companion.serializer())), destination$iv$iv));
   }

   public void setDefault(@NotNull Writer writer) {
      Intrinsics.checkNotNullParameter(writer, "writer");
      writer.write("{}");
   }

   private static final void read$lambda$0(Function2 $tmp0, Object p0, Object p1) {
      Intrinsics.checkNotNullParameter($tmp0, "$tmp0");
      $tmp0.invoke(p0, p1);
   }

   @Serializable
   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0083\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B)\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J!\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eHÇ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006!"},
      d2 = {"Lme/jooon/JooonAddons/features/funnyFishing/FishingData$SeaCreatureData;", "", "seen1", "", "catches", "timeSince", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIDLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ID)V", "getCatches", "()I", "getTimeSince", "()D", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "JooonAddons"}
   )
   private static final class SeaCreatureData {
      @NotNull
      public static final FishingData.SeaCreatureData.Companion Companion = new FishingData.SeaCreatureData.Companion((DefaultConstructorMarker)null);
      private final int catches;
      private final double timeSince;

      public SeaCreatureData(int catches, double timeSince) {
         this.catches = catches;
         this.timeSince = timeSince;
      }

      public final int getCatches() {
         return this.catches;
      }

      public final double getTimeSince() {
         return this.timeSince;
      }

      public final int component1() {
         return this.catches;
      }

      public final double component2() {
         return this.timeSince;
      }

      @NotNull
      public final FishingData.SeaCreatureData copy(int catches, double timeSince) {
         return new FishingData.SeaCreatureData(catches, timeSince);
      }

      // $FF: synthetic method
      public static FishingData.SeaCreatureData copy$default(FishingData.SeaCreatureData var0, int var1, double var2, int var4, Object var5) {
         if ((var4 & 1) != 0) {
            var1 = var0.catches;
         }

         if ((var4 & 2) != 0) {
            var2 = var0.timeSince;
         }

         return var0.copy(var1, var2);
      }

      @NotNull
      public String toString() {
         return "SeaCreatureData(catches=" + this.catches + ", timeSince=" + this.timeSince + ')';
      }

      public int hashCode() {
         int result = Integer.hashCode(this.catches);
         result = result * 31 + Double.hashCode(this.timeSince);
         return result;
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof FishingData.SeaCreatureData)) {
            return false;
         } else {
            FishingData.SeaCreatureData var2 = (FishingData.SeaCreatureData)other;
            if (this.catches != var2.catches) {
               return false;
            } else {
               return Double.compare(this.timeSince, var2.timeSince) == 0;
            }
         }
      }

      // $FF: synthetic method
      @JvmStatic
      public static final void write$Self(FishingData.SeaCreatureData self, CompositeEncoder output, SerialDescriptor serialDesc) {
         output.encodeIntElement(serialDesc, 0, self.catches);
         output.encodeDoubleElement(serialDesc, 1, self.timeSince);
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
      public SeaCreatureData(int seen1, int catches, double timeSince, SerializationConstructorMarker serializationConstructorMarker) {
         if (3 != (3 & seen1)) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 3, FishingData$SeaCreatureData$$serializer.INSTANCE.getDescriptor());
         }

         super();
         this.catches = catches;
         this.timeSince = timeSince;
      }

      @Metadata(
         mv = {1, 8, 0},
         k = 1,
         xi = 48,
         d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"},
         d2 = {"Lme/jooon/JooonAddons/features/funnyFishing/FishingData$SeaCreatureData$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lme/jooon/JooonAddons/features/funnyFishing/FishingData$SeaCreatureData;", "JooonAddons"}
      )
      public static final class Companion {
         private Companion() {
         }

         @NotNull
         public final KSerializer<FishingData.SeaCreatureData> serializer() {
            return (KSerializer)FishingData$SeaCreatureData$$serializer.INSTANCE;
         }

         // $FF: synthetic method
         public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
         }
      }
   }
}
