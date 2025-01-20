package me.jooon.JooonAddons.utils;

import io.netty.handler.codec.http.HttpMethod;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.features.betterlootshare.ESP;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Lookup;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\rH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J`\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\u0018\u0010\u0015\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0016\u001a\u00020\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J<\u0010\u0019\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0016\u001a\u00020\u0004H\u0007J2\u0010\u001a\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0016\u001a\u00020\u0004H\u0007J<\u0010\u001b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0016\u001a\u00020\u0004H\u0007J<\u0010\u001c\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0016\u001a\u00020\u0004H\u0007JJ\u0010\u001d\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\u0018\u0010\u0015\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\rJT\u0010\u001d\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\u0018\u0010\u0015\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0016\u001a\u00020\u0004J<\u0010\u001e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0016\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"},
   d2 = {"Lme/jooon/JooonAddons/utils/HttpUtils;", "", "()V", "DEFAULT_CHARSET", "", "reqConf", "Lorg/apache/http/client/config/RequestConfig;", "standardHandler", "Lorg/apache/http/impl/client/StandardHttpRequestRetryHandler;", "addHeader", "Lorg/apache/http/client/methods/HttpRequestBase;", "httpRequest", "headerMap", "", "initSSL", "", "builder", "Lorg/apache/http/impl/client/HttpClientBuilder;", "send", "url", "content", "formParamMap", "contentCharset", "method", "Lio/netty/handler/codec/http/HttpMethod;", "sendDelete", "sendGet", "sendPatch", "sendPost", "sendPostForm", "sendPut", "JooonAddons"}
)
public final class HttpUtils {
   @NotNull
   public static final HttpUtils INSTANCE = new HttpUtils();
   @NotNull
   private static final String DEFAULT_CHARSET = "UTF-8";
   @Nullable
   private static RequestConfig reqConf;
   @Nullable
   private static StandardHttpRequestRetryHandler standardHandler;

   private HttpUtils() {
   }

   private final String send(String url, String content, Map<String, String> headerMap, Map<String, String> formParamMap, String contentCharset, HttpMethod method) {
      CloseableHttpClient httpClient = null;

      try {
         HttpClientBuilder builder = HttpClientBuilder.create().setRetryHandler((HttpRequestRetryHandler)standardHandler);
         String var10000 = url.toLowerCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var10000, "this as java.lang.String).toLowerCase(Locale.ROOT)");
         if (StringsKt.startsWith$default(var10000, "https", false, 2, (Object)null)) {
            Intrinsics.checkNotNullExpressionValue(builder, "builder");
            this.initSSL(builder);
         }

         httpClient = builder.build();
         HttpResponse httpResponse = null;
         if (Intrinsics.areEqual(method, HttpMethod.GET)) {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(reqConf);
            this.addHeader((HttpRequestBase)httpGet, headerMap);
            httpResponse = (HttpResponse)httpClient.execute((HttpUriRequest)httpGet);
         } else if (!Intrinsics.areEqual(method, HttpMethod.POST)) {
            if (Intrinsics.areEqual(method, HttpMethod.DELETE)) {
               HttpDelete httpDelete = new HttpDelete(url);
               httpDelete.setConfig(reqConf);
               this.addHeader((HttpRequestBase)httpDelete, headerMap);
               httpResponse = (HttpResponse)httpClient.execute((HttpUriRequest)httpDelete);
            } else if (Intrinsics.areEqual(method, HttpMethod.PUT)) {
               HttpPut httpPut = new HttpPut(url);
               httpPut.setConfig(reqConf);
               this.addHeader((HttpRequestBase)httpPut, headerMap);
               httpPut.setEntity((HttpEntity)(new StringEntity(content, contentCharset)));
               httpResponse = (HttpResponse)httpClient.execute((HttpUriRequest)httpPut);
            } else if (Intrinsics.areEqual(method, HttpMethod.PATCH)) {
               HttpPatch httpPatch = new HttpPatch(url);
               httpPatch.setConfig(reqConf);
               this.addHeader((HttpRequestBase)httpPatch, headerMap);
               httpPatch.setEntity((HttpEntity)(new StringEntity(content, contentCharset)));
               httpResponse = (HttpResponse)httpClient.execute((HttpUriRequest)httpPatch);
            }
         } else {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(reqConf);
            this.addHeader((HttpRequestBase)httpPost, headerMap);
            if (formParamMap == null || formParamMap.isEmpty()) {
               httpPost.setEntity((HttpEntity)(new StringEntity(content, contentCharset)));
            } else {
               List ls = (List)(new ArrayList());
               Iterator var13 = formParamMap.entrySet().iterator();

               while(var13.hasNext()) {
                  Entry var14 = (Entry)var13.next();
                  String key = (String)var14.getKey();
                  String value = (String)var14.getValue();
                  HttpUtils $this$send_u24lambda_u240 = (HttpUtils)this;
                  int var18 = false;
                  ls.add(new BasicNameValuePair(key, value));
               }

               httpPost.setEntity((HttpEntity)(new UrlEncodedFormEntity(ls, "UTF-8")));
            }

            httpResponse = (HttpResponse)httpClient.execute((HttpUriRequest)httpPost);
         }

         if (httpResponse != null) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
               String var10 = EntityUtils.toString(httpResponse.getEntity());
               return var10;
            }

            Logger var35 = ESP.INSTANCE.getLogger();
            StringBuilder var10001 = (new StringBuilder()).append("Failed request with status code ");
            StatusLine var10002 = httpResponse.getStatusLine();
            var35.error(var10001.append(var10002 != null ? var10002.getStatusCode() : null).toString());
         }
      } catch (ClientProtocolException var28) {
         var28.printStackTrace();
      } catch (IOException var29) {
         var29.printStackTrace();
      } finally {
         try {
            if (httpClient != null) {
               httpClient.close();
            }
         } catch (Exception var27) {
            var27.printStackTrace();
         }

      }

      return null;
   }

   // $FF: synthetic method
   static String send$default(HttpUtils var0, String var1, String var2, Map var3, Map var4, String var5, HttpMethod var6, int var7, Object var8) {
      if ((var7 & 16) != 0) {
         var5 = "UTF-8";
      }

      return var0.send(var1, var2, var3, var4, var5, var6);
   }

   private final HttpRequestBase addHeader(HttpRequestBase httpRequest, Map<String, String> headerMap) {
      if (headerMap != null && !headerMap.isEmpty()) {
         Set keys = headerMap.keySet();
         Iterator iterator = keys.iterator();

         while(iterator.hasNext()) {
            String key = (String)iterator.next();
            httpRequest.addHeader(key, (String)headerMap.get(key));
         }
      }

      return httpRequest;
   }

   private final void initSSL(HttpClientBuilder builder) {
      TrustManager[] var3 = new TrustManager[]{new X509TrustManager() {
         @NotNull
         public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
         }

         public void checkClientTrusted(@NotNull X509Certificate[] certs, @NotNull String authType) {
            Intrinsics.checkNotNullParameter(certs, "certs");
            Intrinsics.checkNotNullParameter(authType, "authType");
         }

         public void checkServerTrusted(@NotNull X509Certificate[] certs, @NotNull String authType) {
            Intrinsics.checkNotNullParameter(certs, "certs");
            Intrinsics.checkNotNullParameter(authType, "authType");
         }
      }};
      TrustManager[] trustAllCerts = var3;
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init((KeyManager[])null, trustAllCerts, new SecureRandom());
      SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(sc);
      Registry r = RegistryBuilder.create().register("https", scsf).build();
      BasicHttpClientConnectionManager ccm = new BasicHttpClientConnectionManager((Lookup)r);
      builder.setSslcontext(sc).setSSLSocketFactory((LayeredConnectionSocketFactory)scsf).setConnectionManager((HttpClientConnectionManager)ccm);
   }

   @JvmOverloads
   @Nullable
   public final String sendGet(@NotNull String url, @Nullable Map<String, String> headerMap, @NotNull String contentCharset) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(contentCharset, "contentCharset");
      return this.send(url, "", headerMap, (Map)null, contentCharset, HttpMethod.GET);
   }

   // $FF: synthetic method
   public static String sendGet$default(HttpUtils var0, String var1, Map var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = "UTF-8";
      }

      return var0.sendGet(var1, var2, var3);
   }

   @Nullable
   public final String sendPostForm(@NotNull String url, @Nullable String content, @Nullable Map<String, String> headerMap, @Nullable Map<String, String> formParamMap) {
      Intrinsics.checkNotNullParameter(url, "url");
      return this.send(url, content, headerMap, formParamMap, "UTF-8", HttpMethod.POST);
   }

   @JvmOverloads
   @Nullable
   public final String sendPost(@NotNull String url, @Nullable String content, @Nullable Map<String, String> headerMap, @NotNull String contentCharset) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(contentCharset, "contentCharset");
      return this.send(url, content, headerMap, (Map)null, contentCharset, HttpMethod.POST);
   }

   // $FF: synthetic method
   public static String sendPost$default(HttpUtils var0, String var1, String var2, Map var3, String var4, int var5, Object var6) {
      if ((var5 & 8) != 0) {
         var4 = "UTF-8";
      }

      return var0.sendPost(var1, var2, var3, var4);
   }

   @Nullable
   public final String sendPostForm(@NotNull String url, @Nullable String content, @Nullable Map<String, String> headerMap, @Nullable Map<String, String> formParamMap, @NotNull String contentCharset) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(contentCharset, "contentCharset");
      return this.send(url, content, headerMap, formParamMap, contentCharset, HttpMethod.POST);
   }

   // $FF: synthetic method
   public static String sendPostForm$default(HttpUtils var0, String var1, String var2, Map var3, Map var4, String var5, int var6, Object var7) {
      if ((var6 & 16) != 0) {
         var5 = "UTF-8";
      }

      return var0.sendPostForm(var1, var2, var3, var4, var5);
   }

   @JvmOverloads
   @Nullable
   public final String sendDelete(@NotNull String url, @Nullable String content, @Nullable Map<String, String> headerMap, @NotNull String contentCharset) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(contentCharset, "contentCharset");
      return this.send(url, content, headerMap, (Map)null, contentCharset, HttpMethod.DELETE);
   }

   // $FF: synthetic method
   public static String sendDelete$default(HttpUtils var0, String var1, String var2, Map var3, String var4, int var5, Object var6) {
      if ((var5 & 8) != 0) {
         var4 = "UTF-8";
      }

      return var0.sendDelete(var1, var2, var3, var4);
   }

   @JvmOverloads
   @Nullable
   public final String sendPut(@NotNull String url, @Nullable String content, @Nullable Map<String, String> headerMap, @NotNull String contentCharset) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(contentCharset, "contentCharset");
      return this.send(url, content, headerMap, (Map)null, contentCharset, HttpMethod.PUT);
   }

   // $FF: synthetic method
   public static String sendPut$default(HttpUtils var0, String var1, String var2, Map var3, String var4, int var5, Object var6) {
      if ((var5 & 8) != 0) {
         var4 = "UTF-8";
      }

      return var0.sendPut(var1, var2, var3, var4);
   }

   @JvmOverloads
   @Nullable
   public final String sendPatch(@NotNull String url, @Nullable String content, @Nullable Map<String, String> headerMap, @NotNull String contentCharset) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(contentCharset, "contentCharset");
      return this.send(url, content, headerMap, (Map)null, contentCharset, HttpMethod.PATCH);
   }

   // $FF: synthetic method
   public static String sendPatch$default(HttpUtils var0, String var1, String var2, Map var3, String var4, int var5, Object var6) {
      if ((var5 & 8) != 0) {
         var4 = "UTF-8";
      }

      return var0.sendPatch(var1, var2, var3, var4);
   }

   @JvmOverloads
   @Nullable
   public final String sendGet(@NotNull String url, @Nullable Map<String, String> headerMap) {
      Intrinsics.checkNotNullParameter(url, "url");
      return sendGet$default(this, url, headerMap, (String)null, 4, (Object)null);
   }

   @JvmOverloads
   @Nullable
   public final String sendPost(@NotNull String url, @Nullable String content, @Nullable Map<String, String> headerMap) {
      Intrinsics.checkNotNullParameter(url, "url");
      return sendPost$default(this, url, content, headerMap, (String)null, 8, (Object)null);
   }

   @JvmOverloads
   @Nullable
   public final String sendDelete(@NotNull String url, @Nullable String content, @Nullable Map<String, String> headerMap) {
      Intrinsics.checkNotNullParameter(url, "url");
      return sendDelete$default(this, url, content, headerMap, (String)null, 8, (Object)null);
   }

   @JvmOverloads
   @Nullable
   public final String sendPut(@NotNull String url, @Nullable String content, @Nullable Map<String, String> headerMap) {
      Intrinsics.checkNotNullParameter(url, "url");
      return sendPut$default(this, url, content, headerMap, (String)null, 8, (Object)null);
   }

   @JvmOverloads
   @Nullable
   public final String sendPatch(@NotNull String url, @Nullable String content, @Nullable Map<String, String> headerMap) {
      Intrinsics.checkNotNullParameter(url, "url");
      return sendPatch$default(this, url, content, headerMap, (String)null, 8, (Object)null);
   }

   static {
      HttpUtils var10000 = INSTANCE;
      reqConf = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).setConnectionRequestTimeout(5000).setRedirectsEnabled(false).setMaxRedirects(0).build();
      var10000 = INSTANCE;
      standardHandler = new StandardHttpRequestRetryHandler(3, true);
   }
}
