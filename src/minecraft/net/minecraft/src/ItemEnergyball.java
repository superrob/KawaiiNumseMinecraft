package net.minecraft.src;

import java.util.Random;

public class ItemEnergyball extends Item
{
    public ItemEnergyball(int par1)
    {
        super(par1);
        maxStackSize = 64;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            par1ItemStack.stackSize--;
        }

        par2World.playSoundAtEntity(par3EntityPlayer, "mob.numse.numseshoot", 1.0F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!par2World.isRemote)
        {
            par2World.spawnEntityInWorld(new EntityNumseEnergy(par2World, par3EntityPlayer));
        }

        return par1ItemStack;
    }
}
