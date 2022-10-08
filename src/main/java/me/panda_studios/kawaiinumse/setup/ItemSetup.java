package me.panda_studios.kawaiinumse.setup;

import me.panda_studios.kawaiinumse.Kawaiinumse;
import me.panda_studios.kawaiinumse.item.*;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemSetup {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Kawaiinumse.MODID);

    public static final RegistryObject<Item> RAINBOW_POOP =
            ITEMS.register("rainbow_poop", () -> new RainbowPoopItem(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
    public static final RegistryObject<Item> POOP =
            ITEMS.register("poop", () -> new PoopItem(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
    public static final RegistryObject<Item> RAINBOW_COOKIE =
            ITEMS.register("rainbow_cookie", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
    public static final RegistryObject<Item> RAINBOW_DUST =
            ITEMS.register("rainbow_dust", () -> new RainbowDustItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> RAINBOW_INGOT =
            ITEMS.register("rainbow_ingot", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> RAINBOW_ENERGY =
            ITEMS.register("rainbow_energy", () -> new RainbowEnergyItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> RAINBOW_ENERGY_CORE =
            ITEMS.register("rainbow_energy_core", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> ENERGY_RIFLE =
            ITEMS.register("energy_rifle", () -> new EnergyRifleItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> ENERGY_DRILL =
            ITEMS.register("energy_drill", () -> new EnergyDrillItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

    public static final RegistryObject<ForgeSpawnEggItem> KAWAIINUMSE_EGG =
            ITEMS.register("kawaiinumse_egg", () -> new ForgeSpawnEggItem(EntitySetup.KAWAIINUMSE, 0x7487CF, 0x282089,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}