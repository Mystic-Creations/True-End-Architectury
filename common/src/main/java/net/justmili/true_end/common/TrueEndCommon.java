package net.justmili.true_end.common;

import dev.architectury.event.events.common.TickEvent;
import net.justmili.true_end.common.init.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public final class TrueEndCommon {
    public static final Logger LOGGER = LoggerFactory.getLogger(TrueEndCommon.class);
    public static final String MOD_ID = "true_end";

    public static void init() {
        // Write common init code here.

        TickEvent.SERVER_POST.register(server -> processQueue());

        TrueEndBlocks.register();
        TrueEndItems.register();
        TrueEndCreativeTab.register();
        TrueEndParticleTypes.register();
        TrueEndPoiTypes.register();
    }

    private static final Queue<Map.Entry<Runnable, Integer>> workQueue = new LinkedList<>();

    public static void queueServerWork(int tickDelay, Runnable action) {
        workQueue.add(new AbstractMap.SimpleEntry<>(action, tickDelay));
    }

    private static void processQueue() {
        Iterator<Map.Entry<Runnable, Integer>> iterator = workQueue.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Runnable, Integer> entry = iterator.next();
            int newTick = entry.getValue() - 1;
            if (newTick <= 0) {
                entry.getKey().run();
                iterator.remove();
            } else {
                entry.setValue(newTick);
            }
        }
    }

}
