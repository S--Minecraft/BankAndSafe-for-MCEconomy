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
	private static final ResourceLocation guiTextures = new ResourceLocation("bankandsafe", "textures/guis/guiBank.png");

	public GuiBlockBank(EntityPlayer player, World world, int x, int y, int z)
	{
		super(new ContainerBlockBank(player, world, x, y, z));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRenderer.drawString("Bank", 58, 6, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
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

	//ボタン追加
	/*
	@Override
	public void initGui() {
		super.initGui();
		//make buttons
		//id, x, y, width, height, text
		controlList.add(new GuiButton(1, 10, 52, 20, 20, "+"));
		controlList.add(new GuiButton(2, 40, 72, 20, 20, "-"));
	}

	protected void actionPerformed(GuiButton guibutton) {
		//id is the id you give your button
		switch(guibutton.id) {
		case 1:
			i += 1;
			break;
		case 2:
			i -= 1;
		}
		//Packet code here
		//PacketDispatcher.sendPacketToServer(packet); //send packet
	}
	*/
}