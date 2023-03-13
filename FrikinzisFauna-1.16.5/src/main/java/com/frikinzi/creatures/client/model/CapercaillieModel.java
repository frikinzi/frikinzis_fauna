package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.CapercaillieEntity;
import com.frikinzi.creatures.entity.MonalEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CapercaillieModel extends AnimatedGeoModel<CapercaillieEntity> {
    @Override
    public ResourceLocation getModelLocation(CapercaillieEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/peafowl/peafowlchick.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/capercaillie/capercaillie.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CapercaillieEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/capercaillie/capercailliechick.png");
        } if (object.isSleeping()) {
        return new ResourceLocation(Creatures.MODID, "textures/entity/capercaillie/capercaillie" + object.getGenderName() + "sleep.png");
    }
        return new ResourceLocation(Creatures.MODID, "textures/entity/capercaillie/capercaillie" + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CapercaillieEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.peachick.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.capercaillie.json");
    }
}
