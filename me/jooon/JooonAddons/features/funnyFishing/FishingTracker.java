package me.jooon.JooonAddons.features.funnyFishing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.config.PersistentSave;
import me.jooon.JooonAddons.gui.GuiElement;
import me.jooon.JooonAddons.render.font.FontUtils;
import me.jooon.JooonAddons.utils.core.FloatPair;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Items;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.WorldEvent.Unload;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001&B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u000e\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u0005J\u0006\u0010\u001c\u001a\u00020\u0005J\u0006\u0010\u001d\u001a\u00020\u0005J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u001fH\u0007J\u0010\u0010 \u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020!H\u0007J\u0016\u0010\"\u001a\u00020#2\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%R6\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR6\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR&\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006'"},
   d2 = {"Lme/jooon/JooonAddons/features/funnyFishing/FishingTracker;", "", "()V", "lastTimeCatched", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "getLastTimeCatched", "()Ljava/util/HashMap;", "setLastTimeCatched", "(Ljava/util/HashMap;)V", "mobsCatched", "", "getMobsCatched", "setMobsCatched", "seaCreatureMessages", "", "getSeaCreatureMessages", "()Ljava/util/Map;", "setSeaCreatureMessages", "(Ljava/util/Map;)V", "autoDetectFishingType", "", "event", "Lnet/minecraftforge/fml/common/gameevent/TickEvent$PlayerTickEvent;", "getFuckingOffset", "text", "getTimeSinceMobs", "getTrackerMobs", "listenForSeaCreatureCatches", "Lnet/minecraftforge/client/event/ClientChatReceivedEvent;", "saveData", "Lnet/minecraftforge/event/world/WorldEvent$Unload;", "textXoffset", "", "element", "Lme/jooon/JooonAddons/gui/GuiElement;", "FishingTrackerGUIElement", "JooonAddons"}
)
@SourceDebugExtension({"SMAP\nFishingTracker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FishingTracker.kt\nme/jooon/JooonAddons/features/funnyFishing/FishingTracker\n+ 2 PersistentSave.kt\nme/jooon/JooonAddons/config/PersistentSave$Companion\n*L\n1#1,454:1\n90#2,2:455\n*S KotlinDebug\n*F\n+ 1 FishingTracker.kt\nme/jooon/JooonAddons/features/funnyFishing/FishingTracker\n*L\n302#1:455,2\n*E\n"})
public final class FishingTracker {
   @NotNull
   public static final FishingTracker INSTANCE = new FishingTracker();
   @NotNull
   private static Map<String, String> seaCreatureMessages;
   @NotNull
   private static HashMap<String, Integer> mobsCatched;
   @NotNull
   private static HashMap<String, Double> lastTimeCatched;

   private FishingTracker() {
   }

   @NotNull
   public final Map<String, String> getSeaCreatureMessages() {
      return seaCreatureMessages;
   }

