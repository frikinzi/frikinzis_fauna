package com.creatures.afrikinzi.entity.roller;

import com.creatures.afrikinzi.entity.conure.EntityConure;
import com.creatures.afrikinzi.entity.raven.EntityRaven;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelRoller extends AnimatedGeoModel<EntityRoller> {
    @Override
    public ResourceLocation getModelLocation(EntityRoller object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/roller/rollerfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/roller/roller.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(EntityRoller object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/roller/roller" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/roller/roller" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityRoller object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.roller.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.roller.json");
    }
}
