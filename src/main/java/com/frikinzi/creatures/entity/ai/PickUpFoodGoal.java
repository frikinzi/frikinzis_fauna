package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.entity.PelicanEntity;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;
import java.util.List;

public class PickUpFoodGoal extends Goal {
    private final CreaturesBirdEntity bird;
    private ItemEntity target;

    public PickUpFoodGoal(CreaturesBirdEntity bird) {
        this.bird = bird;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (bird instanceof PelicanEntity pelican) {
            if (pelican.isPouchFull()) return false;
        } else {
            if (!bird.getMainHandItem().isEmpty()) return false;
        }
        if (bird.isBaby()) return false;
        if (bird.pickupCooldown > 0) return false;

        List<ItemEntity> items = bird.level().getEntitiesOfClass(
                ItemEntity.class,
                bird.getBoundingBox().inflate(10.0),
                e -> e.isAlive() && bird.isFood(e.getItem())
        );
        if (items.isEmpty()) return false;
        target = items.get(0);
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (target == null || !target.isAlive() || !bird.isFood(target.getItem())) return false;
        if (bird instanceof PelicanEntity pelican) {
            return !pelican.isPouchFull();
        }
        return bird.getMainHandItem().isEmpty();
    }

    @Override
    public void stop() {
        target = null;
    }

    @Override
    public void tick() {
        if (target == null) return;
        bird.getLookControl().setLookAt(target);
        bird.getNavigation().moveTo(target, 1.2);

        if (bird.distanceTo(target) < 1.5) {
            ItemStack item = target.getItem().copy();
            item.setCount(1);
            if (bird instanceof PelicanEntity pelican) {
                pelican.addToPouch(item);
            }
            else {
                bird.setItemSlot(EquipmentSlot.MAINHAND, item);
            }
            bird.setGuaranteedDrop(EquipmentSlot.MAINHAND);
            target.getItem().shrink(1);
            if (target.getItem().isEmpty()) target.discard();
            target = null;
        }
    }
}