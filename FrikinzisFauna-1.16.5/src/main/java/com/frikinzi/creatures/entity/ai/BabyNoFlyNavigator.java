package com.frikinzi.creatures.entity.ai;


import com.frikinzi.creatures.entity.base.TameableBirdBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.network.DebugPacketSender;
import net.minecraft.pathfinding.FlyingNodeProcessor;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class BabyNoFlyNavigator extends GroundPathNavigator {
    public BabyNoFlyNavigator(MobEntity p_i47412_1_, World p_i47412_2_) {
        super(p_i47412_1_, p_i47412_2_);
    }

    protected PathFinder createPathFinder(int p_179679_1_) {
        if (!this.mob.isBaby()) {
            this.nodeEvaluator = new FlyingNodeProcessor();
            this.nodeEvaluator.setCanPassDoors(true);
            return new PathFinder(this.nodeEvaluator, p_179679_1_); }
        else {
            return super.createPathFinder(p_179679_1_);
        }
    }


    protected boolean canUpdatePath() {
        if (!this.mob.isBaby()) {
            return this.canFloat() && this.isInLiquid() || !this.mob.isPassenger(); }
        else {
            return this.mob.isOnGround() || this.isInLiquid() || this.mob.isPassenger();
        }
    }

    protected Vector3d getTempMobPos() {
        if (!this.mob.isBaby()) {
            return this.mob.position(); } else {
            return super.getTempMobPos();
        }
    }

    public Path createPath(Entity p_75494_1_, int p_75494_2_) {
        if (!this.mob.isBaby()) {
            return this.createPath(p_75494_1_.blockPosition(), p_75494_2_); }
        else {
            return super.createPath(p_75494_1_, p_75494_2_);
        }
    }

    public void tick() {
        if (!this.mob.isBaby()) {
            ++this.tick;
            if (this.hasDelayedRecomputation) {
                this.recomputePath();
            }

            if (!this.isDone()) {
                if (this.canUpdatePath()) {
                    this.followThePath();
                } else if (this.path != null && !this.path.isDone()) {
                    Vector3d vector3d = this.path.getNextEntityPos(this.mob);
                    if (MathHelper.floor(this.mob.getX()) == MathHelper.floor(vector3d.x) && MathHelper.floor(this.mob.getY()) == MathHelper.floor(vector3d.y) && MathHelper.floor(this.mob.getZ()) == MathHelper.floor(vector3d.z)) {
                        this.path.advance();
                    }
                }

                DebugPacketSender.sendPathFindingPacket(this.level, this.mob, this.path, this.maxDistanceToWaypoint);
                if (!this.isDone()) {
                    Vector3d vector3d1 = this.path.getNextEntityPos(this.mob);
                    this.mob.getMoveControl().setWantedPosition(vector3d1.x, vector3d1.y, vector3d1.z, this.speedModifier);
                }
            }
        } else {
            super.tick();
        }
    }

    protected boolean canMoveDirectly(Vector3d p_75493_1_, Vector3d p_75493_2_, int p_75493_3_, int p_75493_4_, int p_75493_5_) {
        if (!this.mob.isBaby()) {
            int i = MathHelper.floor(p_75493_1_.x);
            int j = MathHelper.floor(p_75493_1_.y);
            int k = MathHelper.floor(p_75493_1_.z);
            double d0 = p_75493_2_.x - p_75493_1_.x;
            double d1 = p_75493_2_.y - p_75493_1_.y;
            double d2 = p_75493_2_.z - p_75493_1_.z;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if (d3 < 1.0E-8D) {
                return false;
            } else {
                double d4 = 1.0D / Math.sqrt(d3);
                d0 = d0 * d4;
                d1 = d1 * d4;
                d2 = d2 * d4;
                double d5 = 1.0D / Math.abs(d0);
                double d6 = 1.0D / Math.abs(d1);
                double d7 = 1.0D / Math.abs(d2);
                double d8 = (double) i - p_75493_1_.x;
                double d9 = (double) j - p_75493_1_.y;
                double d10 = (double) k - p_75493_1_.z;
                if (d0 >= 0.0D) {
                    ++d8;
                }

                if (d1 >= 0.0D) {
                    ++d9;
                }

                if (d2 >= 0.0D) {
                    ++d10;
                }

                d8 = d8 / d0;
                d9 = d9 / d1;
                d10 = d10 / d2;
                int l = d0 < 0.0D ? -1 : 1;
                int i1 = d1 < 0.0D ? -1 : 1;
                int j1 = d2 < 0.0D ? -1 : 1;
                int k1 = MathHelper.floor(p_75493_2_.x);
                int l1 = MathHelper.floor(p_75493_2_.y);
                int i2 = MathHelper.floor(p_75493_2_.z);
                int j2 = k1 - i;
                int k2 = l1 - j;
                int l2 = i2 - k;

                while (j2 * l > 0 || k2 * i1 > 0 || l2 * j1 > 0) {
                    if (d8 < d10 && d8 <= d9) {
                        d8 += d5;
                        i += l;
                        j2 = k1 - i;
                    } else if (d9 < d8 && d9 <= d10) {
                        d9 += d6;
                        j += i1;
                        k2 = l1 - j;
                    } else {
                        d10 += d7;
                        k += j1;
                        l2 = i2 - k;
                    }
                }

                return true;
            }
        }
        else {
            return super.canMoveDirectly(p_75493_1_, p_75493_2_, p_75493_3_, p_75493_4_, p_75493_5_);
        }
    }

    public void setCanOpenDoors(boolean p_192879_1_) {
        this.nodeEvaluator.setCanOpenDoors(p_192879_1_);
    }

    public void setCanPassDoors(boolean p_192878_1_) {
        this.nodeEvaluator.setCanPassDoors(p_192878_1_);
    }

    public boolean isStableDestination(BlockPos p_188555_1_) {
        if (!this.mob.isBaby()) {
            return this.level.getBlockState(p_188555_1_).entityCanStandOn(this.level, p_188555_1_, this.mob);
        } else {
            return super.isStableDestination(p_188555_1_);
        }
    }
}