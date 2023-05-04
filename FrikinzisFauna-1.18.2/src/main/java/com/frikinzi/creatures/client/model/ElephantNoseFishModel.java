package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ElephantNoseFishEntity;
import com.frikinzi.creatures.entity.TambaquiEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class ElephantNoseFishModel extends AnimatedTickingGeoModel<ElephantNoseFishEntity> {

    @Override
    public ResourceLocation getModelLocation(ElephantNoseFishEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/elephantnose/elephantnosefry.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/elephantnose/elephantnose.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ElephantNoseFishEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/elephantnose/elephantnosefry.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/elephantnose/elephantnose" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ElephantNoseFishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.elephantnose.json");
    }

}
