package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.GroundHornbillModel;
import com.frikinzi.creatures.client.model.MagpieModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.GroundHornbillEntity;
import com.frikinzi.creatures.entity.MagpieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GroundHornbillRenderer extends GeoEntityRenderer<GroundHornbillEntity> {
    public GroundHornbillRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GroundHornbillModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(GroundHornbillEntity animatable, MatrixStack stackIn, float ticks,
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
        stackIn.scale(multiplier, multiplier, multiplier);

    }
}
