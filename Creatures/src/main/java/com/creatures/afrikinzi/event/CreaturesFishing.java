package com.creatures.afrikinzi.event;

import com.creatures.afrikinzi.config.CreaturesConfig;
import com.creatures.afrikinzi.init.ItemInit;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CreaturesFishing {

    @SubscribeEvent
    public static void onLootTablesLoaded(LootTableLoadEvent event)
    {
        if (event.getName().equals(LootTableList.GAMEPLAY_FISHING_FISH))
        {

            final LootPool main = event.getTable().getPool("main");

            if (main != null && CreaturesConfig.fishLoot == true)
            {
                main.addEntry(new LootEntryItem(ItemInit.RAW_GOURAMI, 10, 0, new LootFunction[0], new LootCondition[] {new RandomChance(0.1F)}, "creatures:gourami_loot"));
                main.addEntry(new LootEntryItem(ItemInit.CRAB_PINCERS, 10, 0, new LootFunction[0], new LootCondition[] {new RandomChance(0.1F)}, "creatures:crab_pincers_loot"));
                main.addEntry(new LootEntryItem(ItemInit.GUPPY_TAIL, 10, 0, new LootFunction[0], new LootCondition[] {new RandomChance(0.1F)}, "creatures:guppy_tail_loot"));
                main.addEntry(new LootEntryItem(ItemInit.RAW_PIKE, 10, 0, new LootFunction[0], new LootCondition[] {new RandomChance(0.1F)}, "creatures:pike_loot"));
                main.addEntry(new LootEntryItem(ItemInit.RAW_KOI, 8, 0, new LootFunction[0], new LootCondition[] {new RandomChance(0.1F)}, "creatures:koi_loot"));
                main.addEntry(new LootEntryItem(ItemInit.RAW_SHRIMP, 10, 0, new LootFunction[0], new LootCondition[] {new RandomChance(0.1F)}, "creatures:raw_shrimp"));
                main.addEntry(new LootEntryItem(ItemInit.RAW_AROWANA, 3, 0, new LootFunction[0], new LootCondition[] {new RandomChance(0.1F)}, "creatures:arowana_loot"));
                main.addEntry(new LootEntryItem(ItemInit.RAW_TROUT, 15, 0, new LootFunction[0], new LootCondition[] {new RandomChance(0.1F)}, "creatures:trout_loot"));
                main.addEntry(new LootEntryItem(ItemInit.RAW_RED_SNAPPER, 15, 0, new LootFunction[0], new LootCondition[] {new RandomChance(0.1F)}, "creatures:red_snapper_loot"));
            }

        }

    }
}
