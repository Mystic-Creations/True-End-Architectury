package net.mysticcreations.true_end.client.renderer;

import net.mysticcreations.true_end.entity.Unknown;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
public class UnknownEntityRenderer {

    public static class UnknownRenderer extends MobRenderer<Unknown, HumanoidModel<Unknown>> {
        public UnknownRenderer(EntityRendererProvider.Context context) {
            super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
        }

        @Override
        public boolean shouldRender(Unknown entity, Frustum frustum, double camX, double camY, double camZ) {
            return true;
        }

        @Override
        public ResourceLocation getTextureLocation(Unknown entity) {
            return new ResourceLocation("true_end:textures/entity/unknown/" + entity.getTextureName() + ".png");
        }
    }
}