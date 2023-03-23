package com.frikinzi.creatures.item;

import com.frikinzi.creatures.entity.egg.EggEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class EggItem extends Item {
    private final int species;

    public EggItem(int p_i48465_2_, Item.Properties p_i48465_4_) {
        super(p_i48465_4_);
        this.species = p_i48465_2_;
    }

    public InteractionResultHolder<ItemStack> use(Level p_77659_1_, Player p_77659_2_, InteractionHand p_77659_3_) {
        ItemStack itemstack = p_77659_2_.getItemInHand(p_77659_3_);
        HitResult raytraceresult = getPlayerPOVHitResult(p_77659_1_, p_77659_2_, ClipContext.Fluid.SOURCE_ONLY);
        BlockHitResult blockraytraceresult = (BlockHitResult)raytraceresult;
        BlockPos blockpos = blockraytraceresult.getBlockPos();
        EggEntity egg = new EggEntity(CreaturesEntities.EGG.get(), p_77659_2_.level);
        CompoundTag compoundnbt = itemstack.getTag();
        //System.out.println(compoundnbt);
        egg.setPos(blockpos.getX() + 0.5, blockpos.getY() + 1, blockpos.getZ() +0.5);
        egg.setSpecies(this.species);
        if (compoundnbt != null && compoundnbt.contains("EggVariant", 3)) {
            egg.setVariant(compoundnbt.getInt("EggVariant"));
        }
        if (compoundnbt != null && compoundnbt.contains("EggHeightMultiplier")) {
            egg.setHeightMultiplier(compoundnbt.getFloat("EggHeightMultiplier"));
        }
        //System.out.println(this.species);
        p_77659_2_.level.addFreshEntity(egg);
        itemstack.shrink(1);
        return InteractionResultHolder.consume(itemstack);
    }
}
