package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.SparrowEntity;
import com.frikinzi.creatures.entity.WoodDuckEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class SparrowModel extends AnimatedTickingGeoModel<SparrowEntity> {

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
            return new ResourceLocation(Creatures.MODID, "textures/entity/sparrow/sparrow" + object.getVariant() + object.getGenderString() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/sparrow/sparrow" + object.getVariant() + object.getGenderString() + ".png");
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
