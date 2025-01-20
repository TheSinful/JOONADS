package me.jooon.JooonAddons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.json.Json;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.config.Cache;
import me.jooon.JooonAddons.events.impl.PacketEvent;
import me.jooon.JooonAddons.events.impl.SendChatMessageEvent;
import me.jooon.JooonAddons.features.betterlootshare.ESP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0007J\u0010\u00104\u001a\u0002012\u0006\u00102\u001a\u000205H\u0007J\u0010\u00106\u001a\u0002012\u0006\u00102\u001a\u000207H\u0007J\u0010\u00108\u001a\u0002012\u0006\u00102\u001a\u000209H\u0007J\u0010\u0010:\u001a\u0002012\u0006\u00102\u001a\u00020;H\u0007J\u0010\u0010<\u001a\u0002012\u0006\u00102\u001a\u00020=H\u0007R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u000eR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\f\"\u0004\b\"\u0010\u000eR\u001c\u0010#\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\f\"\u0004\b%\u0010\u000eR\u001a\u0010&\u001a\u00020'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\f\"\u0004\b.\u0010\u000eR\u000e\u0010/\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006>"},
   d2 = {"Lme/jooon/JooonAddons/utils/SBInfo;", "", "()V", "currentTimeDate", "Ljava/util/Date;", "getCurrentTimeDate", "()Ljava/util/Date;", "setCurrentTimeDate", "(Ljava/util/Date;)V", "date", "", "getDate", "()Ljava/lang/String;", "setDate", "(Ljava/lang/String;)V", "joinedWorld", "", "junkRegex", "Lkotlin/text/Regex;", "lastLocRaw", "lastManualLocRaw", "lastOpenContainerName", "lastSentToLimbo", "getLastSentToLimbo", "()J", "setLastSentToLimbo", "(J)V", "location", "getLocation", "setLocation", "locraw", "Lme/jooon/JooonAddons/utils/LocrawObject;", "mode", "getMode", "setMode", "objective", "getObjective", "setObjective", "onSkyblock", "", "getOnSkyblock", "()Z", "setOnSkyblock", "(Z)V", "time", "getTime", "setTime", "timePattern", "onChatMessage", "", "event", "Lme/jooon/JooonAddons/events/impl/PacketEvent$ReceiveEvent;", "onGuiOpen", "Lnet/minecraftforge/client/event/GuiOpenEvent;", "onPacket", "Lme/jooon/JooonAddons/events/impl/PacketEvent$SendEvent;", "onSendChatMessage", "Lme/jooon/JooonAddons/events/impl/SendChatMessageEvent;", "onTick", "Lnet/minecraftforge/fml/common/gameevent/TickEvent$ClientTickEvent;", "onWorldChange", "Lnet/minecraftforge/event/world/WorldEvent$Load;", "JooonAddons"}
)
@SourceDebugExtension({"SMAP\nSBInfo.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SBInfo.kt\nme/jooon/JooonAddons/utils/SBInfo\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,222:1\n96#2:223\n1549#3:224\n1620#3,3:225\n1#4:228\n*S KotlinDebug\n*F\n+ 1 SBInfo.kt\nme/jooon/JooonAddons/utils/SBInfo\n*L\n116#1:223\n154#1:224\n154#1:225,3\n*E\n"})
public final class SBInfo {
   @NotNull
   public static final SBInfo INSTANCE = new SBInfo();
   @NotNull
   private static final Regex timePattern = new Regex(".+(am|pm)");
   private static boolean onSkyblock;
   @NotNull
   private static String location = "";
   @NotNull
   private static String date = "";
   @NotNull
   private static String time = "";
   @Nullable
   private static String objective = "";
   @Nullable
   private static String mode = "";
   @Nullable
   private static Date currentTimeDate;
   private static long lastSentToLimbo;
   @JvmField
   @Nullable
   public static String lastOpenContainerName;
   private static long lastManualLocRaw = -1L;
   private static long lastLocRaw = -1L;
   private static long joinedWorld = -1L;
   @Nullable
   private static LocrawObject locraw;
   @NotNull
   private static final Regex junkRegex = new Regex("[^ -ħû]");

   private SBInfo() {
   }

   public final boolean getOnSkyblock() {
      return onSkyblock;
   }

   public final void setOnSkyblock(boolean <set-?>) {
      onSkyblock = var1;
   }

   @NotNull
   public final String getLocation() {
      return location;
   }

