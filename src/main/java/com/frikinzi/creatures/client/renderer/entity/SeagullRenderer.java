package com.frikinzi.creatures.client.renderer.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.model.entity.SeagullModel;
import com.frikinzi.creatures.client.renderer.layer.SeagullHeldItemLayer;
import com.frikinzi.creatures.entity.SeagullEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class SeagullRenderer extends GeoEntityRenderer<SeagullEntity> {
    public SeagullRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SeagullModel());
        this.shadowRadius = 0.4F;
        this.addRenderLayer(new SeagullHeldItemLayer(this));

    }

    @Override
    public void defaultRender(PoseStack stack, SeagullEntity animatable, MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer,
                  float yaw, float partialTick, int packedLight) {
        float multiplier;
        if (CreaturesConfig.height_on.get()) {
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
        stack.scale(0.6F * multiplier, 0.6F * multiplier, 0.6F * multiplier);
        super.defaultRender(stack, animatable, bufferSource, renderType, buffer, yaw, partialTick, packedLight);
    }


}
