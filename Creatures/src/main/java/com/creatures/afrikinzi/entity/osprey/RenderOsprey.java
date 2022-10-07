package com.creatures.afrikinzi.entity.osprey;

import com.creatures.afrikinzi.entity.stellers_sea_eagle.EntityStellersSeaEagle;
import com.creatures.afrikinzi.entity.stellers_sea_eagle.ModelStellersSeaEagle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderOsprey extends GeoEntityRenderer<EntityOsprey> {
    public RenderOsprey(RenderManager renderManager) {
        super(renderManager, new ModelOsprey());
        this.shadowSize = 0.4F;
    }

    @Override
    public void renderEarly(EntityOsprey animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
            GlStateManager.scale(0.8F, 0.8F, 0.8F);
    }

}
