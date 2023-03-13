package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.CapercaillieEntity;
import com.frikinzi.creatures.entity.PheasantEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PheasantModel extends AnimatedGeoModel<PheasantEntity> {
    @Override
    public ResourceLocation getModelLocation(PheasantEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/peafowl/peafowlchick.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/pheasant/pheasant.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PheasantEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/pheasant/pheasantchick.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/pheasant/pheasant" + object.getVariant() + object.getGenderName() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/pheasant/pheasant" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PheasantEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.peachick.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.pheasant.json");
    }
}
