package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GhostCrabEntity;
import com.frikinzi.creatures.registry.CreaturesItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

abstract public class AbstractCrabBase extends AnimalEntity {
    public AbstractCrabBase(EntityType<? extends AbstractCrabBase> p_i48567_1_, World p_i48567_2_) {
        super(p_i48567_1_, p_i48567_2_);
    }

    public String getSpeciesName() {
        return this.getType().getDescription().getString();
    }

    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == CreaturesItems.FF_GUIDE) {
            if (this.level.isClientSide) {
                Creatures.PROXY.setReferencedMob(this);
                Creatures.PROXY.openCreaturesGUI(itemstack);
                return ActionResultType.sidedSuccess(this.level.isClientSide);
            }
        }
        return super.mobInteract(p_230254_1_, p_230254_2_);
    }

    public String getFoodName() {
        return "";
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.DEAD_BUSH, 1);
    }
}
