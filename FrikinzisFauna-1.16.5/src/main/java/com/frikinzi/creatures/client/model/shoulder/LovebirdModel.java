package com.frikinzi.creatures.client.model.shoulder;

import com.frikinzi.creatures.entity.LovebirdEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.ParrotModel;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
// Made with Blockbench 4.5.2


public class LovebirdModel extends SegmentedModel<LovebirdEntity> {
	private final ModelRenderer bone;
	private final ModelRenderer everythingexceptlegs;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer tail;
	private final ModelRenderer cube_r3;
	private final ModelRenderer bone3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer bone8;
	private final ModelRenderer cube_r8;
	private final ModelRenderer neckandhead;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer head;
	private final ModelRenderer cube_r11;
	private final ModelRenderer beak;
	private final ModelRenderer cube_r12;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer bone4;
	private final ModelRenderer bone6;
	private final ModelRenderer cube_r15;
	private final ModelRenderer cube_r16;
	private final ModelRenderer cube_r17;
	private final ModelRenderer bone5;
	private final ModelRenderer bone7;
	private final ModelRenderer cube_r18;
	private final ModelRenderer cube_r19;
	private final ModelRenderer cube_r20;

	public LovebirdModel() {
		texWidth = 64;
		texHeight = 64;

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 21.0F, 0.0F);


