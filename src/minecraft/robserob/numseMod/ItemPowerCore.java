package robserob.numseMod;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPowerCore extends Item
{
    public ItemPowerCore(int par1)
    {
        super(par1);
    }

    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }
}
