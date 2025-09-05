package net.justmili.true_end.client;

import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.true_end.client.renderer.BlackOverlayRenderer;
import net.justmili.true_end.client.renderer.FunnyScreenRenderer;
import net.justmili.true_end.client.renderer.UnknownEntityRenderer;
import net.justmili.true_end.init.*;
import net.justmili.true_end.procedures.advancements.OpenInventoryAdvancement;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public final class TrueEndCommonClient {

    public static void init() {

        EntityRendererRegistry.register(
                TEEntities.UNKNOWN,
                UnknownEntityRenderer.UnknownRenderer::new
        );

        ItemPropertiesRegistry.register(
                TEBlocks.VOID.get().asItem(),
                new ResourceLocation("true_end:type"),
                (stack, world, entity, seed) -> {
                    CompoundTag bst = stack.getTagElement("BlockStateTag");
                    if (bst != null && "white".equals(bst.getString("type"))) {
                        return 1.0f;
                    }
                    return 0.0f;
                }
        );

        ItemPropertiesRegistry.register(
                TEItems.DREAMERS_COMPASS.get(), new ResourceLocation("angle"),
                (stack, world, entity, seed) -> {
                    if (world == null || entity == null) {
                        return 0F;
                    }
                    CompoundTag tag = stack.getOrCreateTag();

                    if (!tag.getBoolean("TargetTracked")) {
                        return 0F;
                    }

                    BlockPos targetPos = new BlockPos(
                            tag.getInt("TargetX"),
                            tag.getInt("TargetY"),
                            tag.getInt("TargetZ")
                    );
                    BlockPos playerPos = entity.blockPosition();
                    float playerYaw = entity.getYRot(); // in degrees

                    // Delta vector from player to target
                    double dx = targetPos.getX() - playerPos.getX();
                    double dz = targetPos.getZ() - playerPos.getZ();

                    // Angle to target in degrees (0 = east, 90 = south)
                    double targetAngle = Math.toDegrees(Math.atan2(dz, dx));
                    targetAngle = (targetAngle - 90.0) % 360.0; // Fix 90° counter-clockwise offset

                    // Normalize both angles
                    targetAngle = (targetAngle + 360.0) % 360.0;
                    playerYaw = (playerYaw + 360.0f) % 360.0f;

                    // Relative angle between where player is looking and where target is
                    double relative = (targetAngle - playerYaw + 360.0) % 360.0;

                    // Now convert to 0.0 - 1.0 float for the predicate
                    float angleValue = (float) (relative / 360.0);

                    return angleValue;
                }
        );

        MenuRegistry.registerScreenFactory(TEScreens.BLACK_SCREEN.get(), BlackOverlayRenderer::new);
        MenuRegistry.registerScreenFactory(TEScreens.FUNNY_SCREEN.get(), FunnyScreenRenderer::new);

        ClientTickEvent.CLIENT_POST.register(VersionOverlay::onClientTick);
        ClientTickEvent.CLIENT_POST.register(OpenInventoryAdvancement::onClientTick);

        TEPackets.registerClient();
    }
}
