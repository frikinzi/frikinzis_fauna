package com.frikinzi.creatures.util;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.*;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.entity.base.TameableBirdBase;
import com.frikinzi.creatures.registry.ModEntityTypes;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Creatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributes {

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        EntitySpawnPlacementRegistry.register(ModEntityTypes.KOI.get(),
                EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.DOTTYBACK.get(),
                EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.PIKE.get(),
                EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.GOURAMI.get(),
                EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.SHRIMP.get(),
                EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.GUPPY.get(),
                EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.AROWANA.get(),
                EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.TROUT.get(),
                EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.BLUE_TANG.get(),
                EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.FIRE_GOBY.get(),
                EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.FLAME_ANGELFISH.get(),
                EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.GOLDFISH.get(),
                EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.RANCHU.get(),
                EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.RED_SNAPPER.get(),
                EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                FishBase::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.LOVEBIRD.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.SPOONBILL.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.IBIS.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.KAKAPO.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.MANDARIN_DUCK.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.WOOD_DUCK.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.LORIKEET.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.CONURE.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.DOVE.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.RAVEN.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.GOLDEN_EAGLE.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.STELLERS_SEA_EAGLE.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                StellersSeaEagleEntity::checkSSESpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.GYRFALCON.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.FAIRYWREN.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.RED_KITE.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.GHOST_CRAB.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                GhostCrabEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.FIDDLER_CRAB.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                GhostCrabEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.PYGMY_FALCON.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                PygmyFalconEntity::checkFalconSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.BARN_OWL.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.WILD_DUCK.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.ROLLER.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.CHICKADEE.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.PYGMY_GOOSE.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.SWALLOW.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.PEAFOWL.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.SPARROW.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.BUSHTIT.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.LAUGHINGTHRUSH.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.EAGLEOWL.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.ROBIN.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);
        EntitySpawnPlacementRegistry.register(ModEntityTypes.MAGPIE.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING,
                TameableBirdBase::checkBirdSpawnRules);



        event.put(ModEntityTypes.KOI.get(), KoiEntity.createAttributes().build());
        event.put(ModEntityTypes.LOVEBIRD.get(), LovebirdEntity.createAttributes().build());
        event.put(ModEntityTypes.DOTTYBACK.get(), DottybackEntity.createAttributes().build());
        event.put(ModEntityTypes.PIKE.get(), PikeEntity.createAttributes().build());
        event.put(ModEntityTypes.GUPPY.get(), GuppyEntity.createAttributes().build());
        event.put(ModEntityTypes.SPOONBILL.get(), SpoonbillEntity.createAttributes().build());
        event.put(ModEntityTypes.KAKAPO.get(), KakapoEntity.createAttributes().build());
        event.put(ModEntityTypes.MANDARIN_DUCK.get(), MandarinDuckEntity.createAttributes().build());
        event.put(ModEntityTypes.AROWANA.get(), ArowanaEntity.createAttributes().build());
        event.put(ModEntityTypes.RAVEN.get(), RavenEntity.createAttributes().build());
        event.put(ModEntityTypes.SHRIMP.get(), ShrimpEntity.createAttributes().build());
        event.put(ModEntityTypes.DOVE.get(), DoveEntity.createAttributes().build());
        event.put(ModEntityTypes.RED_KITE.get(), RedKiteEntity.createAttributes().build());
        event.put(ModEntityTypes.GOLDEN_EAGLE.get(), GoldenEagleEntity.createAttributes().build());
        event.put(ModEntityTypes.STELLERS_SEA_EAGLE.get(), StellersSeaEagleEntity.createAttributes().build());
        event.put(ModEntityTypes.GYRFALCON.get(), GyrfalconEntity.createAttributes().build());
        event.put(ModEntityTypes.LORIKEET.get(), LorikeetEntity.createAttributes().build());
        event.put(ModEntityTypes.CONURE.get(), ConureEntity.createAttributes().build());
        event.put(ModEntityTypes.FAIRYWREN.get(), FairywrenEntity.createAttributes().build());
        event.put(ModEntityTypes.GHOST_CRAB.get(), GhostCrabEntity.createAttributes().build());
        event.put(ModEntityTypes.GOURAMI.get(), GouramiEntity.createAttributes().build());
        event.put(ModEntityTypes.PYGMY_FALCON.get(), PygmyFalconEntity.createAttributes().build());
        event.put(ModEntityTypes.BARN_OWL.get(), BarnOwlEntity.createAttributes().build());
        event.put(ModEntityTypes.WILD_DUCK.get(), WildDuckEntity.createAttributes().build());
        event.put(ModEntityTypes.ROLLER.get(), RollerEntity.createAttributes().build());
        event.put(ModEntityTypes.GOLDFISH.get(), GoldfishEntity.createAttributes().build());
        event.put(ModEntityTypes.RANCHU.get(), RanchuEntity.createAttributes().build());
        event.put(ModEntityTypes.CHICKADEE.get(), ChickadeeEntity.createAttributes().build());
        event.put(ModEntityTypes.PYGMY_GOOSE.get(), PygmyGooseEntity.createAttributes().build());
        event.put(ModEntityTypes.FIRE_GOBY.get(), FireGobyEntity.createAttributes().build());
        event.put(ModEntityTypes.BLUE_TANG.get(), BlueTangEntity.createAttributes().build());
        event.put(ModEntityTypes.TROUT.get(), TroutEntity.createAttributes().build());
        event.put(ModEntityTypes.SWALLOW.get(), SwallowEntity.createAttributes().build());
        event.put(ModEntityTypes.FIDDLER_CRAB.get(), FiddlerCrabEntity.createAttributes().build());
        event.put(ModEntityTypes.FLAME_ANGELFISH.get(), FlameAngelfishEntity.createAttributes().build());
        event.put(ModEntityTypes.IBIS.get(), IbisEntity.createAttributes().build());
        event.put(ModEntityTypes.RED_SNAPPER.get(), RedSnapperEntity.createAttributes().build());
        event.put(ModEntityTypes.WOOD_DUCK.get(), WoodDuckEntity.createAttributes().build());
        event.put(ModEntityTypes.PEAFOWL.get(), PeafowlEntity.createAttributes().build());
        event.put(ModEntityTypes.SPARROW.get(), SparrowEntity.createAttributes().build());
        event.put(ModEntityTypes.BUSHTIT.get(), BushtitEntity.createAttributes().build());
        event.put(ModEntityTypes.EAGLEOWL.get(), EagleOwlEntity.createAttributes().build());
        event.put(ModEntityTypes.ROBIN.get(), RobinEntity.createAttributes().build());
        event.put(ModEntityTypes.MAGPIE.get(), MagpieEntity.createAttributes().build());
        event.put(ModEntityTypes.LAUGHINGTHRUSH.get(), LaughingthrushEntity.createAttributes().build());
    }

}