   public final void setSeaCreatureMessages(@NotNull Map<String, String> <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      seaCreatureMessages = var1;
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

   @NotNull
   public final String getTrackerMobs() {
      String text = "";
      text = Config.INSTANCE.getFishingTrackerType() == 0 ? "-----------------.§fSquid.§fSea Walker.§fSea Guardian.§aSea Witch.§aSea Archer.§aRider Of The Deep.§9Catfish.§9Carrot King.§9Sea Leech.§5Guardian Defender.§5Deep Sea Protector.§6Water Hydra.§6Sea Emperor." : (Config.INSTANCE.getFishingTrackerType() == 1 ? "-----------------.§9Magma Slug.§9Moogma.§9Lava Leech.§9Pyroclastic Worm.§9Lava Flame.§9Fire Eel.§9Taurus.§dThunder.§dLord Jawbus." : "");
      if (Config.INSTANCE.getFishingTrackerMarina()) {
         text = text + "-----------------.§cNurse Shark.§cBlue Shark.§cTiger Shark.§cGreat White Shark.";
      }

      if (Config.INSTANCE.getFishingTrackerSpooky()) {
         text = text + "-----------------.§fScarecrow.§aNightmare.§5Werewolf.§6Phantom Fisher.§6Grim Reaper.";
      }

      if (Config.INSTANCE.getFishingTrackerWinter()) {
         text = text + "-----------------.§fFrozen Steve.§fFrosty The Snowman.§aGrinch.§9Nutcracker.§6Yeti.§6Reindrake.";
      }

      text = text + "-----------------";
      return text;
   }

   public final int getFuckingOffset(@NotNull String text) {
      Intrinsics.checkNotNullParameter(text, "text");
      byte var10000;
      switch(text.length()) {
      case 4:
         var10000 = 6;
         break;
      case 5:
         var10000 = 12;
         break;
      case 6:
         var10000 = 18;
         break;
      case 7:
         var10000 = 24;
         break;
      case 8:
         var10000 = 30;
         break;
      case 9:
         var10000 = 36;
         break;
      case 10:
         var10000 = 42;
         break;
      default:
         var10000 = 0;
      }

      return var10000;
   }

   @NotNull
   public final String getTimeSinceMobs() {
      if (!Config.INSTANCE.getFishingTrackerTimeSince()) {
         return "";
      } else {
         String text = "§lTime Since.";
         if (Config.INSTANCE.getFishingTrackerMarina()) {
            text = text + "§cGreat White.";
         }

         if (Config.INSTANCE.getFishingTrackerSpooky()) {
            text = text + "§6Grim Reaper.";
         }

         if (Config.INSTANCE.getFishingTrackerWinter()) {
            text = text + "§6Yeti.§6Reindrake.";
         }

         if (Config.INSTANCE.getFishingTrackerType() == 1) {
            text = text + "§dThunder.§dLord Jawbus.";
         }

         if (Config.INSTANCE.getFishingTrackerType() == 0) {
            text = text + "§6Sea Emperor.";
         }

         text = text + "-----------------";
         return text;
      }
   }

   public final float textXoffset(@NotNull String text, @NotNull GuiElement element) {
      Intrinsics.checkNotNullParameter(text, "text");
      Intrinsics.checkNotNullParameter(element, "element");
      float var10000;
      if (element.getActualX() + element.getActualWidth() / (float)2 < (float)(JooonAddons.Companion.getMc().field_71443_c / 4)) {
         var10000 = 4.0F;
      } else {
         float offset = (float)(element.getWidth() - GuiElement.Companion.getFr().func_78256_a(text)) - 4.0F;
         var10000 = offset;
      }

      return var10000;
   }

   @SubscribeEvent
   public final void listenForSeaCreatureCatches(@NotNull ClientChatReceivedEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      Iterator var2 = seaCreatureMessages.entrySet().iterator();

      while(var2.hasNext()) {
         Entry mobMessage = (Entry)var2.next();
         String var10000 = event.message.func_150260_c();
         Intrinsics.checkNotNullExpressionValue(var10000, "event.message.unformattedText");
         if (StringsKt.startsWith(var10000, (String)mobMessage.getKey(), true)) {
            String var4 = (String)mobMessage.getValue();
            switch(var4.hashCode()) {
            case -1206209605:
               if (var4.equals("Great White Shark")) {
                  ((Map)lastTimeCatched).put("Great White Shark", (double)System.currentTimeMillis() / 1000.0D);
               }
               break;
            case -744791801:
               if (var4.equals("Sea Emperor")) {
                  ((Map)lastTimeCatched).put("Sea Emperor", (double)System.currentTimeMillis() / 1000.0D);
               }
               break;
            case -624388117:
               if (var4.equals("Lord Jawbus")) {
                  ((Map)lastTimeCatched).put("Lord Jawbus", (double)System.currentTimeMillis() / 1000.0D);
               }
               break;
            case -308535691:
               if (var4.equals("Reindrake")) {
                  ((Map)lastTimeCatched).put("Reindrake", (double)System.currentTimeMillis() / 1000.0D);
               }
               break;
            case 2752161:
               if (var4.equals("Yeti")) {
                  ((Map)lastTimeCatched).put("Yeti", (double)System.currentTimeMillis() / 1000.0D);
               }
               break;
            case 329757892:
               if (var4.equals("Thunder")) {
                  ((Map)lastTimeCatched).put("Thunder", (double)System.currentTimeMillis() / 1000.0D);
               }
               break;
            case 1300590528:
               if (var4.equals("Grim Reaper")) {
                  ((Map)lastTimeCatched).put("Grim Reaper", (double)System.currentTimeMillis() / 1000.0D);
               }
            }

            if (mobsCatched.get(mobMessage.getValue()) != null) {
               Map var5 = (Map)mobsCatched;
               Object var10001 = mobMessage.getValue();
               Object var10002 = mobsCatched.get(mobMessage.getValue());
               Intrinsics.checkNotNull(var10002);
               var5.put(var10001, ((Number)var10002).intValue() + 1);
            } else {
               ((Map)mobsCatched).put(mobMessage.getValue(), 1);
            }
         }
      }

   }

   @SubscribeEvent
   public final void autoDetectFishingType(@NotNull PlayerTickEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getFishingTracker()) {
         if (Config.INSTANCE.getFishingTrackerTypeAutoDetect()) {
            if (event.player.field_71104_cf != null) {
               if (Config.INSTANCE.getFishingTrackerType() != 2) {
                  if (event.player.field_71104_cf.func_70090_H()) {
                     Config.INSTANCE.setFishingTrackerType(0);
                  }

                  if (event.player.field_71104_cf.func_180799_ab()) {
                     Config.INSTANCE.setFishingTrackerType(1);
                  }

               }
            }
         }
      }
   }

   @SubscribeEvent
   public final void saveData(@NotNull Unload event) {
      Intrinsics.checkNotNullParameter(event, "event");
      PersistentSave.Companion this_$iv = PersistentSave.Companion;
      int $i$f$markDirty = false;
      this_$iv.markDirty(Reflection.getOrCreateKotlinClass(FishingData.class));
   }

