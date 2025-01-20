package me.jooon.JooonAddons.features.dungeons;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.events.impl.PacketEvent;
import me.jooon.JooonAddons.features.Feature;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0006\u0010\u0007\u001a\u00020\u0004¨\u0006\b"},
   d2 = {"Lme/jooon/JooonAddons/features/dungeons/Jerry;", "Lme/jooon/JooonAddons/features/Feature;", "()V", "onPacket", "", "event", "Lme/jooon/JooonAddons/events/impl/PacketEvent$ReceiveEvent;", "toggleJerry", "JooonAddons"}
)
public final class Jerry extends Feature {
   @NotNull
   public static final Jerry INSTANCE = new Jerry();

   private Jerry() {
   }

   @SubscribeEvent
   public final void onPacket(@NotNull PacketEvent.ReceiveEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (Config.INSTANCE.getJerryKB()) {
         if (this.getMc().field_71439_g != null) {
            ItemStack heldItem = this.getMc().field_71439_g.func_70694_bm();
            if (heldItem != null) {
               String var10000 = heldItem.func_82833_r();
               Intrinsics.checkNotNullExpressionValue(var10000, "heldItem.displayName");
               if (StringsKt.contains$default((CharSequence)Utils.stripColor(var10000), (CharSequence)"Bonzo's Staff", false, 2, (Object)null)) {
                  return;
               }
            }

            try {
               if (StringsKt.contains$default((CharSequence)event.getPacket().toString(), (CharSequence)"S12PacketEntityVelocity", false, 2, (Object)null)) {
                  Packet var5 = event.getPacket();
                  Intrinsics.checkNotNull(var5, "null cannot be cast to non-null type net.minecraft.network.play.server.S12PacketEntityVelocity");
                  if (((S12PacketEntityVelocity)var5).func_149412_c() == this.getMc().field_71439_g.func_145782_y()) {
                     this.getMc().field_71439_g.field_70181_x = (double)((S12PacketEntityVelocity)event.getPacket()).func_149410_e() / 8000.0D;
                     event.setCanceled(true);
                  }
               }
            } catch (Exception var4) {
               var4.printStackTrace();
               this.printdev("§7[§aJooonAddons§7]§8 Failed to apply Jerry Knockback");
            }

         }
      }
   }

   public final void toggleJerry() {
      Config.INSTANCE.setJerryKB(!Config.INSTANCE.getJerryKB());
      Utils.INSTANCE.addMessage(Config.INSTANCE.getJerryKB() ? "§7[§aJooonAddons§7]§8 Jerry: §aOn" : "§7[§aJooonAddons§7]§8 Jerry: §cOff");
      this.getMc().field_71439_g.func_85030_a("random.orb", 1.0F, 0.7F);
   }
}
