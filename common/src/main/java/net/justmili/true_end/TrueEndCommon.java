package net.justmili.true_end;

import dev.architectury.event.events.common.*;
import net.justmili.true_end.config.TrueEndConfig;
import net.justmili.true_end.init.*;
import net.justmili.true_end.procedures.advancements.NotAlone;
import net.justmili.true_end.procedures.advancements.OnARailTracker;
import net.justmili.true_end.procedures.advancements.WhenPigsFly;
import net.justmili.true_end.procedures.alphafeatures.AlphaFoodSystem;
import net.justmili.true_end.procedures.alphafeatures.NoCooldown;
import net.justmili.true_end.procedures.alphafeatures.NoSprint;
import net.justmili.true_end.procedures.alphafeatures.WoolDrop;
import net.justmili.true_end.procedures.events.ChatReplies;
import net.justmili.true_end.procedures.events.FoodLvlReset;
import net.justmili.true_end.procedures.events.NoBtdEscape;
import net.justmili.true_end.procedures.events.NoVoidDamage;
import net.justmili.true_end.procedures.randomevents.MobStare;
import net.justmili.true_end.procedures.randomevents.SoundPlayer;
import net.justmili.true_end.procedures.randomevents.TimeChange;
import net.justmili.true_end.procedures.randomevents.UnknownSpawning;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public final class TrueEndCommon {
    public static final Logger LOGGER = LoggerFactory.getLogger(TrueEndCommon.class);
    public static final String MOD_ID = "true_end";

    public static void init() {
        // Write common init code here.

        TickEvent.SERVER_POST.register(server -> processQueue());

        TrueEndConfig.setup();

        TrueEndSounds.register();
        TrueEndBlocks.register();
        TrueEndItems.register();
        TrueEndCreativeTab.register();
        TrueEndParticleTypes.register();
        TrueEndPoiTypes.register();
        TrueEndEntities.register();

        registerEvents();

    }

    public static void registerEvents() {

        TickEvent.SERVER_LEVEL_POST.register(MobStare::onWorldTick);
        TickEvent.SERVER_LEVEL_POST.register(UnknownSpawning::onWorldTick);
        TickEvent.PLAYER_POST.register(TimeChange::onPlayerTick);
        TickEvent.PLAYER_POST.register(SoundPlayer::onPlayerTick);
        TickEvent.PLAYER_POST.register(NoSprint::onPlayerTick);
        TickEvent.PLAYER_POST.register(NotAlone::onPlayerTick);
        TickEvent.PLAYER_POST.register(OnARailTracker::onPlayerTick);

        ChatEvent.RECEIVED.register(ChatReplies::onChat);

        PlayerEvent.CHANGE_DIMENSION.register(FoodLvlReset::onChangeDimension);
        PlayerEvent.CHANGE_DIMENSION.register(NoCooldown::onPlayerChangedDimension);
        PlayerEvent.PLAYER_RESPAWN.register(NoBtdEscape::onPlayerRespawn);
        PlayerEvent.PLAYER_RESPAWN.register(NoCooldown::onPlayerRespawn);

        EntityEvent.LIVING_DEATH.register(NoBtdEscape::onPlayerDeath);
        EntityEvent.LIVING_DEATH.register(WhenPigsFly::onPigFallDeath);
        EntityEvent.LIVING_HURT.register(NoVoidDamage::onEntityAttacked);
        EntityEvent.LIVING_HURT.register(WoolDrop::onEntityAttacked);

        InteractionEvent.RIGHT_CLICK_ITEM.register(AlphaFoodSystem::onRightClickItem);
        InteractionEvent.RIGHT_CLICK_BLOCK.register(AlphaFoodSystem::onRightClickBlock);
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
