package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.CapercaillieEntity;
import com.frikinzi.creatures.entity.PheasantEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PheasantModel extends GeoModel<PheasantEntity> {
    @Override
    public ResourceLocation getModelResource(PheasantEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/peafowl/peafowlchick.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/pheasant/pheasant.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PheasantEntity object)
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
    public ResourceLocation getAnimationResource(PheasantEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.peachick.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.pheasant.json");
    }
}
