package com.frikinzi.creatures.client.renderer.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.model.entity.GuppyModel;
import com.frikinzi.creatures.client.model.entity.TetraModel;
import com.frikinzi.creatures.entity.GuppyEntity;
import com.frikinzi.creatures.entity.SquidEntity;
import com.frikinzi.creatures.entity.TetraEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class TetraRenderer extends GeoEntityRenderer<TetraEntity>{
    public TetraRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TetraModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public void defaultRender(PoseStack stack, TetraEntity animatable, MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer,
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
        stack.scale(0.6F * multiplier, 0.6F * multiplier, 0.6F * multiplier);
        super.defaultRender(stack, animatable, bufferSource, renderType, buffer, yaw, partialTick, packedLight);
    }

    @Override
    public RenderType getRenderType(TetraEntity animatable, ResourceLocation textureLocation,
                                    @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(textureLocation);

    }
}
