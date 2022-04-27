package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.WildDuckModel;
import com.frikinzi.creatures.client.model.WoodDuckModel;
import com.frikinzi.creatures.entity.WildDuckEntity;
import com.frikinzi.creatures.entity.WoodDuckEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WoodDuckRenderer extends GeoEntityRenderer<WoodDuckEntity> {
    public WoodDuckRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WoodDuckModel());
        this.shadowRadius = 0.3F;
    }

    @Override
    public void renderEarly(WoodDuckEntity animatable, MatrixStack stackIn, float ticks,
                            IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn,
                red, green, blue, partialTicks);
        if (animatable.isBaby()) {
            stackIn.scale(0.8F, 0.8F, 0.8F);
        }
        stackIn.scale(0.8F, 0.8F, 0.8F);
    }
}
