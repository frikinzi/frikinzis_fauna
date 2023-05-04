package com.frikinzi.creatures.util;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.*;
import com.frikinzi.creatures.entity.base.AbstractCreaturesFish;
import com.frikinzi.creatures.entity.base.AbstractWalkingCreature;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.egg.RoeEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.shadowed.eliotlash.mclib.math.functions.classic.Pi;

@Mod.EventBusSubscriber(modid = Creatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
    private static final BiMap<Integer, EntityType<? extends CreaturesBirdEntity>> birdEntityMap = HashBiMap.create();
    private static final BiMap<Integer, EntityType<? extends AbstractCreaturesFish>> fishEntityMap = HashBiMap.create();

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        SpawnPlacements.register(CreaturesEntities.LOVEBIRD.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, LovebirdEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SPOONBILL.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, SpoonbillEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.KAKAPO.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, KakapoEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.MANDARIN_DUCK.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, MandarinDuckEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.RAVEN.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, RavenEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.DOVE.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, DoveEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.RED_KITE.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, RedKiteEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.GOLDEN_EAGLE.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, GoldenEagleEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.STELLERS_SEA_EAGLE.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, StellersSeaEagleEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.GYRFALCON.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, GyrfalconEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.LORIKEET.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, LorikeetEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.CONURE.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, ConureEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.FAIRYWREN.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, FairywrenEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PYGMY_FALCON.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, PygmyFalconEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.BARN_OWL.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, BarnOwlEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.WILD_DUCK.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, WildDuckEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.ROLLER.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, RollerEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.CHICKADEE.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, ChickadeeEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PYGMY_GOOSE.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, PygmyGooseEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SWALLOW.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, SwallowEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PEAFOWL.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, PeafowlEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SPARROW.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, SparrowEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.BUSHTIT.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, BushtitEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.LAUGHINGTHRUSH.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, LaughingthrushEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.EAGLEOWL.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, EagleOwlEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.ROBIN.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, RobinEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.MAGPIE.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, MagpieEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.GOOSE.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, GooseEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.OSPREY.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, OspreyEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.KINGFISHER.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, KingfisherEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PELICAN.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, PelicanEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.LAPWING.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, LapwingEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SKUA.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, SkuaEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.BUNTING.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, BuntingEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.MONAL.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, MonalEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.TANAGER.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, TanagerEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.FINCH.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, FinchEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.CAPERCAILLIE.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, CapercaillieEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PHEASANT.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, PheasantEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.STORK.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, StorkEntity::checkBirdSpawnRules);

        SpawnPlacements.register(CreaturesEntities.KOI.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, KoiEntity::checkSpawnRules);
        SpawnPlacements.register(CreaturesEntities.DOTTYBACK.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DottybackEntity::checkSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PIKE.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PikeEntity::checkSpawnRules);
        SpawnPlacements.register(CreaturesEntities.GUPPY.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.AROWANA.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SHRIMP.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.GOURAMI.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.GOLDFISH.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.RANCHU.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.FIRE_GOBY.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.BLUE_TANG.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.FLAME_ANGELFISH.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.TROUT.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.RED_SNAPPER.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.TIGER_BARB.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.ARAPAIMA.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PIRANHA.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.TAMBAQUI.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.ELEPHANT_NOSE.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);

        SpawnPlacements.register(CreaturesEntities.GHOST_CRAB.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GhostCrabEntity::checkCrabSpawnRules);
        SpawnPlacements.register(CreaturesEntities.VAMPIRE_CRAB.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, VampireCrabEntity::checkCrabSpawnRules);
        SpawnPlacements.register(CreaturesEntities.FIDDLER_CRAB.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, FiddlerCrabEntity::checkCrabSpawnRules);
        SpawnPlacements.register(CreaturesEntities.TARANTULA.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, TarantulaEntity::checkCrabSpawnRules);
    }

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(CreaturesEntities.LOVEBIRD.get(), LovebirdEntity.createAttributes().build());
        event.put(CreaturesEntities.SPOONBILL.get(), SpoonbillEntity.createAttributes().build());
        event.put(CreaturesEntities.KAKAPO.get(), KakapoEntity.createAttributes().build());
        event.put(CreaturesEntities.MANDARIN_DUCK.get(), MandarinDuckEntity.createAttributes().build());
        event.put(CreaturesEntities.RAVEN.get(), RavenEntity.createAttributes().build());
        event.put(CreaturesEntities.DOVE.get(), DoveEntity.createAttributes().build());
        event.put(CreaturesEntities.RED_KITE.get(), RedKiteEntity.createAttributes().build());
        event.put(CreaturesEntities.GOLDEN_EAGLE.get(), GoldenEagleEntity.createAttributes().build());
        event.put(CreaturesEntities.STELLERS_SEA_EAGLE.get(), StellersSeaEagleEntity.createAttributes().build());
        event.put(CreaturesEntities.GYRFALCON.get(), GyrfalconEntity.createAttributes().build());
        event.put(CreaturesEntities.LORIKEET.get(), LorikeetEntity.createAttributes().build());
        event.put(CreaturesEntities.CONURE.get(), ConureEntity.createAttributes().build());
        event.put(CreaturesEntities.FAIRYWREN.get(), FairywrenEntity.createAttributes().build());
        event.put(CreaturesEntities.PYGMY_FALCON.get(), PygmyFalconEntity.createAttributes().build());
        event.put(CreaturesEntities.BARN_OWL.get(), BarnOwlEntity.createAttributes().build());
        event.put(CreaturesEntities.WILD_DUCK.get(), WildDuckEntity.createAttributes().build());
        event.put(CreaturesEntities.ROLLER.get(), RollerEntity.createAttributes().build());
        event.put(CreaturesEntities.CHICKADEE.get(), ChickadeeEntity.createAttributes().build());
        event.put(CreaturesEntities.PYGMY_GOOSE.get(), PygmyGooseEntity.createAttributes().build());
        event.put(CreaturesEntities.SWALLOW.get(), SwallowEntity.createAttributes().build());
        event.put(CreaturesEntities.IBIS.get(), IbisEntity.createAttributes().build());
        event.put(CreaturesEntities.WOOD_DUCK.get(), WoodDuckEntity.createAttributes().build());
        event.put(CreaturesEntities.PEAFOWL.get(), PeafowlEntity.createAttributes().build());
        event.put(CreaturesEntities.SPARROW.get(), SparrowEntity.createAttributes().build());
        event.put(CreaturesEntities.BUSHTIT.get(), BushtitEntity.createAttributes().build());
        event.put(CreaturesEntities.EAGLEOWL.get(), EagleOwlEntity.createAttributes().build());
        event.put(CreaturesEntities.ROBIN.get(), RobinEntity.createAttributes().build());
        event.put(CreaturesEntities.LAUGHINGTHRUSH.get(), LaughingthrushEntity.createAttributes().build());
        event.put(CreaturesEntities.MAGPIE.get(), MagpieEntity.createAttributes().build());
        event.put(CreaturesEntities.GOOSE.get(), GooseEntity.createAttributes().build());
        event.put(CreaturesEntities.OSPREY.get(), OspreyEntity.createAttributes().build());
        event.put(CreaturesEntities.KINGFISHER.get(), KingfisherEntity.createAttributes().build());
        event.put(CreaturesEntities.PELICAN.get(), PelicanEntity.createAttributes().build());
        event.put(CreaturesEntities.LAPWING.get(), LapwingEntity.createAttributes().build());
        event.put(CreaturesEntities.SKUA.get(), SkuaEntity.createAttributes().build());
        event.put(CreaturesEntities.BUNTING.get(), BuntingEntity.createAttributes().build());
        event.put(CreaturesEntities.TANAGER.get(), TanagerEntity.createAttributes().build());
        event.put(CreaturesEntities.MONAL.get(), MonalEntity.createAttributes().build());
        event.put(CreaturesEntities.FINCH.get(), FinchEntity.createAttributes().build());
        event.put(CreaturesEntities.CAPERCAILLIE.get(), CapercaillieEntity.createAttributes().build());
        event.put(CreaturesEntities.PHEASANT.get(), PheasantEntity.createAttributes().build());
        event.put(CreaturesEntities.STORK.get(), StorkEntity.createAttributes().build());

        event.put(CreaturesEntities.KOI.get(), KoiEntity.createAttributes().build());
        event.put(CreaturesEntities.DOTTYBACK.get(), DottybackEntity.createAttributes().build());
        event.put(CreaturesEntities.PIKE.get(), PikeEntity.createAttributes().build());
        event.put(CreaturesEntities.GUPPY.get(), GuppyEntity.createAttributes().build());
        event.put(CreaturesEntities.AROWANA.get(), ArowanaEntity.createAttributes().build());
        event.put(CreaturesEntities.SHRIMP.get(), ShrimpEntity.createAttributes().build());
        event.put(CreaturesEntities.GOURAMI.get(), GouramiEntity.createAttributes().build());
        event.put(CreaturesEntities.GOLDFISH.get(), GoldfishEntity.createAttributes().build());
        event.put(CreaturesEntities.RANCHU.get(), RanchuEntity.createAttributes().build());
        event.put(CreaturesEntities.FIRE_GOBY.get(), FireGobyEntity.createAttributes().build());
        event.put(CreaturesEntities.BLUE_TANG.get(), BlueTangEntity.createAttributes().build());
        event.put(CreaturesEntities.FLAME_ANGELFISH.get(), FlameAngelfishEntity.createAttributes().build());
        event.put(CreaturesEntities.TROUT.get(), TroutEntity.createAttributes().build());
        event.put(CreaturesEntities.RED_SNAPPER.get(), RedSnapperEntity.createAttributes().build());
        event.put(CreaturesEntities.TIGER_BARB.get(), TigerBarbEntity.createAttributes().build());
        event.put(CreaturesEntities.ARAPAIMA.get(), ArapaimaEntity.createAttributes().build());
        event.put(CreaturesEntities.PIRANHA.get(), PiranhaEntity.createAttributes().build());
        event.put(CreaturesEntities.TAMBAQUI.get(), TambaquiEntity.createAttributes().build());
        event.put(CreaturesEntities.ELEPHANT_NOSE.get(), ElephantNoseFishEntity.createAttributes().build());

        event.put(CreaturesEntities.GHOST_CRAB.get(), GhostCrabEntity.createAttributes().build());
        event.put(CreaturesEntities.FIDDLER_CRAB.get(), FiddlerCrabEntity.createAttributes().build());
        event.put(CreaturesEntities.VAMPIRE_CRAB.get(), VampireCrabEntity.createAttributes().build());
        event.put(CreaturesEntities.TARANTULA.get(), TarantulaEntity.createAttributes().build());

        event.put(CreaturesEntities.EGG.get(), EggEntity.createAttributes().build());
        event.put(CreaturesEntities.ROE.get(), RoeEntity.createAttributes().build());

    }

    @SubscribeEvent
    public static void birdMap(final RegistryEvent.Register<EntityType<?>> event) {
            birdEntityMap.put(0, CreaturesEntities.LOVEBIRD.get());
            birdEntityMap.put(1, CreaturesEntities.SPOONBILL.get());
            birdEntityMap.put(2, CreaturesEntities.KAKAPO.get());
            birdEntityMap.put(3, CreaturesEntities.MANDARIN_DUCK.get());
            birdEntityMap.put(4, CreaturesEntities.RAVEN.get());
            birdEntityMap.put(5, CreaturesEntities.DOVE.get());
            birdEntityMap.put(6, CreaturesEntities.RED_KITE.get());
            birdEntityMap.put(7, CreaturesEntities.GOLDEN_EAGLE.get());
            birdEntityMap.put(8, CreaturesEntities.STELLERS_SEA_EAGLE.get());
            birdEntityMap.put(9, CreaturesEntities.GYRFALCON.get());
            birdEntityMap.put(10, CreaturesEntities.LORIKEET.get());
            birdEntityMap.put(11, CreaturesEntities.CONURE.get());
            birdEntityMap.put(12, CreaturesEntities.FAIRYWREN.get());
            birdEntityMap.put(13, CreaturesEntities.PYGMY_FALCON.get());
            birdEntityMap.put(14, CreaturesEntities.BARN_OWL.get());
            birdEntityMap.put(15, CreaturesEntities.WILD_DUCK.get());
            birdEntityMap.put(16, CreaturesEntities.ROLLER.get());
            birdEntityMap.put(17, CreaturesEntities.CHICKADEE.get());
            birdEntityMap.put(18, CreaturesEntities.PYGMY_GOOSE.get());
            birdEntityMap.put(19, CreaturesEntities.SWALLOW.get());
            birdEntityMap.put(20, CreaturesEntities.IBIS.get());
            birdEntityMap.put(21, CreaturesEntities.WOOD_DUCK.get());
            birdEntityMap.put(22, CreaturesEntities.PEAFOWL.get());
            birdEntityMap.put(23, CreaturesEntities.SPARROW.get());
            birdEntityMap.put(24, CreaturesEntities.BUSHTIT.get());
            birdEntityMap.put(25, CreaturesEntities.EAGLEOWL.get());
            birdEntityMap.put(26, CreaturesEntities.ROBIN.get());
            birdEntityMap.put(27, CreaturesEntities.LAUGHINGTHRUSH.get());
            birdEntityMap.put(28, CreaturesEntities.MAGPIE.get());
            birdEntityMap.put(29, CreaturesEntities.GOOSE.get());
            birdEntityMap.put(30, CreaturesEntities.OSPREY.get());
            birdEntityMap.put(31, CreaturesEntities.KINGFISHER.get());
            birdEntityMap.put(32, CreaturesEntities.PELICAN.get());
            birdEntityMap.put(33, CreaturesEntities.LAPWING.get());
            birdEntityMap.put(34, CreaturesEntities.SKUA.get());
            birdEntityMap.put(35, CreaturesEntities.BUNTING.get());
            birdEntityMap.put(36, CreaturesEntities.MONAL.get());
            birdEntityMap.put(37, CreaturesEntities.TANAGER.get());
            birdEntityMap.put(38, CreaturesEntities.FINCH.get());
            birdEntityMap.put(39, CreaturesEntities.CAPERCAILLIE.get());
            birdEntityMap.put(40, CreaturesEntities.PHEASANT.get());
            birdEntityMap.put(41, CreaturesEntities.STORK.get());
    }

    @SubscribeEvent
    public static void fishMap(final RegistryEvent.Register<EntityType<?>> event) {
        fishEntityMap.put(1, CreaturesEntities.KOI.get());
        fishEntityMap.put(2, CreaturesEntities.DOTTYBACK.get());
        fishEntityMap.put(3, CreaturesEntities.PIKE.get());
        fishEntityMap.put(4, CreaturesEntities.GUPPY.get());
        fishEntityMap.put(5, CreaturesEntities.AROWANA.get());
        fishEntityMap.put(6, CreaturesEntities.SHRIMP.get());
        fishEntityMap.put(7, CreaturesEntities.GOURAMI.get());
        fishEntityMap.put(8, CreaturesEntities.GOLDFISH.get());
        fishEntityMap.put(9, CreaturesEntities.RANCHU.get());
        fishEntityMap.put(10, CreaturesEntities.FIRE_GOBY.get());
        fishEntityMap.put(11, CreaturesEntities.BLUE_TANG.get());
        fishEntityMap.put(12, CreaturesEntities.FLAME_ANGELFISH.get());
        fishEntityMap.put(13, CreaturesEntities.TROUT.get());
        fishEntityMap.put(14, CreaturesEntities.RED_SNAPPER.get());
        fishEntityMap.put(15, CreaturesEntities.TIGER_BARB.get());
        fishEntityMap.put(16, CreaturesEntities.ARAPAIMA.get());
        fishEntityMap.put(17, CreaturesEntities.PIRANHA.get());
        fishEntityMap.put(18, CreaturesEntities.TAMBAQUI.get());
        fishEntityMap.put(19, CreaturesEntities.ELEPHANT_NOSE.get());

    }

    public static BiMap<Integer, EntityType<? extends CreaturesBirdEntity>> getBirdEntityMap() {
        return birdEntityMap;
    }
    public static BiMap<Integer, EntityType<? extends AbstractCreaturesFish>> getFishEntityMap() {
        return fishEntityMap;
    }



}
