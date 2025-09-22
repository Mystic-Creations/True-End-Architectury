package net.mysticcreations.true_end.procedures.advancements;

import net.mysticcreations.true_end.config.TEConfig;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import static net.mysticcreations.true_end.init.TEDimKeys.BTD;

public class NotAlone {
    public static void grantAdvancement(ServerPlayer player) {
        if (!((player.level().dimension()) == BTD)) return;
        if (!TEConfig.randomEventsToggle) return;
        if (!(Math.random() < TEConfig.randomEventChance)) return;

        Advancement advancement = player.server.getAdvancements().getAdvancement(new ResourceLocation("true_end:not_alone"));
        AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
        if (!progress.isDone()) {
            for (String criteria : progress.getRemainingCriteria())
                player.getAdvancements().award(advancement, criteria);
        }
    }
}