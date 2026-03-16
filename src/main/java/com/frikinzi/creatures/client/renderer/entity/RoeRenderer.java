package com.frikinzi.creatures.client.renderer.entity;

import com.frikinzi.creatures.client.model.entity.RoeModel;
import com.frikinzi.creatures.entity.egg.CreaturesRoeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class RoeRenderer extends GeoEntityRenderer<CreaturesRoeEntity> {
    public RoeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RoeModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public void defaultRender(PoseStack stack, CreaturesRoeEntity animatable, MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer,
                  float yaw, float partialTick, int packedLight) {
        stack.scale(0.6F, 0.6F, 0.6F);
        super.defaultRender(stack, animatable, bufferSource, renderType, buffer, yaw, partialTick, packedLight);
    }
}
