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

import com.frikinzi.creatures.client.model.entity.GhostCrabModel;
import com.frikinzi.creatures.client.model.entity.VampireCrabModel;
import com.frikinzi.creatures.entity.GhostCrabEntity;
import com.frikinzi.creatures.entity.VampireCrabEntity;

public class VampireCrabRenderer extends GeoEntityRenderer<VampireCrabEntity> {
    public VampireCrabRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VampireCrabModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public void defaultRender(PoseStack stack, VampireCrabEntity animatable, MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer,
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
        stack.scale(0.5F *multiplier, 0.5F *multiplier, 0.5F *multiplier);
        super.defaultRender(stack, animatable, bufferSource, renderType, buffer, yaw, partialTick, packedLight);
    }
}