   static {
      new FishingTracker.FishingTrackerGUIElement();
      Pair[] var0 = new Pair[]{TuplesKt.to("A Squid appeared.", "Squid"), TuplesKt.to("You caught a Sea Walker.", "Sea Walker"), TuplesKt.to("You stumbled upon a Sea Guardian.", "Sea Guardian"), TuplesKt.to("It looks like you've disrupted the Sea Witch's brewing session. Watch out, she's furious!", "Sea Witch"), TuplesKt.to("You reeled in a Sea Archer.", "Sea Archer"), TuplesKt.to("The Rider of the Deep has emerged.", "Rider Of The Deep"), TuplesKt.to("Huh? A Catfish!", "Catfish"), TuplesKt.to("Is this even a fish? It's the Carrot King!", "Carrot King"), TuplesKt.to("Gross! A Sea Leech!", "Sea Leech"), TuplesKt.to("You've discovered a Guardian Defender of the sea.", "Guardian Defender"), TuplesKt.to("You have awoken the Deep Sea Protector, prepare for a battle!", "Deep Sea Protector"), TuplesKt.to("The Water Hydra has come to test your strength.", "Water Hydra"), TuplesKt.to("The Sea Emperor arises from the depths.", "Sea Emperor"), TuplesKt.to("Phew! It's only a Scarecrow.", "Scarecrow"), TuplesKt.to("You hear trotting from beneath the waves, you caught a Nightmare.", "Nightmare"), TuplesKt.to("It must be a full moon, a Werewolf appears.", "Werewolf"), TuplesKt.to("The spirit of a long lost Phantom Fisher has come to haunt you.", "Phantom Fisher"), TuplesKt.to("This can't be! The manifestation of death himself!", "Grim Reaper"), TuplesKt.to("Frozen Steve fell into the pond long ago, never to resurface...until now!", "Frozen Steve"), TuplesKt.to("It's a snowman! He looks harmless", "Frosty The Snowman"), TuplesKt.to("The Grinch stole Jerry's Gifts...get them back!", "Grinch"), TuplesKt.to("What is this creature!?", "Yeti"), TuplesKt.to("You found a forgotten Nutcracker laying beneath the ice.", "Nutcracker"), TuplesKt.to("A Reindrake forms from the depths.", "Reindrake"), TuplesKt.to("A tiny fin emerges from the water, you've caught a Nurse Shark.", "Nurse Shark"), TuplesKt.to("You spot a fin as blue as the water it came from, it's a Blue Shark.", "Blue Shark"), TuplesKt.to("A striped beast bounds from the depths, the wild Tiger Shark!", "Tiger Shark"), TuplesKt.to("Hide no longer, a Great White Shark has tracked your scent and thirsts for your blood!", "Great White Shark"), TuplesKt.to("From beneath the lava appears a Magma Slug.", "Magma Slug"), TuplesKt.to("You hear a faint Moo from the lava... A Moogma appears.", "Moogma"), TuplesKt.to("A small but fearsome Lava Leech emerges.", "Lava Leech"), TuplesKt.to("You feel the heat radiating as a Pyroclastic Worm surfaces.", "Pyroclastic Worm"), TuplesKt.to("A Lava Flame flies out from beneath the lava.", "Lava Flame"), TuplesKt.to("A Fire Eel slithers out from the depths.", "Fire Eel"), TuplesKt.to("Taurus and his steed emerge.", "Taurus"), TuplesKt.to("You hear a massive rumble as Thunder emerges.", "Thunder"), TuplesKt.to("You have angered a legendary creature... Lord Jawbus has arrived", "Lord Jawbus"), TuplesKt.to("A Water Worm surfaces!", "Water Worm"), TuplesKt.to("A Poisoned Water Worm surfaces!", "Poisoned Water Worm"), TuplesKt.to("A Zombie miner surfaces!", "Zombie Miner"), TuplesKt.to("A flaming worm surfaces from the depths!", "Flaming Worm"), TuplesKt.to("A Lava Blaze has surfaced from the depths!", "Lava Blaze"), TuplesKt.to("A Lava Pigman arose from the depths!", "Lava Pigman")};
      seaCreatureMessages = MapsKt.mapOf(var0);
      mobsCatched = FishingData.INSTANCE.getMobsCatched();
      lastTimeCatched = FishingData.INSTANCE.getLastTimeCatched();
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006¨\u0006\u0010"},
      d2 = {"Lme/jooon/JooonAddons/features/funnyFishing/FishingTracker$FishingTrackerGUIElement;", "Lme/jooon/JooonAddons/gui/GuiElement;", "()V", "height", "", "getHeight", "()I", "toggled", "", "getToggled", "()Z", "width", "getWidth", "demoRender", "", "render", "JooonAddons"}
   )
   @SourceDebugExtension({"SMAP\nFishingTracker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FishingTracker.kt\nme/jooon/JooonAddons/features/funnyFishing/FishingTracker$FishingTrackerGUIElement\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,454:1\n1940#2,14:455\n1940#2,14:469\n1940#2,14:483\n1940#2,14:497\n1940#2,14:511\n1940#2,14:525\n1940#2,14:539\n1940#2,14:553\n748#2,10:567\n*S KotlinDebug\n*F\n+ 1 FishingTracker.kt\nme/jooon/JooonAddons/features/funnyFishing/FishingTracker$FishingTrackerGUIElement\n*L\n119#1:455,14\n121#1:469,14\n145#1:483,14\n147#1:497,14\n168#1:511,14\n170#1:525,14\n194#1:539,14\n196#1:553,14\n208#1:567,10\n*E\n"})
   public static final class FishingTrackerGUIElement extends GuiElement {
      public FishingTrackerGUIElement() {
         super("Fishing Tracker", new FloatPair(10, 10));
         JooonAddons.Companion.getGuiManager().registerElement((GuiElement)this);
      }

