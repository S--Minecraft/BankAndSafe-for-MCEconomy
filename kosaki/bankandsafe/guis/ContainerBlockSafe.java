package kosaki.bankandsafe.guis;

import kosaki.bankandsafe.BankAndSafe;
import kosaki.bankandsafe.tileentities.TileEntitySafe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerBlockSafe extends Container
{
	private InventoryPlayer playerInventory;
	private IInventory guiInventory = new InventoryBasic("slots", false, 4);
	private TileEntitySafe tileentitysafe;
	private World world;
	private int xCoord;
	private int yCoord;
	private int zCoord;

	public ContainerBlockSafe(EntityPlayer player, World world, int x, int y, int z)
	{
		this.world  = world;
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
	}

	//スロット追加
	public ContainerBlockSafe(EntityPlayer player, TileEntitySafe par2TileEntity) {
		this.tileentitysafe = par2TileEntity;
		// Inventoryで追加するインベントリ
		this.addSlotToContainer(new Slot(this.tileentitysafe, 0, 45, 51));
		this.addSlotToContainer(new Slot(this.tileentitysafe, 1, 45, 87));
		this.addSlotToContainer(new Slot(this.tileentitysafe, 2, 79, 51));
		this.addSlotToContainer(new Slot(this.tileentitysafe, 3, 79, 87));

		int i;
		// 1 ～ 3段目のインベントリ
		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		// 4段目のインベントリ
		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
		}
	}

	// Shiftクリック
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			//スロット番号が1or3の時
			if (par2 == 1 && par2 == 3)
			{
				//アイテムの移動(スロット4～39へ)
				if (!this.mergeItemStack(itemstack1, 4, 39, true))
				{
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			}
			//スロット番号が1or3でない時
			else if (par2 != 1 && par2 != 3)
			{
				//アイテムの移動(スロット1or3へ)
				if (!this.mergeItemStack(itemstack1, 1, 1, false) && !this.mergeItemStack(itemstack1, 3, 3, false))
				{
					return null;
				}
				return null;
			}
			//アイテムの移動(スロット3～39へ)
			else if (!this.mergeItemStack(itemstack1, 3, 39, false))
			{
				return null;
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}
			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}
		return itemstack;
	}

	//開ける距離
	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return this.world.getBlockId(this.xCoord, this.yCoord, this.zCoord) != BankAndSafe.blockSafe.blockID ? false
			: entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
}