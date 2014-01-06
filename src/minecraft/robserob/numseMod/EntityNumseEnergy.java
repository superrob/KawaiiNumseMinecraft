package robserob.numseMod;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityNumseEnergy extends EntityThrowable
{
	public int damage = 1;
	public Entity shootingEntity;
    public EntityNumseEnergy(World par1World)
    {
        super(par1World);
    }

    public EntityNumseEnergy(World par1World, EntityLivingBase par2EntityLiving)
    {
        super(par1World, par2EntityLiving);
    }

    public EntityNumseEnergy(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
    
    public int getBrightnessForRender(float par1)
    {
        return 0xf000f0;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float par1)
    {
        return 1.0F;
    }
    
    protected float getGravityVelocity()
    {
        return 0.002F;
    }
    
    public void setDamage(int d) {
    	damage = d;
    }
    
    public void onUpdate() {
    	worldObj.spawnParticle("smoke", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
    	worldObj.spawnParticle("smoke", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
    	worldObj.spawnParticle("smoke", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
    	worldObj.spawnParticle("smoke", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
    	worldObj.spawnParticle("smoke", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
    	super.onUpdate();
    }

    public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8)
    {
        float f = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= f;
        par3 /= f;
        par5 /= f;
        par1 += rand.nextGaussian() * 0.0074999998323619366D * (double)par8;
        par3 += rand.nextGaussian() * 0.0074999998323619366D * (double)par8;
        par5 += rand.nextGaussian() * 0.0074999998323619366D * (double)par8;
        par1 *= par7;
        par3 *= par7;
        par5 *= par7;
        motionX = par1*2.5;
        motionY = par3*2.5;
        motionZ = par5*2.5;
        float f1 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        prevRotationYaw = rotationYaw = (float)((Math.atan2(par1, par5) * 180D) / Math.PI);
        prevRotationPitch = rotationPitch = (float)((Math.atan2(par3, f1) * 180D) / Math.PI);
    }
    
    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if (par1MovingObjectPosition.entityHit != null)
        {        	
            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), damage); // func_85052_h = thrower
        }

    	Object obj = null;
        for (int var3 = 0; var3 < 13; ++var3)
        {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
        /*
        for (int i = 0; i < 16; i++)
        {
        	Minecraft mc = ModLoader.getMinecraftInstance();
        	Object obj = null;
        	mc.effectRenderer.addEffect(((EntityFX)(obj = new EntityBreakingFX(worldObj, posX, posY, posZ, mod_Numse.energyBall))));
        }
		*/
        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
}