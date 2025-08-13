package net.justmili.true_end.procedures.randomevents;

import net.justmili.true_end.TrueEndCommon;
import net.justmili.true_end.config.TEConfig;
import net.justmili.true_end.init.TEBlocks;
import net.justmili.true_end.init.TEDimKeys;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;

import static net.minecraft.world.level.block.Blocks.*;

public class SoundPlayer {
    public static void onPlayerTick(Player player) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;
        if (serverPlayer.level().dimension() != TEDimKeys.NWAD) if (serverPlayer.level().dimension() != Level.OVERWORLD) return;

        if (!TEConfig.randomEventsToggle) return;
        if (!(Math.random() < TEConfig.randomEventChance)) return;

        double x = serverPlayer.getX();
        double y = serverPlayer.getY();
        double z = serverPlayer.getZ();
        Level level = serverPlayer.level();

        //Sound Players
        if (groundBlock(level, x, y, z) == TEBlocks.GRASS_BLOCK.get() || groundBlock(level, x, y, z) == GRASS_BLOCK) {
            playSounds(serverPlayer, 8, SoundEvents.GRASS_BREAK);
        }
        if (groundBlock(level, x, y, z) == SAND) {
            playSounds(serverPlayer, 8, SoundEvents.SAND_STEP);
        }
        if (groundBlock(level, x, y, z) == TEBlocks.DIRT.get() || groundBlock(level, x, y, z) == DIRT) {
            playSounds(serverPlayer, 12, SoundEvents.GRAVEL_STEP);
        }
        if (groundBlock(level, x, y, z) == TEBlocks.STONE.get() || groundBlock(level, x, y, z) == STONE) {
            playSounds(serverPlayer, 10, SoundEvents.STONE_BREAK);
        }
        if (groundBlock(level, x, y, z) == DEEPSLATE) {
            playSounds(serverPlayer, 16, SoundEvents.DEEPSLATE_BREAK);
        }
    }

    public static Block groundBlock(Level level, double x, double y, double z) {
        return level.getBlockState(BlockPos.containing(x, y - 0.5, z)).getBlock();
    }

    private static void playSounds(ServerPlayer player, Integer delay, SoundEvent soundEvent) {
        int soundX = 16 + (int)(Math.random() * ((48 - 16) + 1))/4;
        int soundY = 1 + (int) (Math.random() * ((8 - 1) + 1));
        int soundZ = 16 + (int)(Math.random() * ((48 - 16) + 1))/4;
        LevelAccessor world = player.level();
        int randomRepeatCount = 3 + (int) (Math.random() * ((9 - 3) + 1));
        for (int index3 = 0; index3 < (randomRepeatCount - 1); index3++) {
            TrueEndCommon.queueServerWork(delay, () -> {
                if (world instanceof Level level) {
                    if (!level.isClientSide()) {
                        level.playSound(null, BlockPos.containing(soundX, soundY, soundZ), soundEvent, SoundSource.NEUTRAL, 1, 1);
                    }
                }
            });
        }
    }
}