/*
package kosaki.bankandsafe.tileentities;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBank extends TileEntity implements ISidedInventory
{
	private static final int slots_righttop = new int;
	private static final int slots_rightbottom = new int;
	private static final int slots_lefttop = new int;
	private static final int slots_leftbottom = new int;

	public ItemStack Item100MPStacks = new ItemStack;
	public ItemStack Item1000MPStacks = new ItemStack;

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);

		//アイテムの読み込み
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		this.sampleItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.sampleItemStacks.length)
			{
				this.sampleItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}
	// スロット数
	@Override
	public int getSizeInventory() {
		return this.sampleItemStacks.length;
	}
	// インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int par1) {
		return this.sampleItemStacks[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (this.sampleItemStacks[par1] != null)
		{
			ItemStack itemstack;

			if (this.sampleItemStacks[par1].stackSize <= par2)
			{
				itemstack = this.sampleItemStacks[par1];
				this.sampleItemStacks[par1] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.sampleItemStacks[par1].splitStack(par2);

				if (this.sampleItemStacks[par1].stackSize == 0)
				{
					this.sampleItemStacks[par1] = null;
				}
				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.sampleItemStacks[par1] != null)
		{
			ItemStack itemstack = this.sampleItemStacks[par1];
			this.sampleItemStacks[par1] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		this.sampleItemStacks[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	// インベントリの名前
	@Override
	public String getInvName() {
		return "Sample";
	}

	// 多言語対応かどうか
	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	// インベントリ内のスタック限界値
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void onInventoryChanged() {
		this.onInventoryChanged();
	}

	// par1EntityPlayerがTileEntityを使えるかどうか
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		return par1 == 2 ? false : (par1 == 1 ? this.isItemFuel(par2ItemStack) : true);
	}

	//ホッパーにアイテムの受け渡しをする際の優先度
	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
	}

	//ホッパーからアイテムを入れられるかどうか
	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
		return this.isItemValidForSlot(par1, par2ItemStack);
	}

	//隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
		return par3 != 0 || par1 != 1 || par2ItemStack.itemID == Item.bucketEmpty.itemID;
	}
}
*/