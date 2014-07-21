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
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.OreDictionary;
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

@Mod
(
	modid="BankAndSafe",
	name="BankAndSafe for MCEconomy",
	version="0.0.1_Alpha",
	dependencies="required-after:Forge@[9.10,);required-after:FML@[6.2,);after:MCEconomy"
	//after:IC2;after:Forestry;after:SextiarySector;after:BuildCraft|Core;after:EnderIO
	//required-
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false
)
public class BankAndSafe
{
	/**
	 *自体のインスタンス・IDのフィールド等
	 */
	@Instance("BankAndSafe")
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

	//public static Achivement getMPWand;
	//public static Achivement ExchangeMPWand;
	//public static Achivement getMPSafe;
	//public static Achivement getMPBank;
	//public static Achivement moreMPdrop;
	//public static Achivement To100,000MP;
	//public static Achivement To1,000,000MP;
	//public static Achivement To100,000,000MP;
	//http://minecraftjp.info/modding/index.php/%E5%AE%9F%E7%B8%BE%E3%81%AE%E8%BF%BD%E5%8A%A0

	//ID
	public int blockBankID;
	public int blockSafeID;
	//public int blockMPID;
	public int item100MPID;
	public int item1000MPID;
	public int itemMPWandID;

	public static final int bankGUIID = 0;
	public static final int safeGUIID = 1;

	//メッセージ
	public static final String ITEM1000MP_TO_MP_MESSAGE = "Exchange to 1000MP is succeeded!";
	public static final String ITEM100MP_TO_MP_MESSAGE = "Exchange to 100MP is succeeded!";
	public static final String MP_TO_ITEM1000MP_MESSAGE = "Exchange to 1000MPItem is succeeded!";
	public static final String MP_TO_ITEM1000MP_CANCEL_MESSAGE = "Exchange to 1000MPItem is failed.";
	public static final String MP_TO_ITEM100MP_MESSAGE = "Exchange to 100MPItem is succeeded!";
	public static final String MP_TO_ITEM100MP_CANCEL_MESSAGE = "Exchange to 100MPItem is failed.";


	/*
	 * ModIDを渡して新しいチャネルを取得する.
	 * このインスタンスは引数で与えたModIDのMod専用のチャネルになる.
	 */
	//public static final SimpleNetworkWrapper packetDispatcher = NetworkRegistry.INSTANCE.newSimpleChannel("BankAndSafe");

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
				cfg.getBlock("blockSafeID", 2551)
				//,cfg.getBlock("blockMPID", 2552)
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
						"Enchant[More MP Drop]ID", 255).getInt();

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
		BASLogger.BASLoading("Now registering Blocks and Items.");
		blockBank = new BlockBank(blockBankID, Material.iron);
		blockSafe = new BlockSafe(blockSafeID, Material.iron);
		//blockMP = new BlockMP(blockMPID, Material.sponge)
		item100MP = new Item100MP(item100MPID-256);
		item1000MP = new Item1000MP(item1000MPID-256);
		itemMPWand = new ItemMPWand(itemMPWandID-256);
		GameRegistry.registerBlock(blockBank, "blockBank");
		GameRegistry.registerBlock(blockSafe, "blockSafe");
		//GameRegistry.registerBlock(blockSafe, ItemBlockSafe.class, "blockSafe");
		//GameRegistry.registerBlock(blockMP, ItemBlockMP.class, "blockMP");
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
		BASLogger.BASLoading("Now setting up contents.");
		/**
		 *MP増加エンチャント
		 */
		if (moreMPdropAdd)
		{
			moreMPdrop = new EnchantmentMoreMPdrop(moreMPdropID, 2);
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
		 *
		 *langファイルへ移動
		 */
		//BASLogger.BASLoading("[BankAndSafe for MCEconomy] Registering languages.");
		/*
		LanguageRegistry.addName(blockBank, "MPBank");
		LanguageRegistry.addName(blockSafe, "MPSafe");
		//LanguageRegistry.addName(new ItemStack(blockMP, 1, 0), "100MP Coin Block");
		//LanguageRegistry.addName(new ItemStack(blockMP, 1, 4), "1000MP Bill Block");
		LanguageRegistry.addName(item100MP, "100MP Coin");
		LanguageRegistry.addName(item1000MP, "1000MP Bill");
		LanguageRegistry.addName(itemMPWand, "MPWand");
		*/
		/**
		 *TileEntity登録
		 */
		GameRegistry.registerTileEntity(TileEntitySafe.class, "containerSafe");
		/**
		 *GUI追加
		 */
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
		/**
		 *パケット追加
		 */
		//packetDispatcher.registerMessage(GuiBlockBankButtonPacketHandler.class, GuiBlockBankButtonPacket.class, 0, Side.SERVER);
		/**
		 * エンチャント登録
		 */
		MinecraftForge.EVENT_BUS.register(this);
		/**
		 * 鉱石辞書登録
		 */
		/*
		 * moneyMP100		=	100MPCoin
		 * moneyMP1000		=	1000MPBill
		 */
		OreDictionary.registerOre("moneyMP100", new ItemStack(this.item100MP));
		OreDictionary.registerOre("moneyMP1000", new ItemStack(this.item1000MP));
		/**
		 * レシピ登録
		 */
		//MPの杖
		GameRegistry.addRecipe(new ItemStack(this.itemMPWand, 1),
								new Object[]{ " X ",
											  "YZY",
											  "YYY",
											  'X',Item.goldNugget,//金塊
											  'Y',new ItemStack(Item.dyePowder, 1, 4),//ブレイズロッド
											  'Z',Item.blazeRod});//ラピスラズリ
		//MP金庫
		GameRegistry.addRecipe(new ItemStack(this.blockSafe, 1),
								new Object[]{ "XYX",
											  "XZX",
											  "SSS",
											  'X',this.item100MP,//100MP玉
											  'Y',Block.tripWireSource,//トリップワイヤーフック
											  'Z',Block.obsidian,//黒曜石
											  'S',Block.stoneBrick});//石レンガ
		//MP銀行
		GameRegistry.addRecipe(new ItemStack(this.blockBank, 1),
								new Object[]{ "XYX",
											  "XZX",
											  "SSS",
											  'X',this.item1000MP,//1000MP札
											  'Y',Block.tripWireSource,//トリップワイヤーフック
											  'Z',Block.obsidian,//黒曜石
											  'S',Block.stoneBrick});//石レンガ
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
