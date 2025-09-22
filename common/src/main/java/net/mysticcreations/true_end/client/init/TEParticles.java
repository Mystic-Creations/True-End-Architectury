package net.mysticcreations.true_end.client.init;

import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import net.mysticcreations.true_end.init.TEParticleTypes;
import net.mysticcreations.true_end.particle.DreamPortalParticle;

public class TEParticles {

    public static void register() {
        ParticleProviderRegistry.register(TEParticleTypes.DREAM_PORTAL_PARTICLE.get(), DreamPortalParticle::provider);
    }

}
