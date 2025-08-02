package net.justmili.true_end.procedures.events;

import net.justmili.true_end.init.TrueEndDimKeys;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class FoodLvlReset {
    public static void onChangeDimension(ServerPlayer player, ResourceKey<Level> fromDimension, ResourceKey<Level> toDimension) {

        if (fromDimension == TrueEndDimKeys.BTD && toDimension == Level.OVERWORLD) {
            player.getFoodData().setFoodLevel(20);
            player.getFoodData().setSaturation(10.0f);
        }
    }
}
