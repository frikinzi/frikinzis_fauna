package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.ArowanaModel;
import com.frikinzi.creatures.client.model.TroutModel;
import com.frikinzi.creatures.entity.ArowanaEntity;
import com.frikinzi.creatures.entity.TroutEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TroutRenderer extends GeoEntityRenderer<TroutEntity>{
    public TroutRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TroutModel());
        this.shadowRadius = 0.3F;
    }
}
