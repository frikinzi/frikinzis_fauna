package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.DottybackModel;
import com.frikinzi.creatures.client.model.RedSnapperModel;
import com.frikinzi.creatures.entity.DottybackEntity;
import com.frikinzi.creatures.entity.RedSnapperEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RedSnapperRenderer extends GeoEntityRenderer<RedSnapperEntity>{
    public RedSnapperRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RedSnapperModel());
        this.shadowRadius = 0.3F;
    }
}
