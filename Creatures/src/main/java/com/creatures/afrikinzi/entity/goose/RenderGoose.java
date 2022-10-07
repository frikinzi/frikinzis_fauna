package com.creatures.afrikinzi.entity.goose;

import com.creatures.afrikinzi.entity.fairy_wren.EntityFairyWren;
import com.creatures.afrikinzi.entity.fairy_wren.ModelFairyWren;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderGoose extends GeoEntityRenderer<EntityGoose> {
    public RenderGoose(RenderManager renderManager) {
        super(renderManager, new ModelGoose());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityGoose animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        GlStateManager.scale(0.7F, 0.7F, 0.7F);
    }
}
