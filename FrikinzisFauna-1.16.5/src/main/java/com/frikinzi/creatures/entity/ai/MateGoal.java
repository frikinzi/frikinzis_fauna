package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.egg.CreaturesEggEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class MateGoal extends Goal {
    private static final EntityPredicate PARTNER_TARGETING = (new EntityPredicate()).range(8.0D).allowInvulnerable().allowSameTeam().allowUnseeable();
    private final CreaturesBirdEntity bird;
    World level;
    protected CreaturesBirdEntity partner;
    private int loveTime;
    private final double speedModifier;


    public MateGoal(CreaturesBirdEntity bird, double speedIn) {
        this.bird = bird;
        this.level = bird.level;
        this.speedModifier = speedIn;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        if (!this.bird.isInLove()) {
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
        List<CreaturesBirdEntity> list = this.level.getNearbyEntities(this.bird.getClass(), PARTNER_TARGETING, this.bird, this.bird.getBoundingBox().inflate(8.0D));
        double d0 = Double.MAX_VALUE;
        CreaturesBirdEntity animalentity = null;

        for (CreaturesBirdEntity animalentity1 : list) {
            if (animalentity1.getGender() != this.bird.getGender() && this.bird.canMate(animalentity1) && this.bird.distanceToSqr(animalentity1) < d0) {
                // monogamous birds with a defined partner should only see their mate as a "Free partner"
                if ((animalentity1.isMonogamous() && (animalentity1.getMate() != null)) || (this.bird.isMonogamous() && this.bird.getMate() != null)) {
                    System.out.println("has mate");
                    if (animalentity1.getMate().isDeadOrDying()) {
                        animalentity = animalentity1;
                    }
                    if (animalentity1.getMate() == this.bird) {
                        animalentity = animalentity1;
                    }
                } else {
                    animalentity = animalentity1;
                }

                d0 = this.bird.distanceToSqr(animalentity1);
            }
        }
        return animalentity;
    }

    public void stop() {
        this.partner = null;
        this.loveTime = 0;
    }

    public void tick() {
        this.bird.getLookControl().setLookAt(this.partner, 10.0F, (float)this.bird.getMaxHeadXRot());
        this.bird.getNavigation().moveTo(this.partner, this.speedModifier);
        ++this.loveTime;
        this.bird.setMateUUID(this.partner.getUUID());
        this.partner.setMateUUID(this.bird.getUUID());
        if (this.loveTime >= 60 && this.bird.distanceToSqr(this.partner) < 9.0D) {
            this.layEgg((ServerWorld)this.level);
        }

    }

    protected void layEgg(ServerWorld server) {
        int c = this.bird.getClutchSize();
<<<<<<< Updated upstream
        for (int j = 0; j <= c; j++) {
=======
        for (int j = 1; j <= c; j++) {
>>>>>>> Stashed changes
        CreaturesEggEntity egg = this.bird.layEgg(this.partner);
        if (egg != null) {
            this.bird.setAge(6000);
            this.partner.setAge(6000);
            this.bird.resetLove();
            this.partner.resetLove();
            CreaturesBirdEntity mother;

            if (this.bird.getGender() == 0) {
                mother = this.bird;
            } else {
                mother = this.partner;
            }
            egg.setParentUUID(mother.getUUID());

            float f = (float)(this.bird.getRandom().nextGaussian() * 0.05 + ((this.bird.getHeightMultiplier() + this.partner.getHeightMultiplier())/2));
            egg.setHeightMultiplier(f);

            Random rand = new Random();
            egg.setPos(MathHelper.floor(mother.getX()) + 0.5 + (-1+rand.nextFloat()*2), MathHelper.floor(mother.getY()) + 0.5, MathHelper.floor(mother.getZ()) + 0.5 + (-1+rand.nextFloat()*2));
            server.addFreshEntityWithPassengers(egg);
                //System.out.println(this.bird.getClutchSize());
            }
            server.broadcastEntityEvent(this.bird, (byte)18);
            Random random = this.bird.getRandom();
            for (int i = 0; i < 17; ++i) {
                final double d0 = random.nextGaussian() * 0.02D;
                final double d1 = random.nextGaussian() * 0.02D;
                final double d2 = random.nextGaussian() * 0.02D;
                final double d3 = random.nextDouble() * this.bird.getBbWidth() * 2.0D - this.bird.getBbWidth();
                final double d4 = 0.5D + random.nextDouble() * this.bird.getBbHeight();
                final double d5 = random.nextDouble() * this.bird.getBbWidth() * 2.0D - this.bird.getBbWidth();
                this.level.addParticle(ParticleTypes.HEART, this.bird.getX() + d3, this.bird.getY() + d4, this.bird.getZ() + d5, d0, d1, d2);
            }
            if (server.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
                server.addFreshEntity(new ExperienceOrbEntity(server, this.bird.getX(), this.bird.getY(), this.bird.getZ(), this.bird.getRandom().nextInt(7) + 1));
            }
        }
    }

}
