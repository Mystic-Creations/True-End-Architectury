package net.justmili.true_end.procedures.events;

import net.justmili.true_end.config.TEConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import static net.justmili.true_end.init.TEDimKeys.BTD;

public class PlayCredits {
    private static boolean hasShownCreditsThisSession = false;

    public static void onDimensionChange(ServerPlayer player, ServerLevel fromWorld,  ServerLevel toWorld) {
        if (TEConfig.creditsToggle) hasShownCreditsThisSession = false;
        if (hasShownCreditsThisSession) return;

        if (fromWorld.dimension() == BTD && toWorld.dimension() == Level.OVERWORLD) {
            hasShownCreditsThisSession = true;
            TEConfig.updateConfig("creditsToggle", false);

            //TestCredits.execute(player.x, player.y);
        }
    }
}