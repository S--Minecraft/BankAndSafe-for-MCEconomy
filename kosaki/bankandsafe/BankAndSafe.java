package kosaki.bankandsafe;

import java.util.logging.Level;

import kosaki.bankandsafe.blocks.BlockBank;
import kosaki.bankandsafe.blocks.BlockSafe;
import kosaki.bankandsafe.items.Item1000MP;
import kosaki.bankandsafe.items.Item100MP;
import kosaki.bankandsafe.items.ItemMPWand;
import kosaki.bankandsafe.plugins.SSPlugin;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod
(
	modid="BankAndSafe",
	name="BankAndSafe for MCEconomy",
	version="0.0.1",
	dependencies="required-after:Forge@[9.10,);required-after:FML@[6.2,);"
	//after:IC2;after:Forestry;after:SextiarySector;required-after:MCEconomy;
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false
)

public class BankAndSafe
{
	@Mod.Instance("BankAndSafe for MCEconomy")
	public static BankAndSafe instance;
	/**
	 *自体のインスタンス・IDのフィールド等
	 */
	public static final CreativeTabs tabBankAndSafe = new CreativeTabs("BankAndSafe");
	public static Block blockBank;
	public static Block blockSafe;
	public static Item item100MP;
	public static Item item1000MP;
	public static Item itemMPWand;
	public int blockBankID;
	public int blockSafeID;
	public int item100MPID;
	public int item1000MPID;
	public int itemMPWandID;
	public static final int bankGUIID=1;
	public static final int safeGUIID=2;

	/**
	 *PreInit
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		/**
		 *Config作成・設定
		 */
		Configuration cfg=new Configuration(e.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			//ブロックID・アイテムID
			Property blockProp[]={
				cfg.getBlock("blockBankID", 2550),
				cfg.getBlock("blockSafeID", 2551),
				};
			Property itemProp[]={
					cfg.getItem("item100MPID",2756),
					cfg.getItem("item1000MPID",2757),
					cfg.getItem("itemMPWandID",2758)
					};

			//テクスチャ16x or 32x
			//BankAndSafe.useIC2GregMPのConfig
			//分からん

			//blockProp.comment="EUTeleporter's BlockID";
			blockBankID=blockProp[0].getInt();
			blockSafeID=blockProp[1].getInt();
			item100MPID=itemProp[0].getInt();
			item1000MPID=itemProp[1].getInt();
			itemMPWandID=itemProp[2].getInt();

		}
		catch(Exception ex)
		{
			FMLLog.log(Level.SEVERE, ex, "Error has occurred.");
		}
		finally
		{
			cfg.save();
		}

		/**
		 *Block・Item追加
		 */
		blockBank = (new BlockBank(blockBankID, Material.rock)).setCreativeTab(tabBankAndSafe);
		blockSafe = (new BlockSafe(blockSafeID, Material.rock)).setCreativeTab(tabBankAndSafe);
		item100MP = (new Item100MP(item100MPID)).setCreativeTab(tabBankAndSafe);
		item1000MP = (new Item1000MP(item1000MPID)).setCreativeTab(tabBankAndSafe);
		itemMPWand = (new ItemMPWand(itemMPWandID)).setCreativeTab(tabBankAndSafe);
		GameRegistry.registerBlock(blockBank, "blockBank");
		GameRegistry.registerBlock(blockSafe, "blockSafe");
		GameRegistry.registerItem(item100MP, "item100MP");
		GameRegistry.registerItem(item1000MP, "item1000MP");
		GameRegistry.registerItem(itemMPWand, "itemMPWand");
	}

	/**
	 *Init
	 */
	@EventHandler
	public void eventInit(FMLInitializationEvent e)
	{
		/**
		 *敵を倒したときのドロップMP
		 */
		MinecraftForge.EVENT_BUS.register(new LivingDeathEventHandler());
		/**
		 *ワールドに入ったときMPがマイナスだったときの修正
		 *できねえ
		 */
		//MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
		/**
		 *GUI追加
		 */
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
	}

	/**
	 *PostInit
	 */
	@EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
		if (Loader.isModLoaded("SextiarySector"))
		{
			System.out.println("[BankAndSafe]Now checking SextiarySector");
			try
			{
				//SS連携読み込み
				System.out.println("[BankAndSafe]Successfully Loaded SextiarySector Plugin");
				SSPlugin.load();
			}
			catch (Exception e1) {
				System.out.println("[BankAndSafe]Failed to check SextiarySector");
				e1.printStackTrace(System.err);
			}
		}

		/*
		if(Loader.isModLoaded("IC2"))
		{
			System.out.println("[BankAndSafe]Now checking for IndustrialCraft2");
			if(Loader.isModLoaded("gregtech_addon") && BankAndSafe.useIC2GregMP)
			{
				try
				{
					//Configオン+IC2+Gregで読み込み
					System.out.println("[BankAndSafe]Successfully Loaded IndustrialCraft2 Plugin");
					IC2Plugin.load();
				}
				catch (Exception e2) {
					System.out.println("[BankAndSafe]Failed to check for IndustrialCraft2");
					e.printStackTrace(System.err);
				}
			}else if(Load.isModLoaded("gregtech_addon") && !BankAndSafe.useIC2GregMP)
			{
				//Configオフ+IC2+Greg、読み込みなし
				System.out.println("[BankAndSafe]Won't check for IndustrialCraft2");
				System.out.println("[BankAndSafe]If you wanna buy the IndustrialCraft2's Items, change the config useIC2GregMP to true.");
			}else{
				try
				{
					//IC2連携読み込み
					System.out.println("[BankAndSafe]Successfully Loaded IndustrialCraft2 Plugin");
					IC2Plugin.load();
				}
				catch (Exception e3) {
					System.out.println("[BankAndSafe]Failed to check for IndustrialCraft2");
					e.printStackTrace(System.err);
				}
			}
		if(Loader.isModLoaded("BuildCraft|Core"))
		{
			System.out.println("[BankAndSafe]Now checking for BuildCraft");
			try
			{
				//BC連携読み込み
				System.out.println("[BankAndSafe]Successfully Loaded BuildCraft Plugin");
				BCPlugin.load();
			}
			catch (Exception e4) {
				System.out.println("[BankAndSafe]Failed to check for BuildCraft");
				e.printStackTrace(System.err);
			}
		}
		if(Loader.isModLoaded("EnderIO"))
		{
			System.out.println("[BankAndSafe]Now checking for EnderIO");
			try
			{
				//EIO連携読み込み
				System.out.println("[BankAndSafe]Successfully Loaded EndeIO Plugin");
				EIOPlugin.load();
			}
			catch (Exception e5) {
				System.out.println("[BankAndSafe]Failed to check for EnderIO");
				e.printStackTrace(System.err);
			}
		}

		*/
	}
}
