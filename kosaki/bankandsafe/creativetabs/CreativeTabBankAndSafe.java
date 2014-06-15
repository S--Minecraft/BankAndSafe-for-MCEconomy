package kosaki.bankandsafe.creativetabs;

import kosaki.bankandsafe.BankAndSafe;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabBankAndSafe extends CreativeTabs
{
	public CreativeTabBankAndSafe(String label)
	{
		super(label);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return BankAndSafe.item100MP;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "BankAndSafe";
	}

}