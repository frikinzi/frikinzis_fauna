package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GroundHornbillEntity;
import com.frikinzi.creatures.entity.WildDuckEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GroundHornbillModel extends GeoModel<GroundHornbillEntity> {
    @Override
    public ResourceLocation getModelResource(GroundHornbillEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/groundhornbill/groundhornbill_baby.geo.json");

        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/groundhornbill/groundhornbill.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GroundHornbillEntity object)
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
    public ResourceLocation getAnimationResource(GroundHornbillEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.groundhornbill_baby.json");

        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.groundhornbill.json");
    }
}
