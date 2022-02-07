package com.creatures.afrikinzi.entity.creatures_spoonbill;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderCreaturesSpoonbill extends GeoEntityRenderer<EntityCreaturesSpoonbill> {
    public RenderCreaturesSpoonbill(RenderManager renderManager) {
        super(renderManager, new ModelCreaturesSpoonbill());
        this.shadowSize = 0.4F;
    }

    @Override
    public void renderEarly(EntityCreaturesSpoonbill animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
        } else {
            GlStateManager.scale(0.8F, 0.8F, 0.8F);
        }
    }

}
