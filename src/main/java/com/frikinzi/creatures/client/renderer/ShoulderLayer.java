package com.frikinzi.creatures.client.renderer;

import com.frikinzi.creatures.client.renderer.entity.LovebirdRenderer;
import com.frikinzi.creatures.entity.*;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;

public class ShoulderLayer<T extends Player> extends RenderLayer<T, PlayerModel<T>> {

    public ShoulderLayer(RenderLayerParent<T, PlayerModel<T>> parent) {
        super(parent);
    }


    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity,
                       float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks,
                       float netHeadYaw, float headPitch) {

        CompoundTag shoulderNBT = entity.getShoulderEntityLeft();
        if (shoulderNBT.isEmpty()) return;

        EntityType<?> type = EntityType.byString(shoulderNBT.getString("id")).orElse(null);
        if (type == null) return;

        CreaturesBirdEntity bird = getOrCreateBirdEntity(shoulderNBT);
        if (bird == null) return;

        EntityRenderer<?> renderer = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(bird);
        bird.setOnGround(true);
        bird.tickCount = entity.tickCount;

        //float yaw = netHeadYaw;

        if (type == CreaturesEntities.LOVEBIRD.get()) {
            LovebirdEntity lovebird = (LovebirdEntity) getOrCreateBirdEntity(shoulderNBT);

            if (lovebird != null) {
                LovebirdRenderer render1 = (LovebirdRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(lovebird);
                lovebird.setOnGround(true);
                lovebird.tickCount = entity.tickCount;

                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                render1.render(lovebird, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();

            }
        }

    }

    private CreaturesBirdEntity getOrCreateBirdEntity(CompoundTag shoulderNBT) {
        EntityType<?> type = EntityType.byString(shoulderNBT.getString("id")).orElse(null);
        if (type == null) return null;
        CreaturesBirdEntity bird = (CreaturesBirdEntity) type.create(Minecraft.getInstance().level);
        if (bird != null) {
            bird.readAdditionalSaveData(shoulderNBT);
        }
        return bird;
    }
}