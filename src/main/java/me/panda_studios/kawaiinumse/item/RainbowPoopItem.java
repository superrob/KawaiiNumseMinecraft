package me.panda_studios.kawaiinumse.item;

import me.panda_studios.kawaiinumse.setup.EffectSetup;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;

public class RainbowPoopItem extends BoneMealItem {
    public RainbowPoopItem(Properties properties) {
        super(properties
                .food(new FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2f)
                        .effect(new MobEffectInstance(EffectSetup.DIARRHEA.get(), 1200, 0), 0.75f)
                        .build())
        );
    }

    @Override
    public boolean isEdible() {
        return true;
    }

}
