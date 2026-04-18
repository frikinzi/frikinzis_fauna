package com.frikinzi.creatures.item;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.base.FishBase;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class FishStorageBinItem extends Item {
    public static final int MAX_CAPACITY = 10;

    public FishStorageBinItem(Properties properties) {
        super(properties);
    }

    public static ListTag getFishList(ItemStack bin) {
        if (!bin.hasTag()) return new ListTag();
        return bin.getOrCreateTag().getList("StoredFish", Tag.TAG_COMPOUND);
    }

    public static int getFishCount(ItemStack bin) {
        return getFishList(bin).size();
    }

    public static boolean isFull(ItemStack bin) {
        return getFishCount(bin) >= MAX_CAPACITY;
    }

    public static boolean addFish(ItemStack bin, FishBase fish) {
        if (isFull(bin)) return false;
        CompoundTag tag = new CompoundTag();
        fish.save(tag);
        ResourceLocation key = EntityType.getKey(fish.getType());
        tag.putString("id", key.toString());
        tag.putFloat("SizeMultiplier", fish.getHeightMultiplier());
        tag.putInt("Variant", fish.getVariant());
        tag.putInt("Subvariant", fish.getSubVariant());
        tag.putInt("Age", fish.getAge());
        tag.putString("Species", fish.getSpeciesName());
        if (fish.hasCustomName()) tag.putString("DisplayName", fish.getDisplayName().getString());

        ListTag list = getFishList(bin);
        list.add(tag);
        bin.getOrCreateTag().put("StoredFish", list);
        return true;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (!context.getLevel().isClientSide()) return InteractionResult.SUCCESS;

        ItemStack stack = context.getItemInHand();
        int slot = player.getInventory().findSlotMatchingItem(stack);
        BlockPos releasePos = context.getClickedPos().relative(context.getClickedFace());

        if (player.level().isClientSide()) {
            Creatures.PROXY.openBinScreen(stack, slot, releasePos);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack bin = player.getItemInHand(hand);

        if (level.isClientSide()) {
            BlockHitResult raytraceResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);

            BlockPos releasePos;
            if (raytraceResult.getType() == BlockHitResult.Type.BLOCK
                    && level.getBlockState(raytraceResult.getBlockPos()).getBlock() instanceof net.minecraft.world.level.block.LiquidBlock) {
                releasePos = raytraceResult.getBlockPos();
            } else {
                return InteractionResultHolder.pass(bin);
            }

            int slot = player.getInventory().findSlotMatchingItem(bin);
            if (player.level().isClientSide()) {
                Creatures.PROXY.openBinScreen(bin, slot, releasePos);
            }
            return InteractionResultHolder.success(bin);
        }

        return InteractionResultHolder.pass(bin);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level,
                                List<Component> tooltip, TooltipFlag flag) {
        int count = getFishCount(stack);
        tooltip.add(Component.translatable("creatures.message.bin.count", count, MAX_CAPACITY)
                .withStyle(ChatFormatting.GRAY));
        if (count > 0) {
            ListTag list = getFishList(stack);
            for (int i = 0; i < list.size(); i++) {
                String species = list.getCompound(i).getString("Species");
                tooltip.add(Component.literal("• " + species)
                        .withStyle(Style.EMPTY.withItalic(true).withColor(0xAAAAAA)));
            }
        }
    }
}