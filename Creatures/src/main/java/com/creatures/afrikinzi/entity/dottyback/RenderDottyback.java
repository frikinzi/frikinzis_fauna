package com.creatures.afrikinzi.entity.dottyback;

import com.creatures.afrikinzi.entity.blue_tang.EntityBlueTang;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderDottyback extends GeoEntityRenderer<EntityDottyback>
{

    public RenderDottyback(RenderManager renderManager) {
        super(renderManager, new ModelDottyback());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityDottyback animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.4F, 0.4F, 0.4F);
        }
    }
}
