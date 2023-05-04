package com.frikinzi.creatures.item;

import com.frikinzi.creatures.entity.egg.EggEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class EggItem extends Item {
    private final int species;

    public EggItem(int p_i48465_2_, Item.Properties p_i48465_4_) {
        super(p_i48465_4_);
        this.species = p_i48465_2_;
    }

    public @NotNull InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        ItemStack item = context.getItemInHand();
        if (!world.isClientSide) {
            BlockPos blockPos = new BlockPos(context.getClickedPos()).relative(context.getClickedFace());
            EggEntity egg = new EggEntity(CreaturesEntities.EGG.get(), player.level);
            CompoundTag compoundnbt = item.getTag();
            //System.out.println(compoundnbt);
            egg.setPos(blockPos.getX()+0.5, blockPos.getY(), blockPos.getZ()+0.5);
            egg.setSpecies(this.species);
            if (compoundnbt != null && compoundnbt.contains("EggVariant", 3)) {
                egg.setVariant(compoundnbt.getInt("EggVariant"));
            }
            if (compoundnbt != null && compoundnbt.contains("EggHeightMultiplier")) {
                egg.setHeightMultiplier(compoundnbt.getFloat("EggHeightMultiplier"));
            }
            //System.out.println(this.species);
            world.addFreshEntity(egg);
            item.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
}
