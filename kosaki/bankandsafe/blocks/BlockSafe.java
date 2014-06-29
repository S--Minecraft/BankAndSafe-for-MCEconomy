/*
package kosaki.bankandsafe.blocks;

import kosaki.bankandsafe.BankAndSafe;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSafe extends TileEntity
{
	@SideOnly(Side.CLIENT)
	private Icon frontIcon;
	private Icon blockIcon;

	public BlockSafe(int par1, Material par2Material)
	{
		super(par1, par2Material);
		this.setCreativeTab(BankAndSafe.tabBankAndSafe);
		this.setUnlocalizedName("blockSafe");
		//this.setTextureName("bankandsafe:Safe - " + BankAndSafe.textureSizeFile);
	}

	/**
	 *GUI
	 *//*
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float disX, float disY, float disZ)
	{
		//GUIを開く
		if (!world.isRemote)
		{
			player.openGui(BankAndSafe.instance, BankAndSafe.safeGUIID, world, x, y, z);
		}
		return true;
	}

	/**
	 *コンテナ関連
	 */
	//TileEntity生成
/*
	public TileEntity createNewTileEntity(World world) {
		return new TileEntity();
	}
*/
	//ブロックが壊れたら、中のアイテムをまき散らす
/*
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		TileEntity tileentity = (TileEntity) par1World.getBlockTileEntity(par2, par3, par4);

		if (tileentity != null)
		{
			for (int j1 = 0; j1 < tileentity.getSizeInventory(); ++j1)
			{
				ItemStack itemstack = tileentity.getStackInSlot(j1);
				if (itemstack != null)
				{
					float f = par1World.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = par1World.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = par1World.rand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0)
					{
						int k1 = par1World.rand.nextInt(21) + 10;
						if (k1 > itemstack.stackSize)
						{
							k1 = itemstack.stackSize;
						}
						itemstack.stackSize -= k1;
						EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));
						if (itemstack.hasTagCompound())
						{
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}
						float f3 = 0.05F;
						entityitem.motionX = (double)((float)par1World.rand.nextGaussian() * f3);
						entityitem.motionY = (double)((float)par1World.rand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double)((float)par1World.rand.nextGaussian() * f3);
						par1World.spawnEntityInWorld(entityitem);
					}
				}
			}
			par1World.func_96440_m(par2, par3, par4, par5);
		}
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	/**
	 *テクスチャ
	 *//*
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
		//return par1 == 1 ? this.blockIcon : (par1 == 0 ? this.blockIcon : (par2 == 2 && par1 == 2 ? this.blockFront : (par2 == 3 && par1 == 5 ? this.blockFront : (par2 == 0 && par1 == 3 ? this.blockFront : (par2 == 1 && par1 == 4 ? this.blockFront : this.blockIcon)))));
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

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		//表示面のテクスチャの設定
		//blockFront:前面　blockIcon:その他
		this.blockIcon = par1IconRegister.registerIcon("bankandsafe:Side - " + BankAndSafe.textureSizeFile);
		this.frontIcon = par1IconRegister.registerIcon("bankandsafe:Safe - " + BankAndSafe.textureSizeFile);
	}
}
*/