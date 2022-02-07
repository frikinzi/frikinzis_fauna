package com.creatures.afrikinzi.entity.gyrfalcon;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderGyrfalcon extends GeoEntityRenderer<EntityGyrfalcon> {
    public RenderGyrfalcon(RenderManager renderManager) {
        super(renderManager, new ModelGyrfalcon());
        this.shadowSize = 0.5F;
    }

    @Override
    public void renderEarly(EntityGyrfalcon animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.8F, 0.8F, 0.8F);
        }
    }
}
