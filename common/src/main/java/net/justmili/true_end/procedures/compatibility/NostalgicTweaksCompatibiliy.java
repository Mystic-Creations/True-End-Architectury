package net.justmili.true_end.procedures.compatibility;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.platform.Platform;
import net.justmili.true_end.config.TEConfig;
import net.justmili.true_end.init.TEDimKeys;
import net.minecraft.world.entity.player.Player;

public class NostalgicTweaksCompatibiliy {

    public static boolean justOut = false;

    public static void onPlayerTick(Player player) {
        // check for client side
        if (!TEConfig.nostalgicTweaksCompatability) return;
        if (!Platform.isModLoaded("nostalgic_tweaks")) return;
        if (player.level().dimension() == TEDimKeys.BTD) {
            loadVars();
            justOut = true;
        } else {

            if (justOut) {

                justOut = false;
                restoreVars();
            }
            storeVars();
        }
    }

    @ExpectPlatform
    public static void loadVars() {

    }

    @ExpectPlatform
    public static void restoreVars() {

    }

    @ExpectPlatform
    public static void storeVars() {

    }
}
