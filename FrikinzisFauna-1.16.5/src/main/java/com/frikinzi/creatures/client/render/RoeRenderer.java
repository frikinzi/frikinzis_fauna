package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.EggModel;
import com.frikinzi.creatures.client.model.RoeModel;
import com.frikinzi.creatures.entity.egg.CreaturesEggEntity;
import com.frikinzi.creatures.entity.egg.CreaturesRoeEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RoeRenderer extends GeoEntityRenderer<CreaturesRoeEntity> {
    public RoeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RoeModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public void renderEarly(CreaturesRoeEntity animatable, MatrixStack stackIn, float ticks,
                            IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn,
                red, green, blue, partialTicks);
        stackIn.scale(0.6F, 0.6F, 0.6F);
    }
}
