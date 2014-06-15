package kosaki.bankandsafe.guis;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBlockBank extends GuiContainer
{
	public GuiBlockBank(EntityPlayer player, World world, int x, int y, int z)
	{
		super(new ContainerBlockBank(player, world, x, y, z));
	}

	protected void drawGuiContainerForegroundLayer()
	{
		fontRenderer.drawString("Bank", 58, 6, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		this.mc.getTextureManager().bindTexture(new ResourceLocation ("bankandsafe", "textures/gui/guiBank.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int xStart = width - xSize >> 1;
		int yStart = height - ySize >> 1;
		drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}
}