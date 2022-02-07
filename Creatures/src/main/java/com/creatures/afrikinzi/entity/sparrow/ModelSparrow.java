package com.creatures.afrikinzi.entity.sparrow;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelSparrow extends AnimatedGeoModel<EntitySparrow> {
    @Override
    public ResourceLocation getModelLocation(EntitySparrow object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/sparrow/sparrowfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/sparrow/sparrow.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySparrow object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/sparrow/sparrow" + object.getVariant() + object.getGenderName() + "fly.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/sparrow/sparrow" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntitySparrow object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.sparrow.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.sparrow.json");
    }

}
