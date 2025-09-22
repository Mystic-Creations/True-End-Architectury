package net.mysticcreations.true_end.config;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.mysticcreations.true_end.init.TEPackets;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class ConfigSync {

    public static void sendFogToggleAll(MinecraftServer server) {
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            sendFogToggle(player);
        }
    }

    public static void sendFogToggle(ServerPlayer player) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeBoolean(TEConfig.fogToggle);
        NetworkManager.sendToPlayer(player, TEPackets.FOG_TOGGLE, buf);
    }
}
