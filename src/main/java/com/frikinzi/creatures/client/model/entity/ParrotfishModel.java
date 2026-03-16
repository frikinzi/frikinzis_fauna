package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LookdownEntity;
import com.frikinzi.creatures.entity.ParrotfishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ParrotfishModel extends GeoModel<ParrotfishEntity> {
    @Override
    public ResourceLocation getModelResource(ParrotfishEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/parrotfish/parrotfishbaby.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/parrotfish/parrotfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ParrotfishEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/parrotfish/parrotfishbaby" + object.getVariant() + ".png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/parrotfish/parrotfish" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(ParrotfishEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.parrotfishbaby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.parrotfish.json");
    }
}
