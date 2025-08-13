
package net.justmili.true_end.item;

import net.justmili.true_end.init.TESounds;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;

import java.util.List;

public class MusicDiskFarlands extends RecordItem {
    public MusicDiskFarlands() {
        super(8, TESounds.MUSIC_FARLANDS.get(), new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 2540);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
    }
}
