package com.creatures.afrikinzi.entity.osprey;

import com.creatures.afrikinzi.config.CreaturesConfig;
import com.creatures.afrikinzi.entity.ICreaturesEntity;
import com.creatures.afrikinzi.entity.RaptorBase;
import com.creatures.afrikinzi.entity.ai.EntityAIFollowOwnerCreatures;
import com.creatures.afrikinzi.entity.arowana.EntityArowana;
import com.creatures.afrikinzi.entity.koi.EntityKoi;
import com.creatures.afrikinzi.entity.trout.EntityTrout;
import com.creatures.afrikinzi.init.ItemInit;
import com.creatures.afrikinzi.util.handlers.SoundsHandler;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Set;

public class EntityOsprey extends RaptorBase implements IAnimatable, ICreaturesEntity {
    private AnimationFactory factory = new AnimationFactory(this);

    public EntityOsprey(World worldIn) {
        super(worldIn);
        this.setSize(1.8F, 1.5F);
        this.moveHelper = new EntityFlyHelper(this);
        this.setPathPriority(PathNodeType.WATER, 1.0F);
        Set<Item> TAME_ITEMS = Sets.newHashSet(Items.FISH, Items.COOKED_FISH, ItemInit.RAW_AROWANA, ItemInit.RAW_TROUT, ItemInit.RAW_KOI, ItemInit.RAW_PIKE, ItemInit.RAW_GOURAMI);
    }

    @Override
    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(5, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.2D, true));
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        if (CreaturesConfig.raptorsFollow == true) {
        this.tasks.addTask(6, new EntityAIFollowOwnerCreatures(this, 1.0D, 5.0F, 1.0F)); }
        this.tasks.addTask(7, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        //this.targetTasks.addTask(6, new EntityStellersSeaEagle.AIFishing(this));
        if (CreaturesConfig.eagleAttacks == true) {
            this.targetTasks.addTask(8, new EntityAITargetNonTamed(this, EntityTrout.class, false, (Predicate) null));
            this.targetTasks.addTask(8, new EntityAITargetNonTamed(this, EntityArowana.class, false, (Predicate) null));
            //this.targetTasks.addTask(6, new EntityAITargetNonTamed(this, EntityChicken.class, false, (Predicate) null));
            this.targetTasks.addTask(8, new EntityAITargetNonTamed(this, EntityKoi.class, false, (Predicate) null));
            this.targetTasks.addTask(8, new EntityAITargetNonTamed(this, EntitySquid.class, false, (Predicate) null));
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving() && this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        if (!this.onGround || this.isFlying()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", true));
            return PlayState.CONTINUE;
        }
        if (this.isSleeping() && !this.isDead) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", true));
            return PlayState.CONTINUE;
        } if (this.isSitting()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sit", true));
            return PlayState.CONTINUE;
        }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
            return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.40000000298023224D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
    }

    public SoundEvent getAmbientSound()
    {
        if (!this.isSleeping()) {

            return SoundsHandler.OSPREY_AMBIENT;
        } else {
            return null;
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return TAME_ITEMS.contains(stack.getItem());
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
        }
        if (!this.isTamed()) {
            return false;
        }
        else if (!(otherAnimal instanceof EntityOsprey))
        {
            return false;
        }
        else
        {
            EntityOsprey entityosprey = (EntityOsprey)otherAnimal;
            if (!entityosprey.isTamed())  {
                return false;
            }
            return this.isInLove() && entityosprey.isInLove();
        }


    }

    @Override
    public EntityOsprey createChild(EntityAgeable ageable)
    {
        EntityOsprey entitystellersseaeagle = new EntityOsprey(this.world);
        entitystellersseaeagle.setGender(this.rand.nextInt(2));

        return entitystellersseaeagle;
    }

    @Override
    public Set<Item> getTameItems() {
        TAME_ITEMS = Sets.newHashSet(Items.FISH, ItemInit.RAW_AROWANA, ItemInit.RAW_TROUT, ItemInit.RAW_KOI, ItemInit.RAW_GOURAMI, ItemInit.RAW_PIKE);
        return TAME_ITEMS;
    }

    public String getSpeciesName() {
        String s1 = I18n.format("entity.osprey.name");
        return s1;
    }

    public String getFoodName() {
        return net.minecraft.util.text.translation.I18n.translateToLocal(Items.FISH.getUnlocalizedName() + ".name").trim();
    }

}
