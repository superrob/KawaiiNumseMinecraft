package robserob.numseMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class rainbowBlock extends Block {

    public rainbowBlock(Material arg0)
    {
        super(arg0);
        func_149672_a(new SoundType("cloth", 1.0F, 1.0F));
        func_149711_c(0.7F);
        func_149647_a(CreativeTabs.tabBlock);
        func_149658_d("numsemod:rainbowblock");
    }

}
