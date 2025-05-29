package net.justmili.true_end.common.variables;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class LevelData extends SavedData {
    private int myVariable = 0;

    public static final String ID = "true_end:map_vars";

    private boolean defaultKeepInv = false;
    private double btdSpawnX = 0.0;
    private double btdSpawnY = 0.0;
    private double btdSpawnZ = 0.0;

    public static LevelData create() {
        return new LevelData();
    }

    public static LevelData load(CompoundTag tag) {
        LevelData data = new LevelData();
        data.btdSpawnX = tag.getDouble("btdSpawnX");
        data.btdSpawnY = tag.getDouble("btdSpawnY");
        data.btdSpawnZ = tag.getDouble("btdSpawnZ");
        data.defaultKeepInv = tag.getBoolean("defaultKeepInv");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putDouble("btdSpawnX", btdSpawnX);
        tag.putDouble("btdSpawnY", btdSpawnY);
        tag.putDouble("btdSpawnZ", btdSpawnZ);
        tag.putBoolean("defaultKeepInv", defaultKeepInv);
        return tag;
    }

    public int getVariable() {
        return myVariable;
    }
    public void setVariable(int variable) {
        myVariable = variable;
        setDirty();
    }
    public double getBtdSpawnX() {
        return btdSpawnX;
    }
    public void setBtdSpawnX(double btdSpawnX) {
        this.btdSpawnX = btdSpawnX;
        setDirty();
    }
    public double getBtdSpawnY() {
        return btdSpawnY;
    }
    public void setBtdSpawnY(double btdSpawnY) {
        this.btdSpawnY = btdSpawnY;
        setDirty();
    }
    public double getBtdSpawnZ() {
        return btdSpawnZ;
    }
    public void setBtdSpawnZ(double btdSpawnZ) {
        this.btdSpawnZ = btdSpawnZ;
        setDirty();
    }
}
