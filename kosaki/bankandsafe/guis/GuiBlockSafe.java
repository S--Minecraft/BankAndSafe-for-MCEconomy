package kosaki.bankandsafe.guis;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBlockSafe extends GuiContainer
{
	private static final ResourceLocation guiTextures = new ResourceLocation("bankandsafe", "textures/guis/guiSafe.png");

	public GuiBlockSafe(EntityPlayer player, World world, int x, int y, int z)
	{
		super(new ContainerBlockSafe(player, world, x, y, z));
		this.xSize = 175;
		this.ySize = 173;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		//文字の部分
		int x = (xSize - fontRenderer.getStringWidth(StatCollector.translateToLocal("tile.blockSafe.name"))) / 2;
		fontRenderer.drawString(StatCollector.translateToLocal("tile.blockSafe.name"), x, 10, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		//GUI画像の位置
		this.mc.getTextureManager().bindTexture(guiTextures);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int xStart = width - xSize >> 1;
		int yStart = height - ySize >> 1;
		drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}

	static ResourceLocation textureForButton()
    {
        return guiTextures;
    }

}