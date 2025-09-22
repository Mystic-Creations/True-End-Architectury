package net.mysticcreations.true_end.procedures.randomevents;

import net.mysticcreations.true_end.config.TEConfig;
import net.mysticcreations.true_end.interfaces.User32;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class SleepPopup {
    public static void onPlayerInBed(LocalPlayer player) {
        player.sendSystemMessage(Component.literal("fuck you`111"));
        if (!TEConfig.randomEventsToggle) return;
        if (!TEConfig.popupsToggle) return;
        //if (!(Math.random() < TrueEndConfig.randomEventChance * 1.5)) return;
        if (player.level().dimension() != Level.OVERWORLD) return;
        player.sendSystemMessage(Component.literal("fuck you"));

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!player.isSleeping()) return;
            String osName = System.getProperty("os.name").toLowerCase();

            if (osName.contains("win")) {
                User32.INSTANCE.MessageBoxA(0L, "wake up.", "", 0);
            }
            //We'll do this shit for Linux and MacOS some other time
        }).start();
    }
}