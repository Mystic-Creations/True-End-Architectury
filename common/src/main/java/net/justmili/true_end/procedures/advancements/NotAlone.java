package net.justmili.true_end.procedures.advancements;

import net.justmili.true_end.config.TEConfig;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import static net.justmili.true_end.init.TEDimKeys.BTD;

public class NotAlone {

    public static void onPlayerTick(Player player) {
        if (player == null) return;
        if (!((player.level().dimension()) == BTD)) return;
        if (!TEConfig.randomEventsToggle) return;
        if (!(Math.random() < TEConfig.randomEventChance / 2)) return;
        if (player instanceof ServerPlayer serverPlayer) {
            Advancement advancement = serverPlayer.server.getAdvancements().getAdvancement(new ResourceLocation("true_end:not_alone"));
            AdvancementProgress progress = serverPlayer.getAdvancements().getOrStartProgress(advancement);
            if (!progress.isDone()) {
                for (String criteria : progress.getRemainingCriteria())
                    serverPlayer.getAdvancements().award(advancement, criteria);
            }
        }
    }
}