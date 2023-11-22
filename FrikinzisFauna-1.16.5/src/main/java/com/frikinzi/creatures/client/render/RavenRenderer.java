package com.frikinzi.creatures.client.render;

import com.frikinzi.creatures.client.model.RavenModel;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.RavenEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.vector.Vector3f;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
public class RavenRenderer extends GeoEntityRenderer<RavenEntity>{
    private static final ItemStack book = new ItemStack(Items.BOOK);
    public RavenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RavenModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void renderEarly(RavenEntity animatable, MatrixStack stackIn, float ticks,
                            IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn,
                red, green, blue, partialTicks);
<<<<<<< Updated upstream
        Float multiplier;
        if (CreaturesConfig.height_on.get() == true) {
=======
        float multiplier;
        if (CreaturesConfig.height_on.get()) {
>>>>>>> Stashed changes
            multiplier = animatable.getHeightMultiplier();
        } else {
            multiplier = 1.0F;
        }
<<<<<<< Updated upstream
        if (animatable.isBaby()) {
            stackIn.scale(0.4F * multiplier, 0.4F * multiplier, 0.4F * multiplier);
        } else {
            stackIn.scale(multiplier, multiplier, multiplier);
        }
    }

    @Override
    public void renderRecursively(GeoBone bone, MatrixStack stack, IVertexBuilder bufferIn, int packedLightIn,
                                  int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("bipedLeftArm_1")) {
            stack.pushPose();
            stack.mulPose(Vector3f.XP.rotationDegrees(-40));
            stack.mulPose(Vector3f.YP.rotationDegrees(0));
            stack.mulPose(Vector3f.ZP.rotationDegrees(-5));
            stack.translate(0.30D, 0.90D, 0.3D);
            stack.scale(1.0f, 1.0f, 1.0f);
            Minecraft.getInstance().getItemRenderer().renderStatic(book, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND,
                    packedLightIn, packedOverlayIn, stack, this.rtb);
            stack.popPose();
            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
        }
        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
=======
        if (!animatable.isBaby()) {
            stackIn.scale(0.7F *multiplier, 0.7F *multiplier, 0.7F *multiplier);
        } else {
            stackIn.scale(multiplier, multiplier, multiplier);

        }
>>>>>>> Stashed changes
    }

}
