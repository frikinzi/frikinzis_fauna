package com.creatures.afrikinzi.entity.fiddler_crab;

import com.creatures.afrikinzi.entity.ghostcrab.EntityGhostCrab;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelFiddlerCrab extends AnimatedGeoModel<EntityFiddlerCrab> {
    @Override
    public ResourceLocation getModelLocation(EntityFiddlerCrab object)
    {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/fiddlercrab/fiddlercrab.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityFiddlerCrab object)
    {

            return new ResourceLocation(Reference.MOD_ID, "textures/entity/fiddlercrab/fiddlercrab" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityFiddlerCrab object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.fiddlercrab.json");
    }
}
