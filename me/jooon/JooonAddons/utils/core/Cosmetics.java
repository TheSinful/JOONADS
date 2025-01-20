package me.jooon.JooonAddons.utils.core;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.essential.api.utils.Multithreading;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.sequences.Sequence;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.jdk8.RegexExtensionsJDK8Kt;
import me.jooon.JooonAddons.JooonAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0007J\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u0007J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0018R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010\u000e\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"},
   d2 = {"Lme/jooon/JooonAddons/utils/core/Cosmetics;", "", "()V", "customCapes", "Ljava/util/HashMap;", "", "Lnet/minecraft/util/ResourceLocation;", "Lkotlin/collections/HashMap;", "customNames", "Ljava/util/HashSet;", "Lme/jooon/JooonAddons/utils/core/CustomName;", "Lkotlin/collections/HashSet;", "gson", "Lcom/google/gson/Gson;", "namesCache", "getNamesCache", "()Ljava/util/HashMap;", "regex", "Lkotlin/text/Regex;", "getCustomCape", "username", "getCustomNicks", "message", "loadCustomCapes", "", "loadCustomNicks", "JooonAddons"}
)
@SourceDebugExtension({"SMAP\nCosmetics.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Cosmetics.kt\nme/jooon/JooonAddons/utils/core/Cosmetics\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,129:1\n1855#2,2:130\n1855#2,2:133\n1855#2:135\n1045#2:136\n1855#2,2:137\n1856#2:139\n1#3:132\n*S KotlinDebug\n*F\n+ 1 Cosmetics.kt\nme/jooon/JooonAddons/utils/core/Cosmetics\n*L\n75#1:130,2\n26#1:133,2\n30#1:135\n36#1:136\n39#1:137,2\n30#1:139\n*E\n"})
public final class Cosmetics {
   @NotNull
   public static final Cosmetics INSTANCE = new Cosmetics();
   @NotNull
   private static final HashSet<CustomName> customNames = new HashSet();
   @NotNull
   private static final HashMap<String, ResourceLocation> customCapes = new HashMap();
   @NotNull
   private static final Regex regex = new Regex("(?:§.)*(?<rank>\\[(?:§.)*\\d+(?:§.)*\\])? ?(?:§.)*(?<prefix>\\[\\w\\w\\w(?:§.)*(?:\\+(?:§.)*)*])? ?(?<username>\\w{3,16})(?:§.)*:*");
   @NotNull
   private static final HashMap<String, String> namesCache = new HashMap();
   @NotNull
   private static final Gson gson = new Gson();

   private Cosmetics() {
   }

   @NotNull
   public final HashMap<String, String> getNamesCache() {
      return namesCache;
   }

   public final void loadCustomNicks() {
      Multithreading.runAsync(Cosmetics::loadCustomNicks$lambda$4);
   }

   public final void loadCustomCapes() {
      Multithreading $this$loadCustomCapes_u24lambda_u246 = Multithreading.INSTANCE;
      int var2 = false;
      customCapes.clear();
      CharSequence var10000 = (CharSequence)DatabaseConnection.INSTANCE.fetchData("CustomCapes");
      String[] var3 = new String[]{"{\"documents\":"};
      String response = StringsKt.dropLast((String)StringsKt.split$default(var10000, var3, false, 0, 6, (Object)null).get(1), 1);
      Object var18 = gson.fromJson(response, JsonArray.class);
      Intrinsics.checkNotNullExpressionValue(var18, "gson.fromJson(response, JsonArray::class.java)");
      List var19 = CollectionsKt.toList((Iterable)var18);
      if (var19 != null) {
         Iterable $this$forEach$iv = (Iterable)var19;
         int $i$f$forEach = false;
         Iterator var7 = $this$forEach$iv.iterator();

         while(var7.hasNext()) {
            Object element$iv = var7.next();
            JsonElement it = (JsonElement)element$iv;
            int var10 = false;
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type com.google.gson.JsonObject");
            JsonObject var20 = (JsonObject)it;
            String name = ((JsonObject)it).getAsJsonPrimitive("username").getAsString();
            String capeURL = ((JsonObject)it).getAsJsonPrimitive("capeURL").getAsString();

            try {
               URL imageUrl = new URL(capeURL);
               BufferedImage image = ImageIO.read(imageUrl);
               DynamicTexture texture = new DynamicTexture(image);
               ResourceLocation textureLocation = new ResourceLocation("JooonAddons", String.valueOf(texture.func_110552_b()));
               Minecraft.func_71410_x().func_110434_K().func_110579_a(textureLocation, (ITextureObject)texture);
               Map var21 = (Map)customCapes;
               Intrinsics.checkNotNullExpressionValue(name, "name");
               var21.put(name, textureLocation);
            } catch (Exception var17) {
               var17.printStackTrace();
            }
         }
      }

      System.out.println("Loaded custom capes");
   }

