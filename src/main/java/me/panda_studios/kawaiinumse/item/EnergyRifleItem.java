package me.panda_studios.kawaiinumse.item;

import me.panda_studios.kawaiinumse.entity.ThrownEnergyball;
import me.panda_studios.kawaiinumse.setup.EntitySetup;
import me.panda_studios.kawaiinumse.setup.ItemSetup;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.Set;
import java.util.function.Predicate;

public class EnergyRifleItem extends Item {
    public EnergyRifleItem(Properties properties) {
        super(properties
                .durability(500)
                .rarity(Rarity.RARE)
        );
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack ammoItemStack = new ItemStack(ItemSetup.RAINBOW_ENERGY.get());
        ItemStack itemStack = player.getItemInHand(interactionHand);
        ItemStack ammo = null;
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            if (player.getInventory().getItem(i).sameItem(ammoItemStack)) {
                ammo = player.getInventory().getItem(i);
                break;
            }
        }
        if (ammo == null && !player.isCreative()) {
            return InteractionResultHolder.fail(itemStack);
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        player.getCooldowns().addCooldown(this, 1);
        if (!level.isClientSide) {
            ThrownEnergyball thrownEnergyball = new ThrownEnergyball(level, player);
            thrownEnergyball.setItem(new ItemStack(ItemSetup.RAINBOW_ENERGY.get()));
            thrownEnergyball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(thrownEnergyball);
        }
        itemStack.hurtAndBreak(1, player, (event) -> {
            event.broadcastBreakEvent(player.getUsedItemHand());
        });

        if (!player.isCreative()) {
            ammo.shrink(1);
            if (ammo.isEmpty()) {
                player.getInventory().removeItem(ammo);
            }
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}
