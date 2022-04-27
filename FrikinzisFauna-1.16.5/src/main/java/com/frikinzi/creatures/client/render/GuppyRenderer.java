package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.GuppyModel;
import com.frikinzi.creatures.client.model.PikeModel;
import com.frikinzi.creatures.entity.GuppyEntity;
import com.frikinzi.creatures.entity.PikeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GuppyRenderer extends GeoEntityRenderer<GuppyEntity>{
    public GuppyRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GuppyModel());
        this.shadowRadius = 0.2F;
    }
}
