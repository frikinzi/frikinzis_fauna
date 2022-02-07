package com.creatures.afrikinzi.entity.golden_eagle;

import com.creatures.afrikinzi.config.CreaturesConfig;
import com.creatures.afrikinzi.entity.ICreaturesEntity;
import com.creatures.afrikinzi.entity.RaptorBase;
import com.creatures.afrikinzi.entity.ai.EntityAIFollowOwnerCreatures;
import com.creatures.afrikinzi.entity.raven.EntityRaven;
import com.creatures.afrikinzi.init.ItemInit;
import com.creatures.afrikinzi.util.handlers.SoundsHandler;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Set;

public class EntityGoldenEagle extends RaptorBase implements IAnimatable, ICreaturesEntity {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.RABBIT, Items.PORKCHOP, Items.CHICKEN, ItemInit.RAW_LARGE_WILD_BIRD_MEAT, ItemInit.RAW_SMALL_WILD_BIRD_MEAT, Items.RABBIT);

    public EntityGoldenEagle(World worldIn)
    {
        super(worldIn);
        this.setSize(1.8F, 1.5F);
        this.moveHelper = new EntityFlyHelper(this);
    }

    @Override
    protected void initEntityAI()
    {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        if (CreaturesConfig.raptorsFollow == true) {
        this.tasks.addTask(6, new EntityAIFollowOwnerCreatures(this, 1.0D, 5.0F, 1.0F));
        }
        this.tasks.addTask(7, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
        this.tasks.addTask(5, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        if (!this.isChild()) {
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        }
        if (CreaturesConfig.eagleAttacks == true) {
        this.targetTasks.addTask(6, new EntityGoldenEagle.AIHunting());
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (this.isAttacking() == true && this.motionY < 0.0D && !this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", true));
            return PlayState.CONTINUE;
        }
        if (event.isMoving() && this.onGround && !this.isDead) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        } if (!this.onGround || this.isFlying() && !this.isDead) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", true));
        return PlayState.CONTINUE;
    } if (this.isSleeping() && !this.isDead) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", true));
        return PlayState.CONTINUE;
    } if (this.isSitting()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("sit", true));
        return PlayState.CONTINUE;
    }
    else {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.40000000298023224D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
        }
        else if (!(otherAnimal instanceof EntityGoldenEagle))
        {
            return false;
        }
            EntityGoldenEagle entitygoldeneagle = (EntityGoldenEagle)otherAnimal;
            return this.isInLove() && entitygoldeneagle.isInLove();
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return TAME_ITEMS.contains(stack.getItem());
    }

    @Override
    public EntityGoldenEagle createChild(EntityAgeable ageable)
    {
            EntityGoldenEagle entitygoldeneagle = new EntityGoldenEagle(this.world);
            entitygoldeneagle.setGender(this.rand.nextInt(2));
            return entitygoldeneagle;

    }

    @Override
    public void onKillEntity(EntityLivingBase living) {
        this.HungerTime = this.rand.nextInt(100) + 100;
        if (living instanceof EntityAnimal) {
            this.heal(5.0F);
            System.out.println("wild raptor healed 5");
        }
    }

    class AIHunting extends EntityAITargetNonTamed<EntityRabbit> {
        public AIHunting()
        {
            super(EntityGoldenEagle.this, EntityRabbit.class, false, (Predicate)null);
        }

        public boolean shouldExecute() {
            if (EntityGoldenEagle.this.isChild()) {
                return false;
            } //if (this.targetEntity instanceof EntityGoldenEagle) {
              //  return false;
           // }
            else {
                if (super.shouldExecute()) {
                   // if (this.targetEntity instanceof EntityGoldenEagle) {
                    //    return false; }
                    if (--EntityGoldenEagle.this.HungerTime <= 0) {
                        //System.out.println("raptor is hungry! " + EntityGoldenEagle.this.HungerTime);
                        return true;
                    }
                }
            }
            EntityGoldenEagle.this.setAttackTarget((EntityLivingBase)null);
            return false;
        }
    }

    public SoundEvent getAmbientSound()
    {
        if (!this.isSleeping()) {

            return SoundsHandler.GOLDEN_EAGLE_AMBIENT;
        } else {
            return null;
        }
    }


}
