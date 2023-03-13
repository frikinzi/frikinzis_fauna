package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.ArapaimaModel;
import com.frikinzi.creatures.client.model.ArowanaModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ArapaimaEntity;
import com.frikinzi.creatures.entity.ArowanaEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ArapaimaRenderer extends GeoEntityRenderer<ArapaimaEntity>{
    public ArapaimaRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ArapaimaModel());
        this.shadowRadius = 0.3F;
    }

    @Override
    public void renderEarly(ArapaimaEntity animatable, MatrixStack stackIn, float ticks,
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
            stackIn.scale(0.8F, 0.8F, 0.8F);
        }
        stackIn.scale(1F * multiplier, 1F * multiplier, 1F * multiplier);
    }
}
