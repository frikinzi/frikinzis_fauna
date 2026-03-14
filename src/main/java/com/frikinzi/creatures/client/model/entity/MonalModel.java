package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.MonalEntity;
import com.frikinzi.creatures.entity.WildDuckEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MonalModel extends GeoModel<MonalEntity> {
    @Override
    public ResourceLocation getModelResource(MonalEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/peafowl/peafowlchick.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/monal/monal.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MonalEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/monal/monalchick.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/monal/monal" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(MonalEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.peachick.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.monal.json");
    }
}
