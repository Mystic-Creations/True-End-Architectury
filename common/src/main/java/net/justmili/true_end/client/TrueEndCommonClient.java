package net.justmili.true_end.client;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.justmili.true_end.client.init.TrueEndParticles;
import net.justmili.true_end.client.renderer.UnknownEntityRenderer;
import net.justmili.true_end.init.TEEntities;

public final class TrueEndCommonClient {

    public static void init() {

        TrueEndParticles.register();

        EntityRendererRegistry.register(
                TEEntities.UNKNOWN,
                UnknownEntityRenderer.UnknownRenderer::new
        );
    }
}
