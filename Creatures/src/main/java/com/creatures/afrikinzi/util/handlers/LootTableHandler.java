package com.creatures.afrikinzi.util.handlers;

import com.creatures.afrikinzi.init.ItemInit;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LootTableHandler {
    public static final ResourceLocation FISH = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "fish"));
    public static final ResourceLocation KOI = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "koi"));
    public static final ResourceLocation PIKE = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "pike"));
    public static final ResourceLocation AROWANA = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "arowana"));
    public static final ResourceLocation GUPPY = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "guppy"));
    public static final ResourceLocation SHRIMP = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "shrimp"));
    public static final ResourceLocation GOURAMI = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "gourami"));
    public static final ResourceLocation DUCK = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "duck"));
    public static final ResourceLocation GENERIC_BIRD = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "genericbird"));
    public static final ResourceLocation RAVEN = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "raven"));
    public static final ResourceLocation CRAB = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "crab"));
    public static final ResourceLocation PARROT = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "creaturesparrot"));
    public static final ResourceLocation BIRD_OF_PREY = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "bird_of_prey"));
    public static final ResourceLocation BIRD_OF_PREY_SMALL = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "bird_of_prey_small"));

}
