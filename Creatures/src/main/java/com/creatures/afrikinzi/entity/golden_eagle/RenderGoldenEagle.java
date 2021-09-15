package com.creatures.afrikinzi.entity.golden_eagle;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderGoldenEagle extends GeoEntityRenderer<EntityGoldenEagle> {
    public RenderGoldenEagle(RenderManager renderManager) {
        super(renderManager, new ModelGoldenEagle());
        this.shadowSize = 0.5F;
    }

    @Override
    public void renderEarly(EntityGoldenEagle animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
        }
        GlStateManager.scale(0.8F, 0.8F, 0.8F);
    }
}
