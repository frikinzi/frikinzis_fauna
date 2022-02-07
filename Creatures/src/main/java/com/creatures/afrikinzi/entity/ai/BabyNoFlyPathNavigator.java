package com.creatures.afrikinzi.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BabyNoFlyPathNavigator extends PathNavigate {

    public BabyNoFlyPathNavigator(EntityLiving p_i47412_1_, World p_i47412_2_)
    {
        super(p_i47412_1_, p_i47412_2_);
    }

    protected PathFinder getPathFinder()
    {
        if (this.entity.isChild()) {
            this.nodeProcessor = new WalkNodeProcessor();
            this.nodeProcessor.setCanEnterDoors(true);
            return new PathFinder(this.nodeProcessor);
        }
        else {
        this.nodeProcessor = new FlyingNodeProcessor();
        this.nodeProcessor.setCanEnterDoors(true);
        return new PathFinder(this.nodeProcessor);
        }
    }

    protected boolean canNavigate()
    {
        if (this.entity.isChild()) {
            return this.entity.onGround || this.getCanSwim() && this.isInLiquid() || this.entity.isRiding();
        } else {
        return this.canFloat() && this.isInLiquid() || !this.entity.isRiding();
        }
    }

    protected Vec3d getEntityPosition()
    {
        if (this.entity.isChild()) {
            return new Vec3d(this.entity.posX, (double)this.getPathablePosY(), this.entity.posZ);
        } else {
        return new Vec3d(this.entity.posX, this.entity.posY, this.entity.posZ);
        }
    }

    public Path getPathToEntityLiving(Entity entityIn)
    {
        return this.getPathToPos(new BlockPos(entityIn));
    }

    public void onUpdateNavigation() {
        if (!this.entity.isChild()) {
            ++this.totalTicks;

            if (this.tryUpdatePath) {
                this.updatePath();
            }

            if (!this.noPath()) {
                if (this.canNavigate()) {
                    this.pathFollow();
                } else if (this.currentPath != null && this.currentPath.getCurrentPathIndex() < this.currentPath.getCurrentPathLength()) {
                    Vec3d vec3d = this.currentPath.getVectorFromIndex(this.entity, this.currentPath.getCurrentPathIndex());

                    if (MathHelper.floor(this.entity.posX) == MathHelper.floor(vec3d.x) && MathHelper.floor(this.entity.posY) == MathHelper.floor(vec3d.y) && MathHelper.floor(this.entity.posZ) == MathHelper.floor(vec3d.z)) {
                        this.currentPath.setCurrentPathIndex(this.currentPath.getCurrentPathIndex() + 1);
                    }
                }

                this.debugPathFinding();

                if (!this.noPath()) {
                    Vec3d vec3d1 = this.currentPath.getPosition(this.entity);
                    this.entity.getMoveHelper().setMoveTo(vec3d1.x, vec3d1.y, vec3d1.z, this.speed);
                }
            }
        } else {
            super.onUpdateNavigation();
        }
    }

    protected boolean isDirectPathBetweenPoints(Vec3d posVec31, Vec3d posVec32, int sizeX, int sizeY, int sizeZ) {
        if (this.entity.isChild()) {
            int i = MathHelper.floor(posVec31.x);
            int j = MathHelper.floor(posVec31.z);
            double d0 = posVec32.x - posVec31.x;
            double d1 = posVec32.z - posVec31.z;
            double d2 = d0 * d0 + d1 * d1;

            if (d2 < 1.0E-8D) {
                return false;
            } else {
                double d3 = 1.0D / Math.sqrt(d2);
                d0 = d0 * d3;
                d1 = d1 * d3;
                sizeX = sizeX + 2;
                sizeZ = sizeZ + 2;

                if (!this.isSafeToStandAt(i, (int) posVec31.y, j, sizeX, sizeY, sizeZ, posVec31, d0, d1)) {
                    return false;
                } else {
                    sizeX = sizeX - 2;
                    sizeZ = sizeZ - 2;
                    double d4 = 1.0D / Math.abs(d0);
                    double d5 = 1.0D / Math.abs(d1);
                    double d6 = (double) i - posVec31.x;
                    double d7 = (double) j - posVec31.z;

                    if (d0 >= 0.0D) {
                        ++d6;
                    }

                    if (d1 >= 0.0D) {
                        ++d7;
                    }

                    d6 = d6 / d0;
                    d7 = d7 / d1;
                    int k = d0 < 0.0D ? -1 : 1;
                    int l = d1 < 0.0D ? -1 : 1;
                    int i1 = MathHelper.floor(posVec32.x);
                    int j1 = MathHelper.floor(posVec32.z);
                    int k1 = i1 - i;
                    int l1 = j1 - j;

                    while (k1 * k > 0 || l1 * l > 0) {
                        if (d6 < d7) {
                            d6 += d4;
                            i += k;
                            k1 = i1 - i;
                        } else {
                            d7 += d5;
                            j += l;
                            l1 = j1 - j;
                        }

                        if (!this.isSafeToStandAt(i, (int) posVec31.y, j, sizeX, sizeY, sizeZ, posVec31, d0, d1)) {
                            return false;
                        }
                    }

                    return true;
                }
            }
        } else {
            int i = MathHelper.floor(posVec31.x);
            int j = MathHelper.floor(posVec31.y);
            int k = MathHelper.floor(posVec31.z);
            double d0 = posVec32.x - posVec31.x;
            double d1 = posVec32.y - posVec31.y;
            double d2 = posVec32.z - posVec31.z;
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
                double d8 = (double) i - posVec31.x;
                double d9 = (double) j - posVec31.y;
                double d10 = (double) k - posVec31.z;

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
                int k1 = MathHelper.floor(posVec32.x);
                int l1 = MathHelper.floor(posVec32.y);
                int i2 = MathHelper.floor(posVec32.z);
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
    }

    public void setCanOpenDoors(boolean p_192879_1_)
    {
        this.nodeProcessor.setCanOpenDoors(p_192879_1_);
    }

    public void setCanEnterDoors(boolean p_192878_1_)
    {
        this.nodeProcessor.setCanEnterDoors(p_192878_1_);
    }

    public void setCanFloat(boolean p_192877_1_)
    {
        this.nodeProcessor.setCanSwim(determineFloat());
    }

    public boolean determineFloat() {
        if (this.entity.isChild()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean canFloat()
    {
        return this.nodeProcessor.getCanSwim();
    }

    public boolean canEntityStandOnPos(BlockPos pos)
    {
        return this.world.getBlockState(pos).isTopSolid();
    }

    public boolean getCanSwim()
    {
        return this.nodeProcessor.getCanSwim();
    }


    private boolean isPositionClear(int x, int y, int z, int sizeX, int sizeY, int sizeZ, Vec3d p_179692_7_, double p_179692_8_, double p_179692_10_) {
        if (this.entity.isChild()) {
            for (BlockPos blockpos : BlockPos.getAllInBox(new BlockPos(x, y, z), new BlockPos(x + sizeX - 1, y + sizeY - 1, z + sizeZ - 1))) {
                double d0 = (double) blockpos.getX() + 0.5D - p_179692_7_.x;
                double d1 = (double) blockpos.getZ() + 0.5D - p_179692_7_.z;

                if (d0 * p_179692_8_ + d1 * p_179692_10_ >= 0.0D) {
                    Block block = this.world.getBlockState(blockpos).getBlock();

                    if (!block.isPassable(this.world, blockpos)) {
                        return false;
                    }
                }
            }

            return true;
        }
        return true;
    }



    private boolean isSafeToStandAt(int x, int y, int z, int sizeX, int sizeY, int sizeZ, Vec3d vec31, double p_179683_8_, double p_179683_10_)
    {
        int i = x - sizeX / 2;
        int j = z - sizeZ / 2;

        if (!this.isPositionClear(i, y, j, sizeX, sizeY, sizeZ, vec31, p_179683_8_, p_179683_10_))
        {
            return false;
        }
        else
        {
            for (int k = i; k < i + sizeX; ++k)
            {
                for (int l = j; l < j + sizeZ; ++l)
                {
                    double d0 = (double)k + 0.5D - vec31.x;
                    double d1 = (double)l + 0.5D - vec31.z;

                    if (d0 * p_179683_8_ + d1 * p_179683_10_ >= 0.0D)
                    {
                        PathNodeType pathnodetype = this.nodeProcessor.getPathNodeType(this.world, k, y - 1, l, this.entity, sizeX, sizeY, sizeZ, true, true);

                        if (pathnodetype == PathNodeType.WATER)
                        {
                            return false;
                        }

                        if (pathnodetype == PathNodeType.LAVA)
                        {
                            return false;
                        }

                        if (pathnodetype == PathNodeType.OPEN)
                        {
                            return false;
                        }

                        pathnodetype = this.nodeProcessor.getPathNodeType(this.world, k, y, l, this.entity, sizeX, sizeY, sizeZ, true, true);
                        float f = this.entity.getPathPriority(pathnodetype);

                        if (f < 0.0F || f >= 8.0F)
                        {
                            return false;
                        }

                        if (pathnodetype == PathNodeType.DAMAGE_FIRE || pathnodetype == PathNodeType.DANGER_FIRE || pathnodetype == PathNodeType.DAMAGE_OTHER)
                        {
                            return false;
                        }
                    }
                }
            }

            return true;
        }
    }


    private int getPathablePosY() {
        if (this.entity.isChild()) {
            if (this.entity.isInWater() && this.getCanSwim()) {
                int i = (int) this.entity.getEntityBoundingBox().minY;
                Block block = this.world.getBlockState(new BlockPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ))).getBlock();
                int j = 0;

                while (block == Blocks.FLOWING_WATER || block == Blocks.WATER) {
                    ++i;
                    block = this.world.getBlockState(new BlockPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ))).getBlock();
                    ++j;

                    if (j > 16) {
                        return (int) this.entity.getEntityBoundingBox().minY;
                    }
                }

                return i;
            } else {
                return (int) (this.entity.getEntityBoundingBox().minY + 0.5D);
            }
        } else {
            if (this.entity.isInWater() && this.getCanSwim()) {
                int i = (int) this.entity.getEntityBoundingBox().minY;
                Block block = this.world.getBlockState(new BlockPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ))).getBlock();
                int j = 0;

                while (block == Blocks.FLOWING_WATER || block == Blocks.WATER) {
                    ++i;
                    block = this.world.getBlockState(new BlockPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ))).getBlock();
                    ++j;

                    if (j > 16) {
                        return (int) this.entity.getEntityBoundingBox().minY;
                    }
                }

                return i;
            } else {
                return (int) (this.entity.getEntityBoundingBox().minY + 0.5D);
            }
        }
    }


}
