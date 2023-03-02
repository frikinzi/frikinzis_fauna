package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.CapercaillieModel;
import com.frikinzi.creatures.client.model.MonalModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.CapercaillieEntity;
import com.frikinzi.creatures.entity.MonalEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CapercaillieRenderer extends GeoEntityRenderer<CapercaillieEntity>{
    public CapercaillieRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CapercaillieModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(CapercaillieEntity animatable, MatrixStack stackIn, float ticks,
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
        stackIn.scale(0.7F * multiplier, 0.7F * multiplier, 0.7F * multiplier);
    }

}
