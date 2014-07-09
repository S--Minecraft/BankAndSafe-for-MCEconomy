package kosaki.bankandsafe.items;
 
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
 
public class ItemBlockMP extends ItemBlock
{
	public ItemBlockMP(int par1)
	{
		super(par1);
		this.setMaxDamage(8);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName() + "_" + par1ItemStack.getItemDamage();
	}
}