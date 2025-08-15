package net.justmili.true_end.init;

import dev.architectury.networking.NetworkManager;
import net.justmili.true_end.commands.calls.screentests.TestCredits;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class TEPackets {

    public static ResourceLocation SHOW_CREDITS_PACKET = new ResourceLocation("true_end:show_credits");

    public static void registerClient() {

        NetworkManager.registerReceiver(NetworkManager.Side.S2C, SHOW_CREDITS_PACKET, (buf, context) -> {
            Player player = context.getPlayer();
            // Logic
            TestCredits.execute(player.getX(), player.getY(), player.getZ());
        });

    }

    public static void registerServer() {




    }

}
