package net.justmili.true_end.procedures.randomevents;

import dev.architectury.registry.menu.MenuRegistry;
import net.justmili.true_end.TrueEndCommon;
import net.justmili.true_end.commands.calls.screens.BlackOverlay;
import net.justmili.true_end.config.TEConfig;
import net.justmili.true_end.init.TEBiomes;
import net.justmili.true_end.init.TEScreens;
import net.justmili.true_end.variables.TEVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.Random;

public class BlackScreenSeepingReality {
    public static void onPlayerTick(Player player) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        if (!player.level().getBiome(player.blockPosition()).is(TEBiomes.SEEPING_REALITY)) {
            TEVariables.getPlayerData(serverPlayer).setSeepingRealityTime(0);
            return;
        }
        if (!TEConfig.flashingLights) return;


        int t = TEVariables.getPlayerData(serverPlayer).getSeepingRealityTime() + 1;
        TEVariables.getPlayerData(serverPlayer).setSeepingRealityTime(t);

        final int PHASE1 = 3 * 60 * 20;    // 3600
        final int PHASE2 = 5 * 60 * 20;    // 6000
        final int PHASE3 = 10 * 60 * 20;   //12000
        final int PHASE4 = 15 * 60 * 20;   //18000
        final int MAX_REPEATS = 5;

        Random rng = new Random();

        if (t == PHASE1) {
            int repeats = 3 + rng.nextInt(MAX_REPEATS - 3 + 1);
            for (int i = 0; i < repeats; i++) {
                BlackOverlay.call(serverPlayer);
                int dur = rng.nextInt(3) + 1; // 1–3 ticks
                TrueEndCommon.queueServerWork(dur, player::closeContainer);
            }
        }

        if (t == PHASE4) {
            int repeats = 3 + rng.nextInt(MAX_REPEATS - 3 + 1);
            for (int i = 0; i < repeats; i++) {
                BlackOverlay.call(serverPlayer);
                int d = rng.nextInt(3) + 1;  // 1–3 ticks
                TrueEndCommon.queueServerWork(d, player::closeContainer);
            }
            BlackOverlay.call(serverPlayer);
            TrueEndCommon.queueServerWork(100, () -> {
                if (player instanceof ServerPlayer sp) {
                    ServerLevel sLevel = (ServerLevel) sp.level();
                    BlockPos resp = sp.getRespawnPosition();
                    if (resp == null) resp = sLevel.getSharedSpawnPos();
                    sp.teleportTo(sLevel, resp.getX() + 0.5, resp.getY() + 1, resp.getZ() + 0.5, sp.getYRot(), sp.getXRot()
                    );
                }
                TEVariables.getPlayerData(serverPlayer).setSeepingRealityTime(0);
            });
            return;
        }

        if (t < PHASE1) return;

        double chance;
        int flashDur;
        if (t < PHASE2) {
            chance   = 0.0005;
            flashDur = rng.nextInt(3) + 1;
        } else if (t < PHASE3) {
            chance   = 0.001;
            flashDur = rng.nextInt(4) + 3;
        } else {
            chance   = 0.002;
            flashDur = rng.nextInt(6) + 2;
        }

        if (rng.nextDouble() < chance) {
            int repeats = 3 + rng.nextInt(MAX_REPEATS - 3 + 1);
            for (int i = 0; i < repeats; i++) {
                BlackOverlay.call(serverPlayer);
                TrueEndCommon.queueServerWork(flashDur, player::closeContainer);
            }
        }

    }
}
