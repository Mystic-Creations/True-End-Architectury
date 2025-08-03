package net.justmili.true_end.fabric;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.justmili.true_end.TrueEndCommon;
import net.fabricmc.api.ModInitializer;
import net.justmili.true_end.commands.DevCommand;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;

public final class TrueEndFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        TrueEndCommon.init();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            DevCommand.register(dispatcher);
        });
        FireBlock fire = (FireBlock) Blocks.FIRE;

        fire.setFlammable(ModBlocks.MY_BLOCK.get(), 60, 100);
    });
    }
}
