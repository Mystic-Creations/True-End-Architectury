package net.mysticcreations.true_end.config;

import net.mysticcreations.true_end.TrueEndCommon;
import net.mysticcreations.true_end.config.serializer.GsonSerializer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import org.slf4j.event.Level;

import java.util.Map;
import java.util.HashMap;

public class TEConfig {

    public static final GsonSerializer serializer = new GsonSerializer("TrueEnd_COMMON");
    public static Map<String, Object> entries;

    public static double randomEventChance = 0.05;
    public static double entitySpawnChance = 0.05;
    public static int btdConversationDelay = 40;
    public static boolean randomEventsToggle = true;
    public static boolean popupsToggle = true;
    public static boolean fogToggle = true;
    public static boolean creditsToggle = true;
    public static boolean flashingLights = true;
    public static boolean daytimeChangeToggle = true;
    public static boolean clearDreamItems = true;

    public static boolean fogToggleClient;

    public static void setup() {
        Map<String, Object> entries = TEConfig.serializer.deserialize();
        entries = entries == null ? new HashMap<>() : entries;

        entries.putIfAbsent("randomEventChance", 0.005d);
        entries.putIfAbsent("entitySpawnChance", 0.05d);
        entries.putIfAbsent("btdConversationDelay", 40d);
        entries.putIfAbsent("randomEventsToggle", true);
        entries.putIfAbsent("popupsToggle", true);
        entries.putIfAbsent("fogToggle", true);
        entries.putIfAbsent("creditsToggle", true);
        entries.putIfAbsent("flashingLights", true);
        entries.putIfAbsent("daytimeChangeToggle", true);
        entries.putIfAbsent("clearDreamItems", true);
        entries.putIfAbsent("nostalgicTweaksCompatability", true);

        randomEventChance = (double) entries.get("randomEventChance");
        entitySpawnChance = (double) entries.get("entitySpawnChance");
        btdConversationDelay = Math.toIntExact(Math.round((double) entries.get("btdConversationDelay")));
        randomEventsToggle = (boolean) entries.get("randomEventsToggle");
        popupsToggle = (boolean) entries.get("popupsToggle");
        fogToggle = (boolean) entries.get("fogToggle");
        creditsToggle = (boolean) entries.get("creditsToggle");
        flashingLights = (boolean) entries.get("flashingLights");
        daytimeChangeToggle = (boolean) entries.get("daytimeChangeToggle");
        clearDreamItems = (boolean) entries.get("clearDreamItems");

        TEConfig.entries = entries;
        serializer.serialize(TEConfig.entries);
        TrueEndCommon.LOGGER.info("[TrueEnd] Successfully loaded TrueEnd Config!");
        TrueEndCommon.LOGGER.atLevel(Level.DEBUG).log(TEConfig.serializer.getMessage());
    }

    public static void updateConfig(String key, Object value) {
        entries.put(key, value);
        serializer.serialize(entries);

        switch (key) {
            case "randomEventChance" -> randomEventChance = (double)  value;
            case "entitySpawnChance" -> entitySpawnChance = (double)  value;
            case "btdConversationDelay" -> btdConversationDelay = (int) value;
            case "creditsToggle" -> creditsToggle = (boolean) value;
            case "fogToggle" -> fogToggle = (boolean) value;
            case "popupsToggle" -> popupsToggle = (boolean) value;
            case "daytimeChangeToggle" -> daytimeChangeToggle = (boolean) value;
            case "clearDreamItems" -> clearDreamItems = (boolean) value;
            case "flashingLights" -> flashingLights = (boolean) value;
            default -> TrueEndCommon.LOGGER.warn("updateConfig: unhandled key '{}'", key);
        }
    }

    public static int handleBoolean(CommandSourceStack src, String key, boolean value) {
        try {
            updateConfig(key, value);
        } catch (Exception e) {
            src.sendFailure(Component.literal("Failed to update config '" + key + "': " + e.getMessage()));
            return 0;
        }
        src.sendSuccess(() -> Component.literal("Config '" + key + "' set to " + value), false);
        return 1;
    }
    public static int handleDouble(CommandSourceStack src, String key, double value) {
        try {
            updateConfig(key, value);
        } catch (Exception e) {
            src.sendFailure(Component.literal("Failed to update config '" + key + "': " + e.getMessage()));
            return 0;
        }
        src.sendSuccess(() -> Component.literal("Config '" + key + "' set to " + value), false);
        return 1;
    }
    public static int handleInt(CommandSourceStack src, String key, int value) {
        return handleDouble(src, key, value);
    }
}
