package me.panda_studios.kawaiinumse.effect;

import me.panda_studios.kawaiinumse.Kawaiinumse;
import me.panda_studios.kawaiinumse.setup.ItemSetup;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Attr;

import java.util.Random;

public class DiarrheaEffect extends MobEffect {
    public DiarrheaEffect() {
        super(MobEffectCategory.NEUTRAL, 5797459);
    }

    public int poopTime = (20 * 10);

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        super.applyEffectTick(entity, amplifier);
        entity.spawnAtLocation(ItemSetup.POOP.get());
        entity.playSound(SoundEvents.CHICKEN_EGG, 1, 1);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % poopTime <= 0;
    }
}
