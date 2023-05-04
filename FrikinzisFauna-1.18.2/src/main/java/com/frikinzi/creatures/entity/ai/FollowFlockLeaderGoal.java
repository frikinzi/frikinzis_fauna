package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.mojang.datafixers.DataFixUtils;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;

import java.util.List;
import java.util.function.Predicate;

public class FollowFlockLeaderGoal extends Goal {
    private static final int INTERVAL_TICKS = 200;
    private final CreaturesBirdEntity mob;
    private int timeToRecalcPath;
    private int nextStartTick;


    public FollowFlockLeaderGoal(CreaturesBirdEntity p_25249_) {
        this.mob = p_25249_;
        this.nextStartTick = this.nextStartTick(p_25249_);
    }


    protected int nextStartTick(CreaturesBirdEntity p_25252_) {
        return reducedTickDelay(200 + p_25252_.getRandom().nextInt(200) % 20);
    }

    public boolean canUse() {
        if (this.mob.isTame()) {
            return false;
        }
        if (this.mob.hasFollowers()) {
            return false;
        } else if (this.mob.isFollower()) {
            return true;
        } else if (this.nextStartTick > 0) {
            --this.nextStartTick;
            return false;
        } else {
            this.nextStartTick = this.nextStartTick(this.mob);
            Predicate<CreaturesBirdEntity> predicate = (p_25258_) -> {
                return p_25258_.canBeFollowed() || !p_25258_.isFollower();
            };
            List<? extends CreaturesBirdEntity> list = this.mob.level.getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
            CreaturesBirdEntity abstractschoolingfish = DataFixUtils.orElse(list.stream().filter(CreaturesBirdEntity::canBeFollowed).findAny(), this.mob);
            abstractschoolingfish.addFollowers(list.stream().filter((p_25255_) -> {
                return !p_25255_.isFollower();
            }));
            return this.mob.isFollower();
        }
    }

    public boolean canContinueToUse() {
        return this.mob.isFollower() && this.mob.inRangeOfLeader();
    }

    public void start() {
        this.timeToRecalcPath = 0;
    }

    public void stop() {
        this.mob.stopFollowing();
    }

    public void tick() {
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            this.mob.pathToLeader();
        }
    }

}
