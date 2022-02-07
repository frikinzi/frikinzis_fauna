package com.creatures.afrikinzi.entity.robin;

import com.creatures.afrikinzi.entity.roller.EntityRoller;
import com.creatures.afrikinzi.entity.roller.ModelRoller;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderRobin extends GeoEntityRenderer<EntityRobin> {
    public RenderRobin(RenderManager renderManager) {
        super(renderManager, new ModelRobin());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityRobin animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.6F, 0.6F, 0.6F);
        }
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
    }
}
