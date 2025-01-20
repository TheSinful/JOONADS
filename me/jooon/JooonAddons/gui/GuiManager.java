package me.jooon.JooonAddons.gui;

import gg.essential.universal.UChat;
import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
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
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
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
import me.jooon.JooonAddons.events.RenderHUDEvent;
import me.jooon.JooonAddons.utils.core.FloatPair;
import me.jooon.JooonAddons.utils.core.GlState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
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
   d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001?B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010!\u001a\u00020\"2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00052\u0006\u0010#\u001a\u00020\u000eH\u0007J\u0010\u0010$\u001a\u0004\u0018\u00010\u00102\u0006\u0010%\u001a\u00020\u000eJ\u0012\u0010&\u001a\u0004\u0018\u00010\u00102\b\u0010'\u001a\u0004\u0018\u00010\u0005J\u0010\u0010(\u001a\u00020\"2\u0006\u0010)\u001a\u00020*H\u0007J\u0010\u0010+\u001a\u00020\"2\u0006\u0010)\u001a\u00020,H\u0007J\u0010\u0010-\u001a\u00020\"2\u0006\u0010.\u001a\u00020/H\u0016J\u000e\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u0010J\u0010\u00103\u001a\u00020\"2\u0006\u0010)\u001a\u000204H\u0007J\u0010\u00105\u001a\u00020\"2\u0006\u00106\u001a\u000207H\u0002J\u0014\u00108\u001a\b\u0012\u0004\u0012\u00020\u0010092\u0006\u0010:\u001a\u00020\u0005J\u0010\u0010;\u001a\u00020\"2\u0006\u0010<\u001a\u00020=H\u0016J\u0010\u0010>\u001a\u00020\"2\u0006\u0010<\u001a\u00020=H\u0016R-\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR-\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R-\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00100\u0004j\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0010`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\tR*\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0010`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001c¨\u0006@"},
   d2 = {"Lme/jooon/JooonAddons/gui/GuiManager;", "Lme/jooon/JooonAddons/config/PersistentSave;", "()V", "GUIPOSITIONS", "Ljava/util/HashMap;", "", "Lme/jooon/JooonAddons/utils/core/FloatPair;", "Lkotlin/collections/HashMap;", "getGUIPOSITIONS", "()Ljava/util/HashMap;", "GUISCALES", "", "getGUISCALES", "counter", "", "elements", "Lme/jooon/JooonAddons/gui/GuiElement;", "getElements", "names", "subtitle", "getSubtitle", "()Ljava/lang/String;", "setSubtitle", "(Ljava/lang/String;)V", "subtitleDisplayTicks", "getSubtitleDisplayTicks", "()I", "setSubtitleDisplayTicks", "(I)V", "title", "titleDisplayTicks", "getTitleDisplayTicks", "setTitleDisplayTicks", "createTitle", "", "ticks", "getByID", "ID", "getByName", "name", "onRenderHUD", "event", "Lme/jooon/JooonAddons/events/RenderHUDEvent;", "onTick", "Lnet/minecraftforge/fml/common/gameevent/TickEvent$ClientTickEvent;", "read", "reader", "Ljava/io/Reader;", "registerElement", "", "e", "renderPlayerInfo", "Lnet/minecraftforge/client/event/RenderGameOverlayEvent$Post;", "renderTitles", "scaledResolution", "Lnet/minecraft/client/gui/ScaledResolution;", "searchElements", "", "query", "setDefault", "writer", "Ljava/io/Writer;", "write", "GuiElementLocation", "JooonAddons"}
)
@SourceDebugExtension({"SMAP\nGuiManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GuiManager.kt\nme/jooon/JooonAddons/gui/GuiManager\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 SerialFormat.kt\nkotlinx/serialization/SerialFormatKt\n*L\n1#1,212:1\n96#2:213\n1855#3,2:214\n1179#3,2:216\n1253#3,4:218\n113#4:222\n*S KotlinDebug\n*F\n+ 1 GuiManager.kt\nme/jooon/JooonAddons/gui/GuiManager\n*L\n184#1:213\n194#1:214,2\n198#1:216,2\n198#1:218,4\n198#1:222\n*E\n"})
public final class GuiManager extends PersistentSave {
   @NotNull
   public static final GuiManager INSTANCE = new GuiManager();
   @NotNull
   private static final HashMap<String, FloatPair> GUIPOSITIONS = new HashMap();
   @NotNull
   private static final HashMap<String, Float> GUISCALES = new HashMap();
   @NotNull
   private static final HashMap<Integer, GuiElement> elements = new HashMap();
   @NotNull
   private static final HashMap<String, GuiElement> names = new HashMap();
   @JvmField
   @Nullable
   public static String title;
   @Nullable
   private static String subtitle;
   private static int titleDisplayTicks;
   private static int subtitleDisplayTicks;
   private static int counter;

