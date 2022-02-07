package com.creatures.afrikinzi.entity.fiddler_crab;

import com.creatures.afrikinzi.entity.ghostcrab.EntityGhostCrab;
import com.creatures.afrikinzi.entity.ghostcrab.ModelGhostCrab;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderFiddlerCrab extends GeoEntityRenderer<EntityFiddlerCrab> {
    public RenderFiddlerCrab(RenderManager renderManager) {
        super(renderManager, new ModelFiddlerCrab());
        this.shadowSize = 0.1F;
    }
    @Override
    public void renderEarly(EntityFiddlerCrab animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
        }
        GlStateManager.scale(0.6F, 0.6F, 0.6F);
    }
}
