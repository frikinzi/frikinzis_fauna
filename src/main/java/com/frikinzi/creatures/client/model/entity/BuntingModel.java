package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BuntingEntity;
import com.frikinzi.creatures.entity.SparrowEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BuntingModel extends GeoModel<BuntingEntity> {
    @Override
    public ResourceLocation getModelResource(BuntingEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/bunting/buntingfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/bunting/bunting.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BuntingEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/bunting/bunting" + object.getVariant() + object.getGenderName() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/bunting/bunting" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(BuntingEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.bunting.json");
    }
}
