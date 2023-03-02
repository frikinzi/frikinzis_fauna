package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.GhostCrabModel;
import com.frikinzi.creatures.client.model.VampireCrabModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.GhostCrabEntity;
import com.frikinzi.creatures.entity.VampireCrabEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class VampireCrabRenderer extends GeoEntityRenderer<VampireCrabEntity> {
    public VampireCrabRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new VampireCrabModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public void renderEarly(VampireCrabEntity animatable, MatrixStack stackIn, float ticks,
                            IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn,
                red, green, blue, partialTicks);
        Float multiplier;
        if (CreaturesConfig.height_on.get() == true) {
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
        if (animatable.isBaby()) {
            stackIn.scale(0.5F, 0.5F, 0.5F);
        }
        stackIn.scale(0.5F *multiplier, 0.5F *multiplier, 0.5F *multiplier);
    }
}