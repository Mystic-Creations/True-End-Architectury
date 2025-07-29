package net.justmili.true_end.forge;

import dev.architectury.platform.forge.EventBuses;
import net.justmili.true_end.TrueEndCommon;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import static org.antlr.runtime.debug.DebugEventListener.PROTOCOL_VERSION;

@Mod(TrueEndCommon.MOD_ID)
public final class TrueEndForge {
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(
            ResourceLocation.parse(TrueEndCommon.MOD_ID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    public TrueEndForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(TrueEndCommon.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        // Run our common setup.
        TrueEndCommon.init();
    }
}
