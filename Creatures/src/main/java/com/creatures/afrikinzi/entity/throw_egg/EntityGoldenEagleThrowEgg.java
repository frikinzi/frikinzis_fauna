package com.creatures.afrikinzi.entity.throw_egg;

import com.creatures.afrikinzi.entity.golden_eagle.EntityGoldenEagle;
import com.creatures.afrikinzi.objects.items.CreaturesEgg;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGoldenEagleThrowEgg extends EntityThrowable {
    public static Class <? extends EntityAnimal> spawnentity;

    public EntityGoldenEagleThrowEgg(World worldIn, Class <? extends EntityAnimal> entity)
    {

        super(worldIn);
        spawnentity = entity;
    }

    public EntityGoldenEagleThrowEgg(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityGoldenEagleThrowEgg(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public static void registerFixesEgg(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "ThrowEgg");
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            double d0 = 0.08D;

            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, Item.getIdFromItem(Items.EGG));
            }
        }
    }

    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
        }

        if (!this.world.isRemote)
        {
            if (this.rand.nextInt(8) == 0)
            {
                int i = 1;

                if (this.rand.nextInt(32) == 0)
                {
                    i = 4;
                }

                for (int j = 0; j < i; ++j)
                {
                    EntityAnimal entitygoldeneagle = new EntityGoldenEagle(this.world);
                    entitygoldeneagle.setGrowingAge(-24000);
                    entitygoldeneagle.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                    this.world.spawnEntity(entitygoldeneagle);
                }
            }

            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
}
