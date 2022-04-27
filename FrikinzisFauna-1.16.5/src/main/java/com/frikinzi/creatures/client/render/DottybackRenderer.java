package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.DottybackModel;
import com.frikinzi.creatures.client.model.KoiModel;
import com.frikinzi.creatures.entity.DottybackEntity;
import com.frikinzi.creatures.entity.KoiEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DottybackRenderer extends GeoEntityRenderer<DottybackEntity>{
    public DottybackRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new DottybackModel());
        this.shadowRadius = 0.2F;
    }
}
