package com.creatures.afrikinzi.entity.gyrfalcon;

import com.creatures.afrikinzi.entity.golden_eagle.ModelGoldenEagle;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderGyrfalcon extends GeoEntityRenderer<EntityGyrfalcon> {
    public RenderGyrfalcon(RenderManager renderManager) {
        super(renderManager, new ModelGyrfalcon());
        this.shadowSize = 0.5F;
    }
}
