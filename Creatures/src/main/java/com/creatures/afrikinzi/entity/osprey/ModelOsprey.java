package com.creatures.afrikinzi.entity.osprey;

import com.creatures.afrikinzi.entity.stellers_sea_eagle.EntityStellersSeaEagle;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelOsprey extends AnimatedGeoModel<EntityOsprey> {
    @Override
    public ResourceLocation getModelLocation(EntityOsprey object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/baby_eagle/babyraptor.geo.json");
        }else {
            if (object.isFlying()) {
                return new ResourceLocation(Reference.MOD_ID, "geo/entity/osprey/ospreyfly.geo.json");
            }
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/osprey/osprey.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureLocation(EntityOsprey object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/baby_eagle/ospreyb.png");
        } else {
            if (object.isFlying()) {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/osprey/ospreyfly.png");
            } else if (object.isSleeping()) {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/osprey/ospreysleep.png");
            } else {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/osprey/osprey.png");}
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityOsprey object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.babyraptor.json");
        } else {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.osprey.json"); }
    }

}
