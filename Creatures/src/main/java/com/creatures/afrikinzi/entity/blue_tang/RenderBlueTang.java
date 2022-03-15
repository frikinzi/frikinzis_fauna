package com.creatures.afrikinzi.entity.blue_tang;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderBlueTang extends GeoEntityRenderer<EntityBlueTang> {
    public RenderBlueTang(RenderManager renderManager) {
        super(renderManager, new ModelBlueTang());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityBlueTang animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.4F, 0.4F, 0.4F);
        }
        GlStateManager.scale(0.6F, 0.6F, 0.6F);
    }
}
