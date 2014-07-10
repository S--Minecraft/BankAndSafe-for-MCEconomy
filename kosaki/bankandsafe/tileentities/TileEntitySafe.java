package kosaki.bankandsafe.tileentities;

import kosaki.bankandsafe.guis.ContainerBlockSafe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;


public class TileEntitySafe extends TileEntity implements ISidedInventory
{
	private static ItemStack Item100MPStack;
	private static ItemStack Item1000MPStack;
	private static int slots_100in;
	private static int slots_100out;
	private static int slots_1000in;
	private static int slots_1000out;
	public static int stackLimit;
	private static ContainerBlockSafe container;
	@Override
	public int getSizeInventory() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
	@Override
	public ItemStack getStackInSlot(int i) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public ItemStack decrStackSize(int i, int j) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		// TODO 自動生成されたメソッド・スタブ

	}
	@Override
	public String getInvName() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public boolean isInvNameLocalized() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	@Override
	public int getInventoryStackLimit() {
		// TODO 自動生成されたメソッド・スタブ
		return 64;
	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	@Override
	public void openChest() {
		// TODO 自動生成されたメソッド・スタブ

	}
	@Override
	public void closeChest() {
		// TODO 自動生成されたメソッド・スタブ

	}
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}


	//駄目だ…わからん
}