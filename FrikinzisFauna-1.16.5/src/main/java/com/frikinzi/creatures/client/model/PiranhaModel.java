package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GoldfishEntity;
import com.frikinzi.creatures.entity.PiranhaEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PiranhaModel extends AnimatedGeoModel<PiranhaEntity> {
    @Override
    public ResourceLocation getModelLocation(PiranhaEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/piranha/piranhafry.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/piranha/piranha.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PiranhaEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/piranha/piranhafry.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/piranha/piranha" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PiranhaEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.piranhafry.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.piranha.json");
    }
}
