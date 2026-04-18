package com.frikinzi.creatures.client.renderer.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.model.entity.SkuaModel;
import com.frikinzi.creatures.client.renderer.layer.SkuaHeldItemLayer;
import com.frikinzi.creatures.entity.SkuaEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class SkuaRenderer extends GeoEntityRenderer<SkuaEntity> {
    public SkuaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SkuaModel());
        this.shadowRadius = 0.4F;
        this.addRenderLayer(new SkuaHeldItemLayer(this));
    }

    @Override
    public void defaultRender(PoseStack stack, SkuaEntity animatable, MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer,
                  float yaw, float partialTick, int packedLight) {
        float multiplier;
        if (CreaturesConfig.height_on.get()) {
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
        if (animatable.isBaby()) {
            stack.scale(0.6F * multiplier, 0.6F * multiplier, 0.6F * multiplier);
        }
        stack.scale(1F * multiplier, 1F * multiplier, 1F * multiplier);
        super.defaultRender(stack, animatable, bufferSource, renderType, buffer, yaw, partialTick, packedLight);
    }
}
