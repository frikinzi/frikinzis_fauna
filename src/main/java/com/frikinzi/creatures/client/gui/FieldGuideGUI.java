package com.frikinzi.creatures.client.gui;

import com.frikinzi.creatures.entity.*;
import com.frikinzi.creatures.entity.base.AbstractCrabBase;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.player.FieldGuideCapability;
import com.frikinzi.creatures.player.SpeciesEntry;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Quaternionf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FieldGuideGUI extends Screen {
    public static final List<SpeciesEntry> ALL_SPECIES = List.of(
            new SpeciesEntry("avocet", 4, AvocetEntity.SPECIES_NAMES, AvocetEntity.SCIENTIFIC_NAMES, CreaturesEntities.AVOCET, CreaturesItems.AVOCET_SPAWN_EGG, Component.translatable("entity.creatures.avocet"), AvocetEntity.REGIONS),
            new SpeciesEntry("bandedpenguin", 4, BandedPenguinEntity.SPECIES_NAMES, BandedPenguinEntity.SCIENTIFIC_NAMES, CreaturesEntities.BANDED_PENGUIN, CreaturesItems.BANDED_PENGUIN_SPAWN_EGG, Component.translatable("entity.creatures.bandedpenguin"), BandedPenguinEntity.REGIONS),
            new SpeciesEntry("barnowl", 1, Map.of(), Map.of(), CreaturesEntities.BARN_OWL, CreaturesItems.BARN_OWL_SPAWN_EGG, Component.translatable("entity.creatures.barn_owl"), BarnOwlEntity.REGIONS),
            new SpeciesEntry("booby", 11, BoobyEntity.SPECIES_NAMES, BoobyEntity.SCIENTIFIC_NAMES, CreaturesEntities.BOOBY, CreaturesItems.BOOBY_SPAWN_EGG, Component.translatable("entity.creatures.booby"), BoobyEntity.REGIONS),
            new SpeciesEntry("brushtailedpenguin", 3, BrushTailedPenguinEntity.SPECIES_NAMES, BrushTailedPenguinEntity.SCIENTIFIC_NAMES, CreaturesEntities.BRUSH_TAILED_PENGUIN, CreaturesItems.BRUSH_TAILED_PENGUIN_SPAWN_EGG, Component.translatable("entity.creatures.brushtailedpenguin"), BrushTailedPenguinEntity.REGIONS),
            new SpeciesEntry("bunting", 5, BuntingEntity.SPECIES_NAMES, BuntingEntity.SCIENTIFIC_NAMES, CreaturesEntities.BUNTING, CreaturesItems.BUNTING_SPAWN_EGG, Component.translatable("entity.creatures.bunting"), BuntingEntity.REGIONS),
            new SpeciesEntry("bushtit", 4, BushtitEntity.SPECIES_NAMES, BushtitEntity.SCIENTIFIC_NAMES, CreaturesEntities.BUSHTIT, CreaturesItems.BUSHTIT_SPAWN_EGG, Component.translatable("entity.creatures.bushtit"), BushtitEntity.REGIONS),
            new SpeciesEntry("capercaillie", 1, Map.of(), Map.of(), CreaturesEntities.CAPERCAILLIE, CreaturesItems.CAPERCAILLIE_SPAWN_EGG, Component.translatable("entity.creatures.capercaillie"), CapercaillieEntity.REGIONS),
            new SpeciesEntry("chickadee", 3, ChickadeeEntity.SPECIES_NAMES, ChickadeeEntity.SCIENTIFIC_NAMES, CreaturesEntities.CHICKADEE, CreaturesItems.CHICKADEE_SPAWN_EGG, Component.translatable("entity.creatures.chickadee"), ChickadeeEntity.REGIONS),
            new SpeciesEntry("cockoftherock", 2, CockOfTheRockEntity.SPECIES_NAMES, CockOfTheRockEntity.SCIENTIFIC_NAMES, CreaturesEntities.COCK_OF_THE_ROCK, CreaturesItems.COCK_OF_THE_ROCK_SPAWN_EGG, Component.translatable("entity.creatures.cockoftherock"), CockOfTheRockEntity.REGIONS),
            new SpeciesEntry("conure", 3, ConureEntity.SPECIES_NAMES, ConureEntity.SCIENTIFIC_NAMES, CreaturesEntities.CONURE, CreaturesItems.CONURE_SPAWN_EGG, Component.translatable("entity.creatures.conure"), ConureEntity.REGIONS),
            new SpeciesEntry("cormorant", 4, CormorantEntity.SPECIES_NAMES, CormorantEntity.SCIENTIFIC_NAMES, CreaturesEntities.CORMORANT, CreaturesItems.CORMORANT_SPAWN_EGG, Component.translatable("entity.creatures.cormorant"), CormorantEntity.REGIONS),
            new SpeciesEntry("crane", 14, CraneEntity.SPECIES_NAMES, CraneEntity.SCIENTIFIC_NAMES, CreaturesEntities.CRANE, CreaturesItems.CRANE_SPAWN_EGG, Component.translatable("entity.creatures.crane"), CraneEntity.REGIONS),
            new SpeciesEntry("crestedpenguin", 8, CrestedPenguinEntity.SPECIES_NAMES, CrestedPenguinEntity.SCIENTIFIC_NAMES, CreaturesEntities.CRESTED_PENGUIN, CreaturesItems.CRESTED_PENGUIN_SPAWN_EGG, Component.translatable("entity.creatures.crestedpenguin"), CrestedPenguinEntity.REGIONS),
            new SpeciesEntry("dove", 18, DoveEntity.SPECIES_NAMES, DoveEntity.SCIENTIFIC_NAMES, CreaturesEntities.DOVE, CreaturesItems.DOVE_SPAWN_EGG, Component.translatable("entity.creatures.dove"), DoveEntity.REGIONS),
            new SpeciesEntry("eagleowl", 2, EagleOwlEntity.SPECIES_NAMES, EagleOwlEntity.SCIENTIFIC_NAMES, CreaturesEntities.EAGLEOWL, CreaturesItems.EAGLEOWL_SPAWN_EGG, Component.translatable("entity.creatures.eagleowl"), EagleOwlEntity.REGIONS),
            new SpeciesEntry("fairywren", 6, FairywrenEntity.SPECIES_NAMES, FairywrenEntity.SCIENTIFIC_NAMES, CreaturesEntities.FAIRYWREN, CreaturesItems.FAIRYWREN_SPAWN_EGG, Component.translatable("entity.creatures.fairywren"), FairywrenEntity.REGIONS),
            new SpeciesEntry("finch", 16, FinchEntity.SPECIES_NAMES, FinchEntity.SCIENTIFIC_NAMES, CreaturesEntities.FINCH, CreaturesItems.FINCH_SPAWN_EGG, Component.translatable("entity.creatures.finch"), FinchEntity.REGIONS),
            new SpeciesEntry("frigate", 5, FrigateEntity.SPECIES_NAMES, FrigateEntity.SCIENTIFIC_NAMES, CreaturesEntities.FRIGATE, CreaturesItems.FRIGATE_SPAWN_EGG, Component.translatable("entity.creatures.frigate"), FrigateEntity.REGIONS),
            new SpeciesEntry("goose", 6, GooseEntity.SPECIES_NAMES, GooseEntity.SCIENTIFIC_NAMES, CreaturesEntities.GOOSE, CreaturesItems.GOOSE_SPAWN_EGG, Component.translatable("entity.creatures.goose"), GooseEntity.REGIONS),
            new SpeciesEntry("goldeneagle", 1, Map.of(), Map.of(), CreaturesEntities.GOLDEN_EAGLE, CreaturesItems.GOLDEN_EAGLE_SPAWN_EGG, Component.translatable("entity.creatures.golden_eagle"), GoldenEagleEntity.REGIONS),
            new SpeciesEntry("groundhornbill", 2, GroundHornbillEntity.SPECIES_NAMES, GroundHornbillEntity.SCIENTIFIC_NAMES, CreaturesEntities.GROUND_HORNBILL, CreaturesItems.GROUND_HORNBILL_SPAWN_EGG, Component.translatable("entity.creatures.groundhornbill"), GroundHornbillEntity.REGIONS),
            new SpeciesEntry("gyrfalcon", 1, Map.of(), Map.of(), CreaturesEntities.GYRFALCON, CreaturesItems.GYRFALCON_SPAWN_EGG, Component.translatable("entity.creatures.gyrfalcon"), GyrfalconEntity.REGIONS),
            new SpeciesEntry("ibis", 11, IbisEntity.SPECIES_NAMES, IbisEntity.SCIENTIFIC_NAMES, CreaturesEntities.IBIS, CreaturesItems.IBIS_SPAWN_EGG, Component.translatable("entity.creatures.ibis"), IbisEntity.REGIONS),
            new SpeciesEntry("kakapo", 1, Map.of(), Map.of(), CreaturesEntities.KAKAPO, CreaturesItems.KAKAPO_SPAWN_EGG, Component.translatable("entity.creatures.kakapo")),
            new SpeciesEntry("kingfisher", 7, KingfisherEntity.SPECIES_NAMES, KingfisherEntity.SCIENTIFIC_NAMES, CreaturesEntities.KINGFISHER, CreaturesItems.KINGFISHER_SPAWN_EGG, Component.translatable("entity.creatures.kingfisher"), KingfisherEntity.REGIONS),
            new SpeciesEntry("lapwing", 7, LapwingEntity.SPECIES_NAMES, LapwingEntity.SCIENTIFIC_NAMES, CreaturesEntities.LAPWING, CreaturesItems.LAPWING_SPAWN_EGG, Component.translatable("entity.creatures.lapwing"), LapwingEntity.REGIONS),
            new SpeciesEntry("largepenguin", 2, LargePenguinEntity.SPECIES_NAMES, LargePenguinEntity.SCIENTIFIC_NAMES, CreaturesEntities.LARGE_PENGUIN, CreaturesItems.LARGE_PENGUIN_SPAWN_EGG, Component.translatable("entity.creatures.largepenguin"), LargePenguinEntity.REGIONS),
            new SpeciesEntry("laughingthrush", 8, LaughingthrushEntity.SPECIES_NAMES, LaughingthrushEntity.SCIENTIFIC_NAMES, CreaturesEntities.LAUGHINGTHRUSH, CreaturesItems.LAUGHINGTHRUSH_SPAWN_EGG, Component.translatable("entity.creatures.laughingthrush"), LaughingthrushEntity.REGIONS),
            new SpeciesEntry("littlepenguin", 2, LittlePenguinEntity.SPECIES_NAMES, LittlePenguinEntity.SCIENTIFIC_NAMES, CreaturesEntities.LITTLE_PENGUIN, CreaturesItems.LITTLE_PENGUIN_SPAWN_EGG, Component.translatable("entity.creatures.littlepenguin"), LittlePenguinEntity.REGIONS),
            new SpeciesEntry("lorikeet", 6, LorikeetEntity.SPECIES_NAMES, LorikeetEntity.SCIENTIFIC_NAMES, CreaturesEntities.LORIKEET, CreaturesItems.LORIKEET_SPAWN_EGG, Component.translatable("entity.creatures.lorikeet"), LorikeetEntity.REGIONS),
            new SpeciesEntry("lovebird", 13, LovebirdEntity.SPECIES_NAMES, LovebirdEntity.SCIENTIFIC_NAMES, CreaturesEntities.LOVEBIRD, CreaturesItems.LOVEBIRD_SPAWN_EGG, Component.translatable("entity.creatures.lovebird"), LovebirdEntity.REGIONS),
            new SpeciesEntry("magpie", 6, MagpieEntity.SPECIES_NAMES, MagpieEntity.SCIENTIFIC_NAMES, CreaturesEntities.MAGPIE, CreaturesItems.MAGPIE_SPAWN_EGG, Component.translatable("entity.creatures.magpie"), MagpieEntity.REGIONS),
            new SpeciesEntry("mandarinduck", 1, Map.of(), Map.of(), CreaturesEntities.MANDARIN_DUCK, CreaturesItems.MANDARIN_DUCK_SPAWN_EGG, Component.translatable("entity.creatures.mandarin_duck"), MandarinDuckEntity.REGIONS),
            new SpeciesEntry("monal", 3, MonalEntity.SPECIES_NAMES, MonalEntity.SCIENTIFIC_NAMES, CreaturesEntities.MONAL, CreaturesItems.MONAL_SPAWN_EGG, Component.translatable("entity.creatures.monal"), MonalEntity.REGIONS),
            new SpeciesEntry("marabou", 3, MarabouEntity.SPECIES_NAMES, MarabouEntity.SCIENTIFIC_NAMES, CreaturesEntities.MARABOU, CreaturesItems.MARABOU_SPAWN_EGG, Component.translatable("entity.creatures.marabou"), MarabouEntity.REGIONS),
            new SpeciesEntry("osprey", 1, Map.of(), Map.of(), CreaturesEntities.OSPREY, CreaturesItems.OSPREY_SPAWN_EGG, Component.translatable("entity.creatures.osprey"), OspreyEntity.REGIONS),
            new SpeciesEntry("peafowl", 3, PeafowlEntity.SPECIES_NAMES, PeafowlEntity.SCIENTIFIC_NAMES, CreaturesEntities.PEAFOWL, CreaturesItems.PEAFOWL_SPAWN_EGG, Component.translatable("entity.creatures.peafowl"), PeafowlEntity.REGIONS),
            new SpeciesEntry("pelican", 8, PelicanEntity.SPECIES_NAMES, PelicanEntity.SCIENTIFIC_NAMES, CreaturesEntities.PELICAN, CreaturesItems.PELICAN_SPAWN_EGG, Component.translatable("entity.creatures.pelican"), PelicanEntity.REGIONS),
            new SpeciesEntry("pheasant", 3, PheasantEntity.SPECIES_NAMES, PheasantEntity.SCIENTIFIC_NAMES, CreaturesEntities.PHEASANT, CreaturesItems.PHEASANT_SPAWN_EGG, Component.translatable("entity.creatures.pheasant"), PheasantEntity.REGIONS),
            new SpeciesEntry("puffin", 5, PuffinEntity.SPECIES_NAMES, PuffinEntity.SCIENTIFIC_NAMES, CreaturesEntities.PUFFIN, CreaturesItems.PUFFIN_SPAWN_EGG, Component.translatable("entity.creatures.puffin"), PuffinEntity.REGIONS),
            new SpeciesEntry("pygmyfalcon", 1, Map.of(), Map.of(), CreaturesEntities.PYGMY_FALCON, CreaturesItems.PYGMY_FALCON_SPAWN_EGG, Component.translatable("entity.creatures.pygmyfalcon"), PygmyFalconEntity.REGIONS),
            new SpeciesEntry("pygmygoose", 3, PygmyGooseEntity.SPECIES_NAMES, PygmyGooseEntity.SCIENTIFIC_NAMES, CreaturesEntities.PYGMY_GOOSE, CreaturesItems.PYGMY_GOOSE_SPAWN_EGG, Component.translatable("entity.creatures.pygmy_goose"), PygmyGooseEntity.REGIONS),
            new SpeciesEntry("rail", 5, RailEntity.SPECIES_NAMES, RailEntity.SCIENTIFIC_NAMES, CreaturesEntities.RAIL, CreaturesItems.RAIL_SPAWN_EGG, Component.translatable("entity.creatures.rail"), RailEntity.REGIONS),
            new SpeciesEntry("raven", 5, RavenEntity.SPECIES_NAMES, RavenEntity.SCIENTIFIC_NAMES, CreaturesEntities.RAVEN, CreaturesItems.RAVEN_SPAWN_EGG, Component.translatable("entity.creatures.raven"), RavenEntity.REGIONS),
            new SpeciesEntry("redkite", 1, Map.of(), Map.of(), CreaturesEntities.RED_KITE, CreaturesItems.RED_KITE_SPAWN_EGG, Component.translatable("entity.creatures.red_kite"), RedKiteEntity.REGIONS),
            new SpeciesEntry("robin", 5, RobinEntity.SPECIES_NAMES, RobinEntity.SCIENTIFIC_NAMES, CreaturesEntities.ROBIN, CreaturesItems.ROBIN_SPAWN_EGG, Component.translatable("entity.creatures.robin"), RobinEntity.REGIONS),
            new SpeciesEntry("roller", 5, RollerEntity.SPECIES_NAMES, RollerEntity.SCIENTIFIC_NAMES, CreaturesEntities.ROLLER, CreaturesItems.ROLLER_SPAWN_EGG, Component.translatable("entity.creatures.roller"), RollerEntity.REGIONS),
            new SpeciesEntry("stellersseaeagle", 5, SeaEagleEntity.SPECIES_NAMES, SeaEagleEntity.SCIENTIFIC_NAMES, CreaturesEntities.SEA_EAGLE, CreaturesItems.STELLERS_SEA_EAGLE_SPAWN_EGG, Component.translatable("entity.creatures.stellers_sea_eagle"), SeaEagleEntity.REGIONS),
            new SpeciesEntry("seagull", 9, SeagullEntity.SPECIES_NAMES, SeagullEntity.SCIENTIFIC_NAMES, CreaturesEntities.SEAGULL, CreaturesItems.SEAGULL_SPAWN_EGG, Component.translatable("entity.creatures.seagull"), SeagullEntity.REGIONS),
            new SpeciesEntry("secretarybird", 1, Map.of(), Map.of(), CreaturesEntities.SECRETARYBIRD, CreaturesItems.SECRETARYBIRD_SPAWN_EGG, Component.translatable("entity.creatures.secretarybird"), SecretaryBirdEntity.REGIONS),
            new SpeciesEntry("shoebill", 3, Map.of(), Map.of(), CreaturesEntities.SHOEBILL, CreaturesItems.SHOEBILL_SPAWN_EGG, Component.translatable("entity.creatures.shoebill"), ShoebillEntity.REGIONS),
            new SpeciesEntry("skua", 4, SkuaEntity.SPECIES_NAMES, SkuaEntity.SCIENTIFIC_NAMES, CreaturesEntities.SKUA, CreaturesItems.SKUA_SPAWN_EGG, Component.translatable("entity.creatures.skua"), SkuaEntity.REGIONS),
            new SpeciesEntry("sparrow", 6, SparrowEntity.SPECIES_NAMES, SparrowEntity.SCIENTIFIC_NAMES, CreaturesEntities.SPARROW, CreaturesItems.SPARROW_SPAWN_EGG, Component.translatable("entity.creatures.sparrow"), SparrowEntity.REGIONS),
            new SpeciesEntry("spoonbill", 6, SpoonbillEntity.SPECIES_NAMES, SpoonbillEntity.SCIENTIFIC_NAMES, CreaturesEntities.SPOONBILL, CreaturesItems.SPOONBILL_SPAWN_EGG, Component.translatable("entity.creatures.spoonbill"), SpoonbillEntity.REGIONS),
            new SpeciesEntry("starling", 7, StarlingEntity.SPECIES_NAMES, StarlingEntity.SCIENTIFIC_NAMES, CreaturesEntities.STARLING, CreaturesItems.STARLING_SPAWN_EGG, Component.translatable("entity.creatures.starling"), StarlingEntity.REGIONS),
            new SpeciesEntry("stilt", 6, StiltEntity.SPECIES_NAMES, StiltEntity.SCIENTIFIC_NAMES, CreaturesEntities.STILT, CreaturesItems.STILT_SPAWN_EGG, Component.translatable("entity.creatures.stilt"), StiltEntity.REGIONS),
            new SpeciesEntry("stork", 9, StorkEntity.SPECIES_NAMES, StorkEntity.SCIENTIFIC_NAMES, CreaturesEntities.STORK, CreaturesItems.STORK_SPAWN_EGG, Component.translatable("entity.creatures.stork"), StorkEntity.REGIONS),
            new SpeciesEntry("swallow", 5, SwallowEntity.SPECIES_NAMES, SwallowEntity.SCIENTIFIC_NAMES, CreaturesEntities.SWALLOW, CreaturesItems.SWALLOW_SPAWN_EGG, Component.translatable("entity.creatures.swallow"), SwallowEntity.REGIONS),
            new SpeciesEntry("tanager", 9, TanagerEntity.SPECIES_NAMES, TanagerEntity.SCIENTIFIC_NAMES, CreaturesEntities.TANAGER, CreaturesItems.TANAGER_SPAWN_EGG, Component.translatable("entity.creatures.tanager"), TanagerEntity.REGIONS),
            new SpeciesEntry("whistlingduck", 4, WhistlingDuckEntity.SPECIES_NAMES, WhistlingDuckEntity.SCIENTIFIC_NAMES, CreaturesEntities.WHISTLING_DUCK, CreaturesItems.WHISTLINGDUCK_SPAWN_EGG, Component.translatable("entity.creatures.whistlingduck"), WhistlingDuckEntity.REGIONS),
            new SpeciesEntry("wildduck", 13, WildDuckEntity.SPECIES_NAMES, WildDuckEntity.SCIENTIFIC_NAMES, CreaturesEntities.WILD_DUCK, CreaturesItems.WILD_DUCK_SPAWN_EGG, Component.translatable("entity.creatures.wild_duck"), WildDuckEntity.REGIONS),
            new SpeciesEntry("woodduck", 1, Map.of(), Map.of(), CreaturesEntities.WOOD_DUCK, CreaturesItems.WOOD_DUCK_SPAWN_EGG, Component.translatable("entity.creatures.woodduck"), WoodDuckEntity.REGIONS),
            new SpeciesEntry("yelloweyedpenguin", 1, Map.of(), Map.of(), CreaturesEntities.YELLOW_EYED_PENGUIN, CreaturesItems.YELLOW_EYED_PENGUIN_SPAWN_EGG, Component.translatable("entity.creatures.yelloweyedpenguin"), YellowEyedPenguinEntity.REGIONS),

            // fish
            new SpeciesEntry("arapaima", 3, Map.of(), Map.of(), CreaturesEntities.ARAPAIMA, CreaturesItems.ARAPAIMA_SPAWN_EGG, Component.translatable("entity.creatures.arapaima"), ArapaimaEntity.REGIONS),
            new SpeciesEntry("arowana", 3, Map.of(), Map.of(), CreaturesEntities.AROWANA, CreaturesItems.AROWANA_SPAWN_EGG, Component.translatable("entity.creatures.arowana"), ArowanaEntity.REGIONS),
            new SpeciesEntry("barracuda", 3, BarracudaEntity.SPECIES_NAMES, BarracudaEntity.SCIENTIFIC_NAMES, CreaturesEntities.BARRACUDA, CreaturesItems.BARRACUDA_SPAWN_EGG, Component.translatable("entity.creatures.barracuda")),
            new SpeciesEntry("bluetang", 10, BlueTangEntity.SPECIES_NAMES, BlueTangEntity.SCIENTIFIC_NAMES, CreaturesEntities.BLUE_TANG, CreaturesItems.BLUE_TANG_SPAWN_EGG, Component.translatable("entity.creatures.blue_tang")),
            new SpeciesEntry("clownfish", 8, ClownfishEntity.SPECIES_NAMES, ClownfishEntity.SCIENTIFIC_NAMES, CreaturesEntities.CLOWNFISH, CreaturesItems.CLOWNFISH_SPAWN_EGG, Component.translatable("entity.creatures.clownfish")),
            new SpeciesEntry("dottyback", 4, DottybackEntity.SPECIES_NAMES, DottybackEntity.SCIENTIFIC_NAMES, CreaturesEntities.DOTTYBACK, CreaturesItems.DOTTYBACK_SPAWN_EGG, Component.translatable("entity.creatures.dottyback")),
            new SpeciesEntry("elephantnose", 4, Map.of(), Map.of(), CreaturesEntities.ELEPHANTNOSE, CreaturesItems.ELEPHANTNOSE_SPAWN_EGG, Component.translatable("entity.creatures.elephantnose"), ElephantNoseFishEntity.REGIONS),
            new SpeciesEntry("firegoby", 1, Map.of(), Map.of(), CreaturesEntities.FIRE_GOBY, CreaturesItems.FIRE_GOBY_SPAWN_EGG, Component.translatable("entity.creatures.fire_goby")),
            new SpeciesEntry("flameangelfish", 1, Map.of(), Map.of(), CreaturesEntities.FLAME_ANGELFISH, CreaturesItems.FLAME_ANGELFISH_SPAWN_EGG, Component.translatable("entity.creatures.flame_angelfish")),
            new SpeciesEntry("goldfish", 8, Map.of(), Map.of(), CreaturesEntities.GOLDFISH, CreaturesItems.GOLDFISH_SPAWN_EGG, Component.translatable("entity.creatures.goldfish"), GoldfishEntity.REGIONS),
            new SpeciesEntry("gourami", 5, GouramiEntity.SPECIES_NAMES, GouramiEntity.SCIENTIFIC_NAMES, CreaturesEntities.GOURAMI, CreaturesItems.GOURAMI_SPAWN_EGG, Component.translatable("entity.creatures.gourami"), GouramiEntity.REGIONS),
            new SpeciesEntry("guppy", 6, GuppyEntity.SPECIES_NAMES, Map.of(), CreaturesEntities.GUPPY, CreaturesItems.GUPPY_SPAWN_EGG, Component.translatable("entity.creatures.guppy"), GuppyEntity.REGIONS),
            new SpeciesEntry("koi", 9, Map.of(), Map.of(), CreaturesEntities.KOI, CreaturesItems.KOI_SPAWN_EGG, Component.translatable("entity.creatures.koi"), KoiEntity.REGIONS),
            new SpeciesEntry("lookdown", 2, LookdownEntity.SPECIES_NAMES, Map.of(), CreaturesEntities.LOOKDOWN, CreaturesItems.LOOKDOWN_SPAWN_EGG, Component.translatable("entity.creatures.lookdown")),
            new SpeciesEntry("lungfish", 4, LungfishEntity.SPECIES_NAMES, LungfishEntity.SCIENTIFIC_NAMES, CreaturesEntities.LUNGFISH, CreaturesItems.LUNGFISH_SPAWN_EGG, Component.translatable("entity.creatures.lungfish"), LungfishEntity.REGIONS),
            new SpeciesEntry("mantisshrimp", 6, MantisShrimpEntity.SPECIES_NAMES, Map.of(), CreaturesEntities.MANTIS_SHRIMP, CreaturesItems.MANTIS_SHRIMP_SPAWN_EGG, Component.translatable("entity.creatures.mantisshrimp")),
            new SpeciesEntry("parrotfish", 12, ParrotfishEntity.SPECIES_NAMES, ParrotfishEntity.SCIENTIFIC_NAMES, CreaturesEntities.PARROTFISH, CreaturesItems.PARROTFISH_SPAWN_EGG, Component.translatable("entity.creatures.parrotfish")),
            new SpeciesEntry("pike", 1, Map.of(), Map.of(), CreaturesEntities.PIKE, CreaturesItems.PIKE_SPAWN_EGG, Component.translatable("entity.creatures.pike"), PikeEntity.REGIONS),
            new SpeciesEntry("piranha", 5, PiranhaEntity.SPECIES_NAMES, PiranhaEntity.SCIENTIFIC_NAMES, CreaturesEntities.PIRANHA, CreaturesItems.PIRANHA_SPAWN_EGG, Component.translatable("entity.creatures.piranha"), PiranhaEntity.REGIONS),
            new SpeciesEntry("ranchu", 4, Map.of(), Map.of(), CreaturesEntities.RANCHU, CreaturesItems.RANCHU_SPAWN_EGG, Component.translatable("entity.creatures.ranchu"), RanchuEntity.REGIONS),
            new SpeciesEntry("redsnapper", 2, RedSnapperEntity.SPECIES_NAMES, Map.of(), CreaturesEntities.RED_SNAPPER, CreaturesItems.RED_SNAPPER_SPAWN_EGG, Component.translatable("entity.creatures.red_snapper")),
            new SpeciesEntry("sawfish", 5, SawfishEntity.SPECIES_NAMES, Map.of(), CreaturesEntities.SAWFISH, CreaturesItems.SAWFISH_SPAWN_EGG, Component.translatable("entity.creatures.sawfish")),
            new SpeciesEntry("seadragon", 3, SeaDragonEntity.SPECIES_NAMES, SeaDragonEntity.SCIENTIFIC_NAMES, CreaturesEntities.SEADRAGON, CreaturesItems.SEADRAGON_SPAWN_EGG, Component.translatable("entity.creatures.seadragon")),
            new SpeciesEntry("shrimp", 10, ShrimpEntity.SPECIES_NAMES, Map.of(), CreaturesEntities.SHRIMP, CreaturesItems.SHRIMP_SPAWN_EGG, Component.translatable("entity.creatures.shrimp"), ShrimpEntity.REGIONS),
            new SpeciesEntry("squid", 10, SquidEntity.SPECIES_NAMES, SquidEntity.SCIENTIFIC_NAMES, CreaturesEntities.SQUID, CreaturesItems.SQUID_SPAWN_EGG, Component.translatable("entity.creatures.squid")),
            new SpeciesEntry("stingray", 15, StingrayEntity.SPECIES_NAMES, StingrayEntity.SCIENTIFIC_NAMES, CreaturesEntities.STINGRAY, CreaturesItems.STINGRAY_SPAWN_EGG, Component.translatable("entity.creatures.stingray")),
            new SpeciesEntry("swordfish", 9, SwordfishEntity.SPECIES_NAMES, SwordfishEntity.SCIENTIFIC_NAMES, CreaturesEntities.SWORDFISH, CreaturesItems.SWORDFISH_SPAWN_EGG, Component.translatable("entity.creatures.swordfish")),
            new SpeciesEntry("tambaqui", 6, Map.of(), Map.of(), CreaturesEntities.TAMBAQUI, CreaturesItems.TAMBAQUI_SPAWN_EGG, Component.translatable("entity.creatures.tambaqui"), TambaquiEntity.REGIONS),
            new SpeciesEntry("tetra", 7, TetraEntity.SPECIES_NAMES, TetraEntity.SCIENTIFIC_NAMES, CreaturesEntities.TETRA, CreaturesItems.TETRA_SPAWN_EGG, Component.translatable("entity.creatures.tetra"), TetraEntity.REGIONS),
            new SpeciesEntry("tigerbarb", 2, TigerBarbEntity.SPECIES_NAMES, TigerBarbEntity.SCIENTIFIC_NAMES, CreaturesEntities.TIGERBARB, CreaturesItems.TIGERBARB_SPAWN_EGG, Component.translatable("entity.creatures.tigerbarb"), TigerBarbEntity.REGIONS),
            new SpeciesEntry("trout", 4, TroutEntity.SPECIES_NAMES, TroutEntity.SCIENTIFIC_NAMES, CreaturesEntities.TROUT, CreaturesItems.TROUT_SPAWN_EGG, Component.translatable("entity.creatures.trout"), TroutEntity.REGIONS),
            new SpeciesEntry("trumpetfish", 3, TrumpetfishEntity.SPECIES_NAMES, Map.of(), CreaturesEntities.TRUMPETFISH, CreaturesItems.TRUMPETFISH_SPAWN_EGG, Component.translatable("entity.creatures.trumpetfish")),

            // crabs + tarantula
            new SpeciesEntry("ghostcrab", 4, GhostCrabEntity.SPECIES_NAMES, Map.of(), CreaturesEntities.GHOST_CRAB, CreaturesItems.GHOST_CRAB_SPAWN_EGG, Component.translatable("entity.creatures.ghostcrab")),
            new SpeciesEntry("fiddlercrab", 5, FiddlerCrabEntity.SPECIES_NAMES, FiddlerCrabEntity.SCIENTIFIC_NAMES, CreaturesEntities.FIDDLER_CRAB, CreaturesItems.FIDDLER_CRAB_SPAWN_EGG, Component.translatable("entity.creatures.fiddlercrab"), FiddlerCrabEntity.REGIONS),
            new SpeciesEntry("vampirecrab", 5, Map.of(), Map.of(), CreaturesEntities.VAMPIRECRAB, CreaturesItems.VAMPIRE_CRAB_SPAWN_EGG, Component.translatable("entity.creatures.vampirecrab"), VampireCrabEntity.REGIONS),
            new SpeciesEntry("ediblecrab", 4, EdibleCrabEntity.SPECIES_NAMES, EdibleCrabEntity.SCIENTIFIC_NAMES, CreaturesEntities.EDIBLE_CRAB, CreaturesItems.EDIBLE_CRAB_SPAWN_EGG, Component.translatable("entity.creatures.ediblecrab"), EdibleCrabEntity.REGIONS),
            new SpeciesEntry("tarantula", 15, TarantulaEntity.SPECIES_NAMES, TarantulaEntity.SCIENTIFIC_NAMES, CreaturesEntities.TARANTULA, CreaturesItems.TARANTULA_SPAWN_EGG, Component.translatable("entity.creatures.tarantula"), TarantulaEntity.REGIONS)
    );
    public static long lastQuizDay = -1;
    public static boolean quizCompletedToday = false;
    private Button regionButton;
    private Button iucnButton;

    public FieldGuideGUI() {
        super(Component.translatable("creatures_fieldgui"));
    }

    private static final int CELL_SIZE = 40;
    private static final ResourceLocation BOOK_TEXTURE = new ResourceLocation("creatures:textures/gui/creatures/book.png");
    private int currentPage = 0;
    private String searchQuery = "";
    private net.minecraft.client.gui.components.EditBox searchBox;

    private List<Integer> getFilteredIndices(FieldGuideCapability cap) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < ALL_SPECIES.size(); i++) {
            SpeciesEntry species = ALL_SPECIES.get(i);
            if (!searchQuery.isEmpty()) {
                // only show discovered when searching
                if (cap == null || !cap.hasDiscoveredAny(species.entityKey)) continue;
                boolean matches = species.displayName.getString().toLowerCase().contains(searchQuery);
                if (!matches) {
                    for (int v = 1; v <= species.totalVariants; v++) {
                        if (species.getSpeciesName(v).toLowerCase().contains(searchQuery)) {
                            matches = true;
                            break;
                        }
                    }
                }
                if (!matches) continue;
            }
            indices.add(i);
        }
        return indices;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        int bookW = 390, bookH = 245;
        int bookX = (this.width - bookW) / 2;
        int bookY = (this.height - bookH) / 2;

        int cellSize = 40;
        int colsPerPage = 3;
        int leftPageX  = bookX + 55;
        int rightPageX = bookX + 200;
        int pageY      = bookY + 35;
        int pageH      = bookH - 65;
        int visibleRows = pageH / cellSize;
        int itemsPerPage = colsPerPage * 2 * visibleRows;

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        graphics.blit(BOOK_TEXTURE, bookX, bookY, 0, 0, bookW, bookH, bookW, bookW);

        FieldGuideCapability cap = Minecraft.getInstance().player
                .getCapability(FieldGuideCapability.CAPABILITY).orElse(null);

        Component hoveredTooltip = null;
        int tooltipX = 0, tooltipY = 0;

        if (currentPage == 0) {
            regionButton.visible = true;
            iucnButton.visible = true;
            if (searchBox != null) searchBox.visible = false;

            int centerX = rightPageX + bookW / 2 - 120;
            int centerY = pageY + bookH / 2 - 30;

            graphics.pose().pushPose();
            graphics.pose().translate(centerX, centerY - 55, 0);
            graphics.pose().scale(1.5f, 1.5f, 1.5f);
            float x = -font.width("Frikinzi's Fauna") / 2.0f;
            font.drawInBatch("Frikinzi's Fauna", x, 0, 0x3D2B1F, false,
                    graphics.pose().last().pose(), graphics.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            graphics.pose().popPose();

            graphics.pose().pushPose();
            graphics.pose().translate(centerX, centerY - 35, 0);
            graphics.pose().scale(1.5f, 1.5f, 1.5f);
            x = -font.width(Component.translatable("creatures.fieldgui.fieldguide").getString()) / 2.0f;
            font.drawInBatch(Component.translatable("creatures.fieldgui.fieldguide").getString(), x, 0, 0x3D2B1F, false,
                    graphics.pose().last().pose(), graphics.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            graphics.pose().popPose();

            int totalSpecies = ALL_SPECIES.stream().mapToInt(s -> s.totalVariants).sum();
            int discoveredSpecies = 0;
            if (cap != null) {
                discoveredSpecies = (int) cap.getAll().stream()
                        .filter(key -> ALL_SPECIES.stream().anyMatch(s -> key.startsWith(s.entityKey + "_")))
                        .map(key -> {
                            String[] parts = key.split("_");
                            return parts.length >= 3 ? parts[0] + "_" + parts[1] : key;
                        })
                        .distinct().count();
            }

            graphics.pose().pushPose();
            graphics.pose().translate(centerX, centerY, 0);
            graphics.pose().scale(1f, 1f, 0.8f);
            x = -font.width(discoveredSpecies + " / " + totalSpecies + " " + Component.translatable("creatures.fieldgui.discover").getString()) / 2.0f;
            font.drawInBatch(discoveredSpecies + " / " + totalSpecies + " " + Component.translatable("creatures.fieldgui.discover").getString(), x, 0, 0x3D2B1F, false,
                    graphics.pose().last().pose(), graphics.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            graphics.pose().popPose();

            int barWidth = 120, barHeight = 8;
            int barX = centerX - barWidth / 2;
            int barY = centerY + 15;
            graphics.fill(barX, barY, barX + barWidth, barY + barHeight, 0x55000000);
            int fillWidth = (int) ((float) discoveredSpecies / totalSpecies * barWidth);
            graphics.fill(barX, barY, barX + fillWidth, barY + barHeight, 0xFF5C8A3C);
            graphics.fill(barX, barY, barX + barWidth, barY + 1, 0x88000000);
            graphics.fill(barX, barY + barHeight - 1, barX + barWidth, barY + barHeight, 0x88000000);
            graphics.fill(barX, barY, barX + 1, barY + barHeight, 0x88000000);
            graphics.fill(barX + barWidth - 1, barY, barX + barWidth, barY + barHeight, 0x88000000);

            int introY = bookY + 155;
            String[] lines = {
                    Component.translatable("creatures.fieldgui.rightclickintro").getString(),
                    Component.translatable("creatures.fieldgui.spyglassintro").getString(),
                    Component.translatable("creatures.fieldgui.clickanimalintro").getString(),
                    Component.translatable("creatures.fieldgui.takedailyquiz").getString()
            };
            float scale = 0.7f;
            for (String line : lines) {
                graphics.pose().pushPose();
                graphics.pose().translate(rightPageX, introY, 0);
                graphics.pose().scale(scale, scale, 1f);
                font.drawInBatch(line, 0, 0, 0x5C4033, false,
                        graphics.pose().last().pose(), graphics.bufferSource(),
                        Font.DisplayMode.NORMAL, 0, 15728880);
                graphics.pose().popPose();
                introY += (int)(10 * scale);
            }

            int imgSize = 64;
            int imgX = bookX + 50;
            int imgY = bookY + 80;
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
            graphics.blit(new ResourceLocation("creatures:textures/painting/fischers.png"), imgX, imgY, 0, 0, imgSize, imgSize, imgSize, imgSize);
            graphics.blit(new ResourceLocation("creatures:textures/painting/victoria_crowned.png"), imgX + 70, imgY + 10, 0, 0, imgSize, imgSize, imgSize, imgSize);

            super.render(graphics, mouseX, mouseY, partialTick);
            return;
        } else {
            regionButton.visible = false;
            iucnButton.visible = false;
            if (searchBox != null) searchBox.visible = true;
        }

        // ✅ Filtered + paginated species loop
        List<Integer> filtered = getFilteredIndices(cap);
        int startIdx = (currentPage - 1) * itemsPerPage;
        int endIdx = Math.min(startIdx + itemsPerPage, filtered.size());

        for (int idx = startIdx; idx < endIdx; idx++) {
            int posOnPage = idx - startIdx;
            int speciesIndex = filtered.get(idx);
            SpeciesEntry species = ALL_SPECIES.get(speciesIndex);

            int globalCol = posOnPage % (colsPerPage * 2);
            boolean isRight = globalCol >= colsPerPage;
            int col = globalCol % colsPerPage;
            int x = (isRight ? rightPageX : leftPageX) + col * cellSize;
            int row = posOnPage / (colsPerPage * 2);
            int y = pageY + row * cellSize;

            boolean anyDiscovered = cap != null && cap.hasDiscoveredAny(species.entityKey);

            if (anyDiscovered) {
                LivingEntity dummy = (LivingEntity) species.entityType.get().create(Minecraft.getInstance().level);
                if (dummy != null) {
                    Quaternionf rotation = new Quaternionf().rotateZ((float) Math.PI).rotateY((float) Math.toRadians(140));
                    int scale = (int) (25.0f / dummy.getBbHeight());
                    int offset = 0;

                    if ((dummy instanceof CreaturesBirdEntity bird) && cap != null) {
                        List<int[]> discovered = new ArrayList<>();
                        for (int v : cap.getDiscoveredVariants(species.entityKey)) {
                            for (String g : cap.getDiscoveredGenders(species.entityKey, v)) {
                                discovered.add(new int[]{v, g.equals("m") ? 1 : 0});
                            }
                        }
                        if (!discovered.isEmpty()) {
                            int tick = (int)(System.currentTimeMillis() / 5000) % discovered.size();
                            int[] entry = discovered.get(tick);
                            bird.setVariant(entry[0]);
                            bird.setGender(entry[1]);
                            rotation = bird.getRotforGUI();
                            scale = bird.getScaleforGUI();
                            offset = bird.getYOffsetForGUI();
                        }
                        bird.setOnGround(true);
                    }
                    if ((dummy instanceof FishBase fish) && cap != null) {
                        Set<Integer> variants = cap.getDiscoveredVariants(species.entityKey);
                        if (!variants.isEmpty()) {
                            List<Integer> variantList = new ArrayList<>(variants);
                            int tick = (int)(System.currentTimeMillis() / 5000) % variantList.size();
                            fish.setVariant(variantList.get(tick));
                            fish.setAirSupply(300);
                            fish.setForcedInWater(true);
                            rotation = fish.getRotforGUI();
                            scale = fish.getScaleforGUI();
                            offset = fish.getYOffsetForGUI();
                        }
                        fish.setOnGround(true);
                    }
                    if ((dummy instanceof AbstractCrabBase crab) && cap != null) {
                        Set<Integer> variants = cap.getDiscoveredVariants(species.entityKey);
                        if (!variants.isEmpty()) {
                            List<Integer> variantList = new ArrayList<>(variants);
                            int tick = (int)(System.currentTimeMillis() / 5000) % variantList.size();
                            crab.setVariant(variantList.get(tick));
                            rotation = crab.getRotforGUI();
                            scale = crab.getScaleforGUI();
                            offset = crab.getYOffsetForGUI();
                        }
                        crab.setOnGround(true);
                    }
                    InventoryScreen.renderEntityInInventory(graphics,
                            x + cellSize / 2, y + cellSize - 5 + offset, scale, rotation, null, dummy);
                }
            } else {
                graphics.fill(x, y, x + CELL_SIZE, y + CELL_SIZE, 0xBB888888);
                graphics.drawString(font, "?", x + 12, y + 11, 0xFFFFFF, true);
                graphics.fill(x, y, x + CELL_SIZE, y + 1, 0x55000000);
                graphics.fill(x, y, x + 1, y + CELL_SIZE, 0x55000000);
                graphics.fill(x, y + CELL_SIZE - 1, x + CELL_SIZE, y + CELL_SIZE, 0x55000000);
                graphics.fill(x + CELL_SIZE - 1, y, x + CELL_SIZE, y + CELL_SIZE, 0x55000000);
                if (mouseX >= x && mouseX <= x + CELL_SIZE && mouseY >= y && mouseY <= y + CELL_SIZE) {
                    graphics.renderTooltip(font, Component.literal("???"), mouseX, mouseY);
                }
            }

            if (mouseX >= x && mouseX <= x + cellSize && mouseY >= y && mouseY <= y + cellSize) {
                hoveredTooltip = anyDiscovered
                        ? Component.literal(species.displayName.getString())
                        : Component.literal("???");
                tooltipX = mouseX;
                tooltipY = mouseY;
            }
        }

        if (hoveredTooltip != null) {
            graphics.renderTooltip(font, hoveredTooltip, tooltipX, tooltipY);
        }

        int totalPages = getTotalPages(colsPerPage, visibleRows, filtered.size());
        graphics.drawString(font, currentPage + " / " + (totalPages - 1),
                bookX + bookW / 2 - 10, bookY + bookH - 18, 0x3D2B1F, false);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (currentPage == 0) return super.mouseClicked(mouseX, mouseY, button);
        FieldGuideCapability cap = Minecraft.getInstance().player
                .getCapability(FieldGuideCapability.CAPABILITY).orElse(null);
        if (cap == null) return super.mouseClicked(mouseX, mouseY, button);

        int bookW = 390, bookH = 245;
        int bookX = (this.width - bookW) / 2;
        int bookY = (this.height - bookH) / 2;

        int cellSize = 40, colsPerPage = 3;
        int leftPageX = bookX + 55, rightPageX = bookX + 200;
        int pageY = bookY + 35, pageH = bookH - 65;
        int visibleRows = pageH / cellSize;
        int itemsPerPage = colsPerPage * 2 * visibleRows;

        List<Integer> filtered = getFilteredIndices(cap);
        int startIdx = (currentPage - 1) * itemsPerPage;
        int endIdx = Math.min(startIdx + itemsPerPage, filtered.size());

        for (int idx = startIdx; idx < endIdx; idx++) {
            int posOnPage = idx - startIdx;
            int speciesIndex = filtered.get(idx);
            SpeciesEntry species = ALL_SPECIES.get(speciesIndex);

            int globalCol = posOnPage % (colsPerPage * 2);
            boolean isRight = globalCol >= colsPerPage;
            int col = globalCol % colsPerPage;
            int x = (isRight ? rightPageX : leftPageX) + col * cellSize;
            int row = posOnPage / (colsPerPage * 2);
            int y = pageY + row * cellSize;

            if (mouseX >= x && mouseX <= x + cellSize && mouseY >= y && mouseY <= y + cellSize) {
                if (cap.hasDiscoveredAny(species.entityKey)) {
                    Minecraft.getInstance().setScreen(
                            new SpeciesVariantScreen(species, cap.getDiscoveredVariants(species.entityKey), this));
                }
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        FieldGuideCapability cap = Minecraft.getInstance().player
                .getCapability(FieldGuideCapability.CAPABILITY).orElse(null);
        int cellSize = 40, colsPerPage = 3, pageH = 245 - 65;
        int visibleRows = pageH / cellSize;
        List<Integer> filtered = getFilteredIndices(cap);
        int totalPages = getTotalPages(colsPerPage, visibleRows, filtered.size());
        if (delta < 0 && currentPage < totalPages - 1) currentPage++;
        else if (delta > 0 && currentPage > 0) currentPage--;
        return true;
    }

    private int getTotalPages(int colsPerPage, int visibleRows, int filteredSize) {
        int itemsPerPage = colsPerPage * 2 * visibleRows;
        return (int) Math.ceil((double) filteredSize / itemsPerPage) + 1;
    }

    private int getTotalPages(int colsPerPage, int visibleRows) {
        return getTotalPages(colsPerPage, visibleRows, ALL_SPECIES.size());
    }

    @Override
    protected void init() {
        super.init();
        int bookW = 390, bookH = 245;
        int bookX = (this.width - bookW) / 2;
        int bookY = (this.height - bookH) / 2;

        this.addRenderableWidget(Button.builder(Component.literal("◀"), b -> {
            if (currentPage > 0) currentPage--;
        }).pos(bookX + 10, bookY + bookH - 40).size(20, 20).build());

        this.addRenderableWidget(Button.builder(Component.literal("▶"), b -> {
            FieldGuideCapability cap = Minecraft.getInstance().player
                    .getCapability(FieldGuideCapability.CAPABILITY).orElse(null);
            int cellSize = 40, colsPerPage = 3, pageH = bookH - 65;
            int visibleRows = pageH / cellSize;
            List<Integer> filtered = getFilteredIndices(cap);
            int totalPages = getTotalPages(colsPerPage, visibleRows, filtered.size());
            if (currentPage < totalPages - 1) currentPage++;
        }).pos(bookX + bookW - 30, bookY + bookH - 40).size(20, 20).build());

        Button quizButton = Button.builder(Component.literal(
                Component.translatable("creatures.fieldgui.dailyquiz").getString()), b -> {
            FieldGuideCapability cap = Minecraft.getInstance().player
                    .getCapability(FieldGuideCapability.CAPABILITY).orElse(null);
            if (cap != null) {
                DailyQuizScreen quiz = DailyQuizScreen.create(this, cap);
                if (quiz != null) Minecraft.getInstance().setScreen(quiz);
            }
        }).pos(bookX + bookW / 2 - 40, bookY + bookH - 40).size(80, 20).build();
        quizButton.active = FieldGuideGUI.canShowQuiz();
        this.addRenderableWidget(quizButton);

        regionButton = Button.builder(Component.literal(
                                Component.translatable("creatures.fieldgui.bycontinent").getString()),
                        b -> Minecraft.getInstance().setScreen(new RegionSelectScreen(this)))
                .pos(bookX + 40, bookY + 40).size(70, 30).build();
        this.addRenderableWidget(regionButton);

        iucnButton = Button.builder(Component.translatable("creatures.fieldgui.iucn"),
                        b -> Minecraft.getInstance().setScreen(new IUCNSelectScreen(this)))
                .pos(bookX + 120, bookY + 40).size(70, 30).build();
        this.addRenderableWidget(iucnButton);

        searchBox = new net.minecraft.client.gui.components.EditBox(
                font,
                bookX + 55, bookY + 18, 125, 12,
                Component.translatable("creatures.fieldgui.search"));
        searchBox.setMaxLength(30);
        searchBox.setHint(Component.translatable("creatures.fieldgui.search"));
        searchBox.setResponder(query -> {
            searchQuery = query.toLowerCase();
            currentPage = 1;
        });
        searchBox.visible = false;
        this.addRenderableWidget(searchBox);
    }

    private long getCurrentMinecraftDay() {
        return Minecraft.getInstance().level.getDayTime() / 24000L;
    }

    public static boolean canShowQuiz() {
        if (!net.minecraftforge.fml.loading.FMLLoader.isProduction()) return true;
        if (!quizCompletedToday) return true;
        long currentDay = Minecraft.getInstance().level.getDayTime() / 24000L;
        if (currentDay != lastQuizDay) {
            quizCompletedToday = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean isPauseScreen() { return false; }
}
