package com.creatures.afrikinzi.entity.peafowl;

import com.creatures.afrikinzi.entity.lovebird.EntityLovebird;
import com.creatures.afrikinzi.entity.lovebird.ModelLovebird;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderPeafowl extends GeoEntityRenderer<EntityPeafowl> {
    public RenderPeafowl(RenderManager renderManager) {
        super(renderManager, new ModelPeafowl());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityPeafowl animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.7F, 0.7F, 0.7F);
        } else {
        GlStateManager.scale(0.8F, 0.8F, 0.8F); }
    }
}
