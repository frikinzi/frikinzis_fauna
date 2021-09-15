package com.creatures.afrikinzi.entity.golden_eagle;

import com.creatures.afrikinzi.entity.raven.EntityRaven;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelGoldenEagle extends AnimatedGeoModel<EntityGoldenEagle> {
    @Override
    public ResourceLocation getModelLocation(EntityGoldenEagle object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/golden_eagle/golden_eaglefly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/golden_eagle/golden_eagle.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityGoldenEagle object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/golden_eagle/golden_eaglefly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/golden_eagle/golden_eaglesleep.png");
        } else {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/golden_eagle/golden_eagle.png");}
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityGoldenEagle object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.golden_eagle.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.golden_eagle.json");
    }
}
