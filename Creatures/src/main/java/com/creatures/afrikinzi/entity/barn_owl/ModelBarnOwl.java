package com.creatures.afrikinzi.entity.barn_owl;

import com.creatures.afrikinzi.entity.red_kite.EntityRedKite;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelBarnOwl extends AnimatedGeoModel<EntityBarnOwl> {
    @Override
    public ResourceLocation getModelLocation(EntityBarnOwl object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/barnowl/barnowlfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/barnowl/barnowl.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBarnOwl object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/barnowl/barnowlfly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/barnowl/barnowlsleep.png");
        } else {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/barnowl/barnowl.png");}
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityBarnOwl object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.barnowlfly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.barnowl.json");
    }
}
