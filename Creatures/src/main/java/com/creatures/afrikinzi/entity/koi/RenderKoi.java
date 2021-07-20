package com.creatures.afrikinzi.entity.koi;

import com.creatures.afrikinzi.util.Reference;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderKoi extends GeoEntityRenderer<EntityKoi>
{

    public RenderKoi(RenderManager renderManager) {
        super(renderManager, new ModelKoi());
        this.shadowSize = 0.2F;
    }
}
