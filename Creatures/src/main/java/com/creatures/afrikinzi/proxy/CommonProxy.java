package com.creatures.afrikinzi.proxy;

import com.creatures.afrikinzi.entity.arowana.EntityArowana;
import com.creatures.afrikinzi.entity.guppy.EntityGuppy;
import com.creatures.afrikinzi.entity.mandarin_duck.EntityMandarinDuck;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class CommonProxy
{
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    public void init(FMLInitializationEvent event) {
        //EntityRegistry.addSpawn(EntityArowana.class, 50, 1, 2, EnumCreatureType.CREATURE, Biomes.RIVER);
        //EntityRegistry.addSpawn(EntityMandarinDuck.class, 30, 2, 4, EnumCreatureType.CREATURE, Biomes.FOREST);
        //EntityRegistry.addSpawn(EntityGuppy.class, 50, 2, 5, EnumCreatureType.WATER_CREATURE, Biomes.RIVER);
    }
}
