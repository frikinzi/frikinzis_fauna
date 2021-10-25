package com.creatures.afrikinzi.entity.fairy_wren;

import com.creatures.afrikinzi.entity.conure.EntityConure;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderFairyWren extends GeoEntityRenderer<EntityFairyWren> {
    public RenderFairyWren(RenderManager renderManager) {
        super(renderManager, new ModelFairyWren());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityFairyWren animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
        }
        GlStateManager.scale(0.7F, 0.7F, 0.7F);
    }
}
