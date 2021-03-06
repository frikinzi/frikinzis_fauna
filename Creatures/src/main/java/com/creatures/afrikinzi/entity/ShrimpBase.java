package com.creatures.afrikinzi.entity;

import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.init.MobEffects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ShrimpBase extends EntityWaterMob {
    public float fishPitch;
    public float prevFishPitch;
    public float fishYaw;
    public float prevFishYaw;
    public float fishRotation;
    public float prevSquidRotation;
    public float tentacleAngle;
    public float lastTentacleAngle;
    public float randomMotionSpeed;
    public float rotationVelocity;
    public float rotateSpeed;
    public float randomMotionVecX;
    public float randomMotionVecY;
    public float randomMotionVecZ;

    public ShrimpBase(World worldIn)
    {
        super(worldIn);
        this.setSize(0.8F, 0.8F);
        this.rand.setSeed((long)(1 + this.getEntityId()));
        this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
    }


    //public static void registerFixesSquid(DataFixer fixer)
    //{
    //    EntityLiving.registerFixesMob(fixer, FishBase.class);
    //}

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new ShrimpBase.AIMoveRandom(this));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }

    public float getEyeHeight()
    {
        return this.height * 0.5F;
    }

    protected boolean canTriggerWalking()
    {
        return true;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return null;
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        this.prevFishPitch = this.fishPitch;
        this.prevFishYaw = this.fishYaw;
        this.prevSquidRotation = this.fishRotation;
        this.lastTentacleAngle = this.tentacleAngle;
        this.fishRotation += this.rotationVelocity;
        if ((double)this.fishRotation > (Math.PI * 2D))
        {
            if (this.world.isRemote)
            {
                this.fishRotation = ((float)Math.PI * 2F);
            }
            else
            {
                this.fishRotation = (float)((double)this.fishRotation - (Math.PI * 2D));

                if (this.rand.nextInt(10) == 0)
                {
                    this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
                }

                this.world.setEntityState(this, (byte)19);
            }
        }

        if (this.inWater)
        {
            if (this.fishRotation < (float)Math.PI)
            {
                float f = this.fishRotation / (float)Math.PI;
                this.tentacleAngle = MathHelper.sin(f * f * (float)Math.PI) * (float)Math.PI * 0.25F;

                if ((double)f > 0.75D)
                {
                    this.randomMotionSpeed = 0.9F;
                    this.rotateSpeed = 1.0F;
                }
                else
                {
                    this.rotateSpeed *= 0.8F;
                }
            }
            else
            {
                this.tentacleAngle = 0.0F;
                this.randomMotionSpeed *= 0.8F;
                this.rotateSpeed *= 0.99F;
            }

            if (!this.world.isRemote)
            {
                this.motionX = (double)(this.randomMotionVecX * this.randomMotionSpeed);
                this.motionY = (double)(this.randomMotionVecY * this.randomMotionSpeed);
                this.motionZ = (double)(this.randomMotionVecZ * this.randomMotionSpeed);
            }

            float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.renderYawOffset += (-((float)MathHelper.atan2(this.motionX, this.motionZ)) * (180F / (float)Math.PI) - this.renderYawOffset) * 0.1F;
            this.rotationYaw = this.renderYawOffset;
            this.fishYaw = (float)((double)this.fishYaw + Math.PI * (double)this.rotateSpeed * 1.5D);
            this.fishPitch += (-((float)MathHelper.atan2((double)f1, this.motionY)) * (180F / (float)Math.PI) - this.fishPitch) * 0.1F;
        }
        else
        {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.fishRotation)) * (float)Math.PI * 0.25F;

            if (!this.world.isRemote)
            {
                this.motionX = 0.0D;
                this.motionZ = 0.0D;

                if (this.isPotionActive(MobEffects.LEVITATION))
                {
                    this.motionY += 0.05D * (double)(this.getActivePotionEffect(MobEffects.LEVITATION).getAmplifier() + 1) - this.motionY;
                }
                else if (!this.hasNoGravity())
                {
                    this.motionY -= 0.08D;
                }

                this.motionY *= 0.9800000190734863D;
            }

            this.fishPitch = (float)((double)this.fishPitch + (double)(-90.0F - this.fishPitch) * 0.02D);
        }
    }

    public void travel(float strafe, float vertical, float forward)
    {
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
    }

    public boolean getCanSpawnHere()
    {
        return true;
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 19)
        {
            this.fishRotation = 0.0F;
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

    public void setMovementVector(float randomMotionVecXIn, float randomMotionVecYIn, float randomMotionVecZIn)
    {
        this.randomMotionVecX = randomMotionVecXIn;
        this.randomMotionVecY = randomMotionVecYIn;
        this.randomMotionVecZ = randomMotionVecZIn;
    }

    public boolean hasMovementVector()
    {
        return this.randomMotionVecX != 0.0F || this.randomMotionVecY != 0.0F || this.randomMotionVecZ != 0.0F;
    }

    static class AIMoveRandom extends EntityAIBase
    {
        private final ShrimpBase squid;

        public AIMoveRandom(ShrimpBase p_i45859_1_)
        {
            this.squid = p_i45859_1_;
        }

        public boolean shouldExecute()
        {

            return true;
        }

        public void updateTask()
        {
            int i = this.squid.getIdleTime();

            if (i > 0)
            {
                this.squid.setMovementVector(0.0F, 0.0F, 0.0F);
            }
            else if (this.squid.getRNG().nextInt(50) == 0 || !this.squid.inWater || !this.squid.hasMovementVector())
            {
                float f = this.squid.getRNG().nextFloat() * ((float)Math.PI * 2F);
                float f1 = MathHelper.cos(f) * 0.2F;
                float f2 = -0.1F + this.squid.getRNG().nextFloat() * 0.2F;
                float f3 = MathHelper.sin(f) * 0.2F;
                this.squid.setMovementVector(f1, f2, f3);
            }
        }
    }

}
