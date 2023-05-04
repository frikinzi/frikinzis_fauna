package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BushtitEntity;
import com.frikinzi.creatures.entity.EagleOwlEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class EagleOwlModel extends AnimatedTickingGeoModel<EagleOwlEntity> {

    @Override
    public ResourceLocation getModelLocation(EagleOwlEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/eagleowl/eagleowlfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/eagleowl/eagleowl.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(EagleOwlEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/eagleowl/eagleowl" + object.getVariant() + "fly.png");
        } if (object.isSleeping()) {
        return new ResourceLocation(Creatures.MODID, "textures/entity/eagleowl/eagleowl" + object.getVariant() + "sleep.png");
    }
        return new ResourceLocation(Creatures.MODID, "textures/entity/eagleowl/eagleowl" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EagleOwlEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.eagleowl.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.eagleowl.json");
    }

}
