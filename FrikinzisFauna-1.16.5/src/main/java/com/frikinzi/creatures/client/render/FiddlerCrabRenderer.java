package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.FiddlerCrabModel;
import com.frikinzi.creatures.client.model.GhostCrabModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.FiddlerCrabEntity;
import com.frikinzi.creatures.entity.GhostCrabEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FiddlerCrabRenderer extends GeoEntityRenderer<FiddlerCrabEntity> {
    public FiddlerCrabRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FiddlerCrabModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public void renderEarly(FiddlerCrabEntity animatable, MatrixStack stackIn, float ticks,
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
            stackIn.scale(0.4F, 0.4F, 0.4F);
        }
        stackIn.scale(0.6F * multiplier, 0.6F * multiplier, 0.6F * multiplier);
    }
}
