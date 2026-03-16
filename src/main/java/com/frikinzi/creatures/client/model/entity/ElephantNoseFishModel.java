package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ElephantNoseFishEntity;
import com.frikinzi.creatures.entity.GuppyEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ElephantNoseFishModel extends GeoModel<ElephantNoseFishEntity> {
    @Override
    public ResourceLocation getModelResource(ElephantNoseFishEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/elephantnose/elephantnosefry.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/elephantnose/elephantnose.geo.json");    }

    @Override
    public ResourceLocation getTextureResource(ElephantNoseFishEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/elephantnose/elephantnosefry.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/elephantnose/elephantnose" + object.getVariant() + ".png");    }

    @Override
    public ResourceLocation getAnimationResource(ElephantNoseFishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.elephantnose.json");
    }
}
