package net.justmili.true_end.client.forge;

import net.justmili.true_end.client.init.TEParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TEParticlesForge {
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        TEParticles.register();
    }

}
