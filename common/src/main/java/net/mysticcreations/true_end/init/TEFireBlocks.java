package net.mysticcreations.true_end.init;

import net.mysticcreations.true_end.registry.ArsonRegistry;

public class TEFireBlocks {
    public static ArsonRegistry REGISTRY = ArsonRegistry.createInstance();

    public static ArsonRegistry.ArsonRegistryReference WOOD = REGISTRY.registerBlockEntry(TEBlocks.WOOD.get(), new ArsonRegistry.Arsonability(20, 40));
    public static ArsonRegistry.ArsonRegistryReference WOOD_SIX_SIDED = REGISTRY.registerBlockEntry(TEBlocks.WOOD_6_SIDED.get(), new ArsonRegistry.Arsonability(20, 40));
    public static ArsonRegistry.ArsonRegistryReference FENCE = REGISTRY.registerBlockEntry(TEBlocks.FENCE.get(), new ArsonRegistry.Arsonability(20, 40));
    public static ArsonRegistry.ArsonRegistryReference FENCE_GATE = REGISTRY.registerBlockEntry(TEBlocks.FENCE_GATE.get(), new ArsonRegistry.Arsonability(20, 40));
    public static ArsonRegistry.ArsonRegistryReference DOOR = REGISTRY.registerBlockEntry(TEBlocks.DOOR.get(), new ArsonRegistry.Arsonability(20, 40));
    public static ArsonRegistry.ArsonRegistryReference STAIRS = REGISTRY.registerBlockEntry(TEBlocks.WOODEN_STAIRS.get(), new ArsonRegistry.Arsonability(20, 40));
    public static ArsonRegistry.ArsonRegistryReference TRAPDOOR = REGISTRY.registerBlockEntry(TEBlocks.TRAPDOOR.get(), new ArsonRegistry.Arsonability(20, 40));
    public static ArsonRegistry.ArsonRegistryReference PRESSURE_PLATE = REGISTRY.registerBlockEntry(TEBlocks.PRESSURE_PLATE.get(), new ArsonRegistry.Arsonability(20, 40));
    public static ArsonRegistry.ArsonRegistryReference SAPLING = REGISTRY.registerBlockEntry(TEBlocks.SAPLING.get(), new ArsonRegistry.Arsonability(20, 40));
    public static ArsonRegistry.ArsonRegistryReference BUTTON = REGISTRY.registerBlockEntry(TEBlocks.BUTTON.get(), new ArsonRegistry.Arsonability(20, 40));
    public static ArsonRegistry.ArsonRegistryReference LEAVES = REGISTRY.registerBlockEntry(TEBlocks.LEAVES.get(), new ArsonRegistry.Arsonability(20, 40));

    public static void register() {
        ArsonRegistry.register(REGISTRY);
    }

}
