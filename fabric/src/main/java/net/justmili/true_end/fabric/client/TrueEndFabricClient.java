package net.justmili.true_end.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.justmili.true_end.client.TrueEndCommonClient;

public final class TrueEndFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        TrueEndCommonClient.init();
    }
}
