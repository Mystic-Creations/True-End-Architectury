package net.justmili.true_end.common.client;

import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import net.justmili.true_end.common.TrueEndCommon;
import net.justmili.true_end.common.client.init.TrueEndParticles;
import net.justmili.true_end.common.particle.DreamPortalParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;

import net.justmili.true_end.common.init.TrueEndParticleTypes;
import net.minecraft.core.registries.Registries;

public final class TrueEndCommonClient {

    public static void init() {
        TrueEndParticles.register();
    }
}
