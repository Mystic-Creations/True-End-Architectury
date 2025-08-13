
package net.justmili.true_end.item;

import net.justmili.true_end.init.TESounds;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class MusicDiskNeverAlone extends RecordItem {
    public MusicDiskNeverAlone() {
        super(8, TESounds.MUSIC_NEVER_ALONE.get(), new Properties().stacksTo(1).rarity(Rarity.RARE), 5180);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
    }
}