      public void render() {
         if (this.getToggled()) {
            if (JooonAddons.Companion.getMc().field_71439_g.func_70694_bm() != null) {
               if (Intrinsics.areEqual(JooonAddons.Companion.getMc().field_71439_g.func_70694_bm().func_77973_b(), Items.field_151112_aM)) {
                  if (Config.INSTANCE.getFishingTrackerSpooky() || Config.INSTANCE.getFishingTrackerMarina() || Config.INSTANCE.getFishingTrackerWinter() || Config.INSTANCE.getFishingTrackerType() == 1 || Config.INSTANCE.getFishingTrackerType() == 0) {
                     boolean left = this.getActualX() + this.getActualWidth() / (float)2 < (float)(JooonAddons.Companion.getMc().field_71443_c / 4);
                     float xOffset = 0.0F;
                     float offset = 6.0F;
                     CharSequence var10000 = (CharSequence)FishingTracker.INSTANCE.getTrackerMobs();
                     String[] var5 = new String[]{"."};

                     Iterator var4;
                     String textToDisplay;
                     byte var8;
                     byte var9;
                     Iterator iterator$iv;
                     Object maxElem$iv;
                     String it;
                     boolean var13;
                     String it;
                     boolean var15;
                     FontRenderer var16;
                     CharSequence var10001;
                     FontRenderer var17;
                     CharSequence var10002;
                     Object var18;
                     String mob;
                     String[] var21;
                     Iterable $this$maxBy$iv;
                     boolean $i$f$maxByOrThrow;
                     int maxValue$iv;
                     Object e$iv;
                     int v$iv;
                     int var27;
                     HashMap var28;
                     FontRenderer var30;
                     FontRenderer var32;
                     Object var34;
                     float var35;
                     int var36;
                     for(var4 = StringsKt.split$default(var10000, var5, false, 0, 6, (Object)null).iterator(); var4.hasNext(); offset += 9.0F) {
                        mob = (String)var4.next();
                        GuiElement.Companion.getFr().func_175065_a(mob, 0.0F + FishingTracker.INSTANCE.textXoffset(mob, (GuiElement)this), 0.0F + offset, 16777215, true);
                        if (!StringsKt.startsWith$default(mob, "-", false, 2, (Object)null)) {
                           var28 = FishingTracker.INSTANCE.getMobsCatched();
                           var8 = 0;
                           var9 = 2;
                           Integer var29;
                           if (var28.get(StringsKt.removeRange((CharSequence)mob, var8, var9).toString()) == null) {
                              var29 = 0;
                           } else {
                              var28 = FishingTracker.INSTANCE.getMobsCatched();
                              var8 = 0;
                              var9 = 2;
                              var29 = (Integer)var28.get(StringsKt.removeRange((CharSequence)mob, var8, var9).toString());
                           }

                           Integer valToDisplay = var29;
                           textToDisplay = left ? ": " + valToDisplay : valToDisplay + " :";
                           if (left) {
                              var32 = GuiElement.Companion.getFr();
                              var10001 = (CharSequence)FishingTracker.INSTANCE.getTrackerMobs();
                              var21 = new String[]{"."};
                              $this$maxBy$iv = (Iterable)StringsKt.split$default(var10001, var21, false, 0, 6, (Object)null);
                              var16 = var32;
                              $i$f$maxByOrThrow = false;
                              iterator$iv = $this$maxBy$iv.iterator();
                              if (!iterator$iv.hasNext()) {
                                 throw new NoSuchElementException();
                              }

                              maxElem$iv = iterator$iv.next();
                              if (!iterator$iv.hasNext()) {
                                 var34 = maxElem$iv;
                              } else {
                                 it = (String)maxElem$iv;
                                 var13 = false;
                                 maxValue$iv = it.length();

                                 while(true) {
                                    e$iv = iterator$iv.next();
                                    it = (String)e$iv;
                                    var15 = false;
                                    v$iv = it.length();
                                    if (maxValue$iv < v$iv) {
                                       maxElem$iv = e$iv;
                                       maxValue$iv = v$iv;
                                    }

                                    if (!iterator$iv.hasNext()) {
                                       var34 = maxElem$iv;
                                       break;
                                    }
                                 }
                              }

                              var35 = (float)var16.func_78256_a((String)var34) + 8.0F;
                           } else {
                              var36 = this.getWidth();
                              var30 = GuiElement.Companion.getFr();
                              var10002 = (CharSequence)FishingTracker.INSTANCE.getTrackerMobs();
                              var21 = new String[]{"."};
                              $this$maxBy$iv = (Iterable)StringsKt.split$default(var10002, var21, false, 0, 6, (Object)null);
                              var17 = var30;
                              var27 = var36;
                              $i$f$maxByOrThrow = false;
                              iterator$iv = $this$maxBy$iv.iterator();
                              if (!iterator$iv.hasNext()) {
                                 throw new NoSuchElementException();
                              }

                              maxElem$iv = iterator$iv.next();
                              if (!iterator$iv.hasNext()) {
                                 var34 = maxElem$iv;
                              } else {
                                 it = (String)maxElem$iv;
                                 var13 = false;
                                 maxValue$iv = it.length();

                                 while(true) {
                                    e$iv = iterator$iv.next();
                                    it = (String)e$iv;
                                    var15 = false;
                                    v$iv = it.length();
                                    if (maxValue$iv < v$iv) {
                                       maxElem$iv = e$iv;
                                       maxValue$iv = v$iv;
                                    }

                                    if (!iterator$iv.hasNext()) {
                                       var34 = maxElem$iv;
                                       break;
                                    }
                                 }
                              }

                              var18 = var34;
                              var35 = (float)(var27 - var17.func_78256_a((String)var18)) - 20.0F - (float)FishingTracker.INSTANCE.getFuckingOffset(textToDisplay);
                           }

                           xOffset = var35;
                           GuiElement.Companion.getFr().func_175065_a(textToDisplay, xOffset, 0.0F + offset, 16777215, true);
                        }
                     }

                     if (!Config.INSTANCE.getFishingTrackerTimeSince()) {
                        return;
                     }

                     var10000 = (CharSequence)FishingTracker.INSTANCE.getTimeSinceMobs();
                     var5 = new String[]{"."};

                     for(var4 = StringsKt.split$default(var10000, var5, false, 0, 6, (Object)null).iterator(); var4.hasNext(); offset += 9.0F) {
                        mob = (String)var4.next();
                        GuiElement.Companion.getFr().func_175065_a(mob, 0.0F + FishingTracker.INSTANCE.textXoffset(mob, (GuiElement)this), 0.0F + offset, 16777215, true);
                        if (!StringsKt.startsWith$default(mob, "-", false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)mob, (CharSequence)"Time Since", false, 2, (Object)null)) {
                           Object var33;
                           String var37;
                           FontUtils var38;
                           label147: {
                              var28 = FishingTracker.INSTANCE.getLastTimeCatched();
                              var8 = 0;
                              var9 = 2;
                              if (var28.get(StringsKt.removeRange((CharSequence)mob, var8, var9).toString()) != null) {
                                 var28 = FishingTracker.INSTANCE.getLastTimeCatched();
                                 var8 = 0;
                                 var9 = 2;
                                 if (!Intrinsics.areEqual((Double)var28.get(StringsKt.removeRange((CharSequence)mob, var8, var9).toString()), 0.0D)) {
                                    var38 = FontUtils.INSTANCE;
                                    HashMap var31 = FishingTracker.INSTANCE.getLastTimeCatched();
                                    var8 = 0;
                                    var9 = 2;
                                    var33 = var31.get(StringsKt.removeRange((CharSequence)mob, var8, var9).toString());
                                    Intrinsics.checkNotNull(var33);
                                    var37 = var38.getTimeBetween(((Number)var33).doubleValue(), (double)(System.currentTimeMillis() / (long)1000));
                                    break label147;
                                 }
                              }

                              var37 = "Never";
                           }

                           String timeSince = var37;
                           if (StringsKt.contains$default((CharSequence)mob, (CharSequence)"Great White", false, 2, (Object)null)) {
                              if (FishingTracker.INSTANCE.getLastTimeCatched().get("Great White Shark") != null && !Intrinsics.areEqual((Double)FishingTracker.INSTANCE.getLastTimeCatched().get("Great White Shark"), 0.0D)) {
                                 var38 = FontUtils.INSTANCE;
                                 var33 = FishingTracker.INSTANCE.getLastTimeCatched().get("Great White Shark");
                                 Intrinsics.checkNotNull(var33);
                                 timeSince = var38.getTimeBetween(((Number)var33).doubleValue(), (double)System.currentTimeMillis() / 1000.0D);
                              } else {
                                 timeSince = "Never";
                              }
                           }

                           textToDisplay = left ? ": " + timeSince : timeSince + " :";
                           if (left) {
                              var32 = GuiElement.Companion.getFr();
                              var10001 = (CharSequence)FishingTracker.INSTANCE.getTimeSinceMobs();
                              var21 = new String[]{"."};
                              $this$maxBy$iv = (Iterable)CollectionsKt.dropLast(StringsKt.split$default(var10001, var21, false, 0, 6, (Object)null), 1);
                              var16 = var32;
                              $i$f$maxByOrThrow = false;
                              iterator$iv = $this$maxBy$iv.iterator();
                              if (!iterator$iv.hasNext()) {
                                 throw new NoSuchElementException();
                              }

                              maxElem$iv = iterator$iv.next();
                              if (!iterator$iv.hasNext()) {
                                 var34 = maxElem$iv;
                              } else {
                                 it = (String)maxElem$iv;
                                 var13 = false;
                                 maxValue$iv = it.length();

                                 while(true) {
                                    e$iv = iterator$iv.next();
                                    it = (String)e$iv;
                                    var15 = false;
                                    v$iv = it.length();
                                    if (maxValue$iv < v$iv) {
                                       maxElem$iv = e$iv;
                                       maxValue$iv = v$iv;
                                    }

                                    if (!iterator$iv.hasNext()) {
                                       var34 = maxElem$iv;
                                       break;
                                    }
                                 }
                              }

                              var35 = (float)var16.func_78256_a((String)var34) + 14.0F;
                           } else {
                              var36 = this.getWidth();
                              var30 = GuiElement.Companion.getFr();
                              var10002 = (CharSequence)FishingTracker.INSTANCE.getTimeSinceMobs();
                              var21 = new String[]{"."};
                              $this$maxBy$iv = (Iterable)StringsKt.split$default(var10002, var21, false, 0, 6, (Object)null);
                              var17 = var30;
                              var27 = var36;
                              $i$f$maxByOrThrow = false;
                              iterator$iv = $this$maxBy$iv.iterator();
                              if (!iterator$iv.hasNext()) {
                                 throw new NoSuchElementException();
                              }

                              maxElem$iv = iterator$iv.next();
                              if (!iterator$iv.hasNext()) {
                                 var34 = maxElem$iv;
                              } else {
                                 it = (String)maxElem$iv;
                                 var13 = false;
                                 maxValue$iv = it.length();

                                 while(true) {
                                    e$iv = iterator$iv.next();
                                    it = (String)e$iv;
                                    var15 = false;
                                    v$iv = it.length();
                                    if (maxValue$iv < v$iv) {
                                       maxElem$iv = e$iv;
                                       maxValue$iv = v$iv;
                                    }

                                    if (!iterator$iv.hasNext()) {
                                       var34 = maxElem$iv;
                                       break;
                                    }
                                 }
                              }

                              var18 = var34;
                              var35 = (float)(var27 - var17.func_78256_a((String)var18)) + 20.0F - (float)FishingTracker.INSTANCE.getFuckingOffset(textToDisplay);
                           }

                           xOffset = var35;
                           GuiElement.Companion.getFr().func_175065_a(textToDisplay, 0.0F + xOffset, 0.0F + offset, 16777215, true);
                        }
                     }
                  }

               }
            }
         }
      }

