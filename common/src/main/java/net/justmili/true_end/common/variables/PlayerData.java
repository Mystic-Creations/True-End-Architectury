package net.justmili.true_end.common.variables;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.saveddata.SavedData;

public class PlayerData extends SavedData {

    public static final String ID = "true_end:player_vars";

    private boolean beenBeyond = false;

    public static LevelData create() {
        return new LevelData();
    }

    public PlayerData load() {
        CompoundTag tag = new CompoundTag();
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


}
