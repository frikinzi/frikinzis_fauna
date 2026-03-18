package com.frikinzi.creatures.player;

import com.frikinzi.creatures.entity.Region;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;

public class SpeciesEntry {
    public final String entityKey;          // e.g. "lovebird"
    public final int totalVariants;         // total number of variants
    public final Map<Integer, Component> speciesNames;
    public final Map<Integer, String> scientificNames;
    public final RegistryObject<? extends EntityType<?>> entityType;
    public final RegistryObject<ForgeSpawnEggItem> spawnEgg;
    public final Component displayName;
    public final Map<Integer, List<Region>> regions;

    public SpeciesEntry(
            String entityKey,
            int totalVariants,
            Map<Integer, Component> speciesNames,
            Map<Integer, String> scientificNames,
            RegistryObject<? extends EntityType<?>> entityType,
            RegistryObject<ForgeSpawnEggItem> spawnEgg, Component displayName) {
        this.entityKey = entityKey;
        this.totalVariants = totalVariants;
        this.speciesNames = speciesNames;
        this.scientificNames = scientificNames;
        this.entityType = entityType;
        this.spawnEgg = spawnEgg;
        this.displayName=displayName;
        this.regions=Map.of();
    }

    public SpeciesEntry(
            String entityKey,
            int totalVariants,
            Map<Integer, Component> speciesNames,
            Map<Integer, String> scientificNames,
            RegistryObject<? extends EntityType<?>> entityType,
            RegistryObject<ForgeSpawnEggItem> spawnEgg, Component displayName, Map<Integer, List<Region>> regions) {
        this.entityKey = entityKey;
        this.totalVariants = totalVariants;
        this.speciesNames = speciesNames;
        this.scientificNames = scientificNames;
        this.entityType = entityType;
        this.spawnEgg = spawnEgg;
        this.displayName=displayName;
        this.regions = regions;
    }

    public ItemStack getSpawnEgg() {
        return new ItemStack(spawnEgg.get());
    }

    public String getSpeciesName(int variant) {
        Component c = speciesNames.get(variant);
        return c != null ? c.getString() : displayName.getString();
    }

    public String getScientificName(int variant) {
        return scientificNames.getOrDefault(variant, "");
    }
}