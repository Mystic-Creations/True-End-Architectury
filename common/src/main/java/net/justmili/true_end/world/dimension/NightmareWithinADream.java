package net.justmili.true_end.world.dimension;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;

import java.awt.*;

public class NightmareWithinADream extends DimensionSpecialEffects {
    public NightmareWithinADream(float cloudLevel, boolean hasGround, SkyType skyType, boolean forceBrightLightmap, boolean constantAmbientLight) {
        super(cloudLevel, hasGround, skyType, forceBrightLightmap, constantAmbientLight);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 color, float sunHeight) {
        return new Vec3(0, 0, 0);
    }

    @Override
    public boolean isFoggyAt(int x, int y) {
        return true;
    }
}
