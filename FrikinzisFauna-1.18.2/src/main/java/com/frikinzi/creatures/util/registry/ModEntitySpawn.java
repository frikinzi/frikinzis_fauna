package com.frikinzi.creatures.util.registry;

import com.frikinzi.creatures.config.CreaturesConfig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;

public class ModEntitySpawn {
    public static void onBiomesLoad(BiomeLoadingEvent event) {
        List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(MobCategory.CREATURE);
        ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);
        if (CreaturesConfig.lovebird_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.SAVANNA)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.LOVEBIRD.get(), CreaturesConfig.lovebird_spawn_weight.get(), CreaturesConfig.lovebird_min_group.get(), CreaturesConfig.lovebird_max_group.get()));
            }
        } if (CreaturesConfig.spoonbill_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.SWAMP)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.SPOONBILL.get(), CreaturesConfig.spoonbill_spawn_weight.get(), CreaturesConfig.spoonbill_min_group.get(), CreaturesConfig.spoonbill_max_group.get()));
            }
        } if (CreaturesConfig.kakapo_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.KAKAPO.get(), CreaturesConfig.kakapo_spawn_weight.get(), CreaturesConfig.kakapo_min_group.get(), CreaturesConfig.kakapo_max_group.get()));
            }
        }
    }
}
