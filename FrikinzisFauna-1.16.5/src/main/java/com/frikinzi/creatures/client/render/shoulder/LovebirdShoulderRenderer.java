package com.frikinzi.creatures.client.render.shoulder;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.client.model.shoulder.LovebirdModel;
import com.frikinzi.creatures.entity.LovebirdEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.ParrotModel;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.util.ResourceLocation;

public class LovebirdShoulderRenderer extends MobRenderer<LovebirdEntity, LovebirdModel> {
    public LovebirdShoulderRenderer(EntityRendererManager p_i47375_1_) {
        super(p_i47375_1_, new LovebirdModel(), 0.3F);
    }

    public ResourceLocation getTextureLocation(LovebirdEntity object) {
        if (object.isBaby()) {
            if (object.getVariant() == 6 || object.getVariant() == 7 || object.getVariant() == 8) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/baby/lovebirdbaby" + object.getVariant() + object.getGender() + ".png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/baby/lovebirdbaby" + object.getVariant() + ".png");
        }
        if (object.isFlying() || !object.isOnGround()) {
            if (object.getVariant() == 6 || object.getVariant() == 7 || object.getVariant() == 8) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/lovebird" + object.getVariant() + object.getGender() + "fly.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/lovebird" + object.getVariant() + "fly.png");
        } else if (object.isSleeping()) {
            if (object.getVariant() == 6 || object.getVariant() == 7 || object.getVariant() == 8) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/lovebird" + object.getVariant() + object.getGender() + "sleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/lovebird" + object.getVariant() + "sleep.png");
        }
        if (object.getVariant() == 6 || object.getVariant() == 7 || object.getVariant() == 8) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/lovebird" + object.getVariant() + object.getGender() + ".png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/lovebird" + object.getVariant() + ".png");
    }
}
