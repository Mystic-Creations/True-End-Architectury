package net.justmili.true_end.init;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class TEDimKeys {
    public static final ResourceKey<Level> NWAD = ResourceKey.create(
            net.minecraft.core.registries.Registries.DIMENSION,
            new ResourceLocation("true_end", "nightmare_within_a_dream")
    );

    public static final ResourceKey<Level> BTD = ResourceKey.create(
            net.minecraft.core.registries.Registries.DIMENSION,
            new ResourceLocation("true_end", "beyond_the_dream")
    );

    public static final ResourceKey<Level> OVERWORLD = Level.OVERWORLD;
}
