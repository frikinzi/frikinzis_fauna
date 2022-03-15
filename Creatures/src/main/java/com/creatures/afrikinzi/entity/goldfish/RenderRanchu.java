package com.creatures.afrikinzi.entity.goldfish;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderRanchu extends GeoEntityRenderer<EntityRanchuGoldfish> {
    public RenderRanchu(RenderManager renderManager) {
        super(renderManager, new ModelRanchu());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityRanchuGoldfish animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.6F, 0.6F, 0.6F);
        }
            GlStateManager.scale(0.8F, 0.8F, 0.8F);
    }
    }
