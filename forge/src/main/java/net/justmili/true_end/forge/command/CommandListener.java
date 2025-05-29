package net.justmili.true_end.forge.command;

import net.justmili.true_end.common.commands.DevCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommandListener {
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        DevCommand.register(event.getDispatcher());
    }

}
