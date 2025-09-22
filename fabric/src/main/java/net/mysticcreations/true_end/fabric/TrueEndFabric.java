package net.mysticcreations.true_end.fabric;

import net.mysticcreations.true_end.TrueEndCommon;
import net.fabricmc.api.ModInitializer;
import net.mysticcreations.true_end.world.seeping_reality.fabric.SeepingForestRegion;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

public final class TrueEndFabric implements ModInitializer, TerraBlenderApi {

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        TrueEndCommon.init();

    }
    @Override
    public void onTerraBlenderInitialized()
    {
        TrueEndCommon.LOGGER.info("TERRABLENDER");
        Regions.register(new SeepingForestRegion(new ResourceLocation("true_end:overworld_region"), 1));
    }
}
