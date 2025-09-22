package net.mysticcreations.true_end.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class NoStomachGrowlSFX {
    private static final ResourceLocation STOMACH_GROWL = new ResourceLocation("subtle_effects:entity.player.stomach_growl");

    //Note: someone make a mixin for this bullshit please
    public static void onPlaySound(PlaySoundEvent event) {
        var soundInstance = event.getSound();
        if (soundInstance == null) return;

        ResourceLocation id = soundInstance.getLocation();
        if (STOMACH_GROWL.equals(id)) {
            event.setSound(null);
        }
    }
}
