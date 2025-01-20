package me.jooon.JooonAddons.gui.buttons;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.events.UIClickEvent;
import gg.essential.universal.USound;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 3,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"},
   d2 = {"<anonymous>", "", "Lgg/essential/elementa/UIComponent;", "it", "Lgg/essential/elementa/events/UIClickEvent;", "invoke", "gg/essential/vigilance/utils/ExtensionsKt$onLeftClick$1"}
)
@SourceDebugExtension({"SMAP\nExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Extensions.kt\ngg/essential/vigilance/utils/ExtensionsKt$onLeftClick$1\n+ 2 SimpleButton.kt\nme/jooon/JooonAddons/gui/buttons/SimpleButton\n*L\n1#1,10:1\n85#2,2:11\n*E\n"})
public final class SimpleButton$special$$inlined$onLeftClick$1 extends Lambda implements Function2<UIComponent, UIClickEvent, Unit> {
   public SimpleButton$special$$inlined$onLeftClick$1() {
      super(2);
   }

   public final void invoke(@NotNull UIComponent $this$onMouseClick, @NotNull UIClickEvent it) {
      Intrinsics.checkNotNullParameter($this$onMouseClick, "$this$onMouseClick");
      Intrinsics.checkNotNullParameter(it, "it");
      if (it.getMouseButton() == 0) {
         int var5 = false;
         USound.playButtonPress$default(USound.INSTANCE, 0.0F, 1, (Object)null);
      }

   }
}
