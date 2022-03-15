package com.creatures.afrikinzi.entity.shrimp;

import com.creatures.afrikinzi.entity.pike.EntityPike;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderShrimp extends GeoEntityRenderer<EntityShrimp> {
    public RenderShrimp(RenderManager renderManager) {
        super(renderManager, new ModelShrimp());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityShrimp animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.4F, 0.4F, 0.4F);
        }
    }
}
