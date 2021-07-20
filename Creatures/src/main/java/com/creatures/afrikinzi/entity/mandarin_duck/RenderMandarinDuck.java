package com.creatures.afrikinzi.entity.mandarin_duck;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderMandarinDuck extends GeoEntityRenderer {
    public RenderMandarinDuck(RenderManager renderManager) {
        super(renderManager, new ModelMandarinDuck());
        this.shadowSize = 0.3F;
    }
}
