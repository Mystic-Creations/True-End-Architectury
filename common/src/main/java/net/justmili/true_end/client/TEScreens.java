package net.justmili.true_end.client;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.justmili.true_end.TrueEndCommon;
import net.justmili.true_end.client.screens.BlackOverlay;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.inventory.MenuType;

public class Guis {
    public static DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(TrueEndCommon.MOD_ID, Registries.MENU);

    public static final DeferredSupplier<MenuType<BlackOverlay>> BLACK_SCREEN =
            REGISTRY.register("black_screen", new MenuType<>((MenuConstructor<BlackOverlay>) BlackOverlay::new));

    public static void register() {
        REGISTRY.register();
    }
}
