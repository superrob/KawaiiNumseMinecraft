package net.minecraft.src;

import java.io.File;
import java.util.Map;

import net.minecraft.client.Minecraft;

public class mod_Numse extends BaseMod {
	
	public static final Item rainbowPowder = new Item(2500).setItemName("rainbowPowder").setCreativeTab(CreativeTabs.tabMaterials);;
	public static final Block rainbowTNT = new BlockRainbowTNT(155, 0).setResistance(10F).setBlockName("rainbowTNT");
	public static final Block rainbowPowerTNT = new BlockRainbowPowerTNT(157, 0).setResistance(10F).setBlockName("rainbowPowerTNT");
	public static final Block rainbowBlock = (new Block(156, 16, Material.cloth)).setStepSound(new StepSound("cloth", 1.0F, 1.0F)).setHardness(0.7F).setBlockName("rainbowblock").setCreativeTab(CreativeTabs.tabBlock);
	public static final Item chili = (new ItemFood(2501, 5, 0.8F, false).setPotionEffect(Potion.damageBoost.id, 30, 1, 1.0F).setAlwaysEdible().setItemName("Chili"));
	public static final Item rainbowCookie = (new ItemFood(2502, 2, 0.3F, false).setItemName("rainbowCookie"));
	public static final Item energyBall = new ItemEnergyball(2503).setItemName("energyBall").setCreativeTab(CreativeTabs.tabMaterials);
	public static final Item rainbowBar = new Item(2504).setItemName("rainbowBar").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabMaterials);
	public static final Item rainbowCore = new ItemPowerCore(2505).setItemName("rainbowCore").setCreativeTab(CreativeTabs.tabMaterials);
	public static final Item energyRifle = new ItemEnergyrifle(2506).setItemName("energyRifle").setCreativeTab(CreativeTabs.tabCombat);
	public mod_Numse(){	   
			Minecraft mc = ModLoader.getMinecraftInstance();
			
	        ModLoader.registerBlock(rainbowBlock);
	        ModLoader.registerBlock(rainbowTNT);
	        ModLoader.registerBlock(rainbowPowerTNT);
	        ModLoader.addName(rainbowPowder, "Rainbow powder");
	        ModLoader.addName(rainbowTNT, "Rainbow TNT");
	        ModLoader.addName(rainbowPowerTNT, "Rainbowpower TNT");
	        ModLoader.addName(rainbowBlock, "Rainbow block");
	        ModLoader.addName(chili, "Chili");
	        ModLoader.addName(rainbowCookie, "Rainbow cookie");
	        ModLoader.addName(energyBall, "Energy ball");
	        ModLoader.addName(rainbowBar, "Rainbow bar");
	        ModLoader.addName(rainbowCore, "Rainbow powercore");
	        ModLoader.addName(energyRifle, "Energy rifle");
	        
	        rainbowPowder.iconIndex = ModLoader.addOverride("/gui/items.png", "/numse/rainbowpowder.png");
			rainbowBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/numse/rainbowblock.png");			
			rainbowTNT.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/numse/rainbowtnt.png");
			rainbowPowerTNT.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/numse/rainbowpowertnt.png");
	        chili.iconIndex = ModLoader.addOverride("/gui/items.png", "/numse/chili.png");
	        rainbowCookie.iconIndex = ModLoader.addOverride("/gui/items.png", "/numse/rainbowcookie.png");
	        energyBall.iconIndex = ModLoader.addOverride("/gui/items.png", "/numse/item_energyball.png");
	        rainbowBar.iconIndex = ModLoader.addOverride("/gui/items.png", "/numse/item_rainbowbar.png");
	        rainbowCore.iconIndex = ModLoader.addOverride("/gui/items.png", "/numse/item_rainbowcore.png");
	        energyRifle.iconIndex = ModLoader.addOverride("/gui/items.png", "/numse/item_energyrifle.png");
	        
			ModLoader.addRecipe(new ItemStack(mod_Numse.rainbowTNT, 1), new Object [] {
                "ABA", "BAB", "ABA", Character.valueOf('A'), mod_Numse.rainbowPowder, Character.valueOf('B'), Block.sand
	        });
			ModLoader.addRecipe(new ItemStack(mod_Numse.rainbowPowerTNT, 1), new Object [] {
                "CBC", "BAB", "CBC", Character.valueOf('A'), mod_Numse.rainbowCore, Character.valueOf('B'), Block.sand, Character.valueOf('C'), Item.gunpowder,
	        });
	        ModLoader.addRecipe(new ItemStack(mod_Numse.rainbowBlock, 1), new Object [] {
                "AAA", "AAA", "AAA", Character.valueOf('A'), mod_Numse.rainbowBar
	        });
	        ModLoader.addRecipe(new ItemStack(mod_Numse.rainbowCookie, 8), new Object [] {
                "BAB", Character.valueOf('A'), mod_Numse.rainbowPowder, Character.valueOf('B'), Item.wheat
	        });
	        ModLoader.addRecipe(new ItemStack(mod_Numse.rainbowBar, 9), new Object [] {
                "A", Character.valueOf('A'), mod_Numse.rainbowBlock
	        });
	        ModLoader.addRecipe(new ItemStack(mod_Numse.rainbowCore, 1), new Object [] {
                "AAA", "ABA", "AAA", Character.valueOf('A'), mod_Numse.rainbowBar, Character.valueOf('B'), mod_Numse.energyBall
	        });
	        ModLoader.addRecipe(new ItemStack(mod_Numse.energyRifle, 1), new Object [] {
                "CAB", Character.valueOf('A'), mod_Numse.rainbowBar, Character.valueOf('B'), mod_Numse.rainbowCore, Character.valueOf('C'), Item.diamond
	        });
	        
	        ModLoader.addSmelting(mod_Numse.rainbowPowder.shiftedIndex, new ItemStack(mod_Numse.rainbowBar, 1), 0.2f);
			
	        ModLoader.addEntityTracker(this, EntityRainbowTNTPrimed.class, 101, 64, 20,true);
	        ModLoader.addEntityTracker(this, EntityRainbowPowerTNTPrimed.class, 102, 64, 20,true);
	        ModLoader.addEntityTracker(this, EntityNumseEnergy.class, 103, 64, 20,true);
			ModLoader.registerEntityID(EntityNumse.class, "Numse", 100, 14144467, 16758197);
	        ModLoader.addLocalization("entity.Numse.name", "Kawaii Numse");
	        ModLoader.addSpawn(EntityNumse.class, 75, 2, 5, EnumCreatureType.creature);	
			mc.installResource("sound3/mob/numse/numsehit.ogg", new File(mc.mcDataDir,"resources/sound3/mob/numse/numsehit.ogg"));
	        mc.installResource("sound3/mob/numse/numsedeath.ogg", new File(mc.mcDataDir,"resources/sound3/mob/numse/numsedeath.ogg"));
	        mc.installResource("sound3/mob/numse/numseshoot.ogg", new File(mc.mcDataDir,"resources/sound3/mob/numse/numseshoot.ogg"));
	}
	      
	@Override
	public Entity spawnEntity(int entityId, World worldClient, double x,
	double y, double z) {
	        
	        switch (entityId) {
	                case 101:
	                	return new EntityRainbowTNTPrimed(worldClient, x, y, z);
	                case 102:
	                	return new EntityRainbowPowerTNTPrimed(worldClient, x, y, z);	
	                case 103:
		                return new EntityNumseEnergy(worldClient, x, y, z);
	                default:
	                return null;
	        }
	}

	@Override
	public Packet23VehicleSpawn getSpawnPacket(Entity entity, int type) {
	        if (entity instanceof EntityRainbowTNTPrimed || entity instanceof EntityRainbowPowerTNTPrimed || entity instanceof EntityNumseEnergy)
	        return new Packet23VehicleSpawn(entity, type);
	        else
	        return null;
	}
	
	//Render numse
	public void addRenderer(Map map){
	        map.put(EntityNumse.class, new RenderNumse(1.0f));
	        map.put(EntityRainbowTNTPrimed.class, new RenderRainbowTNTPrimed());
	        map.put(EntityRainbowPowerTNTPrimed.class, new RenderRainbowPowerTNTPrimed());
	        map.put(EntityNumseEnergy.class, new RenderSnowball(ModLoader.addOverride("/gui/items.png", "/numse/energyball.png")));
	}
	
	public int addFuel(int i, int j)
    {
	    if(i == mod_Numse.rainbowCore.shiftedIndex)
	    {
	        return 10000;
	    }
        return 0;
    }
	
	//Base Mod Methods
	public void load(){
		
	}
	public String getVersion(){
	        return "1.0";
	}
}
