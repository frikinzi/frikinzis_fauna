package com.creatures.afrikinzi.util.handlers;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler {
    public static final ResourceLocation FISH = LootTableList.register(new ResourceLocation(Reference.MOD_ID + ":loot_tables/fish.json"));
}
