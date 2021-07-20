package com.creatures.afrikinzi.entity.raven;

import com.creatures.afrikinzi.entity.lovebird.EntityLovebird;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelRaven extends AnimatedGeoModel<EntityRaven> {
    @Override
    public ResourceLocation getModelLocation(EntityRaven object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/raven/ravenfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/raven/raven.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityRaven object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/raven/ravenfly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/raven/ravensleep.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/raven/raven.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityRaven object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/raven.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.raven.json");
    }

}
