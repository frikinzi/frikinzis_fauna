package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GoldenEagleEntity;
import com.frikinzi.creatures.entity.GyrfalconEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GyrfalconModel extends AnimatedGeoModel<GyrfalconEntity> {
    @Override
    public ResourceLocation getModelLocation(GyrfalconEntity object)
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
    public ResourceLocation getTextureLocation(GyrfalconEntity object)
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
    public ResourceLocation getAnimationFileLocation(GyrfalconEntity object)
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
