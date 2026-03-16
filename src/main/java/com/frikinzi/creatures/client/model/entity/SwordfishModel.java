package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.SwordfishEntity;
import com.frikinzi.creatures.entity.TambaquiEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SwordfishModel extends GeoModel<SwordfishEntity> {
    @Override
    public ResourceLocation getModelResource(SwordfishEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/swordfish/swordfishbaby.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/swordfish/swordfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SwordfishEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/swordfish/billfishbaby" + object.getBabyVariant() + ".png");

        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/swordfish/billfish" + object.getTextureString() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(SwordfishEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.swordfishbaby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.swordfish.json");
    }
}
