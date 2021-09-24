package com.creatures.afrikinzi.event;

import com.creatures.afrikinzi.init.ItemInit;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

import java.util.Random;

public class CreaturesFarmerTrade implements EntityVillager.ITradeList {
    @Override
    public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
    {
        recipeList.add(new MerchantRecipe(new ItemStack(ItemInit.DUCK_FEATHER,5),new ItemStack(Items.EMERALD,1)));
        recipeList.add(new MerchantRecipe(new ItemStack(ItemInit.RAW_AROWANA,1),new ItemStack(Items.EMERALD,1)));
    }
}
