package me.panda_studios.kawaiinumse.entity;

import com.mojang.math.Vector3f;
import me.panda_studios.kawaiinumse.setup.ItemSetup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class KawaiiNumseEntity extends PathfinderMob implements IAnimatable {
    public static final EntityDataAccessor<Integer> attackState = SynchedEntityData.defineId(KawaiiNumseEntity.class, EntityDataSerializers.INT);

    public static final EntityDataAccessor<Integer> redColor = SynchedEntityData.defineId(KawaiiNumseEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> greenColor = SynchedEntityData.defineId(KawaiiNumseEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> blueColor = SynchedEntityData.defineId(KawaiiNumseEntity.class, EntityDataSerializers.INT);

    public KawaiiNumseEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1, 0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.goalSelector.addGoal(2, new AttackGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    boolean canPoop = true;
    int poopRSTime = 1500;
    public int poopTime = this.random.nextInt(poopRSTime) + poopRSTime;

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (this.isAlive() && --this.poopTime <= 0 && canPoop) {
            this.playSound(SoundEvents.CHICKEN_EGG, 1, 1);
            this.spawnAtLocation(ItemSetup.RAINBOW_POOP.get());
            this.poopTime = this.random.nextInt(poopRSTime) + poopRSTime;
        }
    }

    @Override
    public boolean canStartSwimming() {
        return false;
    }

    int deathTime = 0;
    @Override
    protected void tickDeath() {
        if (deathTime++ >= 30) {
            this.remove(Entity.RemovalReason.KILLED);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(attackState, -1);

        this.entityData.define(redColor, 1);
        this.entityData.define(greenColor, 1);
        this.entityData.define(blueColor, 1);
    }

    public void setAttackState(int time) {
        this.entityData.set(attackState, time);
    }

    public int getAttackState() {
        return this.entityData.get(attackState);
    }

    public void setColor(int r, int g, int b) {
        this.entityData.set(redColor, r);
        this.entityData.set(greenColor, g);
        this.entityData.set(blueColor, b);
    }

    public Vector3f getColor() {
        return new Vector3f(this.entityData.get(redColor), this.entityData.get(greenColor), this.entityData.get(blueColor));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putFloat("redColor", (int) getColor().x());
        compoundTag.putFloat("greenColor", (int) getColor().y());
        compoundTag.putFloat("blueColor", (int) getColor().z());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        int r = (int) compoundTag.getFloat("redColor");
        int g = (int) compoundTag.getFloat("greenColor");
        int b = (int) compoundTag.getFloat("blueColor");
        if (r <= 0 || g <= 0 || b <= 0) {
            int min = 1;
            int max = 255;
            r = this.random.nextInt(max-min) + min;
            g = this.random.nextInt(max-min) + min;
            b = this.random.nextInt(max-min) + min;
        }
        setColor(r, g, b);
    }

    /**
     * Animations
     */
    private final AnimationFactory factory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isInWater()) {
            if (this.dead || this.getHealth() < 0.01 || this.isDeadOrDying()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("Drowned", false));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("Drowning", true));
            return PlayState.CONTINUE;
        }

        if (this.dead || this.getHealth() < 0.01 || this.isDeadOrDying()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("Died", false));
            return PlayState.CONTINUE;
        }

        if (this.isAggressive() && getAttackState() <= 15) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("Shoot", false));
            return PlayState.CONTINUE;
        }

        if (event.isMoving())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("Move", true));
        else
            event.getController().setAnimation(new AnimationBuilder().addAnimation("Idle", true));

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 10, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    static class AttackGoal extends Goal {
        private final KawaiiNumseEntity entity;
        private final int attackTimeD = 20;
        private int attackTime = -1;

        public AttackGoal(KawaiiNumseEntity livingEntity) {
            this.entity = livingEntity;
        }

        @Override
        public boolean canUse() {
            LivingEntity livingentity = this.entity.getTarget();
            return livingentity != null && livingentity.isAlive() && this.entity.canAttack(livingentity);
        }

        @Override
        public void start() {
            super.start();
            this.entity.setAggressive(true);
            attackTime = attackTimeD - 40;
        }

        @Override
        public void stop() {
            super.stop();
            this.entity.setAggressive(false);
            this.attackTime = -1;
        }

        @Override
        public boolean canContinueToUse() {
            return this.canUse();
        }

        @Override
        public void tick() {
            LivingEntity livingentity = this.entity.getTarget();

            if (this.attackTime <= -8)
                this.attackTime = this.attackTimeD;

            if (livingentity != null) {
                boolean inLineOfSight = this.entity.getSensing().hasLineOfSight(livingentity);
                this.attackTime--;
                this.entity.setAttackState(attackTime);
                this.entity.getLookControl().setLookAt(livingentity);
                double d0 = this.entity.distanceToSqr(livingentity);
                if (this.attackTime == 0 && inLineOfSight) {
                    this.entity.level.playSound(null, this.entity.getX(), this.entity.getY(), this.entity.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (this.entity.level.getRandom().nextFloat() * 0.4F + 0.8F));
                    ThrownEnergyball thrownEnergyball = new ThrownEnergyball(this.entity.level, this.entity);
                    thrownEnergyball.setItem(new ItemStack(ItemSetup.RAINBOW_ENERGY.get()));
                    thrownEnergyball.shootFromRotation(this.entity, this.entity.getViewXRot(1), this.entity.getViewYRot(1), 0.0F, 1.5F, 1.0F);
                    this.entity.level.addFreshEntity(thrownEnergyball);
                }
                if (d0 > this.getFollowDistance()) {
                    this.entity.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
                } else {
                    this.entity.getMoveControl().setWantedPosition(this.entity.getX(), this.entity.getY(), this.entity.getZ(), 1.0D);
                }
            }
            super.tick();
        }

        private double getFollowDistance() {
            return this.entity.getAttributeValue(Attributes.FOLLOW_RANGE);
        }
    }
}
