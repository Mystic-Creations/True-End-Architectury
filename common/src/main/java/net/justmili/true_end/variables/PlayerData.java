package net.justmili.true_end.variables;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class PlayerData extends SavedData {

    public static final String ID = "true_end:player_vars";

    private boolean beenBeyond = false;
    private int seepingRealityTime = 0;

    public static WorldData create() {
        return new WorldData();
    }

    public static PlayerData load(CompoundTag tag) {
        PlayerData data = new PlayerData();
        data.beenBeyond = tag.getBoolean("beenBeyond");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putBoolean("beenBeyond", beenBeyond);
        return tag;
    }

    public boolean getBeenBeyond() {
        return beenBeyond;
    }

    public void setBeenBeyond(boolean beenBeyond) {
        this.beenBeyond = beenBeyond;
        setDirty();
    }

    public int getSeepingRealityTime() {
        return seepingRealityTime;
    }

    public void setSeepingRealityTime(int seepingRealityTime) {
        this.seepingRealityTime = seepingRealityTime;
        setDirty();
    }


}
