package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.KakapoEntity;
import com.frikinzi.creatures.entity.SpoonbillEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class KakapoModel extends GeoModel<KakapoEntity> {
    @Override
    public ResourceLocation getModelResource(KakapoEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/kakapo/kakapo_baby.geo.json");
        } else {
            return new ResourceLocation(Creatures.MODID, "geo/entity/kakapo/kakapo.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureResource(KakapoEntity object) {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/kakapo/kakapo_babysleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/kakapo/kakapo_baby.png");
        } else {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/kakapo/kakaposleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/kakapo/kakapo.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(KakapoEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.kakapo_baby.json");
        } else {
            return new ResourceLocation(Creatures.MODID, "animations/animation.kakapo.json");
        }
    }
}
