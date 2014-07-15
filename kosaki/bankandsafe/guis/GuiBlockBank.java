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

    /** The X size of the inventory window in pixels. */
    protected int xSize = 176;

    /** The Y size of the inventory window in pixels. */
    protected int ySize = 152;

	public GuiBlockBank(EntityPlayer player, World world, int x, int y, int z)
	{
		super(new ContainerBlockBank(player, world, x, y, z));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		//文字の部分
		fontRenderer.drawString("Bank", 83, 15, 0x404040);
		fontRenderer.drawString(inBankMPString, 85, 80, 0x999999);
		fontRenderer.drawString(playerMPString, 20, 46, 0x999999);
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

	/**
	 * 内部保存部分
	 */
	protected int inBankMP = 0;
	protected int playerMP = 0/* = getPlayerMP(entityPlayer)*/;
	protected String inBankMPString = String.valueOf(inBankMP);
	protected String playerMPString = String.valueOf(playerMP);


}