package net.justmili.true_end.init;

import net.justmili.true_end.registry.ArsonRegistry;
import net.justmili.true_end.registry.ArsonRegistryReference;

public class TEFireBlocks {
    public static ArsonRegistry REGISTRY = ArsonRegistry.createInstance();

    public static ArsonRegistryReference WOOD = REGISTRY.registerBlockEntry(TEBlocks.WOOD.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference WOOD_SIX_SIDED = REGISTRY.registerBlockEntry(TEBlocks.WOOD_6_SIDED.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference FENCE = REGISTRY.registerBlockEntry(TEBlocks.FENCE.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference FENCE_GATE = REGISTRY.registerBlockEntry(TEBlocks.FENCE_GATE.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference DOOR = REGISTRY.registerBlockEntry(TEBlocks.DOOR.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference STAIRS = REGISTRY.registerBlockEntry(TEBlocks.WOODEN_STAIRS.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference TRAPDOOR = REGISTRY.registerBlockEntry(TEBlocks.TRAPDOOR.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference PRESSURE_PLATE = REGISTRY.registerBlockEntry(TEBlocks.PRESSURE_PLATE.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference SAPLING = REGISTRY.registerBlockEntry(TEBlocks.SAPLING.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference BUTTON = REGISTRY.registerBlockEntry(TEBlocks.BUTTON.get(), new ArsonRegistry.Arsonabilty(20, 40));
    public static ArsonRegistryReference LEAVES = REGISTRY.registerBlockEntry(TEBlocks.LEAVES.get(), new ArsonRegistry.Arsonabilty(20, 40));

    public static void register() {
        ArsonRegistry.register(REGISTRY);
    }

}
