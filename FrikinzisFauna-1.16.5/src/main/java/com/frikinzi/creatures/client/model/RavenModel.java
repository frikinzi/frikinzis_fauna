package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.DottybackEntity;
import com.frikinzi.creatures.entity.RavenEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RavenModel extends AnimatedGeoModel<RavenEntity> {
    @Override
    public ResourceLocation getModelLocation(RavenEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/raven/ravenfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/raven/raven.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RavenEntity object)
    {
        if (object.isFlying()) {
            if (object.getVariant() == 1) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/raven/ravenfly.png");
            } else {
                return new ResourceLocation(Creatures.MODID, "textures/entity/raven/ravenalbinofly.png");
            }
        } else if (object.isSleeping()) {
            if (object.getVariant() == 2) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/raven/ravenalbinosleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/raven/ravensleep.png");
        }
        if (object.getVariant() == 2) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/raven/ravenalbino.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/raven/raven.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RavenEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/raven.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.raven.json");
    }
}
