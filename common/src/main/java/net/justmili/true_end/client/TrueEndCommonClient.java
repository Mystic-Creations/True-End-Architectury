package net.justmili.true_end.client;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.true_end.client.renderer.BlackOverlayRenderer;
import net.justmili.true_end.client.renderer.FunnyScreenRenderer;
import net.justmili.true_end.client.renderer.UnknownEntityRenderer;
import net.justmili.true_end.init.TEBlocks;
import net.justmili.true_end.init.TEEntities;
import net.justmili.true_end.init.TEPackets;
import net.justmili.true_end.init.TEScreens;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public final class TrueEndCommonClient {

    public static void init() {

        EntityRendererRegistry.register(
                TEEntities.UNKNOWN,
                UnknownEntityRenderer.UnknownRenderer::new
        );

        ItemPropertiesRegistry.register(
                TEBlocks.VOID.get().asItem(),
                new ResourceLocation("true_end:type"),
                (stack, world, entity, seed) -> {
                    CompoundTag bst = stack.getTagElement("BlockStateTag");
                    if (bst != null && "white".equals(bst.getString("type"))) {
                        return 1.0f;
                    }
                    return 0.0f;
                }
        );

        MenuRegistry.registerScreenFactory(TEScreens.BLACK_SCREEN.get(), BlackOverlayRenderer::new);
        MenuRegistry.registerScreenFactory(TEScreens.FUNNY_SCREEN.get(), FunnyScreenRenderer::new);

        TEPackets.registerClient();
    }
}
