package com.creatures.afrikinzi.entity.eagleowl;

import com.creatures.afrikinzi.entity.fairy_wren.EntityFairyWren;
import com.creatures.afrikinzi.entity.fairy_wren.ModelFairyWren;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderEagleOwl extends GeoEntityRenderer<EntityEagleOwl> {
    public RenderEagleOwl(RenderManager renderManager) {
        super(renderManager, new ModelEagleOwl());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityEagleOwl animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
        }
        GlStateManager.scale(0.7F, 0.7F, 0.7F);
    }
}
