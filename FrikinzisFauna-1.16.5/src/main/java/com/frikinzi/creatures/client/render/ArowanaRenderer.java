package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.ArowanaModel;
import com.frikinzi.creatures.entity.ArowanaEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ArowanaRenderer extends GeoEntityRenderer<ArowanaEntity>{
    public ArowanaRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ArowanaModel());
        this.shadowRadius = 0.3F;
    }
}
