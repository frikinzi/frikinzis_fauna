package com.creatures.afrikinzi.entity.stellers_sea_eagle;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderStellersSeaEagle extends GeoEntityRenderer<EntityStellersSeaEagle> {
    public RenderStellersSeaEagle(RenderManager renderManager) {
        super(renderManager, new ModelStellersSeaEagle());
        this.shadowSize = 0.6F;
    }

    @Override
    public void renderEarly(EntityStellersSeaEagle animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        } else{
            GlStateManager.scale(0.8F, 0.8F, 0.8F);
        }
    }

}
