package com.creatures.afrikinzi.entity.guppy;

import com.creatures.afrikinzi.entity.arowana.ModelArowana;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderGuppy extends GeoEntityRenderer<EntityGuppy> {
    public RenderGuppy(RenderManager renderManager) {
        super(renderManager, new ModelGuppy());
        this.shadowSize = 0.2F;
    }
}
