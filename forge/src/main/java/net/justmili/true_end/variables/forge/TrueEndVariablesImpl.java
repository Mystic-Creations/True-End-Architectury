package net.justmili.true_end.variables.forge;

import net.justmili.true_end.variables.WorldData;
import net.justmili.true_end.variables.PlayerData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.atomic.AtomicReference;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TrueEndVariablesImpl {

    public static PlayerData getPlayerData(ServerPlayer player) {

        AtomicReference<PlayerData> playerData = new AtomicReference<>();
        player.getCapability(PLAYER_VARS_CAP).ifPresent(
                playerData::set
        );
        return playerData.get();
    }

    public static WorldData getLevelData(Level level) {
        return WorldData.get(level);
    }

    public static final Capability<PlayerData> PLAYER_VARS_CAP =
            CapabilityManager.get(new CapabilityToken<>() {});

    public static final Capability<WorldData> MAP_VARIABLES_CAP =
            CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public static void onRegisterCaps(RegisterCapabilitiesEvent evt) {

        evt.register(PlayerData.class);
        evt.register(WorldData.class);
    }



    @Mod.EventBusSubscriber
    public static class PlayerVariablesProvider implements ICapabilitySerializable<CompoundTag> {
        private PlayerData vars = new PlayerData();
        private final LazyOptional<PlayerData> opt = LazyOptional.of(() -> vars);

        @SubscribeEvent
        public static void attachCaps(AttachCapabilitiesEvent<Entity> evt) {
            if (evt.getObject() instanceof Player && evt.getObject() instanceof ServerPlayer) {
                evt.addCapability(
                        ResourceLocation.parse("true_end:player_variables"),
                        new PlayerVariablesProvider()
                );
            }
        }

        @SubscribeEvent
        public static void cloneOnDeath(PlayerEvent.Clone evt) {
            if (!evt.isWasDeath()) return;
            evt.getOriginal().reviveCaps();
            evt.getOriginal().getCapability(PLAYER_VARS_CAP).ifPresent(oldV ->
                    evt.getEntity().getCapability(PLAYER_VARS_CAP).ifPresent(newV ->
                            newV.setBeenBeyond(oldV.getBeenBeyond())
                    )
            );
        }

        @SubscribeEvent
        public static void syncOnLogin(PlayerEvent.PlayerLoggedInEvent evt) {
            //evt.getEntity().getCapability(PLAYER_VARS_CAP).ifPresent(v -> v.sync((ServerPlayer)evt.getEntity()));
        }

        @SubscribeEvent
        public static void syncOnRespawn(PlayerEvent.PlayerRespawnEvent evt) {
            //evt.getEntity().getCapability(PLAYER_VARS_CAP).ifPresent(v -> v.sync((ServerPlayer)evt.getEntity()));
        }

        @SubscribeEvent
        public static void syncOnDimChange(PlayerEvent.PlayerChangedDimensionEvent evt) {
            //evt.getEntity().getCapability(PLAYER_VARS_CAP).ifPresent(v -> v.sync((ServerPlayer)evt.getEntity()));
        }

        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, net.minecraft.core.Direction side) {
            return cap == PLAYER_VARS_CAP ? opt.cast() : LazyOptional.empty();
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag n = new CompoundTag();
            vars.save(n);
            return n;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            vars = PlayerData.load(nbt);
        }
    }
}
