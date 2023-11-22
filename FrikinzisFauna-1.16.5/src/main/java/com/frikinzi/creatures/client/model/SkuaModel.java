package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LapwingEntity;
import com.frikinzi.creatures.entity.SkuaEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SkuaModel extends AnimatedGeoModel<SkuaEntity> {
    @Override
    public ResourceLocation getModelLocation(SkuaEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/skua/skua_baby.geo.json");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/skua/skuafly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/skua/skua.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(SkuaEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/skua/skua" + object.getVariant() + "_baby_sleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/skua/skua" + object.getVariant() + "_baby.png");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/skua/skua" + object.getVariant() + "fly.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/skua/skua" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/skua/skua" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SkuaEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.skua_baby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.skua.json");
    }
}
