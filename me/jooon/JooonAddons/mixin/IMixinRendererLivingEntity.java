package me.jooon.JooonAddons.mixin;

import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({RendererLivingEntity.class})
public interface IMixinRendererLivingEntity {
   @Accessor("mainModel")
   ModelBase getMainModel();

   @Accessor("layerRenderers")
   List<LayerRenderer<EntityLivingBase>> getLayerRenderers();
}
