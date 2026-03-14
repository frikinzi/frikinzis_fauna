package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.CapercaillieEntity;
import com.frikinzi.creatures.entity.MonalEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CapercaillieModel extends GeoModel<CapercaillieEntity> {
    @Override
    public ResourceLocation getModelResource(CapercaillieEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/peafowl/peafowlchick.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/capercaillie/capercaillie.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CapercaillieEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/capercaillie/capercailliechick.png");
        } if (object.isSleeping()) {
        return new ResourceLocation(Creatures.MODID, "textures/entity/capercaillie/capercaillie" + object.getGenderName() + "sleep.png");
    }
        return new ResourceLocation(Creatures.MODID, "textures/entity/capercaillie/capercaillie" + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(CapercaillieEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.peachick.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.capercaillie.json");
    }
}
