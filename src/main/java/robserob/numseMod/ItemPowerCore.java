package robserob.numseMod;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPowerCore extends Item {
    public ItemPowerCore()
    {
        super();

        setUnlocalizedName("Rainbow powercore");
        setCreativeTab(CreativeTabs.tabMaterials);
        setTextureName("numsemod:rainbowcore");
    }

    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }
}
