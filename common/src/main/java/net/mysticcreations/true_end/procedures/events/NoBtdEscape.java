package net.mysticcreations.true_end.procedures.events;

import dev.architectury.event.EventResult;
import net.mysticcreations.true_end.variables.TEVariables;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static net.mysticcreations.true_end.init.TEDimKeys.BTD;

public class NoBtdEscape {
    private static final Map<UUID, ResourceKey<Level>> diedIn = new HashMap<>();

    public static EventResult onPlayerDeath(LivingEntity entity, DamageSource damageSource) {
        if (!(entity instanceof ServerPlayer player)) return EventResult.pass();
        diedIn.put(player.getUUID(), player.level().dimension());
        
        return EventResult.pass();
    }

    public static void onPlayerRespawn(ServerPlayer player, boolean b) {
        boolean leftBtd = player.getAdvancements()
                .getOrStartProgress(
                        Objects.requireNonNull(player.server.getAdvancements().getAdvancement(new ResourceLocation("true_end:go_back")))
                ).isDone();
        if (leftBtd) return;

        UUID uuid = player.getUUID();
        ResourceKey<Level> dim = diedIn.remove(uuid);
        if (dim == null || dim != BTD) return;

        double bx = TEVariables.getLevelData(player.server.getLevel(dim)).getBtdSpawnX();
        double by = TEVariables.getLevelData(player.server.getLevel(dim)).getBtdSpawnY();
        double bz = TEVariables.getLevelData(player.server.getLevel(dim)).getBtdSpawnZ();
        ServerLevel btd = player.server.getLevel(BTD);
        if (btd == null) return;

        player.teleportTo(btd, bx, by+0.2, bz, 0, 0);
    }
}
