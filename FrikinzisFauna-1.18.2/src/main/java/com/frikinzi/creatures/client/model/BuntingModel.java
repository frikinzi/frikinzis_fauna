package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BuntingEntity;
import com.frikinzi.creatures.entity.SkuaEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class BuntingModel extends AnimatedTickingGeoModel<BuntingEntity> {

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
            return new ResourceLocation(Creatures.MODID, "textures/entity/bunting/bunting" + object.getVariant() + object.getGenderString() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/bunting/bunting" + object.getVariant() + object.getGenderString() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BuntingEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.bunting.json");
    }

}
