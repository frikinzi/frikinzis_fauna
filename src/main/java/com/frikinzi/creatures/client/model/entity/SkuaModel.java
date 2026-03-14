package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LapwingEntity;
import com.frikinzi.creatures.entity.SkuaEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkuaModel extends GeoModel<SkuaEntity> {
    @Override
    public ResourceLocation getModelResource(SkuaEntity object)
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
    public ResourceLocation getTextureResource(SkuaEntity object)
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
    public ResourceLocation getAnimationResource(SkuaEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.skua_baby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.skua.json");
    }
}
