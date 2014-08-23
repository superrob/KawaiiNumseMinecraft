package robserob.numseMod;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityNumseEnergy.class, new RenderSnowball(NumseMod.energyBall));
    }

    @Override
    public void registerSound()
    {
        MinecraftForge.EVENT_BUS.register(new SoundEvent());// register the
                                                            // sound event
                                                            // handling class
    }
}
