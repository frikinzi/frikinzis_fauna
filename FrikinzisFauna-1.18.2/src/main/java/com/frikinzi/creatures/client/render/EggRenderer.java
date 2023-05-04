package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.EggModel;
import com.frikinzi.creatures.client.model.LovebirdModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.LovebirdEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EggRenderer extends GeoEntityRenderer<EggEntity> {

    public EggRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new EggModel());
    }

    @Override
    public void renderEarly(EggEntity animatable, PoseStack stack, float partialTick, MultiBufferSource bufferSource,
                            VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                            float partialTicks) {
        super.renderEarly(animatable, stack, partialTick, bufferSource, buffer, packedLight, packedOverlay, red,
                green, blue, partialTicks);
        Float multiplier;
        if (CreaturesConfig.height_on.get()) {
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
        stack.scale(multiplier, multiplier, multiplier);
    }

}
