package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.KakapoModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.IbisEntity;
import com.frikinzi.creatures.entity.KakapoEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KakapoRenderer extends GeoEntityRenderer<KakapoEntity>{
    public KakapoRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KakapoModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(KakapoEntity animatable, MatrixStack stackIn, float ticks,
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
