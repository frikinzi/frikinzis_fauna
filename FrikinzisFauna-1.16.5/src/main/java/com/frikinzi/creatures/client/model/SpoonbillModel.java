package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.DottybackEntity;
import com.frikinzi.creatures.entity.SpoonbillEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpoonbillModel extends AnimatedGeoModel<SpoonbillEntity> {
    @Override
    public ResourceLocation getModelLocation(SpoonbillEntity object) {
<<<<<<< Updated upstream
=======
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/spoonbill/spoonbill_baby.geo.json");

        }
>>>>>>> Stashed changes
        return new ResourceLocation(Creatures.MODID, "geo/entity/spoonbill/spoonbill.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SpoonbillEntity object)
    {
<<<<<<< Updated upstream
=======
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/spoonbill/spoonbillbaby" + object.getVariant() + "sleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/spoonbill/spoonbillbaby" + object.getVariant() + ".png");

        }
>>>>>>> Stashed changes
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/spoonbill/spoonbill" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/spoonbill/spoonbill" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpoonbillEntity object)
    {
<<<<<<< Updated upstream
=======
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.spoonbillbaby.json");

        }
>>>>>>> Stashed changes
        return new ResourceLocation(Creatures.MODID, "animations/animation.spoonbill.json");
    }
}
