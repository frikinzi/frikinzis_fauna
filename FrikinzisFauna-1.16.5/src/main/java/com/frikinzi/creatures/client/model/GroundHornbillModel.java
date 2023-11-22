package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GroundHornbillEntity;
import com.frikinzi.creatures.entity.WildDuckEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GroundHornbillModel extends AnimatedGeoModel<GroundHornbillEntity> {
    @Override
    public ResourceLocation getModelLocation(GroundHornbillEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/groundhornbill/groundhornbill_baby.geo.json");

        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/groundhornbill/groundhornbill.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GroundHornbillEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/groundhornbill/groundhornbill_baby_sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/groundhornbill/groundhornbill_baby.png");

        }
        if (object.isSleeping()) {
        return new ResourceLocation(Creatures.MODID, "textures/entity/groundhornbill/groundhornbill" + object.getVariant() + object.getGenderName() + "sleep.png");
    }
        return new ResourceLocation(Creatures.MODID, "textures/entity/groundhornbill/groundhornbill" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GroundHornbillEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.groundhornbill_baby.json");

        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.groundhornbill.json");
    }
}
