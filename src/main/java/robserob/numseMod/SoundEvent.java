package robserob.numseMod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;

public class SoundEvent 
{
    @SubscribeEvent
    public void onSoundLoad(SoundLoadEvent event) {
        event.manager.addSound("numsemod:numsedeath.ogg");
        event.manager.addSound("numsemod:numsehit.ogg");
        event.manager.addSound("numsemod:numseshoot.ogg");
    }
}
