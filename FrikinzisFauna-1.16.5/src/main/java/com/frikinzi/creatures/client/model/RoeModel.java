package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.egg.CreaturesEggEntity;
import com.frikinzi.creatures.entity.egg.CreaturesRoeEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.Arrays;

public class RoeModel extends AnimatedGeoModel<CreaturesRoeEntity> {
    // which roe texture should each entity use - number of each entity defined in ModEntityTypes
    Integer[] ONE = {3,6,11};
    Integer[] TWO = {13,15,16};
    Integer[] THREE = {2,4,5,9,10};
    Integer[] FOUR = {1,7,8,12};
    @Override
    public ResourceLocation getModelLocation(CreaturesRoeEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/roe/roe.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CreaturesRoeEntity object)
    {
        if (Arrays.asList(ONE).contains(object.getSpecies())) {
        return new ResourceLocation(Creatures.MODID, "textures/entity/roe/fishroe1.png"); }
        else if (Arrays.asList(TWO).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/roe/fishroe2.png"); }
        else if (Arrays.asList(THREE).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/roe/fishroe3.png"); }
        else if (Arrays.asList(FOUR).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/roe/fishroe4.png"); }
        else {
            return new ResourceLocation(Creatures.MODID, "textures/entity/roe/fishroe1.png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CreaturesRoeEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.egg.json");
    }
}
