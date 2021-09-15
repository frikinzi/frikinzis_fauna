package com.creatures.afrikinzi.entity.fairy_wren;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelFairyWren extends AnimatedGeoModel<EntityFairyWren> {
    @Override
    public ResourceLocation getModelLocation(EntityFairyWren object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/fairy_wren/fairywrenfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/fairy_wren/fairywren.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityFairyWren object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/fairy_wren/wren" + object.getVariant() + object.getGenderName() + "fly.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/fairy_wren/wren" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityFairyWren object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.fairywren.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.fairywren.json");
    }

}
