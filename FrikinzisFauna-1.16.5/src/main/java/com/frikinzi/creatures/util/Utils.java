package com.frikinzi.creatures.util;

import com.frikinzi.creatures.Creatures;
import com.google.common.collect.Maps;
import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.Map;
import java.util.Objects;

public class Utils {


    public static final Map<EntityType<?>, SpawnEggItem> EGG_MAP = Maps.newHashMap();

    public static void addSpawnEggs()
    {
        try
        {
            Map<EntityType<?>, SpawnEggItem> map = ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "field_195987_b");
            map.keySet().removeIf(Objects::isNull);
            map.putAll(EGG_MAP);

            //They are added to the list after net.minecraft.client.renderer.color.ItemColors.init() is called.
            for (SpawnEggItem spawneggitem : SpawnEggItem.eggs())
            {
                Creatures.LOGGER.debug(spawneggitem.toString());
            }
        }
        catch (IllegalArgumentException e)
        {
            throw new RuntimeException("Failed to spawn eggs to map", e);
        }
    }
}
