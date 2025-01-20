package me.jooon.JooonAddons.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({Minecraft.class})
public interface IMixinMinecraft {
   @Accessor("timer")
   Timer getTimer();
}
