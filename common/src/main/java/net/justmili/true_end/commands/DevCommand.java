package net.justmili.true_end.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.justmili.true_end.variables.PlayerData;
import net.justmili.true_end.variables.WorldData;
import net.justmili.true_end.variables.TrueEndVariables;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import static net.justmili.true_end.init.TrueEndDimKeys.BTD;

public class DevCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("true_end")
                        .then(Commands.literal("printVars")
                                .executes(ctx -> {
                                    WorldData worldData = WorldData.get(ctx.getSource().getLevel());

                                    ServerPlayer player = ctx.getSource().getPlayer();

                                    // Player vars
                                    player.sendSystemMessage(Component.literal("----= Per-Player"));
                                    for (ServerPlayer otherPlayer : player.server.getPlayerList().getPlayers()) {
                                        PlayerData playerData = TrueEndVariables.getPlayerData(otherPlayer);
                                        player.sendSystemMessage(Component.literal("-- Player: " + otherPlayer.getName()));
                                        player.sendSystemMessage(Component.literal("hasBeenBeyond: " + playerData.getBeenBeyond()));
                                        player.sendSystemMessage(Component.literal("seepingRealityTime: " + playerData.getSeepingRealityTime()));
                                    }

                                    // World vars
                                    player.sendSystemMessage(Component.literal("\n----= World Variables"));
                                    if (player.level().dimension() == BTD) {
                                        WorldData worldData2 = WorldData.get(ctx.getSource().getLevel());
                                        double btdSpawnX =  worldData.getBtdSpawnX();
                                        double btdSpawnY =  worldData.getBtdSpawnY();
                                        double btdSpawnZ =  worldData.getBtdSpawnZ();
                                        player.sendSystemMessage(Component.literal("btdSpawnX/Y/Z: "+btdSpawnX+"/"+btdSpawnY +"/"+btdSpawnZ));
                                    } else {
                                        player.sendSystemMessage(Component.literal("You're not in BTD"));
                                    }
                                    player.sendSystemMessage(Component.literal("unknownInWorld: " + worldData.isUnknownInWorld()));
                                    // Config vars
                                    /*
                                    source.sendSystemMessage(Component.literal("\n----= Config Variables"));
                                    source.sendSystemMessage(Component.literal("btdConversationDelay: " + worldData.btdConversationDelay));
                                    source.sendSystemMessage(Component.literal("randomEventChance: " + Variables.randomEventChance));
                                    source.sendSystemMessage(Component.literal("entitySpawnChance: " + Variables.entitySpawnChance));
                                    source.sendSystemMessage(Component.literal("creditsToggle: " + Variables.creditsToggle));
                                    source.sendSystemMessage(Component.literal("popupsToggle: " + Variables.popupsToggle));
                                    source.sendSystemMessage(Component.literal("fogToggle: " + Variables.fogToggle));
                                    source.sendSystemMessage(Component.literal("daytimeChangeToggle: " + Variables.daytimeChangeToggle));
                                    source.sendSystemMessage(Component.literal("clearDreamItems: " + Variables.daytimeChangeToggle));
                                    source.sendSystemMessage(Component.literal("flashingLights: " + Variables.flashingLights));


                                     */
                                    return 1;
                                })
                        )
        );
    }
}
