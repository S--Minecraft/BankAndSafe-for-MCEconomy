package kosaki.bankandsafe;

import mceconomy.api.MCEconomyAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

public class WorldEventHandler
{
	@ForgeSubscribe
	/*
	public void onPlayerEntertoWorld(WorldEvent event)
	{
		if(MCEconomyAPI.getPlayerMP(entityPlayer)<0)
		{
			MCEconomyAPI.setPlayerMP(entityPlayer,0);
		}
	}
	*/
	/*
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
	public void onPlayerLogin(EntityPlayer player)
	{
		if(MCEconomyAPI.getPlayerMP(entityPlayer)<0)
			{
				MCEconomyAPI.setPlayerMP(entityPlayer,0);
			}
	}
}
