package com.frikinzi.creatures.client.renderer.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.renderer.layer.CormorantHeldItemLayer;
import com.frikinzi.creatures.client.renderer.layer.KingfisherHeldItemLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import javax.annotation.Nullable;

import com.frikinzi.creatures.client.model.entity.BuntingModel;
import com.frikinzi.creatures.client.model.entity.CormorantModel;
import com.frikinzi.creatures.entity.BuntingEntity;
import com.frikinzi.creatures.entity.CormorantEntity;

public class CormorantRenderer extends GeoEntityRenderer<CormorantEntity> {
    public CormorantRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CormorantModel());
        this.shadowRadius = 0.4F;
        this.addRenderLayer(new CormorantHeldItemLayer(this));

    }

    @Override
    public void defaultRender(PoseStack stack, CormorantEntity animatable, MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer,
                  float yaw, float partialTick, int packedLight) {
        float multiplier;
        if (CreaturesConfig.height_on.get()) {
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
        if (animatable.isBaby()) {
            stack.scale(0.5F * multiplier, 0.5F * multiplier, 0.5F * multiplier);
        }
        stack.scale(0.5F * multiplier, 0.5F * multiplier, 0.5F * multiplier);
        super.defaultRender(stack, animatable, bufferSource, renderType, buffer, yaw, partialTick, packedLight);
    }
}
