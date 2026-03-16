package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LapwingEntity;
import com.frikinzi.creatures.entity.StiltEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class StiltModel extends GeoModel<StiltEntity> {
    @Override
    public ResourceLocation getModelResource(StiltEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/avocet/avocetbaby.geo.json");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/stilt/stiltfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/stilt/stilt.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StiltEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/avocet/avocet_1_baby_sleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/avocet/avocet_1_baby.png");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/stilt/stilt" + object.getVariant() + object.getGenderName() + "fly.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/stilt/stilt" + object.getVariant() + object.getGenderName() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/stilt/stilt" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(StiltEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.babylapwing.json");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.stiltfly.json");

        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.stilt.json");
    }
}
