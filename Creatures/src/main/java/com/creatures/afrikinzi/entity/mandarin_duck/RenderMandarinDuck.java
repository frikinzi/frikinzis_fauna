package com.creatures.afrikinzi.entity.mandarin_duck;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderMandarinDuck extends GeoEntityRenderer<EntityMandarinDuck> {
    public RenderMandarinDuck(RenderManager renderManager) {
        super(renderManager, new ModelMandarinDuck());
        this.shadowSize = 0.5F;
    }

    @Override
    public void renderEarly(EntityMandarinDuck animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (!animatable.isChild()) {
        GlStateManager.scale(1F, 1F, 1F);
        } else {
            GlStateManager.scale(0.7F, 0.7F, 0.7F);
        }
    }

}
