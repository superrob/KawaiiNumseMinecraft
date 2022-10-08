package me.panda_studios.kawaiinumse.setup;

import me.panda_studios.kawaiinumse.Kawaiinumse;
import me.panda_studios.kawaiinumse.block.RainbowPTNT;
import me.panda_studios.kawaiinumse.block.RainbowTNT;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockSetup {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Kawaiinumse.MODID);

    public static final RegistryObject<Block> RAINBOW_TNT = BLOCKS.register("rainbow_tnt", () ->
            new RainbowTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .strength(0, 0)
                    .destroyTime(0)
                    .sound(SoundType.GRASS)
            ));
    public static final RegistryObject<Item> RAINBOW_TNT_ITEM = ItemSetup.ITEMS.register("rainbow_tnt", () ->
            new BlockItem(RAINBOW_TNT.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

    public static final RegistryObject<Block> RAINBOW_POWERED_TNT = BLOCKS.register("rainbow_powered_tnt", () ->
            new RainbowPTNT(BlockBehaviour.Properties.of(Material.EXPLOSIVE)
                    .strength(0, 0)
                    .destroyTime(0)
                    .sound(SoundType.GRASS)
            ));
    public static final RegistryObject<Item> RAINBOW_POWERED_TNT_ITEM = ItemSetup.ITEMS.register("rainbow_powered_tnt", () ->
            new BlockItem(RAINBOW_POWERED_TNT.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

}
