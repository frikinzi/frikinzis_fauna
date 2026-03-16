package com.frikinzi.creatures.client.renderer.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.model.entity.SquidModel;
import com.frikinzi.creatures.entity.SquidEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class SquidRenderer extends GeoEntityRenderer<SquidEntity>{
    public SquidRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SquidModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void defaultRender(PoseStack stack, SquidEntity animatable, MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer,
                  float yaw, float partialTick, int packedLight) {
        float multiplier;
        if (CreaturesConfig.height_on.get()) {
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
        if (animatable.isBaby()) {
            stack.scale(0.2F, 0.2F, 0.2F);
        }
        multiplier = multiplier * (float) animatable.getSizeMultiplier();
        stack.scale(1F * multiplier, 1F * multiplier, 1F * multiplier);
        super.defaultRender(stack, animatable, bufferSource, renderType, buffer, yaw, partialTick, packedLight);
    }

    @Override
    public RenderType getRenderType(SquidEntity animatable, ResourceLocation textureLocation,
                                    @Nullable MultiBufferSource bufferSource, float partialTick) {
        if (animatable.isBaby()) {
            return RenderType.entityTranslucent(textureLocation);
        }
        return super.getRenderType(animatable, textureLocation, bufferSource, partialTick);
    }
}
