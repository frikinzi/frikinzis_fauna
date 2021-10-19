package com.creatures.afrikinzi.entity.pygmy_goose;

import com.creatures.afrikinzi.entity.roller.EntityRoller;
import com.creatures.afrikinzi.entity.roller.ModelRoller;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderPygmyGoose extends GeoEntityRenderer<EntityPygmyGoose> {
    public RenderPygmyGoose(RenderManager renderManager) {
        super(renderManager, new ModelPygmyGoose());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityPygmyGoose animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.8F, 0.8F, 0.8F);
        }
        GlStateManager.scale(0.6F, 0.6F, 0.6F);
    }
}
