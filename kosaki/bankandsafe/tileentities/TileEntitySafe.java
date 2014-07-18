package kosaki.bankandsafe.tileentities;

import kosaki.bankandsafe.guis.ContainerBlockSafe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;


public class TileEntitySafe extends TileEntity implements IInventory
{
	private static ItemStack Item100MPStack;
	private static ItemStack Item1000MPStack;
	private static ItemStack tileSlot[];
	private static int slots[];//[0]=100,[1]=1000
	public static int stackLimit;
	private static ContainerBlockSafe container;
    private String customName;
    
    /**
     * IInventory
     */
    @Override
	public int getSizeInventory() {
		return 2;
	}
	@Override
	public ItemStack getStackInSlot(int i) {
        switch(i)
        {
        case 0: return Item100MPStack;
        case 1: return Item1000MPStack;
        }
		return null;
	}
	@Override
	public ItemStack decrStackSize(int slot, int amt)
	{
		 ItemStack stack = getStackInSlot(slot);
		 if (stack != null)
		 {
			 if (stack.stackSize <= amt)
			 {
				 setInventorySlotContents(slot, null);
			 }
			 else
			{
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0)
				{
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}
	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return getStackInSlot(i);
	}
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		tileSlot[0] = Item100MPStack;
		tileSlot[1] = Item1000MPStack;
	}
	@Override
	public String getInvName() {
		return this.isInvNameLocalized() ? this.customName : "container.safe";
	}
	@Override
	public boolean isInvNameLocalized() {
        return this.customName != null && this.customName.length() > 0;
	}
	@Override
	public int getInventoryStackLimit() {
		return stackLimit;
	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	@Override
	public void openChest() {
		//分からん
	}
	@Override
	public void closeChest() {
		//分からん
	}
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}


	//駄目だ…わからん
}