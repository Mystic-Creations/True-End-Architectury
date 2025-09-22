package net.mysticcreations.true_end.init;

import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.mysticcreations.true_end.TrueEndCommon;
import net.mysticcreations.true_end.commands.calls.screens.BlackOverlay;
import net.mysticcreations.true_end.commands.calls.screens.FunnyScreen;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;

public class TEScreens {
    public static DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(TrueEndCommon.MOD_ID, Registries.MENU);
    public static final RegistrySupplier<MenuType<BlackOverlay>> BLACK_SCREEN = REGISTRY.register("black_screen", ()->MenuRegistry.ofExtended(BlackOverlay::new));
    public static final RegistrySupplier<MenuType<FunnyScreen>> FUNNY_SCREEN = REGISTRY.register("funny_screen", ()->MenuRegistry.ofExtended(FunnyScreen::new));

    public static void register() {
        REGISTRY.register();
    }
}
