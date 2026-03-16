package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.EdibleCrabEntity;
import com.frikinzi.creatures.entity.VampireCrabEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EdibleCrabModel extends GeoModel<EdibleCrabEntity> {
    @Override
    public ResourceLocation getModelResource(EdibleCrabEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/ediblecrab/ediblecrab.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EdibleCrabEntity object)
    {

        return new ResourceLocation(Creatures.MODID, "textures/entity/ediblecrab/ediblecrab" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(EdibleCrabEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.ediblecrab.json");
    }
}
