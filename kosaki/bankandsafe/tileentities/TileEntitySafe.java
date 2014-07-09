package kosaki.bankandsafe.tileentities;

import kosaki.bankandsafe.BankAndSafe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

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
	
	
	//駄目だ…わからん
}