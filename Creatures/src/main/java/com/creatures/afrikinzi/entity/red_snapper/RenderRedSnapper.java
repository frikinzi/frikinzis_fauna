package com.creatures.afrikinzi.entity.red_snapper;

import com.creatures.afrikinzi.entity.pike.EntityPike;
import com.creatures.afrikinzi.entity.trout.EntityTrout;
import com.creatures.afrikinzi.entity.trout.ModelTrout;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderRedSnapper extends GeoEntityRenderer<EntityRedSnapper> {
    public RenderRedSnapper(RenderManager renderManager) {
        super(renderManager, new ModelRedSnapper());
        this.shadowSize = 0.3F;
    }

    @Override
    public void renderEarly(EntityRedSnapper animatable, float ticks, float red, float green, float blue, float partialTicks)
    {
        if (animatable.isChild()) {
            GlStateManager.scale(0.4F, 0.4F, 0.4F);
        }
    }
}
