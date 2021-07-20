package com.creatures.afrikinzi.entity.kakapo;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import javax.annotation.Nullable;

public class ModelKakapo extends AnimatedGeoModel<EntityKakapo> {
    @Override
    public ResourceLocation getModelLocation(EntityKakapo object) {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/kakapo/kakapo_baby.geo.json");
        } else {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/kakapo/kakapo.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureLocation(EntityKakapo object) {
        if (object.isChild()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/kakapo/kakapo_babysleep.png");
            }
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/kakapo/kakapo_baby.png");
        } else {
            if (object.isSleeping()) {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/kakapo/kakaposleep.png");
            }
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/kakapo/kakapo.png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityKakapo object) {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.kakapo_baby.json");
        } else {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.kakapo.json");
        }
    }

}
