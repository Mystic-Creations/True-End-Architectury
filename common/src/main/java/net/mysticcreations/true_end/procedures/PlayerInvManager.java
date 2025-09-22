package net.mysticcreations.true_end.procedures;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.platform.Platform;
import net.mysticcreations.true_end.config.TEConfig;
import net.mysticcreations.true_end.init.TEItems;
import net.mysticcreations.true_end.variables.TEVariables;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.LevelResource;
import java.io.File;
import java.nio.file.Path;
import java.util.Random;

import static net.mysticcreations.true_end.TrueEndCommon.LOGGER;
import static net.mysticcreations.true_end.init.TEDimKeys.BTD;

public class PlayerInvManager {
    private static final Random RAND = new Random();
    private static final File saveDir = TEConfig.serializer.configFile.getParentFile();

    private static String makeBackupFilename(ServerPlayer player, String suffix) {
        Path worldFolder = player.getServer().getWorldPath(LevelResource.LEVEL_DATA_FILE).getParent();
        String folderName = worldFolder.getFileName().toString();
        String cleanName = folderName.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String uuid = player.getUUID().toString().replace("-", "");
        return uuid + "_" + cleanName + "_" + suffix + ".dat";
    }

    // BTD player inv management
    public static void saveInvBTD(ServerPlayer player) {
        if (!TEConfig.clearDreamItems) return;
        CompoundTag root = new CompoundTag();
        ListTag mainList = new ListTag();
        saveInventory(player, root, mainList);

        if (!saveDir.exists()) saveDir.mkdirs();
        File out = new File(saveDir, makeBackupFilename(player, "BTD"));
        try {
            NbtIo.writeCompressed(root, out);
        } catch (Exception e) {
            LOGGER.error("Failed to save BTD for player {}", player.getName().getString(), e);
        }
    }
    public static void restoreInvWithChance(ServerPlayer player) {
        if (!TEConfig.clearDreamItems) return;
        File in = new File(saveDir, makeBackupFilename(player, "BTD"));
        if (!in.exists()) return;

        restoreInventory(player, in, 0.90);
    }

    // NWAD player inv management
    public static void saveInvNWAD(ServerPlayer player) {
        if (!TEConfig.clearDreamItems) return;
        CompoundTag root = new CompoundTag();
        ListTag mainList = new ListTag();
        saveInventory(player, root, mainList);

        if (!saveDir.exists()) saveDir.mkdirs();
        File out = new File(saveDir, makeBackupFilename(player, "NWAD"));
        try {
            NbtIo.writeCompressed(root, out);
        } catch (Exception e) {
            LOGGER.error("Failed to save NWAD for player {}", player.getName().getString(), e);
        }
    }
    public static void restoreInv(ServerPlayer player) {
        if (!TEConfig.clearDreamItems) return;
        File in = new File(saveDir, makeBackupFilename(player, "NWAD"));
        if (!in.exists()) return;

        restoreInventory(player, in, 1.0);
    }

    //Util
    public static void saveInventory(ServerPlayer player, CompoundTag root, ListTag mainList) {
        for (int i = 0; i < player.getInventory().items.size(); i++) {
            ItemStack stack = player.getInventory().items.get(i);
            if (!stack.isEmpty()) {
                CompoundTag entry = new CompoundTag();
                entry.putInt("Slot", i);
                entry.put("Item", stack.save(new CompoundTag()));
                mainList.add(entry);
            }
        }
        root.put("Inventory", mainList);

        ListTag armorList = new ListTag();
        for (int i = 0; i < player.getInventory().armor.size(); i++) {
            ItemStack stack = player.getInventory().armor.get(i);
            if (!stack.isEmpty()) {
                CompoundTag entry = new CompoundTag();
                entry.putInt("Slot", i);
                entry.put("Item", stack.save(new CompoundTag()));
                armorList.add(entry);
            }
        }
        root.put("Armor", armorList);

        ListTag offList = new ListTag();
        for (int i = 0; i < player.getInventory().offhand.size(); i++) {
            ItemStack stack = player.getInventory().offhand.get(i);
            if (!stack.isEmpty()) {
                CompoundTag entry = new CompoundTag();
                entry.putInt("Slot", i);
                entry.put("Item", stack.save(new CompoundTag()));
                offList.add(entry);
            }
        }
        root.put("Offhand", offList);

        if (Platform.isForge()) {
            if (Platform.isModLoaded("curios")) {
                saveCuriosSlots(root, player);
            }
        }
    }

    @ExpectPlatform
    public static void saveCuriosSlots(CompoundTag root, Player player) {
        assert false : "this should never run btw";
    }

    public static void restoreInventory(ServerPlayer player, File in, Double chance) {
        try {
            CompoundTag root = NbtIo.readCompressed(in);

            ListTag mainList = root.getList("Inventory", Tag.TAG_COMPOUND);
            for (Tag t : mainList) {
                CompoundTag entry = (CompoundTag) t;
                int slot = entry.getInt("Slot");
                ItemStack stack = ItemStack.of(entry.getCompound("Item"));
                if (RAND.nextDouble() < chance) {
                    player.getInventory().setItem(slot, stack);
                } else {
                    player.getInventory().setItem(slot, ItemStack.EMPTY);
                }
            }

            ListTag armorList = root.getList("Armor", Tag.TAG_COMPOUND);
            for (Tag t : armorList) {
                CompoundTag entry = (CompoundTag) t;
                int slot = entry.getInt("Slot");
                ItemStack stack = ItemStack.of(entry.getCompound("Item"));
                if (RAND.nextDouble() < chance) {
                    player.getInventory().armor.set(slot, stack);
                } else {
                    player.getInventory().armor.set(slot, ItemStack.EMPTY);
                }
            }

            ListTag offList = root.getList("Offhand", Tag.TAG_COMPOUND);
            for (Tag t : offList) {
                CompoundTag entry = (CompoundTag) t;
                int slot = entry.getInt("Slot");
                ItemStack stack = ItemStack.of(entry.getCompound("Item"));
                if (RAND.nextDouble() < chance) {
                    player.getInventory().offhand.set(slot, stack);
                } else {
                    player.getInventory().offhand.set(slot, ItemStack.EMPTY);
                }
            }
            if (Platform.isForge()) {
                restoreCuriosSlots(player, root);
            }
            in.delete();
        } catch (Exception e) {
            LOGGER.error("Failed to restore inventory for player {}", player.getName().getString(), e);
        }
    }

    @ExpectPlatform
    public static void restoreCuriosSlots(ServerPlayer player, CompoundTag root) {
        assert false : "this should never run btw";
    }

    @ExpectPlatform
    public static void clearCuriosSlots(ServerPlayer player) {
        assert false : "this should never run btw";
    }

    //BTD -> Overworld Inv Restore
    public static void onDimensionChange(ServerPlayer player, ResourceKey<Level> fromDimension, ResourceKey<Level> toDimension) {
        if (!(fromDimension.equals(BTD) && toDimension.equals(Level.OVERWORLD))) return;
        if (!TEConfig.clearDreamItems) return;

        if (TEVariables.getPlayerData(player).getBeenBeyond()) {
            player.getInventory().clearContent();
            clearCuriosSlots(player);
            restoreInvWithChance(player);

            ItemStack cube = new ItemStack(TEItems.MYSTERIOUS_CUBE.get());
            cube.setCount(1);
            boolean added = player.getInventory().add(cube);
            if (!added) {
                player.drop(cube, false);
            }
        }
    }
}