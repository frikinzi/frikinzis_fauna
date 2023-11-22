package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LorikeetEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LorikeetModel extends AnimatedGeoModel<LorikeetEntity> {
    @Override
    public ResourceLocation getModelLocation(LorikeetEntity object)
    {
<<<<<<< Updated upstream
=======
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/baby_parrot/parrotchick.geo.json");
        }
>>>>>>> Stashed changes
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/lorikeet/lorikeetfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/lorikeet/lorikeet.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LorikeetEntity object)
    {
<<<<<<< Updated upstream
=======
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + "_baby_sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + "_baby.png");

        }
>>>>>>> Stashed changes
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + "fly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LorikeetEntity object)
    {
<<<<<<< Updated upstream
=======
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.parrotbaby.json");
        }
>>>>>>> Stashed changes
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.lorikeet.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.lorikeet.json");
    }
}