   @JvmStatic
   @Nullable
   public static final ResourceLocation getCustomCape(@Nullable String username) {
      if (JooonAddons.Companion.getMc().field_71439_g != null && username != null) {
         return customCapes.containsKey(username) ? (ResourceLocation)customCapes.get(username) : null;
      } else {
         return null;
      }
   }

   @JvmStatic
   @Nullable
   public static final String getCustomNicks(@Nullable String message) {
      if (JooonAddons.Companion.getMc().field_71439_g != null && message != null) {
         Cosmetics var10000 = INSTANCE;
         if (namesCache.containsValue(message)) {
            var10000 = INSTANCE;
            return (String)namesCache.get(message);
         } else {
            String text = message;
            Sequence result = Regex.findAll$default(regex, (CharSequence)message, 0, 2, (Object)null);
            int displace = 0;
            Iterator var4 = result.iterator();

            while(true) {
               while(var4.hasNext()) {
                  MatchResult matcher = (MatchResult)var4.next();
                  MatchGroup var19 = RegexExtensionsJDK8Kt.get(matcher.getGroups(), "username");
                  if (var19 != null) {
                     MatchGroup username = var19;
                     String name = StringsKt.trim((CharSequence)username.getValue()).toString();
                     IntRange nameRange = username.getRange();
                     var19 = RegexExtensionsJDK8Kt.get(matcher.getGroups(), "prefix");
                     IntRange prefixRange = var19 != null ? var19.getRange() : null;
                     Iterable var13 = (Iterable)customNames;
                     Iterator var14 = var13.iterator();

                     Object var20;
                     while(true) {
                        if (var14.hasNext()) {
                           Object var15 = var14.next();
                           CustomName it = (CustomName)var15;
                           int var17 = false;
                           if (!Intrinsics.areEqual(it.getName(), name)) {
                              continue;
                           }

                           var20 = var15;
                           break;
                        }

                        var20 = null;
                        break;
                     }

                     CustomName var21 = (CustomName)var20;
                     if (var21 != null) {
                        CustomName.Nick var22 = var21.getNick();
                        if (var22 != null) {
                           CustomName.Nick customName = var22;
                           String newName = StringsKt.replace$default(customName.getNick(), "&", "§", false, 4, (Object)null);
                           String newPrefix = StringsKt.replace$default(customName.getPrefix(), "&", "§", false, 4, (Object)null);
                           IntRange var18 = new IntRange(nameRange.getFirst() + displace, nameRange.getLast() + displace);
                           text = StringsKt.replaceRange((CharSequence)text, var18, (CharSequence)newName).toString();
                           if (prefixRange != null) {
                              var18 = new IntRange(prefixRange.getFirst() + displace, prefixRange.getLast() + displace);
                              text = StringsKt.replaceRange((CharSequence)text, var18, (CharSequence)newPrefix).toString();
                           }

                           displace += newName.length() - (nameRange.getLast() - nameRange.getFirst() + 1) + (prefixRange != null ? newPrefix.length() - (prefixRange.getLast() - prefixRange.getFirst() + 1) : 0);
                        }
                     }
                  }
               }

               var10000 = INSTANCE;
               ((Map)namesCache).put(message, text);
               return text;
            }
         }
      } else {
         return message;
      }
   }

