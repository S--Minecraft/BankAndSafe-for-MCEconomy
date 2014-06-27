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
        return par1 == 1 ? this.blockIcon : (par1 == 0 ? this.blockIcon : (par2 == 2 && par1 == 2 ? this.blockFront : (par2 == 3 && par1 == 5 ? this.blockFront : (par2 == 0 && par1 == 3 ? this.blockFront : (par2 == 1 && par1 == 4 ? this.blockFront : this.blockIcon)))));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        //表示面のテクスチャの設定
        //blockFront:前面　blockIcon:その他
        this.blockIcon = par1IconRegister.registerIcon("BankAndSafe:Side - " + BankAndSafe.textureSizeFile);
        this.blockFront = par1IconRegister.registerIcon("BankAndSafe:Bank - " + BankAndSafe.textureSizeFile);
    }
}