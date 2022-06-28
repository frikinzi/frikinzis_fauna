package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.GooseModel;
import com.frikinzi.creatures.client.model.MandarinDuckModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.GooseEntity;
import com.frikinzi.creatures.entity.MandarinDuckEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GooseRenderer extends GeoEntityRenderer<GooseEntity> {
    public GooseRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GooseModel());
        this.shadowRadius = 0.3F;
    }

    @Override
    public void renderEarly(GooseEntity animatable, MatrixStack stackIn, float ticks,
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
        stackIn.scale(0.7F * multiplier, 0.7F * multiplier, 0.7F * multiplier);
    }
}
