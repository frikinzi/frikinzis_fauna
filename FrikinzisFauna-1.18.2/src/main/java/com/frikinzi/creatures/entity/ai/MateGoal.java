package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class MateGoal extends Goal {
    private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(8.0D).ignoreLineOfSight();
    protected final CreaturesBirdEntity animal;
    private final Class<? extends CreaturesBirdEntity> partnerClass;
    protected final Level level;
    @Nullable
    protected CreaturesBirdEntity partner;
    private int loveTime;
    private final double speedModifier;

    public MateGoal(CreaturesBirdEntity p_25122_, double p_25123_) {
        this(p_25122_, p_25123_, p_25122_.getClass());
    }

    public MateGoal(CreaturesBirdEntity p_25125_, double p_25126_, Class<? extends CreaturesBirdEntity> p_25127_) {
        this.animal = p_25125_;
        this.level = p_25125_.level;
        this.partnerClass = p_25127_;
        this.speedModifier = p_25126_;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        if (!this.animal.isInLove()) {
            return false;
        } else {
            this.partner = this.getFreePartner();
            return this.partner != null;
        }
    }

    public boolean canContinueToUse() {
        return this.partner.isAlive() && this.partner.isInLove() && this.loveTime < 60;
    }

    public void stop() {
        this.partner = null;
        this.loveTime = 0;
    }

    public void tick() {
        this.animal.getLookControl().setLookAt(this.partner, 10.0F, (float) this.animal.getMaxHeadXRot());
        this.animal.getNavigation().moveTo(this.partner, this.speedModifier);
        ++this.loveTime;
        if (this.loveTime >= this.adjustedTickDelay(60) && this.animal.distanceToSqr(this.partner) < 9.0D) {
            this.layEgg((ServerLevel) this.level);
        }

    }

    @Nullable
    private CreaturesBirdEntity getFreePartner() {
        List<? extends CreaturesBirdEntity> list = this.level.getNearbyEntities(this.partnerClass, PARTNER_TARGETING, this.animal, this.animal.getBoundingBox().inflate(8.0D));
        double d0 = Double.MAX_VALUE;
        CreaturesBirdEntity animal = null;

        for (CreaturesBirdEntity animal1 : list) {
            if (this.animal.canMate(animal1) && this.animal.distanceToSqr(animal1) < d0 && this.animal.getGender() != animal1.getGender()) {
                animal = animal1;
                d0 = this.animal.distanceToSqr(animal1);
            }
        }

        return animal;
    }

    protected void breed() {
        this.animal.spawnChildFromBreeding((ServerLevel) this.level, this.partner);
    }

    protected void layEgg(ServerLevel server) {
        int c = this.animal.getClutchSize();
        for (int j = 0; j <= c; j++) {
            EggEntity egg = this.animal.layEgg(this.partner);
            if (egg != null) {
                this.animal.setAge(6000);
                this.partner.setAge(6000);
                this.animal.resetLove();
                this.partner.resetLove();
                CreaturesBirdEntity mother;

                if (this.animal.getGender() == 0) {
                    mother = this.animal;
                } else {
                    mother = this.partner;
                }
                //egg.setParentUUID(mother.getUUID());

                float f = (float) (this.animal.getRandom().nextGaussian() * 0.05 + ((this.animal.getHeightMultiplier() + this.partner.getHeightMultiplier()) / 2));
                egg.setHeightMultiplier(f);

                Random rand = new Random();
                egg.setPos(Mth.floor(mother.getX()) + 0.5 + (-1 + rand.nextFloat() * 1.2), Mth.floor(mother.getY()) + 0.5, Mth.floor(mother.getZ()) + 0.5 + (-1 + rand.nextFloat() * 1.2));
                server.addFreshEntityWithPassengers(egg);
                //System.out.println(this.bird.getClutchSize());
            }
            if (this.partner != null) {
                ServerPlayer serverplayer = this.animal.getLoveCause();
                if (serverplayer == null && this.partner.getLoveCause() != null) {
                    serverplayer = this.partner.getLoveCause();
                }

                if (serverplayer != null) {
                    serverplayer.awardStat(Stats.ANIMALS_BRED);
                    CriteriaTriggers.BRED_ANIMALS.trigger(serverplayer, this.animal, this.partner, egg);
                }
                server.broadcastEntityEvent(this.animal, (byte) 18);
                Random random = this.animal.getRandom();
                for (int i = 0; i < 7; ++i) {
                    double d0 = this.animal.getRandom().nextGaussian() * 0.02D;
                    double d1 = this.animal.getRandom().nextGaussian() * 0.02D;
                    double d2 = this.animal.getRandom().nextGaussian() * 0.02D;
                    this.level.addParticle(ParticleTypes.HEART, this.animal.getRandomX(1.0D), this.animal.getRandomY() + 0.5D, this.animal.getRandomZ(1.0D), d0, d1, d2);
                }
                server.broadcastEntityEvent(this.animal, (byte) 18);
                if (server.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
                    server.addFreshEntity(new ExperienceOrb(server, this.animal.getX(), this.animal.getY(), this.animal.getZ(), this.animal.getRandom().nextInt(7) + 1));
                }
            }
        }
    }
}
