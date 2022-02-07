package com.creatures.afrikinzi.entity.magpie;

import com.creatures.afrikinzi.entity.laughingthrush.EntityLaughingthrush;
import com.creatures.afrikinzi.entity.laughingthrush.ModelLaughingthrush;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderMagpie extends GeoEntityRenderer<EntityMagpie> {
    public RenderMagpie(RenderManager renderManager) {
        super(renderManager, new ModelMagpie());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityMagpie animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (!animatable.isChild()) {
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        } else {
            GlStateManager.scale(0.9F, 0.9F, 0.9F);
        }
    }

}
