package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BushtitEntity;
import com.frikinzi.creatures.entity.StorkEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StorkModel extends AnimatedGeoModel<StorkEntity> {
    @Override
    public ResourceLocation getModelLocation(StorkEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/stork/stork" + object.getModelNumberFromVariant() + "_baby.geo.json");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/stork/stork" + object.getModelNumberFromVariant() + "fly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/stork/stork" + object.getModelNumberFromVariant() + ".geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(StorkEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/stork/stork" + object.getModelNumberFromVariant()+ "_" + object.getVariant() + "_baby_sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/stork/stork" + object.getModelNumberFromVariant()+ "_" + object.getVariant() + "_baby.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/stork/stork" + object.getModelNumberFromVariant()+ "_" + object.getVariant() + "sleep.png");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/stork/stork" + object.getModelNumberFromVariant()+ "_"  + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/stork/stork" + object.getModelNumberFromVariant() + "_"  + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(StorkEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.stork_baby.json");

        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.stork" + object.getModelNumberFromVariant() + ".json");

    }
}
