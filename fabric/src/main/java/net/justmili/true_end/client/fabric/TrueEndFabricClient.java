package net.justmili.true_end.client.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.justmili.true_end.client.renderer.UnknownEntityRenderer;
import net.justmili.true_end.init.TrueEndBlocks;
import net.justmili.true_end.init.TrueEndEntities;
import net.minecraft.client.renderer.RenderType;

public class TrueEndFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(TrueEndBlocks.SAPLING.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TrueEndBlocks.GLASS.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TrueEndBlocks.LEAVES.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TrueEndBlocks.ROSE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TrueEndBlocks.FLOWER.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TrueEndBlocks.TRAPDOOR.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TrueEndBlocks.DOOR.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TrueEndBlocks.BEYOND_THE_DREAM_PORTAL.get(), RenderType.cutout());
        EntityRendererRegistry.register(TrueEndEntities.UNKNOWN.get(), UnknownEntityRenderer.UnknownRenderer::new);
    }
}
