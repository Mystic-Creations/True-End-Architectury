package net.justmili.true_end.commands.calls;

import net.justmili.true_end.config.TrueEndConfig;
import net.justmili.true_end.variables.PlayerData;
import net.justmili.true_end.variables.TrueEndVariables;
import net.justmili.true_end.variables.WorldData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import static net.justmili.true_end.init.TrueEndDimKeys.BTD;

public class PrintVars {

    public static void execute(Level world, ServerPlayer player, CommandSourceStack source) {
        if (player == null || source == null)
            return;

        WorldData getVariable = TrueEndVariables.getLevelData(world);

        int btdSpawnX = (int) getVariable.getBtdSpawnX();
        int btdSpawnY = (int) getVariable.getBtdSpawnY();
        int btdSpawnZ = (int) getVariable.getBtdSpawnZ();

        // Player vars
        source.sendSystemMessage(Component.literal("----= Per-Player"));
        for (ServerPlayer otherPlayer : player.server.getPlayerList().getPlayers()) {
            PlayerData vars = TrueEndVariables.getPlayerData(otherPlayer);
            source.sendSystemMessage(
                    Component.literal("beenBeyond (" + otherPlayer.getName().getString() + "): " + vars.getBeenBeyond())
            );
        }

        // World vars
        source.sendSystemMessage(Component.literal("\n----= World Variables"));
        if (player.level().dimension() == BTD) {
            source.sendSystemMessage(Component.literal("btdSpawnX/Y/Z: "+btdSpawnX+"/"+btdSpawnY +"/"+btdSpawnZ));
        } else {
            source.sendSystemMessage(Component.literal("You're not in BTD"));
        }
        source.sendSystemMessage(Component.literal("unknownInWorld: " + getVariable.isUnknownInWorld()));
        // Config vars
        source.sendSystemMessage(Component.literal("\n----= Config Variables"));
        source.sendSystemMessage(Component.literal("btdConversationDelay: " + TrueEndConfig.btdConversationDelay));
        source.sendSystemMessage(Component.literal("randomEventChance: " + TrueEndConfig.randomEventChance));
        source.sendSystemMessage(Component.literal("entitySpawnChance: " + TrueEndConfig.entitySpawnChance));
        source.sendSystemMessage(Component.literal("creditsToggle: " + TrueEndConfig.creditsToggle));
        source.sendSystemMessage(Component.literal("popupsToggle: " + TrueEndConfig.popupsToggle));
        source.sendSystemMessage(Component.literal("fogToggle: " + TrueEndConfig.fogToggle));
        source.sendSystemMessage(Component.literal("daytimeChangeToggle: " + TrueEndConfig.daytimeChangeToggle));
        source.sendSystemMessage(Component.literal("clearDreamItems: " + TrueEndConfig.daytimeChangeToggle));
        source.sendSystemMessage(Component.literal("flashingLights: " + TrueEndConfig.flashingLights));
    }
}
