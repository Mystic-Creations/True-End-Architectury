package net.justmili.true_end.mixin.fabric;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.architectury.platform.Platform;
import net.justmili.true_end.config.TEConfig;
import net.justmili.true_end.init.TEDimKeys;
import net.justmili.true_end.procedures.randomevents.GlitchVersionOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Gui.class) // replace with the real class
public abstract class GuiMixin {
    @Shadow @Final private Minecraft minecraft;
    @Shadow private int screenWidth;

    @WrapOperation(
            method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/Gui;getVehicleMaxHearts(Lnet/minecraft/world/entity/LivingEntity;)I"
            )
    )
    private int wrapVehicleHearts(Gui instance, LivingEntity vehicle, Operation<Integer> original) {
        return -1;
    }

    @Inject(at = @At("HEAD"), method = "renderExperienceBar", cancellable = true)
    private void renderExperienceBar(CallbackInfo ci) {
        if (minecraft.player.level().dimension() == TEDimKeys.BTD) {
            ci.cancel();
        }
    }

    @ModifyVariable(method = "renderHearts", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int moveHeartsDown(int y) {
        if (this.minecraft.player.level().dimension() != TEDimKeys.BTD) {return y;}
        if (Platform.isModLoaded("nostalgic_tweaks") && TEConfig.nostalgicTweaksCompatability) {
            return y +7 ;
        } else {
            return y -12;
        }
    }

    // "ordinal" = which int local to target, since there are multiple ints in the method
    @ModifyVariable(
            method = "renderPlayerHealth",
            at = @At("STORE"), // right after it's stored
            ordinal = 4     // <-- need to match the correct int local
    )
    private int modifyBubblesX(int original) {
        return original - 101; // shift it down 20 px, or whatever you want
    }

    // "ordinal" = which int local to target, since there are multiple ints in the method
    @ModifyVariable(
            method = "renderPlayerHealth",
            at = @At("STORE"), // right after it's stored
            ordinal = 5     // <-- need to match the correct int local
    )
    private int modifyBubblesY(int original) {
        if (this.minecraft.player.level().dimension() != TEDimKeys.BTD) {return original;}
        if (Platform.isModLoaded("nostalgic_tweaks")  && TEConfig.nostalgicTweaksCompatability) {
            return original;
        } else {
            return original + 20;  // shift it down 20 px, or whatever you want
        }
    }

    @Shadow()
    public abstract Font getFont();

    @Inject(at = @At("HEAD"), method = "render")
    public void render(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {

        if (this.minecraft.player.level().dimension() != TEDimKeys.BTD) return;

        this.minecraft.getProfiler().push("demo");
        Component component = Component.literal(GlitchVersionOverlay.currentText);

        final int fontSize = 32;
        float guiScaleFactor = (float) this.minecraft.getWindow().getScreenWidth() / (float) this.minecraft.getWindow().getGuiScaledWidth();
        float baseFontHeight = (float) this.minecraft.font.lineHeight;
        float userScale = fontSize / baseFontHeight;

        PoseStack pose = guiGraphics.pose();

        pose.pushPose();
        pose.scale(1f / guiScaleFactor, 1f / guiScaleFactor, 1f);
        pose.scale(userScale, userScale, 1f);

        int i = this.getFont().width(component);
        int x = 6;
        int y = 6;
        int textColor = 0xFFFFFF;
        int textShadowColor = 0xFF3F3F3F;
        int drawX = Math.round(x / userScale);
        int drawY = Math.round(y / userScale);


        guiGraphics.drawString(minecraft.font, Component.literal(GlitchVersionOverlay.currentText), drawX + 1, drawY + 1, textShadowColor, false);
        guiGraphics.drawString(minecraft.font, Component.literal(GlitchVersionOverlay.currentText), drawX, drawY, textColor, false);
        this.minecraft.getProfiler().pop();
    }

}
