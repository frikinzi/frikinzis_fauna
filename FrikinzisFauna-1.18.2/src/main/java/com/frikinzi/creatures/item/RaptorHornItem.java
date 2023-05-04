package com.frikinzi.creatures.item;

import com.frikinzi.creatures.entity.base.RaptorEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class RaptorHornItem extends Item {

    public RaptorHornItem(Item.Properties p_i48465_4_) {
        super(p_i48465_4_);
    }

    public InteractionResultHolder<ItemStack> use(Level p_77659_1_, Player p_77659_2_, InteractionHand p_77659_3_) {
        final TargetingConditions targeting = TargetingConditions.forNonCombat().range(60.0D).selector((p_32710_) -> {
            return ((RaptorEntity)p_32710_).isTame() && ((RaptorEntity)p_32710_).getOwner() == p_77659_2_;
        });
        ItemStack itemstack = p_77659_2_.getItemInHand(p_77659_3_);
        List<RaptorEntity> list = p_77659_2_.level.getNearbyEntities(RaptorEntity.class, targeting, p_77659_2_, p_77659_2_.getBoundingBox().inflate(60.0D, 60.0D, 60.0D));
        if (!list.isEmpty()) {
            for (RaptorEntity raptorEntity : list) {
                if (raptorEntity.isHunting() == 1) {
                    raptorEntity.setHunting(0);
                    raptorEntity.moveTo(p_77659_2_.getX(), p_77659_2_.getY()+ 0.5, p_77659_2_.getZ());
                }
            }
            if (p_77659_2_.level.isClientSide) {
                Component i = new TranslatableComponent("message.all_stop_hunting");
                p_77659_2_.sendMessage(i, Util.NIL_UUID);
            }
        }

        return InteractionResultHolder.pass(itemstack);
    }
}
