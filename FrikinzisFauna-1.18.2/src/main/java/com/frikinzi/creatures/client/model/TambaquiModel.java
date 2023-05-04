package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ArapaimaEntity;
import com.frikinzi.creatures.entity.TambaquiEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class TambaquiModel extends AnimatedTickingGeoModel<TambaquiEntity> {

    @Override
    public ResourceLocation getModelLocation(TambaquiEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/tambaqui/tambaquifry.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/tambaqui/tambaqui.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TambaquiEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/tambaqui/tambaquifry.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/tambaqui/tambaqui" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TambaquiEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.tambaqui.json");
    }

}
