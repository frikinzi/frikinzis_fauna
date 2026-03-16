package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.AvocetEntity;
import com.frikinzi.creatures.entity.LapwingEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AvocetModel extends GeoModel<AvocetEntity> {
    @Override
    public ResourceLocation getModelResource(AvocetEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/avocet/avocetbaby.geo.json");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/avocet/avocetfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/avocet/avocet.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AvocetEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/avocet/avocet_1_baby_sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/avocet/avocet_1_baby.png");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/avocet/avocet" + object.getVariant() + "fly.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/avocet/avocet_" + object.getVariant() + "_sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/avocet/avocet_" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(AvocetEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.babyavocet.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.avocet.json");
    }
}
