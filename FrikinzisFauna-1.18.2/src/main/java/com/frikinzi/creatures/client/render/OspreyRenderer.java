package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.GooseModel;
import com.frikinzi.creatures.client.model.OspreyModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.GooseEntity;
import com.frikinzi.creatures.entity.OspreyEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class OspreyRenderer extends GeoEntityRenderer<OspreyEntity> {

    public OspreyRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new OspreyModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(OspreyEntity animatable, PoseStack stack, float partialTick, MultiBufferSource bufferSource,
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
        if (animatable.getGender() == 1) {
            stack.scale(0.8F, 0.8F, 0.8F);
        }
        stack.scale(0.8F * multiplier,0.8F *  multiplier, 0.8F * multiplier);
    }

}
