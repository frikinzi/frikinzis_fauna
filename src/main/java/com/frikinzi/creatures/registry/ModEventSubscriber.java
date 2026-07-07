package com.frikinzi.creatures.registry;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.client.gui.CreaturesCategories;
import com.frikinzi.creatures.client.gui.FieldGuideGUI;
import com.frikinzi.creatures.entity.*;
import com.frikinzi.creatures.entity.base.AbstractCrabBase;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.entity.egg.CreaturesRoeEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import com.frikinzi.creatures.item.FishStorageBinItem;
import com.frikinzi.creatures.player.*;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.stream.IntStream;

@Mod.EventBusSubscriber(modid = Creatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
    private static final BiMap<Integer, EntityType<? extends CreaturesBirdEntity>> birdEntityMap = HashBiMap.create();

    public static String getEntityKeyStatic(Mob mob) {
        return getEntityKey(mob);
    }
    private static SpeciesEntry getSpeciesEntryByKey(String key) {
        return SpeciesRegistry.ALL_SPECIES.stream()
                .filter(s -> s.entityKey.equals(key))
                .findFirst().orElse(null);
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {

        event.put(CreaturesEntities.LOVEBIRD.get(), LovebirdEntity.createAttributes().build());
        event.put(CreaturesEntities.SPOONBILL.get(), SpoonbillEntity.createAttributes().build());
        event.put(CreaturesEntities.KAKAPO.get(), KakapoEntity.createAttributes().build());
        event.put(CreaturesEntities.KOI.get(), KoiEntity.createAttributes().build());
        event.put(CreaturesEntities.DOTTYBACK.get(), DottybackEntity.createAttributes().build());
        event.put(CreaturesEntities.PIKE.get(), PikeEntity.createAttributes().build());
        event.put(CreaturesEntities.GUPPY.get(), GuppyEntity.createAttributes().build());
        event.put(CreaturesEntities.MANDARIN_DUCK.get(), MandarinDuckEntity.createAttributes().build());
        event.put(CreaturesEntities.AROWANA.get(), ArowanaEntity.createAttributes().build());
        event.put(CreaturesEntities.RAVEN.get(), RavenEntity.createAttributes().build());
        event.put(CreaturesEntities.SHRIMP.get(), ShrimpEntity.createAttributes().build());
        event.put(CreaturesEntities.DOVE.get(), DoveEntity.createAttributes().build());
        event.put(CreaturesEntities.RED_KITE.get(), RedKiteEntity.createAttributes().build());
        event.put(CreaturesEntities.GOLDEN_EAGLE.get(), GoldenEagleEntity.createAttributes().build());
        event.put(CreaturesEntities.SEA_EAGLE.get(), SeaEagleEntity.createAttributes().build());
        event.put(CreaturesEntities.GYRFALCON.get(), GyrfalconEntity.createAttributes().build());
        event.put(CreaturesEntities.LORIKEET.get(), LorikeetEntity.createAttributes().build());
        event.put(CreaturesEntities.CONURE.get(), ConureEntity.createAttributes().build());
        event.put(CreaturesEntities.FAIRYWREN.get(), FairywrenEntity.createAttributes().build());
        event.put(CreaturesEntities.GHOST_CRAB.get(), GhostCrabEntity.createAttributes().build());
        event.put(CreaturesEntities.GOURAMI.get(), GouramiEntity.createAttributes().build());
        event.put(CreaturesEntities.PYGMY_FALCON.get(), PygmyFalconEntity.createAttributes().build());
        event.put(CreaturesEntities.BARN_OWL.get(), BarnOwlEntity.createAttributes().build());
        event.put(CreaturesEntities.WILD_DUCK.get(), WildDuckEntity.createAttributes().build());
        event.put(CreaturesEntities.ROLLER.get(), RollerEntity.createAttributes().build());
        event.put(CreaturesEntities.GOLDFISH.get(), GoldfishEntity.createAttributes().build());
        event.put(CreaturesEntities.RANCHU.get(), RanchuEntity.createAttributes().build());
        event.put(CreaturesEntities.CHICKADEE.get(), ChickadeeEntity.createAttributes().build());
        event.put(CreaturesEntities.PYGMY_GOOSE.get(), PygmyGooseEntity.createAttributes().build());
        event.put(CreaturesEntities.FIRE_GOBY.get(), FireGobyEntity.createAttributes().build());
        event.put(CreaturesEntities.BLUE_TANG.get(), BlueTangEntity.createAttributes().build());
        event.put(CreaturesEntities.TROUT.get(), TroutEntity.createAttributes().build());
        event.put(CreaturesEntities.SWALLOW.get(), SwallowEntity.createAttributes().build());
        event.put(CreaturesEntities.FIDDLER_CRAB.get(), FiddlerCrabEntity.createAttributes().build());
        event.put(CreaturesEntities.FLAME_ANGELFISH.get(), FlameAngelfishEntity.createAttributes().build());
        event.put(CreaturesEntities.IBIS.get(), IbisEntity.createAttributes().build());
        event.put(CreaturesEntities.RED_SNAPPER.get(), RedSnapperEntity.createAttributes().build());
        event.put(CreaturesEntities.WOOD_DUCK.get(), WoodDuckEntity.createAttributes().build());
        event.put(CreaturesEntities.PEAFOWL.get(), PeafowlEntity.createAttributes().build());
        event.put(CreaturesEntities.SPARROW.get(), SparrowEntity.createAttributes().build());
        event.put(CreaturesEntities.BUSHTIT.get(), BushtitEntity.createAttributes().build());
        event.put(CreaturesEntities.EAGLEOWL.get(), EagleOwlEntity.createAttributes().build());
        event.put(CreaturesEntities.ROBIN.get(), RobinEntity.createAttributes().build());
        event.put(CreaturesEntities.MAGPIE.get(), MagpieEntity.createAttributes().build());
        event.put(CreaturesEntities.LAUGHINGTHRUSH.get(), LaughingthrushEntity.createAttributes().build());
        event.put(CreaturesEntities.GOOSE.get(), GooseEntity.createAttributes().build());
        event.put(CreaturesEntities.OSPREY.get(), OspreyEntity.createAttributes().build());
        event.put(CreaturesEntities.KINGFISHER.get(), KingfisherEntity.createAttributes().build());
        event.put(CreaturesEntities.PELICAN.get(), PelicanEntity.createAttributes().build());
        event.put(CreaturesEntities.LAPWING.get(), LapwingEntity.createAttributes().build());
        event.put(CreaturesEntities.SKUA.get(), SkuaEntity.createAttributes().build());
        event.put(CreaturesEntities.BUNTING.get(), BuntingEntity.createAttributes().build());
        event.put(CreaturesEntities.MONAL.get(), MonalEntity.createAttributes().build());
        event.put(CreaturesEntities.TANAGER.get(), TanagerEntity.createAttributes().build());
        event.put(CreaturesEntities.FINCH.get(), FinchEntity.createAttributes().build());
        event.put(CreaturesEntities.VAMPIRECRAB.get(), VampireCrabEntity.createAttributes().build());
        event.put(CreaturesEntities.TARANTULA.get(), TarantulaEntity.createAttributes().build());
        event.put(CreaturesEntities.CAPERCAILLIE.get(), CapercaillieEntity.createAttributes().build());
        event.put(CreaturesEntities.TIGERBARB.get(), TigerBarbEntity.createAttributes().build());
        event.put(CreaturesEntities.PHEASANT.get(), PheasantEntity.createAttributes().build());
        event.put(CreaturesEntities.ARAPAIMA.get(), ArapaimaEntity.createAttributes().build());
        event.put(CreaturesEntities.PIRANHA.get(), PiranhaEntity.createAttributes().build());
        event.put(CreaturesEntities.STORK.get(), StorkEntity.createAttributes().build());
        event.put(CreaturesEntities.WHISTLING_DUCK.get(), WhistlingDuckEntity.createAttributes().build());
        event.put(CreaturesEntities.GROUND_HORNBILL.get(), GroundHornbillEntity.createAttributes().build());
        event.put(CreaturesEntities.SECRETARYBIRD.get(), SecretaryBirdEntity.createAttributes().build());
        event.put(CreaturesEntities.SHOEBILL.get(), ShoebillEntity.createAttributes().build());
        event.put(CreaturesEntities.STARLING.get(), StarlingEntity.createAttributes().build());
        event.put(CreaturesEntities.TAMBAQUI.get(), TambaquiEntity.createAttributes().build());
        event.put(CreaturesEntities.ELEPHANTNOSE.get(), ElephantNoseFishEntity.createAttributes().build());
        event.put(CreaturesEntities.CORMORANT.get(), CormorantEntity.createAttributes().build());
        event.put(CreaturesEntities.STINGRAY.get(), StingrayEntity.createAttributes().build());
        event.put(CreaturesEntities.PUFFIN.get(), PuffinEntity.createAttributes().build());
        event.put(CreaturesEntities.SAWFISH.get(), SawfishEntity.createAttributes().build());
        event.put(CreaturesEntities.SEAGULL.get(), SeagullEntity.createAttributes().build());
        event.put(CreaturesEntities.SWORDFISH.get(), SwordfishEntity.createAttributes().build());
        event.put(CreaturesEntities.BOOBY.get(), BoobyEntity.createAttributes().build());
        event.put(CreaturesEntities.SQUID.get(), SquidEntity.createAttributes().build());
        event.put(CreaturesEntities.LOOKDOWN.get(), LookdownEntity.createAttributes().build());
        event.put(CreaturesEntities.BANDED_PENGUIN.get(), BandedPenguinEntity.createAttributes().build());
        event.put(CreaturesEntities.MANTIS_SHRIMP.get(), MantisShrimpEntity.createAttributes().build());
        event.put(CreaturesEntities.RAIL.get(), RailEntity.createAttributes().build());
        event.put(CreaturesEntities.BARRACUDA.get(), BarracudaEntity.createAttributes().build());
        event.put(CreaturesEntities.AVOCET.get(), AvocetEntity.createAttributes().build());
        event.put(CreaturesEntities.SEADRAGON.get(), SeaDragonEntity.createAttributes().build());
        event.put(CreaturesEntities.TRUMPETFISH.get(), TrumpetfishEntity.createAttributes().build());
        event.put(CreaturesEntities.CRESTED_PENGUIN.get(), CrestedPenguinEntity.createAttributes().build());
        event.put(CreaturesEntities.PARROTFISH.get(), ParrotfishEntity.createAttributes().build());
        event.put(CreaturesEntities.YELLOW_EYED_PENGUIN.get(), YellowEyedPenguinEntity.createAttributes().build());
        event.put(CreaturesEntities.BRUSH_TAILED_PENGUIN.get(), BrushTailedPenguinEntity.createAttributes().build());
        event.put(CreaturesEntities.LARGE_PENGUIN.get(), LargePenguinEntity.createAttributes().build());
        event.put(CreaturesEntities.FRIGATE.get(), FrigateEntity.createAttributes().build());
        event.put(CreaturesEntities.CLOWNFISH.get(), ClownfishEntity.createAttributes().build());
        event.put(CreaturesEntities.STILT.get(), StiltEntity.createAttributes().build());
        event.put(CreaturesEntities.LUNGFISH.get(), LungfishEntity.createAttributes().build());
        event.put(CreaturesEntities.LITTLE_PENGUIN.get(), LittlePenguinEntity.createAttributes().build());
        event.put(CreaturesEntities.EDIBLE_CRAB.get(), EdibleCrabEntity.createAttributes().build());
        event.put(CreaturesEntities.MARABOU.get(), MarabouEntity.createAttributes().build());
        event.put(CreaturesEntities.CRANE.get(), CraneEntity.createAttributes().build());
        event.put(CreaturesEntities.COCK_OF_THE_ROCK.get(), CockOfTheRockEntity.createAttributes().build());
        event.put(CreaturesEntities.TETRA.get(), TetraEntity.createAttributes().build());

        event.put(CreaturesEntities.EGG.get(), EggEntity.createAttributes().build());
        event.put(CreaturesEntities.ROE.get(), CreaturesRoeEntity.createAttributes().build());


        //spawn placement
        SpawnPlacements.register(CreaturesEntities.KOI.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.DOTTYBACK.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PIKE.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.GOURAMI.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SHRIMP.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.GUPPY.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.AROWANA.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.TROUT.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.BLUE_TANG.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.FIRE_GOBY.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.FLAME_ANGELFISH.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.GOLDFISH.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.RANCHU.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.RED_SNAPPER.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.TIGERBARB.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PIRANHA.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.ARAPAIMA.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.TAMBAQUI.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.ELEPHANTNOSE.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.STINGRAY.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SAWFISH.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SWORDFISH.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.TRUMPETFISH.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SQUID.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.LOOKDOWN.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.MANTIS_SHRIMP.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.BARRACUDA.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SEADRAGON.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PARROTFISH.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.CLOWNFISH.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        SpawnPlacements.register(CreaturesEntities.LUNGFISH.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);

// ── Ground entities ───────────────────────────────────────────────────────────
        SpawnPlacements.register(CreaturesEntities.LOVEBIRD.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                LovebirdEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SPOONBILL.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.IBIS.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.KAKAPO.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.MANDARIN_DUCK.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.WOOD_DUCK.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.LORIKEET.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.CONURE.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.DOVE.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                DoveEntity::checkDoveSpawnRules);
        SpawnPlacements.register(CreaturesEntities.RAVEN.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.GOLDEN_EAGLE.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SEA_EAGLE.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                SeaEagleEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.GYRFALCON.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.FAIRYWREN.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.RED_KITE.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.GHOST_CRAB.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                GhostCrabEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.FIDDLER_CRAB.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                GhostCrabEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PYGMY_FALCON.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                PygmyFalconEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.BARN_OWL.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.WILD_DUCK.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.ROLLER.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.CHICKADEE.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PYGMY_GOOSE.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SWALLOW.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PEAFOWL.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SPARROW.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                SparrowEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.BUSHTIT.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.LAUGHINGTHRUSH.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.EAGLEOWL.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.ROBIN.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.MAGPIE.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.GOOSE.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.OSPREY.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.KINGFISHER.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                KingfisherEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PELICAN.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                PelicanEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.LAPWING.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SKUA.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                SeaEagleEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.BUNTING.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.MONAL.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.TANAGER.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.FINCH.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.VAMPIRECRAB.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                GhostCrabEntity::checkAnimalSpawnRules);
        SpawnPlacements.register(CreaturesEntities.TARANTULA.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                TarantulaEntity::checkTarantulaSpawnRules);
        SpawnPlacements.register(CreaturesEntities.CAPERCAILLIE.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PHEASANT.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.STORK.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.WHISTLING_DUCK.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.GROUND_HORNBILL.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SECRETARYBIRD.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SHOEBILL.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.STARLING.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.CORMORANT.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.PUFFIN.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.SEAGULL.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                PelicanEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.BOOBY.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                PelicanEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.BANDED_PENGUIN.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                PelicanEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.RAIL.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.AVOCET.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.CRESTED_PENGUIN.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                PelicanEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.BRUSH_TAILED_PENGUIN.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                LargePenguinEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.YELLOW_EYED_PENGUIN.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                PelicanEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.LARGE_PENGUIN.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                LargePenguinEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.FRIGATE.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                PelicanEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.STILT.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CreaturesFlyingBird::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.LITTLE_PENGUIN.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                PelicanEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.CRANE.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                CraneEntity::checkBirdSpawnRules);
        SpawnPlacements.register(CreaturesEntities.EDIBLE_CRAB.get(),
                SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING,
                EdibleCrabEntity::checkCrabSpawnRules);
    }

    public static void init() {
        birdEntityMap.put(0, CreaturesEntities.LOVEBIRD.get());
        birdEntityMap.put(1, CreaturesEntities.SPOONBILL.get());
        birdEntityMap.put(2, CreaturesEntities.KAKAPO.get());
        birdEntityMap.put(3, CreaturesEntities.MANDARIN_DUCK.get());
        birdEntityMap.put(4, CreaturesEntities.RAVEN.get());
        birdEntityMap.put(5, CreaturesEntities.DOVE.get());
        birdEntityMap.put(6, CreaturesEntities.RED_KITE.get());
        birdEntityMap.put(7, CreaturesEntities.GOLDEN_EAGLE.get());
        birdEntityMap.put(8, CreaturesEntities.SEA_EAGLE.get());
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
        birdEntityMap.put(42, CreaturesEntities.WHISTLING_DUCK.get());
        birdEntityMap.put(43, CreaturesEntities.GROUND_HORNBILL.get());
        birdEntityMap.put(44, CreaturesEntities.SECRETARYBIRD.get());
        birdEntityMap.put(45, CreaturesEntities.SHOEBILL.get());
        birdEntityMap.put(46, CreaturesEntities.STARLING.get());
        birdEntityMap.put(47, CreaturesEntities.CORMORANT.get());
        birdEntityMap.put(48, CreaturesEntities.PUFFIN.get());
        birdEntityMap.put(49, CreaturesEntities.SEAGULL.get());
        birdEntityMap.put(50, CreaturesEntities.BOOBY.get());
        birdEntityMap.put(51, CreaturesEntities.BANDED_PENGUIN.get());
        birdEntityMap.put(52, CreaturesEntities.RAIL.get());
        birdEntityMap.put(53, CreaturesEntities.AVOCET.get());
        birdEntityMap.put(54, CreaturesEntities.CRESTED_PENGUIN.get());
        birdEntityMap.put(55, CreaturesEntities.YELLOW_EYED_PENGUIN.get());
        birdEntityMap.put(56, CreaturesEntities.BRUSH_TAILED_PENGUIN.get());
        birdEntityMap.put(57, CreaturesEntities.LARGE_PENGUIN.get());
        birdEntityMap.put(58, CreaturesEntities.FRIGATE.get());
        birdEntityMap.put(59, CreaturesEntities.STILT.get());
        birdEntityMap.put(60, CreaturesEntities.LITTLE_PENGUIN.get());
        birdEntityMap.put(61, CreaturesEntities.MARABOU.get());
        birdEntityMap.put(62, CreaturesEntities.CRANE.get());
        birdEntityMap.put(63, CreaturesEntities.COCK_OF_THE_ROCK.get());
        // etc...
    }
    public static BiMap<Integer, EntityType<? extends CreaturesBirdEntity>> getBirdEntityMap() {
        return birdEntityMap;
    }

    private static String getEntityKey(Mob mob) {
        String type = ForgeRegistries.ENTITY_TYPES.getKey(mob.getType()).getPath();
        if (mob instanceof CreaturesBirdEntity bird) {
            return type.replace("_", "") + "_" + bird.getVariant() + "_" + bird.getGenderString();
        }
        if (mob instanceof FishBase fish) {
            return type.replace("_", "") + "_" + fish.getVariant() + "_" + fish.getGenderString();
        }
        if (mob instanceof AbstractCrabBase crab) {
            return type.replace("_", "") + "_" + crab.getVariant() + "_" + crab.getGenderString();
        }
        return type;
    }

    private static final Map<UUID, UUID> lookingAt = new HashMap<>();
    private static final Map<UUID, Long> lookingStartTime = new HashMap<>();

    private static String getSpeciesName(Mob mob) {
        if (mob instanceof CreaturesBirdEntity bird) return bird.getSpeciesName();
        if (mob instanceof FishBase fish) return fish.getSpeciesName();
        if (mob instanceof AbstractCrabBase crab) return crab.getSpeciesName();
        return mob.getDisplayName().getString();
    }

    @Mod.EventBusSubscriber(modid = Creatures.MODID)
    public class FFGuideInteractEvent {
        private static final Map<UUID, UUID> hookedFish = new HashMap<>(); // hookUUID -> fishUUID

        public static void registerBite(net.minecraft.world.entity.projectile.FishingHook hook, FishBase fish) {
            if (hookedFish.containsKey(hook.getUUID())) return; // already bitten
            hookedFish.put(hook.getUUID(), fish.getUUID());
            hook.level().playSound(null, hook.getX(), hook.getY(), hook.getZ(),
                    net.minecraft.sounds.SoundEvents.FISHING_BOBBER_SPLASH,
                    net.minecraft.sounds.SoundSource.NEUTRAL,
                    1.0F, 1.0F + (hook.level().random.nextFloat() - hook.level().random.nextFloat()) * 0.4F);

            hook.level().playSound(null, hook.blockPosition(),
                    net.minecraft.sounds.SoundEvents.FISHING_BOBBER_THROW,
                    net.minecraft.sounds.SoundSource.NEUTRAL,
                    0.5F, 1.0F);
            hook.setDeltaMovement(hook.getDeltaMovement().x, -0.4, hook.getDeltaMovement().z);

        }

        public static boolean isBitten(net.minecraft.world.entity.projectile.FishingHook hook) {
            return hookedFish.containsKey(hook.getUUID());
        }

        @SubscribeEvent
        public static void onTooltip(net.minecraftforge.event.entity.player.ItemTooltipEvent event) {
            ItemStack stack = event.getItemStack();
            if (!stack.is(Items.FISHING_ROD)) return;
            if (hasBait(stack)) {
                String baitType = getBaitType(stack);
                Component baitName = baitType.equals("fish_food")
                        ? Component.translatable("item.creatures.fish_food")
                        : Component.translatable("item.creatures.algae_wafer");
                event.getToolTip().add(Component.translatable("message.creatures.bait_attached",
                        baitName, getBaitCount(stack)).withStyle(ChatFormatting.GREEN));
            } else {
//                event.getToolTip().add(Component.translatable("message.creatures.no_bait_attached")
//                        .withStyle(ChatFormatting.GRAY));
            }
        }
        private static void checkCategoryAdvancements(FieldGuideCapability cap,
                                                      String completedSpeciesKey, ServerPlayer serverPlayer) {

            for (Map.Entry<String, List<String>> entry : CreaturesCategories.CATEGORIES.entrySet()) {
                String categoryName = entry.getKey();
                List<String> keys = entry.getValue();

                if (!keys.contains(completedSpeciesKey)) {
                    continue;
                }

                Advancement adv = serverPlayer.getServer().getAdvancements()
                        .getAdvancement(new ResourceLocation("creatures",
                                "discover_every_" + categoryName));

                if (adv == null) {
                    continue;
                }

                AdvancementProgress progress = serverPlayer.getAdvancements()
                        .getOrStartProgress(adv);
                if (!progress.isDone()) {
                    progress.grantProgress(completedSpeciesKey.replace("_", ""));
                }
            }
        }

        public static void triggerAchievement(FieldGuideCapability cap, String key, ServerPlayer serverPlayer) {
            SpeciesEntry species = SpeciesRegistry.ALL_SPECIES.stream()
                    .filter(s -> key.startsWith(s.entityKey.replace("_", "")))
                    .findFirst().orElse(null);

            if (species == null) return;

            Set<Integer> discovered = cap.getDiscoveredVariants(species.entityKey);
            boolean allVariants = IntStream.rangeClosed(1, species.totalVariants)
                    .allMatch(discovered::contains);

            if (allVariants) {
                CreaturesCriteriaTriggers.DISCOVERED_ALL_VARIANTS
                        .trigger(serverPlayer, species.entityKey);
                checkCategoryAdvancements(cap, species.entityKey, serverPlayer);
            }

            for (Map.Entry<String, List<String>> entry :
                    CreaturesCategories.CATEGORIES.entrySet()) {
                String categoryName = entry.getKey();
                List<String> categoryKeys = entry.getValue();

                boolean categoryComplete = categoryKeys.stream().allMatch(catKey -> {
                    SpeciesEntry catSpecies = getSpeciesEntryByKey(catKey);
                    if (catSpecies == null) return false;
                    Set<Integer> catDiscovered = cap.getDiscoveredVariants(catKey);
                    return IntStream.rangeClosed(1, catSpecies.totalVariants)
                            .allMatch(catDiscovered::contains);
                });

                if (categoryComplete) {
                    CreaturesCriteriaTriggers.DISCOVERED_CATEGORY
                            .trigger(serverPlayer, categoryName);
                }
            }
        }

        private static boolean isBaitItem(ItemStack stack) {
            return stack.is(CreaturesItems.FISH_FOOD.get())
                    || stack.is(CreaturesItems.ALGAE_WAFER.get());
        }

        public static boolean hasBait(ItemStack rod) {
            return getBaitCount(rod) > 0;
        }

        public static void setBait(ItemStack rod, boolean hasBait) {
            rod.getOrCreateTag().putBoolean("CreaturesBait", hasBait);
        }

        public static String getBaitType(ItemStack rod) {
            if (!rod.hasTag()) return "";
            return rod.getTag().getString("CreaturesBaitType");
        }

        public static void setBaitType(ItemStack rod, String type) {
            rod.getOrCreateTag().putString("CreaturesBaitType", type);
        }

        public static int getBaitCount(ItemStack rod) {
            if (!rod.hasTag()) return 0;
            return rod.getTag().getInt("CreaturesBaitCount");
        }

        public static void setBaitCount(ItemStack rod, int count) {
            rod.getOrCreateTag().putInt("CreaturesBaitCount", count);
            if (count <= 0) {
                rod.getOrCreateTag().remove("CreaturesBaitType");
                rod.getOrCreateTag().remove("CreaturesBaitCount");
            }
        }

        @SubscribeEvent
        public static void onReelIn(PlayerInteractEvent.RightClickItem event) {
            Player player = event.getEntity();
            if (player.level().isClientSide()) return;
            if (!(player.level() instanceof ServerLevel serverLevel)) return;
            if (!event.getItemStack().is(net.minecraft.world.item.Items.FISHING_ROD)) return;
            ItemStack rod = player.getMainHandItem();

            net.minecraft.world.entity.projectile.FishingHook hook = player.fishing;
            if (hook == null) return;

            UUID fishUUID = hookedFish.get(hook.getUUID());
            if (fishUUID == null) return;

            Entity fishEntity = serverLevel.getEntity(fishUUID);
            if (!(fishEntity instanceof FishBase fish)) {
                hookedFish.remove(hook.getUUID());
                return;
            }
            if (rod.is(Items.FISHING_ROD) && FFGuideInteractEvent.hasBait(rod)) {
                int remaining = FFGuideInteractEvent.getBaitCount(rod) - 1;
                FFGuideInteractEvent.setBaitCount(rod, remaining);
                if (remaining <= 0) {
                    FFGuideInteractEvent.setBaitType(rod, "");
                }
            }

            // Discover in field guide
            if (player instanceof ServerPlayer serverPlayer) {
                boolean hasGuide = player.getInventory().items.stream()
                        .anyMatch(stack -> stack.is(CreaturesItems.FF_GUIDE.get()))
                        || player.getOffhandItem().is(CreaturesItems.FF_GUIDE.get());
                if (hasGuide) {
                    String key = ModEventSubscriber.getEntityKeyStatic(fish);
                    serverPlayer.getCapability(FieldGuideCapability.CAPABILITY).ifPresent(cap -> {
                        boolean isNew = cap.discover(key);
                        if (isNew) {
                            NetworkHandler.CHANNEL.send(
                                    PacketDistributor.PLAYER.with(() -> serverPlayer),
                                    new SyncDiscoveryPacket(key));
                            serverPlayer.giveExperiencePoints(10);
                            serverPlayer.sendSystemMessage(Component.translatable(
                                    "message.creatures.discovered",
                                    serverPlayer.getName(),
                                    fish.getSpeciesName() + " " + (fish.getGender() == 1 ? "§9♂" : "§d♀")));
                        }
                        triggerAchievement(cap, key, serverPlayer);
                    });
                }

            }

            // Fling fish toward player
            double dx = player.getX() - fish.getX();
            double dy = player.getY() - fish.getY() + 0.5D;
            double dz = player.getZ() - fish.getZ();
            fish.setDeltaMovement(
                    dx * 0.12D,
                    dy * 0.12D + Math.sqrt(Math.sqrt(dx * dx + dy * dy + dz * dz)) * 0.12D,
                    dz * 0.12D);

            //ItemStack bin = findBinInInventory(player);

            hookedFish.remove(hook.getUUID());
            ItemStack bin = findBinInInventory(player);
            if (bin != null && !FishStorageBinItem.isFull(bin)) {
                final UUID fishId = fish.getUUID();
                serverLevel.getServer().tell(new net.minecraft.server.TickTask(
                        serverLevel.getServer().getTickCount() + 100, () -> {
                    Entity e = serverLevel.getEntity(fishId);
                    if (!(e instanceof FishBase f)) return;
                    boolean stored = FishStorageBinItem.addFish(bin, f);
                    if (stored) {
                        f.remove(Entity.RemovalReason.DISCARDED);
                        player.displayClientMessage(Component.translatable(
                                "creatures.message.bin.stored",
                                f.getSpeciesName(),
                                FishStorageBinItem.getFishCount(bin),
                                FishStorageBinItem.MAX_CAPACITY), true);
                    }
                }));
            }

            player.awardStat(net.minecraft.stats.Stats.ITEM_USED.get(net.minecraft.world.item.Items.FISHING_ROD));
        }


        private static ItemStack findBinInInventory(Player player) {
            for (ItemStack stack : player.getInventory().items) {
                if (stack.getItem() instanceof FishStorageBinItem) return stack;
            }
            return null;
        }

        @SubscribeEvent
        public static void onFishing(ItemFishedEvent event) {
            Player player = event.getEntity();
            if (player.level().isClientSide()) return;
            if (!(player.level() instanceof ServerLevel serverLevel)) return;

            net.minecraft.world.entity.projectile.FishingHook hook = event.getHookEntity();
            if (hook == null) return;

            UUID fishUUID = hookedFish.remove(hook.getUUID());
            if (fishUUID == null) return; // no fish bit this hook, use normal loot

            Entity fishEntity = serverLevel.getEntity(fishUUID);
            if (!(fishEntity instanceof FishBase fish)) return;

            double dx = player.getX() - fish.getX();
            double dy = player.getY() - fish.getY() + 0.5D;
            double dz = player.getZ() - fish.getZ();
            fish.setDeltaMovement(
                    dx * 0.1D,
                    dy * 0.1D + Math.sqrt(Math.sqrt(dx * dx + dy * dy + dz * dz)) * 0.08D,
                    dz * 0.1D);

            event.getDrops().clear();
        }


        @SubscribeEvent
        public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
            Player player = event.getEntity();
            ItemStack stack = player.getItemInHand(event.getHand());

            if (stack.getItem() != CreaturesItems.FF_GUIDE.get()) return;
            if (!(event.getTarget() instanceof Mob mob)) return;
            if (!((mob instanceof CreaturesBirdEntity || mob instanceof FishBase || mob instanceof AbstractCrabBase))) return;

            Creatures.PROXY.setReferencedMob(mob);
            if (player.level().isClientSide()) {
                Creatures.PROXY.openCreaturesGui();
            }
            String key = getEntityKey(mob);
            player.getCapability(FieldGuideCapability.CAPABILITY).ifPresent(cap -> {
                boolean isNew = cap.discover(key);
                if (isNew && !player.level().isClientSide()) {
                    NetworkHandler.CHANNEL.send(
                            PacketDistributor.PLAYER.with(() -> (ServerPlayer) player),
                            new SyncDiscoveryPacket(key));

                    String speciesName = "";
                    int gender = 0;
                    if (mob instanceof CreaturesBirdEntity bird) {
                        speciesName = bird.getSpeciesName();
                        gender = bird.getGender();
                    }
                    else if (mob instanceof FishBase fish) {
                        speciesName = fish.getSpeciesName();
                        gender = fish.getGender();
                    }
                    else if (mob instanceof AbstractCrabBase crab) {
                        speciesName = crab.getSpeciesName();
                        gender = crab.getGender();
                    };

                    ((ServerPlayer) player).giveExperiencePoints(10);

//                    player.sendSystemMessage(Component.translatable(
//                            "message.creatures.discovered",
//                            player.getName(),
//                            speciesName));
                    player.sendSystemMessage(Component.translatable(
                            "message.creatures.discovered",
                            player.getName(),
                            speciesName + " " + (gender == 1 ? "§9♂" : "§d♀")));

//                    NetworkHandler.CHANNEL.send(
//                            PacketDistributor.PLAYER.with(() -> (ServerPlayer) player),
//                            new PlaySoundPacket(net.minecraft.sounds.SoundEvents.EXPERIENCE_ORB_PICKUP));
                }
                if (!player.level().isClientSide() && player instanceof ServerPlayer serverPlayer) {
                    triggerAchievement(cap, key, serverPlayer);
                }            });
            event.setCanceled(true);
        }

        @SubscribeEvent
        public static void onPlayerClone(PlayerEvent.Clone event) {
            if (event.getEntity().level().isClientSide()) return;

            event.getOriginal().reviveCaps();
            event.getOriginal().getCapability(FieldGuideCapability.CAPABILITY).ifPresent(oldCap -> {
                event.getEntity().getCapability(FieldGuideCapability.CAPABILITY).ifPresent(newCap -> {
                    newCap.copyFrom(oldCap);
                });
            });
            event.getOriginal().invalidateCaps();
        }

        @SubscribeEvent
        public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
            if (event.getEntity().level().isClientSide()) return;
            if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;

            serverPlayer.getCapability(FieldGuideCapability.CAPABILITY).ifPresent(cap -> {
                NetworkHandler.CHANNEL.send(
                        PacketDistributor.PLAYER.with(() -> serverPlayer),
                        new SyncAllDiscoveriesPacket(cap.getAll())
                );
            });
        }

        @SubscribeEvent
        public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
            if (event.getLevel().isClientSide()) return;

            Level level = event.getLevel();
            BlockPos pos = event.getPos();
            BlockState state = level.getBlockState(pos);


            if (!state.is(Blocks.COMPOSTER)) return;
            int currentLevel = state.getValue(ComposterBlock.LEVEL);
            if (currentLevel < 8) return;

            Vec3 vec3 = Vec3.atLowerCornerWithOffset(pos, 0.5, 1.01, 0.5).offsetRandom(level.random, 0.7f);
            ItemEntity entity = new ItemEntity(level, vec3.x(), vec3.y(), vec3.z(),
                    new ItemStack(CreaturesItems.MEALWORMS.get()));
            entity.setDefaultPickUpDelay();
            level.addFreshEntity(entity);
        }

        @SubscribeEvent
        public static void onLootTableLoad(LootTableLoadEvent event) {
            if (!event.getName().equals(
                    new ResourceLocation("minecraft", "gameplay/sniffer_digging"))) return;

            CompoundTag tag;
            tag = new CompoundTag();
            tag.putInt("EggVariant", 7);

            LootPool pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(CreaturesItems.KINGFISHER_EGG.get())
                            .setWeight(1)
                            .apply(SetNbtFunction.setTag(tag))
                    )
                    .add(EmptyLootItem.emptyItem().setWeight(99))
                    .build();

            event.getTable().addPool(pool);
        }

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if (event.phase != TickEvent.Phase.END) return;
            if (event.player.level().isClientSide()) return;

            ServerPlayer player = (ServerPlayer) event.player;

            // Check if player is using a spyglass
            if (!player.getUseItem().is(Items.SPYGLASS)) return;

            //you need a guide in your inventory
            boolean hasGuide = player.getInventory().items.stream()
                    .anyMatch(stack -> stack.is(CreaturesItems.FF_GUIDE.get()));
            if (!hasGuide) return;

            // Raycast in the direction the player is looking
            double range = 64.0;
            Vec3 eyePos = player.getEyePosition();
            Vec3 lookVec = player.getLookAngle();
            Vec3 endPos = eyePos.add(lookVec.scale(range));

            AABB searchBox = player.getBoundingBox().expandTowards(lookVec.scale(range)).inflate(1.0);

            List<LivingEntity> entities = player.level().getEntitiesOfClass(
                    LivingEntity.class, searchBox,
                    e -> e instanceof CreaturesBirdEntity
                            || e instanceof FishBase
                            || e instanceof AbstractCrabBase);

            for (LivingEntity entity : entities) {
                // Check if entity is actually in the line of sight
                AABB entityBox = entity.getBoundingBox().inflate(0.3);
                Optional<Vec3> hit = entityBox.clip(eyePos, endPos);
                if (hit.isEmpty()) continue;

                String key = getEntityKey((Mob) entity);
                if (key == null) continue;

                player.getCapability(FieldGuideCapability.CAPABILITY).ifPresent(cap -> {
                    if (!cap.getAll().contains(key)) {
                        cap.discover(key);
                        // Notify player
                        String speciesName = getSpeciesName((Mob) entity);
                        int gender = 0;
                        if (entity instanceof CreaturesBirdEntity bird) {
                            gender = bird.getGender();
                        }
                        if (entity instanceof FishBase fish) {
                            gender = fish.getGender();
                        }
                        if (entity instanceof AbstractCrabBase crab) {
                            gender = crab.getGender();
                        }
                        player.sendSystemMessage(Component.translatable(
                                "message.creatures.discovered",
                                player.getName(),
                                speciesName + " " + (gender == 1 ? "§9♂" : "§d♀")));
                        // Sync to client
                        NetworkHandler.CHANNEL.send(
                                PacketDistributor.PLAYER.with(() -> player),
                                new SyncDiscoveryPacket(key));

                    }
                    triggerAchievement(cap, key, player);
                });
                break;
            }
        }


    }

    @Mod.EventBusSubscriber(modid = Creatures.MODID, value = Dist.CLIENT)
    public class ClientEventHandler {
        @SubscribeEvent
        public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
            Player player = event.getEntity();
            if (!player.level().isClientSide()) return;
            Minecraft mc = Minecraft.getInstance();
            if (mc.hitResult != null && mc.hitResult.getType() == HitResult.Type.ENTITY) return;
            if (player.getItemInHand(event.getHand()).getItem() != CreaturesItems.FF_GUIDE.get()) return;
            if (!player.level().isClientSide()) return;
            if (player.level().isClientSide()) {
                Creatures.PROXY.openFieldGuideGUI();
                //Minecraft.getInstance().setScreen(new FieldGuideGUI());
            }
        }
    }


}
