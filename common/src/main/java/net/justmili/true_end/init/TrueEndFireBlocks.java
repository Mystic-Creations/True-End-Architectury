package net.justmili.true_end.init;

import net.justmili.true_end.registry.ArsonRegistry;
import net.justmili.true_end.registry.ArsonRegistryReference;

public class TrueEndFireBlocks {
    public static ArsonRegistry REGISTRY = ArsonRegistry.createInstance();

    public static ArsonRegistryReference WOOD = REGISTRY.registerBlockEntry(TrueEndBlocks.WOOD.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference WOOD_SIX_SIDED = REGISTRY.registerBlockEntry(TrueEndBlocks.WOOD_6_SIDED.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference FENCE = REGISTRY.registerBlockEntry(TrueEndBlocks.FENCE.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference FENCE_GATE = REGISTRY.registerBlockEntry(TrueEndBlocks.FENCE_GATE.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference DOOR = REGISTRY.registerBlockEntry(TrueEndBlocks.DOOR.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference STAIRS = REGISTRY.registerBlockEntry(TrueEndBlocks.WOODEN_STAIRS.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference TRAPDOOR = REGISTRY.registerBlockEntry(TrueEndBlocks.TRAPDOOR.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference PRESSURE_PLATE = REGISTRY.registerBlockEntry(TrueEndBlocks.PRESSURE_PLATE.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference SAPLING = REGISTRY.registerBlockEntry(TrueEndBlocks.SAPLING.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference BUTTON = REGISTRY.registerBlockEntry(TrueEndBlocks.BUTTON.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference LEAVES = REGISTRY.registerBlockEntry(TrueEndBlocks.LEAVES.get(), new ArsonRegistry.Arsonabilty(20, 40));

    public static void register() {
        ArsonRegistry.register(REGISTRY);
    }

}
