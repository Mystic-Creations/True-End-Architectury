package net.justmili.true_end.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public abstract class TrueEndMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onServerInit(CallbackInfo ci) {
        // no-op
    }
}