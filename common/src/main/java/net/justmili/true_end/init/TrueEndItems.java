package net.justmili.true_end.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;

import net.justmili.true_end.TrueEndCommon;
import net.justmili.true_end.item.MysteriousCube;

public class TrueEndItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(TrueEndCommon.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> DIRT = block(TrueEndBlocks.DIRT);
    public static final RegistrySupplier<Item> GRASS_BLOCK = block(TrueEndBlocks.GRASS_BLOCK);
    public static final RegistrySupplier<Item> FARMLAND = block(TrueEndBlocks.FARMLAND);
    public static final RegistrySupplier<Item> COBBLESTONE = block(TrueEndBlocks.COBBLESTONE);
    public static final RegistrySupplier<Item> MOSSY_COBBLESTONE = block(TrueEndBlocks.MOSSY_COBBLESTONE);
    public static final RegistrySupplier<Item> STONE = block(TrueEndBlocks.STONE);
    public static final RegistrySupplier<Item> COAL_ORE = block(TrueEndBlocks.COAL_ORE);
    public static final RegistrySupplier<Item> IRON_ORE = block(TrueEndBlocks.IRON_ORE);
    public static final RegistrySupplier<Item> GOLD_ORE = block(TrueEndBlocks.GOLD_ORE);
    public static final RegistrySupplier<Item> REDSTONE_ORE = block(TrueEndBlocks.REDSTONE_ORE);
    public static final RegistrySupplier<Item> DIAMOND_ORE = block(TrueEndBlocks.DIAMOND_ORE);
    public static final RegistrySupplier<Item> LEAVES = block(TrueEndBlocks.LEAVES);
    public static final RegistrySupplier<Item> WOOD = block(TrueEndBlocks.WOOD);
    public static final RegistrySupplier<Item> WOOD_6_SIDED = block(TrueEndBlocks.WOOD_6_SIDED);
    public static final RegistrySupplier<Item> WOODEN_PLANKS = block(TrueEndBlocks.WOODEN_PLANKS);
    public static final RegistrySupplier<Item> WOODEN_STAIRS = block(TrueEndBlocks.WOODEN_STAIRS);
    public static final RegistrySupplier<Item> WOODEN_SLAB = block(TrueEndBlocks.WOODEN_SLAB);
    public static final RegistrySupplier<Item> FENCE = block(TrueEndBlocks.FENCE);
    public static final RegistrySupplier<Item> FENCE_GATE = block(TrueEndBlocks.FENCE_GATE);
    public static final RegistrySupplier<Item> DOOR = doubleBlock(TrueEndBlocks.DOOR);
    public static final RegistrySupplier<Item> TRAPDOOR = block(TrueEndBlocks.TRAPDOOR);
    public static final RegistrySupplier<Item> PRESSURE_PLATE = block(TrueEndBlocks.PRESSURE_PLATE);
    public static final RegistrySupplier<Item> BUTTON = block(TrueEndBlocks.BUTTON);
    public static final RegistrySupplier<Item> OBSIDIAN = block(TrueEndBlocks.OBSIDIAN);
    public static final RegistrySupplier<Item> GRAVEL = block(TrueEndBlocks.GRAVEL);
    public static final RegistrySupplier<Item> GLASS = block(TrueEndBlocks.GLASS);
    public static final RegistrySupplier<Item> SAND = block(TrueEndBlocks.SAND);

    public static final RegistrySupplier<Item> MYSTERIOUS_CUBE = REGISTRY.register("mysterious_cube", MysteriousCube::new);

    private static RegistrySupplier<Item> block(RegistrySupplier<Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static RegistrySupplier<Item> doubleBlock(RegistrySupplier<Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
    }

    public static void register() {
        REGISTRY.register();
    }
}
