package com.frikinzi.creatures.config;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CreaturesConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> koi_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Boolean> koi_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> koi_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> koi_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> lovebird_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> lovebird_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> lovebird_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> lovebird_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> lovebird_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> lovebird_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> dottyback_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> dottyback_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> dottyback_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> dottyback_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> pike_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> pike_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> pike_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> pike_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> guppy_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> guppy_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> guppy_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> guppy_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> shrimp_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> shrimp_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> shrimp_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> shrimp_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> gourami_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> gourami_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> gourami_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> gourami_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> arowana_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> arowana_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> arowana_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> arowana_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> ghost_crab_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> ghost_crab_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> ghost_crab_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> ghost_crab_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> lorikeet_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> lorikeet_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> lorikeet_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> lorikeet_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> lorikeet_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> lorikeet_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> conure_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> conure_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> conure_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> conure_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> conure_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> conure_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> spoonbill_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> spoonbill_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> spoonbill_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> spoonbill_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> spoonbill_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> spoonbill_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> dove_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> dove_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> dove_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> dove_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> dove_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> dove_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> mandarin_duck_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> mandarin_duck_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> mandarin_duck_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> mandarin_duck_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> mandarin_duck_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> mandarin_duck_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> kakapo_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> kakapo_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> kakapo_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> kakapo_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> kakapo_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> kakapo_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> raven_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> raven_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> raven_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> raven_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> raven_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> raven_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> fairywren_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> fairywren_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> fairywren_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> fairywren_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> fairywren_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> fairywren_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> golden_eagle_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> golden_eagle_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> golden_eagle_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> golden_eagle_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> golden_eagle_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> golden_eagle_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> gyrfalcon_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> gyrfalcon_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> gyrfalcon_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> gyrfalcon_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> gyrfalcon_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> gyrfalcon_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> red_kite_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> red_kite_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> red_kite_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> red_kite_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> red_kite_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> red_kite_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> stellers_sea_eagle_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> stellers_sea_eagle_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> stellers_sea_eagle_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> stellers_sea_eagle_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> stellers_sea_eagle_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> stellers_sea_eagle_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> pygmy_falcon_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> pygmy_falcon_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> pygmy_falcon_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> pygmy_falcon_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> pygmy_falcon_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> pygmy_falcon_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> barn_owl_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> barn_owl_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> barn_owl_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> barn_owl_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> barn_owl_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> barn_owl_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> goldfish_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> goldfish_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> goldfish_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> goldfish_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> ranchu_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> ranchu_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> ranchu_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> ranchu_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> wild_duck_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> wild_duck_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> wild_duck_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> wild_duck_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> wild_duck_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> wild_duck_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> roller_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> roller_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> roller_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> roller_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> roller_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> roller_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> chickadee_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> chickadee_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> chickadee_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> chickadee_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> chickadee_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> chickadee_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> pygmy_goose_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> pygmy_goose_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> pygmy_goose_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> pygmy_goose_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> pygmy_goose_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> pygmy_goose_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> swallow_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> swallow_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> swallow_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> swallow_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> swallow_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> swallow_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> fire_goby_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> fire_goby_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> fire_goby_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> fire_goby_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> blue_tang_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> blue_tang_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> blue_tang_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> blue_tang_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> flame_angelfish_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> flame_angelfish_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> flame_angelfish_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> flame_angelfish_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> trout_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> trout_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> trout_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> trout_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> fiddler_crab_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> fiddler_crab_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> fiddler_crab_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> fiddler_crab_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> ibis_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> ibis_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> ibis_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> ibis_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> ibis_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> ibis_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> red_snapper_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> red_snapper_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> red_snapper_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> red_snapper_max_group;

    public static final ForgeConfigSpec.ConfigValue<Boolean> wood_duck_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> wood_duck_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> wood_duck_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> wood_duck_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> wood_duck_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> wood_duck_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> peafowl_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> peafowl_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> peafowl_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> peafowl_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> peafowl_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> peafowl_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> sparrow_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> sparrow_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> sparrow_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> sparrow_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> sparrow_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> sparrow_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> bushtit_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> bushtit_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> bushtit_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> bushtit_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> bushtit_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> bushtit_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> laughingthrush_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> laughingthrush_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> laughingthrush_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> laughingthrush_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> laughingthrush_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> laughingthrush_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> eagleowl_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> eagleowl_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> eagleowl_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> eagleowl_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> eagleowl_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> eagleowl_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> robin_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> robin_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> robin_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> robin_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> robin_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> robin_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> magpie_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> magpie_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> magpie_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> magpie_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> magpie_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> magpie_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> goose_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> goose_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> goose_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> goose_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> goose_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> goose_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> osprey_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> osprey_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> osprey_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> osprey_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> osprey_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> osprey_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> kingfisher_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> kingfisher_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> kingfisher_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> kingfisher_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> kingfisher_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> kingfisher_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> pelican_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> pelican_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> pelican_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> pelican_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> pelican_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> pelican_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> lapwing_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> lapwing_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> lapwing_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> lapwing_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> lapwing_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> lapwing_clutch_size;

    public static final ForgeConfigSpec.ConfigValue<Boolean> skua_spawns;
    public static final ForgeConfigSpec.ConfigValue<Integer> skua_spawn_weight;
    public static final ForgeConfigSpec.ConfigValue<Integer> skua_min_group;
    public static final ForgeConfigSpec.ConfigValue<Integer> skua_max_group;
    public static final ForgeConfigSpec.ConfigValue<Float> skua_hatch_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> skua_clutch_size;

    //gameplay
    public static final ForgeConfigSpec.ConfigValue<Boolean> breed_only_variants;
    public static final ForgeConfigSpec.ConfigValue<Boolean> biome_only_variants;
    public static final ForgeConfigSpec.ConfigValue<Boolean> following;
    public static final ForgeConfigSpec.ConfigValue<Boolean> teleporting;
    public static final ForgeConfigSpec.ConfigValue<Double> teleporting_distance;
    public static final ForgeConfigSpec.ConfigValue<Boolean> raptor_attacks;
    public static final ForgeConfigSpec.ConfigValue<Boolean> raptor_throws;
    public static final ForgeConfigSpec.ConfigValue<Integer> raven_albino_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> lovebird_mutation_chance;
    public static final ForgeConfigSpec.ConfigValue<Integer> lorikeet_mutation_chance;
    public static final ForgeConfigSpec.ConfigValue<Float> height_base_multiplier;
    public static final ForgeConfigSpec.ConfigValue<Float> height_standard_deviation;
    public static final ForgeConfigSpec.ConfigValue<Boolean> height_on;
    public static final ForgeConfigSpec.ConfigValue<Integer> base_egg_hatch_time;

    static {
        BUILDER.push("Koi");

        koi_spawns = BUILDER.comment("Enable/disable koi spawns").define("Koi Spawns", true);
        koi_spawn_weight = BUILDER.comment("Spawn weight for koi").define("Koi Spawn Weight", 10);
        koi_min_group = BUILDER.comment("Min group for koi").define("Koi Min Group", 1);
        koi_max_group = BUILDER.comment("Max group for koi").define("Koi Max Group", 2);

        BUILDER.pop();

        BUILDER.push("Lovebird");

        lovebird_spawns = BUILDER.comment("Enable/disable lovebird spawns").define("Lovebird Spawns", true);
        lovebird_spawn_weight = BUILDER.comment("Spawn weight for lovebirds").define("Lovebird Spawn Weight", 20);
        lovebird_min_group = BUILDER.comment("Min group for lovebirds").define("Lovebird Min Group", 3);
        lovebird_max_group = BUILDER.comment("Max group for lovebirds").define("Lovebird Max Group", 6);
        lovebird_hatch_chance = BUILDER.comment("Hatch chance for each lovebird egg").define("Lovebird Hatch Chance", 0.5F);
        lovebird_clutch_size = BUILDER.comment("Max egg clutch size for lovebirds").define("Lovebird Clutch Size", 4);

        BUILDER.pop();

        BUILDER.push("Dottyback");

        dottyback_spawns = BUILDER.comment("Enable/disable dottyback spawns").define("Dottyback Spawns", true);
        dottyback_spawn_weight = BUILDER.comment("Spawn weight for dottybacks").define("Dottyback Spawn Weight", 20);
        dottyback_min_group = BUILDER.comment("Min group for dottybacks").define("Dottyback Min Group", 2);
        dottyback_max_group = BUILDER.comment("Max group for dottybacks").define("Dottyback Max Group", 3);

        BUILDER.pop();

        BUILDER.push("Pike");

        pike_spawns = BUILDER.comment("Enable/disable pike spawns").define("Pike Spawns", true);
        pike_spawn_weight = BUILDER.comment("Spawn weight for pikes").define("Pike Spawn Weight", 8);
        pike_min_group = BUILDER.comment("Min group for pikes").define("Pike Min Group", 1);
        pike_max_group = BUILDER.comment("Max group for pikes").define("Pike Max Group", 1);

        BUILDER.pop();

        BUILDER.push("Guppy");

        guppy_spawns = BUILDER.comment("Enable/disable guppy spawns").define("Guppy Spawns", true);
        guppy_spawn_weight = BUILDER.comment("Spawn weight for guppies").define("Guppy Spawn Weight", 10);
        guppy_min_group = BUILDER.comment("Min group for guppies").define("Guppy Min Group", 3);
        guppy_max_group = BUILDER.comment("Max group for guppies").define("Guppy Max Group", 8);

        BUILDER.pop();

        BUILDER.push("Shrimp");

        shrimp_spawns = BUILDER.comment("Enable/disable shrimp spawns").define("Shrimp Spawns", true);
        shrimp_spawn_weight = BUILDER.comment("Spawn weight for shrimp").define("Shrimp Spawn Weight", 10);
        shrimp_min_group = BUILDER.comment("Min group for shrimp").define("Shrimp Min Group", 4);
        shrimp_max_group = BUILDER.comment("Max group for shrimp").define("Shrimp Max Group", 10);

        BUILDER.pop();

        BUILDER.push("Gourami");

        gourami_spawns = BUILDER.comment("Enable/disable gourami spawns").define("Gourami Spawns", true);
        gourami_spawn_weight = BUILDER.comment("Spawn weight for gourami").define("Gourami Spawn Weight", 10);
        gourami_min_group = BUILDER.comment("Min group for gourami").define("Gourami Min Group", 1);
        gourami_max_group = BUILDER.comment("Max group for gourami").define("Gourami Max Group", 1);

        BUILDER.pop();

        BUILDER.push("Arowana");

        arowana_spawns = BUILDER.comment("Enable/disable arowana spawns").define("Arowana Spawns", true);
        arowana_spawn_weight = BUILDER.comment("Spawn weight for arowana").define("Arowana Spawn Weight", 5);
        arowana_min_group = BUILDER.comment("Min group for arowana").define("Arowana Min Group", 1);
        arowana_max_group = BUILDER.comment("Max group for arowana").define("Arowana Max Group", 1);

        BUILDER.pop();

        BUILDER.push("Ghost Crab");

        ghost_crab_spawns = BUILDER.comment("Enable/disable ghost crab spawns").define("Ghost Crab Spawns", true);
        ghost_crab_spawn_weight = BUILDER.comment("Spawn weight for ghost crab").define("Ghost Crab Spawn Weight", 20);
        ghost_crab_min_group = BUILDER.comment("Min group for ghost crab").define("Ghost Crab Min Group", 2);
        ghost_crab_max_group = BUILDER.comment("Max group for ghost crab").define("Ghost Crab Max Group", 5);

        BUILDER.pop();

        BUILDER.push("Lorikeet");

        lorikeet_spawns = BUILDER.comment("Enable/disable lorikeet spawns").define("Lorikeet Spawns", true);
        lorikeet_spawn_weight = BUILDER.comment("Spawn weight for lorikeet").define("Lorikeet Spawn Weight", 20);
        lorikeet_min_group = BUILDER.comment("Min group for lorikeet").define("Lorikeet Min Group", 2);
        lorikeet_max_group = BUILDER.comment("Max group for lorikeet").define("Lorikeet Max Group", 5);
        lorikeet_hatch_chance = BUILDER.comment("Hatch chance for each lorikeet egg").define("Lorikeet Hatch Chance", 0.4F);
        lorikeet_clutch_size = BUILDER.comment("Max egg clutch size for lorikeets").define("Lorikeet Clutch Size", 4);

        BUILDER.pop();

        BUILDER.push("Conure");

        conure_spawns = BUILDER.comment("Enable/disable conure spawns").define("Conure Spawns", true);
        conure_spawn_weight = BUILDER.comment("Spawn weight for conure").define("Conure Spawn Weight", 20);
        conure_min_group = BUILDER.comment("Min group for conure").define("Conure Min Group", 3);
        conure_max_group = BUILDER.comment("Max group for conure").define("Conure Max Group", 5);
        conure_hatch_chance = BUILDER.comment("Hatch chance for each conure egg").define("Conure Hatch Chance", 0.5F);
        conure_clutch_size = BUILDER.comment("Max egg clutch size for conures").define("Conure Clutch Size", 4);

        BUILDER.pop();

        BUILDER.push("Spoonbill");

        spoonbill_spawns = BUILDER.comment("Enable/disable spoonbill spawns").define("Spoonbill Spawns", true);
        spoonbill_spawn_weight = BUILDER.comment("Spawn weight for spoonbill").define("Spoonbill Spawn Weight", 15);
        spoonbill_min_group = BUILDER.comment("Min group for spoonbill").define("Spoonbill Min Group", 1);
        spoonbill_max_group = BUILDER.comment("Max group for spoonbill").define("Spoonbill Max Group", 2);
        spoonbill_hatch_chance = BUILDER.comment("Hatch chance for each spoonbill egg").define("Spoonbill Hatch Chance", 0.5F);
        spoonbill_clutch_size = BUILDER.comment("Max egg clutch size for spoonbills").define("Spoonbill Clutch Size", 5);

        BUILDER.pop();

        BUILDER.push("Dove");

        dove_spawns = BUILDER.comment("Enable/disable dove spawns").define("Dove Spawns", true);
        dove_spawn_weight = BUILDER.comment("Spawn weight for dove").define("Dove Spawn Weight", 20);
        dove_min_group = BUILDER.comment("Min group for dove").define("Dove Min Group", 2);
        dove_max_group = BUILDER.comment("Max group for dove").define("Dove Max Group", 5);
        dove_hatch_chance = BUILDER.comment("Hatch chance for each dove egg").define("Dove Hatch Chance", 0.8F);
        dove_clutch_size = BUILDER.comment("Max egg clutch size for doves").define("Dove Clutch Size", 2);

        BUILDER.pop();

        BUILDER.push("Mandarin Duck");

        mandarin_duck_spawns = BUILDER.comment("Enable/disable mandarin duck spawns").define("Mandarin Duck Spawns", true);
        mandarin_duck_spawn_weight = BUILDER.comment("Spawn weight for mandarin duck").define("Mandarin Duck Spawn Weight", 20);
        mandarin_duck_min_group = BUILDER.comment("Min group for mandarin duck").define("Mandarin Duck Min Group", 2);
        mandarin_duck_max_group = BUILDER.comment("Max group for mandarin duck").define("Mandarin Duck Max Group", 3);
        mandarin_duck_hatch_chance = BUILDER.comment("Hatch chance for each mandarin duck egg").define("Mandarin Duck Hatch Chance", 0.6F);
        mandarin_duck_clutch_size = BUILDER.comment("Max egg clutch size for mandarin ducks").define("Mandarin Duck Clutch Size", 8);

        BUILDER.pop();

        BUILDER.push("Kakapo");

        kakapo_spawns = BUILDER.comment("Enable/disable kakapo spawns").define("Kakapo Spawns", true);
        kakapo_spawn_weight = BUILDER.comment("Spawn weight for kakapo").define("Kakapo Spawn Weight", 3);
        kakapo_min_group = BUILDER.comment("Min group for kakapo").define("Kakapo Min Group", 1);
        kakapo_max_group = BUILDER.comment("Max group for kakapo").define("Kakapo Max Group", 2);
        kakapo_hatch_chance = BUILDER.comment("Hatch chance for each kakapo egg").define("Kakapo Hatch Chance", 0.1F);
        kakapo_clutch_size = BUILDER.comment("Max egg clutch size for kakapos").define("Kakapo Clutch Size", 4);

        BUILDER.pop();

        BUILDER.push("Raven");

        raven_spawns = BUILDER.comment("Enable/disable raven spawns").define("Raven Spawns", true);
        raven_spawn_weight = BUILDER.comment("Spawn weight for raven").define("Raven Spawn Weight", 25);
        raven_min_group = BUILDER.comment("Min group for raven").define("Raven Min Group", 1);
        raven_max_group = BUILDER.comment("Max group for raven").define("Raven Max Group", 3);
        raven_hatch_chance = BUILDER.comment("Hatch chance for each raven egg").define("Raven Hatch Chance", 0.4F);
        raven_clutch_size = BUILDER.comment("Max egg clutch size for ravens").define("Raven Clutch Size", 7);

        BUILDER.pop();

        BUILDER.push("Fairywren");

        fairywren_spawns = BUILDER.comment("Enable/disable fairywren spawns").define("Fairywren Spawns", true);
        fairywren_spawn_weight = BUILDER.comment("Spawn weight for fairywren").define("Fairywren Spawn Weight", 30);
        fairywren_min_group = BUILDER.comment("Min group for fairywren").define("Fairywren Min Group", 3);
        fairywren_max_group = BUILDER.comment("Max group for fairywren").define("Fairywren Max Group", 6);
        fairywren_hatch_chance = BUILDER.comment("Hatch chance for each fairywren egg").define("Fairywren Hatch Chance", 0.4F);
        fairywren_clutch_size = BUILDER.comment("Max egg clutch size for fairywrens").define("Fairywren Clutch Size", 4);

        BUILDER.pop();

        BUILDER.push("Golden Eagle");

        golden_eagle_spawns = BUILDER.comment("Enable/disable golden eagle spawns").define("Golden Eagle Spawns", true);
        golden_eagle_spawn_weight = BUILDER.comment("Spawn weight for golden eagle").define("Golden Eagle Spawn Weight", 5);
        golden_eagle_min_group = BUILDER.comment("Min group for golden eagle").define("Golden Eagle Min Group", 1);
        golden_eagle_max_group = BUILDER.comment("Max group for golden eagle").define("Golden Eagle Max Group", 2);
        golden_eagle_hatch_chance = BUILDER.comment("Hatch chance for each golden eagle egg").define("Golden Eagle Hatch Chance", 0.4F);
        golden_eagle_clutch_size = BUILDER.comment("Max egg clutch size for golden eagles").define("Golden Eagle Clutch Size", 3);

        BUILDER.pop();

        BUILDER.push("Gyrfalcon");

        gyrfalcon_spawns = BUILDER.comment("Enable/disable gyrfalcon spawns").define("Gyrfalcon Spawns", true);
        gyrfalcon_spawn_weight = BUILDER.comment("Spawn weight for gyrfalcon").define("Gyrfalcon Spawn Weight", 5);
        gyrfalcon_min_group = BUILDER.comment("Min group for gyrfalcon").define("Gyrfalcon Min Group", 1);
        gyrfalcon_max_group = BUILDER.comment("Max group for gyrfalcon").define("Gyrfalcon Max Group", 2);
        gyrfalcon_hatch_chance = BUILDER.comment("Hatch chance for each gyrfalcon egg").define("Gyrfalcon Hatch Chance", 0.3F);
        gyrfalcon_clutch_size = BUILDER.comment("Max egg clutch size for gyrfalcons").define("Gyrfalcon Clutch Size", 5);

        BUILDER.pop();

        BUILDER.push("Red Kite");

        red_kite_spawns = BUILDER.comment("Enable/disable red kite spawns").define("Red Kite Spawns", true);
        red_kite_spawn_weight = BUILDER.comment("Spawn weight for red kite").define("Red Kite Spawn Weight", 5);
        red_kite_min_group = BUILDER.comment("Min group for red kite").define("Red Kite Min Group", 1);
        red_kite_max_group = BUILDER.comment("Max group for red kite").define("Red Kite Max Group", 2);
        red_kite_hatch_chance = BUILDER.comment("Hatch chance for each red kite egg").define("Red Kite Hatch Chance", 0.3F);
        red_kite_clutch_size = BUILDER.comment("Max egg clutch size for red kites").define("Red Kite Clutch Size", 4);

        BUILDER.pop();

        BUILDER.push("Steller's Sea Eagle");

        stellers_sea_eagle_spawns = BUILDER.comment("Enable/disable stellers sea eagle spawns").define("Stellers Sea Eagle Spawns", true);
        stellers_sea_eagle_spawn_weight = BUILDER.comment("Spawn weight for stellers sea eagle").define("Stellers Sea Eagle Spawn Weight", 3);
        stellers_sea_eagle_min_group = BUILDER.comment("Min group for stellers sea eagle").define("Stellers Sea Eagle Min Group", 1);
        stellers_sea_eagle_max_group = BUILDER.comment("Max group for stellers sea eagle").define("Stellers Sea Eagle Max Group", 2);
        stellers_sea_eagle_hatch_chance = BUILDER.comment("Hatch chance for each Steller's sea eagle egg").define("Steller's Sea Eagle Hatch Chance", 0.2F);
        stellers_sea_eagle_clutch_size = BUILDER.comment("Max egg clutch size for Steller's sea eagles").define("Steller's Sea Eagle Clutch Size", 3);

        BUILDER.pop();

        BUILDER.push("Pygmy Falcon");

        pygmy_falcon_spawns = BUILDER.comment("Enable/disable pygmy falcon spawns").define("Pygmy Falcon Spawns", true);
        pygmy_falcon_spawn_weight = BUILDER.comment("Spawn weight for pygmy falcon").define("Pygmy Falcon Spawn Weight", 5);
        pygmy_falcon_min_group = BUILDER.comment("Min group for pygmy falcon").define("Pygmy Falcon Min Group", 1);
        pygmy_falcon_max_group = BUILDER.comment("Max group for pygmy falcon").define("Pygmy Falcon Max Group", 2);
        pygmy_falcon_hatch_chance = BUILDER.comment("Hatch chance for each pygmy falcon egg").define("Pygmy Falcon Hatch Chance", 0.3F);
        pygmy_falcon_clutch_size = BUILDER.comment("Max egg clutch size for pygmy falcons").define("Pygmy Falcon Clutch Size", 4);

        BUILDER.pop();

        BUILDER.push("Barn Owl");

        barn_owl_spawns = BUILDER.comment("Enable/disable barn owl spawns").define("Barn Owl Spawns", true);
        barn_owl_spawn_weight = BUILDER.comment("Spawn weight for barn owl").define("Barn Owl Spawn Weight", 10);
        barn_owl_min_group = BUILDER.comment("Min group for barn owl").define("Barn Owl Min Group", 1);
        barn_owl_max_group = BUILDER.comment("Max group for barn owl").define("Barn Owl Max Group", 2);
        barn_owl_hatch_chance = BUILDER.comment("Hatch chance for each barn owl egg").define("Barn Owl Hatch Chance", 0.3F);
        barn_owl_clutch_size = BUILDER.comment("Max egg clutch size for barn owls").define("Barn Owl Clutch Size", 7);

        BUILDER.pop();

        BUILDER.push("Goldfish");

        goldfish_spawns = BUILDER.comment("Enable/disable goldfish spawns").define("Goldfish Spawns", true);
        goldfish_spawn_weight = BUILDER.comment("Spawn weight for goldfish").define("Goldfish Spawn Weight", 10);
        goldfish_min_group = BUILDER.comment("Min group for goldfish").define("Goldfish Min Group", 2);
        goldfish_max_group = BUILDER.comment("Max group for goldfish").define("Goldfish Max Group", 5);

        BUILDER.pop();

        BUILDER.push("Ranchu");

        ranchu_spawns = BUILDER.comment("Enable/disable ranchu spawns").define("Ranchu Spawns", true);
        ranchu_spawn_weight = BUILDER.comment("Spawn weight for ranchu").define("Ranchu Spawn Weight", 10);
        ranchu_min_group = BUILDER.comment("Min group for ranchu").define("Ranchu Min Group", 1);
        ranchu_max_group = BUILDER.comment("Max group for ranchu").define("Ranchu Max Group", 2);

        BUILDER.pop();

        BUILDER.push("Wild Duck");

        wild_duck_spawns = BUILDER.comment("Enable/disable wild duck spawns").define("Wild Duck Spawns", true);
        wild_duck_spawn_weight = BUILDER.comment("Spawn weight for wild duck").define("Wild Duck Spawn Weight", 20);
        wild_duck_min_group = BUILDER.comment("Min group for wild duck").define("Wild Duck Min Group", 1);
        wild_duck_max_group = BUILDER.comment("Max group for wild duck").define("Wild Duck Max Group", 2);
        wild_duck_hatch_chance = BUILDER.comment("Hatch chance for each wild duck egg").define("Wild Duck Hatch Chance", 0.3F);
        wild_duck_clutch_size = BUILDER.comment("Max egg clutch size for wild ducks").define("Wild Duck Clutch Size", 4);

        BUILDER.pop();

        BUILDER.push("Roller");

        roller_spawns = BUILDER.comment("Enable/disable roller spawns").define("Roller Spawns", true);
        roller_spawn_weight = BUILDER.comment("Spawn weight for roller").define("Roller Spawn Weight", 20);
        roller_min_group = BUILDER.comment("Min group for roller").define("Roller Min Group", 1);
        roller_max_group = BUILDER.comment("Max group for roller").define("Roller Max Group", 2);
        roller_hatch_chance = BUILDER.comment("Hatch chance for each roller egg").define("Roller Hatch Chance", 0.3F);
        roller_clutch_size = BUILDER.comment("Max egg clutch size for rollers").define("Roller Clutch Size", 4);

        BUILDER.pop();

        BUILDER.push("Chickadee");

        chickadee_spawns = BUILDER.comment("Enable/disable chickadee spawns").define("Chickadee Spawns", true);
        chickadee_spawn_weight = BUILDER.comment("Spawn weight for chickadee").define("Chickadee Spawn Weight", 25);
        chickadee_min_group = BUILDER.comment("Min group for chickadee").define("Chickadee Min Group", 2);
        chickadee_max_group = BUILDER.comment("Max group for chickadee").define("Chickadee Max Group", 3);
        chickadee_hatch_chance = BUILDER.comment("Hatch chance for each chickadee egg").define("Chickadee Hatch Chance", 0.3F);
        chickadee_clutch_size = BUILDER.comment("Max egg clutch size for chickadees").define("Chickadee Clutch Size", 6);

        BUILDER.pop();

        BUILDER.push("Pygmy Goose");

        pygmy_goose_spawns = BUILDER.comment("Enable/disable pygmy goose spawns").define("Pygmy Goose Spawns", true);
        pygmy_goose_spawn_weight = BUILDER.comment("Spawn weight for pygmy goose").define("Pygmy Goose Spawn Weight", 25);
        pygmy_goose_min_group = BUILDER.comment("Min group for pygmy goose").define("Pygmy Goose Min Group", 1);
        pygmy_goose_max_group = BUILDER.comment("Max group for pygmy goose").define("Pygmy Goose Max Group", 2);
        pygmy_goose_hatch_chance = BUILDER.comment("Hatch chance for each pygmy goose egg").define("Pygmy Goose Hatch Chance", 0.2F);
        pygmy_goose_clutch_size = BUILDER.comment("Max egg clutch size for pygmy geese").define("Pygmy Goose Clutch Size", 8);

        BUILDER.pop();

        BUILDER.push("Swallow");

        swallow_spawns = BUILDER.comment("Enable/disable swallow spawns").define("Swallow Spawns", true);
        swallow_spawn_weight = BUILDER.comment("Spawn weight for swallow").define("Swallow Spawn Weight", 25);
        swallow_min_group = BUILDER.comment("Min group for swallow").define("Swallow Min Group", 3);
        swallow_max_group = BUILDER.comment("Max group for swallow").define("Swallow Max Group", 5);
        swallow_hatch_chance = BUILDER.comment("Hatch chance for each wild duck egg").define("Wild Duck Hatch Chance", 0.6F);
        swallow_clutch_size = BUILDER.comment("Max egg clutch size for wild ducks").define("Wild Duck Clutch Size", 3);

        BUILDER.pop();

        BUILDER.push("Fire Goby");

        fire_goby_spawns = BUILDER.comment("Enable/disable fire goby spawns").define("Fire Goby Spawns", true);
        fire_goby_spawn_weight = BUILDER.comment("Spawn weight for fire goby").define("Fire Goby Spawn Weight", 20);
        fire_goby_min_group = BUILDER.comment("Min group for fire goby").define("Fire Goby Min Group", 1);
        fire_goby_max_group = BUILDER.comment("Max group for fire goby").define("Fire Goby Max Group", 1);

        BUILDER.pop();

        BUILDER.push("Blue Tang");

        blue_tang_spawns = BUILDER.comment("Enable/disable blue tang spawns").define("Blue Tang Spawns", true);
        blue_tang_spawn_weight = BUILDER.comment("Spawn weight for blue tang").define("Blue Tang Spawn Weight", 20);
        blue_tang_min_group = BUILDER.comment("Min group for blue tang").define("Blue Tang Min Group", 1);
        blue_tang_max_group = BUILDER.comment("Max group for blue tang").define("Blue Tang Max Group", 1);

        BUILDER.pop();

        BUILDER.push("Flame Angelfish");

        flame_angelfish_spawns = BUILDER.comment("Enable/disable flame angelfish spawns").define("Flame Angelfish Spawns", true);
        flame_angelfish_spawn_weight = BUILDER.comment("Spawn weight for flame angelfish").define("Flame Angelfish Spawn Weight", 20);
        flame_angelfish_min_group = BUILDER.comment("Min group for flame angelfish").define("Flame Angelfish Min Group", 1);
        flame_angelfish_max_group = BUILDER.comment("Max group for flame angelfish").define("Flame Angelfish Max Group", 1);

        BUILDER.pop();

        BUILDER.push("Trout");

        trout_spawns = BUILDER.comment("Enable/disable trout spawns").define("Trout Spawns", true);
        trout_spawn_weight = BUILDER.comment("Spawn weight for trout").define("Trout Spawn Weight", 10);
        trout_min_group = BUILDER.comment("Min group for trout").define("Trout Min Group", 1);
        trout_max_group = BUILDER.comment("Max group for trout").define("Trout Max Group", 1);

        BUILDER.pop();

        BUILDER.push("Fiddler Crab");

        fiddler_crab_spawns = BUILDER.comment("Enable/disable fiddler crab spawns").define("Fiddler Crab Spawns", true);
        fiddler_crab_spawn_weight = BUILDER.comment("Spawn weight for fiddler crab").define("Fiddler Crab Spawn Weight", 20);
        fiddler_crab_min_group = BUILDER.comment("Min group for fiddler crab").define("Fiddler Crab Min Group", 2);
        fiddler_crab_max_group = BUILDER.comment("Max group for fiddler crab").define("Fiddler Crab Max Group", 4);

        BUILDER.pop();

        BUILDER.push("Ibis");

        ibis_spawns = BUILDER.comment("Enable/disable ibis spawns").define("Ibis Spawns", true);
        ibis_spawn_weight = BUILDER.comment("Spawn weight for ibis").define("Ibis Spawn Weight", 20);
        ibis_min_group = BUILDER.comment("Min group for ibis").define("Ibis Min Group", 2);
        ibis_max_group = BUILDER.comment("Max group for ibis").define("Ibis Max Group", 4);
        ibis_hatch_chance = BUILDER.comment("Hatch chance for each ibis egg").define("Ibis Hatch Chance", 0.3F);
        ibis_clutch_size = BUILDER.comment("Max egg clutch size for ibises").define("Ibis Clutch Size", 4);

        BUILDER.pop();

        BUILDER.push("Red Snapper");

        red_snapper_spawns = BUILDER.comment("Enable/disable red snapper spawns").define("Red Snapper Spawns", true);
        red_snapper_spawn_weight = BUILDER.comment("Spawn weight for red snapper").define("Red Snapper Spawn Weight", 20);
        red_snapper_min_group = BUILDER.comment("Min group for red snapper").define("Red Snapper Min Group", 3);
        red_snapper_max_group = BUILDER.comment("Max group for red snapper").define("Red Snapper Max Group", 7);

        BUILDER.pop();

        BUILDER.push("Wood Duck");

        wood_duck_spawns = BUILDER.comment("Enable/disable wood duck spawns").define("Wood Duck Spawns", true);
        wood_duck_spawn_weight = BUILDER.comment("Spawn weight for wood duck").define("Wood Duck Spawn Weight", 20);
        wood_duck_min_group = BUILDER.comment("Min group for wood duck").define("Wood Duck Min Group", 2);
        wood_duck_max_group = BUILDER.comment("Max group for wood duck").define("Wood Duck Max Group", 3);
        wood_duck_hatch_chance = BUILDER.comment("Hatch chance for each wood duck egg").define("Wild Duck Hatch Chance", 0.3F);
        wood_duck_clutch_size = BUILDER.comment("Max egg clutch size for wood ducks").define("Wild Duck Clutch Size", 8);

        BUILDER.pop();

        BUILDER.push("Peafowl");

        peafowl_spawns = BUILDER.comment("Enable/disable peafowl spawns").define("Peafowl Spawns", true);
        peafowl_spawn_weight = BUILDER.comment("Spawn weight for peafowl").define("Peafowl Spawn Weight", 20);
        peafowl_min_group = BUILDER.comment("Min group for peafowl").define("Peafowl Min Group", 1);
        peafowl_max_group = BUILDER.comment("Max group for peafowl").define("Peafowl Max Group", 2);
        peafowl_hatch_chance = BUILDER.comment("Hatch chance for each peafowl egg").define("Peafowl Hatch Chance", 0.3F);
        peafowl_clutch_size = BUILDER.comment("Max egg clutch size for peafowls").define("Peafowl Clutch Size", 6);

        BUILDER.pop();

        BUILDER.push("Sparrow");

        sparrow_spawns = BUILDER.comment("Enable/disable sparrow spawns").define("Sparrow Spawns", true);
        sparrow_spawn_weight = BUILDER.comment("Spawn weight for sparrows").define("Sparrow Spawn Weight", 30);
        sparrow_min_group = BUILDER.comment("Min group for sparrows").define("Sparrow Min Group", 4);
        sparrow_max_group = BUILDER.comment("Max group for sparrows").define("Sparrow Max Group", 7);
        sparrow_hatch_chance = BUILDER.comment("Hatch chance for each sparrow egg").define("Sparrow Hatch Chance", 0.6F);
        sparrow_clutch_size = BUILDER.comment("Max egg clutch size for sparrow").define("Sparrow Clutch Size", 5);

        BUILDER.pop();

        BUILDER.push("Bushtit");

        bushtit_spawns = BUILDER.comment("Enable/disable bushtit spawns").define("Bushtit Spawns", true);
        bushtit_spawn_weight = BUILDER.comment("Spawn weight for bushtits").define("Bushtit Spawn Weight", 20);
        bushtit_min_group = BUILDER.comment("Min group for bushtits").define("Bushtit Min Group", 3);
        bushtit_max_group = BUILDER.comment("Max group for bushtits").define("Bushtit Max Group", 5);
        bushtit_hatch_chance = BUILDER.comment("Hatch chance for each bushtit egg").define("Bushtit Hatch Chance", 0.4F);
        bushtit_clutch_size = BUILDER.comment("Max egg clutch size for bushtits").define("Bushtit Clutch Size", 6);

        BUILDER.pop();

        BUILDER.push("Laughingthrush");

        laughingthrush_spawns = BUILDER.comment("Enable/disable laughingthrush spawns").define("Laughingthrush Spawns", true);
        laughingthrush_spawn_weight = BUILDER.comment("Spawn weight for laughingthrushes").define("Laughingthrush Spawn Weight", 20);
        laughingthrush_min_group = BUILDER.comment("Min group for laughingthrushes").define("Laughingthrush Min Group", 3);
        laughingthrush_max_group = BUILDER.comment("Max group for laughingthrushes").define("Laughingthrush Max Group", 5);
        laughingthrush_hatch_chance = BUILDER.comment("Hatch chance for each laughingthrush egg").define("Laughingthrush Hatch Chance", 0.5F);
        laughingthrush_clutch_size = BUILDER.comment("Max egg clutch size for laughingthrushes").define("Laughingthrush Clutch Size", 4);

        BUILDER.pop();

        BUILDER.push("Eagle owl");

        eagleowl_spawns = BUILDER.comment("Enable/disable eagleowl spawns").define("Eagle Owl Spawns", true);
        eagleowl_spawn_weight = BUILDER.comment("Spawn weight for eagle owls").define("Eagle Owl Spawn Weight", 10);
        eagleowl_min_group = BUILDER.comment("Min group for eagle owls").define("Eagle Owl Min Group", 1);
        eagleowl_max_group = BUILDER.comment("Max group for eagle owls").define("Eagle Owl Max Group", 2);
        eagleowl_hatch_chance = BUILDER.comment("Hatch chance for each eagle owl egg").define("Eagle Owl Hatch Chance", 0.5F);
        eagleowl_clutch_size = BUILDER.comment("Max egg clutch size for eagle owls").define("Eagle Owl Clutch Size", 2);

        BUILDER.pop();

        BUILDER.push("Robin");

        robin_spawns = BUILDER.comment("Enable/disable robin spawns").define("Robin Spawns", true);
        robin_spawn_weight = BUILDER.comment("Spawn weight for robins").define("Robin Spawn Weight", 20);
        robin_min_group = BUILDER.comment("Min group for robins").define("Robin Min Group", 3);
        robin_max_group = BUILDER.comment("Max group for robins").define("Robin Max Group", 4);
        robin_hatch_chance = BUILDER.comment("Hatch chance for each robin egg").define("Robin Hatch Chance", 0.4F);
        robin_clutch_size = BUILDER.comment("Max egg clutch size for robins").define("Robin Clutch Size", 6);

        BUILDER.pop();

        BUILDER.push("Magpie");

        magpie_spawns = BUILDER.comment("Enable/disable magpie spawns").define("Magpie Spawns", true);
        magpie_spawn_weight = BUILDER.comment("Spawn weight for magpies").define("Magpie Spawn Weight", 20);
        magpie_min_group = BUILDER.comment("Min group for magpies").define("Magpie Min Group", 3);
        magpie_max_group = BUILDER.comment("Max group for magpies").define("Magpie Max Group", 4);
        magpie_hatch_chance = BUILDER.comment("Hatch chance for each magpie egg").define("Magpie Hatch Chance", 0.3F);
        magpie_clutch_size = BUILDER.comment("Max egg clutch size for magpies").define("Magpie Clutch Size", 7);

        BUILDER.pop();

        BUILDER.push("Goose");

        goose_spawns = BUILDER.comment("Enable/disable goose spawns").define("Goose Spawns", true);
        goose_spawn_weight = BUILDER.comment("Spawn weight for goose").define("Goose Spawn Weight", 20);
        goose_min_group = BUILDER.comment("Min group for goose").define("Goose Min Group", 3);
        goose_max_group = BUILDER.comment("Max group for goose").define("Goose Max Group", 5);
        goose_hatch_chance = BUILDER.comment("Hatch chance for each goose egg").define("Goose Hatch Chance", 0.4F);
        goose_clutch_size = BUILDER.comment("Max egg clutch size for geese").define("Goose Clutch Size", 7);

        BUILDER.pop();

        BUILDER.push("Osprey");

        osprey_spawns = BUILDER.comment("Enable/disable osprey spawns").define("Osprey Spawns", true);
        osprey_spawn_weight = BUILDER.comment("Spawn weight for ospreys").define("Osprey Spawn Weight", 5);
        osprey_min_group = BUILDER.comment("Min group for ospreys").define("Osprey Min Group", 1);
        osprey_max_group = BUILDER.comment("Max group for ospreys").define("Osprey Max Group", 2);
        osprey_hatch_chance = BUILDER.comment("Hatch chance for each osprey egg").define("Osprey Hatch Chance", 0.2F);
        osprey_clutch_size = BUILDER.comment("Max egg clutch size for ospreys").define("Osprey Clutch Size", 4);

        BUILDER.pop();

        BUILDER.push("Kingfisher");

        kingfisher_spawns = BUILDER.comment("Enable/disable kingfisher spawns").define("Kingfisher Spawns", true);
        kingfisher_spawn_weight = BUILDER.comment("Spawn weight for kingfishers").define("Kingfisher Spawn Weight", 20);
        kingfisher_min_group = BUILDER.comment("Min group for kingfisher").define("Kingfisher Min Group", 3);
        kingfisher_max_group = BUILDER.comment("Max group for kingfisher").define("Kingfisher Max Group", 5);
        kingfisher_hatch_chance = BUILDER.comment("Hatch chance for each kingfisher egg").define("Kingfisher Hatch Chance", 0.3F);
        kingfisher_clutch_size = BUILDER.comment("Max egg clutch size for kingfisher").define("Kingfisher Clutch Size", 6);

        BUILDER.pop();

        BUILDER.push("Pelican");

        pelican_spawns = BUILDER.comment("Enable/disable pelican spawns").define("Pelican Spawns", true);
        pelican_spawn_weight = BUILDER.comment("Spawn weight for pelicans").define("Pelican Spawn Weight", 20);
        pelican_min_group = BUILDER.comment("Min group for pelican").define("Pelican Min Group", 3);
        pelican_max_group = BUILDER.comment("Max group for pelican").define("Pelican Max Group", 6);
        pelican_hatch_chance = BUILDER.comment("Hatch chance for each pelican egg").define("Pelican Hatch Chance", 0.4F);
        pelican_clutch_size = BUILDER.comment("Max egg clutch size for pelicans").define("Pelican Clutch Size", 3);

        BUILDER.pop();

        BUILDER.push("Lapwing");

        lapwing_spawns = BUILDER.comment("Enable/disable lapwing spawns").define("Lapwing Spawns", true);
        lapwing_spawn_weight = BUILDER.comment("Spawn weight for lapwings").define("Lapwing Spawn Weight", 20);
        lapwing_min_group = BUILDER.comment("Min group for lapwing").define("Lapwing Min Group", 3);
        lapwing_max_group = BUILDER.comment("Max group for lapwing").define("Lapwing Max Group", 6);
        lapwing_hatch_chance = BUILDER.comment("Hatch chance for each lapwing egg").define("Lapwing Hatch Chance", 0.3F);
        lapwing_clutch_size = BUILDER.comment("Max egg clutch size for lapwing").define("Lapwing Clutch Size", 4);

        BUILDER.pop();

        BUILDER.push("Skua");

        skua_spawns = BUILDER.comment("Enable/disable skua spawns").define("Skua Spawns", true);
        skua_spawn_weight = BUILDER.comment("Spawn weight for skuas").define("Skua Spawn Weight", 20);
        skua_min_group = BUILDER.comment("Min group for skua").define("Skua Min Group", 1);
        skua_max_group = BUILDER.comment("Max group for skua").define("Skua Max Group", 3);
        skua_hatch_chance = BUILDER.comment("Hatch chance for each skua egg").define("Skua Hatch Chance", 0.3F);
        skua_clutch_size = BUILDER.comment("Max egg clutch size for skuas").define("Skua Clutch Size", 2);

        BUILDER.pop();

        BUILDER.push("Gameplay");

        breed_only_variants = BUILDER.comment("Gameplay: Enable/disable breed-only variants").define("Breed-Only Variants", true);
        biome_only_variants = BUILDER.comment("Gameplay: Enable/disable biome-specific variants").define("Biome-Specific Variants", true);
        following = BUILDER.comment("Gameplay: Enable/disable tamed birds following the player").define("Following", true);
        teleporting = BUILDER.comment("Gameplay: Enable/disable tamed birds teleporting to the player while following").define("Teleportation", true);
        teleporting_distance = BUILDER.comment("Gameplay: Distance at which a following bird will teleport to you (higher value = greater distance)").define("Teleporting distance", 3.0D);
        raptor_attacks = BUILDER.comment("Gameplay: Enable/disable raptors attacking prey").define("Raptor Attacks", true);
        raptor_throws = BUILDER.comment("Gameplay: Enable/disable raptors throwing targets into air").define("Raptors Throw", true);
        raven_albino_chance = BUILDER.comment("Rarity of albino ravens (higher number = rarer)").define("Albino Raven Rarity", 500);
        lovebird_mutation_chance = BUILDER.comment("Rarity of mutation lovebirds from breeding").define("Lovebird Mutation Rarity", 5);
        lorikeet_mutation_chance = BUILDER.comment("Rarity of mutation lorikeets from breeding").define("Lorikeet Mutation Rarity", 5);
        height_base_multiplier = BUILDER.comment("Base multiplier of height (note: it's recommended to not change this unless you want all entities to be larger/smaller").define("Height Base Multiplier", 1.0F);
        height_standard_deviation = BUILDER.comment("Standard deviation of height of entities (higher value = more variation, 0 = turning off basically)").define("Height Standard Deviation", 0.10F);
        height_on = BUILDER.comment("Gameplay: Enable/disable height variation").define("Height Variation", true);
        base_egg_hatch_time = BUILDER.comment("Base time for egg to hatch, in ticks").define("Base Egg Hatch Time", 6000);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
