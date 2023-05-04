package com.frikinzi.creatures.item;

import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.TameableFlyingBirdEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class BirdCarrierItem extends Item {

    public BirdCarrierItem(Properties p_i48465_4_) {
        super(p_i48465_4_);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (target instanceof CreaturesBirdEntity) {
            if (stack.hasTag()) {
                player.displayClientMessage(new TranslatableComponent("creatures.message.bird_carrier.full"), true);
                return InteractionResult.PASS;
            } else {
                CreaturesBirdEntity bird = (CreaturesBirdEntity) target;
                if (player.level.isClientSide) return InteractionResult.SUCCESS;
                CompoundTag tags = new CompoundTag();
                bird.save(tags);
                ResourceLocation key = EntityType.getKey(target.getType());
                tags.putString("id", key.toString());
                if (bird.isTame()) {
                    tags.putString("OwnerName", player.getName().getString());
                }
                tags.putInt("Variant", bird.getVariant());
                if (target.hasCustomName()) tags.putString("DisplayName", bird.getDisplayName().getString());
                tags.putString("Species", bird.getSpeciesName());
                target.remove(Entity.RemovalReason.DISCARDED);
                player.displayClientMessage(new TranslatableComponent("creatures.message.bird_carrier.retrieve"), true);
                ItemStack newStack = new ItemStack(this);
                newStack.setTag(tags);
                player.setItemInHand(hand, newStack);

            }
        }
        return InteractionResult.SUCCESS;
    }

    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        ItemStack item = context.getItemInHand();
        if (!item.hasTag()) {
            player.displayClientMessage(new TranslatableComponent("creatures.message.bird_carrier.empty"), true);
            return InteractionResult.PASS;
        }
        if (!world.isClientSide) {
            BlockPos blockPos = new BlockPos(context.getClickedPos()).relative(context.getClickedFace());
            CompoundTag tags;
            tags = item.getTag();
            Entity entity = EntityType.loadEntityRecursive(tags, world, entity1 -> entity1);

            if (entity instanceof CreaturesBirdEntity) {
                entity.absMoveTo(blockPos.getX() + 0.5D, blockPos.getY(), blockPos.getZ() + 0.5D, context.getRotation(), 0);
                entity.setUUID(tags.getUUID("UUID"));
                world.addFreshEntity(entity);

                player.setItemInHand(context.getHand(), new ItemStack(this));
                player.displayClientMessage(new TranslatableComponent("creatures.message.bird_carrier.released"), true);
            }

        }
        return InteractionResult.SUCCESS;

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if (stack.hasTag()) {
            tooltip.add(new TranslatableComponent(stack.getTag().getString("Species")).withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        }
    }
}
