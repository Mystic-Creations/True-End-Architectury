package net.justmili.true_end.common.client.init;

import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import net.justmili.true_end.common.init.TrueEndParticleTypes;
import net.justmili.true_end.common.particle.DreamPortalParticle;
import net.minecraft.core.particles.ParticleOptions;

public class TrueEndParticles {

    public static void register() {
        ParticleProviderRegistry.register(TrueEndParticleTypes.DREAM_PORTAL_PARTICLE.get(), DreamPortalParticle::provider);
    }

}
