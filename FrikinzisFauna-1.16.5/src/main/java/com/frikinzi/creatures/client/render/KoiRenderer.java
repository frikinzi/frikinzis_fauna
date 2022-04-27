package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.entity.KoiEntity;
import com.frikinzi.creatures.client.model.KoiModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KoiRenderer extends GeoEntityRenderer<KoiEntity>{
    public KoiRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KoiModel());
        this.shadowRadius = 0.2F;
    }
}
