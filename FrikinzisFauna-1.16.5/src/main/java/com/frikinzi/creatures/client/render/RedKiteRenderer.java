package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.RavenModel;
import com.frikinzi.creatures.client.model.RedKiteModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.RavenEntity;
import com.frikinzi.creatures.entity.RedKiteEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RedKiteRenderer extends GeoEntityRenderer<RedKiteEntity>{
    public RedKiteRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RedKiteModel());
        this.shadowRadius = 0.6F;
    }

    @Override
    public void renderEarly(RedKiteEntity animatable, MatrixStack stackIn, float ticks,
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
