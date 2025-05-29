package net.justmili.true_end.common.procedures.block;

import net.justmili.true_end.common.TrueEndCommon;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.minecraft.util.RandomSource;

public class RedstoneOreInteraction {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        BlockPos blockPos = BlockPos.containing(x, y, z);
        BlockState blockState = world.getBlockState(blockPos);

        // Set the block to lit
        if (blockState.getBlock().getStateDefinition().getProperty("lit") instanceof BooleanProperty _booleanProp)
            world.setBlock(blockPos, blockState.setValue(_booleanProp, true), 3);

        // Generate a random delay (between 1365 ticks +/- 10% to simulate randomness)
        int randomDelay = 1365 + RandomSource.create().nextInt(273);  // 1365 ticks +/- 10% (136.5 ticks)

        // Schedule the task after the random delay
        TrueEndCommon.queueServerWork(randomDelay, () -> {
            // Set the block back to unlit after the random delay
            BlockState state = world.getBlockState(blockPos);
            if (state.getBlock().getStateDefinition().getProperty("lit") instanceof BooleanProperty _booleanProp2)
                world.setBlock(blockPos, state.setValue(_booleanProp2, false), 3);
        });
    }
}