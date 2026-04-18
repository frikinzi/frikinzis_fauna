package com.frikinzi.creatures.client.renderer.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.model.entity.MarabouModel;
import com.frikinzi.creatures.client.model.entity.StorkModel;
import com.frikinzi.creatures.entity.MarabouEntity;
import com.frikinzi.creatures.entity.StorkEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class MarabouRenderer extends GeoEntityRenderer<MarabouEntity> {
    public MarabouRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MarabouModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void defaultRender(PoseStack stack, MarabouEntity animatable, MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer,
                              float yaw, float partialTick, int packedLight) {
        float multiplier;
        if (CreaturesConfig.height_on.get()) {
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
        if (animatable.getGender() == 1) {
            stack.scale(1.2F, 1.2f, 1.2f);
        }
        stack.scale(0.6F * multiplier, 0.6F * multiplier, 0.6F * multiplier);
        super.defaultRender(stack, animatable, bufferSource, renderType, buffer, yaw, partialTick, packedLight);
    }
}
