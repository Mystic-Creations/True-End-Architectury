package net.justmili.true_end.common.mixin;

import net.justmili.true_end.common.init.TrueEndBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(EatBlockGoal.class)
public class SheepEatTrueEndGrassMixin {

    @Shadow @Final private Mob mob;

    @Shadow @Final private static Predicate<BlockState> IS_TALL_GRASS;

    @Shadow @Final private Level level;

    @Shadow private int eatAnimationTick;

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    public void canUse(CallbackInfoReturnable<Boolean> cir) {
        if (this.mob.getRandom().nextInt(this.mob.isBaby() ? 50 : 1000) != 0) {
            cir.setReturnValue(false);
        } else {
            BlockPos blockpos = this.mob.blockPosition();
            cir.setReturnValue(IS_TALL_GRASS.test(this.level.getBlockState(blockpos)) || this.level.getBlockState(blockpos.below()).is(TrueEndBlocks.GRASS_BLOCK.get()));
        }
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void tick(CallbackInfo ci) {
        this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        if (this.eatAnimationTick == 2) {
            BlockPos blockPos = this.mob.blockPosition();
            if (IS_TALL_GRASS.test(this.level.getBlockState(blockPos))) {
                if (this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                    this.level.destroyBlock(blockPos, false);
                }

                this.mob.ate();
            } else {
                BlockPos blockPos2 = blockPos.below();
                if (this.level.getBlockState(blockPos2).is(Blocks.GRASS_BLOCK)) {
                    if (this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                        this.level.levelEvent(2001, blockPos2, Block.getId(Blocks.GRASS_BLOCK.defaultBlockState()));
                        this.level.setBlock(blockPos2, Blocks.DIRT.defaultBlockState(), 2);
                    }

                    this.mob.ate();
                }
                if (this.level.getBlockState(blockPos2).is(TrueEndBlocks.GRASS_BLOCK.get())) {
                    if (this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                        this.level.levelEvent(2001, blockPos2, Block.getId(TrueEndBlocks.GRASS_BLOCK.get().defaultBlockState()));
                        this.level.setBlock(blockPos2, Blocks.DIRT.defaultBlockState(), 2);
                    }

                    this.mob.ate();
                }
            }

        }
        ci.cancel();
    }
}
