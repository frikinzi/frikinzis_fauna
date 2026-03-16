package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.SeagullEntity;
import com.frikinzi.creatures.entity.StarlingEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SeagullModel extends GeoModel<SeagullEntity> {
    @Override
    public ResourceLocation getModelResource(SeagullEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/skua/skua_baby.geo.json");
        }
        if (object.isFlying() && !object.isInWater()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/seagull/seagullfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/seagull/seagull.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SeagullEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/seagull/seagullbaby.png");
        }
        if (object.isFlying() && !object.isInWater()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/seagull/seagull" + object.getVariant() + "fly.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/seagull/seagull" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/seagull/seagull" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(SeagullEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.skua_baby.json");
        }
        if (object.isFlying() && !object.isInWater()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.seagullfly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.seagull.json");
    }
}
