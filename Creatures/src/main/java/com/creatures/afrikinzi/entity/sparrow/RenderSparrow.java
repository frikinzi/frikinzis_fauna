package com.creatures.afrikinzi.entity.sparrow;

import com.creatures.afrikinzi.entity.fairy_wren.EntityFairyWren;
import com.creatures.afrikinzi.entity.fairy_wren.ModelFairyWren;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderSparrow extends GeoEntityRenderer<EntitySparrow> {
    public RenderSparrow(RenderManager renderManager) {
        super(renderManager, new ModelSparrow());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntitySparrow animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
        }
        GlStateManager.scale(0.7F, 0.7F, 0.7F);
    }
}
