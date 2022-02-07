package com.creatures.afrikinzi.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.pathfinding.PathNavigateGround;

public class CreaturesAISwimming extends EntityAIBase {
    private final EntityLiving entity;

    public CreaturesAISwimming(EntityLiving entityIn)
    {
        this.entity = entityIn;
        this.setMutexBits(4);

        if (entityIn.getNavigator() instanceof PathNavigateGround)
        {
            ((PathNavigateGround)entityIn.getNavigator()).setCanSwim(true);
        }
        else if (entityIn.getNavigator() instanceof PathNavigateFlying)
        {
            ((PathNavigateFlying)entityIn.getNavigator()).setCanFloat(true);
        }
    }

    public boolean shouldExecute()
    {
        return this.entity.isInWater() || this.entity.isInLava();
    }

    public void updateTask()
    {
        this.entity.getJumpHelper().setJumping();
    }
}
