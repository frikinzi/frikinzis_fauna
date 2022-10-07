package com.creatures.afrikinzi.entity.skua;

import com.creatures.afrikinzi.entity.sparrow.EntitySparrow;
import com.creatures.afrikinzi.entity.sparrow.ModelSparrow;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderSkua extends GeoEntityRenderer<EntitySkua> {
    public RenderSkua(RenderManager renderManager) {
        super(renderManager, new ModelSkua());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntitySkua animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
        }
        GlStateManager.scale(1F, 1F, 1F);
    }
}
