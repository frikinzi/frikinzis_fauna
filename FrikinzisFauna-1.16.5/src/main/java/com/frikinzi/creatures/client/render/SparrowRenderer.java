package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.RollerModel;
import com.frikinzi.creatures.client.model.SparrowModel;
import com.frikinzi.creatures.entity.RollerEntity;
import com.frikinzi.creatures.entity.SparrowEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SparrowRenderer extends GeoEntityRenderer<SparrowEntity> {
    public SparrowRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SparrowModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(SparrowEntity animatable, MatrixStack stackIn, float ticks,
                            IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn,
                red, green, blue, partialTicks);
        if (animatable.isBaby()) {
            stackIn.scale(0.5F, 0.5F, 0.5F);
        }
        stackIn.scale(0.7F, 0.7F, 0.7F);
    }
}
