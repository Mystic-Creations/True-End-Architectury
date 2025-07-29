package net.justmili.true_end.variables;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.saveddata.SavedData;

public class WorldData extends SavedData {
    public static final String DATA_NAME = "true_end_mapvars";

    private double btdSpawnX = 0.0;
    private double btdSpawnY = 0.0;
    private double btdSpawnZ = 0.0;
    private boolean unknownInWorld = false;
    private int btdConversationDelay = 0;
    private double randomEventChance = 0.0;
    private boolean randomEventsToggle = false;
    private boolean flashingLights = false;
    private boolean daytimeChangeToggle = false;
    private boolean clearDreamItems = false;

    public boolean isUnknownInWorld() { return unknownInWorld; }
    public double getBtdSpawnX() { return btdSpawnX; }
    public double getBtdSpawnY() { return btdSpawnY; }
    public double getBtdSpawnZ() { return btdSpawnZ; }

    public void setUnknownInWorld(boolean v) { unknownInWorld = v ;setDirty(); }
    public void setBtdSpawn(double x, double y, double z) { btdSpawnX = x; btdSpawnY = y; btdSpawnZ = z; setDirty(); }

    public static WorldData load(CompoundTag nbt) {
        WorldData m = new WorldData();
        m.btdSpawnX = nbt.getDouble("btdSpawnX");
        m.btdSpawnY = nbt.getDouble("btdSpawnY");
        m.btdSpawnZ = nbt.getDouble("btdSpawnZ");
        m.unknownInWorld = nbt.getBoolean("unknownInWorld");
        m.btdConversationDelay = nbt.getInt("btdConversationDelay");
        m.randomEventChance = nbt.getDouble("randomEventChance");
        m.randomEventsToggle = nbt.getBoolean("randomEventsToggle");
        m.flashingLights = nbt.getBoolean("flashingLights");
        m.daytimeChangeToggle = nbt.getBoolean("daytimeChangeToggle");
        m.clearDreamItems = nbt.getBoolean("clearDreamItems");
        return m;
    }

    @Override
    public CompoundTag save(CompoundTag nbt) {
        nbt.putDouble("btdSpawnX", btdSpawnX);
        nbt.putDouble("btdSpawnY", btdSpawnY);
        nbt.putDouble("btdSpawnZ", btdSpawnZ);
        nbt.putBoolean("unknownInWorld", unknownInWorld);
        nbt.putInt("btdConversationDelay", btdConversationDelay);
        nbt.putDouble("randomEventChance", randomEventChance);
        nbt.putBoolean("randomEventsToggle", randomEventsToggle);
        nbt.putBoolean("flashingLights", flashingLights);
        nbt.putBoolean("daytimeChangeToggle", daytimeChangeToggle);
        nbt.putBoolean("clearDreamItems", clearDreamItems);
        return nbt;
    }

    public static WorldData get(LevelAccessor world) {
        if (!(world instanceof ServerLevel lvl)) return new WorldData();
        ServerLevel overworld = lvl.getServer().getLevel(Level.OVERWORLD);
        if (overworld == null) {
            return new WorldData();
        }
        return overworld.getDataStorage().computeIfAbsent(WorldData::load, WorldData::new, DATA_NAME);
    }
}