package com.creatures.afrikinzi.entity.lovebird;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import com.creatures.afrikinzi.entity.lovebird.EntityLovebird;


public class ModelLovebird extends AnimatedGeoModel<EntityLovebird> {
    @Override
    public ResourceLocation getModelLocation(EntityLovebird object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/lovebird/lovebirdfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/lovebird/lovebird.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityLovebird object)
    {
        if (object.isFlying() || !object.onGround) {
            if (object.getVariant() == 6 || object.getVariant() == 7 || object.getVariant() == 8) {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/lovebird/lovebird" + object.getVariant() + object.getGender() + "fly.png");
            }
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/lovebird/lovebird" + object.getVariant() + "fly.png");
        } else if (object.isSleeping()) {
            if (object.getVariant() == 6 || object.getVariant() == 7 || object.getVariant() == 8) {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/lovebird/lovebird" + object.getVariant() + object.getGender() + "sleep.png");
            }
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/lovebird/lovebird" + object.getVariant() + "sleep.png");
        }
        if (object.getVariant() == 6 || object.getVariant() == 7 || object.getVariant() == 8) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/lovebird/lovebird" + object.getVariant() + object.getGender() + ".png");
        }
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/lovebird/lovebird" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityLovebird object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.lovebird.flying.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.lovebird.json");
    }

}
