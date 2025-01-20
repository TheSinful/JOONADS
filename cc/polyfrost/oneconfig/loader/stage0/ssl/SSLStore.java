package cc.polyfrost.oneconfig.loader.stage0.ssl;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class SSLStore {
   private final CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
   private final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

   public SSLStore() throws Exception {
      Path keyStorePath = Paths.get(System.getProperty("java.home"), "lib", "security", "cacerts");
      this.keyStore.load(Files.newInputStream(keyStorePath), (char[])null);
   }

   public SSLStore load(String sslFile) throws Exception {
      InputStream certificateResource = SSLStore.class.getResourceAsStream(sslFile);
      Throwable sslThrowable = null;

      try {
         InputStream certStream = new BufferedInputStream(certificateResource);
         Certificate generatedCertificate = this.certificateFactory.generateCertificate(certStream);
         this.keyStore.setCertificateEntry(sslFile, generatedCertificate);
      } catch (Throwable var13) {
         sslThrowable = var13;
         throw var13;
      } finally {
         if (certificateResource != null) {
            try {
               certificateResource.close();
            } catch (Throwable var12) {
               sslThrowable.addSuppressed(var12);
            }
         } else {
            certificateResource.close();
         }

      }

      return this;
   }

   public SSLContext finish() throws Exception {
      TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      trustManagerFactory.init(this.keyStore);
      SSLContext sslContext = SSLContext.getInstance("TLS");
      sslContext.init((KeyManager[])null, trustManagerFactory.getTrustManagers(), (SecureRandom)null);
      return sslContext;
   }
}
