package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.RandomSequence;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.core.jmx.Server;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class MateGoal extends net.minecraft.world.entity.ai.goal.Goal {
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
        this.level = p_25125_.level();
        this.partnerClass = p_25127_;
        this.speedModifier = p_25126_;
        this.setFlags(EnumSet.of(net.minecraft.world.entity.ai.goal.Goal.Flag.MOVE, net.minecraft.world.entity.ai.goal.Goal.Flag.LOOK));
    }

    public boolean canUse() {
        if (!this.animal.isInLove()) {
            return false;
        }
        else {
            this.partner = this.getFreePartner();
            return this.partner != null;
        }
    }

    public boolean canContinueToUse() {
        return this.partner.isAlive() && this.partner.isInLove() && this.loveTime < 60;
    }

    @Nullable
    private CreaturesBirdEntity getFreePartner() {
        List<? extends CreaturesBirdEntity> list = this.level.getNearbyEntities(this.partnerClass, PARTNER_TARGETING, this.animal, this.animal.getBoundingBox().inflate(8.0D));
        double d0 = Double.MAX_VALUE;
        CreaturesBirdEntity animal = null;

        for(CreaturesBirdEntity animal1 : list) {
            if (this.animal.canMate(animal1) && this.animal.distanceToSqr(animal1) < d0) {
                animal = animal1;
                d0 = this.animal.distanceToSqr(animal1);
            }
        }

        return animal;
    }

    public void stop() {
        this.partner = null;
        this.loveTime = 0;
    }

    public void tick() {
        this.animal.getLookControl().setLookAt(this.partner, 10.0F, (float)this.animal.getMaxHeadXRot());
        this.animal.getNavigation().moveTo(this.partner, this.speedModifier);
        ++this.loveTime;
        if (this.loveTime >= 60 && this.animal.distanceToSqr(this.partner) < 9.0D) {
            this.layEgg((ServerLevel)this.level);
        }

    }

    protected void layEgg(ServerLevel server) {
        int c = this.animal.getClutchSize();
        for (int j = 1; j <= c; j++) {
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

                float f = (float)(this.animal.getRandom().nextGaussian() * 0.05 + ((this.animal.getHeightMultiplier() + this.partner.getHeightMultiplier())/2));
                egg.setHeightMultiplier(f);

                Random rand = new Random();
                egg.setPos(Math.floor(mother.getX()) + 0.5 + (-1+rand.nextFloat()*2), Math.floor(mother.getY()) + 0.5, Math.floor(mother.getZ()) + 0.5 + (-1+rand.nextFloat()*2));
                server.addFreshEntityWithPassengers(egg);
                //System.out.println(this.bird.getClutchSize());
            }
            server.broadcastEntityEvent(this.animal, (byte)18);
            RandomSource random = this.animal.getRandom();
            for (int i = 0; i < 17; ++i) {
                final double d0 = random.nextGaussian() * 0.02D;
                final double d1 = random.nextGaussian() * 0.02D;
                final double d2 = random.nextGaussian() * 0.02D;
                final double d3 = random.nextDouble() * this.animal.getBbWidth() * 2.0D - this.animal.getBbWidth();
                final double d4 = 0.5D + random.nextDouble() * this.animal.getBbHeight();
                final double d5 = random.nextDouble() * this.animal.getBbWidth() * 2.0D - this.animal.getBbWidth();
                this.level.addParticle(ParticleTypes.HEART, this.animal.getX() + d3, this.animal.getY() + d4, this.animal.getZ() + d5, d0, d1, d2);
            }
            if (server.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
                server.addFreshEntity(new ExperienceOrb(server, this.animal.getX(), this.animal.getY(), this.animal.getZ(), this.animal.getRandom().nextInt(7) + 1));
            }
        }
    }

}
