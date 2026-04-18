package com.frikinzi.creatures.registry;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.player.BaitedRodRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CreaturesRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Creatures.MODID);

    public static final RegistryObject<RecipeSerializer<BaitedRodRecipe>> BAITED_ROD_SERIALIZER =
            RECIPE_SERIALIZERS.register("baited_rod",
                    () -> new BaitedRodRecipe.Serializer());
}