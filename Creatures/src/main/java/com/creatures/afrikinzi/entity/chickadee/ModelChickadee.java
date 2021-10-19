package com.creatures.afrikinzi.entity.chickadee;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelChickadee extends AnimatedGeoModel<EntityChickadee> {
    @Override
    public ResourceLocation getModelLocation(EntityChickadee object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/chickadee/chickadeefly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/chickadee/chickadee.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityChickadee object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/chickadee/chickadee" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/chickadee/chickadee" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityChickadee object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.chickadeefly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.chickadee.json");
    }
}
