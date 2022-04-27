package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.KoiModel;
import com.frikinzi.creatures.client.model.PikeModel;
import com.frikinzi.creatures.entity.KoiEntity;
import com.frikinzi.creatures.entity.PikeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PikeRenderer extends GeoEntityRenderer<PikeEntity>{
    public PikeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PikeModel());
        this.shadowRadius = 0.4F;
    }
}
