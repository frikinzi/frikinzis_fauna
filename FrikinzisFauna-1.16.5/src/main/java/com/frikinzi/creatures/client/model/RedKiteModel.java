package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.KakapoEntity;
import com.frikinzi.creatures.entity.RedKiteEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RedKiteModel extends AnimatedGeoModel<RedKiteEntity> {
    @Override
    public ResourceLocation getModelLocation(RedKiteEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/baby_raptor/babyraptor.geo.json");
        } else {
            if (object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "geo/entity/red_kite/red_kitefly.geo.json");
            }
            return new ResourceLocation(Creatures.MODID, "geo/entity/red_kite/red_kite.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureLocation(RedKiteEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/baby_raptor/redkiteb.png");
        } else {
            if (object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/red_kite/redkitefly.png");
            } else if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/red_kite/redkitesleep.png");
            } else {
                return new ResourceLocation(Creatures.MODID, "textures/entity/red_kite/redkite.png");
            }
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RedKiteEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.babyraptor.json");
        } else {
            if (object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "animations/animation.red_kite.fly.json");
            }
            return new ResourceLocation(Creatures.MODID, "animations/animation.red_kite.json");
        }
    }
}
