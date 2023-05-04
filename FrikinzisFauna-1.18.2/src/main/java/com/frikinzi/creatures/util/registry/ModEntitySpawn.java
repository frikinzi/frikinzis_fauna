package com.frikinzi.creatures.util.registry;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.config.CreaturesConfig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Creatures.MODID)
public final class ModEntitySpawn {

    public static void preInit() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, false, BiomeLoadingEvent.class, ModEntitySpawn::onBiomeLoad);
    }

    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        if (event.getName() == null)
            return;
        List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(MobCategory.CREATURE);
        List<MobSpawnSettings.SpawnerData> water = event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT);
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
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.KAKAPO.get(), CreaturesConfig.kakapo_spawn_weight.get(), CreaturesConfig.kakapo_min_group.get(), CreaturesConfig.kakapo_max_group.get()));
            }
        } if (CreaturesConfig.raven_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.RAVEN.get(), CreaturesConfig.raven_spawn_weight.get(), CreaturesConfig.raven_min_group.get(), CreaturesConfig.raven_max_group.get()));
            }
        } if (CreaturesConfig.dove_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) || types.contains(BiomeDictionary.Type.PLAINS) || types.contains(BiomeDictionary.Type.JUNGLE) || types.contains(BiomeDictionary.Type.MESA) || types.contains(BiomeDictionary.Type.MOUNTAIN) || types.contains(BiomeDictionary.Type.SWAMP)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.DOVE.get(), CreaturesConfig.dove_spawn_weight.get(), CreaturesConfig.dove_min_group.get(), CreaturesConfig.dove_max_group.get()));
            }
        } if (CreaturesConfig.red_kite_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.RED_KITE.get(), CreaturesConfig.red_kite_spawn_weight.get(), CreaturesConfig.red_kite_min_group.get(), CreaturesConfig.red_kite_max_group.get()));
            }
        } if (CreaturesConfig.golden_eagle_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) || types.contains(BiomeDictionary.Type.PLAINS) || types.contains(BiomeDictionary.Type.SAVANNA)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.GOLDEN_EAGLE.get(), CreaturesConfig.golden_eagle_spawn_weight.get(), CreaturesConfig.golden_eagle_min_group.get(), CreaturesConfig.golden_eagle_max_group.get()));
            }
        } if (CreaturesConfig.gyrfalcon_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.SNOWY)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.GYRFALCON.get(), CreaturesConfig.gyrfalcon_spawn_weight.get(), CreaturesConfig.gyrfalcon_min_group.get(), CreaturesConfig.gyrfalcon_max_group.get()));
            }
        } if (CreaturesConfig.lorikeet_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.LORIKEET.get(), CreaturesConfig.lorikeet_spawn_weight.get(), CreaturesConfig.lorikeet_min_group.get(), CreaturesConfig.lorikeet_max_group.get()));
            }
        } if (CreaturesConfig.conure_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST)&& !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.CONURE.get(), CreaturesConfig.conure_spawn_weight.get(), CreaturesConfig.conure_min_group.get(), CreaturesConfig.conure_max_group.get()));
            }
        } if (CreaturesConfig.fairywren_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST)&& !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.FAIRYWREN.get(), CreaturesConfig.fairywren_spawn_weight.get(), CreaturesConfig.fairywren_min_group.get(), CreaturesConfig.fairywren_max_group.get()));
            }
        }
        if (CreaturesConfig.ghost_crab_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.BEACH) && !types.contains(BiomeDictionary.Type.COLD)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.GHOST_CRAB.get(), CreaturesConfig.ghost_crab_spawn_weight.get(), CreaturesConfig.ghost_crab_min_group.get(), CreaturesConfig.ghost_crab_max_group.get()));
            }
        }
        if (CreaturesConfig.pygmy_falcon_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.DRY) && !types.contains(BiomeDictionary.Type.COLD)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.PYGMY_FALCON.get(), CreaturesConfig.pygmy_falcon_spawn_weight.get(), CreaturesConfig.pygmy_falcon_min_group.get(), CreaturesConfig.pygmy_falcon_max_group.get()));
            }
        }
        if (CreaturesConfig.barn_owl_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.BARN_OWL.get(), CreaturesConfig.fairywren_spawn_weight.get(), CreaturesConfig.fairywren_min_group.get(), CreaturesConfig.fairywren_max_group.get()));
            }
        }
        if (CreaturesConfig.wild_duck_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.RIVER) && !types.contains(BiomeDictionary.Type.COLD)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.WILD_DUCK.get(), CreaturesConfig.wild_duck_spawn_weight.get(), CreaturesConfig.wild_duck_min_group.get(), CreaturesConfig.wild_duck_max_group.get()));
            }
        }
        if (CreaturesConfig.wild_duck_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.RIVER) && !types.contains(BiomeDictionary.Type.COLD) || types.contains(BiomeDictionary.Type.SWAMP)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.WILD_DUCK.get(), CreaturesConfig.wild_duck_spawn_weight.get(), CreaturesConfig.wild_duck_min_group.get(), CreaturesConfig.wild_duck_max_group.get()));
            }
        }
        if (CreaturesConfig.chickadee_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.CHICKADEE.get(), CreaturesConfig.chickadee_spawn_weight.get(), CreaturesConfig.chickadee_min_group.get(), CreaturesConfig.chickadee_max_group.get()));
            }
        }
        if (CreaturesConfig.pygmy_goose_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.SWAMP)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.PYGMY_GOOSE.get(), CreaturesConfig.pygmy_goose_spawn_weight.get(), CreaturesConfig.pygmy_goose_min_group.get(), CreaturesConfig.pygmy_goose_max_group.get()));
            }
        }
        if (CreaturesConfig.swallow_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER) || types.contains(BiomeDictionary.Type.PLAINS)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.SWALLOW.get(), CreaturesConfig.swallow_spawn_weight.get(), CreaturesConfig.swallow_min_group.get(), CreaturesConfig.swallow_max_group.get()));
            }
        }
        if (CreaturesConfig.fiddler_crab_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.BEACH) && !types.contains(BiomeDictionary.Type.COLD)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.FIDDLER_CRAB.get(), CreaturesConfig.fiddler_crab_spawn_weight.get(), CreaturesConfig.fiddler_crab_min_group.get(), CreaturesConfig.fiddler_crab_max_group.get()));
            }
        }
        if (CreaturesConfig.ibis_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.SWAMP)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.IBIS.get(), CreaturesConfig.ibis_spawn_weight.get(), CreaturesConfig.ibis_min_group.get(), CreaturesConfig.ibis_max_group.get()));
            }
        }
        if (CreaturesConfig.wood_duck_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.SWAMP) || (types.contains(BiomeDictionary.Type.RIVER) && !types.contains(BiomeDictionary.Type.COLD))) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.WOOD_DUCK.get(), CreaturesConfig.wood_duck_spawn_weight.get(), CreaturesConfig.wood_duck_min_group.get(), CreaturesConfig.wood_duck_max_group.get()));
            }
        }
        if (CreaturesConfig.peafowl_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.PEAFOWL.get(), CreaturesConfig.peafowl_spawn_weight.get(), CreaturesConfig.peafowl_min_group.get(), CreaturesConfig.peafowl_max_group.get()));
            }
        }
        if (CreaturesConfig.sparrow_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER) || types.contains(BiomeDictionary.Type.DRY) || types.contains(BiomeDictionary.Type.JUNGLE)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.SPARROW.get(), CreaturesConfig.sparrow_spawn_weight.get(), CreaturesConfig.sparrow_min_group.get(), CreaturesConfig.sparrow_max_group.get()));
            }
        }
        if (CreaturesConfig.bushtit_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.BUSHTIT.get(), CreaturesConfig.bushtit_spawn_weight.get(), CreaturesConfig.bushtit_min_group.get(), CreaturesConfig.bushtit_max_group.get()));
            }
        }
        if (CreaturesConfig.laughingthrush_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.LAUGHINGTHRUSH.get(), CreaturesConfig.laughingthrush_spawn_weight.get(), CreaturesConfig.laughingthrush_min_group.get(), CreaturesConfig.laughingthrush_max_group.get()));
            }
        }
        if (CreaturesConfig.eagleowl_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.EAGLEOWL.get(), CreaturesConfig.eagleowl_spawn_weight.get(), CreaturesConfig.eagleowl_min_group.get(), CreaturesConfig.eagleowl_max_group.get()));
            }
        }
        if (CreaturesConfig.robin_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.ROBIN.get(), CreaturesConfig.robin_spawn_weight.get(), CreaturesConfig.robin_min_group.get(), CreaturesConfig.robin_max_group.get()));
            }
        }
        if (CreaturesConfig.magpie_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.MAGPIE.get(), CreaturesConfig.magpie_spawn_weight.get(), CreaturesConfig.magpie_min_group.get(), CreaturesConfig.magpie_max_group.get()));
            }
        }
        if (CreaturesConfig.goose_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.FOREST) || types.contains(BiomeDictionary.Type.RIVER) && types.contains(BiomeDictionary.Type.SWAMP)) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.GOOSE.get(), CreaturesConfig.goose_spawn_weight.get(), CreaturesConfig.goose_min_group.get(), CreaturesConfig.goose_max_group.get()));
            }
        }
        if (CreaturesConfig.osprey_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.BEACH) || types.contains(BiomeDictionary.Type.RIVER) && types.contains(BiomeDictionary.Type.SWAMP)) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.OSPREY.get(), CreaturesConfig.osprey_spawn_weight.get(), CreaturesConfig.osprey_min_group.get(), CreaturesConfig.osprey_max_group.get()));
            }
        }
        if (CreaturesConfig.kingfisher_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.BEACH) || types.contains(BiomeDictionary.Type.RIVER) && types.contains(BiomeDictionary.Type.SWAMP)) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.KINGFISHER.get(), CreaturesConfig.kingfisher_spawn_weight.get(), CreaturesConfig.kingfisher_min_group.get(), CreaturesConfig.kingfisher_max_group.get()));
            }
        }
        if (CreaturesConfig.pelican_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.BEACH)) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.PELICAN.get(), CreaturesConfig.pelican_spawn_weight.get(), CreaturesConfig.pelican_min_group.get(), CreaturesConfig.pelican_max_group.get()));
            }
        }
        if (CreaturesConfig.lapwing_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.PLAINS) || types.contains(BiomeDictionary.Type.SWAMP)) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.LAPWING.get(), CreaturesConfig.lapwing_spawn_weight.get(), CreaturesConfig.lapwing_min_group.get(), CreaturesConfig.lapwing_max_group.get()));
            }
        }
        if (CreaturesConfig.skua_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.SNOWY)) && !types.contains(BiomeDictionary.Type.END)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.SKUA.get(), CreaturesConfig.skua_spawn_weight.get(), CreaturesConfig.skua_min_group.get(), CreaturesConfig.skua_max_group.get()));
            }
        }
        if (CreaturesConfig.bunting_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.BUNTING.get(), CreaturesConfig.bunting_spawn_weight.get(), CreaturesConfig.bunting_min_group.get(), CreaturesConfig.bunting_max_group.get()));
            }
        }
        if (CreaturesConfig.monal_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.MOUNTAIN)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.MONAL.get(), CreaturesConfig.monal_spawn_weight.get(), CreaturesConfig.monal_min_group.get(), CreaturesConfig.monal_max_group.get()));
            }
        }
        if (CreaturesConfig.tanager_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.JUNGLE)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.TANAGER.get(), CreaturesConfig.tanager_spawn_weight.get(), CreaturesConfig.tanager_min_group.get(), CreaturesConfig.tanager_max_group.get()));
            }
        }
        if (CreaturesConfig.finch_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.SAVANNA)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.FINCH.get(), CreaturesConfig.finch_spawn_weight.get(), CreaturesConfig.finch_min_group.get(), CreaturesConfig.finch_max_group.get()));
            }
        }
        if (CreaturesConfig.capercaillie_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER) || types.contains(BiomeDictionary.Type.MOUNTAIN)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.CAPERCAILLIE.get(), CreaturesConfig.capercaillie_spawn_weight.get(), CreaturesConfig.capercaillie_min_group.get(), CreaturesConfig.capercaillie_max_group.get()));
            }
        }
        if (CreaturesConfig.pheasant_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.FOREST) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER) || types.contains(BiomeDictionary.Type.MOUNTAIN)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.PHEASANT.get(), CreaturesConfig.pheasant_spawn_weight.get(), CreaturesConfig.pheasant_min_group.get(), CreaturesConfig.pheasant_max_group.get()));
            }
        }
        if (CreaturesConfig.tarantula_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.DRY) && !types.contains(BiomeDictionary.Type.COLD) && !types.contains(BiomeDictionary.Type.NETHER) || types.contains(BiomeDictionary.Type.MOUNTAIN) || types.contains(BiomeDictionary.Type.JUNGLE)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.PHEASANT.get(), CreaturesConfig.pheasant_spawn_weight.get(), CreaturesConfig.pheasant_min_group.get(), CreaturesConfig.pheasant_max_group.get()));
            }
        }
        if (CreaturesConfig.vampirecrab_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.RIVER) && !types.contains(BiomeDictionary.Type.COLD) || types.contains(BiomeDictionary.Type.SWAMP)) {
                base.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.VAMPIRE_CRAB.get(), CreaturesConfig.vampirecrab_spawn_weight.get(), CreaturesConfig.vampirecrab_min_group.get(), CreaturesConfig.vampirecrab_max_group.get()));
            }
        }


        //fish
        if (CreaturesConfig.koi_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.RIVER) || types.contains(BiomeDictionary.Type.SWAMP)) && !types.contains(BiomeDictionary.Type.COLD)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.KOI.get(), CreaturesConfig.koi_spawn_weight.get(), CreaturesConfig.koi_min_group.get(), CreaturesConfig.koi_max_group.get()));
            }
        }
        if (CreaturesConfig.dottyback_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.OCEAN) && types.contains(BiomeDictionary.Type.HOT)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.DOTTYBACK.get(), CreaturesConfig.dottyback_spawn_weight.get(), CreaturesConfig.dottyback_min_group.get(), CreaturesConfig.dottyback_max_group.get()));
            }
        }
        if (CreaturesConfig.pike_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.RIVER) && !types.contains(BiomeDictionary.Type.COLD)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.PIKE.get(), CreaturesConfig.pike_spawn_weight.get(), CreaturesConfig.pike_min_group.get(), CreaturesConfig.pike_max_group.get()));
            }
        }
        if (CreaturesConfig.guppy_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.SWAMP) || types.contains(BiomeDictionary.Type.RIVER)) && !types.contains(BiomeDictionary.Type.COLD)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.GUPPY.get(), CreaturesConfig.guppy_spawn_weight.get(), CreaturesConfig.guppy_min_group.get(), CreaturesConfig.guppy_max_group.get()));
            }
        }
        if (CreaturesConfig.arowana_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.SWAMP) && !types.contains(BiomeDictionary.Type.COLD)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.AROWANA.get(), CreaturesConfig.arowana_spawn_weight.get(), CreaturesConfig.arowana_min_group.get(), CreaturesConfig.arowana_max_group.get()));
            }
        }
        if (CreaturesConfig.shrimp_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.RIVER) && !types.contains(BiomeDictionary.Type.COLD)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.SHRIMP.get(), CreaturesConfig.shrimp_spawn_weight.get(), CreaturesConfig.shrimp_min_group.get(), CreaturesConfig.shrimp_max_group.get()));
            }
        }
        if (CreaturesConfig.gourami_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.RIVER) || types.contains(BiomeDictionary.Type.SWAMP)) && !types.contains(BiomeDictionary.Type.COLD)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.GOURAMI.get(), CreaturesConfig.shrimp_spawn_weight.get(), CreaturesConfig.shrimp_min_group.get(), CreaturesConfig.shrimp_max_group.get()));
            }
        }
        if (CreaturesConfig.goldfish_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.RIVER) || types.contains(BiomeDictionary.Type.SWAMP)) && !types.contains(BiomeDictionary.Type.COLD)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.GOLDFISH.get(), CreaturesConfig.goldfish_spawn_weight.get(), CreaturesConfig.goldfish_min_group.get(), CreaturesConfig.goldfish_max_group.get()));
            }
        }
        if (CreaturesConfig.ranchu_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.RIVER) || types.contains(BiomeDictionary.Type.SWAMP)) && !types.contains(BiomeDictionary.Type.COLD)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.RANCHU.get(), CreaturesConfig.ranchu_spawn_weight.get(), CreaturesConfig.ranchu_min_group.get(), CreaturesConfig.ranchu_max_group.get()));
            }
        }
        if (CreaturesConfig.fire_goby_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.OCEAN) && types.contains(BiomeDictionary.Type.HOT)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.FIRE_GOBY.get(), CreaturesConfig.fire_goby_spawn_weight.get(), CreaturesConfig.fire_goby_min_group.get(), CreaturesConfig.fire_goby_max_group.get()));
            }
        }
        if (CreaturesConfig.blue_tang_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.OCEAN) && types.contains(BiomeDictionary.Type.HOT)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.BLUE_TANG.get(), CreaturesConfig.blue_tang_spawn_weight.get(), CreaturesConfig.blue_tang_min_group.get(), CreaturesConfig.blue_tang_max_group.get()));
            }
        }
        if (CreaturesConfig.flame_angelfish_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.OCEAN) && types.contains(BiomeDictionary.Type.HOT)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.FLAME_ANGELFISH.get(), CreaturesConfig.flame_angelfish_spawn_weight.get(), CreaturesConfig.flame_angelfish_min_group.get(), CreaturesConfig.flame_angelfish_max_group.get()));
            }
        }
        if (CreaturesConfig.trout_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.RIVER))) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.TROUT.get(), CreaturesConfig.trout_spawn_weight.get(), CreaturesConfig.trout_min_group.get(), CreaturesConfig.trout_max_group.get()));
            }
        }
        if (CreaturesConfig.red_snapper_spawns.get()) {
            if (types.contains(BiomeDictionary.Type.OCEAN)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.RED_SNAPPER.get(), CreaturesConfig.red_snapper_spawn_weight.get(), CreaturesConfig.red_snapper_min_group.get(), CreaturesConfig.red_snapper_max_group.get()));
            }
        }
        if (CreaturesConfig.tigerbarb_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.RIVER)) && !types.contains(BiomeDictionary.Type.COLD)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.TIGER_BARB.get(), CreaturesConfig.tigerbarb_spawn_weight.get(), CreaturesConfig.tigerbarb_min_group.get(), CreaturesConfig.tigerbarb_max_group.get()));
            }
        }
        if (CreaturesConfig.arapaima_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.RIVER)) && !types.contains(BiomeDictionary.Type.COLD)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.ARAPAIMA.get(), CreaturesConfig.arapaima_spawn_weight.get(), CreaturesConfig.arapaima_min_group.get(), CreaturesConfig.arapaima_max_group.get()));
            }
        }
        if (CreaturesConfig.piranha_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.RIVER)) && !types.contains(BiomeDictionary.Type.COLD)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.PIRANHA.get(), CreaturesConfig.piranha_spawn_weight.get(), CreaturesConfig.piranha_min_group.get(), CreaturesConfig.piranha_max_group.get()));
            }
        }
        if (CreaturesConfig.tambaqui_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.RIVER)) && !types.contains(BiomeDictionary.Type.COLD)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.TAMBAQUI.get(), CreaturesConfig.tambaqui_spawn_weight.get(), CreaturesConfig.tambaqui_min_group.get(), CreaturesConfig.tambaqui_max_group.get()));
            }
        }
        if (CreaturesConfig.elephant_nose_spawns.get()) {
            if ((types.contains(BiomeDictionary.Type.RIVER)) && !types.contains(BiomeDictionary.Type.COLD)) {
                water.add(new MobSpawnSettings.SpawnerData(CreaturesEntities.ELEPHANT_NOSE.get(), CreaturesConfig.elephant_nose_spawn_weight.get(), CreaturesConfig.elephant_nose_min_group.get(), CreaturesConfig.elephant_nose_max_group.get()));
            }
        }
    }
}
