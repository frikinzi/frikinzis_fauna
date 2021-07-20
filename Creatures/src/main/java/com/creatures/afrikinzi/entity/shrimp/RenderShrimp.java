package com.creatures.afrikinzi.entity.shrimp;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderShrimp extends GeoEntityRenderer<EntityShrimp> {
    public RenderShrimp(RenderManager renderManager) {
        super(renderManager, new ModelShrimp());
        this.shadowSize = 0.2F;
    }
}
