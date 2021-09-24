package com.creatures.afrikinzi.objects.items;

import com.creatures.afrikinzi.Creatures;
import com.creatures.afrikinzi.init.ItemInit;
import net.minecraft.item.Item;

public class CreaturesItem extends Item {
    public CreaturesItem(String name) {

        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Creatures.itemsblockstab);


        ItemInit.ITEMS.add(this);
    }

    public void registerModels() {

        Creatures.proxy.registerItemRenderer(this, 0, "inventory");

    }

}
