package com.creatures.afrikinzi.entity.pygmyfalcon;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderPygmyFalcon extends GeoEntityRenderer<EntityPygmyFalcon> {
    public RenderPygmyFalcon(RenderManager renderManager) {
        super(renderManager, new ModelPygmyFalcon());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityPygmyFalcon animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
        }
        GlStateManager.scale(0.7F, 0.7F, 0.7F);
    }
}
