package com.frikinzi.creatures.client.renderer.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.model.entity.LovebirdModel;
import com.frikinzi.creatures.client.model.entity.SpoonbillModel;
import com.frikinzi.creatures.entity.LovebirdEntity;
import com.frikinzi.creatures.entity.SpoonbillEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class SpoonbillRenderer extends GeoEntityRenderer<SpoonbillEntity> {
    public SpoonbillRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SpoonbillModel());
    }

    @Override
    public void defaultRender(PoseStack stack, SpoonbillEntity animatable, MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer,
                  float yaw, float partialTick, int packedLight) {
        float multiplier;
        if (CreaturesConfig.height_on.get()) {
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
        if (animatable.isBaby()) {
            multiplier *= 0.7F;
        }
        stack.scale(0.8F * multiplier, 0.8F * multiplier, 0.8F * multiplier);
        super.defaultRender(stack, animatable, bufferSource, renderType, buffer, yaw, partialTick, packedLight);

    }
}
