package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.KakapoModel;
import com.frikinzi.creatures.client.model.SpoonbillModel;
import com.frikinzi.creatures.entity.KakapoEntity;
import com.frikinzi.creatures.entity.SpoonbillEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KakapoRenderer extends GeoEntityRenderer<KakapoEntity> {

    public KakapoRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new KakapoModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(KakapoEntity animatable, PoseStack stack, float partialTick, MultiBufferSource bufferSource,
                            VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                            float partialTicks) {
        super.renderEarly(animatable, stack, partialTick, bufferSource, buffer, packedLight, packedOverlay, red,
                green, blue, partialTicks);
        Float multiplier;
        multiplier = animatable.getHeightMultiplier();
        stack.scale(multiplier, multiplier, multiplier);
    }

}
