package com.creatures.afrikinzi.entity.robin;

import com.creatures.afrikinzi.entity.roller.EntityRoller;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelRobin extends AnimatedGeoModel<EntityRobin> {
    @Override
    public ResourceLocation getModelLocation(EntityRobin object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/robin/robinfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/robin/robin.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(EntityRobin object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/robin/robin" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/robin/robin" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityRobin object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.robin.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.robin.json");
    }
}
