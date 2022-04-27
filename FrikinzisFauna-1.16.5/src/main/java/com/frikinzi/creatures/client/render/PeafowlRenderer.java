package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.PeafowlModel;
import com.frikinzi.creatures.client.model.PygmyFalconModel;
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
        if (animatable.isBaby()) {
            stackIn.scale(0.7F, 0.7F, 0.7F);
        }
        stackIn.scale(0.8F, 0.8F, 0.8F);
    }

}
