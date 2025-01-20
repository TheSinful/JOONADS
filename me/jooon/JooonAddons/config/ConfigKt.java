package me.jooon.JooonAddons.config;

import kotlin.Metadata;
import me.jooon.Seecreter.Seecreter;

@Metadata(
   mv = {1, 8, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0002¨\u0006\u0002"},
   d2 = {"openSeecreterGui", "", "JooonAddons"}
)
public final class ConfigKt {
   private static final void openSeecreterGui() {
      try {
         Seecreter.config.openGui();
      } catch (Exception var1) {
         var1.printStackTrace();
      }

   }

   // $FF: synthetic method
   public static final void access$openSeecreterGui() {
      openSeecreterGui();
   }
}
