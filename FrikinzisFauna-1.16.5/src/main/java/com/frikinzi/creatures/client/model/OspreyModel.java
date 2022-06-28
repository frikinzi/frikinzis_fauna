package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GyrfalconEntity;
import com.frikinzi.creatures.entity.OspreyEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OspreyModel extends AnimatedGeoModel<OspreyEntity> {
    @Override
    public ResourceLocation getModelLocation(OspreyEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/baby_raptor/babyraptor.geo.json");
        } else {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/osprey/ospreyfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/osprey/osprey.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureLocation(OspreyEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/baby_raptor/ospreyb.png");
        } else {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/osprey/ospreyfly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/osprey/ospreysleep.png");
        } else {
            return new ResourceLocation(Creatures.MODID, "textures/entity/osprey/osprey.png");}
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(OspreyEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.babyraptor.json");
        } else {
        return new ResourceLocation(Creatures.MODID, "animations/animation.osprey.json"); }
    }
}
