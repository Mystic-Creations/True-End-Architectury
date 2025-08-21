package net.justmili.true_end.client.gui.forge;

import com.mojang.blaze3d.vertex.PoseStack;
import net.justmili.true_end.TrueEndCommon;
import net.justmili.true_end.init.TEDimKeys;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TrueEndCommon.MOD_ID, value = Dist.CLIENT)
public class VersionOverlay {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.level.dimension() != TEDimKeys.BTD) return;

        GuiGraphics gui = event.getGuiGraphics();
        PoseStack pose = gui.pose();

        final int fontSize = 32;
        float guiScaleFactor = (float) mc.getWindow().getScreenWidth() / (float) mc.getWindow().getGuiScaledWidth();
        float baseFontHeight = (float) mc.font.lineHeight;
        float userScale = fontSize / baseFontHeight;

        pose.pushPose();
        pose.scale(1f / guiScaleFactor, 1f / guiScaleFactor, 1f);
        pose.scale(userScale, userScale, 1f);

        int x = 6;
        int y = 6;
        int textColor = 0xFFFFFF;
        int textShadowColor = 0xFF3F3F3F;
        int drawX = Math.round(x / userScale);
        int drawY = Math.round(y / userScale);

        gui.drawString(mc.font, Component.literal(net.justmili.true_end.client.VersionOverlay.currentText), drawX + 1, drawY + 1, textShadowColor, false);
        gui.drawString(mc.font, Component.literal(net.justmili.true_end.client.VersionOverlay.currentText), drawX, drawY, textColor, false);

        pose.popPose();
    }
}
