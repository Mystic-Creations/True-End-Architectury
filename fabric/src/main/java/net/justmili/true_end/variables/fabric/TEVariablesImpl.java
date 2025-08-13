package net.justmili.true_end.variables.fabric;

import net.justmili.true_end.fabric.ServerPlayerExt;
import net.justmili.true_end.variables.WorldData;
import net.justmili.true_end.variables.PlayerData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class TEVariablesImpl {

    public static PlayerData getPlayerData(ServerPlayer player) {
        PlayerData playerData = ((ServerPlayerExt) player).true_end$getPlayerData();

        return ((ServerPlayerExt) player).true_end$getPlayerData();
    }

    public static WorldData getLevelData(Level level) {
        return WorldData.get(level);
    }
}
