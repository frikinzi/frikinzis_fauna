package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.MonalEntity;
import com.frikinzi.creatures.entity.WildDuckEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MonalModel extends AnimatedGeoModel<MonalEntity> {
    @Override
    public ResourceLocation getModelLocation(MonalEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/peafowl/peafowlchick.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/monal/monal.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MonalEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/monal/monalchick.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/monal/monal" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MonalEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.peachick.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.monal.json");
    }
}
