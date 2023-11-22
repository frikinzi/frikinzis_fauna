package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BarnOwlEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BarnOwlModel extends AnimatedGeoModel<BarnOwlEntity> {
    @Override
    public ResourceLocation getModelLocation(BarnOwlEntity object)
    {
<<<<<<< Updated upstream
=======
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/barnowl/barnowl_baby.geo.json");
        }
>>>>>>> Stashed changes
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/barnowl/barnowlfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/barnowl/barnowl.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BarnOwlEntity object)
    {
<<<<<<< Updated upstream
=======
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/barnowl/barnowl_baby_sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/barnowl/barnowl_baby.png");
        }
>>>>>>> Stashed changes
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/barnowl/barnowlfly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/barnowl/barnowlsleep.png");
        } else {
            return new ResourceLocation(Creatures.MODID, "textures/entity/barnowl/barnowl.png");}
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BarnOwlEntity object)
    {
<<<<<<< Updated upstream
        if (object.isFlying()) {
=======
        if (object.isFlying() && !object.isBaby()) {
>>>>>>> Stashed changes
            return new ResourceLocation(Creatures.MODID, "animations/animation.barnowlfly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.barnowl.json");
    }
}
