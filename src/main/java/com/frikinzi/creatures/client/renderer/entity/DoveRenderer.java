package com.frikinzi.creatures.client.renderer.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import javax.annotation.Nullable;

import com.frikinzi.creatures.client.model.entity.DoveModel;
import com.frikinzi.creatures.client.model.entity.LovebirdModel;
import com.frikinzi.creatures.entity.DoveEntity;
import com.frikinzi.creatures.entity.LovebirdEntity;

public class DoveRenderer extends GeoEntityRenderer<DoveEntity> {
    public DoveRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DoveModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void defaultRender(PoseStack stack, DoveEntity animatable, MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer,
                  float yaw, float partialTick, int packedLight) {
        float multiplier;
        if (CreaturesConfig.height_on.get()) {
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
        if (animatable.isBaby()) {
            stack.scale(0.5F, 0.5F, 0.5F);
        }
        stack.scale(0.4F * multiplier, 0.4F * multiplier, 0.4F * multiplier);
        if (animatable.getVariant() == 9) {
            stack.scale(1.3F, 1.3f, 1.3f);

        }
        super.defaultRender(stack, animatable, bufferSource, renderType, buffer, yaw, partialTick, packedLight);
    }
}
