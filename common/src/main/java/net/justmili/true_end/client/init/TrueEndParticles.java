package net.justmili.true_end.client.init;

import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import net.justmili.true_end.init.TEParticleTypes;
import net.justmili.true_end.particle.DreamPortalParticle;

public class TrueEndParticles {

    public static void register() {
        ParticleProviderRegistry.register(TEParticleTypes.DREAM_PORTAL_PARTICLE.get(), DreamPortalParticle::provider);
    }

}
