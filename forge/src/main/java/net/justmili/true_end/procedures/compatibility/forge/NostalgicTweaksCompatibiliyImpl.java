package net.justmili.true_end.procedures.compatibility.forge;

import mod.adrenix.nostalgic.tweak.config.CandyTweak;
import net.justmili.true_end.procedures.compatibility.NTVars;

public class NostalgicTweaksCompatibiliyImpl {

    public static NTVars ntVars;

    public static void storeVars() {
        ntVars = new NTVars(

                CandyTweak.HIDE_STAMINA_BAR.get(),
                CandyTweak.OLD_VERSION_OVERLAY.get()
        );
    }

    public static void restoreVars() {

        CandyTweak.OLD_VERSION_OVERLAY.setCacheAndDisk(ntVars.OLD_VERSION_OVERLAY);
        CandyTweak.HIDE_STAMINA_BAR.setCacheAndDisk(ntVars.HIDE_STAMINA_BAR);

    }


    public static void loadVars() {

        CandyTweak.OLD_VERSION_OVERLAY.setCacheAndDisk(false);
        CandyTweak.HIDE_STAMINA_BAR.setCacheAndDisk(true);
    }

}
