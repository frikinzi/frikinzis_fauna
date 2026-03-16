package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.entity.base.WalkingSwimmingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.schedule.Activity;
import com.frikinzi.creatures.entity.CormorantEntity;

import java.util.Optional;

public class CormorantAi {

    private static final UniformInt ADULT_FOLLOW_RANGE = UniformInt.of(5, 16);
    private static final float SPEED_MULTIPLIER_WHEN_IDLING_ON_LAND = 1.0F;
    private static final float SPEED_MULTIPLIER_WHEN_IDLING_IN_WATER = 0.8F;
    private static final float SPEED_MULTIPLIER_WHEN_CHASING = 0.9F;
    private static final float SPEED_MULTIPLIER_WHEN_FOLLOWING_ADULT = 0.6F;
    private static final float SPEED_MULTIPLIER_WHEN_CHASING_IN_WATER = 0.6F;
    private static final float SPEED_MULTIPLIER_WHEN_TEMPTED = 1.0F;

    public static Brain<?> makeBrain(Brain<? extends WalkingSwimmingBird> brain) {
        initCoreActivity(brain);
        initIdleActivity(brain);
        initFightActivity(brain);
        initSwimActivity(brain);
        //initRestActivity(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    private static void initCoreActivity(Brain<? extends WalkingSwimmingBird> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(
                new LookAtTargetSink(45, 90),
                new MoveToTargetSink(),
                new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)
        ));
    }

    // Active when NOT in water — wander on land, try to find water
    private static void initIdleActivity(Brain<? extends WalkingSwimmingBird> brain) {
        brain.addActivityWithConditions(Activity.IDLE,
                ImmutableList.of(
                        Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0F, UniformInt.of(30, 60))),
                        Pair.of(1, new LayEggBehavior<>()),
                        Pair.of(2, new FollowTemptation(e -> SPEED_MULTIPLIER_WHEN_TEMPTED)),
                        Pair.of(3, StartAttacking.create(CormorantAi::findNearestValidAttackTarget)), // added
                        Pair.of(4, BabyFollowAdult.create(ADULT_FOLLOW_RANGE, CormorantAi::getSpeedModifierFollowingAdult)),
                        Pair.of(5, TryFindWater.create(6, SPEED_MULTIPLIER_WHEN_IDLING_ON_LAND)),
                        Pair.of(6, new RunOne<>(
                                ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT),
                                ImmutableList.of(
                                        //Pair.of(RandomStroll.swim(SPEED_MULTIPLIER_WHEN_IDLING_IN_WATER), 2),
                                        Pair.of(RandomStroll.stroll(SPEED_MULTIPLIER_WHEN_IDLING_ON_LAND), 2),
                                        Pair.of(SetWalkTargetFromLookTarget.create(SPEED_MULTIPLIER_WHEN_IDLING_ON_LAND, 3), 1),
                                        Pair.of(BehaviorBuilder.triggerIf(Entity::onGround), 2)
                                )
                        ))
                ),
                // only active when not in water
                ImmutableSet.of(Pair.of(MemoryModuleType.IS_IN_WATER, MemoryStatus.VALUE_ABSENT))
        );
    }

    private static void initSwimActivity(Brain<? extends WalkingSwimmingBird> brain) {
        brain.addActivityWithConditions(Activity.SWIM,
                ImmutableList.of(
                        Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0F, UniformInt.of(30, 60))),
                        Pair.of(1, new FollowTemptation(e -> SPEED_MULTIPLIER_WHEN_TEMPTED)),
                        Pair.of(2, BabyFollowAdult.create(ADULT_FOLLOW_RANGE, CormorantAi::getSpeedModifierFollowingAdult)),
                        Pair.of(4, StartAttacking.create(CormorantAi::findNearestValidAttackTarget)), // added
                        Pair.of(5, TryFindLand.create(8, 1.2F)),
                        Pair.of(6, new GateBehavior<>(
                                ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT),
                                ImmutableSet.of(),
                                GateBehavior.OrderPolicy.ORDERED,
                                GateBehavior.RunningPolicy.TRY_ALL,
                                ImmutableList.of(
                                        Pair.of(RandomStroll.swim(SPEED_MULTIPLIER_WHEN_IDLING_IN_WATER), 2),
                                        Pair.of(RandomStroll.stroll(SPEED_MULTIPLIER_WHEN_IDLING_ON_LAND, true), 1),
                                        Pair.of(SetWalkTargetFromLookTarget.create(SPEED_MULTIPLIER_WHEN_IDLING_IN_WATER, 3), 1),
                                        Pair.of(BehaviorBuilder.triggerIf(Entity::isInWaterOrBubble), 5)
                                )
                        ))
                ),
                ImmutableSet.of(Pair.of(MemoryModuleType.IS_IN_WATER, MemoryStatus.VALUE_PRESENT))
        );
    }

    private static void initFightActivity(Brain<? extends WalkingSwimmingBird> brain) {
        brain.addActivityAndRemoveMemoryWhenStopped(Activity.FIGHT, 0,
                ImmutableList.of(
                        StopAttackingIfTargetInvalid.create(),
                        SetWalkTargetFromAttackTargetIfTargetOutOfReach.create(CormorantAi::getSpeedModifierChasing),
                        MeleeAttack.create(20),
                        EraseMemoryIf.<Mob>create(BehaviorUtils::isBreeding, MemoryModuleType.ATTACK_TARGET)
                ),
                MemoryModuleType.ATTACK_TARGET
        );
    }

    public static void updateActivity(WalkingSwimmingBird cormorant) {
        Brain<? extends WalkingSwimmingBird> brain = cormorant.getBrain();
        Activity previous = brain.getActiveNonCoreActivity().orElse(null);

//        if (cormorant.isSleeping()) {
//            brain.eraseMemory(MemoryModuleType.WALK_TARGET);
//            return;
//        }
        Activity current = brain.getActiveNonCoreActivity().orElse(null);
        if (previous == Activity.REST && current != Activity.REST) {
            brain.eraseMemory(MemoryModuleType.WALK_TARGET);
        }

        brain.setActiveActivityToFirstValid(
                ImmutableList.of(Activity.FIGHT, Activity.SWIM, Activity.IDLE)
        );

        if (previous == Activity.FIGHT && brain.getActiveNonCoreActivity().orElse(null) != Activity.FIGHT) {
            brain.setMemoryWithExpiry(MemoryModuleType.HAS_HUNTING_COOLDOWN, true, 2400L);
        }
    }

