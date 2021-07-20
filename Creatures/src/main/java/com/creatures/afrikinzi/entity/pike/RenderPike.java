package com.creatures.afrikinzi.entity.pike;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderPike extends GeoEntityRenderer<EntityPike> {
    public RenderPike(RenderManager renderManager) {
        super(renderManager, new ModelPike());
        this.shadowSize = 0.5F;
    }
}
