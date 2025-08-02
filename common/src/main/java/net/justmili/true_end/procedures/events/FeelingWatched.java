package net.justmili.true_end.procedures.events;

import net.justmili.true_end.init.TrueEndDimKeys;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
public class FeelingWatched {
	public static void onEntityEndSleep(Player player) {
		if (!player.level().isClientSide() && (player.level().dimension()) == TrueEndDimKeys.BTD)
			player.displayClientMessage(Component.translatable("events.true_end.feelingwatched"), true);
	}
}
