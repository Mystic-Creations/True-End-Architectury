package net.justmili.true_end.forge;

import dev.architectury.platform.forge.EventBuses;
import net.justmili.true_end.TrueEndCommon;
import net.justmili.true_end.world.seeping_reality.forge.SeepingForestRegion;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import terrablender.api.Regions;

import static org.antlr.runtime.debug.DebugEventListener.PROTOCOL_VERSION;

@Mod(TrueEndCommon.MOD_ID)
public final class TrueEndForge {
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(
            ResourceLocation.parse(TrueEndCommon.MOD_ID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    public static IEventBus EVENT_BUS;

    public TrueEndForge() {

        EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(TrueEndCommon.MOD_ID, EVENT_BUS);
        // Run our common setup.
        TrueEndCommon.init();

        EVENT_BUS.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new SeepingForestRegion(ResourceLocation.parse("true_end:overworld_region"), 1));
        });
    }
}
