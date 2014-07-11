package kosaki.bankandsafe;

import java.util.logging.Level;

import kosaki.bankandsafe.blocks.BlockBank;
import kosaki.bankandsafe.blocks.BlockSafe;
import kosaki.bankandsafe.creativetabs.CreativeTabBankAndSafe;
import kosaki.bankandsafe.enchantments.EnchantmentMoreMPdrop;
import kosaki.bankandsafe.items.Item1000MP;
import kosaki.bankandsafe.items.Item100MP;
import kosaki.bankandsafe.items.ItemMPWand;
import kosaki.bankandsafe.tileentities.TileEntitySafe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod
(
	modid="BankAndSafe",
	name="BankAndSafe for MCEconomy",
	version="0.0.1_Alpha",
	dependencies="required-after:Forge@[9.10,);required-after:FML@[6.2,);required-after:MCEconomy"
	//after:IC2;after:Forestry;after:SextiarySector;after:BuildCraft|Core;after:EnderIO
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false	/*,
	clientPacketHandlerSpec = 
		@SidedPacketHandler
		(
			channels = {"mod"},
		 	packetHandler = net.minecraft.src.SSPPacketHandler.class
		),
		
	serverPacketHandlerSpec = 
		@SidedPacketHandler
		(
			channels = {"mod" },
			packetHandler = net.minecraft.src.SMPPacketHandler.class
		)
	*/
)
public class BankAndSafe
{
	/**
	 *自体のインスタンス・IDのフィールド等
	 */
	@Instance("BankAndSafe for MCEconomy")
	public static BankAndSafe instance = new BankAndSafe();
	public static GuiHandler guiHandler = new GuiHandler();

	public static final CreativeTabs tabBankAndSafe = new CreativeTabBankAndSafe("BankAndSafe");
	public static Block blockBank;
	public static Block blockSafe;
	//public static Block blockMP;
	public static Item item100MP;
	public static Item item1000MP;
	public static Item itemMPWand;
	public static Enchantment moreMPdrop;

	//ID
	public int blockBankID;
	public int blockSafeID;
	//public int blockMPID;
	public int item100MPID;
	public int item1000MPID;
	public int itemMPWandID;
	public static final int bankGUIID = 1;
	public static final int safeGUIID = 2;

	//メッセージ
	public static final String ITEM1000MP_TO_MP_MESSAGE = "Exchange to 1000MP is succeeded!";
	public static final String ITEM100MP_TO_MP_MESSAGE = "Exchange to 100MP is succeeded!";
	public static final String MP_TO_ITEM1000MP_MESSAGE = "Exchange to 1000MPItem is succeeded!";
	public static final String MP_TO_ITEM1000MP_CANCEL_MESSAGE = "Exchange to 1000MPItem is failed.";
	public static final String MP_TO_ITEM100MP_MESSAGE = "Exchange to 100MPItem is succeeded!";
	public static final String MP_TO_ITEM100MP_CANCEL_MESSAGE = "Exchange to 100MPItem is failed.";

	//config関連
	public static boolean textureSize;
	public static int textureSizeFile;
	public static boolean moreMPdropAdd;
	public static int moreMPdropID;
	public static boolean respawn0MP;
	public static int respawnUseMP;
	//public static boolean useIC2GregMP

