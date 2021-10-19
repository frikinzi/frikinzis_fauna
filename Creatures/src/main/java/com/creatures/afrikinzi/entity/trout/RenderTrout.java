package com.creatures.afrikinzi.entity.trout;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderTrout extends GeoEntityRenderer<EntityTrout> {
    public RenderTrout(RenderManager renderManager) {
        super(renderManager, new ModelTrout());
        this.shadowSize = 0.3F;
    }
}
