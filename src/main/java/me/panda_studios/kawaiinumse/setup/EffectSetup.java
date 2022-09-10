package me.panda_studios.kawaiinumse.setup;

import me.panda_studios.kawaiinumse.Kawaiinumse;
import me.panda_studios.kawaiinumse.effect.DiarrheaEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectSetup {
    public static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Kawaiinumse.MODID);

    public static final RegistryObject<MobEffect> DIARRHEA = EFFECT.register("diarrhea", () -> new DiarrheaEffect());
}
