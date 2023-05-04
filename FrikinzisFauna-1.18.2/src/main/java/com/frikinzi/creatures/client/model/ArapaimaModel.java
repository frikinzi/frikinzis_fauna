package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ArapaimaEntity;
import com.frikinzi.creatures.entity.TigerBarbEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class ArapaimaModel extends AnimatedTickingGeoModel<ArapaimaEntity> {

    @Override
    public ResourceLocation getModelLocation(ArapaimaEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/arapaima/arapaimafry.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/arapaima/arapaima.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ArapaimaEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/arapaima/arapaimafry.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/arapaima/arapaima" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ArapaimaEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.arapaimafry.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.arapaima.json");
    }

}
