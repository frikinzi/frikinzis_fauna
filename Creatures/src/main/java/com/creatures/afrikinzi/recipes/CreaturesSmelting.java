package com.creatures.afrikinzi.recipes;

import com.creatures.afrikinzi.init.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CreaturesSmelting {

        public static void init()
        {
            GameRegistry.addSmelting(new ItemStack(ItemInit.RAW_KOI), new ItemStack(ItemInit.ROASTED_KOI), 0.25F);
            GameRegistry.addSmelting(new ItemStack(ItemInit.RAW_SHRIMP), new ItemStack(ItemInit.COOKED_SHRIMP), 0.25F);
            GameRegistry.addSmelting(new ItemStack(ItemInit.RAW_AROWANA), new ItemStack(ItemInit.COOKED_AROWANA), 0.25F);
            GameRegistry.addSmelting(new ItemStack(ItemInit.RAW_PIKE), new ItemStack(ItemInit.COOKED_PIKE), 0.25F);
            GameRegistry.addSmelting(new ItemStack(ItemInit.RAW_TROUT), new ItemStack(ItemInit.COOKED_TROUT), 0.25F);
            GameRegistry.addSmelting(new ItemStack(ItemInit.RAW_RED_SNAPPER), new ItemStack(ItemInit.COOKED_RED_SNAPPER), 0.25F);
            GameRegistry.addSmelting(new ItemStack(ItemInit.RAW_LARGE_WILD_BIRD_MEAT), new ItemStack(ItemInit.ROASTED_LARGE_WILD_BIRD_MEAT), 0.25F);
            GameRegistry.addSmelting(new ItemStack(ItemInit.RAW_SMALL_WILD_BIRD_MEAT), new ItemStack(ItemInit.ROASTED_SMALL_WILD_BIRD_MEAT), 0.25F);
        }

    }
