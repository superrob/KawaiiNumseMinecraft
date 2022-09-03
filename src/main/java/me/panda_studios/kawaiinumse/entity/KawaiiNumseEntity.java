package me.panda_studios.kawaiinumse.entity;

import me.panda_studios.kawaiinumse.setup.ItemSetup;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class KawaiiNumseEntity extends PathfinderMob implements IAnimatable {
    public static final EntityDataAccessor<Integer> attackState = SynchedEntityData.defineId(KawaiiNumseEntity.class, EntityDataSerializers.INT);

    public KawaiiNumseEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1, 0));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.goalSelector.addGoal(2, new AttackGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        super.registerGoals();
    }

    boolean canPoop = false;
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
    protected void playStepSound(BlockPos blockPos, BlockState blockState) {

    }

    @Override
    public boolean canStartSwimming() {
        return false;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(attackState, -1);
    }

    public void setAttackState(int time) {
        this.entityData.set(attackState, time);
    }

    public int getAttackState() {
        return this.entityData.get(attackState);
    }

    /**
     * Animations
     */
    private final AnimationFactory factory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isAggressive() && getAttackState() <= 14) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("Shoot", false));
        } else {
            if (event.isMoving())
                event.getController().setAnimation(new AnimationBuilder().addAnimation("Move", true));
            else
                event.getController().setAnimation(new AnimationBuilder().addAnimation("Idle", true));
        }
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
}

class AttackGoal extends Goal {
    private final KawaiiNumseEntity entity;
    private final int attackTimeD = 20 * 3;
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
        attackTime = attackTimeD;
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
        super.tick();
        LivingEntity livingentity = this.entity.getTarget();

        if (this.attackTime <= -8)
            this.attackTime = this.attackTimeD;

        if (livingentity != null) {
            boolean inLineOfSight = this.entity.getSensing().hasLineOfSight(livingentity);
            this.attackTime--;
            this.entity.setAttackState(attackTime);
            this.entity.lookAt(livingentity, 30, 30);
            double d0 = this.entity.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
            if (inLineOfSight) {
                if (d0 < this.getFollowDistance() * this.getFollowDistance()) {
                    if (this.attackTime == 0) {
                        double d1 = livingentity.getX() - this.entity.getX();
                        double d2 = livingentity.getY(0.5D) - this.entity.getY(0.5D);
                        double d3 = livingentity.getZ() - this.entity.getZ();
                        double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;

                        SmallFireball smallfireball = new SmallFireball(
                                this.entity.level,
                                this.entity,
                                this.entity.getRandom().triangle(d1, 2.297D * d4), d2,
                                this.entity.getRandom().triangle(d3, 2.297D * d4)
                        );
                        smallfireball.setPos(smallfireball.getX(), this.entity.getY(0.5D) + 0.5D, smallfireball.getZ());
                        this.entity.level.addFreshEntity(smallfireball);
                    }
                } else {
                    this.entity.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
                }
            }
        }
    }

    private double getFollowDistance() {
        return this.entity.getAttributeValue(Attributes.FOLLOW_RANGE);
    }
}
