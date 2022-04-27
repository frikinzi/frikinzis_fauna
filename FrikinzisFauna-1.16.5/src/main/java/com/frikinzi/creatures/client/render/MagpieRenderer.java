package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.BushtitModel;
import com.frikinzi.creatures.client.model.MagpieModel;
import com.frikinzi.creatures.entity.BushtitEntity;
import com.frikinzi.creatures.entity.MagpieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MagpieRenderer extends GeoEntityRenderer<MagpieEntity> {
    public MagpieRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MagpieModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(MagpieEntity animatable, MatrixStack stackIn, float ticks,
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
