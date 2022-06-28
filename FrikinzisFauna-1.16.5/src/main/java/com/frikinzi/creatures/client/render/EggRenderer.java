package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.DoveModel;
import com.frikinzi.creatures.client.model.EggModel;
import com.frikinzi.creatures.entity.DoveEntity;
import com.frikinzi.creatures.entity.egg.CreaturesEggEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EggRenderer extends GeoEntityRenderer<CreaturesEggEntity> {
    public EggRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new EggModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public void renderEarly(CreaturesEggEntity animatable, MatrixStack stackIn, float ticks,
                            IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn,
                red, green, blue, partialTicks);
    }
}
