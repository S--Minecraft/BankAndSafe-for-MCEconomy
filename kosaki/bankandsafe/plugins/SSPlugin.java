package kosaki.bankandsafe.plugins;

import mceconomy.api.MCEconomyAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import sextiarysector.api.SextiarySectorAPI;
import sextiarysector.api.season.Season;
import cpw.mods.fml.client.FMLClientHandler;

public class SSPlugin
{
<<<<<<< HEAD
	private static World world = FMLClientHandler.instance().getClient().theWorld
	private static EntityPlayer entityPlayer = minecraft.thePlayer;
=======
	private static World world = FMLClientHandler.instance().getClient().theWorld;
	private static EntityPlayer entityPlayer/* = minecraft.thePlayer*/;
>>>>>>> b52d1d1b51fc073249b875369acd55494cad05d3

	public static void load()
	{

		//月給を追加
		//ひと月あたり15MP
		if(SextiarySectorAPI.getDay(world)==30)
		{
			MCEconomyAPI.addPlayerMP(entityPlayer, 15);
		}

		//ボーナスを追加
		//夏は50MP
		if(SextiarySectorAPI.getSeason(world)==Season.SUMMER)
		{
			MCEconomyAPI.addPlayerMP(entityPlayer, 50);
		}
		//冬は30MP
		if(SextiarySectorAPI.getSeason(world)==Season.WINTER)
		{
			MCEconomyAPI.addPlayerMP(entityPlayer, 30);
		}

		//トロフィー取得時にMP取得
		
	}
}