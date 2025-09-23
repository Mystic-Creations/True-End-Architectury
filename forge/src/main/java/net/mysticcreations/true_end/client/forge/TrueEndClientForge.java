package net.mysticcreations.true_end.client.forge;

import net.mysticcreations.true_end.client.TrueEndCommonClient;
import net.mysticcreations.true_end.init.TEParticles;
import net.mysticcreations.true_end.forge.TrueEndForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TrueEndClientForge {

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {

        TrueEndForge.EVENT_BUS.addListener(EntityRenderers::registerEntityRenderers);
        TEParticles.register();
        event.enqueueWork(TrueEndCommonClient::init);
    }

}
