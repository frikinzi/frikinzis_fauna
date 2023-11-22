package com.frikinzi.creatures.client.render.shoulder;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.client.render.DoveRenderer;
import com.frikinzi.creatures.client.render.LovebirdRenderer;
import com.frikinzi.creatures.entity.DoveEntity;
import com.frikinzi.creatures.entity.LovebirdEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.ParrotRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

import java.util.function.Function;


public class LovebirdShoulderLayer<T extends PlayerEntity> extends LayerRenderer<T, PlayerModel<T>> {
    private Lovebird fakeEntity;
    public LovebirdShoulderLayer(IEntityRenderer<T, PlayerModel<T>> parent) {
        super(parent);
        fakeEntity = new Lovebird();
    }

    @Override
    public void render(MatrixStack poseStack, IRenderTypeBuffer bufferSource, int packedLight, T entity, float partialTick, float yaw, float r, float g, float b, float a) {
        LovebirdRenderer fakeRenderer = (LovebirdRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(fakeEntity.getMob());
        LovebirdEntity dove = fakeEntity.getMob();
        dove.setOnGround(true);
        dove.tickCount = entity.tickCount;
        poseStack.pushPose();
        getParentModel().head.translateAndRotate(poseStack);
        poseStack.mulPose(Vector3f.XN.rotationDegrees(180));
        poseStack.translate(0,0.45f,0);
        fakeRenderer.render(dove,yaw,partialTick,poseStack,bufferSource,packedLight);
        poseStack.popPose();
    }

    private class Lovebird{
        private LovebirdEntity mob;

        public LovebirdEntity getMob() {
            if (mob == null){
                CompoundNBT nbt = new CompoundNBT();
                nbt.putString("id", new ResourceLocation(Creatures.MODID,"dove").toString());
                mob = (LovebirdEntity) EntityType.loadEntityRecursive(nbt, Minecraft.getInstance().level, Function.identity());
            }
            return mob;
        }
    }
}