package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.FireGobyModel;
import com.frikinzi.creatures.client.model.FlameAngelfishModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.FireGobyEntity;
import com.frikinzi.creatures.entity.FlameAngelfishEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FlameAngelfishRenderer extends GeoEntityRenderer<FlameAngelfishEntity>{
    public FlameAngelfishRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FlameAngelfishModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public void renderEarly(FlameAngelfishEntity animatable, MatrixStack stackIn, float ticks,
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
        stackIn.scale(0.6F * multiplier, 0.6F * multiplier, 0.6F * multiplier);
    }
}
