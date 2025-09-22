package net.mysticcreations.true_end.world.dimension;

import net.mysticcreations.true_end.config.TEConfig;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class BeyondTheDream extends DimensionSpecialEffects {

    public BeyondTheDream(float cloudLevel, boolean hasGround, SkyType skyType, boolean forceBrightLightmap, boolean constantAmbientLight) {
        super(cloudLevel, hasGround, skyType, forceBrightLightmap, constantAmbientLight);
    }

    @Override
    public @NotNull Vec3 getBrightnessDependentFogColor(@NotNull Vec3 color, float sunHeight) {
        return color;
    }

    @Override
    public boolean isFoggyAt(int x, int y) {
        return TEConfig.fogToggleClient;
    }
}
