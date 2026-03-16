package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ShrimpEntity;
import com.frikinzi.creatures.entity.StingrayEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class StingrayModel extends GeoModel<StingrayEntity> {
    @Override
    public ResourceLocation getModelResource(StingrayEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/stingray/stingraybaby.geo.json");

        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/stingray/stingray.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StingrayEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/stingray/stingraybaby" + object.getVariant() + ".png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/stingray/stingray_" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(StingrayEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.stingray.json");
    }
}
