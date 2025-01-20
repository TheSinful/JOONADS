package me.jooon.JooonAddons.features.dungeons;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.MouseInputEvent;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Mouse;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"},
   d2 = {"Lme/jooon/JooonAddons/features/dungeons/BetterStonk;", "", "()V", "createGhostBlock", "", "onPickaxeMine", "event", "Lnet/minecraftforge/fml/common/gameevent/InputEvent$MouseInputEvent;", "JooonAddons"}
)
public final class BetterStonk {
   @NotNull
   public static final BetterStonk INSTANCE = new BetterStonk();

   private BetterStonk() {
   }

   @SubscribeEvent
   public final void onPickaxeMine(@NotNull MouseInputEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getBetterStonk()) {
         if (Utils.INSTANCE.getInDungeon()) {
            if (!Config.INSTANCE.getBetterStonkShiftOnly() || JooonAddons.Companion.getMc().field_71439_g.func_70093_af()) {
               ItemStack var10000 = JooonAddons.Companion.getMc().field_71439_g.func_70694_bm();
               if (var10000 != null) {
                  ItemStack heldStack = var10000;
                  Item heldItem = heldStack.func_77973_b();
                  if (Mouse.isButtonDown(0) && (Intrinsics.areEqual(heldItem, Items.field_151005_D) || Intrinsics.areEqual(heldItem, Items.field_151046_w) || Intrinsics.areEqual(heldItem, Items.field_151035_b) || Intrinsics.areEqual(heldItem, Items.field_151035_b) || Intrinsics.areEqual(heldItem, Items.field_151050_s) || Intrinsics.areEqual(heldItem, Items.field_151039_o))) {
                     this.createGhostBlock();
                  }

               }
            }
         }
      }
   }

   public final void createGhostBlock() {
      MovingObjectPosition raytrace = JooonAddons.Companion.getMc().field_71439_g.func_174822_a(6.0D, 1.0F);
      if (raytrace != null) {
         Block block = JooonAddons.Companion.getMc().field_71439_g.field_70170_p.func_175726_f(raytrace.func_178782_a()).func_177428_a(raytrace.func_178782_a());
         if (!Intrinsics.areEqual(block, Blocks.field_150486_ae) && !Intrinsics.areEqual(block, Blocks.field_150447_bR) && !Intrinsics.areEqual(block, Blocks.field_150465_bP) && !Intrinsics.areEqual(block, Blocks.field_150442_at) && !Intrinsics.areEqual(block, Blocks.field_150438_bZ)) {
            JooonAddons.Companion.getMc().field_71439_g.field_70170_p.func_175698_g(raytrace.func_178782_a());
         }
      }
   }
}
