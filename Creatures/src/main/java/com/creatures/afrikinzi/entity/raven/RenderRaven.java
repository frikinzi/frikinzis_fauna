package com.creatures.afrikinzi.entity.raven;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderRaven extends GeoEntityRenderer<EntityRaven> {
    public RenderRaven(RenderManager renderManager) {
        super(renderManager, new ModelRaven());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityRaven animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
        }
    }
}
