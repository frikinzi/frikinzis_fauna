package com.creatures.afrikinzi.entity.arowana;

import com.creatures.afrikinzi.entity.koi.ModelKoi;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderArowana extends GeoEntityRenderer<EntityArowana> {
    public RenderArowana(RenderManager renderManager) {
        super(renderManager, new ModelArowana());
        this.shadowSize = 0.2F;
    }
}
