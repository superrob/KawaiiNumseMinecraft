package me.panda_studios.kawaiinumse.setup;

import me.panda_studios.kawaiinumse.Kawaiinumse;
import me.panda_studios.kawaiinumse.item.PoopItem;
import me.panda_studios.kawaiinumse.item.RainbowPoopItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemSetup {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Kawaiinumse.MODID);

    public static final RegistryObject<Item> RAINBOW_POOP =
            ITEMS.register("rainbow_poop", () -> new RainbowPoopItem(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));

    public static final RegistryObject<Item> POOP =
            ITEMS.register("poop", () -> new PoopItem(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
}
