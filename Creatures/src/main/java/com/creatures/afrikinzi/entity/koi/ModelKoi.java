package com.creatures.afrikinzi.entity.koi;

import com.creatures.afrikinzi.entity.koi.EntityKoi;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelKoi extends AnimatedGeoModel<EntityKoi>
{
	@Override
	public ResourceLocation getModelLocation(EntityKoi object)
	{
		return new ResourceLocation(Reference.MOD_ID, "geo/entity/koi/koi.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EntityKoi object)
	{
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/koi/koi" + object.getVariant() + ".png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(EntityKoi object)
	{
		return new ResourceLocation(Reference.MOD_ID, "animations/animation.koi.swim.json");
	}
}