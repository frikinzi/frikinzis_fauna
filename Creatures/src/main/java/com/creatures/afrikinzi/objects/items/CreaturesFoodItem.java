package com.creatures.afrikinzi.objects.items;

import com.creatures.afrikinzi.Creatures;
import com.creatures.afrikinzi.init.ItemInit;
import net.minecraft.item.ItemFood;

public class CreaturesFoodItem extends ItemFood {
    public CreaturesFoodItem(String name, int amount, boolean isWolfFood)
    {
        super(amount, isWolfFood);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Creatures.itemsblockstab);

        ItemInit.ITEMS.add(this);
    }

    public void registerModels()
    {
        Creatures.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
