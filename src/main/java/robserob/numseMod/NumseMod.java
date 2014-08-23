package robserob.numseMod;

import java.io.File;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler; // used in 1.6.2
//import cpw.mods.fml.common.Mod.PreInit;    // used in 1.5.2
//import cpw.mods.fml.common.Mod.Init;       // used in 1.5.2
//import cpw.mods.fml.common.Mod.PostInit;   // used in 1.5.2
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "NumseModID", name = "NumseMod", version = "1.0.0")
public class NumseMod {

    @Instance("NumseMod")
    public static NumseMod instance;

    public static Item rainbowPowder;
    public static Item rainbowBar;
    public static Item rainbowCore;
    public static Item chili;
    public static Item rainbowCookie;
    public static Block rainbowBlock;
    public static Item energyBall;
    public static Item energyRifle;

    @SidedProxy(clientSide = "robserob.numseMod.ClientProxy", serverSide = "robserob.numseMod.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        rainbowPowder = new Item().setUnlocalizedName("rainbowPowder").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("numsemod:rainbowpowder");
        LanguageRegistry.addName(rainbowPowder, "Rainbow powder");
        GameRegistry.registerItem(rainbowPowder, "rainbowPowder");
        // public static final Block rainbowTNT = new BlockRainbowTNT(155,
        // 0).setResistance(10F).setBlockName("rainbowTNT");
        // public static final Block rainbowPowerTNT = new
        // BlockRainbowPowerTNT(157,
        // 0).setResistance(10F).setBlockName("rainbowPowerTNT");
        rainbowBlock = (new rainbowBlock(Material.field_151580_n));
        LanguageRegistry.addName(rainbowBlock, "Rainbow block");
        MinecraftForge.setBlockHarvestLevel(rainbowBlock, "pickaxe", 2);
        GameRegistry.registerBlock(rainbowBlock, "rainbowBlock");

        rainbowBar = new Item().setUnlocalizedName("Rainbow bar").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabMaterials)
                .setTextureName("numsemod:rainbowbar");
        LanguageRegistry.addName(rainbowBar, "Rainbow bar");
        GameRegistry.registerItem(rainbowBar, "rainbowbar");

        rainbowCore = new ItemPowerCore();
        LanguageRegistry.addName(rainbowCore, "Rainbow powercore");
        GameRegistry.registerItem(rainbowCore, "rainbowcore");

        chili = (new ItemFood(5, 0.8F, false).setPotionEffect(Potion.damageBoost.id, 30, 2, 1.0F).setAlwaysEdible().setUnlocalizedName("Chili"))
                .setTextureName("numsemod:chili");
        LanguageRegistry.addName(chili, "Chili");
        GameRegistry.registerItem(chili, "chili");

        rainbowCookie = (new ItemFood(2, 0.3F, false).setPotionEffect(Potion.regeneration.id, 5, 1, 1.0F).setUnlocalizedName("Rainbow cookie"))
                .setTextureName("numsemod:rainbowcookie");
        LanguageRegistry.addName(rainbowCookie, "Rainbow Cookie");
        GameRegistry.registerItem(rainbowCookie, "rainbowCookie");

        energyBall = new ItemEnergyball();
        LanguageRegistry.addName(energyBall, "Energy ball");
        GameRegistry.registerItem(energyBall, "energyBall");
        EntityRegistry.registerModEntity(EntityNumseEnergy.class, "NumseEnergyOrb", EntityRegistry.findGlobalUniqueEntityId(), this, 500, 1, true);

        energyRifle = new ItemEnergyrifle();
        LanguageRegistry.addName(energyRifle, "Energyrifle");
        GameRegistry.registerItem(energyRifle, "energyrifle");

