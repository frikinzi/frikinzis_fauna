package com.creatures.afrikinzi.entity.ghostcrab;

import com.creatures.afrikinzi.entity.dove.EntityDove;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderGhostCrab extends GeoEntityRenderer<EntityGhostCrab> {
    public RenderGhostCrab(RenderManager renderManager) {
        super(renderManager, new ModelGhostCrab());
        this.shadowSize = 0.2F;
    }
    @Override
    public void renderEarly(EntityGhostCrab animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        GlStateManager.scale(0.7F, 0.7F, 0.7F);
    }
}
