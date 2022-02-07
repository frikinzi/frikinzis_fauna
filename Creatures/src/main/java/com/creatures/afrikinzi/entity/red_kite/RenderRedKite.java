package com.creatures.afrikinzi.entity.red_kite;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderRedKite extends GeoEntityRenderer<EntityRedKite> {
    public RenderRedKite(RenderManager renderManager) {
        super(renderManager, new ModelRedKite());
        this.shadowSize = 0.5F;
    }

    @Override
    public void renderEarly(EntityRedKite animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.8F, 0.8F, 0.8F);
        }
    }
}
