package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.PikeModel;
import com.frikinzi.creatures.client.model.SpoonbillModel;
import com.frikinzi.creatures.entity.LovebirdEntity;
import com.frikinzi.creatures.entity.PikeEntity;
import com.frikinzi.creatures.entity.SpoonbillEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SpoonbillRenderer extends GeoEntityRenderer<SpoonbillEntity>{
    public SpoonbillRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SpoonbillModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(SpoonbillEntity animatable, MatrixStack stackIn, float ticks,
                            IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn,
                red, green, blue, partialTicks);
        if (animatable.isBaby()) {
            stackIn.scale(0.5F, 0.5F, 0.5F);
        }
        stackIn.scale(0.8F, 0.8F, 0.8F);
    }
}
