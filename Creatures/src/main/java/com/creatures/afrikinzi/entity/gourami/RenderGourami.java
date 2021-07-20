package com.creatures.afrikinzi.entity.gourami;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderGourami extends GeoEntityRenderer<EntityGourami> {
    public RenderGourami(RenderManager renderManager) {
        super(renderManager, new ModelGourami());
        this.shadowSize = 0.2F;
    }
}
