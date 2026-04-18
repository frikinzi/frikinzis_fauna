package com.frikinzi.creatures.player;

import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesRecipes;
import com.frikinzi.creatures.registry.ModEventSubscriber;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class BaitedRodRecipe extends ShapelessRecipe {

    public BaitedRodRecipe(ResourceLocation id, String group, CraftingBookCategory category,
                           ItemStack result, NonNullList<Ingredient> ingredients) {
        super(id, group, category, result, ingredients);
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess access) {
        ItemStack result = super.assemble(container, access);
        int existingCount = 0;
        String existingType = "";
        String newBaitType = "";

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (isBaitItem(stack)) {
                newBaitType = stack.is(CreaturesItems.FISH_FOOD.get()) ? "fish_food" : "algae_wafer";
            }
            if (stack.is(Items.FISHING_ROD) && ModEventSubscriber.FFGuideInteractEvent.hasBait(stack)) {
                existingCount = ModEventSubscriber.FFGuideInteractEvent.getBaitCount(stack);
                existingType = ModEventSubscriber.FFGuideInteractEvent.getBaitType(stack);
            }
        }

        String finalType = newBaitType.isEmpty() ? existingType : newBaitType;
        int finalCount = Math.min(existingCount + 1, 16); // existing + exactly 1 new bait

        ModEventSubscriber.FFGuideInteractEvent.setBaitCount(result, finalCount);
        ModEventSubscriber.FFGuideInteractEvent.setBaitType(result, finalType);
        return result;
    }

    private boolean isBaitItem(ItemStack stack) {
        return stack.is(CreaturesItems.FISH_FOOD.get()) 
            || stack.is(CreaturesItems.ALGAE_WAFER.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CreaturesRecipes.BAITED_ROD_SERIALIZER.get();
    }

    public static class Serializer implements RecipeSerializer<BaitedRodRecipe> {
        @Override
        public BaitedRodRecipe fromJson(ResourceLocation id, JsonObject json) {
            ShapelessRecipe base = RecipeSerializer.SHAPELESS_RECIPE.fromJson(id, json);
            return new BaitedRodRecipe(id, base.getGroup(), 
                    base.category(), base.getResultItem(RegistryAccess.EMPTY),
                    base.getIngredients());
        }

        @Override
        public BaitedRodRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            ShapelessRecipe base = RecipeSerializer.SHAPELESS_RECIPE.fromNetwork(id, buf);
            return new BaitedRodRecipe(id, base.getGroup(),
                    base.category(), base.getResultItem(RegistryAccess.EMPTY),
                    base.getIngredients());
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, BaitedRodRecipe recipe) {
            RecipeSerializer.SHAPELESS_RECIPE.toNetwork(buf, recipe);
        }
    }
}