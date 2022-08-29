package me.panda_studios.kawaiinumse;

import me.panda_studios.kawaiinumse.entity.render.KawaiiNumseRender;
import me.panda_studios.kawaiinumse.setup.EntitySetup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Kawaiinumse.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntitySetup.KAWAIINUMSE.get(), KawaiiNumseRender::new);
    }
}
