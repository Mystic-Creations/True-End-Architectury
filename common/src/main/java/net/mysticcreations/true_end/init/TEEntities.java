package net.mysticcreations.true_end.init;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.mysticcreations.true_end.TrueEndCommon;
import net.mysticcreations.true_end.entity.Unknown;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class TEEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(TrueEndCommon.MOD_ID, Registries.ENTITY_TYPE);
    public static final RegistrySupplier<EntityType<Unknown>> UNKNOWN = REGISTRY.register("unknown",  () -> EntityType.Builder.<Unknown>of(Unknown::new, MobCategory.CREATURE)
            .sized(0.6F, 1.95F)
            .build("unknown")
    );

    public static void register() {
        REGISTRY.register();

        EntityAttributeRegistry.register(UNKNOWN, Unknown::createAttributes);
    }
}