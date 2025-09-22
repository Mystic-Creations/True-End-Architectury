package net.mysticcreations.true_end.init;

import dev.architectury.networking.NetworkManager;
import net.mysticcreations.true_end.commands.calls.screentests.TestCredits;
import net.mysticcreations.true_end.config.TEConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class TEPackets {

    public static ResourceLocation SHOW_CREDITS_PACKET = new ResourceLocation("true_end:show_credits");
    public static ResourceLocation FOG_TOGGLE =  new ResourceLocation("true_end:fog_toggle");
    public static ResourceLocation OPEN_INVENTORY_PACKET = new ResourceLocation("true_end:open_inventory");

    public static void registerClient() {

        NetworkManager.registerReceiver(NetworkManager.Side.S2C, SHOW_CREDITS_PACKET, (buf, context) -> {
            Player player = context.getPlayer();
            // Logic
            TestCredits.execute(player.getX(), player.getY(), player.getZ());
        });

        NetworkManager.registerReceiver(NetworkManager.Side.S2C, FOG_TOGGLE, (buf, context) -> {
            TEConfig.fogToggleClient = buf.readBoolean();
        });

    }

    public static void registerServer() {



    }

}
