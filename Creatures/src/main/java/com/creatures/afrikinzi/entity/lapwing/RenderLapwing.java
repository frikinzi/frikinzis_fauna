package com.creatures.afrikinzi.entity.lapwing;

import com.creatures.afrikinzi.entity.laughingthrush.EntityLaughingthrush;
import com.creatures.afrikinzi.entity.laughingthrush.ModelLaughingthrush;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderLapwing extends GeoEntityRenderer<EntityLapwing> {
    public RenderLapwing(RenderManager renderManager) {
        super(renderManager, new ModelLapwing());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityLapwing animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
        GlStateManager.scale(0.8F, 0.8F, 0.8F);
        } else {
            GlStateManager.scale(0.7F, 0.7F, 0.7F);
        }
    }

}
