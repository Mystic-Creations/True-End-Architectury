package net.mysticcreations.true_end.procedures.events;

import dev.architectury.event.EventResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class NoVoidDamage {
    public static EventResult onEntityAttacked(LivingEntity entity, DamageSource source, float amount) {

        if (source == null) return EventResult.pass();
        if (source.is(DamageTypes.FELL_OUT_OF_WORLD) && entity.getY() >= -2010) return EventResult.interruptTrue();
        //It's set to -2010 cuz year 2010 is when Alpha 1.1.2_01 released

        return EventResult.pass();
    }
}
