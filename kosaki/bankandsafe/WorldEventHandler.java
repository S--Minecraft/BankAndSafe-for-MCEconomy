package kosaki.bankandsafe;

import mceconomy.api.MCEconomyAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

public class WorldEventHandler
{
	private static EntityPlayer entityPlayer;

	@ForgeSubscribe
	public void onPlayerEntertoWorld(WorldEvent event)
	{
		if(MCEconomyAPI.getPlayerMP(entityPlayer)<0)
		{
			MCEconomyAPI.setPlayerMP(entityPlayer,0);
		}
	}
}
