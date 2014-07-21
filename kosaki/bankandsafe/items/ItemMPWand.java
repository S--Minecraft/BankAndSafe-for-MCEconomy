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
		this.setFull3D();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		//右クリックで1000MPを内部からアイテムに(実装中）

		//1000MP以上ある場合処理
		if(MCEconomyAPI.getPlayerMP(player)>=1000)
		{
			if (!player.inventory.addItemStackToInventory(new ItemStack(BankAndSafe.item1000MP)))
			{
				//1000MP内部から引く
				MCEconomyAPI.reducePlayerMP(player, 1000);

				//1000MPアイテムを追加する
				player.dropPlayerItem(new ItemStack(BankAndSafe.item1000MP));
			}
			//チャットで追加されたと表示
			if (!world.isRemote && player != null)
			{
				player.addChatMessage(BankAndSafe.MP_TO_ITEM1000MP_MESSAGE);
			}
		}
		else
		{
			//チャットでエラーを表示
			if (!world.isRemote && player != null)
			{
				player.addChatMessage(BankAndSafe.MP_TO_ITEM1000MP_CANCEL_MESSAGE);
			}
		}

		return item;
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		//左クリックで100MPを内部からアイテムに(実装中)

		//100MP以上ある場合処理
		if(MCEconomyAPI.getPlayerMP(par2EntityPlayer)>=100)
		{
			if (!par2EntityPlayer.inventory.addItemStackToInventory(new ItemStack(BankAndSafe.item100MP)))
			{
				//100MP内部から引く
				MCEconomyAPI.reducePlayerMP(par2EntityPlayer, 100);
				//100MPアイテムを追加する
				par2EntityPlayer.dropPlayerItem(new ItemStack(BankAndSafe.item100MP));
			}

			//チャットで追加されたと表示
			if (!par3World.isRemote && par2EntityPlayer != null)
			{
				par2EntityPlayer.addChatMessage(BankAndSafe.MP_TO_ITEM100MP_MESSAGE);
			}
		}
		else
		{
			//チャットでエラーを表示
			if (!par3World.isRemote && par2EntityPlayer != null)
			{
				par2EntityPlayer.addChatMessage(BankAndSafe.MP_TO_ITEM100MP_CANCEL_MESSAGE);
			}
		}
        return false;
    }
}
