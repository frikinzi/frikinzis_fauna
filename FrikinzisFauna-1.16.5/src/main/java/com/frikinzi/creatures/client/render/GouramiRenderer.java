package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.GouramiModel;
import com.frikinzi.creatures.client.model.GuppyModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.FiddlerCrabEntity;
import com.frikinzi.creatures.entity.GouramiEntity;
import com.frikinzi.creatures.entity.GuppyEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GouramiRenderer extends GeoEntityRenderer<GouramiEntity>{
    public GouramiRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GouramiModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public void renderEarly(GouramiEntity animatable, MatrixStack stackIn, float ticks,
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
        stackIn.scale(1F * multiplier, 1F * multiplier, 1F * multiplier);
    }
}