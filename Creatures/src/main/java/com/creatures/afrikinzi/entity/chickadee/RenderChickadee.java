package com.creatures.afrikinzi.entity.chickadee;

import com.creatures.afrikinzi.entity.conure.ModelConure;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderChickadee extends GeoEntityRenderer<EntityChickadee> {
    public RenderChickadee(RenderManager renderManager) {
        super(renderManager, new ModelChickadee());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityChickadee animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
    }
        GlStateManager.scale(0.6F, 0.6F, 0.6F);
    }
}
