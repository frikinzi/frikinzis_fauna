package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.CapercaillieEntity;
import com.frikinzi.creatures.entity.PheasantEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class PheasantModel extends AnimatedTickingGeoModel<PheasantEntity> {

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
            return new ResourceLocation(Creatures.MODID, "textures/entity/pheasant/pheasant" + object.getVariant() + object.getGenderString() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/pheasant/pheasant" + object.getVariant() + object.getGenderString() + ".png");
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
