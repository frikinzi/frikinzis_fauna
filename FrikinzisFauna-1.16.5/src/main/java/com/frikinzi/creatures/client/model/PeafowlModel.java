package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.PeafowlEntity;
import com.frikinzi.creatures.entity.PygmyGooseEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PeafowlModel extends AnimatedGeoModel<PeafowlEntity> {
    @Override
    public ResourceLocation getModelLocation(PeafowlEntity object)
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
    public ResourceLocation getTextureLocation(PeafowlEntity object)
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
    public ResourceLocation getAnimationFileLocation(PeafowlEntity object)
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
