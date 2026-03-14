package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.PeafowlEntity;
import com.frikinzi.creatures.entity.PygmyGooseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PeafowlModel extends GeoModel<PeafowlEntity> {
    @Override
    public ResourceLocation getModelResource(PeafowlEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/peafowl/peafowlchick.geo.json");
        }
        if (object.getGender() == 0) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/peafowl/peafowlf.geo.json"); }
        else {
            return new ResourceLocation(Creatures.MODID, "geo/entity/peafowl/peafowlm.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureResource(PeafowlEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/peafowl/peafowlchick" + object.getVariant() + ".png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/peafowl/peafowl" + object.getVariant() + object.getGenderName() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/peafowl/peafowl" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(PeafowlEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.peachick.json");
        }
        if (object.getGender() == 1) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.peafowlm.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.peafowlf.json");
    }
}
