package com.frikinzi.creatures.item;

import com.frikinzi.creatures.entity.SpoonbillEntity;
import com.frikinzi.creatures.entity.egg.CreaturesEggEntity;
import com.frikinzi.creatures.registry.ModEntityTypes;
import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

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
        //System.out.println(compoundnbt);
        egg.setPos(blockpos.getX() + 0.5, blockpos.getY() + 1, blockpos.getZ() +0.5);
        egg.setSpecies(this.species);
        if (compoundnbt != null && compoundnbt.contains("EggVariant", 3)) {
        egg.setVariant(compoundnbt.getInt("EggVariant"));
        }
        if (compoundnbt != null && compoundnbt.contains("EggHeightMultiplier")) {
            egg.setHeightMultiplier(compoundnbt.getFloat("EggHeightMultiplier"));
        }
        p_77659_2_.level.addFreshEntity(egg);
        itemstack.shrink(1);
        return ActionResult.consume(itemstack);
}

//    @OnlyIn(Dist.CLIENT)
//    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
//        CompoundNBT compoundnbt = p_77624_1_.getTag();
//        if (compoundnbt != null && compoundnbt.contains("EggVariant", 3)) {
//            int i = compoundnbt.getInt("EggVariant");
//            String s = String.valueOf(i);
//            IFormattableTextComponent iformattabletextcomponent = new TranslationTextComponent(s);
//            p_77624_3_.add(iformattabletextcomponent);
//        }
//        if (compoundnbt != null && compoundnbt.contains("EggHeightMultiplier")) {
//            float i = compoundnbt.getFloat("EggHeightMultiplier");
//            String s = String.valueOf(i);
//            IFormattableTextComponent iformattabletextcomponent = new TranslationTextComponent(s);
//            p_77624_3_.add(iformattabletextcomponent);
//        }
//    }
}
