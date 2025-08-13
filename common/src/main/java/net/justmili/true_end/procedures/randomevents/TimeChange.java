package net.justmili.true_end.procedures.randomevents;

import net.justmili.true_end.config.TEConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class TimeChange {
    public static final int DAY = 1000;
    public static final int NOON = 6000;
    public static final int NIGHT = 13000;
    public static final int MIDNIGHT = 18000;

    public static void onPlayerTick(Player player) {
        if (!TEConfig.randomEventsToggle) return;
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        ServerLevel world = (ServerLevel) player.level();

        long totalDays = world.getDayTime() / 24000;
        if (totalDays < 3) return;
        if (totalDays % 4 != 0) return;
        makeNight(serverPlayer);
        makeDay(serverPlayer);
    }

    public static void makeNight(ServerPlayer player) {
        if (!(Math.random() < (TEConfig.randomEventChance/64))) return;
        ServerLevel world = (ServerLevel) player.level();
        long time = world.getDayTime() % 24000;
        if (time > DAY && time < NIGHT) {
            long newTime = NIGHT + (long) (Math.random() * (MIDNIGHT - NIGHT));
            long total = world.getDayTime() - time + newTime;
            world.setDayTime(total);
        }
    }

    public static void makeDay(ServerPlayer player) {
        if (!(Math.random() < (TEConfig.randomEventChance/64))) return;
        ServerLevel world = (ServerLevel) player.level();
        long time = world.getDayTime() % 24000;
        boolean isNight = time >= MIDNIGHT || time < DAY;
        if (isNight) {
            long newTime = DAY + (long) (Math.random() * (NOON - DAY));
            long total = world.getDayTime() - time + newTime;
            world.setDayTime(total);
        }
    }
}
