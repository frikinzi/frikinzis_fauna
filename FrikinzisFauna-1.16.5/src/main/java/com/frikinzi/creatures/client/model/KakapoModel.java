package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.KakapoEntity;
import com.frikinzi.creatures.entity.PikeEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KakapoModel extends AnimatedGeoModel<KakapoEntity> {
    @Override
    public ResourceLocation getModelLocation(KakapoEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/kakapo/kakapo_baby.geo.json");
        } else {
            return new ResourceLocation(Creatures.MODID, "geo/entity/kakapo/kakapo.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureLocation(KakapoEntity object) {
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
    public ResourceLocation getAnimationFileLocation(KakapoEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.kakapo_baby.json");
        } else {
            return new ResourceLocation(Creatures.MODID, "animations/animation.kakapo.json");
        }
    }
}
