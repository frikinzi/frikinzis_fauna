package com.frikinzi.creatures.util;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.*;
import com.frikinzi.creatures.entity.egg.EggEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Creatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
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
    }

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(CreaturesEntities.LOVEBIRD.get(), LovebirdEntity.createAttributes().build());
        event.put(CreaturesEntities.SPOONBILL.get(), SpoonbillEntity.createAttributes().build());
        event.put(CreaturesEntities.KAKAPO.get(), KakapoEntity.createAttributes().build());
        event.put(CreaturesEntities.MANDARIN_DUCK.get(), MandarinDuckEntity.createAttributes().build());
        event.put(CreaturesEntities.RAVEN.get(), RavenEntity.createAttributes().build());

        event.put(CreaturesEntities.EGG.get(), EggEntity.createAttributes().build());
    }

}
