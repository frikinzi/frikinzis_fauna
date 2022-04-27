package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.GoldenEagleModel;
import com.frikinzi.creatures.client.model.StellersSeaEagleModel;
import com.frikinzi.creatures.entity.GoldenEagleEntity;
import com.frikinzi.creatures.entity.StellersSeaEagleEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class StellersSeaEagleRenderer extends GeoEntityRenderer<StellersSeaEagleEntity>{
    public StellersSeaEagleRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new StellersSeaEagleModel());
        this.shadowRadius = 0.6F;
    }

    @Override
    public void renderEarly(StellersSeaEagleEntity animatable, MatrixStack stackIn, float ticks,
                            IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn,
                red, green, blue, partialTicks);
        if (animatable.isBaby()) {
            stackIn.scale(1F, 1F, 1F);
        }
        stackIn.scale(0.7F, 0.7F, 0.7F);
    }

}