        ItemStack rainbowPowerStack = new ItemStack(rainbowPowder);
        ItemStack rainbowBarStack = new ItemStack(rainbowBar);
        ItemStack wheatStack = new ItemStack(Items.wheat);
        ItemStack emeraldStack = new ItemStack(Items.emerald);
        ItemStack energyBallStack = new ItemStack(energyBall);
        ItemStack rainbowCoreStack = new ItemStack(rainbowCore);
        // Rainbow Cookie crafting
        GameRegistry.addRecipe(new ItemStack(rainbowCookie, 8), "BAB", 'A', rainbowPowerStack, 'B', wheatStack);

        // Rainbow bar smelting receipe
        GameRegistry.addSmelting(rainbowPowder, rainbowBarStack, 0.2f);

        // Rainbow block crafting
        GameRegistry.addRecipe(new ItemStack(rainbowBlock, 1), "AAA", "AAA", "AAA", 'A', rainbowBarStack);

        // Rainbow powercore crafting
        GameRegistry.addRecipe(new ItemStack(rainbowCore, 1), "AAA", "ABA", "AAA", 'A', rainbowBarStack, 'B', energyBallStack);

        // Rainbow energyrifle
        GameRegistry.addRecipe(new ItemStack(energyRifle, 1), "C  ", " A ", "  B", 'A', rainbowBarStack, 'B', rainbowCoreStack, 'C', emeraldStack);
    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        proxy.registerRenderers();
        proxy.registerSound();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        // Stub Method
    }
    /*
     * Minecraft mc = ModLoader.getMinecraftInstance();
     * 
     * 
     * ModLoader.addRecipe(new ItemStack(NumseMod.rainbowTNT, 1), new Object []
     * { "ABA", "BAB", "ABA", Character.valueOf('A'), NumseMod.rainbowPowder,
     * Character.valueOf('B'), Block.sand });
     * 
     * ModLoader.addRecipe(new ItemStack(NumseMod.rainbowPowerTNT, 1), new
     * Object [] { "CBC", "BAB", "CBC", Character.valueOf('A'),
     * NumseMod.rainbowCore, Character.valueOf('B'), Block.sand,
     * Character.valueOf('C'), Item.gunpowder, });
     * 
     * ModLoader.addEntityTracker(this, EntityRainbowTNTPrimed.class, 101, 64,
     * 20,true); ModLoader.addEntityTracker(this,
     * EntityRainbowPowerTNTPrimed.class, 102, 64, 20,true);
     * ModLoader.registerEntityID(EntityNumse.class, "Numse", 100, 14144467,
     * 16758197); ModLoader.addLocalization("entity.Numse.name",
     * "Kawaii Numse"); ModLoader.addSpawn(EntityNumse.class, 75, 2, 5,
     * EnumCreatureType.creature);
     * mc.installResource("sound3/mob/numse/numsehit.ogg", new
     * File(mc.mcDataDir,"resources/sound3/mob/numse/numsehit.ogg"));
     * mc.installResource("sound3/mob/numse/numsedeath.ogg", new
     * File(mc.mcDataDir,"resources/sound3/mob/numse/numsedeath.ogg")); }
     * 
     * @Override public Entity spawnEntity(int entityId, World worldClient,
     * double x, double y, double z) {
     * 
     * switch (entityId) { case 101: return new
     * EntityRainbowTNTPrimed(worldClient, x, y, z); case 102: return new
     * EntityRainbowPowerTNTPrimed(worldClient, x, y, z); case 103: return new
     * EntityNumseEnergy(worldClient, x, y, z); default: return null; } }
     * 
     * @Override public Packet23VehicleSpawn getSpawnPacket(Entity entity, int
     * type) { if (entity instanceof EntityRainbowTNTPrimed || entity instanceof
     * EntityRainbowPowerTNTPrimed || entity instanceof EntityNumseEnergy)
     * return new Packet23VehicleSpawn(entity, type); else return null; }
     * 
     * //Render numse public void addRenderer(Map map){
     * map.put(EntityNumse.class, new RenderNumse(1.0f));
     * map.put(EntityRainbowTNTPrimed.class, new RenderRainbowTNTPrimed());
     * map.put(EntityRainbowPowerTNTPrimed.class, new
     * RenderRainbowPowerTNTPrimed()); }
     */
}
