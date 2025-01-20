package me.jooon.JooonAddons.utils.core;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(
   mv = {1, 8, 0},
   k = 3,
   xi = 48,
   d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"},
   d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2"}
)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 Cosmetics.kt\nme/jooon/JooonAddons/utils/core/Cosmetics\n*L\n1#1,328:1\n37#2,2:329\n*E\n"})
public final class Cosmetics$loadCustomNicks$lambda$4$lambda$3$$inlined$sortedBy$1<T> implements Comparator {
   public final int compare(T a, T b) {
      JsonElement frame = (JsonElement)a;
      int var4 = false;
      Intrinsics.checkNotNull(frame, "null cannot be cast to non-null type com.google.gson.JsonObject");
      JsonObject var10000 = (JsonObject)frame;
      Comparable var6 = (Comparable)((JsonObject)frame).getAsJsonPrimitive("index").getAsInt();
      frame = (JsonElement)b;
      Comparable var5 = var6;
      var4 = false;
      Intrinsics.checkNotNull(frame, "null cannot be cast to non-null type com.google.gson.JsonObject");
      var10000 = (JsonObject)frame;
      return ComparisonsKt.compareValues(var5, (Comparable)((JsonObject)frame).getAsJsonPrimitive("index").getAsInt());
   }
}
