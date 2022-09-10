package me.panda_studios.kawaiinumse;

import com.mojang.logging.LogUtils;
import me.panda_studios.kawaiinumse.setup.BlockSetup;
import me.panda_studios.kawaiinumse.setup.EffectSetup;
import me.panda_studios.kawaiinumse.setup.EntitySetup;
import me.panda_studios.kawaiinumse.setup.ItemSetup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;

@Mod(Kawaiinumse.MODID)
public class Kawaiinumse {
    public static final String MODID = "kawaiinumse";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public Kawaiinumse() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        GeckoLib.initialize();
        GeckoLibMod.DISABLE_IN_DEV = false;

        ItemSetup.ITEMS.register(modEventBus);
        BlockSetup.BLOCKS.register(modEventBus);
        EntitySetup.ENTITIES.register(modEventBus);
        EffectSetup.EFFECT.register(modEventBus);
    }
}

