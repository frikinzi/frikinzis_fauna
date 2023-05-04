package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.GhostCrabModel;
import com.frikinzi.creatures.client.model.TarantulaModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.GhostCrabEntity;
import com.frikinzi.creatures.entity.TarantulaEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TarantulaRenderer extends GeoEntityRenderer<TarantulaEntity> {

    public TarantulaRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new TarantulaModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public void renderEarly(TarantulaEntity animatable, PoseStack stack, float partialTick, MultiBufferSource bufferSource,
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
        if (animatable.getVariant() == 1 || animatable.getVariant() == 2 || animatable.getVariant() == 5 || animatable.getVariant() == 8) {
            stack.scale(0.7F, 0.7F, 0.7F);
        }
        stack.scale(0.6F * multiplier,0.6F * multiplier, 0.6F * multiplier);
    }

}
