package kosaki.bankandsafe.items;

import kosaki.bankandsafe.BankAndSafe;
import mceconomy.api.MCEconomyAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMPWand extends Item
{

	public ItemMPWand(int par1)
	{
		super(par1);
		this.setCreativeTab(BankAndSafe.tabBankAndSafe);
		this.setUnlocalizedName("ItemMPWand");
		this.setTextureName("bankandsafe:MPWand");
		this.setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		//右クリックで1000MPを内部からアイテムに（未実装）

		//1000MP以上ある場合、1000MP内部から引く
		if(MCEconomyAPI.getPlayerMP(player)>1000)
		{
		MCEconomyAPI.reducePlayerMP(player, 1000);
		}

		//アイテム追加
		//

		return item;
	}
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        //左クリックで100MPを内部からアイテムに？(未実装)

		//100MP以上ある場合、100MP内部から引く
		if(MCEconomyAPI.getPlayerMP(par2EntityPlayer)>100)
		{
		MCEconomyAPI.reducePlayerMP(par2EntityPlayer, 100);
		}

		//アイテム追加
		//

        return false;
    }
}
