package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ElephantNoseFishEntity;
import com.frikinzi.creatures.entity.GuppyEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ElephantNoseFishModel extends AnimatedGeoModel<ElephantNoseFishEntity> {
    @Override
    public ResourceLocation getModelLocation(ElephantNoseFishEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/elephantnose/elephantnosefry.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/elephantnose/elephantnose.geo.json");    }

    @Override
    public ResourceLocation getTextureLocation(ElephantNoseFishEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/elephantnose/elephantnosefry.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/elephantnose/elephantnose" + object.getVariant() + ".png");    }

    @Override
    public ResourceLocation getAnimationFileLocation(ElephantNoseFishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.elephantnose.json");
    }
}