   private static final void loadCustomNicks$lambda$4() {
      Iterable $this$forEach$iv = (Iterable)customNames;
      int $i$f$forEach = false;
      Iterator var2 = $this$forEach$iv.iterator();

      while(var2.hasNext()) {
         Object element$iv = var2.next();
         CustomName it = (CustomName)element$iv;
         int var5 = false;
         MinecraftForge.EVENT_BUS.unregister(it);
      }

      customNames.clear();
      CharSequence var10000 = (CharSequence)DatabaseConnection.INSTANCE.fetchData("CustomNames");
      String[] var18 = new String[]{"{\"documents\":"};
      String response = StringsKt.dropLast((String)StringsKt.split$default(var10000, var18, false, 0, 6, (Object)null).get(1), 1);
      Object var23 = gson.fromJson(response, JsonArray.class);
      Intrinsics.checkNotNullExpressionValue(var23, "gson.fromJson(response, JsonArray::class.java)");
      List var24 = CollectionsKt.toList((Iterable)var23);
      if (var24 != null) {
         Iterable $this$forEach$iv = (Iterable)var24;
         int $i$f$forEach = false;
         Iterator var21 = $this$forEach$iv.iterator();

         while(var21.hasNext()) {
            Object element$iv = var21.next();
            JsonElement it = (JsonElement)element$iv;
            int var7 = false;
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type com.google.gson.JsonObject");
            JsonObject var25 = (JsonObject)it;
            String name = ((JsonObject)it).getAsJsonPrimitive("username").getAsString();
            HashSet var26;
            if (!((JsonObject)it).getAsJsonPrimitive("animated").getAsBoolean()) {
               var26 = customNames;
               Intrinsics.checkNotNullExpressionValue(name, "name");
               String var10007 = ((JsonObject)it).getAsJsonObject().getAsJsonPrimitive("nick").getAsString();
               Intrinsics.checkNotNullExpressionValue(var10007, "it.asJsonObject.getAsJso…rimitive(\"nick\").asString");
               String var10008 = ((JsonObject)it).getAsJsonObject().getAsJsonPrimitive("prefix").getAsString();
               Intrinsics.checkNotNullExpressionValue(var10008, "it.asJsonObject.getAsJso…mitive(\"prefix\").asString");
               var26.add(new CustomName(name, false, CollectionsKt.listOf(new CustomName.Nick(var10007, var10008, -1))));
            } else {
               List nicks = (List)(new ArrayList());
               JsonArray frames = ((JsonObject)it).getAsJsonArray("frames");
               Intrinsics.checkNotNullExpressionValue(frames, "frames");
               Iterable $this$forEach$iv = (Iterable)frames;
               int $i$f$forEach = false;
               $this$forEach$iv = (Iterable)CollectionsKt.sortedWith($this$forEach$iv, (Comparator)(new Cosmetics$loadCustomNicks$lambda$4$lambda$3$$inlined$sortedBy$1()));
               $i$f$forEach = false;
               Iterator var13 = $this$forEach$iv.iterator();

               while(var13.hasNext()) {
                  Object element$iv = var13.next();
                  JsonElement frame = (JsonElement)element$iv;
                  int var16 = false;
                  Intrinsics.checkNotNull(frame, "null cannot be cast to non-null type com.google.gson.JsonObject");
                  var25 = (JsonObject)frame;
                  System.out.println(frame);
                  String var10003 = ((JsonObject)frame).getAsJsonPrimitive("nick").getAsString();
                  Intrinsics.checkNotNullExpressionValue(var10003, "frame.getAsJsonPrimitive(\"nick\").asString");
                  String var10004 = ((JsonObject)frame).getAsJsonPrimitive("prefix").getAsString();
                  Intrinsics.checkNotNullExpressionValue(var10004, "frame.getAsJsonPrimitive(\"prefix\").asString");
                  nicks.add(new CustomName.Nick(var10003, var10004, ((JsonObject)frame).getAsJsonPrimitive("nextIn").getAsInt()));
               }

               var26 = customNames;
               Intrinsics.checkNotNullExpressionValue(name, "name");
               var26.add(new CustomName(name, true, nicks));
            }
         }
      }

      Cosmetics var27 = INSTANCE;
      namesCache.clear();
      System.out.println("Loaded custom nicks");
   }
}
