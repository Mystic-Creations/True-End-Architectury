package net.justmili.true_end.procedures.alphafeatures;

import dev.architectury.platform.Platform;
import net.justmili.true_end.config.TEConfig;
import net.justmili.true_end.init.TEDimKeys;
import net.minecraft.world.entity.player.Player;

public class NoSprint {
    public static void onPlayerTick(Player player) {
        if (player.level().dimension() == TEDimKeys.BTD) {
            player.getFoodData().setFoodLevel(4);
            player.getFoodData().setSaturation(0.0F);
        }
    }
}