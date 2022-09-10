package me.panda_studios.kawaiinumse.setup;

import me.panda_studios.kawaiinumse.Kawaiinumse;
import me.panda_studios.kawaiinumse.block.RainbowTNT;
import me.panda_studios.kawaiinumse.item.PoopItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockSetup {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Kawaiinumse.MODID);

    public static final RegistryObject<Block> RAINBOW_TNT = BLOCKS.register("rainbow_tnt", () ->
            new RainbowTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)));
    public static final RegistryObject<Item> RAINBOW_TNT_Item = ItemSetup.ITEMS.register("rainbow_tnt", () ->
            new BlockItem(RAINBOW_TNT.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

}
