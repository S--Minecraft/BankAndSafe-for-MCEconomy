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
		this.setTextureName("bankandsafe:MPWand - " + BankAndSafe.textureSizeFile);
		this.setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		//右クリックで1000MPを内部からアイテムに(実装中）
		
		//1000MP以上ある場合、処理する
		if(MCEconomyAPI.getPlayerMP(player)>1000)
		{
			//1000MP内部から引く
			MCEconomyAPI.reducePlayerMP(player, 1000);
			//1000MPアイテムを追加する
			if (!player.inventory.addItemStackToInventory(BankAndSafe.item1000MP))
			{
				player.dropPlayerItem(BankAndSafe.item1000MP);
			}
			//チャットで追加されたと表示
			if (!world.isRemote)
			{
				if (player != null)
				player.addChatMessage(BankAndSafe.MP_TO_1000ITEM_MESSAGE);
			}
		}else{
		//チャットでエラーを表示
		if (!world.isRemote)
			{
				if (player != null)
				player.addChatMessage(BankAndSafe.MP_TO_1000ITEM_CANCEL_MESSAGE);
			}
		}
		return item;
	}
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        //左クリックで100MPを内部からアイテムに(実装中)
        //100MP以上ある場合処理
		if(MCEconomyAPI.getPlayerMP(par2EntityPlayer)>100)
		{
			//100MP内部から引く
			if (!par2EntityPlayer.inventory.addItemStackToInventory(BankAndSafe.item100MP))
			{
				par2EntityPlayer.dropPlayerItem(BankAndSafe.item100MP);
			}
			//100MPアイテムを追加する
			MCEconomyAPI.reducePlayerMP(par2EntityPlayer, 100);
			//チャットで追加されたと表示
			if (!par3world.isRemote)
			{
				if (par2EntityPlayer != null)
				par2EntityPlayer.addChatMessage(BankAndSafe.MP_TO_100ITEM_MESSAGE);
			}
		}else{
		//チャットでエラーを表示
		if (!par3world.isRemote)
			{
				if (par2EntityPlayer != null)
				par2EntityPlayer.addChatMessage(BankAndSafe.MP_TO_100ITEM_CANCEL_MESSAGE);
			}
		}
        return false;
    }
}
