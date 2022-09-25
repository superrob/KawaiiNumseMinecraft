package me.panda_studios.kawaiinumse.setup;

import me.panda_studios.kawaiinumse.Kawaiinumse;
import me.panda_studios.kawaiinumse.item.*;
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
    public static final RegistryObject<Item> RAINBOW_DUST =
            ITEMS.register("rainbow_dust", () -> new RainbowDustItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> RAINBOW_ENERGY =
            ITEMS.register("rainbow_energy", () -> new RainbowEnergyItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> ENERGY_RIFLE =
            ITEMS.register("energy_rifle", () -> new EnergyRifleItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
}