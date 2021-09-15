package com.creatures.afrikinzi.entity.conure;

import com.creatures.afrikinzi.entity.lovebird.EntityLovebird;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelConure extends AnimatedGeoModel<EntityConure> {
    @Override
    public ResourceLocation getModelLocation(EntityConure object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/conure/conurefly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/conure/conure.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityConure object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/conure/conure" + object.getVariant() + "fly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/conure/conure" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/conure/conure" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityConure object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.conure.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.conure.json");
    }
}
