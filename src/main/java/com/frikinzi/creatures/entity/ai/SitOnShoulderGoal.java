package com.frikinzi.creatures.entity.ai;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.ShoulderRidingEntity;

public class SitOnShoulderGoal extends Goal {
    private final ShoulderRidingEntity entity;
    private ServerPlayer owner;
    private boolean isSittingOnShoulder;

    public SitOnShoulderGoal(ShoulderRidingEntity p_25483_) {
        this.entity = p_25483_;
    }

    public boolean canUse() {
        ServerPlayer serverplayer = (ServerPlayer) this.entity.getOwner();
        if (serverplayer != null) {
            if (!serverplayer.getShoulderEntityLeft().isEmpty()) {
                return false; //can only land on left shoulder
            }
        }
        boolean flag = serverplayer != null && !serverplayer.isSpectator() && !serverplayer.getAbilities().flying && !serverplayer.isInWater() && !serverplayer.isInPowderSnow;
        return !this.entity.isOrderedToSit() && flag && this.entity.canSitOnShoulder();
    }

    public boolean isInterruptable() {
        return !this.isSittingOnShoulder;
    }

    public void start() {
        this.owner = (ServerPlayer)this.entity.getOwner();
        this.isSittingOnShoulder = false;
    }

    public void tick() {
        if (!this.isSittingOnShoulder && !this.entity.isInSittingPose() && !this.entity.isLeashed()) {
            if (this.entity.getBoundingBox().intersects(this.owner.getBoundingBox())) {
                this.isSittingOnShoulder = this.entity.setEntityOnShoulder(this.owner);
            }

        }
    }
}