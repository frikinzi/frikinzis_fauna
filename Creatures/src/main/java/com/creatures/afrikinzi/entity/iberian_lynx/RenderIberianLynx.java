package com.creatures.afrikinzi.entity.iberian_lynx;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderIberianLynx extends GeoEntityRenderer<EntityIberianLynx> {
    public RenderIberianLynx(RenderManager renderManager) {
        super(renderManager, new ModelIberianLynx());
        this.shadowSize = 0.5F;
    }

    @Override
    public void renderEarly(EntityIberianLynx animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
        } else {
            GlStateManager.scale(0.8F, 0.8F, 0.8F); }
    }
}
