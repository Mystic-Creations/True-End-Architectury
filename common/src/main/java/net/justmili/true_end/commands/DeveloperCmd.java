package net.justmili.true_end.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import net.justmili.true_end.commands.calls.BTDTest;
import net.justmili.true_end.commands.calls.PrintVars;
import net.justmili.true_end.commands.calls.screentests.TestCredits;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class DeveloperCmd {
    public static void register() {
        CommandRegistrationEvent.EVENT.register((dispatcher, registry, selection) -> {
            dispatcher.register(buildCommand());
        });
    }

    private static LiteralArgumentBuilder<CommandSourceStack> buildCommand() {
        return Commands.literal("trueend").requires(s -> s.hasPermission(4))
                .then(Commands.literal("testScreen")
                        .then(Commands.literal("credits").executes(arguments -> {
                            double x = arguments.getSource().getPosition().x();
                            double y = arguments.getSource().getPosition().y();
                            double z = arguments.getSource().getPosition().z();
                            TestCredits.execute(x, y, z);
                            return 0;
                        }))
                        .then(Commands.literal("funny").executes(arguments -> {
                            Level world = arguments.getSource().getLevel();
                            double x = arguments.getSource().getPosition().x();
                            double y = arguments.getSource().getPosition().y();
                            double z = arguments.getSource().getPosition().z();
                            Entity entity = arguments.getSource().getEntity();

                            //TestFunny.execute(world, x, y, z, entity);
                            return 0;
                        }))
                        .then(Commands.literal("black").executes(arguments -> {
                            Level world = arguments.getSource().getLevel();
                            double x = arguments.getSource().getPosition().x();
                            double y = arguments.getSource().getPosition().y();
                            double z = arguments.getSource().getPosition().z();
                            Entity entity = arguments.getSource().getEntity();

                            //TestBlackOverlay.execute(world, x, y, z, entity);
                            return 0;
                        }))
                )
                .then(Commands.literal("printVars").executes(arguments -> {
                    Level world = arguments.getSource().getLevel();
                    Entity entity = arguments.getSource().getEntity();

                    if (entity instanceof ServerPlayer player) {
                        PrintVars.execute(world, player, arguments.getSource());
                    }
                    return 0;
                }))
                .then(Commands.literal("testBTD").executes(arguments -> {
                    Level world = arguments.getSource().getLevel();
                    Entity entity = arguments.getSource().getEntity();

                    BTDTest.execute(world, entity);
                    return 0;
                }))
                .then(Commands.literal("clearCurios").executes(arguments -> {
                    ServerPlayer player = arguments.getSource().getPlayer();
                    //PlayerInvManager.clearCuriosSlots(player); not gonna bother
                    return 0;
                }));
    }
}