   private GuiManager() {
      super(new File("config/joonaddons", "guipositions.json"));
   }

   @NotNull
   public final HashMap<String, FloatPair> getGUIPOSITIONS() {
      return GUIPOSITIONS;
   }

   @NotNull
   public final HashMap<String, Float> getGUISCALES() {
      return GUISCALES;
   }

   @NotNull
   public final HashMap<Integer, GuiElement> getElements() {
      return elements;
   }

   @Nullable
   public final String getSubtitle() {
      return subtitle;
   }

   public final void setSubtitle(@Nullable String <set-?>) {
      subtitle = var1;
   }

   public final int getTitleDisplayTicks() {
      return titleDisplayTicks;
   }

   public final void setTitleDisplayTicks(int <set-?>) {
      titleDisplayTicks = var1;
   }

   public final int getSubtitleDisplayTicks() {
      return subtitleDisplayTicks;
   }

   public final void setSubtitleDisplayTicks(int <set-?>) {
      subtitleDisplayTicks = var1;
   }

   public final boolean registerElement(@NotNull GuiElement e) {
      Intrinsics.checkNotNullParameter(e, "e");

      boolean var2;
      try {
         GuiManager var10000 = INSTANCE;
         int var3 = counter++;
         var10000 = INSTANCE;
         ((Map)elements).put(counter, e);
         ((Map)names).put(e.getName(), e);
         var2 = true;
      } catch (Exception var4) {
         var4.printStackTrace();
         var2 = false;
      }

      return var2;
   }

   @Nullable
   public final GuiElement getByID(int ID) {
      GuiManager var10000 = INSTANCE;
      return (GuiElement)elements.get(ID);
   }

   @Nullable
   public final GuiElement getByName(@Nullable String name) {
      return (GuiElement)((Map)names).get(name);
   }

   @NotNull
   public final List<GuiElement> searchElements(@NotNull String query) {
      Intrinsics.checkNotNullParameter(query, "query");
      List results = (List)(new ArrayList());
      Iterator var3 = ((Map)names).entrySet().iterator();

      while(var3.hasNext()) {
         Entry var4 = (Entry)var3.next();
         String key = (String)var4.getKey();
         GuiElement value = (GuiElement)var4.getValue();
         if (StringsKt.contains$default((CharSequence)key, (CharSequence)query, false, 2, (Object)null)) {
            results.add(value);
         }
      }

      return results;
   }

