package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.MandarinDuckModel;
import com.frikinzi.creatures.client.model.SpoonbillModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.MandarinDuckEntity;
import com.frikinzi.creatures.entity.SpoonbillEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MandarinDuckRenderer extends GeoEntityRenderer<MandarinDuckEntity> {

    public MandarinDuckRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new MandarinDuckModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(MandarinDuckEntity animatable, PoseStack stack, float partialTick, MultiBufferSource bufferSource,
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

        if (animatable.isBaby()) {
            stack.scale(0.8F, 0.8F, 0.8F);
        }
        stack.scale(0.9F * multiplier, 0.9F * multiplier, 0.9F * multiplier);
    }

}
