package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.TarantulaModel;
import com.frikinzi.creatures.client.model.VampireCrabModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.TarantulaEntity;
import com.frikinzi.creatures.entity.VampireCrabEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TarantulaRenderer extends GeoEntityRenderer<TarantulaEntity> {
    public TarantulaRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TarantulaModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public void renderEarly(TarantulaEntity animatable, MatrixStack stackIn, float ticks,
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
        if (animatable.isBaby()) {
            stackIn.scale(0.5F, 0.5F, 0.5F);
        }
        if (animatable.getVariant() == 1 || animatable.getVariant() == 2 || animatable.getVariant() == 5 || animatable.getVariant() == 8) {
            stackIn.scale(0.7F, 0.7F, 0.7F);
        }
        stackIn.scale(0.6F *multiplier, 0.6F *multiplier, 0.6F *multiplier);
    }
}
