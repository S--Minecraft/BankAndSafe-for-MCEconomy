package kosaki.bankandsafe.blocks;

import java.util.List;
import java.util.Random;

import kosaki.bankandsafe.BankAndSafe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMP extends Block
{
	@SideOnly(Side.CLIENT)
	private Icon frontIcon0;
	private Icon blockIcon0;
	private Icon frontIcon1;
	private Icon blockIcon1;

	public BlockMP(int par1, Material par2Material)
	{
		super(par1, par2Material);
		this.setCreativeTab(BankAndSafe.tabBankAndSafe);
		//this.setTextureName("bankandsafe:Bank - " + BankAndSafe.textureSizeFile);
	}

	/**
	 *メタデータ
	 */
	@Override
	public int damageDroppped(int metadata)
	{
		//ブロックを壊した時にドロップするもの
		if(metadata<4)
		{
			return 0;
		}
		else if(3<metadata && metadata<8)
		{
			return 4;
		}
		else
		{
			return 0;
		}
	}
	/*
	public int idDropped(int metadata, Random rand, int fortune)
	{
		//ブロックを壊した時にドロップするもの
		if(metadata<4)
		{
			return this.blockID;
		}
		else if(4<metadata)
		{
			return this.blockID;
		}
		else
		{
			return 0;
		}
	}
	*/

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		//メタデータでブロックを追加する
		//par3List.add(new ItemStack(par1, 1, <メタデータ>));
		//0～7を追加(0～3:100MPCoinBlock,4～7:1000MPBillBlock)
		for (int i = 0; i < 8; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
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
		//par1:面　par2:メタデータ
		//0&1:0～16=Other 2:2,5:3,3:0,4:1,
		//表示面の方向を決定
		//return par1 == 1 ? this.blockIcon : (par1 == 0 ? this.blockIcon : (par2 == 2 && par1 == 2 ? this.blockFront : (par2 == 3 && par1 == 5 ? this.blockFront : (par2 == 0 && par1 == 3 ? this.blockFront : (par2 == 1 && par1 == 4 ? this.blockFront : this.blockIcon)))));

		if(par2<4)
		{
			//100MPCoinBlock
		}else{
			//1000MPBillClock
		}
		return this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		//表示面のテクスチャの設定
		//TopBottomIcon0:上下0　SideIcon01:横0　SideIcon02　TopBottomIcon1:上下1　SideIcon11:横1　SideIcon12
		/*this.TopBottomIcon0 = par1IconRegister.registerIcon("bankandsafe:100MP1 - " + BankAndSafe.textureSizeFile);
		this.SideIcon01 = par1IconRegister.registerIcon("bankandsafe:100MP2 - " + BankAndSafe.textureSizeFile);
		this.SideIcon02 = par1IconRegister.registerIcon("bankandsafe:100MP3 - " + BankAndSafe.textureSizeFile);
		this.TopBottomIcon1 = par1IconRegister.registerIcon("bankandsafe:1000MP1 - " + BankAndSafe.textureSizeFile);
		this.SideIcon11 = par1IconRegister.registerIcon("bankandsafe:1000MP2 - " + BankAndSafe.textureSizeFile);
		this.SideIcon12 = par1IconRegister.registerIcon("bankandsafe:1000MP3 - " + BankAndSafe.textureSizeFile);*/
	}
}
