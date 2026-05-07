package net.mcreator.abyssoria.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.abyssoria.entity.BlackSeaDevilEntity;

public class BlackSeaDevilModel extends GeoModel<BlackSeaDevilEntity> {
	@Override
	public ResourceLocation getAnimationResource(BlackSeaDevilEntity entity) {
		return new ResourceLocation("abyssoria", "animations/bsd.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(BlackSeaDevilEntity entity) {
		return new ResourceLocation("abyssoria", "geo/bsd.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(BlackSeaDevilEntity entity) {
		return new ResourceLocation("abyssoria", "textures/entities/" + entity.getTexture() + ".png");
	}

}
