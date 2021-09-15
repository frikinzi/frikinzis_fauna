package com.creatures.afrikinzi.entity.iberian_lynx;

import com.creatures.afrikinzi.entity.golden_eagle.EntityGoldenEagle;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelIberianLynx extends AnimatedGeoModel<EntityIberianLynx> {
    @Override
    public ResourceLocation getModelLocation(EntityIberianLynx object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/iberian_lynx/lynx_cub.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/iberian_lynx/lynx.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityIberianLynx object)
    {
        if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/iberian_lynx/lynxsleep.png");
        } else if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/iberian_lynx/lynxbaby.png");
        }
        else {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/iberian_lynx/lynx.png");}
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityIberianLynx object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.lynx.json");
    }
}
