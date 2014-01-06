package robserob.numseMod;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEnergyrifle extends Item
{
	private final EnumToolMaterial toolMaterial;
    public ItemEnergyrifle(int par1)
    {
        super(par1);
        toolMaterial = EnumToolMaterial.IRON;
        maxStackSize = 1;
        setMaxDamage(toolMaterial.getMaxUses());
    }
    
    public boolean isFull3D()
    {
        return true;
    }
    
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 0x00040;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par2World.playSoundAtEntity(par3EntityPlayer, "numsemod:numseshoot", 1.0F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
        	par1ItemStack.damageItem(1, par3EntityPlayer);
        }
        if (!par2World.isRemote)
        {
        	EntityNumseEnergy en = new EntityNumseEnergy(par2World, par3EntityPlayer);
        	en.damage = 4;
            par2World.spawnEntityInWorld(en);
        }

        return par1ItemStack;
    }
}
