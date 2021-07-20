package com.creatures.afrikinzi.init;

import com.creatures.afrikinzi.Creatures;
import com.creatures.afrikinzi.entity.arowana.EntityArowana;
import com.creatures.afrikinzi.entity.dottyback.EntityDottyback;
import com.creatures.afrikinzi.entity.dove.EntityDove;
import com.creatures.afrikinzi.entity.gourami.EntityGourami;
import com.creatures.afrikinzi.entity.guppy.EntityGuppy;
import com.creatures.afrikinzi.entity.kakapo.EntityKakapo;
import com.creatures.afrikinzi.entity.koi.EntityKoi;
import com.creatures.afrikinzi.entity.lovebird.EntityLovebird;
import com.creatures.afrikinzi.entity.mandarin_duck.EntityMandarinDuck;
import com.creatures.afrikinzi.entity.pike.EntityPike;
import com.creatures.afrikinzi.entity.raven.EntityRaven;
import com.creatures.afrikinzi.entity.shrimp.EntityShrimp;
import com.creatures.afrikinzi.entity.spoonbill.EntitySpoonbill;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
    public static void registerEntities()
    {
        registerEntity("koi", EntityKoi.class, Reference.ENTITY_KOI, 30, 16731716, 16777215);
        //EntityRegistry.addSpawn(EntityKoi.class, 80, 1, 3, EnumCreatureType.WATER_CREATURE, Biomes.RIVER);
        registerEntity("dottyback", EntityDottyback.class, Reference.ENTITY_DOTTYBACK, 30, 16740608, 6058495);
        //EntityRegistry.addSpawn(EntityDottyback.class, 100, 2, 5, EnumCreatureType.WATER_CREATURE, Biomes.OCEAN);
        registerEntity("lovebird", EntityLovebird.class, Reference.ENTITY_LOVEBIRD, 30, 16749375, 16765696);
        //EntityRegistry.addSpawn(EntityLovebird.class, 80, 2, 6, EnumCreatureType.CREATURE, Biomes.SAVANNA, Biomes.MESA);
        registerEntity("pike", EntityPike.class, Reference.ENTITY_PIKE, 30, 10973747, 16777215);
        //EntityRegistry.addSpawn(EntityPike.class, 80, 1, 1, EnumCreatureType.WATER_CREATURE, Biomes.RIVER, Biomes.FROZEN_RIVER);
        registerEntity("kakapo", EntityKakapo.class, Reference.ENTITY_KAKAPO, 30, 9607980, 12299667);
        //EntityRegistry.addSpawn(EntityKakapo.class, 40, 1, 1, EnumCreatureType.CREATURE, Biomes.FOREST);
        registerEntity("spoonbill", EntitySpoonbill.class, Reference.ENTITY_SPOONBILL, 30, 16490917, 16583198);
        //EntityRegistry.addSpawn(EntitySpoonbill.class, 70, 1, 3, EnumCreatureType.CREATURE, Biomes.RIVER, Biomes.BEACH, Biomes.COLD_BEACH);
        registerEntity("mandarin_duck", EntityMandarinDuck.class, Reference.ENTITY_MANDARIN_DUCK, 30, 11798553, 16640178);
        registerEntity("arowana", EntityArowana.class, Reference.ENTITY_AROWANA, 30, 16771881, 16758578);
        registerEntity("guppy", EntityGuppy.class, Reference.ENTITY_GUPPY, 30, 8578898, 8579010);
        registerEntity("shrimp", EntityShrimp.class, Reference.ENTITY_SHRIMP, 30, 16583198, 16490917);
        registerEntity("raven", EntityRaven.class, Reference.ENTITY_RAVEN, 50, 9536, 4152450);
        registerEntity("gourami", EntityGourami.class, Reference.ENTITY_GOURAMI, 50, 4152450, 16777215);
        registerEntity("dove", EntityDove.class, Reference.ENTITY_DOVE, 50, 14935271, 16755148);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Creatures.instance, range, 1, true, color1, color2);
    }

    private static void registerNonMobEntity() {

    }
}
