package com.creatures.afrikinzi.entity.barn_owl;

import com.creatures.afrikinzi.entity.creatures_spoonbill.EntityCreaturesSpoonbill;
import com.creatures.afrikinzi.entity.creatures_spoonbill.ModelCreaturesSpoonbill;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderBarnOwl extends GeoEntityRenderer<EntityBarnOwl> {
    public RenderBarnOwl(RenderManager renderManager) {
        super(renderManager, new ModelBarnOwl());
        this.shadowSize = 0.5F;
    }

    @Override
    public void renderEarly(EntityBarnOwl animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
        }
    }
}
