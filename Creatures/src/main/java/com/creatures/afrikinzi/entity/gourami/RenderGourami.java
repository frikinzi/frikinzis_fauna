package com.creatures.afrikinzi.entity.gourami;

import com.creatures.afrikinzi.entity.trout.EntityTrout;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderGourami extends GeoEntityRenderer<EntityGourami> {
    public RenderGourami(RenderManager renderManager) {
        super(renderManager, new ModelGourami());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityGourami animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.4F, 0.4F, 0.4F);
        }
    }
}
