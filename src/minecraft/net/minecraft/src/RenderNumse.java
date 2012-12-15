      package net.minecraft.src;
          import org.lwjgl.opengl.GL11;

public class RenderNumse extends RenderLiving
{

    public RenderNumse(float f)
    {
        super(new ModelNumse(), f);
    }
        
        protected void preRenderScale(EntityNumse entity, float f)
    {
        GL11.glScalef(1.5F, 1.5F, 1.5F);
    }

    public void renderjackinhorse(EntityLiving entityliving, double d, double d1, double d2, 
            float f, float f1)
    {
        super.doRenderLiving(entityliving, d, d1, d2, f, f1);
    }

    public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, 
            float f, float f1)
    {
        renderjackinhorse(entityliving, d, d1, d2, f, f1);
    }

    public void doRender(Entity entity, double d, double d1, double d2, 
            float f, float f1)
    {
        renderjackinhorse((EntityLiving)entity, d, d1, d2, f, f1);
    }
        
        protected void preRenderCallback(EntityLiving entityliving, float f)
    {
        preRenderScale((EntityNumse)entityliving, f);
    }
}