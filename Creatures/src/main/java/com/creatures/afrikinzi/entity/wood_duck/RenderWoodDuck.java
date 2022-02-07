package com.creatures.afrikinzi.entity.wood_duck;

import com.creatures.afrikinzi.entity.wild_duck.EntityWildDuck;
import com.creatures.afrikinzi.entity.wild_duck.ModelWildDuck;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderWoodDuck extends GeoEntityRenderer<EntityWoodDuck> {
    public RenderWoodDuck(RenderManager renderManager) {
        super(renderManager, new ModelWoodDuck());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityWoodDuck animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.6F, 0.6F, 0.6F);
        } else{
            GlStateManager.scale(0.8F, 0.8F, 0.8F);
        }
    }
}
