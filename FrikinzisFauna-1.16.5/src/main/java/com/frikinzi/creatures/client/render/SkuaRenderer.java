package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.BushtitModel;
import com.frikinzi.creatures.client.model.SkuaModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.BushtitEntity;
import com.frikinzi.creatures.entity.SkuaEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SkuaRenderer extends GeoEntityRenderer<SkuaEntity> {
    public SkuaRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SkuaModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(SkuaEntity animatable, MatrixStack stackIn, float ticks,
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
<<<<<<< Updated upstream
            stackIn.scale(0.5F * multiplier, 0.5F * multiplier, 0.5F * multiplier);
=======
            stackIn.scale(0.6F * multiplier, 0.6F * multiplier, 0.6F * multiplier);
>>>>>>> Stashed changes
        }
        stackIn.scale(1F * multiplier, 1F * multiplier, 1F * multiplier);
    }
}
