package net.justmili.true_end.procedures.events;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.justmili.true_end.config.TEConfig;
import net.justmili.true_end.init.TEPackets;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import static net.justmili.true_end.init.TEDimKeys.BTD;

public class PlayCredits {
    private static boolean hasShownCreditsThisSession = false;

    public static void onDimensionChange(ServerPlayer player, ResourceKey<Level> fromWorld, ResourceKey<Level> toWorld) {
        if (TEConfig.creditsToggle) hasShownCreditsThisSession = false;
        if (hasShownCreditsThisSession) return;

        if (fromWorld == BTD && toWorld == Level.OVERWORLD) {
            hasShownCreditsThisSession = true;
            TEConfig.updateConfig("creditsToggle", false);

            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
            NetworkManager.sendToPlayer(player, TEPackets.SHOW_CREDITS_PACKET, buf);
        }
    }
}