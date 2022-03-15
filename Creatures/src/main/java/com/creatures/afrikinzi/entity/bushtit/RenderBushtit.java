package com.creatures.afrikinzi.entity.bushtit;

import com.creatures.afrikinzi.entity.barn_owl.EntityBarnOwl;
import com.creatures.afrikinzi.entity.barn_owl.ModelBarnOwl;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderBushtit extends GeoEntityRenderer<EntityBushtit> {
    public RenderBushtit(RenderManager renderManager) {
        super(renderManager, new ModelBushtit());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityBushtit animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
        }
            GlStateManager.scale(0.7F, 0.7F, 0.7F);
    }
}