		everythingexceptlegs = new ModelRenderer(this);
		everythingexceptlegs.setPos(0.5083F, -1.0F, 0.0F);
		bone.addChild(everythingexceptlegs);


		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(1.0F, 1.0F, 0.0F);
		everythingexceptlegs.addChild(cube_r1);
		setRotationAngle(cube_r1, 1.0908F, 0.0F, 0.0F);
		cube_r1.texOffs(0, 0).addBox(-3.5F, -2.0F, -0.7F, 4.0F, 5.0F, 5.0F, 0.1F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(1.0F, 1.0F, 0.0F);
		everythingexceptlegs.addChild(cube_r2);
		setRotationAngle(cube_r2, 1.3963F, 0.0F, 0.0F);
		cube_r2.texOffs(12, 13).addBox(-3.0F, 2.5F, -1.1F, 3.0F, 3.0F, 4.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setPos(-0.4F, 1.5F, 5.0F);
		everythingexceptlegs.addChild(tail);


		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(1.4F, -0.5F, -5.0F);
		tail.addChild(cube_r3);
		setRotationAngle(cube_r3, 1.3963F, 0.0F, 0.0F);
		cube_r3.texOffs(29, 14).addBox(-3.0F, 5.5F, -0.6F, 3.0F, 3.0F, 2.0F, 0.0F, false);
		cube_r3.texOffs(30, 7).addBox(-2.5F, 5.6F, -0.2F, 2.0F, 2.0F, 2.0F, 0.2F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(-4.6083F, 1.0F, 0.0F);
		everythingexceptlegs.addChild(bone3);


		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(1.5083F, 0.0F, 0.0F);
		bone3.addChild(cube_r4);
		setRotationAngle(cube_r4, 1.3526F, 0.0F, 0.0F);
		cube_r4.texOffs(20, 25).addBox(0.1F, 3.0F, 0.9F, 1.0F, 4.0F, 3.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(1.5083F, 0.0F, 0.0F);
		bone3.addChild(cube_r5);
		setRotationAngle(cube_r5, 1.2654F, 0.0F, 0.0F);
		cube_r5.texOffs(0, 23).addBox(0.1F, -1.0F, 0.3F, 1.0F, 5.0F, 4.0F, 0.1F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(-0.5083F, 1.0F, 0.0F);
		everythingexceptlegs.addChild(bone2);


		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(1.5083F, 0.0F, 0.0F);
		bone2.addChild(cube_r6);
		setRotationAngle(cube_r6, 1.3526F, 0.0F, 0.0F);
		cube_r6.texOffs(10, 28).addBox(0.1F, 3.0F, 0.9F, 1.0F, 4.0F, 3.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(1.5083F, 0.0F, 0.0F);
		bone2.addChild(cube_r7);
		setRotationAngle(cube_r7, 1.2654F, 0.0F, 0.0F);
		cube_r7.texOffs(23, 16).addBox(0.1F, -1.0F, 0.3F, 1.0F, 5.0F, 4.0F, 0.1F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(-1.0F, -1.0F, -1.0F);
		everythingexceptlegs.addChild(bone8);


		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(2.0F, 2.0F, 1.0F);
		bone8.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.3927F, 0.0F, 0.0F);
		cube_r8.texOffs(0, 10).addBox(-3.5F, -4.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);

		neckandhead = new ModelRenderer(this);
		neckandhead.setPos(0.7F, -2.0F, 0.0F);
		bone8.addChild(neckandhead);


		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(1.3F, 4.0F, 1.0F);
		neckandhead.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.48F, 0.0F, 0.0F);
		cube_r9.texOffs(11, 20).addBox(-3.0F, -7.1F, -0.4F, 3.0F, 5.0F, 3.0F, -0.1F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(1.3F, 4.0F, 1.0F);
		neckandhead.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.2618F, 0.0F, 0.0F);
		cube_r10.texOffs(18, 0).addBox(-3.0F, -5.7F, -2.6F, 3.0F, 3.0F, 3.0F, -0.01F, false);

		head = new ModelRenderer(this);
		head.setPos(-0.2083F, -1.8F, -0.8F);
		neckandhead.addChild(head);


		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(1.5083F, 6.0F, 2.0F);
		head.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.1309F, 0.0F, 0.0F);
		cube_r11.texOffs(0, 17).addBox(-3.0F, -8.4F, -4.1F, 3.0F, 2.0F, 4.0F, -0.1F, false);
		cube_r11.texOffs(14, 6).addBox(-3.0F, -8.0F, -3.8F, 3.0F, 3.0F, 4.0F, 0.0F, false);

		beak = new ModelRenderer(this);
		beak.setPos(-0.1F, 6.0F, 1.1F);
		head.addChild(beak);


		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(1.5083F, 0.0F, 0.0F);
		beak.addChild(cube_r12);
		setRotationAngle(cube_r12, -1.3963F, 0.0F, 0.0F);
		cube_r12.texOffs(13, 0).addBox(-2.4F, 1.7F, -6.4F, 2.0F, 2.0F, 1.0F, -0.01F, false);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setPos(1.5083F, 0.0F, 0.0F);
		beak.addChild(cube_r13);
		setRotationAngle(cube_r13, -0.0873F, 0.0F, 0.0F);
		cube_r13.texOffs(22, 14).addBox(-2.4F, -5.2F, -5.3F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setPos(1.5083F, 0.0F, 0.0F);
		beak.addChild(cube_r14);
		setRotationAngle(cube_r14, -0.5672F, 0.0F, 0.0F);
		cube_r14.texOffs(20, 32).addBox(-2.4F, -4.2F, -7.1F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(1.0F, 0.0F, 2.0F);
		bone.addChild(bone4);
		bone4.texOffs(32, 26).addBox(-0.6917F, 0.4F, -1.0F, 2.0F, 2.0F, 2.0F, -0.1F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(-1.0F, -0.3F, -1.0F);
		bone4.addChild(bone6);


		cube_r15 = new ModelRenderer(this);
		cube_r15.setPos(1.5083F, 0.0F, 0.0F);
		bone6.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.0F, 0.3491F, 0.0F);
		cube_r15.texOffs(15, 32).addBox(-1.0F, 2.4F, -0.2F, 1.0F, 1.0F, 3.0F, -0.1F, false);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setPos(1.5083F, 0.0F, 0.0F);
		bone6.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.0F, -0.2182F, 0.0F);
		cube_r16.texOffs(26, 2).addBox(-0.5F, 2.4F, -1.2F, 1.0F, 1.0F, 4.0F, -0.1F, false);

		cube_r17 = new ModelRenderer(this);
		cube_r17.setPos(1.5083F, 0.0F, 0.0F);
		bone6.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0F, 0.2618F, 0.0F);
		cube_r17.texOffs(32, 0).addBox(-1.2F, 2.4F, -1.2F, 1.0F, 1.0F, 3.0F, -0.1F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(-0.6F, 0.0F, 2.0F);
		bone.addChild(bone5);
		bone5.texOffs(0, 32).addBox(-1.6917F, 0.4F, -1.0F, 2.0F, 2.0F, 2.0F, -0.1F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(-2.0F, -0.3F, -1.0F);
		bone5.addChild(bone7);


		cube_r18 = new ModelRenderer(this);
		cube_r18.setPos(1.5083F, 0.0F, 0.0F);
		bone7.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.0F, -0.1745F, 0.0F);
		cube_r18.texOffs(24, 9).addBox(-0.4F, 2.4F, -1.2F, 1.0F, 1.0F, 4.0F, -0.1F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setPos(1.5083F, 0.0F, 0.0F);
		bone7.addChild(cube_r19);
		setRotationAngle(cube_r19, 0.0F, 0.1745F, 0.0F);
		cube_r19.texOffs(27, 29).addBox(-0.7F, 2.4F, 0.0F, 1.0F, 1.0F, 3.0F, -0.1F, false);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setPos(1.5083F, 0.0F, 0.0F);
		bone7.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.0F, 0.2618F, 0.0F);
		cube_r20.texOffs(30, 22).addBox(-1.2F, 2.4F, -1.2F, 1.0F, 1.0F, 3.0F, -0.1F, false);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	public void renderOnShoulder(MatrixStack p_228284_1_, IVertexBuilder p_228284_2_, int p_228284_3_, int p_228284_4_, float p_228284_5_, float p_228284_6_, float p_228284_7_, float p_228284_8_, int p_228284_9_) {
		//this.setupAnim(LovebirdModel.State.ON_SHOULDER, p_228284_9_, p_228284_5_, p_228284_6_, 0.0F, p_228284_7_, p_228284_8_);
		this.parts().forEach((p_228285_4_) -> {
			p_228285_4_.render(p_228284_1_, p_228284_2_, p_228284_3_, p_228284_4_);
		});
	}

	@OnlyIn(Dist.CLIENT)
	public static enum State {
		ON_SHOULDER;
	}

	private static LovebirdModel.State getState(ParrotEntity p_217158_0_) {
		return State.ON_SHOULDER;
	}

	public Iterable<ModelRenderer> parts() {
		return ImmutableList.of(this.bone, this.bone2, this.everythingexceptlegs, this.tail, this.head, this.cube_r1, this.cube_r2, this.bone3, this.bone4,this.bone5,this.bone6,this.bone7,this.bone8, this.head, this.neckandhead, this.cube_r3,this.cube_r4, this.cube_r5,this.cube_r6, this.cube_r7, this.cube_r8);
	}

	public void setupAnim(LovebirdEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

	}
}
