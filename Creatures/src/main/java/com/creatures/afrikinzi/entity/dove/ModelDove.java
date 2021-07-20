package com.creatures.afrikinzi.entity.dove;

import com.creatures.afrikinzi.entity.raven.EntityRaven;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelDove extends AnimatedGeoModel<EntityDove> {
    @Override
    public ResourceLocation getModelLocation(EntityDove object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/dove/dovefly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/dove/dove.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityDove object)
    {
        if (object.isFlying() || !object.onGround) {
            if (object.getVariant() == 1 || object.getVariant() == 5) {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/dove/dove" + object.getVariant() + object.getGender() + "fly.png");
            } else {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/dove/dove" + object.getVariant() + "fly.png");
            }
        } else if (object.isSleeping()) {
            if (object.getVariant() == 1 || object.getVariant() == 5) {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/dove/dove" + object.getVariant() + object.getGender() + "sleep.png");
            } else {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/dove/dove" + object.getVariant() + "sleep.png");
            }
        }
        else {
            if (object.getVariant() == 1 || object.getVariant() == 5) {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/dove/dove" + object.getVariant() + object.getGender() + ".png");
            } else {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/dove/dove" + object.getVariant() + ".png");
            }
            }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityDove object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/dovefly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.dove.json");
    }
}
