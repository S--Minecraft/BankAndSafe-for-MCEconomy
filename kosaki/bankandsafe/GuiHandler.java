package kosaki.bankandsafe;

import kosaki.bankandsafe.guis.ContainerBlockBank;
import kosaki.bankandsafe.guis.ContainerBlockSafe;
import kosaki.bankandsafe.guis.GuiBlockBank;
import kosaki.bankandsafe.guis.GuiBlockSafe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == BankAndSafe.bankGUIID)
		{
			return new GuiBlockBank(player, world, x, y, z);
		}
		else if(ID == BankAndSafe.safeGUIID)
		{
			return new GuiBlockSafe(player, world, x, y, z);
		}

		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == BankAndSafe.bankGUIID)
		{
			return new ContainerBlockBank(player, world, x, y, z);
		}
		else if(ID == BankAndSafe.safeGUIID)
		{
			return new ContainerBlockSafe(player, world, x, y, z);
		}

		return null;
	}
}