package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.PeafowlModel;
import com.frikinzi.creatures.client.model.PygmyFalconModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.PeafowlEntity;
import com.frikinzi.creatures.entity.PygmyFalconEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PeafowlRenderer extends GeoEntityRenderer<PeafowlEntity>{
    public PeafowlRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PeafowlModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(PeafowlEntity animatable, MatrixStack stackIn, float ticks,
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
            stackIn.scale(0.7F * multiplier, 0.7F * multiplier, 0.7F * multiplier);
        }
        stackIn.scale(0.8F * multiplier, 0.8F * multiplier, 0.8F * multiplier);
    }

}
