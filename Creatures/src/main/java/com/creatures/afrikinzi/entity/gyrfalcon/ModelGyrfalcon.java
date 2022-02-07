package com.creatures.afrikinzi.entity.gyrfalcon;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelGyrfalcon extends AnimatedGeoModel<EntityGyrfalcon> {
    @Override
    public ResourceLocation getModelLocation(EntityGyrfalcon object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/baby_eagle/babyraptor.geo.json");
        }
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/gyrfalcon/gyrfalconfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/gyrfalcon/gyrfalcon.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityGyrfalcon object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/baby_eagle/gyrfalconb.png");
        }
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/gyrfalcon/gyrfalconfly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/gyrfalcon/gyrfalconsleep.png");
        } else {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/gyrfalcon/gyrfalcon.png");}
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityGyrfalcon object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.babyraptor.json");
        }
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.gyrfalcon.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.gyrfalcon.json");
    }
}
