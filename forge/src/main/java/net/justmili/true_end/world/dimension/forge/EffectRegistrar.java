package net.justmili.true_end.world.dimension.forge;

import net.justmili.true_end.init.TEDimKeys;
import net.justmili.true_end.world.dimension.NightmareWithinADream;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus =  Mod.EventBusSubscriber.Bus.MOD)
public class EffectRegistrar {

    @SubscribeEvent
    public static void registerDimensionEffects(RegisterDimensionSpecialEffectsEvent event) {
        DimensionSpecialEffects customEffect = new NightmareWithinADream(Float.NaN, true, DimensionSpecialEffects.SkyType.NONE, false, false);
        event.register(TEDimKeys.BTD.location(), customEffect);
    }
}
