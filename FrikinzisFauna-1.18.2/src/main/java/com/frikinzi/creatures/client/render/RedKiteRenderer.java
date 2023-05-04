package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.MandarinDuckModel;
import com.frikinzi.creatures.client.model.RedKiteModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.MandarinDuckEntity;
import com.frikinzi.creatures.entity.RedKiteEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RedKiteRenderer extends GeoEntityRenderer<RedKiteEntity> {

    public RedKiteRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new RedKiteModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(RedKiteEntity animatable, PoseStack stack, float partialTick, MultiBufferSource bufferSource,
                            VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                            float partialTicks) {
        super.renderEarly(animatable, stack, partialTick, bufferSource, buffer, packedLight, packedOverlay, red,
                green, blue, partialTicks);
        Float multiplier;
        if (CreaturesConfig.height_on.get() == true) {
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
        stack.scale(multiplier, multiplier, multiplier);
    }

}
