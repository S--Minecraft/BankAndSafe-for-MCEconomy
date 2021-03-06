package kosaki.bankandsafe.items;

import kosaki.bankandsafe.BankAndSafe;
import mceconomy.api.MCEconomyAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Item100MP extends Item
{
	public Item100MP(int par1)
	{
		super(par1);
		this.setCreativeTab(BankAndSafe.tabBankAndSafe);
		this.setUnlocalizedName("Item100MP");
		this.setTextureName("bankandsafe:100MP - " + BankAndSafe.textureSizeFile);
		this.setMaxStackSize(64);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		//右クリックでアイテムから内部に（実装中）
		//アイテム削除
		if (!player.capabilities.isCreativeMode)
		{
			--item.stackSize;

		}
		//内部に100MP追加
		MCEconomyAPI.addPlayerMP(player, 100);
		//チャットで追加されたと表示
		if (!world.isRemote && player != null)
		{
			player.addChatMessage(BankAndSafe.ITEM100MP_TO_MP_MESSAGE);
		}
		return item;
	}
}