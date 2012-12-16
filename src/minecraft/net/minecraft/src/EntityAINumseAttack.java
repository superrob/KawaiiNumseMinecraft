package net.minecraft.src;

public class EntityAINumseAttack extends EntityAIBase
{
    World worldObj;

    /** The entity the AI instance has been applied to */
    EntityLiving entityHost;
    EntityLiving attackTarget;

    /**
     * A decrementing tick that spawns a ranged attack once this value reaches 0. It is then set back to the
     * maxRangedAttackTime.
     */
    int rangedAttackTime = 0;
    int rangedAttacksSinceCap = 0;
    int timeUntilCapReset = 0;
    float entityMoveSpeed;
    int field_75318_f = 0;

    /**
     * The ID of this ranged attack AI. This chooses which entity is to be used as a ranged attack.
     */
    int rangedAttackID;

    /**
     * The maximum time the AI has to wait before peforming another ranged attack.
     */
    int maxRangedAttacks;
    int maxRangedAttackTime;

    public EntityAINumseAttack(EntityLiving par1EntityLiving, float par2, int par3, int par4, int par5)
    {
        this.entityHost = par1EntityLiving;
        this.worldObj = par1EntityLiving.worldObj;
        this.entityMoveSpeed = par2;
        this.rangedAttackID = par3;
        this.maxRangedAttackTime = par4;
        this.maxRangedAttacks = par5;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLiving var1 = this.entityHost.getAttackTarget();

        if (var1 == null)
        {
            return false;
        }
        else
        {
            this.attackTarget = var1;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return this.shouldExecute() || !this.entityHost.getNavigator().noPath();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.attackTarget = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        double var1 = 125.0D;
        double var3 = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.boundingBox.minY, this.attackTarget.posZ);
        boolean var5 = this.entityHost.getEntitySenses().canSee(this.attackTarget);

        if (var5)
        {
            ++this.field_75318_f;
        }
        else
        {
            this.field_75318_f = 0;
        }

        if (var3 <= var1 && this.field_75318_f >= 20)
        {
            this.entityHost.getNavigator().clearPathEntity();
        }
        else
        {
            this.entityHost.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.entityMoveSpeed);
        }

        this.entityHost.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);
        
        if (this.timeUntilCapReset > 0) {
        	this.timeUntilCapReset = Math.max(this.timeUntilCapReset - 1, 0);
        	if (this.timeUntilCapReset <= 0) {
        		this.rangedAttacksSinceCap = 0;
        	}
        }
        
        this.rangedAttackTime = Math.max(this.rangedAttackTime - 1, 0);

        if (this.rangedAttackTime <= 0)
        {
            if (var3 <= var1 && var5)
            {
                this.doRangedAttack();
                if (this.rangedAttacksSinceCap < this.maxRangedAttacks) {
                	this.rangedAttackTime = 3;
                	this.timeUntilCapReset = this.maxRangedAttackTime;
                	this.rangedAttacksSinceCap++;
                } else {
                	this.rangedAttackTime = this.maxRangedAttackTime;
                	this.rangedAttacksSinceCap = 0;
                }
            }
        }
    }

    /**
     * Performs a ranged attack according to the AI's rangedAttackID.
     */
    private void doRangedAttack()
    {
    	EntityNumseEnergy entityenergy = new EntityNumseEnergy(this.worldObj, this.entityHost);
	    double d = attackTarget.posX - entityHost.posX;
	    double d1 = (attackTarget.posY + (double)attackTarget.getEyeHeight()) - 1.1000000238418579D - entityenergy.posY;
	    double d2 = attackTarget.posZ - entityHost.posZ;
	    float f = MathHelper.sqrt_double(d * d + d2 * d2) * 0.2F;
	    entityenergy.setThrowableHeading(d, d1 + (double)f, d2, 1.6F, 12F);
	    if (attackTarget instanceof EntityMob) {
	    	entityenergy.damage = 5;
	    }
	    worldObj.playSoundAtEntity(entityHost, "mob.numse.numseshoot", 1.0F, 1.0F / (entityHost.getRNG().nextFloat() * 0.4F + 0.8F));
	    worldObj.spawnEntityInWorld(entityenergy);
    }
}