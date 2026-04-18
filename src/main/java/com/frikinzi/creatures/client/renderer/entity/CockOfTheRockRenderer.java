package com.frikinzi.creatures.client.renderer.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.model.entity.CockOfTheRockModel;
import com.frikinzi.creatures.entity.CockOfTheRockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class CockOfTheRockRenderer extends GeoEntityRenderer<CockOfTheRockEntity> {
    public CockOfTheRockRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CockOfTheRockModel());
    }

    @Override
    public void defaultRender(PoseStack stack, CockOfTheRockEntity animatable, MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer,
                  float yaw, float partialTick, int packedLight) {
        float multiplier;
        if (CreaturesConfig.height_on.get()) {
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
        stack.scale(multiplier*0.6f, multiplier*0.6f, multiplier*0.6f);
        super.defaultRender(stack, animatable, bufferSource, renderType, buffer, yaw, partialTick, packedLight);

    }
}
