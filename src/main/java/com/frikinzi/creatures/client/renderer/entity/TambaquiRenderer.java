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

import com.frikinzi.creatures.client.model.entity.KoiModel;
import com.frikinzi.creatures.client.model.entity.TambaquiModel;
import com.frikinzi.creatures.entity.KoiEntity;
import com.frikinzi.creatures.entity.TambaquiEntity;

public class TambaquiRenderer extends GeoEntityRenderer<TambaquiEntity>{
    public TambaquiRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TambaquiModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public void defaultRender(PoseStack stack, TambaquiEntity animatable, MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer,
                  float yaw, float partialTick, int packedLight) {
        float multiplier;
        if (CreaturesConfig.height_on.get()) {
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
        stack.scale(1F * multiplier, 1F * multiplier, 1F * multiplier);
        super.defaultRender(stack, animatable, bufferSource, renderType, buffer, yaw, partialTick, packedLight);
    }
}
