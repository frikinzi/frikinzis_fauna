package com.creatures.afrikinzi.entity.koi;

import com.creatures.afrikinzi.entity.laughingthrush.EntityLaughingthrush;
import com.creatures.afrikinzi.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderKoi extends GeoEntityRenderer<EntityKoi>
{

    public RenderKoi(RenderManager renderManager) {
        super(renderManager, new ModelKoi());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityKoi animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.4F, 0.4F, 0.4F);
        }
    }
}
