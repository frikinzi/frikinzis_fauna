package com.creatures.afrikinzi.entity.lovebird;

import com.creatures.afrikinzi.entity.lorikeet.EntityLorikeet;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.util.MatrixStack;

public class RenderLovebird extends GeoEntityRenderer<EntityLovebird> {
    public RenderLovebird(RenderManager renderManager) {
        super(renderManager, new ModelLovebird());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityLovebird animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.4F, 0.4F, 0.4F);
        } else {
        GlStateManager.scale(0.8F, 0.8F, 0.8F); }
    }
}
