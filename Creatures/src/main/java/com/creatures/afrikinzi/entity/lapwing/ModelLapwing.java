package com.creatures.afrikinzi.entity.lapwing;

import com.creatures.afrikinzi.entity.laughingthrush.EntityLaughingthrush;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelLapwing extends AnimatedGeoModel<EntityLapwing> {
    @Override
    public ResourceLocation getModelLocation(EntityLapwing object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/lapwing/lapwingbaby.geo.json");
        }
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/lapwing/lapwingfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/lapwing/lapwing.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(EntityLapwing object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/lapwing/lapwingbaby" + object.getVariant() + ".png");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/lapwing/lapwing" + object.getVariant() + "fly.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/lapwing/lapwing" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/lapwing/lapwing" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityLapwing object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.babylapwing.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.lapwing.json");
    }
}
