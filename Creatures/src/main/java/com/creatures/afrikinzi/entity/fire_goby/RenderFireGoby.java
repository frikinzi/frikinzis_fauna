package com.creatures.afrikinzi.entity.fire_goby;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderFireGoby extends GeoEntityRenderer<EntityFireGoby> {
    public RenderFireGoby(RenderManager renderManager) {
        super(renderManager, new ModelFireGoby());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityFireGoby animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
            GlStateManager.scale(0.6F, 0.6F, 0.6F);
    }
}
