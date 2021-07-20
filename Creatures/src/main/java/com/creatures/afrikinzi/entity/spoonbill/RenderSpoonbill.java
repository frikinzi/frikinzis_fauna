package com.creatures.afrikinzi.entity.spoonbill;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderSpoonbill extends GeoEntityRenderer<EntitySpoonbill> {
    public RenderSpoonbill(RenderManager renderManager) {
        super(renderManager, new ModelSpoonbill());
        this.shadowSize = 0.5F;
    }
}
