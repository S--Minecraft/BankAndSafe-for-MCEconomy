package kosaki.bankandsafe.blocks;

import kosaki.bankandsafe.BankAndSafe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockBank extends Block
{
	public BlockBank(int par1, Material par2Material)
	{
		super(par1, par2Material);
		this.setCreativeTab(BankAndSafe.tabBankAndSafe);
		this.setUnlocalizedName("blockBank");
		this.setTextureName("bankandsafe:Bank");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float disX, float disY, float disZ)
	{
		//GUIを開く
		if (!world.isRemote)
		{
			player.openGui(BankAndSafe.instance, BankAndSafe.bankGUIID, world, x, y, z);
		}
		return true;
	}
}