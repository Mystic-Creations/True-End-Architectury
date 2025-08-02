package net.justmili.true_end.variables;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class TrueEndVariables {

    @ExpectPlatform
    public static PlayerData getPlayerData(ServerPlayer player) {
        throw new AssertionError("Should never be called");
    }

    @ExpectPlatform
    public static WorldData getLevelData(ServerLevel level) {
        throw new AssertionError("Should never be called");
    }
}
