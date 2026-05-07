
package net.mcreator.abyssoria.client.renderer;

import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.abyssoria.entity.model.BlackSeaDevilModel;
import net.mcreator.abyssoria.entity.layer.BlackSeaDevilLayer;
import net.mcreator.abyssoria.entity.BlackSeaDevilEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class BlackSeaDevilRenderer extends GeoEntityRenderer<BlackSeaDevilEntity> {
	public BlackSeaDevilRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new BlackSeaDevilModel());
		this.shadowRadius = 0.5f;
		this.addRenderLayer(new BlackSeaDevilLayer(this));
	}

	@Override
	public RenderType getRenderType(BlackSeaDevilEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, BlackSeaDevilEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green,
			float blue, float alpha) {
		float scale = 0.9f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	protected float getDeathMaxRotation(BlackSeaDevilEntity entityLivingBaseIn) {
		return 0.0F;
	}
}
