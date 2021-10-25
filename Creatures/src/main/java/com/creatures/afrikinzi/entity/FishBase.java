package com.creatures.afrikinzi.entity;

import com.creatures.afrikinzi.config.CreaturesConfig;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FishBase extends EntityCreature {
    private static final DataParameter<Boolean> MOVING = EntityDataManager.<Boolean>createKey(FishBase.class, DataSerializers.BOOLEAN);
    protected float clientSideTailAnimation;
    protected float clientSideTailAnimationO;
    protected float clientSideTailAnimationSpeed;
    protected float clientSideSpikesAnimation;
    protected float clientSideSpikesAnimationO;
    private EntityLivingBase targetedEntity;
    private int clientSideAttackTime;
    private boolean clientSideTouchedGround;
    protected EntityAIWander wander;

    public FishBase(World worldIn)
    {
        super(worldIn);
        this.setSize(0.85F, 0.85F);
        this.moveHelper = new FishBase.FishMoveHelper(this);
        this.clientSideTailAnimation = this.rand.nextFloat();
        this.clientSideTailAnimationO = this.clientSideTailAnimation;
    }

    public static void registerFixesGuardian(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, FishBase.class);
    }

    protected void initEntityAI()
    {
        this.wander = new EntityAIWander(this, 0.2D, 1);
        this.tasks.addTask(7, this.wander);
        this.wander.setMutexBits(3);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(MOVING, Boolean.valueOf(false));
    }

    public boolean isMoving()
    {
        return ((Boolean)this.dataManager.get(MOVING)).booleanValue();
    }

    private void setMoving(boolean moving)
    {
        this.dataManager.set(MOVING, Boolean.valueOf(moving));
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }


    public void onLivingUpdate()
    {
        if (this.world.isRemote)
        {

            if (!this.isInWater())
            {

                if (this.motionY > 0.0D && this.clientSideTouchedGround && !this.isSilent())
                {

                }

                this.clientSideTouchedGround = this.motionY < 0.0D && this.world.isBlockNormalCube((new BlockPos(this)).down(), false);
            }
            else if (this.isMoving())
            {

            }
            else
            {
                this.clientSideTailAnimationSpeed += (0.125F - this.clientSideTailAnimationSpeed) * 0.2F;
            }

            this.clientSideTailAnimation += this.clientSideTailAnimationSpeed;
            this.clientSideSpikesAnimationO = this.clientSideSpikesAnimation;

            if (!this.isInWater())
            {
                this.clientSideSpikesAnimation = this.rand.nextFloat();
            }
            else if (this.isMoving())
            {
                //this.clientSideSpikesAnimation += (0.0F - this.clientSideSpikesAnimation) * 0.25F;
            }
            else
            {
                this.clientSideSpikesAnimation += (1.0F - this.clientSideSpikesAnimation) * 0.06F;
            }

            if (this.isMoving() && this.isInWater())
            {
                Vec3d vec3d = this.getLook(0.0F);
            }
        }

        if (this.inWater)
        {
            this.setAir(300);
        }

        super.onLivingUpdate();
    }

    public boolean isNotColliding()
    {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty();
    }


    public void travel(float strafe, float vertical, float forward)
    {
        if (this.isServerWorld() && this.isInWater())
        {
            this.moveRelative(strafe, vertical, forward, 0.1F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.8999999761581421D;
            this.motionY *= 0.8999999761581421D;
            this.motionZ *= 0.8999999761581421D;
        }
        else
        {
            super.travel(strafe, vertical, forward);
        }
    }


    static class FishMoveHelper extends EntityMoveHelper
    {
        private final FishBase fish;

        public FishMoveHelper(FishBase fishbase)
        {
            super(fishbase);
            this.fish = fishbase;
        }

        public void onUpdateMoveHelper()
        {
            if (this.action == EntityMoveHelper.Action.MOVE_TO && !this.fish.getNavigator().noPath())
            {
                double d0 = this.posX - this.fish.posX;
                double d1 = this.posY - this.fish.posY;
                double d2 = this.posZ - this.fish.posZ;
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
                this.fish.rotationYaw = this.limitAngle(this.fish.rotationYaw, f, 90.0F);
                this.fish.renderYawOffset = this.fish.rotationYaw;
                float f1 = (float)(this.speed * this.fish.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                this.fish.setAIMoveSpeed(this.fish.getAIMoveSpeed() + (f1 - this.fish.getAIMoveSpeed()) * 0.125F);
                double d4 = Math.sin((double)(this.fish.ticksExisted + this.fish.getEntityId()) * 0.5D) * 0.05D;
                double d5 = Math.cos((double)(this.fish.rotationYaw * 0.017453292F));
                double d6 = Math.sin((double)(this.fish.rotationYaw * 0.017453292F));
                //this.fish.motionX += d4 * d5;
                //this.fish.motionZ += d4 * d6;
                d4 = Math.sin((double)(this.fish.ticksExisted + this.fish.getEntityId()) * 0.75D) * 0.05D;
                this.fish.motionY += d4 * (d6 + d5) * 0.25D;
                this.fish.motionY += (double)this.fish.getAIMoveSpeed() * d1 * 0.1D;
                EntityLookHelper entitylookhelper = this.fish.getLookHelper();
                double d7 = this.fish.posX + d0 / d3 * 2.0D;
                double d8 = (double)this.fish.getEyeHeight() + this.fish.posY + d1 / d3;
                double d9 = this.fish.posZ + d2 / d3 * 2.0D;
                double d10 = entitylookhelper.getLookPosX();
                double d11 = entitylookhelper.getLookPosY();
                double d12 = entitylookhelper.getLookPosZ();

                if (!entitylookhelper.getIsLooking())
                {
                    d10 = d7;
                    d11 = d8;
                    d12 = d9;
                }

                this.fish.getLookHelper().setLookPosition(d10 + (d7 - d10) * 0.125D, d11 + (d8 - d11) * 0.125D, d12 + (d9 - d12) * 0.125D, 10.0F, 40.0F);
                this.fish.setMoving(true);
            }
            else
            {
                this.fish.setAIMoveSpeed(0.0F);
                this.fish.setMoving(false);
            }
        }
    }

    public int getVerticalFaceSpeed()
    {
        return 180;
    }

    public float getBlockPathWeight(BlockPos pos)
    {
        return this.world.getBlockState(pos).getMaterial() == Material.WATER ? 10.0F + this.world.getLightBrightness(pos) - 0.5F : super.getBlockPathWeight(pos);
    }

    protected PathNavigate createNavigator(World worldIn)
    {
        return new PathNavigateSwimmer(this, worldIn);
    }

    public boolean canBreatheUnderwater()
    {
        return true;
    }

    public void onEntityUpdate()
    {
        int i = this.getAir();
        super.onEntityUpdate();

        if (this.isEntityAlive() && !this.isInWater())
        {
            --i;
            this.setAir(i);

            if (this.getAir() == -20)
            {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.DROWN, 2.0F);
            }
        }
        else
        {
            this.setAir(300);
        }
    }

    public boolean isPushedByWater()
    {
        return false;
    }

    @Override
    protected boolean canDespawn()
    {
        if (CreaturesConfig.fishDespawns == true) {
        return true; }
        else {
            return false;
        }
    }

}
