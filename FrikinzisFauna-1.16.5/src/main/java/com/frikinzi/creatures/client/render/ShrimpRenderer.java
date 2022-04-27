package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.KoiModel;
import com.frikinzi.creatures.client.model.ShrimpModel;
import com.frikinzi.creatures.entity.KoiEntity;
import com.frikinzi.creatures.entity.ShrimpEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ShrimpRenderer extends GeoEntityRenderer<ShrimpEntity>{
    public ShrimpRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ShrimpModel());
        this.shadowRadius = 0.2F;
    }
}
