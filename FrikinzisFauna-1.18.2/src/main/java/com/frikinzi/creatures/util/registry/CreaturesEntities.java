package com.frikinzi.creatures.util.registry;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.*;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CreaturesEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,
            Creatures.MODID);

    public static final RegistryObject<EntityType<LovebirdEntity>> LOVEBIRD = ENTITY_TYPES.register("lovebird",
            () -> EntityType.Builder.<LovebirdEntity>of(LovebirdEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "lovebird").toString()));
    public static final RegistryObject<EntityType<SpoonbillEntity>> SPOONBILL = ENTITY_TYPES.register("creatures_spoonbill",
            () -> EntityType.Builder.<SpoonbillEntity>of(SpoonbillEntity::new, MobCategory.CREATURE).sized(0.8F, 0.8F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "spoonbill").toString()));
    public static final RegistryObject<EntityType<KakapoEntity>> KAKAPO = ENTITY_TYPES.register("kakapo",
            () -> EntityType.Builder.<KakapoEntity>of(KakapoEntity::new, MobCategory.CREATURE).sized(0.8F, 0.8F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "kakapo").toString()));
    public static final RegistryObject<EntityType<MandarinDuckEntity>> MANDARIN_DUCK = ENTITY_TYPES.register("mandarin_duck",
            () -> EntityType.Builder.<MandarinDuckEntity>of(MandarinDuckEntity::new, MobCategory.CREATURE).sized(0.8F, 0.8F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "mandarin_duck").toString()));
    public static final RegistryObject<EntityType<RavenEntity>> RAVEN = ENTITY_TYPES.register("raven",
            () -> EntityType.Builder.<RavenEntity>of(RavenEntity::new, MobCategory.CREATURE).sized(0.8F, 0.8F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "raven").toString()));
    public static final RegistryObject<EntityType<DoveEntity>> DOVE = ENTITY_TYPES.register("dove",
            () -> EntityType.Builder.<DoveEntity>of(DoveEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "dove").toString()));

    public static final RegistryObject<EntityType<EggEntity>> EGG = ENTITY_TYPES.register("egg",
            () -> EntityType.Builder.<EggEntity>of(EggEntity::new, MobCategory.CREATURE).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "egg").toString()));

    public static int getIntFromBirdEntity(CreaturesBirdEntity T) {
        if (T instanceof LovebirdEntity) {
            return 0;
        } else if (T instanceof SpoonbillEntity) {
            return 1;
        } else if (T instanceof KakapoEntity) {
            return 2;
        } else if (T instanceof MandarinDuckEntity) {
            return 3;
        } else if (T instanceof RavenEntity) {
            return 4;
        } else if (T instanceof DoveEntity) {
            return 5;
        } return 0;
    }

}
