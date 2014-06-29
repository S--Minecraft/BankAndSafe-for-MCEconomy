package kosaki.bankandsafe.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentMoreMPdrop extends Enchantment
{
	public EnchantmentMoreMPdrop(int id, int weight)
	{
		super(id, weight, EnumEnchantmentType.weapon);
	}
	
	public int getMaxLevel()
	{
		return 3;
	}
	
	public int getMinEnchantability(int par1)
	{
		return 15 + (par1 - 1) * 12;
	}
	
	public int getMaxEnchantability(int par1)
	{
		return getMinEnchantability(par1) + 60;
	}
	
	public boolean canApplyTogether(Enchantment enchantment)
	{
		return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != looting.effectId;
	}
}
