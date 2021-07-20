package com.creatures.afrikinzi.entity.raven;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderRaven extends GeoEntityRenderer<EntityRaven> {
    public RenderRaven(RenderManager renderManager) {
        super(renderManager, new ModelRaven());
        this.shadowSize = 0.3F;
    }
}
