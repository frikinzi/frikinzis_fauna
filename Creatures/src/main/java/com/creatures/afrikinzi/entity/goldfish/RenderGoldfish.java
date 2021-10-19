package com.creatures.afrikinzi.entity.goldfish;

import com.creatures.afrikinzi.entity.pygmy_goose.EntityPygmyGoose;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderGoldfish extends GeoEntityRenderer<EntityGoldfish> {
    public RenderGoldfish(RenderManager renderManager) {
        super(renderManager, new ModelGoldfish());
        this.shadowSize = 0.2F;
    }

    @Override
    public void renderEarly(EntityGoldfish animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        GlStateManager.scale(0.6F, 0.6F, 0.6F);
    }
}
