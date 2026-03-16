package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ArapaimaEntity;
import com.frikinzi.creatures.entity.SawfishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SawfishModel extends GeoModel<SawfishEntity> {
    @Override
    public ResourceLocation getModelResource(SawfishEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/sawfish/sawfishbaby.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/sawfish/sawfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SawfishEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/sawfish/sawfishbaby" + object.getVariant() + ".png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/sawfish/sawfish" + object.getVariant() + object.getGenderString() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(SawfishEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.sawfishbaby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.sawfish.json");
    }
}
