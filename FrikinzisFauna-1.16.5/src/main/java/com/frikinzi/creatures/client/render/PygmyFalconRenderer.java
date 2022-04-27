package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.GoldenEagleModel;
import com.frikinzi.creatures.client.model.PygmyFalconModel;
import com.frikinzi.creatures.entity.GoldenEagleEntity;
import com.frikinzi.creatures.entity.PygmyFalconEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PygmyFalconRenderer extends GeoEntityRenderer<PygmyFalconEntity>{
    public PygmyFalconRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PygmyFalconModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(PygmyFalconEntity animatable, MatrixStack stackIn, float ticks,
                            IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn,
                red, green, blue, partialTicks);
        if (animatable.isBaby()) {
            stackIn.scale(0.7F, 0.7F, 0.7F);
        }
        stackIn.scale(0.7F, 0.7F, 0.7F);
    }

}