//public static void updateActivity(CormorantEntity cormorant) {
//    Brain<CormorantEntity> brain = cormorant.getBrain();
//    Activity previous = brain.getActiveNonCoreActivity().orElse(null);
//
//    // Only include REST as a candidate when it's actually sleep time
//    ImmutableList<Activity> candidates = cormorant.timeSleep()
//            ? ImmutableList.of(Activity.FIGHT, Activity.REST, Activity.SWIM, Activity.IDLE)
//            : ImmutableList.of(Activity.FIGHT, Activity.SWIM, Activity.IDLE);
//
//    brain.setActiveActivityToFirstValid(candidates);
//
//    if (previous == Activity.FIGHT && brain.getActiveNonCoreActivity().orElse(null) != Activity.FIGHT) {
//        brain.setMemoryWithExpiry(MemoryModuleType.HAS_HUNTING_COOLDOWN, true, 2400L);
//    }
//}

    private static float getSpeedModifierChasing(LivingEntity entity) {
        return entity.isInWaterOrBubble() ? SPEED_MULTIPLIER_WHEN_CHASING : SPEED_MULTIPLIER_WHEN_IDLING_ON_LAND;
    }

    private static float getSpeedModifierFollowingAdult(LivingEntity entity) {
        return entity.isInWaterOrBubble() ? SPEED_MULTIPLIER_WHEN_IDLING_IN_WATER : SPEED_MULTIPLIER_WHEN_FOLLOWING_ADULT;
    }

    private static Optional<? extends LivingEntity> findNearestValidAttackTarget(WalkingSwimmingBird cormorant) {
        return BehaviorUtils.isBreeding(cormorant) ? Optional.empty()
                : cormorant.getBrain().getMemory(MemoryModuleType.NEAREST_ATTACKABLE);
    }

    private static void initRestActivity(Brain<? extends WalkingSwimmingBird> brain) {
        brain.addActivityWithConditions(Activity.REST,
                ImmutableList.of(
                        Pair.of(0, new SleepBehavior())
                ),
                ImmutableSet.of(
                        Pair.of(MemoryModuleType.IS_IN_WATER, MemoryStatus.VALUE_ABSENT),
                        Pair.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_ABSENT)
                )
        );
    }
}
