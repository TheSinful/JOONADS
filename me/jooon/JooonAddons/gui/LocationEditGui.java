package me.jooon.JooonAddons.gui;

import gg.essential.universal.UResolution;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.config.PersistentSave;
import me.jooon.JooonAddons.gui.buttons.LocationButton;
import me.jooon.JooonAddons.gui.buttons.ResizeButton;
import me.jooon.JooonAddons.utils.core.FloatPair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\b\u0010\u0016\u001a\u00020\tH\u0016J \u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\rH\u0016J\b\u0010\u001c\u001a\u00020\u0011H\u0016J\b\u0010\u001d\u001a\u00020\u0011H\u0016J \u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u0019H\u0014J \u0010 \u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u0019H\u0014J\b\u0010\"\u001a\u00020\u0011H\u0016J\b\u0010#\u001a\u00020\u0011H\u0002J\b\u0010$\u001a\u00020\u0011H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"},
   d2 = {"Lme/jooon/JooonAddons/gui/LocationEditGui;", "Lnet/minecraft/client/gui/GuiScreen;", "()V", "dragging", "Lme/jooon/JooonAddons/gui/GuiElement;", "locationButtons", "", "Lme/jooon/JooonAddons/gui/buttons/LocationButton;", "resizing", "", "resizingCorner", "Lme/jooon/JooonAddons/gui/buttons/ResizeButton$Corner;", "scaleCache", "", "xOffset", "yOffset", "actionPerformed", "", "button", "Lnet/minecraft/client/gui/GuiButton;", "addResizeCorners", "element", "doesGuiPauseGame", "drawScreen", "mouseX", "", "mouseY", "partialTicks", "handleMouseInput", "initGui", "mouseClicked", "mouseButton", "mouseReleased", "state", "onGuiClosed", "onMouseMove", "recalculateResizeButtons", "JooonAddons"}
)
@SourceDebugExtension({"SMAP\nLocationEditGui.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LocationEditGui.kt\nme/jooon/JooonAddons/gui/LocationEditGui\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 PersistentSave.kt\nme/jooon/JooonAddons/config/PersistentSave$Companion\n*L\n1#1,230:1\n800#2,11:231\n766#2:242\n857#2,2:243\n1855#2,2:245\n90#3,2:247\n*S KotlinDebug\n*F\n+ 1 LocationEditGui.kt\nme/jooon/JooonAddons/gui/LocationEditGui\n*L\n96#1:231,11\n96#1:242\n96#1:243,2\n96#1:245,2\n228#1:247,2\n*E\n"})
public final class LocationEditGui extends GuiScreen {
   private float xOffset;
   private float yOffset;
   private boolean resizing;
   @Nullable
   private ResizeButton.Corner resizingCorner;
   @Nullable
   private GuiElement dragging;
   @NotNull
   private final Map<GuiElement, LocationButton> locationButtons = (Map)(new HashMap());
   private float scaleCache;

   public boolean func_73868_f() {
      return false;
   }

   public void func_73866_w_() {
      Iterator var1 = ((Map)JooonAddons.Companion.getGuiManager().getElements()).entrySet().iterator();

      while(var1.hasNext()) {
         GuiElement value = (GuiElement)((Entry)var1.next()).getValue();
         LocationButton lb = new LocationButton(value);
         this.field_146292_n.add(lb);
         this.locationButtons.put(value, lb);
      }

   }

   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
      this.onMouseMove();
      this.recalculateResizeButtons();
      this.func_73733_a(0, 0, this.field_146294_l, this.field_146295_m, (new Color(0, 0, 0, 50)).getRGB(), (new Color(0, 0, 0, 200)).getRGB());
      Iterator var4 = this.field_146292_n.iterator();

