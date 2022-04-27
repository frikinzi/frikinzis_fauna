package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.DoveEntity;
import com.frikinzi.creatures.entity.FairywrenEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FairywrenModel extends AnimatedGeoModel<FairywrenEntity> {
    @Override
    public ResourceLocation getModelLocation(FairywrenEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/fairy_wren/fairywrenfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/fairy_wren/fairywren.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FairywrenEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/fairy_wren/wren" + object.getVariant() + object.getGenderName() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/fairy_wren/wren" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FairywrenEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.fairywren.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.fairywren.json");
    }
}
