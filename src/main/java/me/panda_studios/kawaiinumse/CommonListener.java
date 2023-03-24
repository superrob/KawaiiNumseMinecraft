package me.panda_studios.kawaiinumse;

import me.panda_studios.kawaiinumse.setup.EntitySetup;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Kawaiinumse.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonListener {
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntitySetup.KAWAIINUMSE.get(),
                Mob.createMobAttributes()
                        .add(Attributes.MAX_HEALTH, 20)
                        .add(Attributes.MOVEMENT_SPEED, 0.25)
                        .add(Attributes.FOLLOW_RANGE, 10)
                        .build());
    }
}
