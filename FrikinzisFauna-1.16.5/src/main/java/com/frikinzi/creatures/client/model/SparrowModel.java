package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.RollerEntity;
import com.frikinzi.creatures.entity.SparrowEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SparrowModel extends AnimatedGeoModel<SparrowEntity> {
    @Override
    public ResourceLocation getModelLocation(SparrowEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/sparrow/sparrowfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/sparrow/sparrow.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(SparrowEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/sparrow/sparrow" + object.getVariant() + object.getGenderName() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/sparrow/sparrow" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SparrowEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.sparrow.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.sparrow.json");
    }
}
