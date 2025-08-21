package net.justmili.true_end.init;


import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.true_end.TrueEndCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class TESounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(TrueEndCommon.MOD_ID,Registries.SOUND_EVENT);
    private static RegistrySupplier<SoundEvent> sound(String name) {
        return REGISTRY.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(TrueEndCommon.MOD_ID+":"+name)));
    }
    public static final RegistrySupplier<SoundEvent> VINE_BOOM = sound("vine_boom");
    public static final RegistrySupplier<SoundEvent> MOD_CREDITS_MUSIC = sound("back_in_the_game");
    public static final RegistrySupplier<SoundEvent> MUSIC_FARLANDS = sound("farlands");
    public static final RegistrySupplier<SoundEvent> MUSIC_NEVER_ALONE = sound("never_alone");
    public static final RegistrySupplier<SoundEvent> DAISY_BELL = sound("daisy_bell");

    public static void register() {
        REGISTRY.register();
    }
}