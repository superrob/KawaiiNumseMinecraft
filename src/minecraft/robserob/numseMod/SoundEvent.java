package robserob.numseMod;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundEvent 
{
        @ForgeSubscribe
        public void onSoundLoad(SoundLoadEvent event) {
        	event.manager.addSound("numsemod:numsedeath.ogg");
        	event.manager.addSound("numsemod:numsehit.ogg");
            event.manager.addSound("numsemod:numseshoot.ogg");
        }
}
