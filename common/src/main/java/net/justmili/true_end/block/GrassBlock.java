package net.justmili.true_end.block;

import net.justmili.true_end.init.TEBlocks;
import net.justmili.true_end.procedures.block.GrassBlockLeftClick;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

import net.minecraft.world.phys.BlockHitResult;

public class GrassBlock extends Block {
    public GrassBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.GRASS).strength(0.6f).randomTicks());
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 15;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!canStayGrass(world, pos)) {
            if (!world.isClientSide()) {
                world.setBlock(pos, TEBlocks.DIRT.get().defaultBlockState(), 3);
            }
        }
    }

    private static boolean canStayGrass(LevelReader world, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = world.getBlockState(abovePos);
        if (aboveState.is(TEBlocks.LEAVES.get())) {
            return true;
        }
        return aboveState.getLightBlock(world, abovePos) <= 0;
    }
    
	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
        if (entity.getMainHandItem().is(ItemTags.HOES)) {
            world.setBlock(BlockPos.containing(x, y, z), TEBlocks.FARMLAND.get().defaultBlockState(), 3);
            if (world.isClientSide()) {
                world.playLocalSound(x, y, z, SoundEvent.createVariableRangeEvent(new ResourceLocation("item.hoe.till")), SoundSource.BLOCKS, 1, 1, false);
            } else {
                world.playLocalSound(x, y, z, SoundEvent.createVariableRangeEvent(new ResourceLocation("item.hoe.till")), SoundSource.BLOCKS, 1, 1, false);
            }
            return InteractionResult.SUCCESS;
        }
		return InteractionResult.FAIL;
	}

	@Override
	public void attack(BlockState blockstate, Level world, BlockPos pos, Player entity) {
		super.attack(blockstate, world, pos, entity);
		GrassBlockLeftClick.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
	}
}