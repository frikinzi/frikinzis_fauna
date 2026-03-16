package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LargePenguinEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LargePenguinModel extends GeoModel<LargePenguinEntity> {
    @Override
    public ResourceLocation getModelResource(LargePenguinEntity object) {
        if (object.isBaby() || object.isPesto()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/largepenguin/largepenguinbaby.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/largepenguin/largepenguin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LargePenguinEntity object)
    {
        if (object.isBaby() || object.isPesto()) {
            if (object.isInWater()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/largepenguin/largepenguinbaby" + object.getVariant() + "swim.png");

            }
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/largepenguin/largepenguinbaby" + object.getVariant() + "sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/largepenguin/largepenguinbaby" + object.getVariant() + ".png");

        }

        if (object.onIceBlock(object) && object.moving()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/largepenguin/largepenguin" + object.getVariant() + "swim.png");

        }
        if (object.isInWater()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/largepenguin/largepenguin" + object.getVariant() + "swim.png");

        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/largepenguin/largepenguin" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/largepenguin/largepenguin" + object.getVariant() + ".png");

    }

    @Override
    public ResourceLocation getAnimationResource(LargePenguinEntity object)
    {
        if (object.isBaby() || object.isPesto()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.largepenguinbaby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.largepenguin.json");

    }
}
