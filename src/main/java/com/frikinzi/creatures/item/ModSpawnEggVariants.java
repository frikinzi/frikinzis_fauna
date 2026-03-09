package com.frikinzi.creatures.item;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LovebirdEntity;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeSpawnEggItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ModSpawnEggVariants extends ForgeSpawnEggItem {
    private Supplier<? extends EntityType<?>> typeGetter;
    private int currentSpecies;
    private final Supplier<? extends EntityType<? extends Mob>> entityType;

    public ModSpawnEggVariants(Supplier<? extends EntityType<? extends Mob>> typeIn, final int i, final int j) {
        super(typeIn, i, j, new Item.Properties().stacksTo(64));
        typeGetter = typeIn;
        this.entityType = typeIn;

        this.currentSpecies = 0;
        DispenserBlock.registerBehavior(this, new DefaultDispenseItemBehavior() {
            public ItemStack execute(BlockSource source, ItemStack stack) {
                Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
                EntityType<?> entitytype = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
                entitytype.spawn(source.getLevel(), stack, null, source.getPos().relative(direction),
                        MobSpawnType.DISPENSER, direction != Direction.UP, false);
                stack.shrink(1);
                return stack;
            }
        });
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("item.creatures.spawn_egg_current", this.getCurrentSpeciesName(stack)).withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
    }


    public String getCurrentSpeciesName(ItemStack stack) {
        if (stack.hasTag()) {
            if (stack.getTag().contains("EntityTag")) {
                this.currentSpecies = stack.getTag().getCompound("EntityTag").getInt("Variant");
            }
        }
        EntityType<?> entitytype = this.getType(stack.getTag());
        if (entitytype == CreaturesEntities.LOVEBIRD.get()) {
            if (this.currentSpecies > 0) {
                if (LovebirdEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                    return LovebirdEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                }
            }
        }
        return "Random";

    }

    public void increaseSpeciesCount(Level world) {
        CreaturesBirdEntity bird = (CreaturesBirdEntity) this.entityType.get().create(world);
        this.currentSpecies += 1;
        if (this.currentSpecies > bird.numVariants()) {
            this.currentSpecies = 0;
        }

    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!(worldIn instanceof ServerLevel)) {
            return InteractionResultHolder.success(itemstack);
        }
        if (playerIn.isSteppingCarefully()) {
            this.increaseSpeciesCount(worldIn);
            itemstack.setTag(new CompoundTag());
            if (!(this.currentSpecies <= 0)) {
                CompoundTag entityNBT = new CompoundTag();
                entityNBT.putInt("Variant", this.currentSpecies);
                itemstack.getTag().put("EntityTag", entityNBT);
            }
            playerIn.displayClientMessage(Component.translatable("item.creatures.spawn_egg_change", this.getCurrentSpeciesName(itemstack)), true);
            return InteractionResultHolder.pass(itemstack);
        }

        BlockHitResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.SOURCE_ONLY);
        if (raytraceresult.getType() != BlockHitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            BlockPos blockpos = raytraceresult.getBlockPos();
            if (!(worldIn.getBlockState(blockpos).getBlock() instanceof LiquidBlock)) {
                return InteractionResultHolder.pass(itemstack);
            } else if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos, raytraceresult.getDirection(), itemstack)) {
                EntityType<?> entitytype = this.getType(itemstack.getTag());
                CreaturesBirdEntity bird = (CreaturesBirdEntity) entitytype.spawn((ServerLevel) worldIn, itemstack, playerIn, blockpos, MobSpawnType.SPAWN_EGG, false, false);
                if (bird == null) {
                    return InteractionResultHolder.pass(itemstack);
                } else {
                    if (!playerIn.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }

                    if (this.currentSpecies == 0) {
                        bird.setVariant(bird.methodOfDeterminingVariant());
                    } else if (this.currentSpecies > 0) {
                        bird.setVariant(this.currentSpecies);
                    }
                    ((ServerLevel) worldIn).addFreshEntityWithPassengers(bird);
                    playerIn.awardStat(Stats.ITEM_USED.get(this));
                    worldIn.gameEvent(playerIn, GameEvent.ENTITY_PLACE, blockpos);
                    return InteractionResultHolder.consume(itemstack);
                }

            }
            }  return InteractionResultHolder.fail(itemstack);

    }

}





//    @Override
//    public EntityType<?> getType(@Nullable CompoundTag p_208076_1_) {
//        return typeGetter.get();
//    }

