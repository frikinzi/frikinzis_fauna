package com.creatures.afrikinzi.entity.dove;

import com.creatures.afrikinzi.entity.fairy_wren.EntityFairyWren;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderDove extends GeoEntityRenderer<EntityDove> {
    public RenderDove(RenderManager renderManager) {
        super(renderManager, new ModelDove());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityDove animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        GlStateManager.scale(0.7F, 0.7F, 0.7F);
    }
}
