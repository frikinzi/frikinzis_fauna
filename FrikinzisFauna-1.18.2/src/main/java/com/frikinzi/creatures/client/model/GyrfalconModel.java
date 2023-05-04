package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GyrfalconEntity;
import com.frikinzi.creatures.entity.RedKiteEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class GyrfalconModel extends AnimatedTickingGeoModel<GyrfalconEntity> {

    @Override
    public ResourceLocation getModelLocation(GyrfalconEntity object) {
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
    public ResourceLocation getTextureLocation(GyrfalconEntity object) {
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
    public ResourceLocation getAnimationFileLocation(GyrfalconEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.babyraptor.json");
        } else {
            if (object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "animations/animation.gyrfalcon.fly.json");
            }
            return new ResourceLocation(Creatures.MODID, "animations/animation.gyrfalcon.json"); }
    }

}
