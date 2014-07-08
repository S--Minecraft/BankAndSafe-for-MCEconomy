package kosaki.bankandsafe.blocks;

import kosaki.bankandsafe.BankAndSafe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBank extends Block
{
	@SideOnly(Side.CLIENT)
	private Icon frontIcon;
	private Icon blockIcon;

	public BlockBank(int par1, Material par2Material)
	{
		super(par1, par2Material);
		this.setCreativeTab(BankAndSafe.tabBankAndSafe);
		this.setUnlocalizedName("blockBank");
		//this.setTextureName("bankandsafe:Bank - " + BankAndSafe.textureSizeFile);
	}

	/**
	 *GUI
	 */
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

	/**
	 *テクスチャ
	 */
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		//向きがあるように設定・東西南北固定をなくす
		int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		//表示面の方向を決定
		if(par1==0 || par1==1)
		{
			return blockIcon;
		}
		else if((par1==2 && par2==2) || (par1==5 && par2==3) || (par1==3 && par2==0) || (par1==4 && par2==1))
		{
			return this.frontIcon;
		}
		else
		{
			return this.blockIcon;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		//表示面のテクスチャの設定
		//blockFront:前面　blockIcon:その他
		this.blockIcon = par1IconRegister.registerIcon("bankandsafe:Side - " + BankAndSafe.textureSizeFile);
		this.frontIcon = par1IconRegister.registerIcon("bankandsafe:Bank - " + BankAndSafe.textureSizeFile);
	}
}