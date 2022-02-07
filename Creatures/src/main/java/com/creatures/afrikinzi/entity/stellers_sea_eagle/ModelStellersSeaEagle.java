package com.creatures.afrikinzi.entity.stellers_sea_eagle;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import javax.annotation.Nullable;

public class ModelStellersSeaEagle extends AnimatedGeoModel<EntityStellersSeaEagle> {
    @Override
    public ResourceLocation getModelLocation(EntityStellersSeaEagle object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/baby_eagle/babyraptor.geo.json");
        }
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/stellers_sea_eagle/stellers_sea_eaglefly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/stellers_sea_eagle/stellers_sea_eagle.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityStellersSeaEagle object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/baby_eagle/stellersseaeagleb.png");
        }
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/stellers_sea_eagle/stellers_sea_eaglefly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/stellers_sea_eagle/stellers_sea_eaglesleep.png");
        } else {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/stellers_sea_eagle/stellers_sea_eagle.png");}
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityStellersSeaEagle object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.babyraptor.json");
        }
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.stellers_sea_eaglefly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.stellers_sea_eagle.json");
    }

}
