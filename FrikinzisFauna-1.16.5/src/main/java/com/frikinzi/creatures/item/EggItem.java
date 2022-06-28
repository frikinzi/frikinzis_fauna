package com.frikinzi.creatures.item;

import com.frikinzi.creatures.entity.SpoonbillEntity;
import com.frikinzi.creatures.entity.egg.CreaturesEggEntity;
import com.frikinzi.creatures.registry.ModEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EggItem extends Item {
    private final int species;

    public EggItem(int p_i48465_2_, Item.Properties p_i48465_4_) {
        super(p_i48465_4_);
        this.species = p_i48465_2_;
    }



    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        ItemStack itemstack = p_77659_2_.getItemInHand(p_77659_3_);
        RayTraceResult raytraceresult = getPlayerPOVHitResult(p_77659_1_, p_77659_2_, RayTraceContext.FluidMode.SOURCE_ONLY);
        BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceresult;
        BlockPos blockpos = blockraytraceresult.getBlockPos();
        CreaturesEggEntity egg = new CreaturesEggEntity(ModEntityTypes.EGG.get(), p_77659_2_.level);
        CompoundNBT compoundnbt = itemstack.getTag();
        egg.setPos(blockpos.getX() + 0.5, blockpos.getY() + 1, blockpos.getZ() +0.5);
        egg.setSpecies(this.species);
        if (compoundnbt != null && compoundnbt.contains("EggVariant", 3)) {
        egg.setVariant(compoundnbt.getInt("EggVariant"));
        }
        p_77659_2_.level.addFreshEntity(egg);
        itemstack.shrink(1);
        return ActionResult.consume(itemstack);
}
}
