package com.creatures.afrikinzi.objects.items;

import com.creatures.afrikinzi.Creatures;
import com.creatures.afrikinzi.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class CreaturesEgg extends Item {
    Class <? extends EntityAnimal> spawnentity;

    public CreaturesEgg(String name, Class <? extends EntityAnimal> entity)
    {
        this.maxStackSize = 16;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Creatures.itemsblockstab);
        spawnentity = entity;

        ItemInit.ITEMS.add(this);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!playerIn.capabilities.isCreativeMode)
        {
            itemstack.shrink(1);
        }

        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote)
        {
            EntityEgg entityegg = new EntityEgg(worldIn, playerIn);
            entityegg.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entityegg);
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

    public Class <? extends EntityAnimal> getSpawnEntity(Class <? extends EntityAnimal> newentity) {
        spawnentity = newentity;
        return spawnentity;
    }

    public void registerModels()
    {
        Creatures.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
