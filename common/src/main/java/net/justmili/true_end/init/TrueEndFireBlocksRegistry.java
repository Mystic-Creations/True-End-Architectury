package net.justmili.true_end.init;

import net.justmili.true_end.registry.ArsonRegistry;
import net.justmili.true_end.registry.ArsonRegistryReference;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;

public class TrueEndFireBlocksRegistry {
    public static ArsonRegistry REGISTRY = ArsonRegistry.createInstance();

    public static ArsonRegistryReference WOOD = REGISTRY.registerBlockEntry(TrueEndBlocks.WOOD.get(), new ArsonRegistry.Arsonabilty(20, 40));

    public static void register() {

        ArsonRegistry.register(REGISTRY);
    }
}
