package me.jooon.JooonAddons.utils.core;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B9\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J!\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!HÇ\u0001R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\r¨\u0006$"},
   d2 = {"Lme/jooon/JooonAddons/utils/core/GithubRelease;", "", "seen1", "", "tagName", "", "uploader", "body", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBody", "()Ljava/lang/String;", "getTagName$annotations", "()V", "getTagName", "getUploader", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "JooonAddons"}
)
public final class GithubRelease {
   @NotNull
   public static final GithubRelease.Companion Companion = new GithubRelease.Companion((DefaultConstructorMarker)null);
   @NotNull
   private final String tagName;
   @NotNull
   private final String uploader;
   @NotNull
   private final String body;

   public GithubRelease(@NotNull String tagName, @NotNull String uploader, @NotNull String body) {
      Intrinsics.checkNotNullParameter(tagName, "tagName");
      Intrinsics.checkNotNullParameter(uploader, "uploader");
      Intrinsics.checkNotNullParameter(body, "body");
      super();
      this.tagName = tagName;
      this.uploader = uploader;
      this.body = body;
   }

   @NotNull
   public final String getTagName() {
      return this.tagName;
   }

   /** @deprecated */
   // $FF: synthetic method
   @SerialName("tag_name")
   public static void getTagName$annotations() {
   }

   @NotNull
   public final String getUploader() {
      return this.uploader;
   }

   @NotNull
   public final String getBody() {
      return this.body;
   }

   @NotNull
   public final String component1() {
      return this.tagName;
   }

   @NotNull
   public final String component2() {
      return this.uploader;
   }

   @NotNull
   public final String component3() {
      return this.body;
   }

   @NotNull
   public final GithubRelease copy(@NotNull String tagName, @NotNull String uploader, @NotNull String body) {
      Intrinsics.checkNotNullParameter(tagName, "tagName");
      Intrinsics.checkNotNullParameter(uploader, "uploader");
      Intrinsics.checkNotNullParameter(body, "body");
      return new GithubRelease(tagName, uploader, body);
   }

   // $FF: synthetic method
   public static GithubRelease copy$default(GithubRelease var0, String var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = var0.tagName;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.uploader;
      }

      if ((var4 & 4) != 0) {
         var3 = var0.body;
      }

      return var0.copy(var1, var2, var3);
   }

   @NotNull
   public String toString() {
      return "GithubRelease(tagName=" + this.tagName + ", uploader=" + this.uploader + ", body=" + this.body + ')';
   }

   public int hashCode() {
      int result = this.tagName.hashCode();
      result = result * 31 + this.uploader.hashCode();
      result = result * 31 + this.body.hashCode();
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof GithubRelease)) {
         return false;
      } else {
         GithubRelease var2 = (GithubRelease)other;
         if (!Intrinsics.areEqual(this.tagName, var2.tagName)) {
            return false;
         } else if (!Intrinsics.areEqual(this.uploader, var2.uploader)) {
            return false;
         } else {
            return Intrinsics.areEqual(this.body, var2.body);
         }
      }
   }

   // $FF: synthetic method
   @JvmStatic
   public static final void write$Self(GithubRelease self, CompositeEncoder output, SerialDescriptor serialDesc) {
      output.encodeStringElement(serialDesc, 0, self.tagName);
      output.encodeStringElement(serialDesc, 1, self.uploader);
      output.encodeStringElement(serialDesc, 2, self.body);
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
   public GithubRelease(int seen1, @SerialName("tag_name") String tagName, String uploader, String body, SerializationConstructorMarker serializationConstructorMarker) {
      if (7 != (7 & seen1)) {
         PluginExceptionsKt.throwMissingFieldException(seen1, 7, GithubRelease$$serializer.INSTANCE.getDescriptor());
      }

      super();
      this.tagName = tagName;
      this.uploader = uploader;
      this.body = body;
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"},
      d2 = {"Lme/jooon/JooonAddons/utils/core/GithubRelease$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lme/jooon/JooonAddons/utils/core/GithubRelease;", "JooonAddons"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final KSerializer<GithubRelease> serializer() {
         return (KSerializer)GithubRelease$$serializer.INSTANCE;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
