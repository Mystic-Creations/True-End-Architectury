package net.justmili.true_end.forge;

import net.justmili.true_end.common.TrueEndCommon;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TrueEndCommon.MOD_ID)
public final class TrueEndForge {
    public TrueEndForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(TrueEndCommon.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        // Run our common setup.
        TrueEndCommon.init();
    }
}
