package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.DoveModel;
import com.frikinzi.creatures.client.model.IbisModel;
import com.frikinzi.creatures.entity.DoveEntity;
import com.frikinzi.creatures.entity.IbisEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class IbisRenderer extends GeoEntityRenderer<IbisEntity> {
    public IbisRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new IbisModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(IbisEntity animatable, MatrixStack stackIn, float ticks,
                            IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn,
                red, green, blue, partialTicks);
        if (animatable.isBaby()) {
            stackIn.scale(0.4F, 0.4F, 0.4F);
        }
        stackIn.scale(0.6F, 0.6F, 0.6F);
    }
}
