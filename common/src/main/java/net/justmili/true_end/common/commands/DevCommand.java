package net.justmili.true_end.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class DevCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("true_end")
                        .then(Commands.argument("text", StringArgumentType.string())
                                .then(Commands.argument("number", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            String text = StringArgumentType.getString(context, "text");
                                            int number = IntegerArgumentType.getInteger(context, "number");

                                            context.getSource().sendSuccess(
                                                    () -> net.minecraft.network.chat.Component.literal("Received: " + text + ", " + number),
                                                    false
                                            );

                                            return 1;
                                        })))
        );
    }
}
