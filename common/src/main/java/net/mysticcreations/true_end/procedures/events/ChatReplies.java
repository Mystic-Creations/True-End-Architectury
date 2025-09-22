package net.mysticcreations.true_end.procedures.events;

import dev.architectury.event.EventResult;
import net.mysticcreations.true_end.TrueEndCommon;
import net.mysticcreations.true_end.config.TEConfig;
import net.mysticcreations.true_end.init.TEDimKeys;
import net.mysticcreations.true_end.procedures.randomevents.TimeChange;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelAccessor;

import java.util.Locale;
import java.util.Random;

public class ChatReplies {
    //Detection & Util
    public static EventResult onChat(ServerPlayer player, Component component) {
        LevelAccessor world = player.serverLevel();

        MinecraftServer server = player.getServer();
        if (server != null) server.execute(() -> {
            String msg = component.getString().toLowerCase(Locale.ROOT).trim().replaceAll("[!?.-]+$", "");
            hardcodedReplies(world, msg, player);
        });

        return EventResult.pass();
    }
    private static void sendChatReply(LevelAccessor world, String msg, Integer delay) {
        if (!world.isClientSide() && world.getServer() != null) {
            TrueEndCommon.queueServerWork(delay, () -> {
                MinecraftServer server = world.getServer();
                server.getPlayerList().broadcastSystemMessage(Component.literal(msg), false);
            });
        }
    }

    //Main components
    public static void hardcodedReplies(LevelAccessor world, String msg, ServerPlayer player) {
        int delay = (int)((Math.random()*50)+15);
        switch (msg) {
            case "sleep" -> sendChatReply(world, "<§kUnknown§r> No more.", delay);
            case "awake" -> sendChatReply(world, "<§kUnknown§r> You.", delay);
            case "where from" -> sendChatReply(world, "<§kUnknown§r> The fog.", delay);
            case "why is it night" -> nightReply(player);
            case "is anyone there", "anyone there" -> sendChatReply(world, "<§kUnknown§r> Yes.", delay);
            case "who are you", "what is your name", "what's your name", "whats your name"
                    -> sendChatReply(world, "<§kUnknown§r> Unknown. Forgotten.", delay);
            case "nightmare" -> sendChatReply(world, "<§kUnknown§r> Within.", delay);
            case "fuck you" -> punish(player);
            case "where am i" -> sendChatReply(world, "<§kUnknown§r> "+(int)player.getX()+"/"+(int)player.getY()+"/"+(int)player.getZ()+".", delay);
            case "where are you" -> sendChatReply(world,"<§kUnknown§r> U29tZXdoZXJlIGNsb3NlLg==", delay);
            case "hello", "hi" -> sendChatReply(world, "<§kUnknown§r> Hi.", delay);
            case "go away", "please go away", "leave me alone", "can you leave me alone", "can you go away", "please leave me alone"
                    -> sendChatReply(world, "<§kUnknown§r> I can't.", delay);

            //Easter eggs
            case "28/09/1939", "09/28/1939" -> meetAgain(player); //Reference to "We'll meet again" by Vera Lynn, with that also Gravity Falls but also fits with the last words said by the voices in the mod
            case "null" -> sendChatReply(world, "<§kUnknown§r> I'm not Null", delay);
            case "the broken script" -> sendChatReply(world, "<§kUnknown§r> Inspiration.", delay);
            case "zarsai", "zarsaivt", "shinhoa", "shinhoaz", "fireydude", "imfireydude" -> sendChatReply(world, "<SillyMili> <3", 30);
            default -> randomReplies(world, player);
        }
    }
    public static void randomReplies(LevelAccessor world, ServerPlayer player) {
        if (player.level().dimension() == TEDimKeys.BTD) return;
        if (!(Math.random() < (TEConfig.randomEventChance)/48)) return;
        int delay = 15+(int)(Math.random()*46);
        String[] messages = {
                "<§kUnknown§r> This isn't real.",
                "<§kUnknown§r> Wake up.",
                "<§kUnknown§r> "+player.getName().getString().trim()+".",
                "<§kUnknown§r> They see you.",
                "<§kUnknown§r> The world changes, but not them."
        };
        String chat = messages[new Random().nextInt(messages.length)];
        sendChatReply(world, chat, delay);
    }

    //More behavior
    private static void nightReply(ServerPlayer player) {
        ServerLevel world = (ServerLevel) player.level();
        int delay = (int)((Math.random()*50)+15);
        long time = world.getGameTime() % 24000;
        boolean isDay = time >= TimeChange.DAY && time < TimeChange.NIGHT;
        if (!isDay) {
            sendChatReply(world, "<§kUnknown§r> Sleep.", delay);
        } else {
            sendChatReply(world, "<§kUnknown§r> It is not.", delay);
        }
    }
    private static void punish(ServerPlayer player) {
        int delay = (int) ((Math.random()*50)+20);
        MinecraftServer server = player.server;
        String playerName = player.getDisplayName().getString();
        String textA = "§7§o["+playerName+"'s game mode has been changed to Adventure Mode by §kUnknown§r§7§o]";
        String textS = "§7§o["+playerName+"'s game mode has been changed to Survival Mode by §kUnknown§r§7§o]";

        TrueEndCommon.queueServerWork(delay, () -> {
            player.setGameMode(GameType.ADVENTURE);
            server.getPlayerList().broadcastSystemMessage(Component.literal(textA), false);
            TrueEndCommon.queueServerWork(6000, () -> {
                player.setGameMode(GameType.SURVIVAL);
                server.getPlayerList().broadcastSystemMessage(Component.literal(textS), false);
            });
        });
    }
    private static void meetAgain(ServerPlayer player) {
        ServerLevel world = (ServerLevel) player.level();

        String[] lines = {
                "§3We'll meet again",
                "§2Don't know where",
                "§3Don't know when",
                "§2But I know we'll meet again",
                "§9Some sunny day",
                "§3Keep smiling through",
                "§2Just like you",
                "§3always do",
                "§2'Til the blue skies chase those dark clouds",
                "§9Far away"
        };
        int[] delays = { 45, 40, 40, 40, 50, 55, 40, 40, 45, 50 };

        int cumulative = 0;
        for (int i = 0; i < lines.length; i++) {
            cumulative += delays[i];
            int idx = i;
            TrueEndCommon.queueServerWork(cumulative, () -> {
                sendChatReply(world, lines[idx], 0);
            });
        }
    }
}