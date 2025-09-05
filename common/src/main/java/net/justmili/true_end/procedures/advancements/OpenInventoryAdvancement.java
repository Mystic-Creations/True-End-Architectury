package net.justmili.true_end.procedures.advancements;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.justmili.true_end.init.TEPackets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.FriendlyByteBuf;

public class OpenInventoryAdvancement {
    private static boolean hasOpenedInventory = false;
    public static void onClientTick(Minecraft mc) {
        if (mc.screen instanceof InventoryScreen) {
            if (!hasOpenedInventory) {
                hasOpenedInventory = true;
                FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
                NetworkManager.sendToServer(TEPackets.OPEN_INVENTORY_PACKET, buf);
            }
        } else {
            hasOpenedInventory = false;
        }
    }
}