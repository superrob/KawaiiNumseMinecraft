package net.minecraft.src;

import java.util.Random;

public class EntityNumse extends EntityTameable {
        Random r=new Random();

    public EntityNumse(World world){
        super(world);
        int rand = r.nextInt(8);
        switch (rand) {
        	case 0:
        		texture = "/numse/numse_yellow.png";
        	break;
        	case 1:
        		texture = "/numse/numse_green.png";
        	break;
        	case 2:
        		texture = "/numse/numse_red.png";
        	break;
        	case 3:
        		texture = "/numse/numse_orange.png";
        	break;
        	case 4:
        		texture = "/numse/numse_blue.png";
        	break;
        	case 5:
        		texture = "/numse/numse_lime.png";
        	break;
        	case 6:
        		texture = "/numse/numse_gray.png";
        	break;
        	case 7:
        		texture = "/numse/numse_pink.png";
        	break;
        	default:
        		texture = "/numse/numse_purple.png";
        	break;
        }
        moveSpeed = 0.31F;
        tasks.addTask(1, new EntityAINumseAttack(this, moveSpeed, 1, 20));
        tasks.addTask(2, new EntityAIWander(this, moveSpeed));
        tasks.addTask(3, new EntityAISwimming(this));
        tasks.addTask(4, new EntityAIFollowOwner(this, moveSpeed, 10F, 2.0F));
        tasks.addTask(5, new EntityAIWatchClosest(this, net.minecraft.src.EntityPlayer.class, 8F));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, net.minecraft.src.EntityMob.class, 16F, 0, false, true));
        targetTasks.addTask(4, new EntityAIOwnerHurtByTarget(this));
        targetTasks.addTask(5, new EntityAIOwnerHurtTarget(this));
    }
    
    public boolean isAIEnabled()
    {
        return true;
    }

    public int getMaxHealth(){
        return 5;
    }

    protected int func_40119_ar(){
        return 15;
    }
    
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

        if (itemstack != null && itemstack.itemID == mod_Numse.rainbowCookie.shiftedIndex)
        {    
        	for (int i = 0; i < 12; i++)
            {
                double d = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                worldObj.spawnParticle("heart", (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
            }
        	if (!par1EntityPlayer.capabilities.isCreativeMode)
            {
                itemstack.stackSize--;

                if (itemstack.stackSize <= 0)
                {
                    par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, null);
                }
            }
        	if (!worldObj.isRemote)
            {
                setTamed(true);
                setPathToEntity(null);
                setAttackTarget(null);
                setEntityHealth(10);
                setOwner(par1EntityPlayer.username);
                playTameEffect(true);
                worldObj.setEntityState(this, (byte)7);
            }
            return true;
        }
        else
        {
            return super.interact(par1EntityPlayer);
        }
    }

    public void onLivingUpdate(){
        super.onLivingUpdate();
    }

    protected String getLivingSound(){
        return "";
    }

    protected String getHurtSound(){
        return "mob.numse.numsehit";
    }

    protected String getDeathSound(){
        return "mob.numse.numsedeath";
    }

    protected void dropFewItems(boolean par1, int par2)
    {
    	dropItem(mod_Numse.energyBall.shiftedIndex, rand.nextInt(2)+1);
        int j = rand.nextInt(3);

        for (int k = 0; k < j; k++)
        {
	        int i = getDropItemId();
	        dropItem(i, 1);
        }
    }
    
    protected int getDropItemId(){
    	if (r.nextInt(7) == 0) {
    		return mod_Numse.chili.shiftedIndex;
    	} else {
    		if (r.nextInt(3) == 0) {
    			return mod_Numse.energyBall.shiftedIndex;
    		} else {
    			return mod_Numse.rainbowPowder.shiftedIndex;
    		}
    	}
    }

    public EnumCreatureAttribute func_40124_t(){
        return EnumCreatureAttribute.UNDEFINED;
    }
    
    public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
                return new EntityNumse(worldObj);
    }

	@Override
	public EntityAgeable func_90011_a(EntityAgeable var1) {
		// TODO Auto-generated method stub
		return null;
	}
}