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

import com.frikinzi.creatures.client.model.entity.PiranhaModel;
import com.frikinzi.creatures.client.model.entity.TigerBarbModel;
import com.frikinzi.creatures.entity.PiranhaEntity;
import com.frikinzi.creatures.entity.TigerBarbEntity;

public class PiranhaRenderer extends GeoEntityRenderer<PiranhaEntity>{
    public PiranhaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PiranhaModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public void defaultRender(PoseStack stack, PiranhaEntity animatable, MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer,
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
        if (animatable.getVariant() == 3) {
            stack.scale(2F, 2F, 2F);
        }
        stack.scale(0.8F * multiplier, 0.8F * multiplier, 0.8F * multiplier);
        super.defaultRender(stack, animatable, bufferSource, renderType, buffer, yaw, partialTick, packedLight);
    }
}
