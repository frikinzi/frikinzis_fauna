package com.creatures.afrikinzi.entity.pike;

import com.creatures.afrikinzi.entity.goldfish.EntityGoldfish;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderPike extends GeoEntityRenderer<EntityPike> {
    public RenderPike(RenderManager renderManager) {
        super(renderManager, new ModelPike());
        this.shadowSize = 0.5F;
    }

    @Override
    public void renderEarly(EntityPike animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.4F, 0.4F, 0.4F);
        }
    }
}
