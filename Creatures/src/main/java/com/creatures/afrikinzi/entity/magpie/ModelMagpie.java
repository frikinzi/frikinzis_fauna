package com.creatures.afrikinzi.entity.magpie;

import com.creatures.afrikinzi.entity.laughingthrush.EntityLaughingthrush;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelMagpie extends AnimatedGeoModel<EntityMagpie> {
    @Override
    public ResourceLocation getModelLocation(EntityMagpie object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/magpie/magpiefly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/magpie/magpie.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(EntityMagpie object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/magpie/magpie" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/magpie/magpie" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityMagpie object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.magpie.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.magpie.json");
    }
}
