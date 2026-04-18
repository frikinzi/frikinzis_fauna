package com.frikinzi.creatures.item;

import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.FishBase;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class FishNetItem extends Item {

    public FishNetItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (target instanceof FishBase fish) {
            if (stack.hasTag()) {
                player.displayClientMessage(Component.translatable("creatures.message.fishingnet.full"), true);
                return InteractionResult.PASS;
            } else {
                if (player.level().isClientSide()) return InteractionResult.SUCCESS;
                CompoundTag tags = new CompoundTag();
                fish.save(tags);
                ResourceLocation key = EntityType.getKey(target.getType());
                tags.putString("id", key.toString());
//                if (bird.isTame()) {
//                    tags.putString("OwnerName", player.getName().getString());
//                }
                tags.putFloat("SizeMultiplier", fish.getHeightMultiplier());
                tags.putInt("Variant", fish.getVariant());
                if (target.hasCustomName()) tags.putString("DisplayName", fish.getDisplayName().getString());
                tags.putString("Species", fish.getSpeciesName());
                target.remove(Entity.RemovalReason.DISCARDED);
                player.displayClientMessage(Component.translatable("creatures.message.fishingnet.retrieve"), true);
                ItemStack newStack = new ItemStack(this);
                newStack.setTag(tags);
                player.setItemInHand(hand, newStack);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        ItemStack item = context.getItemInHand();
        if (!item.hasTag()) {
            player.displayClientMessage(Component.translatable("creatures.message.fishingnet.empty"), true);
            return InteractionResult.PASS;
        }
        if (!level.isClientSide()) {
            BlockPos blockPos = context.getClickedPos().relative(context.getClickedFace());
            CompoundTag tags = item.getTag();
            Entity entity = EntityType.loadEntityRecursive(tags, level, e -> e);

            if (entity instanceof FishBase) {
                entity.absMoveTo(blockPos.getX() + 0.5D, blockPos.getY(), blockPos.getZ() + 0.5D, context.getRotation(), 0);
                entity.setUUID(tags.getUUID("UUID"));
                ((FishBase) entity).setPersistenceRequired();
                level.addFreshEntity(entity);

                player.setItemInHand(context.getHand(), new ItemStack(this));
                player.displayClientMessage(Component.translatable("creatures.message.fishingnet.released"), true);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (stack.hasTag()) {
            tooltip.add(Component.translatable(stack.getTag().getString("Species"))
                    .setStyle(Style.EMPTY.withItalic(true).withColor(0xAAAAAA)));
        }
    }
}
