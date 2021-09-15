package com.creatures.afrikinzi.entity.arowana;

import com.creatures.afrikinzi.entity.koi.ModelKoi;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderArowana extends GeoEntityRenderer<EntityArowana> {
    public RenderArowana(RenderManager renderManager) {
        super(renderManager, new ModelArowana());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityArowana animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        GlStateManager.scale(1.2F, 1.2F, 1.2F);
    }
}
