package me.jooon.JooonAddons.config;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u00020\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u0012\u0010\u0018\u001a\u00020\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"},
   d2 = {"Lme/jooon/JooonAddons/config/Cache;", "", "()V", "attempts", "", "currentDay", "currentHour", "getCurrentHour", "()I", "setCurrentHour", "(I)V", "currentMinute", "getCurrentMinute", "setCurrentMinute", "currentTime", "", "getCurrentTime", "()Ljava/lang/String;", "setCurrentTime", "(Ljava/lang/String;)V", "lastIP", "lastLocation", "getLastLocation", "setLastLocation", "lastName", "JooonAddons"}
)
public final class Cache {
   @NotNull
   public static final Cache INSTANCE = new Cache();
   @JvmField
   public static int currentDay;
   private static int currentHour;
   private static int currentMinute;
   @NotNull
   private static String currentTime = "";
   @NotNull
   private static String lastLocation = "";
   @JvmField
   @NotNull
   public static String lastIP = "";
   @JvmField
   @NotNull
   public static String lastName = "";
   @JvmField
   public static int attempts;

   private Cache() {
   }

   public final int getCurrentHour() {
      return currentHour;
   }

   public final void setCurrentHour(int <set-?>) {
      currentHour = var1;
   }

   public final int getCurrentMinute() {
      return currentMinute;
   }

   public final void setCurrentMinute(int <set-?>) {
      currentMinute = var1;
   }

   @NotNull
   public final String getCurrentTime() {
      return currentTime;
   }

   public final void setCurrentTime(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      currentTime = var1;
   }

   @NotNull
   public final String getLastLocation() {
      return lastLocation;
   }

   public final void setLastLocation(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      lastLocation = var1;
   }
}
