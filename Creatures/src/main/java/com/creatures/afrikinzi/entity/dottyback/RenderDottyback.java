package com.creatures.afrikinzi.entity.dottyback;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderDottyback extends GeoEntityRenderer<EntityDottyback>
{

    public RenderDottyback(RenderManager renderManager) {
        super(renderManager, new ModelDottyback());
        this.shadowSize = 0.2F;
    }
}
