package com.creatures.afrikinzi.entity.dove;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderDove extends GeoEntityRenderer<EntityDove> {
    public RenderDove(RenderManager renderManager) {
        super(renderManager, new ModelDove());
        this.shadowSize = 0.3F;
    }
}
