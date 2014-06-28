package kosaki.bankandsafe;

import mceconomy.api.MCEconomyAPI;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

//public class WorldEventHandler
public class WorldEventHandler implements IPlayerTracker
{
	//@ForgeSubscribe
	/*
	public void onPlayerEntertoWorld(WorldEvent event)
	{
		if(MCEconomyAPI.getPlayerMP(entityPlayer)<0)
		{
			MCEconomyAPI.setPlayerMP(entityPlayer,0);
		}
	}

	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
		{
			if(MCEconomyAPI.getPlayerMP(entityPlayer)<0)
			{
				MCEconomyAPI.setPlayerMP(entityPlayer,0);
			}
		}
	}
	*/

	//ワールドに入ったときにMPが0未満だったときに0にする
	@Override
	public void onPlayerLogin(EntityPlayer entityPlayer)
	{
		if(MCEconomyAPI.getPlayerMP(entityPlayer)<0)
		{
			MCEconomyAPI.setPlayerMP(entityPlayer,0);
		}
	}

	//リスポーン時にMPを0にする(configから変更可)
	@Override
	public void onPlayerRespawn(EntityPlayer entityPlayer)
	{
		if(BankAndSafe.respawn0MP)
		{
			MCEconomyAPI.setPlayerMP(entityPlayer,0);
		}
	}

	//IPlayerTracker実装の上で必須メソッド
	@Override
	public void onPlayerLogout(EntityPlayer entityPlayer)
	{

	}

	//上に同じ
	@Override
	public void onPlayerChangedDimension(EntityPlayer entityPlayer)
	{

	}
}
