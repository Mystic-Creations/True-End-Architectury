package net.justmili.true_end.client;

import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.true_end.TrueEndCommon;
import net.justmili.true_end.client.screens.BlackOverlay;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class TEScreens {
    public static DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(TrueEndCommon.MOD_ID, Registries.MENU);
    public static final RegistrySupplier<MenuType<BlackOverlay>> BLACK_SCREEN = REGISTRY.register("black_screen", ()->MenuRegistry.ofExtended(BlackOverlay::new));
    public static void register() {
        REGISTRY.register();
    }
}
