
package net.mysticcreations.true_end.procedures.alphafeatures;

import net.mysticcreations.true_end.init.TEDimKeys;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class NoCooldown {
    private static final AttributeModifier modifier = new AttributeModifier("9b91a426-cc5c-4a08-a0e5-7d00627cb3ef",200.0, AttributeModifier.Operation.ADDITION);

    public static void onPlayerChangedDimension(ServerPlayer player, ResourceKey<Level> fromDimension, ResourceKey<Level> toDimension) {
        applyCooldown(player, toDimension);
    }

    public static void onPlayerRespawn(ServerPlayer player, boolean bl) {
        ResourceKey<Level> toDim = ((ServerPlayer) player).getRespawnDimension();

        applyCooldown(player, toDim);
    }

    private static void applyCooldown(Player player, ResourceKey<Level> toDim) {


        AttributeInstance attackSpeedAttr = player.getAttribute(Attributes.ATTACK_SPEED);
        if (attackSpeedAttr == null) return;

        if (toDim.equals(TEDimKeys.BTD)) {
            attackSpeedAttr.addPermanentModifier(modifier);
        } else {
            attackSpeedAttr.removeModifier(modifier);
        }
    }
}