	/**
	 *PreInit
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		BASLogger.BASLoading("Start loading...");

		/**
		 *Config作成・設定
		 */
		BASLogger.BASLoading("Now loading config.");
		Configuration cfg=new Configuration(e.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			//ブロックID・アイテムID
			Property blockProp[]=
			{
				cfg.getBlock("blockBankID", 2550),
				cfg.getBlock("blockSafeID", 2551),
			};
			Property itemProp[]={
				cfg.getItem("item100MPID",12756),
				cfg.getItem("item1000MPID",12757),
				cfg.getItem("itemMPWandID",12758)
			};
			itemProp[1].comment="These are same as game ID because it is subtracting 256 internally.";

			//テクスチャx16 or x32
			textureSize = cfg.get(cfg.CATEGORY_GENERAL,
						"Will you use x32 for the texture?",
						false).getBoolean(false);
				//cfg.getBlock("blockMPID", 2552)
			if(textureSize)
			{
				textureSizeFile = 32;
			}
			else
			{
				textureSizeFile = 16;
			}

			//MP増加エンチャントを有効化するか
			moreMPdropAdd = cfg.get(cfg.CATEGORY_GENERAL,
						"Will Enchant [More MP Drop] be added?",
						true).getBoolean(true);
			//MP増加エンチャントのID
			moreMPdropID = cfg.get(cfg.CATEGORY_GENERAL,
						"moreMPdropID", 255).getInt();

			//リスポーン時に0MPにするかどうか
			respawn0MP = cfg.get(cfg.CATEGORY_GENERAL,
						"When you respawn, will the MP be 0?",
						false).getBoolean(false);
			//リスポーン時にMPを消費するかどうか
			respawnUseMP = cfg.get(cfg.CATEGORY_GENERAL,
						"When you respawn,how much MP will be needed?",
						10).getInt();
			//IC2とGreg導入時、MPで商品を買えるのを許可するか
			/*
			useIC2GregMP = cfg.get(config.CATEGORY_GENERAL,
						"When you are using Gregtech, can you buy the IC2 block with MP?",
						false).getBoolean(false);
			*/
			blockBankID=blockProp[0].getInt();
			blockSafeID=blockProp[1].getInt();
			//blockMPID=blockProp[2].getInt();
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
			if (cfg.hasChanged())
			{
				cfg.save();
			}
		}

