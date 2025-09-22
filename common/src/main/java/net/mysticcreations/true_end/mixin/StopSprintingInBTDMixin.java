package net.mysticcreations.true_end.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
public class StopSprintingInBTDMixin {
/*
    @Shadow
    @Final
    private EntityType<?> type;

    @Shadow
    private Level level;

    @ModifyArg(at = @At("INVOKE"),method = "setSprinting")
    public boolean setSprinting(boolean sprinting) {
        if (type == EntityType.PLAYER && this.level.dimension() == TEDimKeys.BTD) {
            ((EntityAccessor) this).invokeSetSharedFlag(3, false);
            return false;
        }
        return sprinting;
    }

 */
}