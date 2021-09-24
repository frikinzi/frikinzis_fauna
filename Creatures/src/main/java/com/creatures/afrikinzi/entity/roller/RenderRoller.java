package com.creatures.afrikinzi.entity.roller;

import com.creatures.afrikinzi.entity.red_kite.EntityRedKite;
import com.creatures.afrikinzi.entity.red_kite.ModelRedKite;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderRoller extends GeoEntityRenderer<EntityRoller> {
    public RenderRoller(RenderManager renderManager) {
        super(renderManager, new ModelRoller());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityRoller animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.4F, 0.4F, 0.4F);
        }
        GlStateManager.scale(0.6F, 0.6F, 0.6F);
    }
}