		/**
		 *Block・Item追加
		 */
		//別クラス化のときは「(new クラス名()).registerBlocks();」
		//System.out.println("[BankAndSafe for MCEconomy] Adding blocks and items.");
		BASLogger.BASLoading("Now registering Blocks and Items.");
		/*blockBank = new BlockBank(blockBankID, Material.iron);
		blockSafe = new BlockSafe(blockSafeID, Material.iron);
		//blockMP = new BlockMP(blockMPID, Material.sponge)
		item100MP = new Item100MP(item100MPID-256);
		item1000MP = new Item1000MP(item1000MPID-256);
		itemMPWand = new ItemMPWand(itemMPWandID-256);
		GameRegistry.registerBlock(blockBank, "blockBank");
		//GameRegistry.registerBlock(blockSafe, ItemBlockSafe.class, "blockSafe");
		GameRegistry.registerBlock(blockSafe, "blockSafe");
		//GameRegistry.registerBlock(blockMP, ItemBlockMP.class, "blockMP");
		GameRegistry.registerItem(item100MP, "item100MP");
		GameRegistry.registerItem(item1000MP, "item1000MP");
		GameRegistry.registerItem(itemMPWand, "itemMPWand");*/
	}

	/**
	 *Init
	 */
	@EventHandler
	public void eventInit(FMLInitializationEvent e)
	{
		//System.out.println("[BankAndSafe for MCEconomy] Setting up contents.");
		BASLogger.BASLoading("Now setting up contents.");
		blockBank = new BlockBank(blockBankID, Material.iron);
		blockSafe = new BlockSafe(blockSafeID, Material.iron);
		//blockMP = new BlockMP(blockMPID, Material.sponge)
		item100MP = new Item100MP(item100MPID-256);
		item1000MP = new Item1000MP(item1000MPID-256);
		itemMPWand = new ItemMPWand(itemMPWandID-256);
		GameRegistry.registerBlock(blockBank, "blockBank");
		//GameRegistry.registerBlock(blockSafe, ItemBlockSafe.class, "blockSafe");
		GameRegistry.registerBlock(blockSafe, "blockSafe");
		//GameRegistry.registerBlock(blockMP, ItemBlockMP.class, "blockMP");
		GameRegistry.registerItem(item100MP, "item100MP");
		GameRegistry.registerItem(item1000MP, "item1000MP");
		GameRegistry.registerItem(itemMPWand, "itemMPWand");

		/**
		 *MP増加エンチャント
		 */
		if (moreMPdropAdd)
		{
			moreMPdrop = (new EnchantmentMoreMPdrop(moreMPdropID, 2)).setName("moreMPdrop");
		}
		/**
		 *敵を倒したときのドロップMP
		 */
		MinecraftForge.EVENT_BUS.register(new LivingDeathEventHandler());
		/**
		 *リスポーン時にMPを0にする/ワールドに入ったときにMPが0未満だったときに0にする
		 */
		MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
		/**
		 *言語登録
		 */
		System.out.println("[BankAndSafe for MCEconomy] Registering languages.");
		//Localization.addLocalization("/bankandsafe/lang/", DefaultProps.DEFAULT_LANGUAGE);
		//別クラス化のときは「(new LangRegister()).lang();」
		LanguageRegistry.addName(blockBank, "MPBank");
		LanguageRegistry.addName(blockSafe, "MPSafe");
		//LanguageRegistry.addName(new ItemStack(blockMP, 1, 0), "100MP Coin Block");
		//LanguageRegistry.addName(new ItemStack(blockMP, 1, 4), "1000MP Bill Block");
		LanguageRegistry.addName(item100MP, "100MP Coin");
		LanguageRegistry.addName(item1000MP, "1000MP Bill");
		LanguageRegistry.addName(itemMPWand, "MPWand");

		/**
		 *TileEntity登録
		 */
		//GameRegistry.registerTileEntity(BlockBank.class, "blockBank");
		GameRegistry.registerTileEntity(TileEntitySafe.class, "containerSafe");
		/**
		 *GUI追加
		 */
		NetworkRegistry.instance().registerGuiHandler(this, guihandler);
		/**
		 * エンチャント登録
		 */
		MinecraftForge.EVENT_BUS.register(this);
	}

	/**
	 *PostInit
	 */
	@EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
		//System.out.println("[BankAndSafe for MCEconomy] Setting up plugins.");
		BASLogger.BASLoading("Start setting up plugins.");
		/*
		if (Loader.isModLoaded("SextiarySector"))
		{
			BASLogger.checkingMod("SextiarySector");
			try
			{
				//SS連携読み込み
				BASLogger.loadedMod("SextiarySector");
				SSPlugin.load();
			}
			catch (Exception e1)
			{
				BASLogger.failedMod("SextiarySector");
				System.out.println("[BankAndSafe]Failed to check SextiarySector");
				e1.printStackTrace(System.err);
			}
		}
		*/

		/*
		if(Loader.isModLoaded("IC2"))
		{
			BASLogger.checkingMod("IndustrialCraft2");
			if(Loader.isModLoaded("gregtech_addon") && BankAndSafe.useIC2GregMP)
			{
				try
				{
					//Configオン+IC2+Gregで読み込み
					BASLogger.loadedMod("IndustrialCraft2");
					IC2Plugin.load();
				}
				catch (Exception e2)
				{
					BASLogger.failedMod("IndustrialCraft2");
					e.printStackTrace(System.err);
				}
			}
			else if(Load.isModLoaded("gregtech_addon") && !BankAndSafe.useIC2GregMP)
			{
				//Configオフ+IC2+Greg、読み込みなし
				BASLogger.log("Won't check for IndustrialCraft2");
				BASLogger.log("If you wanna buy the IndustrialCraft2's Items, change the config useIC2GregMP to true");
			}
			else
			{
				try
				{
					//IC2連携読み込み
					BASLogger.loadedMod("IndustrialCraft2");
					IC2Plugin.load();
				}
				catch (Exception e3)
				{
					BASLogger.failedMod("IndustrialCraft2");
					e.printStackTrace(System.err);
				}
			}
		}

		if(Loader.isModLoaded("BuildCraft|Core"))
		{
			BASLogger.checkingMod("BuildCraft");
			try
			{
				//BC連携読み込み
				BASLogger.loadedMod("BuildCraft");
				BCPlugin.load();
			}
			catch (Exception e4)
			{
				BASLogger.failedMod("BuildCraft");
				e.printStackTrace(System.err);
			}
		}

		if(Loader.isModLoaded("EnderIO"))
		{
			BASLogger.checkingMod("EnderIO")
			try
			{
				//EIO連携読み込み
				BASLogger.loadedMod("EnderIO");
				EIOPlugin.load();
			}
			catch (Exception e5)
			{
				BASLogger.failedMod("EnderIO");
				e.printStackTrace(System.err);
			}
		}*/
    }
}