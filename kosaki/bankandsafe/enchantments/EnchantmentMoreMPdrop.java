package kosaki.bankandsafe.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentMoreMPdrop extends Enchantment
{
	public EnchantmentMoreMPdrop(int id, int weight)
	{
		super(id, weight, EnumEnchantmentType.weapon);
		this.setName("moreMPdrop");
	}

	@Override
	public int getMaxLevel()
	{
		return 3;
	}

	@Override
	public int getMinEnchantability(int par1)
	{
		return 15 + (par1 - 1) * 12;
	}

	@Override
	public int getMaxEnchantability(int par1)
	{
		return getMinEnchantability(par1) + 60;
	}

	@Override
	public boolean canApplyTogether(Enchantment enchantment)
	{
		return super.canApplyTogether(enchantment) && enchantment.effectId != looting.effectId;
	}
}
