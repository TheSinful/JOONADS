package gg.essential.loader.stage1;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class EssentialLoaderBase {
   private static final Logger LOGGER = LogManager.getLogger(EssentialLoaderBase.class);
   protected static final String STAGE2_PKG = "gg.essential.loader.stage2.";
   protected static final String STAGE2_CLS = "gg.essential.loader.stage2.EssentialLoader";
   private static final String BASE_URL = System.getProperty("essential.download.url", (String)System.getenv().getOrDefault("ESSENTIAL_DOWNLOAD_URL", "https://downloads.essential.gg"));
   private static final String BRANCH = System.getProperty("essential.stage2.branch", (String)System.getenv().getOrDefault("ESSENTIAL_STAGE2_BRANCH", "stable"));
   private static final String VERSION_URL;
   private static final boolean AUTO_UPDATE;
   private final String variant;
   private final String gameVersion;
   private Object stage2;
   private boolean loaded;

   EssentialLoaderBase(String variant, String gameVersion) {
      this.variant = variant;
      this.gameVersion = gameVersion;
   }

   public void load(Path gameDir) throws Exception {
      if (!this.loaded) {
         this.loaded = true;
         Path dataDir = gameDir.resolve("essential").resolve("loader").resolve("stage1").resolve(this.variant);
         Path stage2File = dataDir.resolve("stage2." + this.gameVersion + ".jar");
         URL stage2Url = stage2File.toUri().toURL();
         if (!Files.exists(dataDir, new LinkOption[0])) {
            Files.createDirectories(dataDir);
         }

         boolean needUpdate = !Files.exists(stage2File, new LinkOption[0]);
         EssentialLoaderBase.FileMeta meta = null;
         if (needUpdate || AUTO_UPDATE) {
            meta = this.fetchLatestMetadata();
            if (meta == null && needUpdate) {
               return;
            }
         }

         if (!needUpdate && meta != null && !meta.checksum.equals(this.getChecksum(stage2File))) {
            needUpdate = true;
         }

         if (needUpdate) {
            Path downloadedFile = Files.createTempFile("essential-download-", "");
            if (this.downloadFile(meta, downloadedFile)) {
               Files.deleteIfExists(stage2File);
               Files.move(downloadedFile, stage2File);
            } else {
               LOGGER.warn("Unable to download Essential, please check your internet connection. If the problem persists, please contact Support.");
               Files.deleteIfExists(downloadedFile);
            }
         }

         if (Files.exists(stage2File, new LinkOption[0])) {
            ClassLoader classLoader = this.addToClassLoader(stage2Url);
            this.stage2 = Class.forName("gg.essential.loader.stage2.EssentialLoader", true, classLoader).getConstructor(Path.class, String.class).newInstance(gameDir, this.gameVersion);
            this.stage2.getClass().getMethod("load").invoke(this.stage2);
         }
      }
   }

   public Object getStage2() {
      return this.stage2;
   }

   protected abstract ClassLoader addToClassLoader(URL var1) throws Exception;

   public void initialize() {
      if (this.stage2 != null) {
         try {
            this.stage2.getClass().getMethod("initialize").invoke(this.stage2);
         } catch (Throwable var2) {
            throw new RuntimeException("Unexpected error", var2);
         }
      }
   }

   private String getChecksum(Path input) {
      try {
         InputStream inputStream = Files.newInputStream(input);
         Throwable var3 = null;

         String var4;
         try {
            var4 = DigestUtils.md5Hex(inputStream);
         } catch (Throwable var14) {
            var3 = var14;
            throw var14;
         } finally {
            if (inputStream != null) {
               if (var3 != null) {
                  try {
                     inputStream.close();
                  } catch (Throwable var13) {
                     var3.addSuppressed(var13);
                  }
               } else {
                  inputStream.close();
               }
            }

         }

         return var4;
      } catch (Exception var16) {
         var16.printStackTrace();
         return null;
      }
   }

   private URLConnection prepareConnection(String url) throws IOException {
      URLConnection urlConnection = (new URL(url)).openConnection();
      if (urlConnection instanceof HttpURLConnection) {
         HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
         httpURLConnection.setRequestMethod("GET");
         httpURLConnection.setUseCaches(true);
         httpURLConnection.setConnectTimeout(30000);
         httpURLConnection.setReadTimeout(30000);
         httpURLConnection.setDoOutput(true);
         httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Essential Initializer)");
      }

      return urlConnection;
   }

   private EssentialLoaderBase.FileMeta fetchLatestMetadata() {
      URLConnection connection = null;

      JsonObject responseObject;
      JsonElement jsonChecksum;
      try {
         connection = this.prepareConnection(String.format(VERSION_URL, this.gameVersion.replace(".", "-")));
         InputStream inputStream = connection.getInputStream();
         Throwable var5 = null;

         String response;
         try {
            response = IOUtils.toString(inputStream, Charset.defaultCharset());
         } catch (Throwable var15) {
            var5 = var15;
            throw var15;
         } finally {
            if (inputStream != null) {
               if (var5 != null) {
                  try {
                     inputStream.close();
                  } catch (Throwable var14) {
                     var5.addSuppressed(var14);
                  }
               } else {
                  inputStream.close();
               }
            }

         }

         jsonChecksum = (new JsonParser()).parse(response);
         responseObject = jsonChecksum.isJsonObject() ? jsonChecksum.getAsJsonObject() : null;
      } catch (JsonParseException | IOException var17) {
         LOGGER.error("Error occurred checking for updates for game version {}.", new Object[]{this.gameVersion, var17});
         this.logConnectionInfoOnError(connection);
         return null;
      }

      if (responseObject == null) {
         LOGGER.warn("Essential does not support the following game version: {}", new Object[]{this.gameVersion});
         return null;
      } else {
         JsonElement jsonUrl = responseObject.get("url");
         jsonChecksum = responseObject.get("checksum");
         String url = jsonUrl != null && jsonUrl.isJsonPrimitive() ? jsonUrl.getAsString() : null;
         String checksum = jsonChecksum != null && jsonChecksum.isJsonPrimitive() ? responseObject.get("checksum").getAsString() : null;
         if (!StringUtils.isEmpty(url) && !StringUtils.isEmpty(checksum)) {
            return new EssentialLoaderBase.FileMeta(url, checksum);
         } else {
            LOGGER.warn("Unexpected response object data (url={}, checksum={})", new Object[]{jsonUrl, jsonChecksum});
            return null;
         }
      }
   }

   private boolean downloadFile(EssentialLoaderBase.FileMeta meta, Path target) {
      URLConnection connection = null;

      try {
         connection = this.prepareConnection(meta.url);
         Files.copy(connection.getInputStream(), target, new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
      } catch (IOException var5) {
         LOGGER.error("Error occurred when downloading file '{}'.", new Object[]{meta.url, var5});
         this.logConnectionInfoOnError(connection);
         return false;
      }

      String actualHash = this.getChecksum(target);
      if (!meta.checksum.equals(actualHash)) {
         LOGGER.warn("Downloaded Essential file checksum did not match what we expected (actual={}, expected={}", new Object[]{actualHash, meta.checksum});
         return false;
      } else {
         return true;
      }
   }

   private void logConnectionInfoOnError(URLConnection connection) {
      if (connection != null) {
         LOGGER.error("url: {}", new Object[]{connection.getURL()});
         LOGGER.error("cf-ray: {}", new Object[]{connection.getHeaderField("cf-ray")});
      }
   }

   static {
      VERSION_URL = BASE_URL + "/v1/mods/essential/loader-stage2/updates/" + BRANCH + "/%s/";
      AUTO_UPDATE = "true".equals(System.getProperty("essential.autoUpdate", "true"));
   }

   private static class FileMeta {
      String url;
      String checksum;

      public FileMeta(String url, String checksum) {
         this.url = url;
         this.checksum = checksum;
      }
   }
}
