package com.creatures.afrikinzi.entity.laughingthrush;

import com.creatures.afrikinzi.entity.roller.EntityRoller;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelLaughingthrush extends AnimatedGeoModel<EntityLaughingthrush> {
    @Override
    public ResourceLocation getModelLocation(EntityLaughingthrush object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/laughingthrush/laughingthrushfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/laughingthrush/laughingthrush.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(EntityLaughingthrush object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/laughingthrush/laughingthrush" + object.getVariant() + "fly.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/laughingthrush/laughingthrush" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/laughingthrush/laughingthrush" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityLaughingthrush object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.laughingthrush.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.laughingthrush.json");
    }
}
