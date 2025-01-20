package me.jooon.JooonAddons.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.JooonAddons;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007J\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\r"},
   d2 = {"Lme/jooon/JooonAddons/utils/ScoreboardUtil;", "", "()V", "sidebarLines", "", "", "getSidebarLines", "()Ljava/util/List;", "setSidebarLines", "(Ljava/util/List;)V", "cleanSB", "scoreboard", "fetchScoreboardLines", "JooonAddons"}
)
@SourceDebugExtension({"SMAP\nScoreboardUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ScoreboardUtil.kt\nme/jooon/JooonAddons/utils/ScoreboardUtil\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,49:1\n3864#2:50\n4387#2,2:51\n766#3:53\n857#3,2:54\n1549#3:56\n1620#3,3:57\n*S KotlinDebug\n*F\n+ 1 ScoreboardUtil.kt\nme/jooon/JooonAddons/utils/ScoreboardUtil\n*L\n30#1:50\n30#1:51,2\n38#1:53\n38#1:54,2\n42#1:56\n42#1:57,3\n*E\n"})
public final class ScoreboardUtil {
   @NotNull
   public static final ScoreboardUtil INSTANCE = new ScoreboardUtil();
   @NotNull
   private static List<String> sidebarLines = CollectionsKt.emptyList();

   private ScoreboardUtil() {
   }

   @JvmStatic
   @NotNull
   public static final String cleanSB(@NotNull String scoreboard) {
      Intrinsics.checkNotNullParameter(scoreboard, "scoreboard");
      char[] var10000 = Utils.INSTANCE.stripControlCodes(scoreboard).toCharArray();
      Intrinsics.checkNotNullExpressionValue(var10000, "this as java.lang.String).toCharArray()");
      char[] $this$filter$iv = var10000;
      int $i$f$filter = false;
      char[] $this$filterTo$iv$iv = $this$filter$iv;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      int $i$f$filterTo = false;
      int var6 = 0;

      for(int var7 = $this$filter$iv.length; var6 < var7; ++var6) {
         char element$iv$iv = $this$filterTo$iv$iv[var6];
         int var10 = false;
         if (' ' <= element$iv$iv ? element$iv$iv < 127 : false) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      return CollectionsKt.joinToString$default((Iterable)((List)destination$iv$iv), (CharSequence)"", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
   }

   @NotNull
   public final List<String> getSidebarLines() {
      return sidebarLines;
   }

   public final void setSidebarLines(@NotNull List<String> <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      sidebarLines = var1;
   }

   @NotNull
   public final List<String> fetchScoreboardLines() {
      WorldClient var10000 = JooonAddons.Companion.getMc().field_71441_e;
      Scoreboard var14 = var10000 != null ? var10000.func_96441_U() : null;
      if (var14 == null) {
         return CollectionsKt.emptyList();
      } else {
         Scoreboard scoreboard = var14;
         ScoreObjective var15 = scoreboard.func_96539_a(1);
         if (var15 == null) {
            return CollectionsKt.emptyList();
         } else {
            ScoreObjective objective = var15;
            Collection var16 = scoreboard.func_96534_i(objective);
            Intrinsics.checkNotNullExpressionValue(var16, "scoreboard.getSortedScores(objective)");
            Iterable $this$map$iv = (Iterable)var16;
            int $i$f$map = false;
            Collection destination$iv$iv = (Collection)(new ArrayList());
            int $i$f$mapTo = false;
            Iterator var9 = $this$map$iv.iterator();

            Object element$iv$iv;
            Score it;
            boolean var12;
            while(var9.hasNext()) {
               boolean var18;
               label42: {
                  element$iv$iv = var9.next();
                  it = (Score)element$iv$iv;
                  var12 = false;
                  if (it != null && it.func_96653_e() != null) {
                     String var17 = it.func_96653_e();
                     Intrinsics.checkNotNullExpressionValue(var17, "input.playerName");
                     if (!StringsKt.startsWith$default(var17, "#", false, 2, (Object)null)) {
                        var18 = true;
                        break label42;
                     }
                  }

                  var18 = false;
               }

               if (var18) {
                  destination$iv$iv.add(element$iv$iv);
               }
            }

            List scores = CollectionsKt.take((Iterable)((List)destination$iv$iv), 15);
            $this$map$iv = (Iterable)scores;
            $i$f$map = false;
            destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
            $i$f$mapTo = false;
            var9 = $this$map$iv.iterator();

            while(var9.hasNext()) {
               element$iv$iv = var9.next();
               it = (Score)element$iv$iv;
               var12 = false;
               destination$iv$iv.add(ScorePlayerTeam.func_96667_a((Team)scoreboard.func_96509_i(it.func_96653_e()), it.func_96653_e()));
            }

            return CollectionsKt.asReversed((List)destination$iv$iv);
         }
      }
   }
}
