package com.creatures.afrikinzi.entity.lorikeet;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderLorikeet extends GeoEntityRenderer<EntityLorikeet> {
    public RenderLorikeet(RenderManager renderManager) {
        super(renderManager, new ModelLorikeet());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityLorikeet animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
        } else {
        GlStateManager.scale(0.8F, 0.8F, 0.8F); }
    }
}
