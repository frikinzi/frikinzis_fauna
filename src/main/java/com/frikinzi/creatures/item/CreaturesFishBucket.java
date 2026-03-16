package com.frikinzi.creatures.item;

import com.frikinzi.creatures.entity.base.FishBase;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class CreaturesFishBucket extends MobBucketItem {

    private final Supplier<? extends EntityType<?>> fishTypeSupplier;

    public CreaturesFishBucket(Supplier<? extends EntityType<?>> fishTypeIn, Supplier<? extends Fluid> fluidIn, Properties properties) {
        super(fishTypeIn, fluidIn, () -> SoundEvents.BUCKET_EMPTY_FISH, properties);
        this.fishTypeSupplier = fishTypeIn;
    }

    protected EntityType<?> getFishType() {
        return fishTypeSupplier.get();
    }

    @Override
    public void checkExtraContent(@Nullable Player player, Level level, ItemStack stack, BlockPos pos) {
        if (level instanceof ServerLevel serverLevel) {
            this.spawn(serverLevel, stack, pos);
        }
    }

    private void spawn(ServerLevel level, ItemStack stack, BlockPos pos) {
        Entity entity = getFishType().spawn(level, stack, null, pos, MobSpawnType.BUCKET, true, false);
        if (entity instanceof FishBase fish) {
            fish.setFromBucket(true);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (getFishType() == EntityType.TROPICAL_FISH) {
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains("BucketVariantTag", 3)) {
                int i = tag.getInt("BucketVariantTag");
                ChatFormatting[] formatting = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY};
                String baseColor = "color.minecraft." + TropicalFish.getBaseColor(i).getName();
                String patternColor = "color.minecraft." + TropicalFish.getPatternColor(i).getName();

                //tooltip.add(Component.translatable(TropicalFish.getFishTypeName(i)).withStyle(formatting));
                MutableComponent colorText = Component.translatable(baseColor);
                if (!baseColor.equals(patternColor)) {
                    colorText.append(", ").append(Component.translatable(patternColor));
                }
                colorText.withStyle(formatting);
                tooltip.add(colorText);
            }
        }
    }
}
