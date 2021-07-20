package com.creatures.afrikinzi.entity.kakapo;

import com.creatures.afrikinzi.entity.kakapo.EntityKakapo;
import com.creatures.afrikinzi.entity.kakapo.ModelKakapo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.util.MatrixStack;

public class RenderKakapo extends GeoEntityRenderer<EntityKakapo> {
        public RenderKakapo(RenderManager renderManager) {
            super(renderManager, new ModelKakapo());
            this.shadowSize = 0.5F;
        }

    }
