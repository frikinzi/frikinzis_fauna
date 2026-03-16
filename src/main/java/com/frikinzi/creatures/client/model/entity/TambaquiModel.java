package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GoldfishEntity;
import com.frikinzi.creatures.entity.TambaquiEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TambaquiModel extends GeoModel<TambaquiEntity> {
    @Override
    public ResourceLocation getModelResource(TambaquiEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/tambaqui/tambaquifry.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/tambaqui/tambaqui.geo.json");    }

    @Override
    public ResourceLocation getTextureResource(TambaquiEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/tambaqui/tambaquifry.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/tambaqui/tambaqui" + object.getVariant() + ".png");    }

    @Override
    public ResourceLocation getAnimationResource(TambaquiEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.tambaqui.json");
    }
}
