package net.justmili.true_end.variables.fabric;

import net.justmili.true_end.fabric.ServerPlayerExt;
import net.justmili.true_end.variables.WorldData;
import net.justmili.true_end.variables.PlayerData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class TrueEndVariablesImpl {

    public static PlayerData getPlayerData(ServerPlayer player) {
        PlayerData playerData = ((ServerPlayerExt) player).true_end$getPlayerData();

        return ((ServerPlayerExt) player).true_end$getPlayerData();
    }

    public static WorldData getLevelData(ServerLevel level) {
        return WorldData.get(level);
    }
}
