package com.frikinzi.creatures.registry;

import com.frikinzi.creatures.player.DiscoveredAllVariantsTrigger;
import com.frikinzi.creatures.player.DiscoveredCategoryTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class CreaturesCriteriaTriggers {
    public static DiscoveredAllVariantsTrigger DISCOVERED_ALL_VARIANTS;
    public static DiscoveredCategoryTrigger DISCOVERED_CATEGORY;

    public static void register() {
        DISCOVERED_ALL_VARIANTS = CriteriaTriggers.register(new DiscoveredAllVariantsTrigger());
        DISCOVERED_CATEGORY = CriteriaTriggers.register(new DiscoveredCategoryTrigger());
    }
}