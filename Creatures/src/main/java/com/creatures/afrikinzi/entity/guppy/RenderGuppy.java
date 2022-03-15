package com.creatures.afrikinzi.entity.guppy;

import com.creatures.afrikinzi.entity.arowana.ModelArowana;
import com.creatures.afrikinzi.entity.koi.EntityKoi;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderGuppy extends GeoEntityRenderer<EntityGuppy> {
    public RenderGuppy(RenderManager renderManager) {
        super(renderManager, new ModelGuppy());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityGuppy animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (!animatable.isChild()) {
            GlStateManager.scale(0.6F, 0.6F, 0.6F);
        }
    }
}
