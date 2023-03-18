package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.PiranhaModel;
import com.frikinzi.creatures.client.model.TigerBarbModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.PiranhaEntity;
import com.frikinzi.creatures.entity.TigerBarbEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PiranhaRenderer extends GeoEntityRenderer<PiranhaEntity>{
    public PiranhaRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PiranhaModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public void renderEarly(PiranhaEntity animatable, MatrixStack stackIn, float ticks,
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
        if (animatable.getVariant() == 3) {
            stackIn.scale(2F, 2F, 2F);
        }
        stackIn.scale(0.8F * multiplier, 0.8F * multiplier, 0.8F * multiplier);
    }
}
