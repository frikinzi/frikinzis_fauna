package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.BushtitModel;
import com.frikinzi.creatures.client.model.MagpieModel;
import com.frikinzi.creatures.config.CreaturesConfig;
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
<<<<<<< Updated upstream
        Float multiplier;
        if (CreaturesConfig.height_on.get() == true) {
=======
        float multiplier;
        if (CreaturesConfig.height_on.get()) {
>>>>>>> Stashed changes
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
        if (animatable.isBaby()) {
<<<<<<< Updated upstream
            stackIn.scale(0.5F * multiplier, 0.5F * multiplier, 0.5F * multiplier);
=======
            stackIn.scale(0.8F * multiplier, 0.8F * multiplier, 0.8F * multiplier);
>>>>>>> Stashed changes
        }
        stackIn.scale(0.7F * multiplier, 0.7F * multiplier, 0.7F * multiplier);
    }
}
