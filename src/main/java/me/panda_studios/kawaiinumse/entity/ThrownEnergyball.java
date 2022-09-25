package me.panda_studios.kawaiinumse.entity;

import me.panda_studios.kawaiinumse.setup.EntitySetup;
import me.panda_studios.kawaiinumse.setup.ItemSetup;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrownEnergyball extends ThrowableItemProjectile {
    public ThrownEnergyball(EntityType<? extends ThrownEnergyball> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownEnergyball(Level level, LivingEntity livingEntity) {
        super(EntitySetup.RAINBOW_ENERGY.get(), livingEntity, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemSetup.RAINBOW_ENERGY.get();
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);

        entityHitResult.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), 10);
    }
}
