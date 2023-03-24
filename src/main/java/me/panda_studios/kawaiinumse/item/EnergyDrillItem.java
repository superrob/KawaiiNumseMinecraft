package me.panda_studios.kawaiinumse.item;

import me.panda_studios.kawaiinumse.setup.TierSetup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Rarity;

public class EnergyDrillItem extends DiggerItem {
    public EnergyDrillItem(Properties properties) {
        super(1, 1, TierSetup.DRILL, BlockTags.MINEABLE_WITH_PICKAXE, properties
                .rarity(Rarity.RARE)
        );
    }
}
