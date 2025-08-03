package net.justmili.true_end.init;

import net.justmili.true_end.particle.DreamPortalParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;

import net.justmili.true_end.TrueEndCommon;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;

public class TrueEndParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(TrueEndCommon.MOD_ID, Registries.PARTICLE_TYPE);

    public static final RegistrySupplier<SimpleParticleType> DREAM_PORTAL_PARTICLE =
            PARTICLE_TYPES.register("dream_portal_particle", () -> new DreamPortalParticleType(false));

    public static void register() {
        PARTICLE_TYPES.register();
    }
}



