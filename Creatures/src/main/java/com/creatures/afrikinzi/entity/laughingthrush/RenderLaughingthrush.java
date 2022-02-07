package com.creatures.afrikinzi.entity.laughingthrush;

import com.creatures.afrikinzi.entity.mandarin_duck.EntityMandarinDuck;
import com.creatures.afrikinzi.entity.mandarin_duck.ModelMandarinDuck;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderLaughingthrush extends GeoEntityRenderer<EntityLaughingthrush> {
    public RenderLaughingthrush(RenderManager renderManager) {
        super(renderManager, new ModelLaughingthrush());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityLaughingthrush animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (!animatable.isChild()) {
        GlStateManager.scale(0.6F, 0.6F, 0.6F);
        } else {
            GlStateManager.scale(0.8F, 0.8F, 0.8F);
        }
    }

}
