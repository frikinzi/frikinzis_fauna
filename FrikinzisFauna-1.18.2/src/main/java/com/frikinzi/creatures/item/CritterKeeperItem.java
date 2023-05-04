package com.frikinzi.creatures.item;

import com.frikinzi.creatures.entity.base.AbstractWalkingCreature;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class CritterKeeperItem extends Item {

    public CritterKeeperItem(Properties p_i48465_4_) {
        super(p_i48465_4_);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, LivingEntity target, InteractionHand hand) {

        if (target instanceof AbstractWalkingCreature) {
            if (stack.hasTag()) {
                player.displayClientMessage(new TranslatableComponent("creatures.message.critter_keeper.full"), true);
                return InteractionResult.PASS;
            } else {
                AbstractWalkingCreature bird = (AbstractWalkingCreature) target;
                if (player.level.isClientSide) return InteractionResult.SUCCESS;
                CompoundTag tags = new CompoundTag();
                bird.save(tags);
                ResourceLocation key = EntityType.getKey(target.getType());
                tags.putString("id", key.toString());
                tags.putInt("Variant", bird.getVariant());
                if (target.hasCustomName()) tags.putString("DisplayName", bird.getDisplayName().getString());
                tags.putString("Species", bird.getSpeciesName());
                target.remove(Entity.RemovalReason.DISCARDED);
                player.displayClientMessage(new TranslatableComponent("creatures.message.critter_keeper.retrieve"), true);
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
            player.displayClientMessage(new TranslatableComponent("creatures.message.critter_keeper.empty"), true);
            return InteractionResult.PASS;
        }
        if (!world.isClientSide) {
            BlockPos blockPos = new BlockPos(context.getClickedPos()).relative(context.getClickedFace());
            CompoundTag tags;
            tags = item.getTag();
            Entity entity = EntityType.loadEntityRecursive(tags, world, entity1 -> entity1);

            if (entity instanceof AbstractWalkingCreature) {
                entity.absMoveTo(blockPos.getX() + 0.5D, blockPos.getY(), blockPos.getZ() + 0.5D, context.getRotation(), 0);
                entity.setUUID(tags.getUUID("UUID"));
                world.addFreshEntity(entity);

                player.setItemInHand(context.getHand(), new ItemStack(this));
                player.displayClientMessage(new TranslatableComponent("creatures.message.critter_keeper.released"), true);
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
