package me.jooon.JooonAddons.render.font;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.gui.GuiElement;
import me.jooon.JooonAddons.render.RenderUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J&\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004¨\u0006\u0015"},
   d2 = {"Lme/jooon/JooonAddons/render/font/FontUtils;", "", "()V", "getTimeBetween", "", "timeOne", "", "timeTwo", "smartFontPlacement", "", "position", "text", "element", "Lme/jooon/JooonAddons/gui/GuiElement;", "smartItemPlacement", "", "item", "Lnet/minecraft/item/ItemStack;", "smartTexturePlacement", "resourceBasicLocation", "resourceMirrorLocation", "JooonAddons"}
)
public final class FontUtils {
   @NotNull
   public static final FontUtils INSTANCE = new FontUtils();

   private FontUtils() {
   }

   public final float smartFontPlacement(float position, @NotNull String text, @NotNull GuiElement element) {
      Intrinsics.checkNotNullParameter(text, "text");
      Intrinsics.checkNotNullParameter(element, "element");
      float var10000;
      if (element.getActualX() + element.getActualWidth() / (float)2 < (float)(JooonAddons.Companion.getMc().field_71443_c / 4)) {
         var10000 = position;
      } else {
         float offset = 0.0F;
         switch(text.length()) {
         case 4:
            offset = 6.0F;
            break;
         case 5:
            offset = 12.0F;
            break;
         case 6:
            offset = 18.0F;
            break;
         case 14:
            offset = 66.0F;
         }

         var10000 = position - offset;
      }

      return var10000;
   }

   public final void smartTexturePlacement(float position, @NotNull GuiElement element, @NotNull String resourceBasicLocation, @NotNull String resourceMirrorLocation) {
      Intrinsics.checkNotNullParameter(element, "element");
      Intrinsics.checkNotNullParameter(resourceBasicLocation, "resourceBasicLocation");
      Intrinsics.checkNotNullParameter(resourceMirrorLocation, "resourceMirrorLocation");
      ResourceLocation textureBasic;
      if (element.getActualX() + element.getActualWidth() / (float)2 < (float)(JooonAddons.Companion.getMc().field_71443_c / 4)) {
         textureBasic = new ResourceLocation(resourceBasicLocation);
         RenderUtils.renderTexture$default(textureBasic, (int)position, 0, 0, 0, false, 56, (Object)null);
      } else {
         textureBasic = new ResourceLocation(resourceMirrorLocation);
         RenderUtils.renderTexture$default(textureBasic, (int)((double)position + (double)element.getWidth() / 1.5D), 0, 0, 0, false, 56, (Object)null);
      }

   }

   public final void smartItemPlacement(@NotNull GuiElement element, @NotNull ItemStack item) {
      Intrinsics.checkNotNullParameter(element, "element");
      Intrinsics.checkNotNullParameter(item, "item");
      int offset = 0;
      if (element.getActualX() + element.getActualWidth() / (float)2 > (float)(JooonAddons.Companion.getMc().field_71443_c / 4)) {
         offset = 34;
      }

      RenderUtils.renderItem(item, -1 + offset, 2);
   }

   @NotNull
   public final String getTimeBetween(double timeOne, double timeTwo) {
      double secondsBetween = Math.floor(timeTwo - timeOne);
      String timeFormatted = null;
      int days = false;
      int hours = false;
      int minutes = false;
      int seconds = false;
      int hours;
      if (secondsBetween > 86400.0D) {
         int days = (int)(secondsBetween / (double)86400);
         hours = (int)(secondsBetween % (double)86400 / (double)3600);
         timeFormatted = "" + days + 'd' + hours + 'h';
      } else {
         int minutes;
         if (secondsBetween > 3600.0D) {
            hours = (int)(secondsBetween / (double)3600);
            minutes = (int)(secondsBetween % (double)3600 / (double)60);
            timeFormatted = "" + hours + 'h' + minutes + 'm';
         } else {
            minutes = (int)(secondsBetween / (double)60);
            int seconds = (int)(secondsBetween % (double)60);
            timeFormatted = "" + minutes + 'm' + seconds + 's';
         }
      }

      return timeFormatted;
   }
}
