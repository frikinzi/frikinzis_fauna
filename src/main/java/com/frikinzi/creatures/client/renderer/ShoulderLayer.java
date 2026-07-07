package com.frikinzi.creatures.client.renderer;

import com.frikinzi.creatures.client.renderer.entity.*;
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
import net.minecraft.world.entity.Entity;
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
        if (type == CreaturesEntities.LORIKEET.get()) {
            LorikeetEntity lorikeet = (LorikeetEntity) getOrCreateBirdEntity(shoulderNBT);

            if (lorikeet != null) {
                LorikeetRenderer render1 = (LorikeetRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(lorikeet);
                lorikeet.setOnGround(true);
                lorikeet.tickCount = entity.tickCount;

                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                render1.render(lorikeet, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();

            }
        }if (type == CreaturesEntities.BARN_OWL.get()) {
            BarnOwlEntity barnOwl = (BarnOwlEntity) getOrCreateBirdEntity(shoulderNBT);
            if (barnOwl != null) {
                BarnOwlRenderer barnOwlRenderer = (BarnOwlRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(barnOwl);
                barnOwl.setOnGround(true); barnOwl.tickCount = entity.tickCount;
                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                barnOwlRenderer.render(barnOwl, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            }
        }
        if (type == CreaturesEntities.CONURE.get()) {
            ConureEntity conure = (ConureEntity) getOrCreateBirdEntity(shoulderNBT);
            if (conure != null) {
                ConureRenderer conureRenderer = (ConureRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(conure);
                conure.setOnGround(true); conure.tickCount = entity.tickCount;
                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                conureRenderer.render(conure, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            }
        }
        if (type == CreaturesEntities.DOVE.get()) {
            DoveEntity dove = (DoveEntity) getOrCreateBirdEntity(shoulderNBT);
            if (dove != null) {
                DoveRenderer doveRenderer = (DoveRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(dove);
                dove.setOnGround(true); dove.tickCount = entity.tickCount;
                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                doveRenderer.render(dove, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            }
        }
        if (type == CreaturesEntities.EAGLEOWL.get()) {
            EagleOwlEntity eagleOwl = (EagleOwlEntity) getOrCreateBirdEntity(shoulderNBT);
            if (eagleOwl != null) {
                EagleOwlRenderer eagleOwlRenderer = (EagleOwlRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(eagleOwl);
                eagleOwl.setOnGround(true); eagleOwl.tickCount = entity.tickCount;
                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                eagleOwlRenderer.render(eagleOwl, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            }
        }
        if (type == CreaturesEntities.FINCH.get()) {
            FinchEntity finch = (FinchEntity) getOrCreateBirdEntity(shoulderNBT);
            if (finch != null) {
                FinchRenderer finchRenderer = (FinchRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(finch);
                finch.setOnGround(true); finch.tickCount = entity.tickCount;
                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                finchRenderer.render(finch, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            }
        }
        if (type == CreaturesEntities.GOLDEN_EAGLE.get()) {
            GoldenEagleEntity goldenEagle = (GoldenEagleEntity) getOrCreateBirdEntity(shoulderNBT);
            if (goldenEagle != null) {
                GoldenEagleRenderer goldenEagleRenderer = (GoldenEagleRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(goldenEagle);
                goldenEagle.setOnGround(true); goldenEagle.tickCount = entity.tickCount;
                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                goldenEagleRenderer.render(goldenEagle, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            }
        }
        if (type == CreaturesEntities.GYRFALCON.get()) {
            GyrfalconEntity gyrfalcon = (GyrfalconEntity) getOrCreateBirdEntity(shoulderNBT);
            if (gyrfalcon != null) {
                GyrfalconRenderer gyrfalconRenderer = (GyrfalconRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(gyrfalcon);
                gyrfalcon.setOnGround(true); gyrfalcon.tickCount = entity.tickCount;
                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                gyrfalconRenderer.render(gyrfalcon, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            }
        }
        if (type == CreaturesEntities.MONAL.get()) {
            MonalEntity monal = (MonalEntity) getOrCreateBirdEntity(shoulderNBT);
            if (monal != null) {
                MonalRenderer monalRenderer = (MonalRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(monal);
                monal.setOnGround(true); monal.tickCount = entity.tickCount;
                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                monalRenderer.render(monal, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            }
        }
        if (type == CreaturesEntities.OSPREY.get()) {
            OspreyEntity osprey = (OspreyEntity) getOrCreateBirdEntity(shoulderNBT);
            if (osprey != null) {
                OspreyRenderer ospreyRenderer = (OspreyRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(osprey);
                osprey.setOnGround(true); osprey.tickCount = entity.tickCount;
                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                ospreyRenderer.render(osprey, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            }
        }
        if (type == CreaturesEntities.PEAFOWL.get()) {
            PeafowlEntity peafowl = (PeafowlEntity) getOrCreateBirdEntity(shoulderNBT);
            if (peafowl != null) {
                PeafowlRenderer peafowlRenderer = (PeafowlRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(peafowl);
                peafowl.setOnGround(true); peafowl.tickCount = entity.tickCount;
                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                peafowlRenderer.render(peafowl, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            }
        }
        if (type == CreaturesEntities.PYGMY_FALCON.get()) {
            PygmyFalconEntity pygmyFalcon = (PygmyFalconEntity) getOrCreateBirdEntity(shoulderNBT);
            if (pygmyFalcon != null) {
                PygmyFalconRenderer pygmyFalconRenderer = (PygmyFalconRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(pygmyFalcon);
                pygmyFalcon.setOnGround(true); pygmyFalcon.tickCount = entity.tickCount;
                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                pygmyFalconRenderer.render(pygmyFalcon, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            }
        }
        if (type == CreaturesEntities.RAVEN.get()) {
            RavenEntity raven = (RavenEntity) getOrCreateBirdEntity(shoulderNBT);
            if (raven != null) {
                RavenRenderer ravenRenderer = (RavenRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(raven);
                raven.setOnGround(true); raven.tickCount = entity.tickCount;
                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                ravenRenderer.render(raven, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            }
        }
        if (type == CreaturesEntities.RED_KITE.get()) {
            RedKiteEntity redKite = (RedKiteEntity) getOrCreateBirdEntity(shoulderNBT);
            if (redKite != null) {
                RedKiteRenderer redKiteRenderer = (RedKiteRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(redKite);
                redKite.setOnGround(true); redKite.tickCount = entity.tickCount;
                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                redKiteRenderer.render(redKite, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            }
        }
        if (type == CreaturesEntities.SEA_EAGLE.get()) {
            SeaEagleEntity seaEagle = (SeaEagleEntity) getOrCreateBirdEntity(shoulderNBT);
            if (seaEagle != null) {
                SeaEagleRenderer seaEagleRenderer = (SeaEagleRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(seaEagle);
                seaEagle.setOnGround(true); seaEagle.tickCount = entity.tickCount;
                poseStack.pushPose();
                getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.mulPose(com.mojang.math.Axis.XN.rotationDegrees(180));
                poseStack.translate(0, 0.45f, 0);
                seaEagleRenderer.render(seaEagle, netHeadYaw, partialTick, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            }
        }

    }

    private CreaturesBirdEntity getOrCreateBirdEntity(CompoundTag shoulderNBT) {
        EntityType<?> type = EntityType.byString(shoulderNBT.getString("id")).orElse(null);
        if (type == null) return null;
        Entity entity = type.create(Minecraft.getInstance().level);
            if (!(entity instanceof CreaturesBirdEntity)) return null;
        CreaturesBirdEntity bird = (CreaturesBirdEntity) type.create(Minecraft.getInstance().level);
        if (bird != null) {
            bird.readAdditionalSaveData(shoulderNBT);
        }
        return bird;
    }
}