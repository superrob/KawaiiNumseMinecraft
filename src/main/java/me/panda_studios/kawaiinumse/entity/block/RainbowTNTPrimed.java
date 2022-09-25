package me.panda_studios.kawaiinumse.entity.block;

import me.panda_studios.kawaiinumse.setup.EntitySetup;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;

import javax.annotation.Nullable;

public class RainbowTNTPrimed extends Entity {
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(RainbowTNTPrimed.class, EntityDataSerializers.INT);
    private static final int DEFAULT_FUSE_TIME = 80;
    @Nullable
    private LivingEntity owner;

    public RainbowTNTPrimed(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
        this.blocksBuilding = true;
    }
    public RainbowTNTPrimed(Level level, double posX, double posY, double posZ, @org.jetbrains.annotations.Nullable LivingEntity entity) {
        this(EntitySetup.RAINBOW_TNT.get(), level);
        this.setPos(posX, posY, posZ);
        double d0 = level.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.xo = posX;
        this.yo = posY;
        this.zo = posZ;
        this.owner = entity;
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_FUSE_ID, 80);
    }

    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    public boolean isPickable() {
        return !this.isRemoved();
    }

    @Override
    public void tick() {
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
        if (this.onGround) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.5D, 0.7D));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.level.isClientSide) {
                this.explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level.isClientSide) {
                this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

        if (getFuse() <= 1) {
            if (this.level.isClientSide) {
                int max = 3;
                int min = -3;
                for (int slot = 0; slot < 10; slot++) {
                    int x = this.random.nextInt(max-min) + min;
                    int y = this.random.nextInt(max-min) + min;
                    int z = this.random.nextInt(max-min) + min;
                    this.level.addParticle(ParticleTypes.EXPLOSION, this.getX() + x, this.getY() + y, this.getZ() + z, 1, 0, 0);
                }
            }
        }
    }

    protected void explode() {
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1.0F, 1.0F);

        Explosion explosion = new Explosion(this.level, this, null, null, this.getX(), this.getY(0.0625D), this.getZ(), 4.0F, false, Explosion.BlockInteraction.NONE);
        explosion.explode();
        for (BlockPos block: explosion.getToBlow()) {
            if (this.level.getBlockState(block).getBlock() instanceof BonemealableBlock) {
                BonemealableBlock bonemealableBlock = (BonemealableBlock) this.level.getBlockState(block).getBlock();
                if (bonemealableBlock.isValidBonemealTarget(this.level, block, this.level.getBlockState(block), this.level.isClientSide)) {
                    if (this.level instanceof ServerLevel) {
                        if (bonemealableBlock.isBonemealSuccess(this.level, this.level.random, block, this.level.getBlockState(block))) {
                            bonemealableBlock.performBonemeal((ServerLevel)this.level, this.level.random, block, this.level.getBlockState(block));
                        }
                    }
                }
            }
        }
        explosion.finalizeExplosion(true);
    }

    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putShort("Fuse", (short)this.getFuse());
    }

    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        this.setFuse(compoundTag.getShort("Fuse"));
    }

    protected float getEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 0.15F;
    }

    public void setFuse(int i) {
        this.entityData.set(DATA_FUSE_ID, i);
    }

    public int getFuse() {
        return this.entityData.get(DATA_FUSE_ID);
    }

    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