   public final void setLocation(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      location = var1;
   }

   @NotNull
   public final String getDate() {
      return date;
   }

   public final void setDate(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      date = var1;
   }

   @NotNull
   public final String getTime() {
      return time;
   }

   public final void setTime(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      time = var1;
   }

   @Nullable
   public final String getObjective() {
      return objective;
   }

   public final void setObjective(@Nullable String <set-?>) {
      objective = var1;
   }

   @Nullable
   public final String getMode() {
      return mode;
   }

   public final void setMode(@Nullable String <set-?>) {
      mode = var1;
   }

   @Nullable
   public final Date getCurrentTimeDate() {
      return currentTimeDate;
   }

   public final void setCurrentTimeDate(@Nullable Date <set-?>) {
      currentTimeDate = var1;
   }

   public final long getLastSentToLimbo() {
      return lastSentToLimbo;
   }

   public final void setLastSentToLimbo(long <set-?>) {
      lastSentToLimbo = var1;
   }

   @SubscribeEvent
   public final void onGuiOpen(@NotNull GuiOpenEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Utils.INSTANCE.getInSkyblock()) {
         if (event.gui instanceof GuiChest) {
            GuiScreen var10000 = event.gui;
            Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type net.minecraft.client.gui.inventory.GuiChest");
            GuiChest chest = (GuiChest)var10000;
            Container var5 = chest.field_147002_h;
            Intrinsics.checkNotNull(var5, "null cannot be cast to non-null type net.minecraft.inventory.ContainerChest");
            ContainerChest container = (ContainerChest)var5;
            String containerName = container.func_85151_d().func_145748_c_().func_150260_c();
            lastOpenContainerName = containerName;
         }

      }
   }

   @SubscribeEvent
   public final void onWorldChange(@NotNull Load event) {
      Intrinsics.checkNotNullParameter(event, "event");
      lastLocRaw = -1L;
      locraw = null;
      mode = null;
      joinedWorld = System.currentTimeMillis();
      lastOpenContainerName = null;
   }

   @SubscribeEvent
   public final void onSendChatMessage(@NotNull SendChatMessageEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      String msg = event.getMessage();
      if (StringsKt.startsWith$default(StringsKt.trim((CharSequence)msg).toString(), "/locraw", false, 2, (Object)null)) {
         lastManualLocRaw = System.currentTimeMillis();
      }

   }

   @SubscribeEvent(
      priority = EventPriority.LOW,
      receiveCanceled = true
   )
   public final void onChatMessage(@NotNull PacketEvent.ReceiveEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (event.getPacket() instanceof S02PacketChat) {
         String unformatted = ((S02PacketChat)event.getPacket()).func_148915_c().func_150260_c();
         if (Intrinsics.areEqual(unformatted, "You were spawned in Limbo.")) {
            lastSentToLimbo = System.currentTimeMillis();
         }

         Intrinsics.checkNotNullExpressionValue(unformatted, "unformatted");
         if (StringsKt.startsWith$default(unformatted, "{", false, 2, (Object)null) && StringsKt.endsWith$default(unformatted, "}", false, 2, (Object)null)) {
            try {
               Json this_$iv = JooonAddons.Companion.getJson();
               int $i$f$decodeFromString = false;
               this_$iv.getSerializersModule();
               LocrawObject obj = (LocrawObject)this_$iv.decodeFromString((DeserializationStrategy)LocrawObject.Companion.serializer(), unformatted);
               if (System.currentTimeMillis() - lastManualLocRaw > 6000L && !Loader.isModLoaded("skytils")) {
                  Utils.INSTANCE.cancelChatPacket(event);
               }

               locraw = obj;
               mode = obj.getMode();
               if (System.currentTimeMillis() - lastSentToLimbo > 60000L) {
                  Cache.INSTANCE.setLastLocation(obj.getMode());
               }
            } catch (SerializationException var7) {
               var7.printStackTrace();
            }
         }
      }

   }

   @SubscribeEvent
   public final void onPacket(@NotNull PacketEvent.SendEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (event.getPacket() instanceof C01PacketChatMessage) {
         String var10000 = ((C01PacketChatMessage)event.getPacket()).func_149439_c();
         Intrinsics.checkNotNullExpressionValue(var10000, "event.packet.message");
         if (StringsKt.startsWith$default(var10000, "/locraw", false, 2, (Object)null)) {
            lastLocRaw = System.currentTimeMillis();
         }
      }

   }

   @SubscribeEvent
   public final void onTick(@NotNull ClientTickEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (event.phase == Phase.START && JooonAddons.Companion.getMc().field_71439_g != null && JooonAddons.Companion.getMc().field_71441_e != null) {
         ScoreObjective scoreObjective = JooonAddons.Companion.getMc().field_71439_g.func_96123_co().func_96539_a(1);
         String var10000;
         boolean var33;
         if (scoreObjective != null) {
            label91: {
               var10000 = scoreObjective.func_96678_d();
               Intrinsics.checkNotNullExpressionValue(var10000, "scoreObjective.displayName");
               if (!StringsKt.contains$default((CharSequence)Utils.stripColor(var10000), (CharSequence)"SKYBLOCK", false, 2, (Object)null)) {
                  var10000 = scoreObjective.func_96678_d();
                  Intrinsics.checkNotNullExpressionValue(var10000, "scoreObjective.displayName");
                  if (!StringsKt.contains$default((CharSequence)Utils.stripColor(var10000), (CharSequence)"SKIBLOCK", false, 2, (Object)null)) {
                     var33 = false;
                     break label91;
                  }
               }

               var33 = true;
            }
         } else {
            var33 = false;
         }

         onSkyblock = var33;
         if (onSkyblock) {
            long currentTime = System.currentTimeMillis();
            if (locraw == null && currentTime - joinedWorld > 1300L && currentTime - lastLocRaw > 1000L) {
               lastLocRaw = System.currentTimeMillis();
               JooonAddons.Companion.getMc().field_71439_g.func_71165_d("/locraw");
            }

            try {
               Iterable $this$map$iv = (Iterable)ScoreboardUtil.INSTANCE.fetchScoreboardLines();
               int $i$f$map = false;
               Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
               int $i$f$mapTo = false;
               Iterator var11 = $this$map$iv.iterator();

               while(var11.hasNext()) {
                  Object item$iv$iv = var11.next();
                  String it = (String)item$iv$iv;
                  int var14 = false;
                  destination$iv$iv.add(Utils.INSTANCE.stripControlCodes(it));
               }

               List lines = (List)destination$iv$iv;
               if (lines.size() >= 5) {
                  date = StringsKt.trim((CharSequence)Utils.INSTANCE.stripControlCodes((String)lines.get(2))).toString();
                  MatchResult matcher = Regex.find$default(timePattern, (CharSequence)lines.get(3), 0, 2, (Object)null);
                  if (matcher != null) {
                     time = StringsKt.trim((CharSequence)Utils.INSTANCE.stripControlCodes((String)matcher.getGroupValues().get(0))).toString();

                     try {
                        String timeSpace = StringsKt.replace$default(StringsKt.replace$default(time, "am", " am", false, 4, (Object)null), "pm", " pm", false, 4, (Object)null);
                        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
                        currentTimeDate = parseFormat.parse(timeSpace);
                     } catch (ParseException var16) {
                        ESP.INSTANCE.getLogger().info("Error");
                     }
                  }

                  Iterable var23 = (Iterable)lines;
                  Iterator var26 = var23.iterator();

                  Object var34;
                  while(true) {
                     if (var26.hasNext()) {
                        Object var28 = var26.next();
                        String it = (String)var28;
                        int var32 = false;
                        if (!StringsKt.contains$default((CharSequence)it, '⏣', false, 2, (Object)null)) {
                           continue;
                        }

                        var34 = var28;
                        break;
                     }

                     var34 = null;
                     break;
                  }

                  var10000 = (String)var34;
                  if ((String)var34 != null) {
                     CharSequence var24 = (CharSequence)var10000;
                     Regex var27 = junkRegex;
                     String it = "";
                     var10000 = var27.replace(var24, it);
                     if (var10000 != null) {
                        var10000 = StringsKt.trim((CharSequence)var10000).toString();
                        if (var10000 != null) {
                           it = var10000;
                           int var31 = false;
                           SBInfo var35 = INSTANCE;
                           location = it;
                        }
                     }
                  }
               }

               objective = null;
               Iterator var19 = lines.iterator();
               int var21 = 0;

               while(var19.hasNext()) {
                  int i = var21++;
                  String line = (String)var19.next();
                  if (Intrinsics.areEqual(line, "Objective")) {
                     objective = (String)lines.get(i + 1);
                     break;
                  }
               }
            } catch (Exception var17) {
               var17.printStackTrace();
            }

         }
      }
   }
}
