package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.KingfisherModel;
import com.frikinzi.creatures.client.model.PelicanModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.KingfisherEntity;
import com.frikinzi.creatures.entity.PelicanEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PelicanRenderer extends GeoEntityRenderer<PelicanEntity> {

    public PelicanRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new PelicanModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(PelicanEntity animatable, PoseStack stack, float partialTick, MultiBufferSource bufferSource,
                            VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                            float partialTicks) {
        super.renderEarly(animatable, stack, partialTick, bufferSource, buffer, packedLight, packedOverlay, red,
                green, blue, partialTicks);
        float multiplier;
        if (CreaturesConfig.height_on.get()) {
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
        if (animatable.isBaby()) {
            stack.scale(0.5F, 0.5F, 0.5F);
        }
        stack.scale(0.8F * multiplier,0.8F *  multiplier, 0.8F * multiplier);
    }

}
