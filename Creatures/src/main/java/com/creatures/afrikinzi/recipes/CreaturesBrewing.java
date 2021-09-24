package com.creatures.afrikinzi.recipes;

import com.creatures.afrikinzi.init.ItemInit;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.PotionHelper;

public class CreaturesBrewing {
    public static void init() {
        PotionHelper.addMix(PotionTypes.AWKWARD, ItemInit.GUPPY_TAIL, PotionTypes.WATER_BREATHING);
        PotionHelper.addMix(PotionTypes.AWKWARD, ItemInit.RAW_AROWANA, PotionTypes.LONG_WATER_BREATHING);
        PotionHelper.addMix(PotionTypes.AWKWARD, ItemInit.RAW_GOURAMI, PotionTypes.SWIFTNESS);
        PotionHelper.addMix(PotionTypes.AWKWARD, ItemInit.BIRD_OF_PREY_FEATHER, PotionTypes.LONG_STRENGTH);
    }
}
