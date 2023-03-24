package me.panda_studios.kawaiinumse.setup;

import me.panda_studios.kawaiinumse.Kawaiinumse;
import me.panda_studios.kawaiinumse.entity.KawaiiNumseEntity;
import me.panda_studios.kawaiinumse.entity.ThrownEnergyball;
import me.panda_studios.kawaiinumse.entity.block.RainbowPTNTPrimed;
import me.panda_studios.kawaiinumse.entity.block.RainbowTNTPrimed;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntitySetup {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Kawaiinumse.MODID);

    public static <T extends Entity> RegistryObject<EntityType<T>> buildEntity(String name, EntityType.EntityFactory<T> entity, float width, float height) {
        return ENTITIES.register(name,
                () -> EntityType.Builder.of(entity, MobCategory.CREATURE).sized(width, height).build(name));
    }

    public static final RegistryObject<EntityType<KawaiiNumseEntity>> KAWAIINUMSE = buildEntity("kawaiinumse", KawaiiNumseEntity::new, 1f, 1f);

    public static final RegistryObject<EntityType<RainbowTNTPrimed>> RAINBOW_TNT = buildEntity("rainbow_tnt", RainbowTNTPrimed::new, 1f, 1f);
    public static final RegistryObject<EntityType<RainbowPTNTPrimed>> RAINBOW_POWERED_TNT = buildEntity("rainbow_powered_tnt", RainbowPTNTPrimed::new, 1f, 1f);

    public static final RegistryObject<EntityType<ThrownEnergyball>> RAINBOW_ENERGY = buildEntity("rainbow_energy", ThrownEnergyball::new, 0.5f, 0.5f);
}