      while(var4.hasNext()) {
         GuiButton button = (GuiButton)var4.next();
         if (button instanceof LocationButton) {
            if (((LocationButton)button).getElement().getToggled()) {
               GlStateManager.func_179094_E();
               float scale = ((LocationButton)button).getElement().getScale();
               GlStateManager.func_179109_b(((LocationButton)button).getX(), ((LocationButton)button).getY(), 0.0F);
               GlStateManager.func_179139_a((double)scale, (double)scale, 1.0D);
               button.func_146112_a(this.field_146297_k, mouseX, mouseY);
               GlStateManager.func_179121_F();
               if (((LocationButton)button).func_146115_a()) {
                  GlStateManager.func_179109_b(0.0F, 0.0F, 100.0F);
                  this.func_146283_a(CollectionsKt.listOf(((LocationButton)button).getElement().getName()), mouseX, mouseY);
                  GlStateManager.func_179109_b(0.0F, 0.0F, -100.0F);
               }
            }
         } else if (button instanceof ResizeButton) {
            GuiElement element = ((ResizeButton)button).getElement();
            GlStateManager.func_179094_E();
            float scale = element.getScale();
            GlStateManager.func_179109_b(((ResizeButton)button).getX(), ((ResizeButton)button).getY(), 0.0F);
            GlStateManager.func_179139_a((double)scale, (double)scale, 1.0D);
            button.func_146112_a(this.field_146297_k, mouseX, mouseY);
            GlStateManager.func_179121_F();
         } else {
            button.func_146112_a(this.field_146297_k, mouseX, mouseY);
         }
      }

   }

   public void func_146284_a(@NotNull GuiButton button) {
      Intrinsics.checkNotNullParameter(button, "button");
      UResolution sr = UResolution.INSTANCE;
      float minecraftScale = (float)UResolution.getScaleFactor();
      float floatMouseX = (float)Mouse.getX() / minecraftScale;
      float floatMouseY = (float)(this.field_146297_k.field_71440_d - Mouse.getY()) / minecraftScale;
      if (button instanceof LocationButton) {
         this.dragging = ((LocationButton)button).getElement();
         GuiElement var10002 = this.dragging;
         Intrinsics.checkNotNull(var10002);
         this.xOffset = floatMouseX - var10002.getActualX();
         var10002 = this.dragging;
         Intrinsics.checkNotNull(var10002);
         this.yOffset = floatMouseY - var10002.getActualY();
      } else if (button instanceof ResizeButton) {
         this.dragging = ((ResizeButton)button).getElement();
         this.resizing = true;
         this.scaleCache = ((ResizeButton)button).getElement().getScale();
         this.xOffset = floatMouseX - ((ResizeButton)button).getX();
         this.yOffset = floatMouseY - ((ResizeButton)button).getY();
         this.resizingCorner = ((ResizeButton)button).getCorner();
      }

   }

   protected void func_73864_a(int mouseX, int mouseY, int mouseButton) {
      if (mouseButton == 1) {
         List var10000 = this.field_146292_n;
         Intrinsics.checkNotNullExpressionValue(var10000, "buttonList");
         Iterable $this$forEach$iv = (Iterable)var10000;
         int $i$f$forEach = false;
         Collection destination$iv$iv = (Collection)(new ArrayList());
         int $i$f$filterTo = false;
         Iterator var9 = $this$forEach$iv.iterator();

         Object element$iv$iv;
         while(var9.hasNext()) {
            element$iv$iv = var9.next();
            if (element$iv$iv instanceof LocationButton) {
               destination$iv$iv.add(element$iv$iv);
            }
         }

         $this$forEach$iv = (Iterable)((List)destination$iv$iv);
         $i$f$forEach = false;
         destination$iv$iv = (Collection)(new ArrayList());
         $i$f$filterTo = false;
         var9 = $this$forEach$iv.iterator();

         while(var9.hasNext()) {
            element$iv$iv = var9.next();
            LocationButton it = (LocationButton)element$iv$iv;
            int var12 = false;
            Minecraft var10001 = this.field_146297_k;
            Intrinsics.checkNotNullExpressionValue(var10001, "mc");
            if (it.func_146116_c(var10001, mouseX, mouseY)) {
               destination$iv$iv.add(element$iv$iv);
            }
         }

         $this$forEach$iv = (Iterable)((List)destination$iv$iv);
         $i$f$forEach = false;
         Iterator var6 = $this$forEach$iv.iterator();

         while(var6.hasNext()) {
            Object element$iv = var6.next();
            LocationButton it = (LocationButton)element$iv;
            int var15 = false;
            it.getElement().setPos(new FloatPair(10, 10));
            it.getElement().setScale(1.0F);
         }
      }

      super.func_73864_a(mouseX, mouseY, mouseButton);
   }

   private final void onMouseMove() {
      UResolution sr = UResolution.INSTANCE;
      float minecraftScale = (float)UResolution.getScaleFactor();
      float floatMouseX = (float)Mouse.getX() / minecraftScale;
      float floatMouseY = (float)(Display.getHeight() - Mouse.getY()) / minecraftScale;
      if (this.resizing) {
         LocationButton var10000 = (LocationButton)this.locationButtons.get(this.dragging);
         if (var10000 == null) {
            return;
         }

         LocationButton locationButton = var10000;
         ResizeButton.Corner var18 = this.resizingCorner;
         float scaledX;
         float scaledY;
         float width;
         float height;
         float newWidth;
         float newHeight;
         float scaleX;
         float scaleY;
         float newScale;
         GuiElement var16;
         switch(var18 == null ? -1 : LocationEditGui.WhenMappings.$EnumSwitchMapping$0[var18.ordinal()]) {
         case -1:
         case 0:
         case 2:
         case 4:
         default:
            break;
         case 1:
            scaledX = locationButton.getX();
            scaledY = locationButton.getY();
            width = locationButton.getX2() - locationButton.getX();
            height = locationButton.getY2() - locationButton.getY();
            newWidth = floatMouseX - scaledX;
            newHeight = floatMouseY - scaledY;
            scaleX = newWidth / width;
            scaleY = newHeight / height;
            newScale = RangesKt.coerceAtLeast(RangesKt.coerceAtLeast(scaleX, scaleY / (float)2), 0.5F);
            if (locationButton.getElement().getScale() * newScale < 0.5F) {
               locationButton.getElement().setScale(0.5F);
            } else if (locationButton.getElement().getScale() * newScale > 10.0F) {
               locationButton.getElement().setScale(10.0F);
            } else {
               var16 = locationButton.getElement();
               var16.setScale(var16.getScale() * newScale);
            }
            break;
         case 3:
            scaledX = locationButton.getX();
            scaledY = locationButton.getY();
            width = locationButton.getX2() - locationButton.getX();
            height = locationButton.getY() - locationButton.getY2();
            newWidth = floatMouseX - scaledX;
            newHeight = floatMouseY - scaledY;
            scaleX = newWidth / width;
            scaleY = newHeight / height;
            newScale = RangesKt.coerceAtLeast(RangesKt.coerceAtLeast(scaleX, scaleY), 0.5F);
            if (locationButton.getElement().getScale() * newScale < 0.5F) {
               locationButton.getElement().setScale(0.5F);
            } else if (locationButton.getElement().getScale() * newScale > 10.0F) {
               locationButton.getElement().setScale(10.0F);
            } else {
               var16 = locationButton.getElement();
               var16.setScale(var16.getScale() * newScale);
            }
         }

         this.recalculateResizeButtons();
      } else if (this.dragging != null) {
         float x = (floatMouseX - this.xOffset) / (float)UResolution.getScaledWidth();
         float y = (floatMouseY - this.yOffset) / (float)UResolution.getScaledHeight();
         GuiElement var19 = this.dragging;
         Intrinsics.checkNotNull(var19);
         var19.setPos(x, y);
         GuiElement var10001 = this.dragging;
         Intrinsics.checkNotNull(var10001);
         this.addResizeCorners(var10001);
      }

   }

   private final void addResizeCorners(GuiElement element) {
      this.field_146292_n.removeIf(LocationEditGui::addResizeCorners$lambda$2);
      this.field_146292_n.removeIf(LocationEditGui::addResizeCorners$lambda$3);
      LocationButton var10000 = (LocationButton)this.locationButtons.get(element);
      if (var10000 != null) {
         LocationButton locationButton = var10000;
         float boxXOne = locationButton.getX() - (float)2 * element.getScale();
         float boxXTwo = locationButton.getX() + element.getActualWidth() - (float)2 * element.getScale();
         float boxYOne = locationButton.getY() - (float)2 * element.getScale();
         float boxYTwo = locationButton.getY() + element.getActualHeight() + (float)4 * element.getScale();
         this.field_146292_n.add(new ResizeButton(boxXOne, boxYOne, element, ResizeButton.Corner.TOP_LEFT));
         this.field_146292_n.add(new ResizeButton(boxXTwo, boxYOne, element, ResizeButton.Corner.TOP_RIGHT));
         this.field_146292_n.add(new ResizeButton(boxXOne, boxYTwo, element, ResizeButton.Corner.BOTTOM_LEFT));
         this.field_146292_n.add(new ResizeButton(boxXTwo, boxYTwo, element, ResizeButton.Corner.BOTTOM_RIGHT));
      }
   }

   private final void recalculateResizeButtons() {
      Iterator var1 = this.field_146292_n.iterator();

      while(var1.hasNext()) {
         GuiButton button = (GuiButton)var1.next();
         if (button instanceof ResizeButton) {
            ResizeButton.Corner corner = ((ResizeButton)button).getCorner();
            GuiElement element = ((ResizeButton)button).getElement();
            LocationButton var10000 = (LocationButton)this.locationButtons.get(element);
            if (var10000 != null) {
               LocationButton locationButton = var10000;
               float boxXOne = locationButton.getX() - (float)2 * element.getScale();
               float boxXTwo = locationButton.getX() + element.getActualWidth() - (float)2 * element.getScale();
               float boxYOne = locationButton.getY() - (float)2 * element.getScale();
               float boxYTwo = locationButton.getY() + element.getActualHeight() + (float)2 * element.getScale();
               switch(LocationEditGui.WhenMappings.$EnumSwitchMapping$0[corner.ordinal()]) {
               case 1:
                  ((ResizeButton)button).setX(boxXTwo);
                  ((ResizeButton)button).setY(boxYTwo);
                  break;
               case 2:
                  ((ResizeButton)button).setX(boxXOne);
                  ((ResizeButton)button).setY(boxYOne);
                  break;
               case 3:
                  ((ResizeButton)button).setX(boxXTwo);
                  ((ResizeButton)button).setY(boxYOne);
                  break;
               case 4:
                  ((ResizeButton)button).setX(boxXOne);
                  ((ResizeButton)button).setY(boxYTwo);
               }
            }
         }
      }

   }

   public void func_146274_d() {
      super.func_146274_d();
      GuiElement hovered = LocationButton.Companion.getLastHoveredElement();
      if (hovered != null) {
         hovered.setScale(RangesKt.coerceAtLeast(hovered.getScale() + (float)Mouse.getEventDWheel() / 1000.0F, 0.5F));
      }

   }

   protected void func_146286_b(int mouseX, int mouseY, int state) {
      super.func_146286_b(mouseX, mouseY, state);
      this.dragging = null;
      this.resizing = false;
      this.scaleCache = 0.0F;
   }

   public void func_146281_b() {
      PersistentSave.Companion this_$iv = PersistentSave.Companion;
      int $i$f$markDirty = false;
      this_$iv.markDirty(Reflection.getOrCreateKotlinClass(GuiManager.class));
   }

   private static final boolean addResizeCorners$lambda$2(Function1 $tmp0, Object p0) {
      Intrinsics.checkNotNullParameter($tmp0, "$tmp0");
      return (Boolean)$tmp0.invoke(p0);
   }

   private static final boolean addResizeCorners$lambda$3(Function1 $tmp0, Object p0) {
      Intrinsics.checkNotNullParameter($tmp0, "$tmp0");
      return (Boolean)$tmp0.invoke(p0);
   }

   // $FF: synthetic class
   @Metadata(
      mv = {1, 8, 0},
      k = 3,
      xi = 48
   )
   public class WhenMappings {
      // $FF: synthetic field
      public static final int[] $EnumSwitchMapping$0;

      static {
         int[] var0 = new int[ResizeButton.Corner.values().length];

         try {
            var0[ResizeButton.Corner.BOTTOM_RIGHT.ordinal()] = 1;
         } catch (NoSuchFieldError var5) {
         }

         try {
            var0[ResizeButton.Corner.TOP_LEFT.ordinal()] = 2;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[ResizeButton.Corner.TOP_RIGHT.ordinal()] = 3;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[ResizeButton.Corner.BOTTOM_LEFT.ordinal()] = 4;
         } catch (NoSuchFieldError var2) {
         }

         $EnumSwitchMapping$0 = var0;
      }
   }
}
