package net.mysticcreations.true_end.mixin;

import net.mysticcreations.true_end.procedures.randomevents.SleepPopup;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class PlayerSleepMixin extends LivingEntity {

    protected PlayerSleepMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void startSleepInBed(CallbackInfo ci) {
        if (this.isSleeping()) {
            SleepPopup.onPlayerInBed((LocalPlayer) ((LivingEntity) this));
        }
    }
}
