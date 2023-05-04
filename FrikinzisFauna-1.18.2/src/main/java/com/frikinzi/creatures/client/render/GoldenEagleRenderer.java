package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.GoldenEagleModel;
import com.frikinzi.creatures.client.model.RedKiteModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.GoldenEagleEntity;
import com.frikinzi.creatures.entity.RedKiteEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GoldenEagleRenderer extends GeoEntityRenderer<GoldenEagleEntity> {

    public GoldenEagleRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new GoldenEagleModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(GoldenEagleEntity animatable, PoseStack stack, float partialTick, MultiBufferSource bufferSource,
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
        if (animatable.getGender() == 1) {
            stack.scale(0.8F, 0.8F, 0.8F);
        }
        stack.scale(0.7F * multiplier, 0.7F * multiplier, 0.7F * multiplier);
    }

}
