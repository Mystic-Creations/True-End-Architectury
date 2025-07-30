package net.justmili.true_end.procedures.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import net.justmili.true_end.init.TrueEndBlocks;

public class GrassBlockLeftClick {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(TagKey.create(Registries.ITEM,new ResourceLocation("minecraft:hoes")))) {
            world.setBlock(BlockPos.containing(x, y, z), TrueEndBlocks.DIRT.get().defaultBlockState(), 3);
            if (world instanceof ServerLevel _level) {
                ItemEntity entityToSpawn = new ItemEntity(_level, x, (y + 1), z, new ItemStack(Items.WHEAT_SEEDS));
                entityToSpawn.setPickUpDelay(15);
                _level.addFreshEntity(entityToSpawn);
            }
            if (Math.random() < 0.05) {
                if (world instanceof ServerLevel _level) {
                    ItemEntity entityToSpawn = new ItemEntity(_level, x, (y + 1), z, new ItemStack(Items.WHEAT_SEEDS));
                    entityToSpawn.setPickUpDelay(15);
                    _level.addFreshEntity(entityToSpawn);
                }
            }
        }
    }
}