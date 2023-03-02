package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BuntingEntity;
import com.frikinzi.creatures.entity.SparrowEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BuntingModel extends AnimatedGeoModel<BuntingEntity> {
    @Override
    public ResourceLocation getModelLocation(BuntingEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/bunting/buntingfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/bunting/bunting.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(BuntingEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/bunting/bunting" + object.getVariant() + object.getGenderName() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/bunting/bunting" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BuntingEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.bunting.json");
    }
}
