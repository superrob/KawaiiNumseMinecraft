package me.panda_studios.kawaiinumse.setup;

import me.panda_studios.kawaiinumse.Kawaiinumse;
import me.panda_studios.kawaiinumse.entity.KawaiiNumseEntity;
import me.panda_studios.kawaiinumse.entity.block.RainbowTNTPrimed;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntitySetup {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Kawaiinumse.MODID);

    public static <T extends Entity> RegistryObject<EntityType<T>> buildEntity(EntityType.EntityFactory<T> entity,
                                                                               Class<T> entityClass, float width, float height) {
        String name = entityClass.getSimpleName().toLowerCase();
        return ENTITIES.register(name,
                () -> EntityType.Builder.of(entity, MobCategory.CREATURE).sized(width, height).build(name));
    }

    public static final RegistryObject<EntityType<KawaiiNumseEntity>> KAWAIINUMSE = buildEntity(KawaiiNumseEntity::new, KawaiiNumseEntity.class, 1f, 1f);

    public static final RegistryObject<EntityType<RainbowTNTPrimed>> RAINBOW_TNT = buildEntity(RainbowTNTPrimed::new, RainbowTNTPrimed.class, 1f, 1f);
}
