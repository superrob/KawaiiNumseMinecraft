package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class EntityRainbowTNTPrimed extends Entity
{
    /** How long the fuse is */
    public int fuse;

    
    public EntityRainbowTNTPrimed(World par1World)
    {
        super(par1World);
        this.fuse = 0;
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
    }

    public EntityRainbowTNTPrimed(World par1World, double par2, double par4, double par6)
    {
        this(par1World);
        this.setPosition(par2, par4, par6);
        float var8 = (float)(Math.random() * Math.PI * 2.0D);
        this.motionX = (double)(-((float)Math.sin((double)var8)) * 0.02F);
        this.motionY = 0.20000000298023224D;
        this.motionZ = (double)(-((float)Math.cos((double)var8)) * 0.02F);
        this.fuse = 80;
        this.prevPosX = par2;
        this.prevPosY = par4;
        this.prevPosZ = par6;
    }

    protected void entityInit() {}

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
            this.motionY *= -0.5D;
        }

        if (this.fuse-- <= 0)
        {
            this.setDead();

            if (!this.worldObj.isRemote)
            {
                this.explode();
            }
        }
        else
        {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    private void explode()
    {
    	Minecraft mc = ModLoader.getMinecraftInstance();    
    	Object obj = null;
    	mc.effectRenderer.addEffect(((EntityFX)(obj = new EntityLargeExplodeRainbowFX(mc.renderEngine, worldObj, posX, posY, posZ, 25.0D, 25.0D, 25.0D))));
    	worldObj.playSoundEffect(posX, posY, posZ, "random.explode", 4F, (1.0F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
    	
    	
    	float explosionSize = 5;
    	float f = explosionSize;
        int i = 16;

        for (int j = 0; j < i; j++)
        {
            for (int l = 0; l < i; l++)
            {
                label0:

                for (int j1 = 0; j1 < i; j1++)
                {
                    if (j != 0 && j != i - 1 && l != 0 && l != i - 1 && j1 != 0 && j1 != i - 1)
                    {
                        continue;
                    }

                    double d = ((float)j / ((float)i - 1.0F)) * 2.0F - 1.0F;
                    double d1 = ((float)l / ((float)i - 1.0F)) * 2.0F - 1.0F;
                    double d2 = ((float)j1 / ((float)i - 1.0F)) * 2.0F - 1.0F;
                    double d3 = Math.sqrt(d * d + d1 * d1 + d2 * d2);
                    d /= d3;
                    d1 /= d3;
                    d2 /= d3;
                    float f1 = explosionSize * (0.7F + worldObj.rand.nextFloat() * 0.6F);
                    double d5 = posX;
                    double d7 = posY;
                    double d9 = posZ;
                    float f2 = 0.3F;

                    do
                    {
                        if (f1 <= 0.0F)
                        {
                            continue label0;
                        }

                        int l2 = MathHelper.floor_double(d5);
                        int i3 = MathHelper.floor_double(d7);
                        int j3 = MathHelper.floor_double(d9);
                        int k3 = worldObj.getBlockId(l2, i3, j3);
                        if (k3 > 0)
                        {
                            f1 -= (Block.blocksList[k3].getExplosionResistance(this) + 0.3F) * f2;
                        }

                        if (f1 > 0.0F)
                        {
                        	int idofBlock = worldObj.getBlockId(l2, i3, j3+1);
                        	if (idofBlock == Block.grass.blockID || idofBlock == Block.dirt.blockID || idofBlock == Block.tilledField.blockID) { //(worldObj.canBlockBePlacedAt(Block.plantRed.blockID, l2, i3, j3+1, true, 0)) { //  && (worldObj.getBlockId(l2, i3, j3) == Block.dirt.blockID || worldObj.getBlockId(l2, i3, j3) == Block.grass.blockID)
                        		if (rand.nextInt(60) == 0) {
	                        		if (rand.nextInt(2) == 0) {
	                        			worldObj.setBlockWithNotify(l2, i3, j3+1, Block.plantRed.blockID);             
	                        		} else {
	                        			worldObj.setBlockWithNotify(l2, i3, j3+1, Block.plantYellow.blockID);  
	                        		}
                        		}
                        	}
                            //destroyedBlockPositions.add(new ChunkPosition(l2, i3, j3));
                        }
                        d5 += d * (double)f2;
                        d7 += d1 * (double)f2;
                        d9 += d2 * (double)f2;
                        f1 -= f2 * 0.75F;
                    }
                    while (true);
                }
            }
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setByte("Fuse", (byte)fuse);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        fuse = par1NBTTagCompound.getByte("Fuse");
    }

    public float getShadowSize()
    {
        return 0.0F;
    }
}
