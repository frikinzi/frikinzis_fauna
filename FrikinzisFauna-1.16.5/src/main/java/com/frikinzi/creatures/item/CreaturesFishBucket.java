package com.frikinzi.creatures.item;

import com.frikinzi.creatures.entity.base.FishBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class CreaturesFishBucket extends BucketItem {
    private final EntityType<?> type;

    @Deprecated
    public CreaturesFishBucket(EntityType<?> p_i49022_1_, Fluid p_i49022_2_, Item.Properties p_i49022_3_) {
        super(p_i49022_2_, p_i49022_3_);
        this.type = p_i49022_1_;
        this.fishTypeSupplier = () -> p_i49022_1_;
    }

    public CreaturesFishBucket(java.util.function.Supplier<? extends EntityType<?>> fishTypeIn, java.util.function.Supplier<? extends Fluid> p_i49022_2_, Item.Properties builder) {
        super(p_i49022_2_, builder);
        this.type = null;
        this.fishTypeSupplier = fishTypeIn;
    }

    public void checkExtraContent(World p_203792_1_, ItemStack p_203792_2_, BlockPos p_203792_3_) {
        if (p_203792_1_ instanceof ServerWorld) {
            this.spawn((ServerWorld)p_203792_1_, p_203792_2_, p_203792_3_);
        }

    }

    protected void playEmptySound(@Nullable PlayerEntity p_203791_1_, IWorld p_203791_2_, BlockPos p_203791_3_) {
        p_203791_2_.playSound(p_203791_1_, p_203791_3_, SoundEvents.BUCKET_EMPTY_FISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
    }

    private void spawn(ServerWorld p_205357_1_, ItemStack p_205357_2_, BlockPos p_205357_3_) {
        Entity entity = this.type.spawn(p_205357_1_, p_205357_2_, (PlayerEntity)null, p_205357_3_, SpawnReason.BUCKET, true, false);
        if (entity != null) {
            ((AbstractFishEntity)entity).setFromBucket(true);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        if (this.type == EntityType.TROPICAL_FISH) {
            CompoundNBT compoundnbt = p_77624_1_.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                TextFormatting[] atextformatting = new TextFormatting[]{TextFormatting.ITALIC, TextFormatting.GRAY};
                String s = "color.minecraft." + TropicalFishEntity.getBaseColor(i);
                String s1 = "color.minecraft." + TropicalFishEntity.getPatternColor(i);

                for(int j = 0; j < TropicalFishEntity.COMMON_VARIANTS.length; ++j) {
                    if (i == TropicalFishEntity.COMMON_VARIANTS[j]) {
                        p_77624_3_.add((new TranslationTextComponent(TropicalFishEntity.getPredefinedName(j))).withStyle(atextformatting));
                        return;
                    }
                }

                p_77624_3_.add((new TranslationTextComponent(TropicalFishEntity.getFishTypeName(i))).withStyle(atextformatting));
                IFormattableTextComponent iformattabletextcomponent = new TranslationTextComponent(s);
                if (!s.equals(s1)) {
                    iformattabletextcomponent.append(", ").append(new TranslationTextComponent(s1));
                }

                iformattabletextcomponent.withStyle(atextformatting);
                p_77624_3_.add(iformattabletextcomponent);
            }
        }

    }

    private final java.util.function.Supplier<? extends EntityType<?>> fishTypeSupplier;
    protected EntityType<?> getFishType() {
        return fishTypeSupplier.get();
    }
}
