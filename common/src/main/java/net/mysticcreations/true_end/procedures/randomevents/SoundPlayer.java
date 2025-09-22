package net.mysticcreations.true_end.procedures.randomevents;

import net.mysticcreations.true_end.TrueEndCommon;
import net.mysticcreations.true_end.config.TEConfig;
import net.mysticcreations.true_end.init.TEBlocks;
import net.mysticcreations.true_end.init.TEDimKeys;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import static net.mysticcreations.true_end.init.TEDimKeys.BTD;
import static net.minecraft.world.level.block.Blocks.*;

public class SoundPlayer {
    public static void onPlayerTick(Player player) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;
        if (serverPlayer.level().dimension() != TEDimKeys.NWAD)
            if (serverPlayer.level().dimension() != Level.OVERWORLD) return;

        if (!TEConfig.randomEventsToggle) return;
        if (!(Math.random() < TEConfig.randomEventChance)) return;

        double x = serverPlayer.getX();
        double y = serverPlayer.getY();
        double z = serverPlayer.getZ();
        Level level = serverPlayer.level();

        //Sound Players
        if (groundBlock(level, x, y, z) == TEBlocks.GRASS_BLOCK.get() || groundBlock(level, x, y, z) == GRASS_BLOCK) {
            if (Math.random() < 0.90) {
                repeatSound(serverPlayer, 8, SoundEvents.GRASS_STEP);
            } else {
                repeatSound(serverPlayer, 8, SoundEvents.GRASS_BREAK);
            }
        }
        if (groundBlock(level, x, y, z) == SAND) {
            if (Math.random() < 0.90) {
                repeatSound(serverPlayer, 8, SoundEvents.SAND_STEP);
            } else {
                repeatSound(serverPlayer, 8, SoundEvents.SAND_BREAK);
            }
        }
        if (groundBlock(level, x, y, z) == TEBlocks.DIRT.get()
                || groundBlock(level, x, y, z) == TEBlocks.GRAVEL.get()
                || groundBlock(level, x, y, z) == DIRT
                || groundBlock(level, x, y, z) == GRAVEL) {
            if (Math.random() < 0.90) {
                repeatSound(serverPlayer, 8, SoundEvents.GRAVEL_STEP);
            } else {
                repeatSound(serverPlayer, 12, SoundEvents.GRAVEL_BREAK);
            }
        }
        if (groundBlock(level, x, y, z) == TEBlocks.STONE.get() || groundBlock(level, x, y, z) == STONE) {
            if (Math.random() < 0.40) {
                repeatSound(serverPlayer, 8, SoundEvents.STONE_STEP);
            } else {
                repeatSound(serverPlayer, 10, SoundEvents.STONE_BREAK);
            }
        }
        if (groundBlock(level, x, y, z) == DEEPSLATE) {
            if (Math.random() < 0.60) {
                repeatSound(serverPlayer, 8, SoundEvents.DEEPSLATE_STEP);
            } else {
                repeatSound(serverPlayer, 16, SoundEvents.DEEPSLATE_BREAK);
            }
        }
        if ((serverPlayer.getY() < 0 || player.level().dimension() == BTD) && Math.random() < 0.00005) {
            playSound(serverPlayer, 5, SoundEvent.createVariableRangeEvent(new ResourceLocation("true_end:daisy_bell")));
        }
    }

    public static Block groundBlock(Level level, double x, double y, double z) {
        return level.getBlockState(BlockPos.containing(x, y - 0.5, z)).getBlock();
    }

    private static void repeatSound(ServerPlayer player, Integer delay, SoundEvent soundEvent) {
        int randomRepeatCount = 3 + (int) (Math.random() * ((9 - 3) + 1));
        int soundX = 16 + (int) (Math.random() * ((48 - 16) + 1)) / 4;
        int soundY = 1 + (int) (Math.random() * ((8 - 1) + 1));
        int soundZ = 16 + (int) (Math.random() * ((48 - 16) + 1)) / 4;
        Level level = player.level();
        if (level.isClientSide()) return;

        for (int index3 = 0; index3 < (randomRepeatCount - 1); index3++) {
            TrueEndCommon.queueServerWork(delay, () -> {
                level.playSound(null,
                        BlockPos.containing(soundX, soundY, soundZ),
                        soundEvent,
                        SoundSource.NEUTRAL, 1, 1);
            });
        }
    }

    public static void playSound(ServerPlayer player, Integer delay, SoundEvent soundEvent) {
        int soundX = 16 + (int) (Math.random() * ((48 - 16) + 1)) / 4;
        int soundY = 1 + (int) (Math.random() * ((8 - 1) + 1));
        int soundZ = 16 + (int) (Math.random() * ((48 - 16) + 1)) / 4;
        Level level = player.level();
        if (level.isClientSide()) return;

        TrueEndCommon.queueServerWork(delay, () -> {
            level.playSound(
                    null,
                    BlockPos.containing(soundX, soundY, soundZ),
                    soundEvent,
                    SoundSource.NEUTRAL, 1, 1);
        });
    }
}