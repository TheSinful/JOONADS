package me.jooon.JooonAddons.hooks.render;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.events.impl.RenderEntityModelEvent;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Metadata(
   mv = {1, 8, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"},
   d2 = {"onRenderEntityModel", "", "entity", "Lnet/minecraft/entity/EntityLivingBase;", "model", "Lnet/minecraft/client/model/ModelBase;", "partialTicks", "", "ci", "Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfo;", "JooonAddons"}
)
public final class RenderEntityHookKt {
   public static final void onRenderEntityModel(@NotNull EntityLivingBase entity, @NotNull ModelBase model, float partialTicks, @NotNull CallbackInfo ci) {
      Intrinsics.checkNotNullParameter(entity, "entity");
      Intrinsics.checkNotNullParameter(model, "model");
      Intrinsics.checkNotNullParameter(ci, "ci");
      if ((new RenderEntityModelEvent(entity, model, partialTicks)).postAndCatch()) {
         ci.cancel();
      }

   }
}
