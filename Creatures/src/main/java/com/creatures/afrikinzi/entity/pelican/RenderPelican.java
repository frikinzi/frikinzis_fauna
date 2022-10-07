package com.creatures.afrikinzi.entity.pelican;

import com.creatures.afrikinzi.entity.pygmy_goose.EntityPygmyGoose;
import com.creatures.afrikinzi.entity.pygmy_goose.ModelPygmyGoose;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderPelican extends GeoEntityRenderer<EntityPelican> {
    public RenderPelican(RenderManager renderManager) {
        super(renderManager, new ModelPelican());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityPelican animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.6F, 0.6F, 0.6F);
        }
        GlStateManager.scale(0.8F, 0.8F, 0.8F);
    }
}
