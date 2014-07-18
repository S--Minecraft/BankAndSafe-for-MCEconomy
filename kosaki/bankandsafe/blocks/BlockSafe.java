package kosaki.bankandsafe.blocks;

import kosaki.bankandsafe.BankAndSafe;
import kosaki.bankandsafe.tileentities.TileEntitySafe;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSafe extends BlockContainer
{
	@SideOnly(Side.CLIENT)
	private Icon frontIcon;

	public BlockSafe(int par1, Material par2Material)
	{
		super(par1, par2Material);
		this.setCreativeTab(BankAndSafe.tabBankAndSafe);
		this.setUnlocalizedName("blockSafe");
	}

	/**
	 *GUI
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are)
	{
		if (world.getBlockTileEntity(x, y, z) == null || player.isSneaking())
		{
			return false;
		}

		if(!world.isRemote)
		{
			//GUIを開く
			player.openGui(BankAndSafe.instance, BankAndSafe.safeGUIID, world, x, y, z);
		}
		return true;
	}

	/**
	 *コンテナ関連
	 */
	//TileEntity生成
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntitySafe();
	}

	//ブロックが壊れたら、中のアイテムを削除
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{

	}

	/**
	 *テクスチャ
	 */
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		//向きがあるように設定・東西南北固定をなくす
		int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		//表示面の方向を決定
		if(par1==0 || par1==1)
		{
			return this.blockIcon;
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

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		//表示面のテクスチャの設定
		//blockFront:前面　blockIcon:その他
		this.blockIcon = par1IconRegister.registerIcon("bankandsafe:Side - " + BankAndSafe.textureSizeFile);
		this.frontIcon = par1IconRegister.registerIcon("bankandsafe:Safe - " + BankAndSafe.textureSizeFile);
	}
}