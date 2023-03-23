package com.frikinzi.creatures.item;

import com.frikinzi.creatures.Creatures;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.ForgeSpawnEggItem;

import java.util.function.Supplier;

public class CreaturesSpawnEgg extends ForgeSpawnEggItem {
    private Supplier<? extends EntityType<?>> typeGetter;

    public CreaturesSpawnEgg(Supplier<? extends EntityType<?>> typeIn, final int i, final int j) {
        super(null, i, j, new Item.Properties().stacksTo(64).tab(Creatures.CREATURES_TAB));
        typeGetter = typeIn;
        // Have to manually add dispenser behavior due to forge item registry event
        // running too late.
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

//    @Override
//    public EntityType<?> getType(@Nullable CompoundTag p_208076_1_) {
//        return typeGetter.get();
//    }
}