   @SubscribeEvent
   public final void renderPlayerInfo(@NotNull Post event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Minecraft.func_71410_x().field_71456_v instanceof GuiIngameForge) {
         if (event.type == ElementType.HOTBAR) {
            GlState.Companion.pushState();
            MinecraftForge.EVENT_BUS.post((Event)(new RenderHUDEvent((RenderGameOverlayEvent)event)));
            GlState.Companion.popState();
         }
      }
   }

   @JvmStatic
   public static final void createTitle(@Nullable String title, int ticks) {
      INSTANCE.getMc().field_71439_g.func_85030_a("random.orb", 1.0F, 0.5F);
      GuiManager var10000 = INSTANCE;
      GuiManager.title = title;
      var10000 = INSTANCE;
      titleDisplayTicks = ticks;
   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public final void onRenderHUD(@NotNull RenderHUDEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (!(Minecraft.func_71410_x().field_71462_r instanceof LocationEditGui)) {
         this.getMc().field_71424_I.func_76320_a("SkytilsHUD");
         GuiManager var10000 = INSTANCE;

         for(Iterator var2 = ((Map)elements).entrySet().iterator(); var2.hasNext(); this.getMc().field_71424_I.func_76319_b()) {
            GuiElement element = (GuiElement)((Entry)var2.next()).getValue();
            this.getMc().field_71424_I.func_76320_a(element.getName());

            try {
               GlStateManager.func_179094_E();
               GlStateManager.func_179109_b(element.getActualX(), element.getActualY(), element.getActualY());
               GlStateManager.func_179152_a(element.getScale(), element.getScale(), element.getScale());
               element.render();
               GlStateManager.func_179121_F();
            } catch (Exception var5) {
               var5.printStackTrace();
               UChat.chat("§7[§aJooonAddons§7]§8 Error while rendering " + element.getName() + ". Please report this to jooon#1952");
            }
         }

         var10000 = INSTANCE;
         ScaledResolution var10001 = event.getEvent().resolution;
         Intrinsics.checkNotNullExpressionValue(var10001, "event.event.resolution");
         var10000.renderTitles(var10001);
         this.getMc().field_71424_I.func_76319_b();
      }
   }

   @SubscribeEvent
   public final void onTick(@NotNull ClientTickEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (event.phase == Phase.START) {
         GuiManager var10000 = INSTANCE;
         int var2;
         if (titleDisplayTicks > 0) {
            var10000 = INSTANCE;
            var2 = titleDisplayTicks;
            titleDisplayTicks = var2 + -1;
         } else {
            var10000 = INSTANCE;
            titleDisplayTicks = 0;
            var10000 = INSTANCE;
            title = null;
         }

         var10000 = INSTANCE;
         if (subtitleDisplayTicks > 0) {
            var10000 = INSTANCE;
            var2 = subtitleDisplayTicks;
            subtitleDisplayTicks = var2 + -1;
         } else {
            var10000 = INSTANCE;
            subtitleDisplayTicks = 0;
            var10000 = INSTANCE;
            subtitle = null;
         }

      }
   }

   private final void renderTitles(ScaledResolution scaledResolution) {
      Minecraft mc = Minecraft.func_71410_x();
      if (mc.field_71441_e != null && mc.field_71439_g != null) {
         int scaledWidth = scaledResolution.func_78326_a();
         int scaledHeight = scaledResolution.func_78328_b();
         int stringWidth;
         float scale;
         if (title != null) {
            stringWidth = mc.field_71466_p.func_78256_a(title);
            scale = 4.0F;
            if ((float)stringWidth * scale > (float)scaledWidth * 0.9F) {
               scale = (float)scaledWidth * 0.9F / (float)stringWidth;
            }

            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)(scaledWidth / 2), (float)(scaledHeight / 2), 0.0F);
            GlStateManager.func_179147_l();
            GlStateManager.func_179120_a(770, 771, 1, 0);
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a(scale, scale, scale);
            mc.field_71466_p.func_175065_a(title, (float)(-mc.field_71466_p.func_78256_a(title) / 2), -20.0F, 16711680, true);
            GlStateManager.func_179121_F();
            GlStateManager.func_179121_F();
         }

         GuiManager var10000 = INSTANCE;
         if (subtitle != null) {
            GuiManager var10001 = INSTANCE;
            stringWidth = mc.field_71466_p.func_78256_a(subtitle);
            scale = 2.0F;
            if ((float)stringWidth * scale > (float)scaledWidth * 0.9F) {
               scale = (float)scaledWidth * 0.9F / (float)stringWidth;
            }

            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)(scaledWidth / 2), (float)(scaledHeight / 2), 0.0F);
            GlStateManager.func_179147_l();
            GlStateManager.func_179120_a(770, 771, 1, 0);
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a(scale, scale, scale);
            var10001 = INSTANCE;
            GuiManager var10003 = INSTANCE;
            mc.field_71466_p.func_175065_a(subtitle, (float)(-mc.field_71466_p.func_78256_a(subtitle)) / 2.0F, -23.0F, 16711680, true);
            GlStateManager.func_179121_F();
            GlStateManager.func_179121_F();
         }

      }
   }

   public void read(@NotNull Reader reader) {
      Intrinsics.checkNotNullParameter(reader, "reader");
      Json this_$iv = this.getJson();
      String string$iv = TextStreamsKt.readText(reader);
      int $i$f$decodeFromString = false;
      this_$iv.getSerializersModule();
      ((Map)this_$iv.decodeFromString((DeserializationStrategy)(new LinkedHashMapSerializer(StringSerializer.INSTANCE, GuiManager.GuiElementLocation.Companion.serializer())), string$iv)).forEach(GuiManager::read$lambda$0);
   }

   public void write(@NotNull Writer writer) {
      Intrinsics.checkNotNullParameter(writer, "writer");
      Set var10000 = names.entrySet();
      Intrinsics.checkNotNullExpressionValue(var10000, "names.entries");
      Iterable $this$forEach$iv = (Iterable)var10000;
      int $i$f$forEach = false;
      Iterator var4 = $this$forEach$iv.iterator();

      while(var4.hasNext()) {
         Object element$iv = var4.next();
         Entry var6 = (Entry)element$iv;
         int var7 = false;
         Intrinsics.checkNotNullExpressionValue(var6, "(n, e)");
         String n = (String)var6.getKey();
         GuiElement e = (GuiElement)var6.getValue();
         GuiManager var24 = INSTANCE;
         Map var25 = (Map)GUIPOSITIONS;
         Intrinsics.checkNotNullExpressionValue(n, "n");
         var25.put(n, e.getPos());
         var24 = INSTANCE;
         ((Map)GUISCALES).put(n, e.getScale());
      }

      StringFormat $this$encodeToString$iv = (StringFormat)this.getJson();
      GuiManager var10001 = INSTANCE;
      Set var26 = GUIPOSITIONS.entrySet();
      Intrinsics.checkNotNullExpressionValue(var26, "GuiManager.GUIPOSITIONS.entries");
      Iterable $this$associate$iv = (Iterable)var26;
      int $i$f$encodeToString = false;
      int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv, 10)), 16);
      Map destination$iv$iv = (Map)(new LinkedHashMap(capacity$iv));
      int $i$f$associateTo = false;
      Iterator var22 = $this$associate$iv.iterator();

      while(var22.hasNext()) {
         Object element$iv$iv = var22.next();
         Entry it = (Entry)element$iv$iv;
         int var13 = false;
         Object var27 = it.getKey();
         GuiManager.GuiElementLocation var28 = new GuiManager.GuiElementLocation;
         float var10003 = ((FloatPair)it.getValue()).getX();
         float var10004 = ((FloatPair)it.getValue()).getY();
         GuiManager var10005 = INSTANCE;
         Float var15 = (Float)GUISCALES.get(it.getKey());
         if (var15 == null) {
            var15 = 1.0F;
         }

         Intrinsics.checkNotNullExpressionValue(var15, "me.jooon.JooonAddons.gui…r.GUISCALES[it.key] ?: 1f");
         var28.<init>(var10003, var10004, ((Number)var15).floatValue());
         Pair var23 = TuplesKt.to(var27, var28);
         destination$iv$iv.put(var23.getFirst(), var23.getSecond());
      }

      $i$f$encodeToString = false;
      $this$encodeToString$iv.getSerializersModule();
      writer.write($this$encodeToString$iv.encodeToString((SerializationStrategy)(new LinkedHashMapSerializer(StringSerializer.INSTANCE, GuiManager.GuiElementLocation.Companion.serializer())), destination$iv$iv));
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
      d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0083\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B1\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J!\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 HÇ\u0001R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006#"},
      d2 = {"Lme/jooon/JooonAddons/gui/GuiManager$GuiElementLocation;", "", "seen1", "", "x", "", "y", "scale", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IFFFLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(FFF)V", "getScale", "()F", "getX", "getY", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "JooonAddons"}
   )
   private static final class GuiElementLocation {
      @NotNull
      public static final GuiManager.GuiElementLocation.Companion Companion = new GuiManager.GuiElementLocation.Companion((DefaultConstructorMarker)null);
      private final float x;
      private final float y;
      private final float scale;

      public GuiElementLocation(float x, float y, float scale) {
         this.x = x;
         this.y = y;
         this.scale = scale;
      }

      public final float getX() {
         return this.x;
      }

      public final float getY() {
         return this.y;
      }

      public final float getScale() {
         return this.scale;
      }

      public final float component1() {
         return this.x;
      }

      public final float component2() {
         return this.y;
      }

      public final float component3() {
         return this.scale;
      }

      @NotNull
      public final GuiManager.GuiElementLocation copy(float x, float y, float scale) {
         return new GuiManager.GuiElementLocation(x, y, scale);
      }

      // $FF: synthetic method
      public static GuiManager.GuiElementLocation copy$default(GuiManager.GuiElementLocation var0, float var1, float var2, float var3, int var4, Object var5) {
         if ((var4 & 1) != 0) {
            var1 = var0.x;
         }

         if ((var4 & 2) != 0) {
            var2 = var0.y;
         }

         if ((var4 & 4) != 0) {
            var3 = var0.scale;
         }

         return var0.copy(var1, var2, var3);
      }

      @NotNull
      public String toString() {
         return "GuiElementLocation(x=" + this.x + ", y=" + this.y + ", scale=" + this.scale + ')';
      }

      public int hashCode() {
         int result = Float.hashCode(this.x);
         result = result * 31 + Float.hashCode(this.y);
         result = result * 31 + Float.hashCode(this.scale);
         return result;
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof GuiManager.GuiElementLocation)) {
            return false;
         } else {
            GuiManager.GuiElementLocation var2 = (GuiManager.GuiElementLocation)other;
            if (Float.compare(this.x, var2.x) != 0) {
               return false;
            } else if (Float.compare(this.y, var2.y) != 0) {
               return false;
            } else {
               return Float.compare(this.scale, var2.scale) == 0;
            }
         }
      }

      // $FF: synthetic method
      @JvmStatic
      public static final void write$Self(GuiManager.GuiElementLocation self, CompositeEncoder output, SerialDescriptor serialDesc) {
         output.encodeFloatElement(serialDesc, 0, self.x);
         output.encodeFloatElement(serialDesc, 1, self.y);
         output.encodeFloatElement(serialDesc, 2, self.scale);
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
      public GuiElementLocation(int seen1, float x, float y, float scale, SerializationConstructorMarker serializationConstructorMarker) {
         if (7 != (7 & seen1)) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 7, GuiManager$GuiElementLocation$$serializer.INSTANCE.getDescriptor());
         }

         super();
         this.x = x;
         this.y = y;
         this.scale = scale;
      }

      @Metadata(
         mv = {1, 8, 0},
         k = 1,
         xi = 48,
         d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"},
         d2 = {"Lme/jooon/JooonAddons/gui/GuiManager$GuiElementLocation$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lme/jooon/JooonAddons/gui/GuiManager$GuiElementLocation;", "JooonAddons"}
      )
      public static final class Companion {
         private Companion() {
         }

         @NotNull
         public final KSerializer<GuiManager.GuiElementLocation> serializer() {
            return (KSerializer)GuiManager$GuiElementLocation$$serializer.INSTANCE;
         }

         // $FF: synthetic method
         public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
         }
      }
   }
}
