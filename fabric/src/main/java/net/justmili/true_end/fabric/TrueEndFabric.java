package net.justmili.true_end.fabric;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.justmili.true_end.TrueEndCommon;
import net.fabricmc.api.ModInitializer;
import net.justmili.true_end.world.seeping_reality.fabric.SeepingForestRegion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
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
        Regions.register(new SeepingForestRegion(new ResourceLocation("true_end:overworld_region"), 1));

    }
}
