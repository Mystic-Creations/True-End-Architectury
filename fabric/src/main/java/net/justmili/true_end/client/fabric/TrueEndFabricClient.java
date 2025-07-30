package net.justmili.true_end.client.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.justmili.true_end.init.TrueEndBlocks;
import net.minecraft.client.renderer.RenderType;

public class TrueEndFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(TrueEndBlocks.SAPLING.get(), RenderType.cutout());
    }
}
