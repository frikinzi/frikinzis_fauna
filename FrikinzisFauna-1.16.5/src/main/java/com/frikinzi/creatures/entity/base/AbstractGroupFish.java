package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.entity.ai.FollowLeaderGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

abstract public class AbstractGroupFish extends AbstractFish {
    private AbstractGroupFish leader;
    private int schoolSize = 1;

    public AbstractGroupFish(EntityType<? extends AbstractGroupFish> p_i49856_1_, World p_i49856_2_) {
        super(p_i49856_1_, p_i49856_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new FollowLeaderGoal(this));
    }

    public int getMaxSpawnClusterSize() {
        return this.getMaxSchoolSize();
    }

    public int getMaxSchoolSize() {
        return super.getMaxSpawnClusterSize();
    }

    protected boolean canRandomSwim() {
        return !this.isFollower();
    }

    public boolean isFollower() {
        return this.leader != null && this.leader.isAlive();
    }

    public AbstractGroupFish startFollowing(AbstractGroupFish p_212803_1_) {
        this.leader = p_212803_1_;
        p_212803_1_.addFollower();
        return p_212803_1_;
    }

    public void stopFollowing() {
        this.leader.removeFollower();
        this.leader = null;
    }

    private void addFollower() {
        ++this.schoolSize;
    }

    private void removeFollower() {
        --this.schoolSize;
    }

    public boolean canBeFollowed() {
        return this.hasFollowers() && this.schoolSize < this.getMaxSchoolSize();
    }

    public void tick() {
        super.tick();
        if (this.hasFollowers() && this.level.random.nextInt(200) == 1) {
            List<AbstractFish> list = this.level.getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
            if (list.size() <= 1) {
                this.schoolSize = 1;
            }
        }

    }

    public boolean hasFollowers() {
        return this.schoolSize > 1;
    }

    public boolean inRangeOfLeader() {
        return this.distanceToSqr(this.leader) <= 121.0D;
    }

    public void pathToLeader() {
        if (this.isFollower()) {
            this.getNavigation().moveTo(this.leader, 1.0D);
        }

    }

    public void addFollowers(Stream<AbstractGroupFish> p_212810_1_) {
        p_212810_1_.limit((long)(this.getMaxSchoolSize() - this.schoolSize)).filter((p_212801_1_) -> {
            return p_212801_1_ != this;
        }).forEach((p_212804_1_) -> {
            p_212804_1_.startFollowing(this);
        });
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
        if (p_213386_4_ == null) {
            p_213386_4_ = new AbstractGroupFish.GroupData(this);
        } else {
            this.startFollowing(((AbstractGroupFish.GroupData)p_213386_4_).leader);
        }

        return p_213386_4_;
    }

    public static class GroupData implements ILivingEntityData {
        public final AbstractGroupFish leader;

        public GroupData(AbstractGroupFish p_i49858_1_) {
            this.leader = p_i49858_1_;
        }
    }
}
