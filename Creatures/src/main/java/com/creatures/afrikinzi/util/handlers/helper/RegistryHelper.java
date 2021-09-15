package com.creatures.afrikinzi.util.handlers.helper;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class RegistryHelper {
    public static class Entities {

        public static Biome[] grabBiomesFromType(BiomeDictionary.Type type)
        {
            return BiomeDictionary.getBiomes(type).stream().toArray(Biome[]::new);
        }

        public static BiomeDictionary.Type[] getBiomeTypesFromString(String[] types)
        {
            BiomeDictionary.Type[] bt = new BiomeDictionary.Type[types.length];
            for (int i = 0; i < types.length; i++)
            {
                String s = types[i].toUpperCase();
                bt[i] = BiomeDictionary.Type.getType(s);
            }

            return bt;
        }

    }
}
