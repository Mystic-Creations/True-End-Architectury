package net.justmili.true_end.init;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.justmili.true_end.TrueEndCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TrueEndCreativeTab {

    public static final DeferredRegister<CreativeModeTab> REGISTRY =
            DeferredRegister.create(TrueEndCommon.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final DeferredSupplier<CreativeModeTab> TRUE_END = REGISTRY.register("true_end", () ->
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("item_group.true_end.true_end")).icon(() -> new ItemStack(TrueEndBlocks.GRASS_BLOCK.get())).displayItems((parameters, tabData) -> {
                tabData.accept(TrueEndBlocks.GRASS_BLOCK.get().asItem());
                tabData.accept(TrueEndBlocks.DIRT.get().asItem());
                tabData.accept(TrueEndBlocks.FARMLAND.get().asItem());
                tabData.accept(TrueEndBlocks.FLOWER.get().asItem());
                tabData.accept(TrueEndBlocks.ROSE.get().asItem());
                tabData.accept(TrueEndBlocks.SAPLING.get().asItem());
                tabData.accept(TrueEndBlocks.LEAVES.get().asItem());
                tabData.accept(TrueEndBlocks.WOOD.get().asItem());
                tabData.accept(TrueEndBlocks.WOOD_6_SIDED.get().asItem());
                tabData.accept(TrueEndBlocks.WOODEN_PLANKS.get().asItem());
                tabData.accept(TrueEndBlocks.WOODEN_STAIRS.get().asItem());
                tabData.accept(TrueEndBlocks.WOODEN_SLAB.get().asItem());
                tabData.accept(TrueEndBlocks.FENCE.get().asItem());
                tabData.accept(TrueEndBlocks.FENCE_GATE.get().asItem());
                tabData.accept(TrueEndBlocks.DOOR.get().asItem());
                tabData.accept(TrueEndBlocks.TRAPDOOR.get().asItem());
                tabData.accept(TrueEndBlocks.PRESSURE_PLATE.get().asItem());
                tabData.accept(TrueEndBlocks.BUTTON.get().asItem());
                tabData.accept(TrueEndBlocks.STONE.get().asItem());
                tabData.accept(TrueEndBlocks.COAL_ORE.get().asItem());
                tabData.accept(TrueEndBlocks.IRON_ORE.get().asItem());
                tabData.accept(TrueEndBlocks.GOLD_ORE.get().asItem());
                tabData.accept(TrueEndBlocks.REDSTONE_ORE.get().asItem());
                tabData.accept(TrueEndBlocks.DIAMOND_ORE.get().asItem());
                tabData.accept(TrueEndBlocks.COBBLESTONE.get().asItem());
                tabData.accept(TrueEndBlocks.MOSSY_COBBLESTONE.get().asItem());
                tabData.accept(TrueEndBlocks.GRAVEL.get().asItem());
                tabData.accept(TrueEndBlocks.SAND.get().asItem());
                tabData.accept(TrueEndBlocks.GLASS.get().asItem());
                tabData.accept(TrueEndBlocks.OBSIDIAN.get().asItem());
                tabData.accept(TrueEndItems.MYSTERIOUS_CUBE.get());
                tabData.accept(TrueEndItems.DREAMERS_COMPASS.get());
                tabData.accept(TrueEndItems.MUSIC_DISK_FARLANDS.get());
                tabData.accept(TrueEndItems.MUSIC_DISK_NEVER_ALONE.get());

                ItemStack blackVoid = new ItemStack(TrueEndItems.VOID.get());
                blackVoid.getOrCreateTagElement("BlockStateTag").putString("type", "black");
                tabData.accept(blackVoid);
                ItemStack whiteVoid = new ItemStack(TrueEndItems.VOID.get());
                whiteVoid.getOrCreateTagElement("BlockStateTag").putString("type", "white");
                tabData.accept(whiteVoid);
            }).build());
    public static void register() {
        // Register the tab itself first
        REGISTRY.register();
    }
}
