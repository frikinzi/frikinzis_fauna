package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.KakapoModel;
import com.frikinzi.creatures.entity.KakapoEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KakapoRenderer extends GeoEntityRenderer<KakapoEntity>{
    public KakapoRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KakapoModel());
        this.shadowRadius = 0.4F;
    }
}
