package me.jooon.JooonAddons.render;

import gg.essential.api.utils.Multithreading;
import java.awt.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.FloatRef;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.gui.GuiElement;
import me.jooon.JooonAddons.utils.Utils;
import me.jooon.JooonAddons.utils.core.FloatPair;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001 B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000eR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0006\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0010\"\u0004\b\u0019\u0010\u0012¨\u0006!"},
   d2 = {"Lme/jooon/JooonAddons/render/DisplayNotification;", "", "()V", "altText", "", "getAltText", "()Ljava/lang/String;", "color", "Ljava/awt/Color;", "getColor", "()Ljava/awt/Color;", "setColor", "(Ljava/awt/Color;)V", "displayTopAndBottomLines", "", "getDisplayTopAndBottomLines", "()Z", "setDisplayTopAndBottomLines", "(Z)V", "notificationText", "getNotificationText", "setNotificationText", "(Ljava/lang/String;)V", "renderNotification", "getRenderNotification", "setRenderNotification", "displayNotification", "", "text", "duration", "", "topAndBottomLines", "NotificationGUIElement", "JooonAddons"}
)
public final class DisplayNotification {
   @NotNull
   public static final DisplayNotification INSTANCE = new DisplayNotification();
   private static boolean renderNotification;
   @NotNull
   private static String notificationText = "";
   private static boolean displayTopAndBottomLines;
   @NotNull
   private static final String altText = "§k§lIWONDERWHOANDWHYISREADINGTHAT";
   @NotNull
   private static Color color;

   private DisplayNotification() {
   }

   public final boolean getRenderNotification() {
      return renderNotification;
   }

   public final void setRenderNotification(boolean <set-?>) {
      renderNotification = var1;
   }

   @NotNull
   public final String getNotificationText() {
      return notificationText;
   }

   public final void setNotificationText(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      notificationText = var1;
   }

   public final boolean getDisplayTopAndBottomLines() {
      return displayTopAndBottomLines;
   }

   public final void setDisplayTopAndBottomLines(boolean <set-?>) {
      displayTopAndBottomLines = var1;
   }

   @NotNull
   public final String getAltText() {
      return altText;
   }

   @NotNull
   public final Color getColor() {
      return color;
   }

   public final void setColor(@NotNull Color <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      color = var1;
   }

   public final void displayNotification(@NotNull String text, long duration, boolean topAndBottomLines) {
      Intrinsics.checkNotNullParameter(text, "text");
      String soundName = "random.orb";
      float volume = 1.0F;
      FloatRef pitch = new FloatRef();
      pitch.element = 1.0F;
      notificationText = text;
      displayTopAndBottomLines = topAndBottomLines;
      Multithreading.runAsync(DisplayNotification::displayNotification$lambda$0);
   }

   private static final void displayNotification$lambda$0(String $soundName, float $volume, FloatRef $pitch, long $duration) {
      Intrinsics.checkNotNullParameter($soundName, "$soundName");
      Intrinsics.checkNotNullParameter($pitch, "$pitch");
      DisplayNotification var10000 = INSTANCE;
      renderNotification = true;
      JooonAddons.Companion.getMc().field_71439_g.func_85030_a($soundName, $volume, $pitch.element);
      Thread.sleep(100L);
      $pitch.element = 1.5F;
      JooonAddons.Companion.getMc().field_71439_g.func_85030_a($soundName, $volume, $pitch.element);
      Thread.sleep(100L);
      $pitch.element = 1.8F;
      JooonAddons.Companion.getMc().field_71439_g.func_85030_a($soundName, $volume, $pitch.element);
      Thread.sleep(100L);
      $pitch.element = 1.2F;
      JooonAddons.Companion.getMc().field_71439_g.func_85030_a($soundName, $volume, $pitch.element);
      Thread.sleep(100L);
      $pitch.element = 1.0F;
      JooonAddons.Companion.getMc().field_71439_g.func_85030_a($soundName, $volume, $pitch.element);
      Thread.sleep(100L);
      $pitch.element = 2.0F;
      JooonAddons.Companion.getMc().field_71439_g.func_85030_a($soundName, $volume, $pitch.element);
      Thread.sleep($duration);
      var10000 = INSTANCE;
      renderNotification = false;
   }

   static {
      Color var10000 = Color.WHITE;
      Intrinsics.checkNotNullExpressionValue(var10000, "WHITE");
      color = var10000;
      new DisplayNotification.NotificationGUIElement();
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006¨\u0006\u0010"},
      d2 = {"Lme/jooon/JooonAddons/render/DisplayNotification$NotificationGUIElement;", "Lme/jooon/JooonAddons/gui/GuiElement;", "()V", "height", "", "getHeight", "()I", "toggled", "", "getToggled", "()Z", "width", "getWidth", "demoRender", "", "render", "JooonAddons"}
   )
   public static final class NotificationGUIElement extends GuiElement {
      public NotificationGUIElement() {
         super("Notifications", (FloatPair)null, 2, (DefaultConstructorMarker)null);
         JooonAddons.Companion.getGuiManager().registerElement((GuiElement)this);
      }

      public void render() {
         if (DisplayNotification.INSTANCE.getRenderNotification()) {
            GL11.glPushMatrix();
            GL11.glScaled(4.0D, 4.0D, 4.0D);
            GL11.glTranslated(28.75D - (double)GuiElement.Companion.getFr().func_78256_a(Utils.stripColor(DisplayNotification.INSTANCE.getNotificationText())) / (double)2, 0.0D, 0.0D);
            GuiElement.Companion.getFr().func_175065_a(DisplayNotification.INSTANCE.getNotificationText(), 0.0F, 4.0F, DisplayNotification.INSTANCE.getColor().getRGB(), true);
            GL11.glPopMatrix();
            if (DisplayNotification.INSTANCE.getDisplayTopAndBottomLines()) {
               GuiElement.Companion.getFr().func_175065_a(DisplayNotification.INSTANCE.getAltText(), 17.0F, 0.0F, 16777215, true);
               GuiElement.Companion.getFr().func_175065_a(DisplayNotification.INSTANCE.getAltText(), 17.0F, 60.0F, 16777215, true);
            }
         }

      }

      public void demoRender() {
         String text = "§4Notification";
         GuiElement.Companion.getFr().func_175065_a(DisplayNotification.INSTANCE.getAltText(), 17.0F, 0.0F, 16777215, true);
         GL11.glPushMatrix();
         GL11.glScaled(4.0D, 4.0D, 4.0D);
         GL11.glTranslated(28.75D - (double)GuiElement.Companion.getFr().func_78256_a(Utils.stripColor(text)) / (double)2, 0.0D, 0.0D);
         GuiElement.Companion.getFr().func_175065_a(text, 0.0F, 4.0F, DisplayNotification.INSTANCE.getColor().getRGB(), true);
         GL11.glPopMatrix();
         GuiElement.Companion.getFr().func_175065_a(DisplayNotification.INSTANCE.getAltText(), 17.0F, 60.0F, 16777215, true);
      }

      public boolean getToggled() {
         return true;
      }

      public int getHeight() {
         return 60;
      }

      public int getWidth() {
         return 230;
      }
   }
}