      public void demoRender() {
         if (!Config.INSTANCE.getFishingTrackerSpooky() && !Config.INSTANCE.getFishingTrackerMarina() && !Config.INSTANCE.getFishingTrackerWinter() && Config.INSTANCE.getFishingTrackerType() != 1 && Config.INSTANCE.getFishingTrackerType() != 0) {
            GuiElement.Companion.getFr().func_175065_a("Timer Is Empty", 25.0F, 12.0F, 16777215, true);
         } else {
            boolean left = this.getActualX() + this.getActualWidth() / (float)2 < (float)(JooonAddons.Companion.getMc().field_71443_c / 4);
            float xOffset = 0.0F;
            float offset = 6.0F;
            CharSequence var10000 = (CharSequence)FishingTracker.INSTANCE.getTrackerMobs();
            String[] var5 = new String[]{"."};

            Iterator var4;
            String textToDisplay;
            byte var8;
            byte var9;
            Iterator iterator$iv;
            Object maxElem$iv;
            String it;
            boolean var13;
            String it;
            boolean var15;
            FontRenderer var16;
            CharSequence var10001;
            FontRenderer var17;
            CharSequence var10002;
            Object var18;
            String mob;
            String[] var21;
            Iterable $this$maxBy$iv;
            boolean $i$f$maxByOrThrow;
            int maxValue$iv;
            Object e$iv;
            int v$iv;
            int var27;
            HashMap var28;
            FontRenderer var30;
            FontRenderer var32;
            Object var34;
            float var35;
            int var36;
            for(var4 = StringsKt.split$default(var10000, var5, false, 0, 6, (Object)null).iterator(); var4.hasNext(); offset += 9.0F) {
               mob = (String)var4.next();
               GuiElement.Companion.getFr().func_175065_a(mob, 0.0F + FishingTracker.INSTANCE.textXoffset(mob, (GuiElement)this), 0.0F + offset, 16777215, true);
               if (!StringsKt.startsWith$default(mob, "-", false, 2, (Object)null)) {
                  var28 = FishingTracker.INSTANCE.getMobsCatched();
                  var8 = 0;
                  var9 = 2;
                  Integer var29;
                  if (var28.get(StringsKt.removeRange((CharSequence)mob, var8, var9).toString()) == null) {
                     var29 = 0;
                  } else {
                     var28 = FishingTracker.INSTANCE.getMobsCatched();
                     var8 = 0;
                     var9 = 2;
                     var29 = (Integer)var28.get(StringsKt.removeRange((CharSequence)mob, var8, var9).toString());
                  }

                  Integer valToDisplay = var29;
                  textToDisplay = left ? ": " + valToDisplay : valToDisplay + " :";
                  if (left) {
                     var32 = GuiElement.Companion.getFr();
                     var10001 = (CharSequence)FishingTracker.INSTANCE.getTrackerMobs();
                     var21 = new String[]{"."};
                     $this$maxBy$iv = (Iterable)StringsKt.split$default(var10001, var21, false, 0, 6, (Object)null);
                     var16 = var32;
                     $i$f$maxByOrThrow = false;
                     iterator$iv = $this$maxBy$iv.iterator();
                     if (!iterator$iv.hasNext()) {
                        throw new NoSuchElementException();
                     }

                     maxElem$iv = iterator$iv.next();
                     if (!iterator$iv.hasNext()) {
                        var34 = maxElem$iv;
                     } else {
                        it = (String)maxElem$iv;
                        var13 = false;
                        maxValue$iv = it.length();

                        while(true) {
                           e$iv = iterator$iv.next();
                           it = (String)e$iv;
                           var15 = false;
                           v$iv = it.length();
                           if (maxValue$iv < v$iv) {
                              maxElem$iv = e$iv;
                              maxValue$iv = v$iv;
                           }

                           if (!iterator$iv.hasNext()) {
                              var34 = maxElem$iv;
                              break;
                           }
                        }
                     }

                     var35 = (float)var16.func_78256_a((String)var34) + 8.0F;
                  } else {
                     var36 = this.getWidth();
                     var30 = GuiElement.Companion.getFr();
                     var10002 = (CharSequence)FishingTracker.INSTANCE.getTrackerMobs();
                     var21 = new String[]{"."};
                     $this$maxBy$iv = (Iterable)StringsKt.split$default(var10002, var21, false, 0, 6, (Object)null);
                     var17 = var30;
                     var27 = var36;
                     $i$f$maxByOrThrow = false;
                     iterator$iv = $this$maxBy$iv.iterator();
                     if (!iterator$iv.hasNext()) {
                        throw new NoSuchElementException();
                     }

                     maxElem$iv = iterator$iv.next();
                     if (!iterator$iv.hasNext()) {
                        var34 = maxElem$iv;
                     } else {
                        it = (String)maxElem$iv;
                        var13 = false;
                        maxValue$iv = it.length();

                        while(true) {
                           e$iv = iterator$iv.next();
                           it = (String)e$iv;
                           var15 = false;
                           v$iv = it.length();
                           if (maxValue$iv < v$iv) {
                              maxElem$iv = e$iv;
                              maxValue$iv = v$iv;
                           }

                           if (!iterator$iv.hasNext()) {
                              var34 = maxElem$iv;
                              break;
                           }
                        }
                     }

                     var18 = var34;
                     var35 = (float)(var27 - var17.func_78256_a((String)var18)) - 20.0F - (float)FishingTracker.INSTANCE.getFuckingOffset(textToDisplay);
                  }

                  xOffset = var35;
                  GuiElement.Companion.getFr().func_175065_a(textToDisplay, xOffset, 0.0F + offset, 16777215, true);
               }
            }

            if (!Config.INSTANCE.getFishingTrackerTimeSince()) {
               return;
            }

            var10000 = (CharSequence)FishingTracker.INSTANCE.getTimeSinceMobs();
            var5 = new String[]{"."};

            for(var4 = StringsKt.split$default(var10000, var5, false, 0, 6, (Object)null).iterator(); var4.hasNext(); offset += 9.0F) {
               mob = (String)var4.next();
               GuiElement.Companion.getFr().func_175065_a(mob, 0.0F + FishingTracker.INSTANCE.textXoffset(mob, (GuiElement)this), 0.0F + offset, 16777215, true);
               if (!StringsKt.startsWith$default(mob, "-", false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)mob, (CharSequence)"Time Since", false, 2, (Object)null)) {
                  Object var33;
                  String var37;
                  FontUtils var38;
                  label140: {
                     var28 = FishingTracker.INSTANCE.getLastTimeCatched();
                     var8 = 0;
                     var9 = 2;
                     if (var28.get(StringsKt.removeRange((CharSequence)mob, var8, var9).toString()) != null) {
                        var28 = FishingTracker.INSTANCE.getLastTimeCatched();
                        var8 = 0;
                        var9 = 2;
                        if (!Intrinsics.areEqual((Double)var28.get(StringsKt.removeRange((CharSequence)mob, var8, var9).toString()), 0.0D)) {
                           var38 = FontUtils.INSTANCE;
                           HashMap var31 = FishingTracker.INSTANCE.getLastTimeCatched();
                           var8 = 0;
                           var9 = 2;
                           var33 = var31.get(StringsKt.removeRange((CharSequence)mob, var8, var9).toString());
                           Intrinsics.checkNotNull(var33);
                           var37 = var38.getTimeBetween(((Number)var33).doubleValue(), (double)(System.currentTimeMillis() / (long)1000));
                           break label140;
                        }
                     }

                     var37 = "Never";
                  }

                  String timeSince = var37;
                  if (StringsKt.contains$default((CharSequence)mob, (CharSequence)"Great White", false, 2, (Object)null)) {
                     if (FishingTracker.INSTANCE.getLastTimeCatched().get("Great White Shark") != null && !Intrinsics.areEqual((Double)FishingTracker.INSTANCE.getLastTimeCatched().get("Great White Shark"), 0.0D)) {
                        var38 = FontUtils.INSTANCE;
                        var33 = FishingTracker.INSTANCE.getLastTimeCatched().get("Great White Shark");
                        Intrinsics.checkNotNull(var33);
                        timeSince = var38.getTimeBetween(((Number)var33).doubleValue(), (double)System.currentTimeMillis() / 1000.0D);
                     } else {
                        timeSince = "Never";
                     }
                  }

                  textToDisplay = left ? ": " + timeSince : timeSince + " :";
                  if (left) {
                     var32 = GuiElement.Companion.getFr();
                     var10001 = (CharSequence)FishingTracker.INSTANCE.getTimeSinceMobs();
                     var21 = new String[]{"."};
                     $this$maxBy$iv = (Iterable)CollectionsKt.dropLast(StringsKt.split$default(var10001, var21, false, 0, 6, (Object)null), 1);
                     var16 = var32;
                     $i$f$maxByOrThrow = false;
                     iterator$iv = $this$maxBy$iv.iterator();
                     if (!iterator$iv.hasNext()) {
                        throw new NoSuchElementException();
                     }

                     maxElem$iv = iterator$iv.next();
                     if (!iterator$iv.hasNext()) {
                        var34 = maxElem$iv;
                     } else {
                        it = (String)maxElem$iv;
                        var13 = false;
                        maxValue$iv = it.length();

                        while(true) {
                           e$iv = iterator$iv.next();
                           it = (String)e$iv;
                           var15 = false;
                           v$iv = it.length();
                           if (maxValue$iv < v$iv) {
                              maxElem$iv = e$iv;
                              maxValue$iv = v$iv;
                           }

                           if (!iterator$iv.hasNext()) {
                              var34 = maxElem$iv;
                              break;
                           }
                        }
                     }

                     var35 = (float)var16.func_78256_a((String)var34) + 14.0F;
                  } else {
                     var36 = this.getWidth();
                     var30 = GuiElement.Companion.getFr();
                     var10002 = (CharSequence)FishingTracker.INSTANCE.getTimeSinceMobs();
                     var21 = new String[]{"."};
                     $this$maxBy$iv = (Iterable)StringsKt.split$default(var10002, var21, false, 0, 6, (Object)null);
                     var17 = var30;
                     var27 = var36;
                     $i$f$maxByOrThrow = false;
                     iterator$iv = $this$maxBy$iv.iterator();
                     if (!iterator$iv.hasNext()) {
                        throw new NoSuchElementException();
                     }

                     maxElem$iv = iterator$iv.next();
                     if (!iterator$iv.hasNext()) {
                        var34 = maxElem$iv;
                     } else {
                        it = (String)maxElem$iv;
                        var13 = false;
                        maxValue$iv = it.length();

                        while(true) {
                           e$iv = iterator$iv.next();
                           it = (String)e$iv;
                           var15 = false;
                           v$iv = it.length();
                           if (maxValue$iv < v$iv) {
                              maxElem$iv = e$iv;
                              maxValue$iv = v$iv;
                           }

                           if (!iterator$iv.hasNext()) {
                              var34 = maxElem$iv;
                              break;
                           }
                        }
                     }

                     var18 = var34;
                     var35 = (float)(var27 - var17.func_78256_a((String)var18)) + 20.0F - (float)FishingTracker.INSTANCE.getFuckingOffset(textToDisplay);
                  }

                  xOffset = var35;
                  GuiElement.Companion.getFr().func_175065_a(textToDisplay, 0.0F + xOffset, 0.0F + offset, 16777215, true);
               }
            }
         }

      }

      public boolean getToggled() {
         return Config.INSTANCE.getFishingTracker();
      }

      public int getHeight() {
         CharSequence var10000 = (CharSequence)FishingTracker.INSTANCE.getTrackerMobs();
         String[] var1 = new String[]{"."};
         int var12 = StringsKt.split$default(var10000, var1, false, 0, 6, (Object)null).size();
         CharSequence var10001 = (CharSequence)FishingTracker.INSTANCE.getTimeSinceMobs();
         var1 = new String[]{"."};
         Iterable $this$dropWhile$iv = (Iterable)StringsKt.split$default(var10001, var1, false, 0, 6, (Object)null);
         int var9 = var12;
         int $i$f$dropWhile = false;
         boolean yielding$iv = false;
         ArrayList list$iv = new ArrayList();
         Iterator var5 = $this$dropWhile$iv.iterator();

         while(var5.hasNext()) {
            Object item$iv = var5.next();
            if (yielding$iv) {
               list$iv.add(item$iv);
            } else {
               String it = (String)item$iv;
               int var8 = false;
               if (!Intrinsics.areEqual(it, "")) {
                  list$iv.add(item$iv);
                  yielding$iv = true;
               }
            }
         }

         List var10 = (List)list$iv;
         return (var9 + var10.size()) * 9 + 4;
      }

      public int getWidth() {
         return 115;
      }
   }
}
