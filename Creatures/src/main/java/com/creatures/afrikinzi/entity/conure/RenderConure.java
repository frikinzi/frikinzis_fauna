package com.creatures.afrikinzi.entity.conure;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityCreature;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderConure extends GeoEntityRenderer<EntityConure> {
    public RenderConure(RenderManager renderManager) {
        super(renderManager, new ModelConure());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityConure animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        GlStateManager.scale(0.8F, 0.8F, 0.8F);
    }
}
