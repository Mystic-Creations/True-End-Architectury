package net.justmili.true_end.procedures.randomevents;

import java.util.List;

import net.justmili.true_end.TrueEndCommon;
import net.justmili.true_end.config.TrueEndConfig;
import net.justmili.true_end.entity.Unknown;
import net.justmili.true_end.init.TrueEndEntities;
import net.justmili.true_end.variables.TrueEndVariables;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

public class UnknownSpawning {
    private static final long TICK_INTERVAL = 1200L;
    private static final int MAX_ATTEMPTS = 16;

    public static void onWorldTick(ServerLevel world) {

        if (world.getGameTime() % TICK_INTERVAL != 0) return;
        if (world.dimension() == Level.END) return;
        if (!TrueEndConfig.randomEventsToggle) return;

        double chanceMultiplier = 0.0;
        Difficulty difficulty = world.getDifficulty();
        if (difficulty == Difficulty.PEACEFUL) {
            chanceMultiplier = 3.0; // :)
        } else if (difficulty == Difficulty.EASY) {
            chanceMultiplier = 0.5;
        } else if (difficulty == Difficulty.NORMAL) {
            chanceMultiplier = 1.0;
        } else if (difficulty == Difficulty.HARD) {
            chanceMultiplier = 2.0;
        }

        if (!(Math.random() < (TrueEndConfig.entitySpawnChance * chanceMultiplier))) return;

        List<ServerPlayer> players = world.players();
        if (players.isEmpty()) return;
        ServerPlayer player = players.get(world.random.nextInt(players.size()));
        double maxDistance = (world.getServer().getPlayerList().getViewDistance() * 16.0) - 48.0;

        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            double angle = world.random.nextDouble() * Math.PI * 2.0;
            double dist = 32.0 + world.random.nextDouble() * (maxDistance - 32.0);
            double px = player.getX(), pz = player.getZ();
            int x = Mth.floor(px + Math.cos(angle) * dist);
            int z = Mth.floor(pz + Math.sin(angle) * dist);
            int surfaceY = world.getHeight(Heightmap.Types.MOTION_BLOCKING, x, z);

            BlockPos groundPos = new BlockPos(x, surfaceY - 1, z);
            BlockState groundState = world.getBlockState(groundPos);
            if (groundState.isAir() || groundState.getBlock() == Blocks.WATER || groundState.getBlock() == Blocks.LAVA) {
                TrueEndCommon.LOGGER.debug("Attempt {}: invalid ground at {}: {}", attempt, groundPos, groundState.getBlock());
                continue;
            }

            EntityType<Unknown> type = TrueEndEntities.UNKNOWN.get();
            Unknown entity = type.create(world);

            if (entity == null) return;
            entity.moveTo(x + 0.5, surfaceY, z + 0.5, world.random.nextFloat() * 360.0F, 0.0F);
            entity.setPersistenceRequired();
            world.addFreshEntity(entity);

            TrueEndVariables.getLevelData(world).setUnknownInWorld(true);
            TrueEndCommon.LOGGER.info("Spawned 'Unknown' at {} on {} after {} attempts.", entity.blockPosition(), groundState, attempt + 1);
            return;
        }
    }
}