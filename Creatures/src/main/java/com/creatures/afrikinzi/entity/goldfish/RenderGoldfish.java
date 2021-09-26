package com.creatures.afrikinzi.entity.goldfish;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderGoldfish extends GeoEntityRenderer<EntityGoldfish> {
    public RenderGoldfish(RenderManager renderManager) {
        super(renderManager, new ModelGoldfish());
        this.shadowSize = 0.2F;
    }
}
