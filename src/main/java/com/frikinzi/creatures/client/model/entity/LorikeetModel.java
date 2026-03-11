package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LorikeetEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LorikeetModel extends GeoModel<LorikeetEntity> {
    @Override
    public ResourceLocation getModelResource(LorikeetEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/baby_parrot/parrotchick.geo.json");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/lorikeet/lorikeetfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/lorikeet/lorikeet.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LorikeetEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + "_baby_sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + "_baby.png");

        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + "fly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(LorikeetEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.parrotbaby.json");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.lorikeet.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.lorikeet.json");
    }
}
