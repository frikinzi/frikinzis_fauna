package com.creatures.afrikinzi.entity.swallow;

import com.creatures.afrikinzi.entity.roller.EntityRoller;
import com.creatures.afrikinzi.entity.roller.ModelRoller;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderSwallow extends GeoEntityRenderer<EntitySwallow> {
    public RenderSwallow(RenderManager renderManager) {
        super(renderManager, new ModelSwallow());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntitySwallow animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.4F, 0.4F, 0.4F);
        }
        GlStateManager.scale(0.7F, 0.7F, 0.7F);
    }
}
