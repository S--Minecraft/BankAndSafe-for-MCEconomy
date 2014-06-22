package kosaki.bankandsafe.plugins;

import mceconomy.api.MCEconomyAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import sextiarysector.api.SextiarySectorAPI;
import sextiarysector.api.season.Season;

public class SSPlugin
{
	private static World world;
	private static EntityPlayer entityPlayer;

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
		if(SextiarySectorAPI.getSeason(world)==summer)
		{
			MCEconomyAPI.addPlayerMP(entityPlayer, 50);
		}
		//冬は30MP
		if(SextiarySectorAPI.getSeason(world)==winter)
		{
			MCEconomyAPI.addPlayerMP(entityPlayer, 30);
		}

		//トロフィー取得時にMP取得

	}
}