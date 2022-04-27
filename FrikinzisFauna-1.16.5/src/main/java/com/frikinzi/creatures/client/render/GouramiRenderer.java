package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.GouramiModel;
import com.frikinzi.creatures.client.model.GuppyModel;
import com.frikinzi.creatures.entity.GouramiEntity;
import com.frikinzi.creatures.entity.GuppyEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GouramiRenderer extends GeoEntityRenderer<GouramiEntity>{
    public GouramiRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GouramiModel());
        this.shadowRadius = 0.2F;
    }
}
