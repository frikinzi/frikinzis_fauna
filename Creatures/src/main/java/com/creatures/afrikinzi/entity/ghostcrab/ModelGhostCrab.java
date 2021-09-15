package com.creatures.afrikinzi.entity.ghostcrab;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelGhostCrab extends AnimatedGeoModel<EntityGhostCrab> {
    @Override
    public ResourceLocation getModelLocation(EntityGhostCrab object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/ghostcrab/ghostcrab.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/ghostcrab/ghostcrab.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityGhostCrab object)
    {

            return new ResourceLocation(Reference.MOD_ID, "textures/entity/ghostcrab/ghostcrab" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityGhostCrab object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.ghostcrab.json");
    }
}
