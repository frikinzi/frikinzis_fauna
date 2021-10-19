package com.creatures.afrikinzi.entity.flame_angelfish;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderFlameAngelfish extends GeoEntityRenderer<EntityFlameAngelfish> {
    public RenderFlameAngelfish(RenderManager renderManager) {
        super(renderManager, new ModelFlameAngelfish());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityFlameAngelfish animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        GlStateManager.scale(0.6F, 0.6F, 0.6F);
    }
}
