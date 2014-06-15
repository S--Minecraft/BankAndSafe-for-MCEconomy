package kosaki.bankandsafe.guis;

import kosaki.bankandsafe.BankAndSafe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public class ContainerBlockBank extends Container
{
	private World world;
	private int xCoord;
	private int yCoord;
	private int zCoord;

	public ContainerBlockBank(EntityPlayer player, World world, int x, int y, int z)
	{
		this.world  = world;
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return this.world.getBlockId(this.xCoord, this.yCoord, this.zCoord) != BankAndSafe.blockBank.blockID ? false
			: entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
}