package com.frikinzi.creatures.client.renderer.layer;

import com.frikinzi.creatures.entity.KingfisherEntity;
import com.frikinzi.creatures.entity.SkuaEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

import javax.annotation.Nullable;

public class SkuaHeldItemLayer extends BlockAndItemGeoLayer<SkuaEntity> {

    public SkuaHeldItemLayer(GeoRenderer<SkuaEntity> renderer) {
        super(renderer);
    }

    @Nullable
    @Override
    protected ItemStack getStackForBone(GeoBone bone, SkuaEntity entity) {
        if (bone.getName().equals("RightHandItem")) {
            return entity.getMainHandItem();
        }
        return null;
    }

    @Override
    protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, SkuaEntity entity) {
        return ItemDisplayContext.GROUND;
    }

    @Override
    protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack,
                                      SkuaEntity entity, MultiBufferSource bufferSource,
                                      float partialTick, int packedLight, int packedOverlay) {

        poseStack.mulPose(Axis.YP.rotationDegrees(60));
        poseStack.mulPose(Axis.XP.rotationDegrees(90));
        //poseStack.mulPose(Axis.XP.rotationDegrees(90));
        //poseStack.mulPose(Axis.YP.rotationDegrees(60));

        super.renderStackForBone(poseStack, bone, stack, entity, bufferSource,
                partialTick, packedLight, packedOverlay);
    }
}