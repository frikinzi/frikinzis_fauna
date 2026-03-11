package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GoldenEagleEntity;
import com.frikinzi.creatures.entity.GyrfalconEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GyrfalconModel extends GeoModel<GyrfalconEntity> {
    @Override
    public ResourceLocation getModelResource(GyrfalconEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/baby_raptor/babyraptor.geo.json");
        } else {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/gyrfalcon/gyrfalconfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/gyrfalcon/gyrfalcon.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureResource(GyrfalconEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/baby_raptor/gyrfalconb.png");
        } else {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/gyrfalcon/gyrfalconfly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/gyrfalcon/gyrfalconsleep.png");
        } else {
            return new ResourceLocation(Creatures.MODID, "textures/entity/gyrfalcon/gyrfalcon.png");}
        }
    }

    @Override
    public ResourceLocation getAnimationResource(GyrfalconEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.babyraptor.json");
        } else {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.gyrfalcon.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.gyrfalcon.json"); }
    }
}
