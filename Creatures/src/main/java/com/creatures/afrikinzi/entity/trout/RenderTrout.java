package com.creatures.afrikinzi.entity.trout;

import com.creatures.afrikinzi.entity.koi.EntityKoi;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderTrout extends GeoEntityRenderer<EntityTrout> {
    public RenderTrout(RenderManager renderManager) {
        super(renderManager, new ModelTrout());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityTrout animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.4F, 0.4F, 0.4F);
        }
    }
}
