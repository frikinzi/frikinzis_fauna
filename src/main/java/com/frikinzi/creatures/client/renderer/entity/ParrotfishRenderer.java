package com.frikinzi.creatures.client.renderer.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.model.entity.ParrotfishModel;
import com.frikinzi.creatures.entity.ParrotfishEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class ParrotfishRenderer extends GeoEntityRenderer<ParrotfishEntity>{
    public ParrotfishRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ParrotfishModel());
        this.shadowRadius = 0.3F;
    }

    @Override
    public void defaultRender(PoseStack stack, ParrotfishEntity animatable, MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer,
                  float yaw, float partialTick, int packedLight) {
        float multiplier;
        if (CreaturesConfig.height_on.get()) {
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
        if (animatable.getVariant() == 9 || animatable.getVariant() == 1) {
            stack.scale(0.9F * multiplier, 0.9F * multiplier, 0.9F * multiplier);
        } else {
            stack.scale(0.6F * multiplier, 0.6F * multiplier, 0.6F * multiplier);
        }
        super.defaultRender(stack, animatable, bufferSource, renderType, buffer, yaw, partialTick, packedLight);
    }

    @Override
    public RenderType getRenderType(ParrotfishEntity animatable, ResourceLocation textureLocation,
                                    @Nullable MultiBufferSource bufferSource, float partialTick) {
        if (animatable.isBaby()) {
            return RenderType.entityTranslucent(textureLocation);
        }
        return super.getRenderType(animatable, textureLocation, bufferSource, partialTick);
    }
}
