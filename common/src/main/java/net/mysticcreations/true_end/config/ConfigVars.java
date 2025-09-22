package net.mysticcreations.true_end.config;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class ConfigVars {

    @ExpectPlatform
    public static double getRandomEventChance() {
        assert false : "This method should not be called";
        return 0;
    }

    @ExpectPlatform
    public static double getEntitySpawnChance() {
        assert false : "This method should not be called";
        return 0;
    }

    @ExpectPlatform
    public static double getBTDConversationDelay() {
        assert false : "This method should not be called";
        return 0;
    }

    @ExpectPlatform
    public static boolean getCreditsToggle() {
        assert false : "This method should not be called";
        return false;
    }

    @ExpectPlatform
    public static boolean getFogToggle() {
        assert false : "This method should not be called";
        return false;
    }

    @ExpectPlatform
    public static boolean getPopupsToggle() {
        assert false : "This method should not be called";
        return false;
    }

    @ExpectPlatform
    public static boolean getDaytimeChangeToggle() {
        assert false : "This method should not be called";
        return false;
    }

    @ExpectPlatform
    public static boolean getClearDreamItems() {
        assert false : "This method should not be called";
        return false;
    }
}
