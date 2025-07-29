package net.justmili.true_end.block;


import net.justmili.true_end.init.TrueEndBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class Dirt extends Block {
    public Dirt() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).sound(SoundType.GRAVEL).strength(0.5f).randomTicks());
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 15;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!canBeGrass(state, world, pos)) {
            // Keep as dirt
        } else {
            if (!world.isClientSide()) {
                world.setBlock(pos, TrueEndBlocks.GRASS_BLOCK.get().defaultBlockState(), 3);
            }
        }
    }

    @Override
    public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        super.use(blockstate, world, pos, entity, hand, hit);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if (entity.getMainHandItem().is(ItemTags.HOES)) {
            world.setBlock(BlockPos.containing(x, y, z), TrueEndBlocks.FARMLAND.get().defaultBlockState(), 3);
            if (world.isClientSide()) {
                world.playLocalSound(x, y, z, SoundEvent.createVariableRangeEvent(new ResourceLocation("item.hoe.till")), SoundSource.BLOCKS, 1, 1, false);
            } else {
                world.playLocalSound(x, y, z, SoundEvent.createVariableRangeEvent(new ResourceLocation("item.hoe.till")), SoundSource.BLOCKS, 1, 1, false);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

private static boolean canBeGrass(BlockState state, LevelReader world, BlockPos pos) {
    BlockPos abovePos = pos.above();
    BlockState aboveState = world.getBlockState(abovePos);

    // Check if there's a block above, if so, don't turn into grass.
    if (!aboveState.isAir()) {
        return false;
    }

    // Check for adjacent grass blocks in a 3x3x3 area
    for (int x = -1; x <= 1; x++) {
        for (int y = -1; y <= 1; y++) { // Added y loop for vertical check
            for (int z = -1; z <= 1; z++) {
                if (x == 0 && y == 0 && z == 0) { // Skip the current block
                    continue;
                }
                BlockPos neighborPos = pos.offset(x, y, z);
                BlockState neighborState = world.getBlockState(neighborPos);

                if (neighborState.is(TrueEndBlocks.GRASS_BLOCK.get())) {
                    return true;
                }
            }
        }
    }
    return false;
	}
}