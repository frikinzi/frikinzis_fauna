package com.creatures.afrikinzi.entity.lovebird;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.util.MatrixStack;

public class RenderLovebird extends GeoEntityRenderer<EntityLovebird> {
    public RenderLovebird(RenderManager renderManager) {
        super(renderManager, new ModelLovebird());
        this.shadowSize = 0.2F;
    }
}
