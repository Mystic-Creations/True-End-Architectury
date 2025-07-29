package net.justmili.true_end.world.teleporter;

import net.justmili.true_end.init.TrueEndPoiTypes;
import net.minecraft.world.level.portal.PortalForcer;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.BlockUtil;

import java.util.Optional;
import java.util.Comparator;

public class BeyondTheDreamTeleporter extends PortalForcer { // Architectury-compatible

    private final ServerLevel level;
    private final BlockPos entityEnterPos;

    public BeyondTheDreamTeleporter(ServerLevel worldServer, BlockPos entityEnterPos) {
        super(worldServer);
        this.level = worldServer;
        this.entityEnterPos = entityEnterPos;
    }

    public Optional<BlockUtil.FoundRectangle> findPortalAround(BlockPos pos, boolean isNear, WorldBorder border) {
        PoiManager poimanager = this.level.getPoiManager();
        int radius = isNear ? 16 : 128;
        poimanager.ensureLoadedAndValid(this.level, pos, radius);
        Optional<PoiRecord> portal = poimanager.getInSquare(
                        record -> record.is(TrueEndPoiTypes.BEYOND_THE_DREAM_PORTAL.getKey()),
                        pos, radius, PoiManager.Occupancy.ANY
                ).filter(record -> border.isWithinBounds(record.getPos()))
                .sorted(Comparator.comparingDouble(r -> r.getPos().distSqr(pos)))
                .filter(record -> level.getBlockState(record.getPos()).hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                .findFirst();

        return portal.map(poi -> {
            BlockPos p = poi.getPos();
            BlockState state = level.getBlockState(p);
            return BlockUtil.getLargestRectangleAround(p, state.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21,
                    bp -> level.getBlockState(bp).equals(state));
        });
    }

    // createPortal, canHostFrame, canPortalReplaceBlock, and placeEntity remain mostly the same
    // (copy those from your original and adjust references to Forge utilities)

    public PortalInfo getPortalInfo(Entity entity, ServerLevel server) {
        WorldBorder worldBorder = server.getWorldBorder();
        double scale = entity.level().dimensionType().coordinateScale();
        BlockPos dest = worldBorder.clampToBounds(entity.getX() * scale, entity.getY(), entity.getZ() * scale);

        return getExitPortal(entity, dest, worldBorder).map(rectangle -> {
            BlockState portalState = entity.level().getBlockState(entityEnterPos);
            Direction.Axis axis = portalState.getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
            Vec3 offset = BeyondTheDreamPortalShape.getRelativePosition(rectangle, axis, entity.position(), entity.getDimensions(entity.getPose()));
            return BeyondTheDreamPortalShape.createPortalInfo(server, rectangle, axis, offset, entity, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot());
        }).orElse(new PortalInfo(entity.position(), Vec3.ZERO, entity.getYRot(), entity.getXRot()));
    }

    protected Optional<BlockUtil.FoundRectangle> getExitPortal(Entity entity, BlockPos pos, WorldBorder worldBorder) {
        Optional<BlockUtil.FoundRectangle> existing = this.findPortalAround(pos, false, worldBorder);
        if (entity instanceof ServerPlayer && existing.isEmpty()) {
            Direction.Axis axis = entity.level().getBlockState(entityEnterPos).getOptionalValue(NetherPortalBlock.AXIS).orElse(Direction.Axis.X);
            return this.createPortal(pos, axis);
        }
        return existing;
    }

    private boolean canPortalReplaceBlock(BlockPos.MutableBlockPos pos) {
        BlockState state = this.level.getBlockState(pos);
        return state.canBeReplaced() && state.getFluidState().isEmpty();
    }
}
