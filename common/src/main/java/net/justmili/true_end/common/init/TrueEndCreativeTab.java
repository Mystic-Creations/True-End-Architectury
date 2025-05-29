package net.justmili.true_end.common.init;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.true_end.common.TrueEndCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.swing.*;

public class TrueEndCreativeTab {

    public static final DeferredRegister<CreativeModeTab> REGISTRY =
            DeferredRegister.create(TrueEndCommon.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final DeferredSupplier<CreativeModeTab> TRUE_END = REGISTRY.register("true_end", () ->
            CreativeTabRegistry.create(
                    Component.translatable("itemGroup.true_end.true_end"),
                    () -> new ItemStack(TrueEndItems.GRASS_BLOCK.get())
            )
    );

    public static void register() {
        // Register the tab itself first
        REGISTRY.register();

    }
}
