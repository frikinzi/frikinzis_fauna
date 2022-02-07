package com.creatures.afrikinzi.entity.ibis;

import com.creatures.afrikinzi.entity.roller.EntityRoller;
import com.creatures.afrikinzi.entity.roller.ModelRoller;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderIbis extends GeoEntityRenderer<EntityIbis> {
    public RenderIbis(RenderManager renderManager) {
        super(renderManager, new ModelIbis());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityIbis animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.4F, 0.4F, 0.4F);
        }
        GlStateManager.scale(0.6F, 0.6F, 0.6F);
    }
}
