package me.jooon.JooonAddons.hooks.render;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"},
   d2 = {"Lme/jooon/JooonAddons/hooks/render/ExtensionEntityLivingBase;", "", "jooonAddonsHook", "Lme/jooon/JooonAddons/hooks/render/EntityLivingBaseHook;", "getJooonAddonsHook", "()Lme/jooon/JooonAddons/hooks/render/EntityLivingBaseHook;", "JooonAddons"}
)
public interface ExtensionEntityLivingBase {
   @NotNull
   EntityLivingBaseHook getJooonAddonsHook();
}
