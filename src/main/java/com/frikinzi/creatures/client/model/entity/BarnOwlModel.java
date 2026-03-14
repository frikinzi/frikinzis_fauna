package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BarnOwlEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BarnOwlModel extends GeoModel<BarnOwlEntity> {
    @Override
    public ResourceLocation getModelResource(BarnOwlEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/barnowl/barnowl_baby.geo.json");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/barnowl/barnowlfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/barnowl/barnowl.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BarnOwlEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/barnowl/barnowl_baby_sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/barnowl/barnowl_baby.png");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/barnowl/barnowlfly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/barnowl/barnowlsleep.png");
        } else {
            return new ResourceLocation(Creatures.MODID, "textures/entity/barnowl/barnowl.png");}
    }

    @Override
    public ResourceLocation getAnimationResource(BarnOwlEntity object)
    {
        if (object.isFlying() && !object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.barnowlfly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.barnowl.json");
    }
}
