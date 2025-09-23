package net.mysticcreations.true_end.client.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.mysticcreations.true_end.client.TrueEndCommonClient;
import net.mysticcreations.true_end.init.TEParticles;
import net.mysticcreations.true_end.client.renderer.UnknownEntityRenderer;
import net.mysticcreations.true_end.init.TEBlocks;
import net.mysticcreations.true_end.init.TEEntities;
import net.minecraft.client.renderer.RenderType;

public class TrueEndFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(TEBlocks.SAPLING.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TEBlocks.GLASS.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TEBlocks.LEAVES.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TEBlocks.ROSE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TEBlocks.FLOWER.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TEBlocks.TRAPDOOR.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TEBlocks.DOOR.get(), RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(TEBlocks.BEYOND_THE_DREAM_PORTAL.get(), RenderType.cutout());
        EntityRendererRegistry.register(TEEntities.UNKNOWN.get(), UnknownEntityRenderer.UnknownRenderer::new);

        TrueEndCommonClient.init();

        TEParticles.register();
    }
}
