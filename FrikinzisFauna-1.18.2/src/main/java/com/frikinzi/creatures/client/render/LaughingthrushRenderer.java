package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.LaughingthrushModel;
import com.frikinzi.creatures.client.model.RobinModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.LaughingthrushEntity;
import com.frikinzi.creatures.entity.RobinEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class LaughingthrushRenderer extends GeoEntityRenderer<LaughingthrushEntity> {

    public LaughingthrushRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new LaughingthrushModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(LaughingthrushEntity animatable, PoseStack stack, float partialTick, MultiBufferSource bufferSource,
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
            stack.scale(0.5F,0.5F,0.5F);
        }
        stack.scale(0.6F * multiplier,0.6F *  multiplier, 0.6F * multiplier);
    }

}
