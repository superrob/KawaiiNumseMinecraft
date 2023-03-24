package me.panda_studios.kawaiinumse.block;

import me.panda_studios.kawaiinumse.entity.block.RainbowTNTPrimed;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;

public class RainbowTNT extends TntBlock {
    public RainbowTNT(Properties properties) {
        super(properties);
    }

    @Override
    public void onCaughtFire(BlockState state, Level level, BlockPos pos, @org.jetbrains.annotations.Nullable Direction direction, @org.jetbrains.annotations.Nullable LivingEntity igniter) {
        explode(level, pos, igniter);
    }

    private static void explode(Level level, BlockPos blockPos, @Nullable LivingEntity livingEntity) {
        if (!level.isClientSide) {
            RainbowTNTPrimed primedtnt =
                    new RainbowTNTPrimed(level, (double)blockPos.getX() + 0.5D, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5D, livingEntity);
            level.addFreshEntity(primedtnt);
            level.playSound((Player)null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(livingEntity, GameEvent.PRIME_FUSE, blockPos);
        }
    }
}
