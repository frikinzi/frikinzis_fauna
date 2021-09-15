package com.creatures.afrikinzi.entity.wild_duck;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderWildDuck extends GeoEntityRenderer<EntityWildDuck> {
    public RenderWildDuck(RenderManager renderManager) {
        super(renderManager, new ModelWildDuck());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityWildDuck animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
        } else{
            GlStateManager.scale(0.8F, 0.8F, 0.8F);
        }
    }
}
