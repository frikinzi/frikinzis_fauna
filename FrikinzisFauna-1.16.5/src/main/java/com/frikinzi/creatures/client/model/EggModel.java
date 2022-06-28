package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LovebirdEntity;
import com.frikinzi.creatures.entity.egg.CreaturesEggEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.Arrays;

public class EggModel extends AnimatedGeoModel<CreaturesEggEntity> {
    Integer[] ONE = {2,3,5,10,11,13,25,29,31,32,29};
    Integer[] TWO = {16,19,12};
    Integer[] THREE = {26,1,34,23,33,28};
    Integer[] FOUR = {9};
    Integer[] FIVE = {14,0,15,13,18};
    Integer[] SIX = {20,4,27};
    Integer[] SEVEN = {7,22,6,30,17,24};
    Integer[] EIGHT = {8,21};
    @Override
    public ResourceLocation getModelLocation(CreaturesEggEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/egg/egg.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CreaturesEggEntity object)
    {
        if (Arrays.asList(ONE).contains(object.getSpecies())) {
        return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg1.png"); }
        else if (Arrays.asList(TWO).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg2.png"); }
        else if (Arrays.asList(THREE).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg3.png"); }
        else if (Arrays.asList(FOUR).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg4.png"); }
        else if (Arrays.asList(FIVE).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg5.png"); }
        else if (Arrays.asList(SIX).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg6.png"); }
        else if (Arrays.asList(SEVEN).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg7.png"); }
        else if (Arrays.asList(EIGHT).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg8.png"); }
        else {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg1.png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CreaturesEggEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.egg.json");
    }
}
